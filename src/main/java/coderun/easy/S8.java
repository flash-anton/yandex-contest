package coderun.easy;

import java.io.*;
import java.util.*;

/**
 * <pre>
 * <a href="https://coderun.yandex.ru/problem/connectivity-components">8. Компоненты связности</a>
 * легкая dfs
 *
 * Дан неориентированный невзвешенный граф, состоящий из N вершин и M ребер. Необходимо посчитать количество его
 * компонент связности и вывести их.
 *
 * Формат ввода
 * Во входном файле записано два числа N и M (0 < N ≤ 100000, 0 ≤ M ≤ 100000). В следующих M строках записаны
 * по два числа i и j (1 ≤ i, j ≤ N), которые означают, что вершины i и j соединены ребром.
 *
 * Формат вывода
 * В первой строчке выходного файла выведите количество компонент связности. Далее выведите сами компоненты связности
 * в следующем формате: в первой строке количество вершин в компоненте, во второй - сами вершины в произвольном порядке.
 *
 * 16  OK  876 мс / 2 с  52.3 Мб / 256 Мб
 * 20  OK  574 мс / 2 с  72.8 Мб / 256 Мб
 * </pre>
 */
public class S8 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        alg(reader, writer);
        reader.close();
        writer.close();
    }

    public static void alg(BufferedReader reader, BufferedWriter writer) throws IOException {
        String[] buf = reader.readLine().split(" ");
        int N = Integer.parseInt(buf[0]);
        int M = Integer.parseInt(buf[1]);

        Map<Integer, Set<Integer>> G = new HashMap<>(N, 1);
        for (int i = 1; i < N + 1; i++) {
            G.put(i, new HashSet<>());
        }
        for (int i = 0; i < M; i++) {
            buf = reader.readLine().split(" ");
            int a = Integer.parseInt(buf[0]);
            int b = Integer.parseInt(buf[1]);
            if (a != b) {
                G.get(a).add(b);
                G.get(b).add(a);
            }
        }

        List<Set<Integer>> components = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        for (int i = 1; i < N + 1; i++) {
            if (!visited.contains(i)) {
                Set<Integer> component = new HashSet<>();
                dfs(G, visited, i, component);
                components.add(component);
            }
        }

        StringBuilder sb = new StringBuilder().append(components.size());
        for (Set<Integer> component : components) {
            sb.append('\n').append(component.size()).append('\n');
            for (int v : component) {
                sb.append(v).append(' ');
            }
        }

        writer.write(sb.toString());
        writer.flush();
    }

    private static void dfs(Map<Integer, Set<Integer>> G, Set<Integer> visited, int unvisited, Set<Integer> component) {
        visited.add(unvisited);
        component.add(unvisited);
        for (int i : G.get(unvisited)) {
            if (!visited.contains(i)) {
                dfs(G, visited, i, component);
            }
        }
    }
}

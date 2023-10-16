package coderun.easy;

import java.io.*;
import java.util.*;

/**
 * <pre>
 * <a href="https://coderun.yandex.ru/problem/search-in-depth">7. Поиск в глубину</a>
 * легкая dfs
 *
 * Дан неориентированный граф, возможно, с петлями и кратными ребрами. Необходимо построить компоненту связности,
 * содержащую первую вершину.
 *
 * Формат ввода
 * В первой строке записаны два целых числа N (1≤N≤10^3) и M (0≤M≤5*10^5) — количество вершин и ребер в графе.
 * В последующих MM строках перечислены ребра — пары чисел, определяющие номера вершин, которые соединяют ребра.
 *
 * Формат вывода
 * В первую строку выходного файла выведите число K — количество вершин в компоненте связности. Во вторую строку
 * выведите K целых чисел — вершины компоненты связности, перечисленные в порядке возрастания номеров.
 *
 * 19  OK  1 с / 2 с  68.8 Мб / 256 Мб
 * </pre>
 */
public class S7 {
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

        Set<Integer> visited = new HashSet<>();
        dfs(G, visited, 1);

        List<Integer> component = new ArrayList<>(visited);
        Collections.sort(component);

        StringBuilder sb = new StringBuilder();
        component.forEach(i -> sb.append(i).append(' '));

        writer.write(component.size() + "\n" + sb);
        writer.flush();
    }

    private static void dfs(Map<Integer, Set<Integer>> G, Set<Integer> visited, int unvisited) {
        visited.add(unvisited);
        for (int i : G.get(unvisited)) {
            if (!visited.contains(i)) {
                dfs(G, visited, i);
            }
        }
    }
}

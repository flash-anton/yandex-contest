package coderun.easy;

import java.io.*;
import java.util.*;

/**
 * <pre>
 * <a href="https://coderun.yandex.ru/problem/cheating">9. Списывание</a>
 * легкая dfs
 *
 * Во время контрольной работы профессор Флойд заметил, что некоторые студенты обмениваются записками. Сначала он хотел
 * поставить им всем двойки, но в тот день профессор был добрым, а потому решил разделить студентов на две группы:
 * списывающих и дающих списывать, и поставить двойки только первым.
 *
 * У профессора записаны все пары студентов, обменявшихся записками. Требуется определить, сможет ли он разделить
 * студентов на две группы так, чтобы любой обмен записками осуществлялся от студента одной группы студенту другой группы.
 *
 * Формат ввода
 * В первой строке находятся два числа N и M — количество студентов и количество пар студентов, обменивающихся записками
 * (1≤N≤10^2, 0≤M≤N(N−1)/2).
 * Далее в M строках расположены описания пар студентов: два числа, соответствующие номерам студентов, обменивающихся
 * записками (нумерация студентов идёт с 1). Каждая пара студентов перечислена не более одного раза.
 *
 * Формат вывода
 * Необходимо вывести ответ на задачу профессора Флойда. Если возможно разделить студентов на две группы - выведите YES;
 * иначе выведите NO.
 *
 * 6  OK  161 мс / 2 с  11.5 Мб / 256 Мб
 * </pre>
 */
public class S9 {
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

        boolean isItPossible = true;
        Set<Integer> group1 = new HashSet<>();
        Set<Integer> group2 = new HashSet<>();
        for (int i = 1; (i < N + 1) && isItPossible; i++) {
            if (!group1.contains(i) && !group2.contains(i)) {
                isItPossible = dfs(G, group1, group2, i);
            }
        }

        writer.write(isItPossible ? "YES" : "NO");
        writer.flush();
    }

    private static boolean dfs(Map<Integer, Set<Integer>> G, Set<Integer> group1, Set<Integer> group2, int unvisitedOfGroup1) {
        group1.add(unvisitedOfGroup1);
        for (int vOfGroup2 : G.get(unvisitedOfGroup1)) {
            if (group1.contains(vOfGroup2)) {
                return false;
            }
            if (!group2.contains(vOfGroup2)) {
                if (!dfs(G, group2, group1, vOfGroup2)) {
                    return false;
                }
            }
        }
        return true;
    }
}

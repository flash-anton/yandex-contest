package contest53032;

import java.io.*;
import java.util.Arrays;

import static java.lang.Math.min;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/53032">Алг 4.0 ДЗ 4 (53032) Перебор и методы его оптимизации</a>
 * <a href="https://contest.yandex.ru/contest/53032/run-report/99094258">OK  111ms  10.17Mb</a>
 *
 * D. Простая задача коммивояжера
 * Ограничение времени 	2 секунды
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Неориентированный взвешенный граф задан матрицей смежности.
 * Найдите кратчайший цикл, который начинается и заканчивается в вершине номер 1 и проходит через все вершины по одному разу.
 *
 * Формат ввода
 * В первой строке вводится число N (N ≤ 10) — количество вершин графа.
 * Следующие N строк содержат по N целых неотрицательных чисел и задают матрицу смежности.
 * Число 0 означает, что ребро отсутствует.
 * Любое другое число задаёт вес ребра.
 *
 * Формат вывода
 * Выведите минимальную суммарную длину цикла или число -1, если цикл построить невозможно.
 * </pre>
 */
public class D {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        alg(reader, writer);
        reader.close();
        writer.close();
    }

    public static void alg(BufferedReader reader, BufferedWriter writer) throws IOException {
        int N = Integer.parseInt(reader.readLine().trim());
        int[][] G = new int[N][];
        for (int i = 0; i < N; i++) {
            G[i] = Arrays.stream(reader.readLine().split(" ")).filter(s -> !s.isEmpty()).mapToInt(Integer::parseInt).toArray();
        }

        String solution = alg1(N, G);

        writer.write(solution);
        writer.flush();
    }

    public static String alg1(int N, int[][] G) {
        boolean[] visited = new boolean[N];
        visited[0] = true;
        long min = f(N,G, visited, 1, 0, 0);
        min = min == Long.MAX_VALUE ? -1 : min;
        return "" + min;
    }

    static long f(int N, int[][] G, boolean[] visited, int visitedCount, long visitedLength, int lastVisited) {
        if (N == 1) {
            return 0;
        }

        if (visitedCount == N) {
            int edge = G[lastVisited][0];
            return edge == 0 ? Long.MAX_VALUE : (edge + visitedLength);
        }

        long length = Long.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            int edge = G[lastVisited][i];
            if (edge != 0 && !visited[i]) {
                visited[i] = true;
                long l = f(N, G, visited, visitedCount + 1, visitedLength + edge, i);
                length = min(length, l);
                visited[i] = false;
            }
        }
        return length;
    }
}

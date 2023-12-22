package contest53027;

import java.io.*;
import java.util.Arrays;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/53027">Яндекс. Тренировки по алгоритмам 4.0. Разминка</a>
 * <a href="https://contest.yandex.ru/contest/53027/run-report/95487840">OK  148ms  11.70Mb</a>
 *
 * A. Не минимум на отрезке
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Задана последовательность целых чисел a1, a2, …, an.
 * Задаются запросы: сказать любой элемент последовательности на отрезке от L до R включительно, не равный минимуму на этом отрезке.
 *
 * Формат ввода
 * В первой строке содержатся два целых числа N, 1 ≤ N ≤ 100 и M, 1 ≤ M ≤ 1000 — длина последовательности и количество запросов соответственно.
 * Во второй строке — сама последовательность, 0 ≤ ai ≤ 1000.
 * Начиная с третьей строки перечисляются M запросов, состоящих из границ отрезка L и R, где L, R - индексы массива, нумеруются с нуля.
 *
 * Формат вывода
 * На каждый запрос выведите в отдельной строке ответ — любой элемент на [L, R], кроме минимального.
 * В случае, если такого элемента нет, выведите "NOT FOUND".
 * </pre>
 */
public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        alg(reader, writer);
        reader.close();
        writer.close();
    }

    public static void alg(BufferedReader reader, BufferedWriter writer) throws IOException {
        int[] buf = Arrays.stream(reader.readLine().split(" ")).filter(s -> !s.isEmpty()).mapToInt(Integer::parseInt).toArray();
        int M = buf[1];
        int[] a = Arrays.stream(reader.readLine().split(" ")).filter(s -> !s.isEmpty()).mapToInt(Integer::parseInt).toArray();
        int[][] req = new int[M][2];
        for (int i = 0; i < M; i++) {
            req[i] = Arrays.stream(reader.readLine().split(" ")).filter(s -> !s.isEmpty()).mapToInt(Integer::parseInt).toArray();
        }

        String solution = alg3(a, req);

        writer.write(solution);
        writer.flush();
    }

    // O(M + N), выдает max(a[L], a[R]) либо последний max на отрезке
    public static String alg3(int[] a, int[][] req) {
        // O(N)
        int[] lastMaxIndexes = new int[a.length];
        for (int i = 1; i < a.length; i++) {
            lastMaxIndexes[i] = (a[i] > a[i - 1]) ? i : lastMaxIndexes[i - 1];
        }

        // O(M)
        StringBuilder sb = new StringBuilder();
        for (int[] r : req) {
            int L = r[0];
            int R = r[1];

            if (a[L] != a[R]) {
                sb.append(Math.max(a[L], a[R]));
            } else if (lastMaxIndexes[R] > L) {
                sb.append(a[lastMaxIndexes[R]]);
            } else {
                sb.append("NOT FOUND");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    // O(M * N), выдает max(a[L], a[R]) либо последний max на отрезке
    public static String alg2(int[] a, int[][] req) {
        StringBuilder sb = new StringBuilder();
        for (int[] r : req) { // O(M)
            int L = r[0];
            int R = r[1];

            int lastMin = a[L];
            int lastMax = lastMin;
            for (int i = L + 1; i <= R; i++) { // O(N)
                lastMin = (a[i] < a[i - 1]) ? a[i] : lastMin;
                lastMax = (a[i] > a[i - 1]) ? a[i] : lastMax;
            }

            if (a[L] != a[R]) {
                sb.append(Math.max(a[L], a[R]));
            } else if (lastMax != lastMin) {
                sb.append(lastMax);
            } else {
                sb.append("NOT FOUND");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    // O(M * N), выдает max на отрезке
    public static String alg1(int[] a, int[][] req) {
        StringBuilder sb = new StringBuilder();
        for (int[] r : req) { // O(M)
            int min = a[r[0]];
            int max = min;
            for (int i = r[0] + 1; i <= r[1]; i++) { // O(N)
                min = Math.min(min, a[i]);
                max = Math.max(max, a[i]);
            }
            sb.append((max == min) ? "NOT FOUND" : String.valueOf(max)).append("\n");
        }
        return sb.toString();
    }
}

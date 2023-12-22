package contest53032;

import java.io.*;
import java.util.Arrays;

import static java.lang.Math.max;
import static java.lang.Math.pow;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/53032">Алг 4.0 ДЗ 4 (53032) Перебор и методы его оптимизации</a>
 * <a href="https://contest.yandex.ru/contest/53032/run-report/99072102">OK  326ms  30.03Mb</a>
 *
 * C. Максимальный разрез
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Взвешенный неориентированный граф без петель задан матрицей смежности.
 * Распределите вершины по двум долям так, чтобы сумма весов рёбер, соединяющих вершины из разных долей, была максимальна.
 *
 * Формат ввода
 * Вводится число N (2 ≤ N ≤ 20) — количество вершин в графе.
 * В следующих N строках, содержащих по N целых чисел от 0 до 1000, задаётся матрица смежности. 0 означает отсутствие ребра.
 *
 * Формат вывода
 * В первой строке выведите суммарный вес рёбер, соединяющих вершины из разных долей.
 * Во второй строке выведите N чисел 1 или 2 — номера долей для каждой из вершин графа.
 * </pre>
 */
public class C {
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

        String solution = alg2(N, G);

        writer.write(solution);
        writer.flush();
    }

    public static String alg1(int N, int[][] G) {
        long max = 0;
        int maxCombination = 0;
        for (int curCombination = 0; curCombination < pow(2, N); curCombination++) {
            long cur = 0;
            for (int A = 0; A < N - 1; A++) {
                for (int B = A + 1; B < N; B++) {
                    if ((curCombination >> A & 1) != (curCombination >> B & 1)) {
                        cur += G[A][B];
                    }
                }
            }
            if (cur > max) {
                max = cur;
                maxCombination = curCombination;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(max).append('\n');
        for (int i = 0; i < N; i++) {
            sb.append((maxCombination >> i & 1) == 1 ? '2' : '1').append(' ');
        }

        return sb.toString();
    }

    public static String alg2(int N, int[][] G) {
        boolean[] curCombination = new boolean[N];
        boolean[] maxCombination = new boolean[N];
        long max = f2(N, G, curCombination, 0, 0, maxCombination);

        StringBuilder sb = new StringBuilder();
        sb.append(max).append('\n');
        for (int i = 0; i < N; i++) {
            sb.append(maxCombination[i] ? '1' : '2').append(' ');
        }
        return sb.toString();
    }

    static long f2(int N, int[][] G, boolean[] curCombination, int connected, long curSum, boolean[] maxCombination) {
        if (connected == N) {
            return curSum;
        } else {
            int A = connected;

            curCombination[A] = true;
            long sum1 = curSum;
            for (int B = 0; B < A; B++) {
                if (curCombination[A] != curCombination[B]) {
                    sum1 += G[A][B];
                }
            }
            boolean[] maxCombination1 = Arrays.copyOf(curCombination, N);
            sum1 = f2(N, G, curCombination, A + 1, sum1, maxCombination1);

            curCombination[A] = false;
            long sum2 = curSum;
            for (int B = 0; B < A; B++) {
                if (curCombination[A] != curCombination[B]) {
                    sum2 += G[A][B];
                }
            }
            boolean[] maxCombination2 = Arrays.copyOf(curCombination, N);
            sum2 = f2(N, G, curCombination, A + 1, sum2, maxCombination2);

            System.arraycopy(sum1 > sum2 ? maxCombination1 : maxCombination2, 0, maxCombination, 0, N);
            return max(sum1, sum2);
        }
    }
}

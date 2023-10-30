package contest.algorithms.training1.contest27844;

import java.io.*;
import java.util.Arrays;
import java.util.function.Predicate;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/27844">Яндекс. Тренировки по алгоритмам июнь 2021, занятие 6. Бинарный поиск</a>
 * <a href="https://contest.yandex.ru/contest/27844/run-report/95242706">OK  394ms  29.85Mb</a>
 *
 * J. Медиана объединения
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Дано N упорядоченных по неубыванию последовательностей целых чисел (т.е. каждый следующий элемент больше либо равен предыдущему),
 * в каждой из последовательностей ровно L элементов.
 *
 * Для каждых двух последовательностей выполняют следующую операцию:
 * объединяют их элементы (в объединенной последовательности каждое число будет идти столько раз, сколько раз оно встречалось суммарно в объединяемых последовательностях),
 * упорядочивают их по неубыванию и
 * смотрят, какой элемент в этой последовательности из 2L элементов окажется на месте номер L (этот элемент называют левой медианой).
 *
 * Напишите программу, которая для каждой пары последовательностей выведет левую медиану их объединения.
 *
 * Формат ввода
 * Сначала вводятся числа N и L (2 ≤ N ≤ 100, 1 ≤ L ≤ 300).
 * В следующих N строках задаются последовательности.
 * Каждая последовательность состоит из L чисел, по модулю не превышающих 30000.
 *
 * Формат вывода
 * В первой строке выведите медиану объединения 1-й и 2-й последовательностей,
 * во второй строке — объединения 1-й и 3-й, и так далее,
 * в (N‑1)-ой строке — объединения 1-й и N-ой последовательностей,
 * далее медиану объединения 2-й и 3-й, 2-й и 4-й, и т.д. до 2-й и N-ой,
 * затем 3-й и 4-й и так далее.
 * В последней строке должна быть выведена медиана объединения (N–1)-й и N-ой последовательностей.
 * </pre>
 */
public class J {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        alg(reader, writer);
        reader.close();
        writer.close();
    }

    public static void alg(BufferedReader reader, BufferedWriter writer) throws IOException {
        int[] buf = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = buf[0];
        int L = buf[1];
        int[][] V = new int[N][L];
        for (int i = 0; i < N; i++) {
            V[i] = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                int[] a = V[i];
                int[] b = V[j];
                int leftMergeMedian = leftMergeMedian(a, b, L);
                sb.append(leftMergeMedian).append("\n");
            }
        }

        writer.write(sb.toString());
        writer.flush();
    }

    static int leftMergeMedian(int[] a, int[] b, int L) {
        int l = Math.min(a[0], b[0]);
        int r = Math.max(a[L - 1], b[L - 1]) + 1; // +1 т.к. r exclusive
        while (l < r) {
            int sum = l + r;
            int m = sum / 2 - (sum % 2 < 0 ? 1 : 0); // левое в отрицательной области

            int minMedianIndex1 = minOk(0, L, i -> a[i] >= m);
            int maxMedianIndex1 = minOk(minMedianIndex1, L, i -> a[i] > m);

            int minMedianIndex2 = minOk(0, L, i -> b[i] >= m);
            int maxMedianIndex2 = minOk(minMedianIndex2, b.length, i -> b[i] > m);

            int minMedianIndex = minMedianIndex1 + minMedianIndex2;
            int maxMedianIndex = maxMedianIndex1 + maxMedianIndex2;

            if (minMedianIndex >= L) {
                r = m;
            } else if (maxMedianIndex < L) {
                l = m + 1;
            } else {
                return m;
            }
        }
        throw new IllegalArgumentException("Unexpected situation");
    }

    /**
     * Бинарный поиск минимального i, при котором isOk.test(i) == true. O(logN)
     */
    static int minOk(int LInclusive, int RExclusive, Predicate<Integer> isOk) {
        while (LInclusive < RExclusive) {
            int M = (LInclusive + RExclusive) / 2;
            if (isOk.test(M)) {
                RExclusive = M;
            } else {
                LInclusive = M + 1;
            }
        }
        return LInclusive;
    }
}

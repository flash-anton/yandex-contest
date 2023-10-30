package contest.algorithms.training1.contest27844;

import java.io.*;
import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/27844">Яндекс. Тренировки по алгоритмам июнь 2021, занятие 6. Бинарный поиск</a>
 * <a href="https://contest.yandex.ru/contest/27844/run-report/95313136">OK  0.986s  70.65Mb</a>
 *
 * K. Медиана объединения-2
 * Ограничение времени 	5 секунд
 * Ограничение памяти 	256Mb
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
 * Сначала вводятся числа N и L (2 ≤ N ≤ 200, 1 ≤ L ≤ 50000).
 * В следующих N строках задаются параметры, определяющие последовательности.
 *
 * Каждая последовательность определяется пятью целочисленными параметрами: x1, d1, a, c, m.
 * Элементы последовательности вычисляются по следующим формулам:
 * x1 нам задано, а для всех i от 2 до L: x(i) = x(i–1) + d(i–1).
 * Последовательность di определяется следующим образом:
 * d1 нам задано, а для i ≥ 2  di = ((a*d(i–1)+c) mod m), где mod – операция получения остатка от деления (a*d(i–1)+c) на m.
 *
 * Для всех последовательностей выполнены следующие ограничения: 1 ≤ m ≤ 40000, 0 ≤ a < m, 0≤c<m, 0≤d1<m.
 * Гарантируется, что все члены всех последовательностей по модулю не превышают 10^9.
 *
 * Формат вывода
 * В первой строке выведите медиану объединения 1-й и 2-й последовательностей,
 * во второй строке — объединения 1-й и 3-й, и так далее, в (N‑1)-ой строке — объединения 1-й и N-ой последовательностей,
 * далее медиану объединения 2-й и 3-й, 2-й и 4-й, и т.д. до 2-й и N-ой, затем 3-й и 4-й и так далее.
 * В последней строке должна быть выведена медиана объединения (N–1)-й и N-ой последовательностей.
 * </pre>
 */
public class K {
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
        int[][] P = new int[N][5];
        for (int i = 0; i < N; i++) {
            P[i] = Arrays.stream(reader.readLine().split(" "))
                    .filter(s -> !s.isEmpty()) // !!! ради 29 теста с двойными пробелами
                    .mapToInt(Integer::parseInt).toArray();
        }

        String solution = Arrays.stream(alg(N, L, P)).mapToObj(String::valueOf).collect(Collectors.joining("\n"));

        writer.write(solution);
        writer.flush();
    }

    static int[] alg(int N, int L, int[][] P) {
        int[][] V = new int[N][L];
        for (int i = 0; i < N; i++) {
            fillValues(L, P[i], V[i]);
        }

        int[] S = new int[N * (N - 1) / 2];
        int k = 0;
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                S[k++] = leftMergeMedian(V[i], V[j], L);
            }
        }

        return S;
    }

    static void fillValues(int L, int[] p, int[] v) {
        int x1 = p[0];
        int d1 = p[1];
        int a = p[2];
        int c = p[3];
        int m = p[4];

        v[0] = x1;
        if (L > 1) {
            // {x1, d1, ...}
            // {x1, d1, d2, ...}
            // {x1, x2, d2, ...}
            v[1] = d1;
            for (int j = 1; j < L - 1; j++) {
                v[j + 1] = (int) (((long) a * v[j] + c) % m); // (4*10^5 * 4*10^5) = 16*10^10 > 2^(32-1)
                v[j] += v[j - 1];
            }
            v[L - 1] += v[L - 2];
        }
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

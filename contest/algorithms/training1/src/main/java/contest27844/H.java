package contest27844;

import java.io.*;
import java.util.Arrays;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/27844">Яндекс. Тренировки по алгоритмам июнь 2021, занятие 6. Бинарный поиск</a>
 * <a href="https://contest.yandex.ru/contest/27844/run-report/95019824">OK  209ms  12.68Mb</a>
 *
 * H. Провода
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Дано N отрезков провода длиной L1, L2, ..., LN сантиметров.
 * Требуется с помощью разрезания получить из них K равных отрезков как можно большей длины, выражающейся целым числом сантиметров.
 * Если нельзя получить K отрезков длиной даже 1 см, вывести 0.
 *
 * Формат ввода
 * В первой строке находятся числа N и К. В следующих N строках - L1, L2, ..., LN, по одному числу в строке.
 * Ограничения: 1 ≤ N, K ≤ 10 000, 100 ≤ Li ≤ 10 000 000, все числа целые.
 *
 * Формат вывода
 * Вывести одно число - полученную длину отрезков.
 * </pre>
 */
public class H {
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
        int K = buf[1];
        int[] L = new int[N];
        for (int i = 0; i < N; i++) {
            L[i] = Integer.parseInt(reader.readLine());
        }

        int solution = alg(K, L);

        writer.write("" + solution);
        writer.flush();
    }

    static int alg(int K, int[] L) {
        int l = 1; // 1 - минимальный допустимый размер отрезка
        int r = Arrays.stream(L).max().orElseThrow(); // max размер искомого отрезка - размер наибольшего из данных
        while (l <= r) {
            int m = (l + r) / 2;
            if (segmentCount(L, m) >= K) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }

        // l - минимальная длина отрезка, при которой невозможно получить K отрезков, т.е. искомое значение - (l-1)
        return l - 1;
    }

    static int slow(int K, int[] L) {
        int max = Arrays.stream(L).max().orElseThrow();
        for (int size = max; size > 0; size--) {
            if (segmentCount(L, size) >= K) {
                return size;
            }
        }
        return 0;
    }

    static long segmentCount(int[] L, int size) {
        long segmentCount = 0;
        for (int l : L) {
            segmentCount += l / size;
        }
        return segmentCount;
    }
}

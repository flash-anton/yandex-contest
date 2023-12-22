package contest27472;

import java.io.*;
import java.util.Arrays;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/27472">Яндекс. Тренировки по алгоритмам июнь 2021, занятие 2. Линейный поиск</a>
 * <a href="https://contest.yandex.ru/contest/27472/run-report/92992118">OK  113ms  9.42Mb</a>
 *
 * F. Симметричная последовательность
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Последовательность чисел назовем симметричной, если она одинаково читается как слева направо, так и справа налево.
 * Например, следующие последовательности являются симметричными:
 * 1 2 3 4 5 4 3 2 1
 * 1 2 1 2 2 1 2 1
 *
 * Вашей программе будет дана последовательность чисел. Требуется определить, какое минимальное количество и каких чисел
 * надо приписать в конец этой последовательности, чтобы она стала симметричной.
 *
 * Формат ввода
 * Сначала вводится число N — количество элементов исходной последовательности (1 ≤ N ≤ 100).
 * Далее идут N чисел — элементы этой последовательности, натуральные числа от 1 до 9.
 *
 * Формат вывода
 * Выведите сначала число M — минимальное количество элементов, которое надо дописать к последовательности,
 * а потом M чисел (каждое — от 1 до 9) — числа, которые надо дописать к последовательности.
 * </pre>
 */
public class F {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        alg(reader, writer);
        reader.close();
        writer.close();
    }

    public static void alg(BufferedReader reader, BufferedWriter writer) throws IOException {
        int N = Integer.parseInt(reader.readLine());
        int[] n = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int max = alg1(N, n);

        int M = N - max;

        StringBuilder sb = new StringBuilder();
        sb.append(M);
        if (M > 0) {
            sb.append('\n');
            for (int i = M - 1; i >= 0; i--) {
                sb.append(n[i]).append(' ');
            }
        }

        writer.write(sb.toString());
        writer.flush();
    }

    public static int alg1(int N, int[] n) {
        int max = 0;
        int[][] b = new int[2][N + 1];
        for (int i = N - 1; i >= 0; i--) {
            int[] b1 = b[i % 2];
            int[] b2 = b[1 - i % 2];
            for (int j = 0; j < N; j++) {
                if (n[i] == n[j]) {
                    b2[j + 1] = b1[j] + 1;
                } else {
                    b2[j + 1] = 0;
                }
            }
            if (b2[N] == N - i) {
                max = b2[N];
            }
        }
        return max;
    }

    public static int alg2(int N, int[] n) {
        int[] m = new int[N];
        for (int i = 0; i < N; i++) {
            m[N - 1 - i] = n[i];
        }

        int max = 1;
        for (int size = 1; size <= N; size++) {
            int nIndex = N - size;
            if (Arrays.equals(m, 0, size, n, nIndex, N)) {
                max = size;
            }
        }
        return max;
    }
}

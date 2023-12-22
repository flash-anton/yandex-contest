package contest53033;

import java.io.*;

import static java.lang.Math.min;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/53033">Алг 4.0 Финал (53033)</a>
 * <a href="https://contest.yandex.ru/contest/53033/run-report/99421055">OK  85ms  8.84Mb</a>
 *
 * A. Объединение последовательностей
 * Ограничение времени 	3 секунды
 * Ограничение памяти 	512Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Даны две бесконечных возрастающих последовательности чисел A и B.
 * i-ый член последовательности A равен i^2.
 * i-ый член последовательности B равен i^3.
 *
 * Требуется найти Cx, где C – возрастающая последовательность, полученная при объединении последовательностей A и B.
 * Если существует некоторое число, которое встречается и в последовательности A и в последовательности B,
 * то в последовательность C это число попадает в единственном экземпляре.
 *
 * Формат ввода
 * В единственной строке входного файла дано натуральное число x (1 ≤ x ≤ 107).
 *
 * Формат вывода
 * В выходной файл выведите Cx.
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
        int x = Integer.parseInt(reader.readLine().trim());

        String solution = alg1(x);

        writer.write(solution);
        writer.flush();
    }

    public static String alg1(long x) {
        long i = 1;
        long ai = 1;
        long j = 1;
        long bj = 1;
        for (int k = 1; k < x; k++) {
            boolean aSmallerOrEqualB = ai == bj || ai < bj;
            boolean bSmallerOrEqualA = ai == bj || bj < ai;
            if (aSmallerOrEqualB) {
                i++;
                ai = i * i;
            }
            if (bSmallerOrEqualA) {
                j++;
                bj = j * j * j;
            }
        }
        long cx = min(ai, bj);

        return "" + cx;
    }
}

package contest.algorithms.training1.contest27794;

import java.io.*;
import java.util.Arrays;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/27794">Яндекс. Тренировки по алгоритмам июнь 2021, занятие 5. Префиксные суммы и два указателя</a>
 * <a href="https://contest.yandex.ru/contest/27794/run-report/94363875">OK  199ms  12.42Mb</a>
 *
 * H. Подстрока
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * В этой задаче Вам требуется найти максимальную по длине подстроку данной строки,
 * такую что каждый символ встречается в ней не более k раз.
 *
 * Формат ввода
 * В первой строке даны два целых числа n и k (1 ≤ n ≤ 100000, 1 ≤ k ≤ n ), где n – количество символов в строке.
 * Во второй строке n символов – данная строка, состоящая только из строчных латинских букв.
 *
 * Формат вывода
 * В выходной файл выведите два числа – длину искомой подстроки и номер её первого символа.
 * Если решений несколько, выведите любое.
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
        int n = buf[0];
        int k = buf[1];
        byte[] s = reader.readLine().getBytes();

        int[] charCounter = new int[128];
        int outOfBoundCounter = 0;
        int longestSubstringLength = 1;
        int longestSubstringOffset = 1;

        int R = 0;
        for (int L = 0; L < n; L++) {
            while ((R == L) || (R < n) && (outOfBoundCounter == 0)) {
                int substringLength = R - L + 1;
                if (++charCounter[s[R]] > k) {
                    outOfBoundCounter++;
                } else if (substringLength > longestSubstringLength) {
                    longestSubstringLength = substringLength;
                    longestSubstringOffset = L + 1;
                }
                R++;
            }

            if (--charCounter[s[L]] == k) {
                outOfBoundCounter--;
            }
        }

        writer.write(longestSubstringLength + " " + longestSubstringOffset);
        writer.flush();
    }
}

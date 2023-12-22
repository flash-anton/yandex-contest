package contest27844;

import java.io.*;
import java.util.Arrays;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/27844">Яндекс. Тренировки по алгоритмам июнь 2021, занятие 6. Бинарный поиск</a>
 * <a href="https://contest.yandex.ru/contest/27844/run-report/94768062">OK  0.622s  32.01Mb</a>
 *
 * B. Приближенный двоичный поиск
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Для каждого из чисел второй последовательности найдите ближайшее к нему в первой.
 *
 * Формат ввода
 * В первой строке входных данных содержатся числа N и K (0 < N, K <= 100 001).
 * Во второй строке задаются N чисел первого массива, отсортированного по неубыванию,
 * а в третьей строке – K чисел второго массива.
 * Каждое число в обоих массивах по модулю не превосходит 2*10^9.
 *
 * Формат вывода
 * Для каждого из K чисел выведите в отдельную строку число из первого массива, наиболее близкое к данному.
 * Если таких несколько, выведите меньшее из них.
 * </pre>
 */
public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        alg(reader, writer);
        reader.close();
        writer.close();
    }

    public static void alg(BufferedReader reader, BufferedWriter writer) throws IOException {
        reader.readLine();
        int[] n = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] k = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        StringBuilder sb = new StringBuilder();
        for (int e : k) {
            int i = Arrays.binarySearch(n, e);
            if (i >= 0) {
                sb.append(n[i]);
            } else if (i == -1) {
                sb.append(n[-i - 1]);
            } else if (i == -n.length - 1) {
                sb.append(n[-i - 2]);
            } else if (e - n[-i - 2] <= n[-i - 1] - e) {
                sb.append(n[-i - 2]);
            } else {
                sb.append(n[-i - 1]);
            }
            sb.append("\n");
        }

        writer.write(sb.toString());
        writer.flush();
    }
}

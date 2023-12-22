package contest27844;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/27844">Яндекс. Тренировки по алгоритмам июнь 2021, занятие 6. Бинарный поиск</a>
 * <a href="https://contest.yandex.ru/contest/27844/run-report/94766988">OK  0.602s  32.27Mb</a>
 *
 * A. Двоичный поиск
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Реализуйте двоичный поиск в массиве
 *
 * Формат ввода
 * В первой строке входных данных содержатся натуральные числа N и K (0 < N, K <= 100 000).
 * Во второй строке задаются N элементов первого массива,
 * а в третьей строке – K элементов второго массива.
 * Элементы обоих массивов - целые числа, каждое из которых по модулю не превосходит 10^9.
 *
 * Формат вывода
 * Требуется для каждого из K чисел вывести в отдельную строку "YES", если это число встречается в первом массиве, и "NO" в противном случае.
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
        reader.readLine();
        Integer[] n = Arrays.stream(reader.readLine().split(" ")).map(Integer::valueOf).toArray(Integer[]::new);
        int[] k = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Arrays.sort(n);

        StringBuilder sb = new StringBuilder();
        for (int e : k) {
            int i = binSearch(n, 0, n.length, e, Comparator.naturalOrder());
            sb.append(i < 0 ? "NO" : "YES").append("\n");
        }

        writer.write(sb.toString());
        writer.flush();
    }

    /**
     * @param furtherSearchDirection принимает (wanted, candidate),
     *                               возвращает направление дальнейшего поиска: <0 слева, >0 справа, =0 найден.
     */
    static <T> int binSearch(T[] sortedArray, int LIncl, int RExcl, T wanted, Comparator<? super T> furtherSearchDirection) {
        while (LIncl < RExcl) {
            int M = (LIncl + RExcl) / 2;
            int direction = furtherSearchDirection.compare(wanted, sortedArray[M]);
            if (direction < 0) {
                RExcl = M;
            } else if (direction > 0) {
                LIncl = M + 1;
            } else {
                return M;
            }
        }
        return -LIncl - 1;
    }
}

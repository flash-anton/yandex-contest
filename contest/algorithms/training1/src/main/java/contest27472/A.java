package contest27472;

import java.io.*;
import java.util.Arrays;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/27472">Яндекс. Тренировки по алгоритмам июнь 2021, занятие 2. Линейный поиск</a>
 * <a href="https://contest.yandex.ru/contest/27472/run-report/92938300">OK  115ms  9.51Mb</a>
 *
 * A. Возрастает ли список?
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Дан список. Определите, является ли он монотонно возрастающим
 * (то есть верно ли, что каждый элемент этого списка больше предыдущего).
 *
 * Выведите YES, если массив монотонно возрастает и NO в противном случае.
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
        int[] n = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        boolean isAscending = true;
        for (int i = 1; i < n.length; i++) {
            isAscending &= n[i] > n[i - 1];
        }

        writer.write(isAscending ? "YES" : "NO");
        writer.flush();
    }
}

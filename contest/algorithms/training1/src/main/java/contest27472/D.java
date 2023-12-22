package contest27472;

import java.io.*;
import java.util.Arrays;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/27472">Яндекс. Тренировки по алгоритмам июнь 2021, занятие 2. Линейный поиск</a>
 * <a href="https://contest.yandex.ru/contest/27472/run-report/92975585">OK  125ms  9.52Mb</a>
 *
 * D. Больше своих соседей
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Дан список чисел.
 * Определите, сколько в этом списке элементов, которые больше двух своих соседей и выведите количество таких элементов.
 *
 * Формат ввода
 * Вводится список чисел. Все числа списка находятся на одной строке.
 *
 * Формат вывода
 * Выведите ответ на задачу.
 * </pre>
 */
public class D {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        alg(reader, writer);
        reader.close();
        writer.close();
    }

    public static void alg(BufferedReader reader, BufferedWriter writer) throws IOException {
        int[] n = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int count = 0;
        for (int i = 1; i < n.length - 1; i++) {
            if (n[i] > n[i - 1] && n[i] > n[i + 1]) {
                count++;
            }
        }

        writer.write(Integer.toString(count));
        writer.flush();
    }
}

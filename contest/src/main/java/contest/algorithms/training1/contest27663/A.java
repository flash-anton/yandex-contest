package contest.algorithms.training1.contest27663;

import java.io.*;
import java.util.Arrays;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/27663">Яндекс. Тренировки по алгоритмам июнь 2021, занятие 3. Множества</a>
 * <a href="https://contest.yandex.ru/contest/27663/run-report/93244948">OK  384ms  27.41Mb</a>
 *
 * A. Количество различных чисел
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Дан список чисел, который может содержать до 100000 чисел. Определите, сколько в нем встречается различных чисел.
 *
 * Формат ввода
 * Вводится список целых чисел. Все числа списка находятся на одной строке.
 *
 * Формат вывода
 * Выведите ответ на задачу.
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
        long count = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).distinct().count();

        writer.write(Long.toString(count));
        writer.flush();
    }
}

package contest27663;

import java.io.*;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/27663">Яндекс. Тренировки по алгоритмам июнь 2021, занятие 3. Множества</a>
 * <a href="https://contest.yandex.ru/contest/27663/run-report/93246355">OK  0.602s  36.97Mb</a>
 *
 * B. Пересечение множеств
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Даны два списка чисел, которые могут содержать до 10000 чисел каждый. Выведите все числа, которые входят как в первый,
 * так и во второй список в порядке возрастания. Примечание. И даже эту задачу на Питоне можно решить в одну строчку.
 *
 * Формат ввода
 * Вводятся два списка целых чисел. Все числа каждого списка находятся на отдельной строке.
 *
 * Формат вывода
 * Выведите ответ на задачу.
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
        Set<Integer> a = Arrays.stream(reader.readLine().split(" ")).map(Integer::valueOf).collect(Collectors.toSet());
        Set<Integer> b = Arrays.stream(reader.readLine().split(" ")).map(Integer::valueOf).collect(Collectors.toSet());
        a.retainAll(b);

        writer.write(a.stream().sorted().map(String::valueOf).collect(Collectors.joining(" ")));
        writer.flush();
    }
}

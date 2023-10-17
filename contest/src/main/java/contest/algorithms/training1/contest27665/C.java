package contest.algorithms.training1.contest27665;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/27665">Яндекс. Тренировки по алгоритмам июнь 2021, занятие 3. Множества</a>
 * <a href="https://contest.yandex.ru/contest/27665/run-report/93425663">OK  0.794s  40.99Mb</a>
 *
 * C. Самое частое слово
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Дан текст. Выведите слово, которое в этом тексте встречается чаще всего.
 * Если таких слов несколько, выведите то, которое меньше в лексикографическом порядке.
 *
 * Формат ввода
 * Вводится текст.
 *
 * Формат вывода
 * Выведите ответ на задачу.
 * </pre>
 */
public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        alg(reader, writer);
        reader.close();
        writer.close();
    }

    public static void alg(BufferedReader reader, BufferedWriter writer) throws IOException {
        Map<String, Integer> m = new HashMap<>();
        int max = reader.lines()
                .flatMap(line -> Arrays.stream(line.split(" ")))
                .filter(word -> !word.isEmpty())
                .mapToInt(word -> m.compute(word, (k, v) -> (v == null) ? 1 : ++v))
                .max().orElseThrow();

        String s = m.keySet().stream()
                .filter(word -> m.get(word) == max)
                .sorted()
                .findFirst().orElseThrow();

        writer.write(s);
        writer.flush();
    }
}

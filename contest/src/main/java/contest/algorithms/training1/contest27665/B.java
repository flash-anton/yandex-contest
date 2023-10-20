package contest.algorithms.training1.contest27665;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/27665">Яндекс. Тренировки по алгоритмам июнь 2021, занятие 4. Словари и сортировка подсчетом</a>
 * <a href="https://contest.yandex.ru/contest/27665/run-report/93424874">OK  0.538s  41.55Mb</a>
 *
 * B. Номер появления слова
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Во входном файле (вы можете читать данные из файла input.txt) записан текст. Словом считается последовательность
 * непробельных символов идущих подряд, слова разделены одним или большим числом пробелов или символами конца строки.
 * Для каждого слова из этого текста подсчитайте, сколько раз оно встречалось в этом тексте ранее.
 *
 * Формат ввода
 * Вводится текст.
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
        Map<String, Integer> m = new HashMap<>();
        String s = reader.lines()
                .flatMap(line -> Arrays.stream(line.split(" ")))
                .filter(word -> !word.isEmpty())
                .map(word -> m.compute(word, (k,v) -> (v == null) ? 0 : ++v))
                .map(String::valueOf)
                .collect(Collectors.joining(" "));

        writer.write(s);
        writer.flush();
    }
}

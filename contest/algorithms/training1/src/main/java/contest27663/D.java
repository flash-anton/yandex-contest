package contest27663;

import java.io.*;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/27663">Яндекс. Тренировки по алгоритмам июнь 2021, занятие 3. Множества</a>
 * <a href="https://contest.yandex.ru/contest/27663/run-report/93253381">OK  343ms  24.58Mb</a>
 *
 * D. Количество слов в тексте
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Во входном файле (вы можете читать данные из sys.stdin, подключив библиотеку sys) записан текст. Словом считается
 * последовательность непробельных символов идущих подряд, слова разделены одним или большим числом пробелов или
 * символами конца строки. Определите, сколько различных слов содержится в этом тексте.
 *
 * Формат ввода
 * Вводится текст.
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
        Set<String> words = reader.lines()
                .flatMap(line -> Arrays.stream(line.split(" ")))
                .filter(word -> !word.isEmpty())
                .collect(Collectors.toSet());

        writer.write(Integer.toString(words.size()));
        writer.flush();
    }
}

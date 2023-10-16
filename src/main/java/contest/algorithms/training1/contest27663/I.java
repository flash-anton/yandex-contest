package contest.algorithms.training1.contest27663;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/27663">Яндекс. Тренировки по алгоритмам июнь 2021, занятие 3. Множества</a>
 * <a href="https://contest.yandex.ru/contest/27663/run-report/93277426">OK  401ms  19.46Mb</a>
 *
 * I. Полиглоты
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Каждый из N школьников некоторой школы знает Mi языков.
 * Определите, какие языки знают все школьники и языки, которые знает хотя бы один из школьников.
 *
 * Формат ввода
 * Первая строка входных данных содержит количество школьников N.
 * Далее идет N чисел Mi, после каждого из чисел идет Mi строк, содержащих названия языков, которые знает i-й школьник.
 * Длина названий языков не превышает 1000 символов, количество различных языков не более 1000.
 * 1 ≤ N ≤ 1000, 1 ≤ Mi ≤ 500.
 *
 * Формат вывода
 * В первой строке выведите количество языков, которые знают все школьники.
 * Начиная со второй строки - список таких языков.
 * Затем - количество языков, которые знает хотя бы один школьник, на следующих строках - список таких языков.
 * </pre>
 */
public class I {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        alg(reader, writer);
        reader.close();
        writer.close();
    }

    private static void append(StringBuilder sb, Collection<String> c) {
        sb.append(c.size()).append('\n');
        for (String s : c) {
            sb.append(s).append('\n');
        }
    }

    public static void alg(BufferedReader reader, BufferedWriter writer) throws IOException {
        int N = Integer.parseInt(reader.readLine());

        Map<String, Integer> total = new HashMap<>();
        for (int i = 0; i < N; i++) {
            int M = Integer.parseInt(reader.readLine());
            for (int j = 0; j < M; j++) {
                String lang = reader.readLine();
                total.compute(lang, (k, v) -> (v == null) ? 1 : ++v);
            }
        }

        StringBuilder sb = new StringBuilder();
        append(sb, total.entrySet().stream().filter(e -> e.getValue() == N).map(Map.Entry::getKey).collect(Collectors.toList()));
        append(sb, total.keySet().stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList()));

        writer.write(sb.toString());
        writer.flush();
    }
}

package contest27663;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/27663">Яндекс. Тренировки по алгоритмам июнь 2021, занятие 3. Множества</a>
 * <a href="https://contest.yandex.ru/contest/27663/run-report/93251051">OK  0.969s  46.43Mb</a>
 *
 * C. Кубики
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	256Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Аня и Боря любят играть в разноцветные кубики, причем у каждого из них свой набор и в каждом наборе все кубики
 * различны по цвету. Однажды дети заинтересовались, сколько существуют цветов таких, что кубики каждого цвета
 * присутствуют в обоих наборах. Для этого они занумеровали все цвета случайными числами. На этом их энтузиазм иссяк,
 * поэтому вам предлагается помочь им в оставшейся части. Номер любого цвета — это целое число в пределах от 0 до 10^9.
 *
 * Формат ввода
 * В первой строке входного файла записаны числа N и M — количество кубиков у Ани и Бори соответственно.
 * В следующих N строках заданы номера цветов кубиков Ани. В последних M строках номера цветов кубиков Бори.
 *
 * Формат вывода
 * Выведите сначала количество, а затем отсортированные по возрастанию номера цветов таких, что кубики каждого цвета есть в обоих наборах,
 * затем количество и отсортированные по возрастанию номера остальных цветов у Ани,
 * потом количество и отсортированные по возрастанию номера остальных цветов у Бори.
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

    private static Set<Integer> readNumberFromEachLine(BufferedReader reader, int lines, Set<Integer> s) throws IOException {
        for (int i = 0; i < lines; i++) {
            s.add(Integer.valueOf(reader.readLine()));
        }
        return s;
    }

    private static void appendSet(StringBuilder sb, Set<Integer> s) {
        sb.append(s.size()).append('\n');
        sb.append(s.stream().sorted().map(String::valueOf).collect(Collectors.joining(" "))).append('\n');
    }

    public static void alg(BufferedReader reader, BufferedWriter writer) throws IOException {
        int[] buf = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Set<Integer> a = readNumberFromEachLine(reader, buf[0], new HashSet<>());
        Set<Integer> b = readNumberFromEachLine(reader, buf[1], new HashSet<>());

        Set<Integer> intersection = a.stream().filter(b::contains).collect(Collectors.toSet());
        a.removeAll(intersection);
        b.removeAll(intersection);

        StringBuilder sb = new StringBuilder();
        appendSet(sb, intersection);
        appendSet(sb, a);
        appendSet(sb, b);

        writer.write(sb.toString());
        writer.flush();
    }
}

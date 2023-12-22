package contest53027;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Map;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/53027">Яндекс. Тренировки по алгоритмам 4.0. Разминка</a>
 * <a href="https://contest.yandex.ru/contest/53027/run-report/95592848">OK  151ms  11.43Mb</a>
 *
 * I. Правильная скобочная последовательность
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Рассмотрим последовательность, состоящую из круглых, квадратных и фигурных скобок.
 *
 * Программа должна определить, является ли данная скобочная последовательность правильной.
 *
 * Пустая последовательность является правильной.
 * Если A — правильная, то последовательности (A), [A], {A} — правильные.
 * Если A и B — правильные последовательности, то последовательность AB — правильная.
 *
 * Формат ввода
 * В единственной строке записана скобочная последовательность, содержащая не более 100000 скобок.
 *
 * Формат вывода
 * Если данная последовательность правильная, то программа должна вывести строку "yes", иначе строку "no".
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

    public static void alg(BufferedReader reader, BufferedWriter writer) throws IOException {
        char[] chars = {};
        if (reader.ready()) {
            chars = reader.readLine().replaceAll("\\s", "").toCharArray();
        }

        String solution = alg(chars);

        writer.write(solution);
        writer.flush();
    }

    public static String alg(char[] chars) {
        Map<Character, Character> bracket = Map.of('(', ')', '[', ']', '{', '}');
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (Character bkt : chars) {
            if (bracket.containsKey(bkt)) {
                stack.push(bracket.get(bkt));
            } else if (!bkt.equals(stack.pollFirst())) {
                return "no";
            }
        }
        return stack.isEmpty() ? "yes" : "no";
    }
}

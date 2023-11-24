package contest.algorithms.training4.contest53032;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/53032">Алг 4.0 ДЗ 4 (53032) Перебор и методы его оптимизации</a>
 * <a href="https://contest.yandex.ru/contest/53032/run-report/99097104">OK  0.549s  53.20Mb</a>
 *
 * E. Генерация правильных скобочных последовательностей - 2
 * Ограничение времени 	2 секунды
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод или brackets2.in
 * Вывод 	стандартный вывод или brackets2.out
 *
 * По данному числу n выведите все правильные скобочные последовательности из круглых и квадратных скобок длины n в лексикографическом порядке.
 *
 * Формат ввода
 * Одно целое число n (0 ≤ n ≤ 16).
 *
 * Формат вывода
 * Выведите все правильные скобочные последовательности из круглых и квадратных скобок длины n в лексикографическом порядке.
 * Каждая последовательность должна выводиться в новой строке.
 * </pre>
 */
public class E {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        alg(reader, writer);
        reader.close();
        writer.close();
    }

    public static void alg(BufferedReader reader, BufferedWriter writer) throws IOException {
        int n = Integer.parseInt(reader.readLine().trim());

        String solution = alg1(n);

        writer.write(solution);
        writer.flush();
    }

    public static String alg1(int n) {
        StringBuilder sb = new StringBuilder();
        f(n, new byte[n], 0, new ArrayDeque<>(), sb);
        return sb.toString();
    }

    static void f(int n, byte[] brackets, int bracketsCount, Deque<Byte> expectedBrackets, StringBuilder sb) {
        if (bracketsCount + expectedBrackets.size() > n) {
            return;
        }

        if (bracketsCount + expectedBrackets.size() == n) {
            for (byte expectedBracket : expectedBrackets) {
                brackets[bracketsCount++] = expectedBracket;
            }
            sb.append(new String(brackets, 0, bracketsCount)).append('\n');
            return;
        }

        brackets[bracketsCount] = '(';
        expectedBrackets.push((byte) ')');
        f(n, brackets, bracketsCount + 1, expectedBrackets, sb);
        expectedBrackets.pop();

        brackets[bracketsCount] = '[';
        expectedBrackets.push((byte) ']');
        f(n, brackets, bracketsCount + 1, expectedBrackets, sb);
        expectedBrackets.pop();

        if (!expectedBrackets.isEmpty()) {
            brackets[bracketsCount] = expectedBrackets.pop();
            f(n, brackets, bracketsCount + 1, expectedBrackets, sb);
            expectedBrackets.push(brackets[bracketsCount]);
        }
    }
}

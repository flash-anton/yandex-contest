package contest27393;

import java.io.*;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/27393">Яндекс. Тренировки по алгоритмам июнь 2021, занятие 1. Сложность, тестирование, особые случаи</a>
 * <a href="https://contest.yandex.ru/contest/27393/run-report/92203789">OK  97ms  8.64Mb</a>
 *
 * D. Уравнение с корнем
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Решите в целых числах уравнение: sqrt(ax+b) == c
 * a, b, c – данные целые числа: найдите все решения или сообщите, что решений в целых числах нет.
 *
 * Формат ввода
 * Вводятся три числа a, b и c по одному в строке.
 *
 * Формат вывода
 * Программа должна вывести все решения уравнения в порядке возрастания, либо NO SOLUTION (заглавными буквами),
 * если решений нет. Если решений бесконечно много, вывести MANY SOLUTIONS.
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
        int a = Integer.parseInt(reader.readLine());
        int b = Integer.parseInt(reader.readLine());
        int c = Integer.parseInt(reader.readLine());

        // sqrt(ax+b) == c  ||  x = (c*c-b)/a
        int ccb = c * c - b;

        String solution;
        if (c < 0 || (ccb != 0 && (a == 0 || (ccb % a != 0)))) {
            // sqrt(ax+b) < 0  ||  x = (c*c-b)/0  ||  дробное x = (c*c-b)/a
            solution = "NO SOLUTION";
        } else if (a == 0) {
            // x = 0/0
            solution = "MANY SOLUTIONS";
        } else {
            solution = Integer.toString(ccb / a);
        }

        writer.write(solution);
        writer.flush();
    }
}

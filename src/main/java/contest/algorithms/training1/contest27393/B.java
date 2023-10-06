package contest.algorithms.training1.contest27393;

import java.io.*;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/27393">Яндекс. Тренировки по алгоритмам июнь 2021, занятие 1. Сложность, тестирование, особые случаи</a>
 *
 * B. Треугольник
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Даны три натуральных числа. Возможно ли построить треугольник с такими сторонами.
 * Если это возможно, выведите строку YES, иначе выведите строку NO.
 * Треугольник — это три точки, не лежащие на одной прямой.
 *
 * Формат ввода
 * Вводятся три натуральных числа.
 *
 * Формат вывода
 * Выведите ответ на задачу.
 *
 * OK  92ms  8.59Mb
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
        int a = Integer.parseInt(reader.readLine());
        int b = Integer.parseInt(reader.readLine());
        int c = Integer.parseInt(reader.readLine());

        boolean isTriangle = (a < (b + c)) && (b < (a + c)) && (c < (a + b));

        writer.write(isTriangle ? "YES" : "NO");
        writer.flush();
    }
}

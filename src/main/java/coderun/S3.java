package coderun;

import java.io.*;

/**
 * <pre>
 * https://coderun.yandex.ru/problem/print-the-route-of-the-maximum-cost
 *
 * 3. Вывести маршрут максимальной стоимости
 * легкая dynamic programming 2D
 *
 * В левом верхнем углу прямоугольной таблицы размером N×M находится черепашка. В каждой клетке таблицы записано
 * некоторое число. Черепашка может перемещаться вправо или вниз, при этом маршрут черепашки заканчивается в правом
 * нижнем углу таблицы.
 *
 * Подсчитаем сумму чисел, записанных в клетках, через которую проползла черепашка (включая начальную и конечную клетку).
 * Найдите наибольшее возможное значение этой суммы и маршрут, на котором достигается эта сумма.
 *
 * Формат ввода
 * В первой строке входных данных записаны два натуральных числа N и M, не превосходящих 100 — размеры таблицы.
 * Далее идет N строк, каждая из которых содержит M чисел, разделенных пробелами — описание таблицы.
 * Все числа в клетках таблицы целые и могут принимать значения от 0 до 100.
 *
 * Формат вывода
 * Первая строка выходных данных содержит максимальную возможную сумму, вторая — маршрут, на котором достигается эта сумма.
 * Маршрут выводится в виде последовательности, которая должна содержать N-1 букву D, означающую передвижение вниз и
 * M-1 букву R, означающую передвижение направо. Если таких последовательностей несколько,
 * необходимо вывести ровно одну (любую) из них.
 * </pre>
 */
public class S3 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        alg(reader, writer);
        reader.close();
        writer.close();
    }

    public static void alg(BufferedReader reader, BufferedWriter writer) throws IOException {
        String[] lineNumbers = reader.readLine().split(" ");
        byte N = Byte.parseByte(lineNumbers[0]); // rows
        byte M = Byte.parseByte(lineNumbers[1]); // columns
        byte[][] nums = new byte[N][M];
        for (int i = 0; i < N; i++) {
            lineNumbers = reader.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                nums[i][j] = Byte.parseByte(lineNumbers[j]);
            }
        }

        int minSum = 0;

        writer.write(Integer.toString(minSum));
        writer.flush();
    }
}

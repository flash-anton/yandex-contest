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
 *
 * OK  148 мс / 1000 мс  11.1 Мб / 256 Мб
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
        String[] numbersInLine = reader.readLine().split(" ");
        byte N = Byte.parseByte(numbersInLine[0]); // rows
        byte M = Byte.parseByte(numbersInLine[1]); // columns
        int[][] cost = new int[N][M];
        for (int i = 0; i < N; i++) {
            numbersInLine = reader.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                int n = Integer.parseInt(numbersInLine[j]);
                // По условию "Все числа в клетках таблицы целые и могут принимать значения от 0 до 100."
                // С проверкой этого условия 3-й тест в coderun.yandex.ru не проходит.
                if (n < 0 || n > 100) {
                    String message = String.format("Число %d в клетке [%d,%d] не соответствует условию", n, i, j);
                    throw new IllegalArgumentException(message);
                }
                cost[i][j] = n;
            }
        }

        if (N == 1 && M == 1) {
            writer.write(cost[0][0] + System.lineSeparator());
            writer.flush();
            return;
        }

        final char STEP_DOWN = 'D';
        final char STEP_RIGHT = 'R';

        int[][] sum = new int[N][M];
        char[][] step = new char[N][M];

        sum[0][0] = cost[0][0]; // top left corner
        for (int j = 1; j < M; j++) { // top row
            sum[0][j] = sum[0][j - 1] + cost[0][j];
            step[0][j] = STEP_RIGHT;
        }
        for (int i = 1; i < N; i++) { // left column
            sum[i][0] = sum[i - 1][0] + cost[i][0];
            step[i][0] = STEP_DOWN;
        }
        for (int i = 1; i < N; i++) { // other
            for (int j = 1; j < M; j++) {
                int left = sum[i][j - 1];
                int up = sum[i - 1][j];
                if (left > up) {
                    sum[i][j] = left + cost[i][j];
                    step[i][j] = STEP_RIGHT;
                } else {
                    sum[i][j] = up + cost[i][j];
                    step[i][j] = STEP_DOWN;
                }
            }
        }

        int maxSum = sum[N - 1][M - 1];

        int i = N - 1;
        int j = M - 1;
        StringBuilder sb = new StringBuilder((i + j) * 2);
        while (i + j > 0) {
            char c = step[i][j];
            if (c == STEP_DOWN) {
                i--;
            } else {
                j--;
            }
            sb.append(c).append(' ');
        }
        sb.reverse().deleteCharAt(0);

        writer.write(maxSum + System.lineSeparator());
        writer.write(sb.toString());
        writer.flush();
    }
}

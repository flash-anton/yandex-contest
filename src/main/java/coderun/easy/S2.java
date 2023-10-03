package coderun.easy;

import java.io.*;

/**
 * <pre>
 * <a href="https://coderun.yandex.ru/problem/cheapest-way">2. Самый дешевый путь</a>
 * легкая dynamic programming 2D
 *
 * В каждой клетке прямоугольной таблицы N×M записано некоторое число. Изначально игрок находится в левой верхней клетке.
 * За один ход ему разрешается перемещаться в соседнюю клетку либо вправо, либо вниз (влево и вверх перемещаться запрещено).
 * При проходе через клетку с игрока берут столько килограммов еды, какое число записано в этой клетке (еду берут также
 * за первую и последнюю клетки его пути).
 *
 * Требуется найти минимальный вес еды в килограммах, отдав которую игрок может попасть в правый нижний угол.
 *
 * Формат ввода
 * Вводятся два числа N и M — размеры таблицы (1≤N≤20, 1≤M≤20). Затем идет N строк по M чисел в каждой — размеры штрафов
 * в килограммах за прохождение через соответствующие клетки (числа от 0 до 100).
 *
 * Формат вывода
 * Выведите минимальный вес еды в килограммах, отдав которую можно попасть в правый нижний угол.
 *
 * OK  95 мс / 1000 мс  8.4 Мб / 256 Мб
 * </pre>
 */
public class S2 {
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
        byte[][] cost = new byte[N][M];
        for (int i = 0; i < N; i++) {
            lineNumbers = reader.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                cost[i][j] = Byte.parseByte(lineNumbers[j]);
            }
        }

        short[][] sum = new short[N][M];
        sum[0][0] = cost[0][0]; // top left corner
        for (int j = 1; j < M; j++) { // top row
            sum[0][j] = (short) (sum[0][j-1] + cost[0][j]);
        }
        for (int i = 1; i < N; i++) { // left column
            sum[i][0] = (short) (sum[i-1][0] + cost[i][0]);
        }

        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                sum[i][j] = (short) (Integer.min(sum[i-1][j], sum[i][j-1]) + cost[i][j]);
            }
        }

        int minSum = sum[N-1][M-1];

        writer.write(Integer.toString(minSum));
        writer.flush();
    }
}

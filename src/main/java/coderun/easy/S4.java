package coderun.easy;

import java.io.*;
import java.util.*;

/**
 * <pre>
 * https://coderun.yandex.ru/problem/knight-move
 *
 * 4. Ход конём
 * легкая dynamic programming 2D
 *
 * Дана прямоугольная доска N×M (N строк и M столбцов). В левом верхнем углу находится шахматный конь, которого
 * необходимо переместить в правый нижний угол доски. В данной задаче конь может перемещаться на две клетки вниз и одну
 * клетку вправо или на одну клетку вниз и две клетки вправо.
 *
 * Необходимо определить, сколько существует различных маршрутов, ведущих из левого верхнего в правый нижний угол.
 *
 * Формат ввода
 * Входной файл содержит два натуральных числа N и M (1⩽N,M⩽50).
 *
 * Формат вывода
 * В выходной файл выведите единственное число — количество способов добраться конём до правого нижнего угла доски.
 *
 * 19  OK   94 мс / 1000 мс   8.4 Мб / 64 Мб - pascalTriangle
 * 19  OK  240 мс / 1000 мс  12.8 Мб / 64 Мб - dynamicProgramming
 * </pre>
 */
public class S4 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        alg(reader, writer);
        reader.close();
        writer.close();
    }

    public static void alg(BufferedReader reader, BufferedWriter writer) throws IOException {
        String[] numbersInLine = reader.readLine().split(" ");
        int N = Byte.parseByte(numbersInLine[0]); // rows
        int M = Byte.parseByte(numbersInLine[1]); // columns

        pascalTriangle(N, M, writer);
//        dynamicProgramming(N, M, writer);
    }

    /**
     * Исходя из условия, решением является элемент Треугольника Паскаля.
     * Причем максимальное значение решения 601.080.390 для правого нижнего угла с координатами (49, 49)
     * соответствует центральному элементу 32-го ряда, которое можно вычислить с помощью 2 int[32/2] буферов.
     */
    private static void pascalTriangle(int N, int M, BufferedWriter writer) throws IOException {
        byte x = (byte) (M - 1);
        byte y = (byte) (N - 1);

        if ((x + y) % 3 > 0) {
            writer.write("0");
            writer.flush();
            return;
        }

        byte pascalTriangleRow = (byte) ((x + y) / 3);

        byte leftIndex = (byte) ((2 * x - y) / 3);
        byte rightIndex = (byte) (pascalTriangleRow - leftIndex);
        if (leftIndex > rightIndex) {
            byte t = leftIndex;
            leftIndex = rightIndex;
            rightIndex = t;
        }

        byte bufSize = (byte) (leftIndex + 1);
        int[] buf1 = new int[bufSize];
        int[] buf2 = new int[bufSize];

        buf1[0] = 1;
        buf2[0] = 1;
        for (int row = 1; row <= pascalTriangleRow; row++) {
            for (int i = Math.max(1, row - rightIndex); i <= Math.min(row, leftIndex); i++) {
                buf2[i] = buf1[i - 1] + buf1[i];
            }
            int[] t = buf1;
            buf1 = buf2;
            buf2 = t;
        }

        long ways = buf1[leftIndex];

        writer.write(Long.toString(ways));
        writer.flush();
    }


    /**
     * Решение через динамическое программирование требует больше памяти и времени.
     * Но оно проще и универсальнее.
     */
    private static void dynamicProgramming(int N, int M, BufferedWriter writer) throws IOException {
        Point start = new Point(1, 1);
        Point end = new Point(N, M);

        Map<Point, Integer> cash = new LinkedHashMap<>();
        Point current = start;
        int value = 1;
        while (current != null && !current.equals(end)) {
            calc(cash, end, current.add(1, 2), value);
            calc(cash, end, current.add(2, 1), value);

            Iterator<Point> it = cash.keySet().iterator();
            current = it.hasNext() ? it.next() : null;
            value = current == null ? 0 : cash.remove(current);
        }

        writer.write(Integer.toString(value));
        writer.flush();
    }

    private static void calc(Map<Point, Integer> cash, Point end, Point current, int addition) {
        if (current.equals(end) || current.inside(end))
            cash.compute(current, (k, v) -> addition + ((v == null) ? 0 : v));
    }

    private record Point(int n, int m) {
        Point add(int n, int m) {
            return new Point(this.n + n, this.m + m);
        }

        boolean inside(Point other) {
            return n < other.n && m < other.m;
        }
    }
}

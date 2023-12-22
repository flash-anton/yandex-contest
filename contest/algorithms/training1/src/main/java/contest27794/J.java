package contest27794;

import java.io.*;
import java.util.*;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/27794">Яндекс. Тренировки по алгоритмам июнь 2021, занятие 5. Префиксные суммы и два указателя</a>
 * <a href="https://contest.yandex.ru/contest/27794/run-report/94676549">OK  1.38s  38.07Mb</a>
 *
 * J. Треугольники
 * 	Все языки 	Python 3.9.1
 * Ограничение времени 	2 секунды 	4 секунды
 * Ограничение памяти 	256Mb 	256Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Петя достаточно давно занимается в математическом кружке, поэтому он уже успел не только правила выполнения простейших операций,
 * но и такое достаточно сложное понятие как симметрия. Для того, чтобы получше изучить симметрию Петя решил начать с
 * наиболее простых геометрических фигур – треугольников. Он скоро понял, что осевой симметрией обладают так называемые
 * равнобедренные треугольники. Поэтому теперь Петя ищет везде такие треугольники.
 *
 * Напомним, что треугольник называется равнобедренным, если его площадь положительна, и у него есть хотя бы две равные стороны.
 *
 * Недавно Петя, зайдя в класс, увидел, что на доске нарисовано n точек.
 * Разумеется, он сразу задумался, сколько существует троек из этих точек, которые являются вершинами равнобедренных треугольников.
 *
 * Требуется написать программу, решающую указанную задачу.
 *
 * Формат ввода
 * Входной файл содержит целое число n (3 ≤ n ≤ 1500).
 * Каждая из последующих строк содержит по два целых числа – xi и yi – координаты i-ой точки.
 * Координаты точек не превосходят 10^9 по абсолютной величине.
 * Среди заданных точек нет совпадающих.
 *
 * Формат вывода
 * В выходной файл выведите ответ на задачу.
 * </pre>
 */
public class J {
    private static class Point {
        final String str;
        final int x;
        final int y;

        Point(String str) {
            this.str = str;
            int[] buf = Arrays.stream(str.split(" ")).mapToInt(Integer::parseInt).toArray();
            x = buf[0];
            y = buf[1];
        }

        long distanceTo(Point p) {
            long dx = p.x - x;
            long dy = p.y - y;
            return dx * dx + dy * dy;
        }

        String oppositeTo(Point mid) {
            return (2 * mid.x - x) + " " + (2 * mid.y - y);
        }
    }

    private static class Group {
        final Set<String> points = new HashSet<>();
        int oppositePointCount = 0; // счетчик для подсчета треугольников нулевой площади
        // Равносторонние треугольники с целочисленными координатами вершин не подсчитываются, т.к. не существуют

        void update(Point vertex, Point point) {
            points.add(point.str);
            oppositePointCount += points.contains(point.oppositeTo(vertex)) ? 1 : 0;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        alg(reader, writer);
        reader.close();
        writer.close();
    }

    public static void alg(BufferedReader reader, BufferedWriter writer) throws IOException {
        int K = Integer.parseInt(reader.readLine());
        Point[] p = reader.lines().map(Point::new).toArray(Point[]::new);

        long triangleCount = 0;

        for (int i = 0; i < K; i++) {
            Map<Long, Group> groups = new HashMap<>();
            for (int j = 0; j < K; j++) {
                if (j != i) {
                    groups.computeIfAbsent(p[i].distanceTo(p[j]), k -> new Group()).update(p[i], p[j]);
                }
            }
            for (Group group : groups.values()) {
                triangleCount += (long) group.points.size() * (group.points.size() - 1) / 2;
                triangleCount -= group.oppositePointCount; // треугольники нулевой площади
            }
        }

        writer.write("" + triangleCount);
        writer.flush();
    }
}

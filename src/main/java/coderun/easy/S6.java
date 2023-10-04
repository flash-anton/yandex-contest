package coderun.easy;

import java.io.*;
import java.util.*;
import java.util.stream.*;

/**
 * <pre>
 * <a href="https://coderun.yandex.ru/problem/nop-with-response-recovery">6. НОП с восстановлением ответа</a>
 * легкая dynamic programming 2D
 *
 * Даны две последовательности, требуется найти и вывести их наибольшую общую подпоследовательность.
 *
 * Формат ввода
 * В первой строке входных данных содержится число N – длина первой последовательности (1 ≤ N ≤ 1000). Во второй строке
 * заданы члены первой последовательности (через пробел) – целые числа, не превосходящие 10000 по модулю.
 * В третьей строке записано число M – длина второй последовательности (1 ≤ M ≤ 1000). В четвертой строке задаются члены
 * второй последовательности (через пробел) – целые числа, не превосходящие 10000 по модулю.
 *
 * Формат вывода
 * Требуется вывести наибольшую общую подпоследовательность данных последовательностей, через пробел.
 *
 * 11  OK  280 мс / 1000 мс  30.6 Мб / 64 Мб
 * </pre>
 */
public class S6 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        alg(reader, writer);
        reader.close();
        writer.close();
    }

    public static void alg(BufferedReader reader, BufferedWriter writer) throws IOException {
        reader.readLine();
        int[] nArr = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::valueOf).toArray();
        reader.readLine();
        int[] mArr = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::valueOf).toArray();

        int[][] buf = new int[nArr.length + 1][];
        Arrays.fill(buf, new int[0]);

        for (int m : mArr) {
            int i = 0;
            int[] top = buf[i];

            for (int n : nArr) {
                i++;
                int[] diagonal = top;
                top = buf[i];
                int[] left = buf[i - 1];

                if (n == m) {
                    buf[i] = Arrays.copyOf(diagonal, diagonal.length + 1);
                    buf[i][diagonal.length] = n;
                } else {
                    buf[i] = left.length > top.length ? left : top;
                }
            }
        }

        int[] max = Arrays.stream(buf).max(Comparator.comparingInt(a -> a.length)).orElseThrow();

        writer.write(Arrays.stream(max).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
        writer.flush();
    }
}

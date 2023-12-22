package contest53032;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/53032">Алг 4.0 ДЗ 4 (53032) Перебор и методы его оптимизации</a>
 * <a href="https://contest.yandex.ru/contest/53032/run-report/98978623">OK  313ms  36.41Mb</a>
 *
 * A. Все перестановки заданной длины
 * Ограничение времени 	2 секунды
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * По данному числу N (0 < N < 10) выведите все перестановки чисел от 1 до N в лексикографическом порядке.
 * </pre>
 */
public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        alg(reader, writer);
        reader.close();
        writer.close();
    }

    public static void alg(BufferedReader reader, BufferedWriter writer) throws IOException {
        int N = Integer.parseInt(reader.readLine().trim());

        String solution = alg1(N);

        writer.write(solution);
        writer.flush();
    }

    public static String alg1(int N) {
        StringBuilder sb = new StringBuilder();
        f1(N, new boolean[N + 1], new byte[1], sb);
        return sb.toString();
    }

    public static String alg2(int N) {
        StringBuilder sb = new StringBuilder();
        List<Character> unused = IntStream.range(1, N + 1).mapToObj(i -> (char) ('0' + i)).collect(Collectors.toList());
        f2(N, unused, new byte[1], sb);
        return sb.toString();
    }

    public static void f1(int N, boolean[] inUse, byte[] result, StringBuilder sb) {
        int n = result.length;
        if (n <= N) {
            for (byte i = 1; i <= N; i++) {
                if (!inUse[i]) {
                    boolean[] nowInUse = Arrays.copyOf(inUse, inUse.length);
                    nowInUse[i] = true;

                    byte[] nowResult = Arrays.copyOf(result, n + 1);
                    nowResult[n] = (byte) ('0' + i);

                    f1(N, nowInUse, nowResult, sb);
                }
            }
        } else {
            sb.append(new String(result, 1, N)).append("\n");
        }
    }

    public static void f2(int N, List<Character> unused, byte[] result, StringBuilder sb) {
        int n = result.length;
        if (n <= N) {
            for (Character c : unused) {
                List<Character> nowUnused = new ArrayList<>(unused);
                nowUnused.remove(c);

                byte[] nowResult = Arrays.copyOf(result, n + 1);
                nowResult[n] = (byte) c.charValue();

                f2(N, nowUnused, nowResult, sb);
            }
        } else {
            sb.append(new String(result, 1, N)).append("\n");
        }
    }
}

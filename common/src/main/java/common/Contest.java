package common;

import java.io.*;
import java.util.Arrays;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/zzzzz">Яндекс. zzzzz</a>
 * <a href="https://contest.yandex.ru/contest/zzzzz/run-report/zzzzz">OK  zzzzz</a>
 * .
 * </pre>
 */
public class Contest {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        alg(reader, writer);
        reader.close();
        writer.close();
    }

    public static void alg(BufferedReader reader, BufferedWriter writer) throws IOException {
        reader.readLine();

        int N = Integer.parseInt(reader.readLine().trim());

        int[] a = Arrays.stream(reader.readLine().split(" ")).filter(s -> !s.isEmpty()).mapToInt(Integer::parseInt).toArray();
        int A = a[0];
        int B = a[1];

        int[][] req = new int[N][];
        for (int i = 0; i < N; i++) {
            req[i] = Arrays.stream(reader.readLine().split(" ")).filter(s -> !s.isEmpty()).mapToInt(Integer::parseInt).toArray();
            req[i][0] = A % B;
        }

        String solution = alg1(a, req);

        writer.write(solution);
        writer.flush();
    }

    public static String alg1(int[] a, int[][] req) {
        return "" + req.length + a.length;
    }

    public static String alg2(int[] a, int[][] req) {
        return "" + req.length + a.length;
    }
}

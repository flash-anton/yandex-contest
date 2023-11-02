package common;

import java.io.*;
import java.util.Arrays;

/**
 * Чтение одного (нескольких) int-а (long-а) на строке (строках) из потока входных данных
 */
public class LineReader {
    public static void skipLine(BufferedReader reader) throws IOException {
        reader.readLine();
    }

    // ==== ints reading ====

    public static int intFromLine(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    public static int[] intsFromLine(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" ")).filter(s -> !s.isEmpty()).mapToInt(Integer::parseInt).toArray();
    }

    public static int[] intFromEachLine(BufferedReader reader, int lines) throws IOException {
        int[] b = new int[lines];
        for (int i = 0; i < lines; i++) {
            b[i] = Integer.parseInt(reader.readLine());
        }
        return b;
    }

    public static int[][] intsFromEachLine(BufferedReader reader, int lines) throws IOException {
        int[][] b = new int[lines][];
        for (int i = 0; i < lines; i++) {
            b[i] = Arrays.stream(reader.readLine().split(" ")).filter(s -> !s.isEmpty()).mapToInt(Integer::parseInt).toArray();
        }
        return b;
    }

    // ==== longs reading ====

    public static long longFromLine(BufferedReader reader) throws IOException {
        return Long.parseLong(reader.readLine());
    }

    public static long[] longsFromLine(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" ")).filter(s -> !s.isEmpty()).mapToLong(Long::parseLong).toArray();
    }

    public static long[] longFromEachLine(BufferedReader reader, int lines) throws IOException {
        long[] b = new long[lines];
        for (int i = 0; i < lines; i++) {
            b[i] = Long.parseLong(reader.readLine());
        }
        return b;
    }

    public static long[][] longsFromEachLine(BufferedReader reader, int lines) throws IOException {
        long[][] b = new long[lines][];
        for (int i = 0; i < lines; i++) {
            b[i] = Arrays.stream(reader.readLine().split(" ")).filter(s -> !s.isEmpty()).mapToLong(Long::parseLong).toArray();
        }
        return b;
    }
}

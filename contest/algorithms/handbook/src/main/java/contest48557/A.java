package contest48557;

import java.io.*;
import java.util.stream.IntStream;

import static java.util.Comparator.comparingInt;

/**
 * <pre>
 * <a href="https://education.yandex.ru/handbook/algorithms">Основы алгоритмов</a>
 * <a href="https://new.contest.yandex.ru/48557">Alg handbook 3.2 (48557) Жадные алгоритмы</a>
 * OK  159ms / 1s  13.02MB / 256MB
 *
 * A. Бронирование переговорки
 *
 * Задано n интервалов. Требуется найти максимальное количество взаимно непересекающихся интервалов.
 * Два интервала пересекаются, если они имеют хотя бы одну общую точку.
 *
 * Формат ввода
 * В первой строке задано одно число n (1≤n≤100) — количество интервалов.
 * В следующих nn строках заданы интервалы li,ri (1≤li≤ri≤50).
 *
 * Формат вывода
 * Выведите ответ на задачу.
 * </pre>
 */
public class A {
    public static void main(String[] args) throws IOException {
        try (InputStream reader = new BufferedInputStream(System.in, 3_000_000);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            alg(reader, writer);
        }
    }

    public static void alg(InputStream is, BufferedWriter writer) throws IOException {
        Reader reader = new Reader(is);
        int n = reader.nextInt();
        int[] start = new int[n];
        int[] end = new int[n];
        for (int i = 0; i < n; i++) {
            start[i] = reader.nextInt();
            end[i] = reader.nextInt();
        }

        int[] iSortedByEnd = IntStream.range(0, n).boxed().sorted(comparingInt(i -> end[i])).mapToInt(i -> i).toArray();

        int count = 0;
        int r = 0;
        for (int i : iSortedByEnd) {
            int l_ = start[i];
            int r_ = end[i];
            if (l_ > r) {
                count++;
                r = r_;
            }
        }

        String solution = "" + count;

        writer.write(solution);
        writer.flush();
    }

    /**
     * "Быстрый" ридер потока.
     */
    public static class Reader {
        private final InputStream is;
        private int lastReadByte = '\n';

        public Reader(InputStream is) {
            this.is = is;
        }

        public long nextLong() throws IOException {
            while (lastReadByte == ' ' || lastReadByte == '\n') {
                lastReadByte = is.read();
            }

            int sign = 1;
            if (lastReadByte == '-') {
                sign = -1;
                lastReadByte = is.read();
            }

            long num = 0;
            while (lastReadByte >= '0' && lastReadByte <= '9') {
                num = (num * 10) + sign * (lastReadByte - '0');
                lastReadByte = is.read();
            }
            return num;
        }

        public long[] nextLongs(long[] a) throws IOException {
            for (int i = 0; i < a.length; i++) {
                a[i] = nextLong();
            }
            return a;
        }

        public int nextInt() throws IOException {
            return (int) nextLong();
        }

        public int[] nextInts(int[] a) throws IOException {
            for (int i = 0; i < a.length; i++) {
                a[i] = nextInt();
            }
            return a;
        }

        public String nextWord() throws IOException {
            while (lastReadByte == ' ' || lastReadByte == '\n') {
                lastReadByte = is.read();
            }

            StringBuilder sb = new StringBuilder();
            while (!(lastReadByte == -1 || lastReadByte == ' ' || lastReadByte == '\n')) {
                sb.append((char) lastReadByte);
                lastReadByte = is.read();
            }
            return sb.toString();
        }

        public String[] nextWords(String[] a) throws IOException {
            for (int i = 0; i < a.length; i++) {
                a[i] = nextWord();
            }
            return a;
        }

        public String readLine() throws IOException {
            if (lastReadByte == '\n') {
                lastReadByte = is.read();
            }

            StringBuilder sb = new StringBuilder();
            while (!(lastReadByte == -1 || lastReadByte == '\n')) {
                sb.append((char) lastReadByte);
                lastReadByte = is.read();
            }
            return sb.toString();
        }
    }
}

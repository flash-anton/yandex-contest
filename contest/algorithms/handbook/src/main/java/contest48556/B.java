package contest48556;

import java.io.*;

/**
 * <pre>
 * <a href="https://education.yandex.ru/handbook/algorithms">Основы алгоритмов</a>
 * <a href="https://new.contest.yandex.ru/48556">Alg handbook 3.1 (48556) Полный перебор и оптимизация перебора</a>
 * OK  135ms / 1s  12.18MB / 256MB
 *
 * B. Сочетания
 *
 * Выведите число сочетаний C(n,k).
 *
 * Формат ввода
 * В первой строке находится два числа n (1≤n≤7), k (1≤k≤7).
 *
 * Формат вывода
 * Выведите ответ на задачу.
 * </pre>
 */
public class B {
    public static void main(String[] args) throws IOException {
        try (InputStream reader = new BufferedInputStream(System.in, 3_000_000);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            alg(reader, writer);
        }
    }

    public static void alg(InputStream is, BufferedWriter writer) throws IOException {
        Reader reader = new Reader(is);
        int n = reader.nextInt();
        int k = reader.nextInt();

        //                 0  1  2  3  4   5    6    7
        int[] factorial = {1, 1, 2, 6, 24, 120, 720, 5040};
        // C(n,k) = n!/(k!*(n-k)!)
        int c = 0;
        if (n >= k)
            c = factorial[n] / (factorial[k] * factorial[(n - k)]);

        String solution = "" + c;

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

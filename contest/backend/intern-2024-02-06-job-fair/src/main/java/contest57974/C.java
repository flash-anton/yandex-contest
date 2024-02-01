package contest57974;

import java.io.*;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/57974">Intern 2024-02-06 job fair (57974)</a>
 * <a href="https://contest.yandex.ru/contest/57974/run-report/106373747">OK  0.505s  78.02Mb</a>
 *
 * C. Инверсии
 * Ограничение времени 	8 секунд
 * Ограничение памяти 	1Gb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Пусть p1, p2, …, pn перестановка чисел от 1 до n.
 * Будем говорить, что пара индексов (i,j) образует инверсию, если i<j и pi>pj.
 *
 * Задана некоторая перестановка (p1,…,pn),
 * требуется определить среднее количество инверсий в перестановке,
 * полученной из данной после одной перестановки пары элементов.
 * При этом индексы переставляемых элементов выбираются равновероятно среди всех пар различных чисел от 1 до n.
 *
 * Формат ввода
 * В первой строке записано одно целое число n (2≤n≤2000).
 * Во второй строке записаны n целых чисел p1, p2, …, pn (1≤pi≤n), все числа в строке различны.
 *
 * Формат вывода
 * Выведите несократимую дробь a∕b,
 * задающую значение среднего числа инверсий по всем возможным парам переставляемых индексов элементов.
 * </pre>
 */
public class C {
    public static void main(String[] args) throws IOException {
        try (InputStream reader = new BufferedInputStream(System.in, 3_000_000);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            alg(reader, writer);
        }
    }

    public static void alg(InputStream is, BufferedWriter writer) throws IOException {
        Reader reader = new Reader(is);
        int n = reader.nextInt();
        int[] p = reader.nextInts(new int[n]);

        String solution = alg1(n, p);

        writer.write(solution);
        writer.flush();
    }

    static String alg1(int n, int[] p) {
        n += 2; // + empty left, right, top, bottom
        int[][] piSmallerPjCount = new int[n][n];
        int[][] piGreaterPjCount = new int[n][n];
        int[][] pjSmallerPiCount = new int[n][n];
        int[][] pjGreaterPiCount = new int[n][n];
        int inversionCount = 0;
        for (int i = 1; i <= n - 3; i++) {
            for (int j = i + 1; j <= n - 2; j++) {
                piSmallerPjCount[i][j] = piSmallerPjCount[i][j - 1];
                piGreaterPjCount[i][j] = piGreaterPjCount[i][j - 1];
                if (p[i - 1] < p[j - 1]) {
                    piSmallerPjCount[i][j]++;
                } else if (p[i - 1] > p[j - 1]) {
                    piGreaterPjCount[i][j]++;
                    inversionCount++;
                }
            }
        }
        for (int j = n - 2; j >= 2; j--) {
            for (int i = j - 1; i >= 1; i--) {
                pjSmallerPiCount[j][i] = pjSmallerPiCount[j][i + 1];
                pjGreaterPiCount[j][i] = pjGreaterPiCount[j][i + 1];
                if (p[i - 1] < p[j - 1]) {
                    pjGreaterPiCount[j][i]++;
                } else if (p[i - 1] > p[j - 1]) {
                    pjSmallerPiCount[j][i]++;
                }
            }
        }

        long a = 0;
        long b = 0;
        for (int i = 1; i <= n - 3; i++) {
            for (int j = i + 1; j <= n - 2; j++) {
                int A = inversionCount;
                A += piSmallerPjCount[i][j - 1];
                A -= piGreaterPjCount[i][j - 1];
                A += pjGreaterPiCount[j][i + 1];
                A -= pjSmallerPiCount[j][i + 1];
                if (p[i - 1] < p[j - 1]) {
                    A++;
                } else if (p[i - 1] > p[j - 1]) {
                    A--;
                }
                a += A;
                b++;
            }
        }
        long gcd = gcd(a, b);
        a /= gcd;
        b /= gcd;

        return a + "/" + b;
    }

    static long gcd(long big, long small) {
        return small == 0 ? big : gcd(small, big % small);
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

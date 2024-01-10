package contest36783;

import java.io.*;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/36783">Interview training 36783</a>
 * <a href="https://contest.yandex.ru/contest/36783/run-report/104545872">OK  311ms  19.72Mb</a>
 *
 * C. Статус 200
 * Язык 	Ограничение времени 	Ограничение памяти 	Ввод 	Вывод
 * Все языки 	1 секунда 	256Mb 	стандартный ввод или input.txt 	стандартный вывод или output.txt
 * Python 3.7.3 	2 секунды 	256Mb
 * C# (MS .NET 6.0 + ASP) 	2 секунды 	64Mb
 * OpenJDK Java 15 	2 секунды 	64Mb
 * GNU c++17 7.3 	0.3 секунды 	64Mb
 * GNU GCC 12.2 C++20 	0.3 секунды 	64Mb
 * C# (MS .NET 5.0 + ASP) 	2 секунды 	64Mb
 *
 * Вам дан массив натуральных чисел ai. Найдите число таких пар элементов (ai,aj), где ∣ai−aj∣%200==0 и i<j.
 * Вы можете воспользоваться заготовками кода для данной задачи из <a href="https://github.com/Yandex-Practicum/algorithms-templates">репозитория курса</a>.
 *
 * Формат ввода
 * В первой строке дан размер массива n (1≤n≤200000 ).
 * Во второй строке через пробел записаны элементы массива ai (1≤ai≤10^9).
 *
 * Формат вывода
 * Выведите единственное число — количество пар, подходящих под указанное выше условие.
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
        int[] a = reader.nextInts(new int[n]);

        String solution = alg1(n, a);

        writer.write(solution);
        writer.flush();
    }

    public static String alg1(int n, int[] a) {
        int[] remainder = new int[200];
        for (int i = 0; i < n; i++)
            remainder[a[i] % 200]++;

        long pairsCount = 0;
        for (int i = 0; i < 200; i++)
            pairsCount += (long) remainder[i] * (remainder[i] - 1) / 2;

        return "" + pairsCount;
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

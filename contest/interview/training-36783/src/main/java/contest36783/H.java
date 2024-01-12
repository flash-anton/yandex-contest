package contest36783;

import java.io.*;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/36783">Interview training 36783</a>
 * <a href="https://contest.yandex.ru/contest/36783/run-report/104789018">OK  439ms  24.78Mb</a>
 *
 * H. Сизиф
 * Язык 	Ограничение времени 	Ограничение памяти 	Ввод 	Вывод
 * Все языки 	1 секунда 	256Mb 	стандартный ввод или input.txt 	стандартный вывод или output.txt
 * Node.js 14.15.5 	2 секунды 	256Mb
 * Python 3.7.3 	1 секунда 	64Mb
 * Golang 1.20.1 	0.3 секунды 	64Mb
 * GNU c++17 7.3 	0.3 секунды 	64Mb
 * GNU GCC 12.2 C++20 	0.3 секунды 	64Mb
 *
 * В этой задаче вы будете перекладывать камни.
 * Изначально есть n кучек камней. Кучка i весит ai килограммов. Кучки можно объединять.
 * При объединении кучек i и j затрачивается ai+aj единиц энергии, при этом две исходные кучки пропадают и появляется кучка весом ai+aj.
 * Определите наименьшее количество энергии, которое надо затратить для объединения всех кучек в одну.
 *
 * Вы можете воспользоваться заготовками кода для данной задачи из репозитория курса.
 *
 * Формат ввода
 * В первой строке дано число n (1≤n≤10^5)
 * В следующей строке записаны массы кучек через пробел — ai (1≤ai≤10^6)
 *
 * Формат вывода
 * Выведите единственное число — минимальную энергию, которую надо затратить на объединение всех кучек. .
 * </pre>
 */
public class H {
    public static void main(String[] args) throws IOException {
        try (InputStream reader = new BufferedInputStream(System.in, 3_000_000);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            alg(reader, writer);
        }
    }

    public static void alg(InputStream is, BufferedWriter writer) throws IOException {
        Reader reader = new Reader(is);
        int n = reader.nextInt();
        Queue<Long> a = new PriorityQueue<>(n);
        for (int i = 0; i < n; i++) {
            a.offer(reader.nextLong());
        }

        long sum = 0;
        while (a.size() > 1) {
            long b = a.poll() + a.poll();
            a.offer(b);
            sum += b;
        }

        String solution = "" + sum;

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

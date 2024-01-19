package contest36783;

import java.io.*;
import java.util.*;

import static java.lang.Math.max;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/36783">Interview training 36783</a>
 * <a href="https://contest.yandex.ru/contest/36783/run-report/105318133">OK  0.78s  68.69Mb</a>
 *
 * T. XORошая задача
 * Язык 	Ограничение времени 	Ограничение памяти 	Ввод 	Вывод
 * Все языки 	0.6 секунд 	256Mb 	стандартный ввод или input.txt 	стандартный вывод или output.txt
 * Node.js 14.15.5 	3 секунды 	256Mb
 * Python 3.7.3 	3 секунды 	256Mb
 * OpenJDK Java 11 	2 секунды 	256Mb
 * C# (MS .NET 6.0 + ASP) 	2 секунды 	256Mb
 * OpenJDK Java 15 	2 секунды 	256Mb
 * C# (MS .NET 5.0 + ASP) 	2 секунды 	256Mb
 *
 * В этой задаче требуется по заданному массиву длины n найти два его элемента xi и xj(1≤i≤j≤n),
 * которые давали бы максимальное значение функции xi⊕xj,
 * где под ⊕ подразумевается операция побитового исключающего «или», то есть xor.
 *
 * Вы можете воспользоваться заготовками кода для данной задачи из репозитория курса.
 *
 * Формат ввода
 * В первой строке дан размер массива n (1≤n≤10^5).
 * Во второй строке через пробел записаны n целых чисел xi (1≤xi≤2^31−1).
 *
 * Формат вывода
 * Выведите единственное число — максимальное значение xor-а, которое можно получить.
 * </pre>
 */
public class T {
    public static void main(String[] args) throws IOException {
        try (InputStream reader = new BufferedInputStream(System.in, 3_000_000);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            alg(reader, writer);
        }
    }

    public static void alg(InputStream is, BufferedWriter writer) throws IOException {
        Reader reader = new Reader(is);
        int n = reader.nextInt();
        List<Integer> x = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            x.add(reader.nextInt());
        }

        String solution = alg2(n, x);

        writer.write(solution);
        writer.flush();
    }

    public static String alg1(int n, List<Integer> x) {
        if (n == 1)
            return "" + x.get(0);

        int max = 0;
        for (int i = 0; i < n - 1; i++) {
            int xi = x.get(i);
            for (int j = i + 1; j < n; j++) {
                int xj = x.get(j);
                max = max(max, xi ^ xj);
            }
        }
        return "" + max;
    }

    public static String alg2(int n, List<Integer> x) {
        Set<Integer> set = new HashSet<>(x);

        String solution;
        if (n == 1) {
            solution = "" + set.iterator().next();

        } else if (set.size() == 1) {
            solution = "" + (n % 2 == 1 ? set.iterator().next() : 0);

        } else {
            BitTreeNode head = new BitTreeNode(31);
            for (int number : set) {
                head.add(number);
            }

            int max = 0;
            for (int number : set) {
                int bestForXor = head.getBestForXorWith(number);
                max = max(max, number ^ bestForXor);
            }

            solution = "" + max;
        }
        return solution;
    }

    static class BitTreeNode {
        final int bitIndex;
        BitTreeNode zero;
        BitTreeNode one;

        BitTreeNode(int bitIndex) {
            this.bitIndex = bitIndex;
        }

        void add(int number) {
            if (bitIndex >= 0) {
                int bitValue = number >> bitIndex & 1;
                if (bitValue == 0) {
                    if (zero == null)
                        zero = new BitTreeNode(bitIndex - 1);
                    zero.add(number);
                } else {
                    if (one == null)
                        one = new BitTreeNode(bitIndex - 1);
                    one.add(number);
                }
            }
        }

        int getBestForXorWith(int number) {
            if (bitIndex < 0) {
                return 0;
            } else {
                int bitValue = number >> bitIndex & 1;
                if (bitValue == 0) {
                    if (one != null)
                        return (1 << bitIndex) + one.getBestForXorWith(number);
                    else
                        return zero.getBestForXorWith(number);
                } else {
                    if (zero != null)
                        return zero.getBestForXorWith(number);
                    else
                        return (1 << bitIndex) + one.getBestForXorWith(number);
                }
            }
        }
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

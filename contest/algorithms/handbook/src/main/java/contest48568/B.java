package contest48568;

import java.io.*;
import java.util.stream.IntStream;

import static java.lang.Math.pow;

/**
 * <pre>
 * <a href="https://education.yandex.ru/handbook/algorithms">Основы алгоритмов</a>
 * <a href="https://new.contest.yandex.ru/48568">Alg handbook 3.4 (48568) Рекурсивные алгоритмы</a>
 * OK  159ms / 1s  12.8MB / 256MB
 *
 * B. Ханойские башни 2
 *
 * Головоломка <<Ханойские башни>> состоит из трёх стержней, пронумеруем их слева направо: 1, 2 и 3.
 * Также в головоломке используется стопка дисков с отверстием посередине.
 * Радиус дисков уменьшается снизу вверх.
 * Изначально диски расположены на левом стержне (стержень 1), самый большой диск находится внизу.
 * Диски в игре перемещаются по одному со стержня на стержень.
 * Диск можно надеть на стержень, только если он пустой или верхний диск на нём большего размера, чем перемещаемый.
 * Цель головоломки — перенести все диски со стержня 1 на стержень 3.
 *
 * Немного изменим правила.
 * Теперь головоломка состоит из четырех стержней, а цель головоломки — перенести все диски со стержня 1 на стержень 4.
 * Найдите минимальное количество ходов, за которое можно решить головоломку.
 *
 * Формат ввода
 * В первой строке задано одно число n (3≤n≤10) — количество дисков на первой башне.
 *
 * Формат вывода
 * В единственной строке выведите ответ на задачу.
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

        String solution = "" + minMoves(n, 4);

        writer.write(solution);
        writer.flush();
    }

    static int minMoves(int discCount, int pegCount) {
        if (discCount == 0) {
            return 0;

        } else if (discCount == 1 && pegCount > 1) {
            return 1;

        } else if (discCount > 1 && pegCount == 3) {
            // https://education.yandex.ru/handbook/algorithms/article/rekursivnye-algoritmy
            return (int) (pow(2, discCount) - 1);

        } else if (discCount > 1 && pegCount > 3) {
            // https://en.wikipedia.org/wiki/Tower_of_Hanoi#Frame%E2%80%93Stewart_algorithm
            // https://dl.acm.org/doi/pdf/10.1145/126459.126460
            return (int) IntStream.range(1, discCount)
                    .mapToLong(i -> 2L * minMoves(i, pegCount) + minMoves(discCount - i, pegCount - 1))
                    .min().orElseThrow();
        } else {
            return Integer.MAX_VALUE;
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

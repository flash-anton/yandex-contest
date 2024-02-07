package contest48568;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * <pre>
 * <a href="https://education.yandex.ru/handbook/algorithms">Основы алгоритмов</a>
 * <a href="https://new.contest.yandex.ru/48568">Alg handbook 3.4 (48568) Рекурсивные алгоритмы</a>
 * OK  105ms / 1s  11.38MB / 256MB
 *
 * A. Ханойские башни
 *
 * Головоломка <<Ханойские башни>> состоит из трёх стержней, пронумеруем их слева направо: 1, 2 и 3.
 * Также в головоломке используется стопка дисков с отверстием посередине.
 * Радиус дисков уменьшается снизу вверх. Изначально диски расположены на левом стержне (стержень 1),
 * самый большой диск находится внизу.
 * Диски в игре перемещаются по одному со стержня на стержень.
 * Диск можно надеть на стержень, только если он пустой или верхний диск на нём большего размера, чем перемещаемый.
 * Цель головоломки — перенести все диски со стержня 1 на стержень 3.
 *
 * Требуется найти последовательность ходов, которая решает головоломку <<Ханойские башни>>.
 *
 * Формат ввода
 * В первой строке задано одно число n (3≤n≤10) — количество дисков на первой башне.
 *
 * Формат вывода
 * В первой строке выведите количество операций k.
 * В следующих kk строках выведите по два числа в каждой xi,yi (1≤xi,yi≤3) — переместить верхний диск
 * со стержня xi на стержень yi.
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

        Queue<int[]> fromToQueue = new ArrayDeque<>();
        move(n, 1, 3, 2, fromToQueue);

        StringBuilder sb = new StringBuilder();
        sb.append(fromToQueue.size()).append('\n');
        for (int[] fromTo : fromToQueue) {
            sb.append(fromTo[0]).append(' ').append(fromTo[1]).append('\n');
        }

        String solution = sb.toString();

        writer.write(solution);
        writer.flush();
    }

    static void move(int discCount, int from, int to, int tmp, Queue<int[]> fromToQueue) {
        if (discCount == 1) {
            fromToQueue.offer(new int[]{from, to});
        } else {
            move(discCount - 1, from, tmp, to, fromToQueue);
            fromToQueue.offer(new int[]{from, to});
            move(discCount - 1, tmp, to, from, fromToQueue);
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

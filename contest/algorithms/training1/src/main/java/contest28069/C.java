package contest28069;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/28069">Алг 1.0 ДЗ 8 (28069) Деревья</a>
 * <a href="https://contest.yandex.ru/contest/28069/run-report/106189550">OK  249ms  16.78Mb</a>
 *
 * C. Второй максимум
 * 	Все языки 	Python 3.6
 * Ограничение времени 	2 секунды 	4 секунды
 * Ограничение памяти 	64Mb 	256Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Выведите второй по величине элемент в построенном дереве. Гарантируется, что такой найдется.
 *
 * Формат ввода
 * Дана последовательность целых чисел, оканчивающаяся нулем. Сам ноль в последовательность не входит.
 *
 * Формат вывода
 * Выведите ответ на задачу.
 * </pre>
 */
public class C {
    public static void main(String[] args) throws IOException {
        try (InputStream reader = new BufferedInputStream(System.in, 3_000_000);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            alg(reader, writer);
        }
    }

    static class Node {
        Long value;
        Node smaller;
        Node greater;

        Integer add(long value) {
            if (this.value == null) {
                this.value = value;
                return 1;
            } else if (this.value == value) {
                return null;
            } else if (this.value > value) {
                if (smaller == null)
                    smaller = new Node();
                Integer deep = smaller.add(value);
                return deep == null ? null : (1 + deep);
            } else {
                if (greater == null)
                    greater = new Node();
                Integer deep = greater.add(value);
                return deep == null ? null : (1 + deep);
            }
        }

        List<Long> tail(int size) {
            List<Long> tail = new ArrayList<>();

            if (greater != null)
                tail.addAll(greater.tail(size));

            if (value != null && tail.size() < size)
                tail.add(0, value);

            if (smaller != null && tail.size() < size)
                tail.addAll(0, smaller.tail(size - tail.size()));

            return tail;
        }
    }

    public static void alg(InputStream is, BufferedWriter writer) throws IOException {
        Reader reader = new Reader(is);

        Node root = new Node();
        long value;
        while ((value = reader.nextLong()) != 0) {
            root.add(value);
        }

        List<Long> tail = root.tail(2);

        String solution = "" + tail.get(0);

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

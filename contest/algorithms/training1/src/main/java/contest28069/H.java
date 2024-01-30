package contest28069;

import java.io.*;

import static java.lang.Math.abs;
import static java.lang.Math.max;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/28069">Алг 1.0 ДЗ 8 (28069) Деревья</a>
 * <a href="https://contest.yandex.ru/contest/28069/run-report/106255008">OK  207ms  15.89Mb</a>
 *
 * H. АВЛ-сбалансированность
 * 	Все языки 	Python 3.6
 * Ограничение времени 	2 секунды 	4 секунды
 * Ограничение памяти 	64Mb 	256Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Дерево называется АВЛ-сбалансированным,
 * если для любой его вершины высота левого и правого поддерева для этой вершины различаются не более чем на 1.
 *
 * Формат ввода
 * Вводится последовательность целых чисел, оканчивающаяся нулем. Сам ноль в последовательность не входит.
 * Постройте дерево, соответствующее данной последовательности.
 *
 * Формат вывода
 * Определите, является ли дерево сбалансированным, выведите слово YES или NO.
 * </pre>
 */
public class H {
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

        int height() {
            int height = 0;
            if (value != null) {
                int left = 0;
                if (smaller != null)
                    left = smaller.height();

                int right = 0;
                if (greater != null)
                    right = greater.height();

                if (left == -1 || right == -1 || abs(left - right) > 1)
                    height = -1;
                else
                    height = 1 + max(left, right);
            }
            return height;
        }
    }

    public static void alg(InputStream is, BufferedWriter writer) throws IOException {
        Reader reader = new Reader(is);

        Node root = new Node();

        long value;
        while ((value = reader.nextLong()) != 0) {
            root.add(value);
        }

        int height = root.height();

        String solution = height == -1 ? "NO" : "YES";

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

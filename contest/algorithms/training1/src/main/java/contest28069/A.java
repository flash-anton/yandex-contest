package contest28069;

import java.io.*;

import static java.lang.Math.max;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/28069">Алг 1.0 ДЗ 8 (28069) Деревья</a>
 * <a href="https://contest.yandex.ru/contest/28069/run-report/106138191">OK  235ms  16.20Mb</a>
 *
 * A. Высота дерева
 * 	Все языки 	Python 3.6
 * Ограничение времени 	2 секунды 	4 секунды
 * Ограничение памяти 	64Mb 	256Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Реализуйте бинарное дерево поиска для целых чисел.
 * Программа получает на вход последовательность целых чисел и строит из них дерево.
 * Элементы в деревья добавляются в соответствии с результатом поиска их места.
 * Если элемент уже существует в дереве, добавлять его не надо.
 * Балансировка дерева не производится.
 *
 * Формат ввода
 * На вход программа получает последовательность натуральных чисел.
 * Последовательность завершается числом 0, которое означает конец ввода, и добавлять его в дерево не надо.
 *
 * Формат вывода
 * Выведите единственное число – высоту получившегося дерева.
 * </pre>
 */
public class A {
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

        int add(long value) {
            if (this.value == null) {
                this.value = value;
            }

            if (this.value == value) {
                return 1;
            } else if (this.value < value) {
                if (smaller == null)
                    smaller = new Node();
                return 1 + smaller.add(value);
            } else {
                if (greater == null)
                    greater = new Node();
                return 1 + greater.add(value);
            }
        }
    }

    public static void alg(InputStream is, BufferedWriter writer) throws IOException {
        Reader reader = new Reader(is);

        Node root = new Node();
        int maxDeep = 0;

        long value;
        while ((value = reader.nextLong()) != 0) {
            maxDeep = max(maxDeep, root.add(value));
        }

        String solution = "" + maxDeep;

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

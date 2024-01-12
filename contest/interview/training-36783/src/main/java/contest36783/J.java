package contest36783;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/36783">Interview training 36783</a>
 * <a href="https://contest.yandex.ru/contest/36783/run-report/104794624">OK  2.937s  66.31Mb</a>
 *
 * J. Ферзи
 * Ограничение времени 	4 секунды
 * Ограничение памяти 	256Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Определите все расстановки из n ферзей на шахматной доске n×n, где ферзи не могут бить друг друга.
 *
 * В качестве ответа на задачу выведите в первой строке число расстановок, а далее все расстановки в следующем формате:
 * одна расстановка описывается n числами.
 * i-е число описывает i-ую строку доски, а именно оно равно номеру клетки, в которой стоит ферзь на текущей строке.
 * Строки нумеруются сверху вниз от 1 до n.
 * Клетки внутри строки нумеруются от 1 до n слева направо.
 *
 * Вы можете воспользоваться заготовками кода для данной задачи из репозитория курса.
 *
 * Формат ввода
 * На вход подаётся единственное число n (1≤n≤13).
 *
 * Формат вывода
 * Выведите единственное число — количество возможных расстановок.
 * Далее по одной в строке выведите расстановки в произвольном порядке.
 * </pre>
 */
public class J {
    public static void main(String[] args) throws IOException {
        try (InputStream reader = new BufferedInputStream(System.in, 3_000_000);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            alg(reader, writer);
        }
    }

    public static void alg(InputStream is, BufferedWriter writer) throws IOException {
        Reader reader = new Reader(is);
        int n = reader.nextInt();

        String solution = alg1(n);

        writer.write(solution);
        writer.flush();
    }

    public static class Table {
        final int n;
        final int n2;
        List<Integer> x = new ArrayList<>();
        List<Integer> y = new ArrayList<>();
        Set<Integer> horizontals = new HashSet<>();
        Set<Integer> verticals = new HashSet<>();
        Set<Integer> diagonals1 = new HashSet<>();
        Set<Integer> diagonals2 = new HashSet<>();

        Table(int n) {
            this.n = n;
            this.n2 = n * n;
        }

        boolean check(int x, int y) {
            return !horizontals.contains(y) &&
                    !verticals.contains(x) &&
                    !diagonals1.contains(x + y) &&
                    !diagonals2.contains(x - y);
        }

        void add(int x, int y) {
            this.x.add(x);
            this.y.add(y);
            horizontals.add(y);
            verticals.add(x);
            diagonals1.add(x + y);
            diagonals2.add(x - y);
        }

        void remove(Integer x, Integer y) {
            this.x.remove(x);
            this.y.remove(y);
            horizontals.remove(y);
            verticals.remove(x);
            diagonals1.remove(x + y);
            diagonals2.remove(x - y);
        }

        void addResult(StringBuilder sb) {
            sb.append('\n');
            x.forEach(i -> sb.append(i + 1).append(' '));
        }
    }

    public static String alg1(int n) {
        Table table = new Table(n);

        StringBuilder sb = new StringBuilder();
        int count = filler(table, -1, sb);
        sb.insert(0, count);

        return sb.toString();
    }

    public static int filler(Table table, int y, StringBuilder sb) {
        if (table.x.size() == table.n) {
            table.addResult(sb);
            return 1;
        }

        int j = y + 1;
        if (j == table.n) {
            return 0;
        }

        int count = 0;
        for (int i = 0; i < table.n; i++) {
            if (table.check(i, j)) {
                table.add(i, j);
                count += filler(table, j, sb);
                table.remove(i, j);
            }
        }
        return count;
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

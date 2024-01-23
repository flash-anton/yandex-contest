package contest27883;

import java.io.*;
import java.util.*;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.util.stream.Collectors.joining;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/27883">Алг 1.0 ДЗ 7 (27883) Сортировка событий</a>
 * <a href="https://contest.yandex.ru/contest/27883/run-report/105662884">OK  1.039s  46.64Mb</a>
 *
 * B. Точки и отрезки
 * Ограничение времени 	3 секунды
 * Ограничение памяти 	256Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Дано n отрезков на числовой прямой и m точек на этой же прямой.
 * Для каждой из данных точек определите, скольким отрезкам они принадлежат.
 * Точка x считается принадлежащей отрезку с концами a и b, если выполняется двойное неравенство min(a, b) ≤ x ≤ max(a, b).
 *
 * Формат ввода
 *
 * Первая строка содержит два целых числа n (1 ≤ n ≤ 10^5) – число отрезков и m (1 ≤ m ≤ 10^5) – число точек.
 * В следующих n строках по два целых числи ai и bi – координаты концов соответствующего отрезка.
 * В последней строке m целых чисел – координаты точек.
 * Все числа по модулю не превосходят 10^9
 *
 * Формат вывода
 * В выходной файл выведите m чисел – для каждой точки количество отрезков, в которых она содержится.
 * </pre>
 */
public class B {
    public static void main(String[] args) throws IOException {
        try (InputStream reader = new BufferedInputStream(System.in, 3_000_000);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            alg(reader, writer);
        }
    }

    static class Event implements Comparable<Event> {
        enum Type {START, X, END} // порядок не менять - важен для сравнения событий

        final Type type;
        final int cord;

        Event(Type type, int cord) {
            this.type = type;
            this.cord = cord;
        }

        @Override
        public int compareTo(Event e) {
            int c = Integer.compare(cord, e.cord);
            if (c == 0 && type != e.type)
                c = type.ordinal() < e.type.ordinal() ? -1 : 1;
            return c;
        }
    }

    public static void alg(InputStream is, BufferedWriter writer) throws IOException {
        Reader reader = new Reader(is);
        int n = reader.nextInt();
        int m = reader.nextInt();

        List<Event> events = new ArrayList<>(n * 2 + m);
        for (int i = 0; i < n; i++) {
            int a = reader.nextInt();
            int b = reader.nextInt();
            events.add(new Event(Event.Type.START, min(a, b)));
            events.add(new Event(Event.Type.END, max(a, b)));
        }

        // Предполагаем, что точки могут повторяться, т.к. в условии не сказано обратного
        List<Integer> xList = new ArrayList<>(m);
        Map<Integer, Integer> segmentsByX = new HashMap<>();
        for (int i = 0; i < m; i++) {
            int x = reader.nextInt();
            xList.add(x);
            segmentsByX.putIfAbsent(x, 0);
        }

        for (int x : segmentsByX.keySet()) {
            events.add(new Event(Event.Type.X, x));
        }

        Collections.sort(events);

        int segments = 0;
        for (Event e : events) {
            if (e.type == Event.Type.START)
                segments++;
            else if (e.type == Event.Type.END)
                segments--;
            else
                segmentsByX.put(e.cord, segments);
        }

        String solution = xList.stream().map(segmentsByX::get).map(String::valueOf).collect(joining(" "));

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

package contest27883;

import java.io.*;
import java.util.*;

import static java.lang.Math.min;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/27883">Алг 1.0 ДЗ 7 (27883) Сортировка событий</a>
 * <a href="https://contest.yandex.ru/contest/27883/run-report/105876660">OK  2.504s  47.20Mb</a>
 *
 * H. Охрана
 * Ограничение времени 	4 секунды
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * На секретной военной базе работает N охранников.
 * Сутки поделены на 10000 равных промежутков времени,
 * и известно когда каждый из охранников приходит на дежурство и уходит с него.
 * Например, если охранник приходит в 5, а уходит в 8, то значит, что он был в 6, 7 и 8-ой промежуток (а в 5-й нет!!!).
 *
 * Укажите, верно ли что для данного набора охранников,
 * объект охраняется в любой момент времени хотя бы одним охранником
 * и удаление любого из них приводит к появлению промежутка времени, когда объект не охраняется.
 * 
 * Формат ввода
 * В первой строке входного файла записано натуральное число K (1 ≤ K ≤ 100) — количество тестов в файле.
 * Каждый тест начинается с числа N (1 ≤ N ≤ 10000), за которым следует N пар неотрицательных целых чисел
 * A и B — время прихода на дежурство и ухода (0 ≤ A ≤ B ≤ 10000) соответствующего охранника.
 *
 * Формат вывода
 * Выведите K строк, где в M-ой строке находится слово Accepted,
 * если M-ый набор охранников удовлетворяет описанным выше условиям.
 * В противном случае выведите Wrong Answer.
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
        int k = reader.nextInt();
        int[][] kna = new int[k][];
        int[][] knb = new int[k][];
        for (int i = 0; i < k; i++) {
            int n = reader.nextInt();
            kna[i] = new int[n];
            knb[i] = new int[n];
            for (int j = 0; j < n; j++) {
                kna[i][j] = reader.nextInt();
                knb[i][j] = reader.nextInt();
            }
        }

        String solution = alg1(k, kna, knb);

        writer.write(solution);
        writer.flush();
    }

    static class Event implements Comparable<Event> {
        enum Type {BEGIN, END}

        final Type type;
        final int moment;
        final int guard;

        Event(Type type, int moment, int guard) {
            this.type = type;
            this.moment = moment;
            this.guard = guard;
        }

        @Override
        public int compareTo(Event e) {
            int c = Integer.compare(moment, e.moment);
            if (c == 0)
                c = Integer.compare(type.ordinal(), e.type.ordinal());
            return c;
        }
    }

    static List<Event> events(int n, int[] a, int[] b) {
        List<Event> events = new ArrayList<>(n * 2);
        for (int i = 0; i < n; i++) {
            events.add(new Event(Event.Type.BEGIN, a[i], i));
            events.add(new Event(Event.Type.END, b[i], i));
        }
        events.sort(Event::compareTo);
        return events;
    }

    static boolean check(int n, int[] a, int[] b) {
        List<Event> events = events(n, a, b);

        if (events.isEmpty() || events.get(0).moment != 0 || events.get(events.size() - 1).moment != 10_000) {
            // базу должен кто-то защищать с первого по последний промежуток времени
            return false;
        }

        int minGuardCount = Integer.MAX_VALUE;
        Set<Integer> aloneGuards = new HashSet<>(n, 1);

        int lastMoment = 0;
        Set<Integer> lastMomentGuards = new HashSet<>(n, 1);

        for (Event e : events) {
            if (e.moment != lastMoment) {
                minGuardCount = min(minGuardCount, lastMomentGuards.size());
                if (lastMomentGuards.size() == 1) {
                    aloneGuards.add(lastMomentGuards.iterator().next());
                }
            }

            lastMoment = e.moment;
            if (e.type == Event.Type.BEGIN) {
                lastMomentGuards.add(e.guard);
            } else {
                lastMomentGuards.remove(e.guard);
            }
        }

        return minGuardCount == 1 && aloneGuards.size() == n;
    }

    public static String alg1(int k, int[][] kna, int[][] knb) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < k; i++) {
            int n = kna[i].length;
            int[] a = kna[i];
            int[] b = knb[i];
            if (check(n, a, b)) {
                sb.append("Accepted").append('\n');
            } else {
                sb.append("Wrong Answer").append('\n');
            }
        }
        return sb.toString();
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

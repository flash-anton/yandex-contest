package contest27883;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/27883">Алг 1.0 ДЗ 7 (27883) Сортировка событий</a>
 * <a href="https://contest.yandex.ru/contest/27883/run-report/105845136">OK  132ms  12.83Mb</a>
 *
 * G. Детский праздник
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Организаторы детского праздника планируют надуть для него M воздушных шариков.
 * С этой целью они пригласили N добровольных помощников, i-й среди которых надувает шарик за Ti минут,
 * однако каждый раз после надувания Zi шариков устает и отдыхает Yi минут.
 * Теперь организаторы праздника хотят узнать, через какое время будут надуты все шарики при наиболее оптимальной
 * работе помощников, и сколько шариков надует каждый из них.
 * (Если помощник надул шарик, и должен отдохнуть, но больше шариков ему надувать не придется, то считается,
 * что он закончил работу сразу после окончания надувания последнего шарика, а не после отдыха).
 *
 * Формат ввода
 * В первой строке входных данных задаются числа M и N (0 ≤ M ≤ 15000, 1 ≤ N ≤ 1000).
 * Следующие N строк содержат по три целых числа - Ti, Zi и Yi соответственно (1 ≤ Ti, Yi ≤ 100, 1 ≤ Zi ≤ 1000).
 *
 * Формат вывода
 * Выведите в первой строке число T - время, за которое будут надуты все шарики.
 * Во второй строке выведите N чисел - количество шариков, надутых каждым из приглашенных помощников.
 * Разделяйте числа пробелами.
 * Если распределений шариков несколько, выведите любое из них.
 * </pre>
 */
public class G {
    public static void main(String[] args) throws IOException {
        try (InputStream reader = new BufferedInputStream(System.in, 3_000_000);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            alg(reader, writer);
        }
    }

    public static void alg(InputStream is, BufferedWriter writer) throws IOException {
        Reader reader = new Reader(is);
        int m = reader.nextInt();
        int n = reader.nextInt();
        int[] t = new int[n];
        int[] z = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            t[i] = reader.nextInt();
            z[i] = reader.nextInt();
            y[i] = reader.nextInt();
        }

        String solution = alg1(m, n, t, z, y);

        writer.write(solution);
        writer.flush();
    }

    static class Event implements Comparable<Event> {
        final int moment;
        final int personIndex;

        Event(int moment, int personIndex) {
            this.moment = moment;
            this.personIndex = personIndex;
        }

        @Override
        public int compareTo(Event e) {
            return Long.compare(moment, e.moment);
        }
    }

    static List<Event> events(int m, int personIndex, int t, int z, int y) {
        List<Event> events = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int justInflateTime = (i + 1) * t;
            int batches = i / z;
            int totalRestAfterBatches = batches * y;
            int totalInflateTime = justInflateTime + totalRestAfterBatches;
            events.add(new Event(totalInflateTime, personIndex));
        }
        return events;
    }

    static List<Event> events(int m, int n, int[] t, int[] z, int[] y) {
        List<Event> events = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            events.addAll(events(m, i, t[i], z[i], y[i]));
            events.sort(Event::compareTo);
            events.subList(m, events.size()).clear();
        }
        return events;
    }

    public static String alg1(int m, int n, int[] t, int[] z, int[] y) {
        List<Event> events = events(m, n, t, z, y);

        int totalTime = events.isEmpty() ? 0 : events.get(events.size() - 1).moment;

        int[] counts = new int[n];
        for (Event e : events) {
            counts[e.personIndex]++;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(totalTime).append('\n');
        for (int i = 0; i < n; i++) {
            sb.append(counts[i]).append(' ');
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

package contest27883;

import java.io.*;
import java.util.*;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/27883">Алг 1.0 ДЗ 7 (27883) Сортировка событий</a>
 * <a href="https://contest.yandex.ru/contest/27883/run-report/105815830">OK  156ms  12.61Mb</a>
 *
 * E. Кассы
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * На одном из московских вокзалов билеты продают N касс.
 * Каждая касса работает без перерыва определенный промежуток времени по фиксированному расписанию
 * (одному и тому же каждый день).
 * Требуется определить, на протяжении какого времени в течение суток работают все кассы одновременно.
 *
 * Формат ввода
 * Сначала вводится одно целое число N (0 < N ≤ 1000).
 * В каждой из следующих N строк через пробел расположены 4 целых числа, первые два из которых обозначают время открытия
 * кассы в часах и минутах (часы — целое число от 0 до 23, минуты — целое число от 0 до 59),
 * оставшиеся два — время закрытия в том же формате.
 * Числа разделены пробелами.
 *
 * Время открытия означает, что в соответствующую ему минуту касса уже работает,
 * а время закрытия — что в соответствующую минуту касса уже не работает.
 * Например, касса, открытая с 10 ч. 30 мин. до 18 ч. 30 мин., ежесуточно работает 480 минут.
 *
 * Если время открытия совпадает с временем закрытия, то касса работает круглосуточно.
 * Если первое время больше второго, то касса начинает работу до полуночи, а заканчивает — на следующий день.
 *
 * Формат вывода
 * Требуется вывести одно число — суммарное время за сутки (в минутах), на протяжении которого работают все N касс.
 *
 * Примечания
 * 1) Первая касса работает с часу до 23 часов, вторая – круглосуточно,
 * третья – с 22 часов до 2 часов ночи следующего дня.
 * Таким образом, все три кассы одновременно работают с 22 до 23 часов и с часу до двух часов, то есть 120 минут.
 * 2) Первая касса работает до 14 часов, а вторая начинает работать в 14 часов 15 минут,
 * то есть одновременно кассы не работают.
 * 3) Вместе кассы работают лишь одну минуту – с 14:00 до 14:01 (в 14:01 вторая касса уже не работает).
 * </pre>
 */
public class E {
    public static void main(String[] args) throws IOException {
        try (InputStream reader = new BufferedInputStream(System.in, 3_000_000);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            alg(reader, writer);
        }
    }

    public static void alg(InputStream is, BufferedWriter writer) throws IOException {
        Reader reader = new Reader(is);
        int n = reader.nextInt();
        int[] open = new int[n];
        int[] close = new int[n];
        for (int i = 0; i < n; i++) {
            open[i] = reader.nextInt() * 60 + reader.nextInt();
            close[i] = reader.nextInt() * 60 + reader.nextInt();
        }

        String solution = alg1(n, open, close);

        writer.write(solution);
        writer.flush();
    }

    static class Event implements Comparable<Event> {
        enum Type {CLOSE, OPEN}

        final Type type;
        final int moment;
        final int terminalIndex;

        Event(Type type, int moment, int terminalIndex) {
            this.type = type;
            this.moment = moment;
            this.terminalIndex = terminalIndex;
        }

        @Override
        public int compareTo(Event e) {
            int c = Integer.compare(moment, e.moment);
            if (c == 0)
                c = Integer.compare(type.ordinal(), e.type.ordinal());
            return c;
        }
    }

    public static String alg1(int n, int[] open, int[] close) {
        List<Event> events = events(n, open, close);

        int totalTime = 0;
        int openTime = 0;

        Set<Integer> openTerminals = new HashSet<>();
        int openTerminalsCount = 0;

        for (Event e : events) {
            if (e.type == Event.Type.OPEN) {
                openTerminals.add(e.terminalIndex);
            } else {
                openTerminals.remove(e.terminalIndex);
            }

            if (openTerminals.size() == n) {
                openTime = e.moment;
            } else if (openTerminalsCount == n && openTerminals.size() == n - 1) {
                totalTime += e.moment - openTime;
            }

            openTerminalsCount = openTerminals.size();
        }

        return "" + totalTime;
    }

    static List<Event> events(int n, int[] open, int[] close) {
        List<Event> events = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (open[i] == close[i]) {
                events.add(new Event(Event.Type.OPEN, 0, i));
                events.add(new Event(Event.Type.CLOSE, 1440, i));
            } else if (open[i] > close[i]) {
                events.add(new Event(Event.Type.OPEN, 0, i));
                events.add(new Event(Event.Type.CLOSE, close[i], i));
                events.add(new Event(Event.Type.OPEN, open[i], i));
                events.add(new Event(Event.Type.CLOSE, 1440, i));
            } else {
                events.add(new Event(Event.Type.OPEN, open[i], i));
                events.add(new Event(Event.Type.CLOSE, close[i], i));
            }
        }
        Collections.sort(events);
        return events;
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

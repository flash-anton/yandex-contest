package contest27883;

import java.io.*;
import java.util.*;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/27883">Алг 1.0 ДЗ 7 (27883) Сортировка событий</a>
 * <a href="https://contest.yandex.ru/contest/27883/run-report/105648577">OK  224ms  15.75Mb</a>
 *
 * A. Наблюдение за студентами
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * На первом курсе одной Школы, учится 1 ≤ N ≤ 10^9 студентов.
 * При проведении экзаменов студентов рассаживают в ряд, каждого за своей партой.
 * Парты пронумерованы числами от 0 до N - 1.
 *
 * Известно, что студент, оставшись без наблюдения, открывает телефон и начинает искать ответы на экзамен в поисковике Яндекса.
 *
 * Поэтому было решено позвать M преподавателей наблюдать за студентами.
 * Когда за студентом наблюдает хотя бы один преподаватель, он стесняется и не идет искать ответы к экзамену.
 * Преподаватель с номером i видит студентов сидящих за партами от bi до ei включительно.
 *
 * Необходимо посчитать количество студентов, которые все таки будут искать ответы к экзамену в Яндексе
 *
 * Формат ввода *
 * В первой строке находятся два целых числа 1 ≤ N ≤ 10^9, 1 ≤ M ≤ 10^4 — число студентов и число преподавателей соответственно.
 * В следующих M строках содержится по два целых числа 0 ≤ bi ≤ ei ≤ N - 1 — парты, за которыми наблюдает i-й преподаватель.
 *
 * Формат вывода
 * Выведите одно число — количество студентов оставшихся без наблюдения.
 * </pre>
 */
public class A {
    public static void main(String[] args) throws IOException {
        try (InputStream reader = new BufferedInputStream(System.in, 3_000_000);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            alg(reader, writer);
        }
    }

    static class Event implements Comparable<Event> {
        enum Type {B, E}

        final Type type;
        final int moment;

        Event(Type type, int moment) {
            this.type = type;
            this.moment = moment;
        }

        @Override
        public int compareTo(Event e) {
            int c = Integer.compare(moment, e.moment);
            if (c == 0 && type != e.type)
                c = type == Event.Type.B ? -1 : 1;
            return c;
        }
    }

    public static void alg(InputStream is, BufferedWriter writer) throws IOException {
        Reader reader = new Reader(is);
        int n = reader.nextInt();
        int m = reader.nextInt();
        List<Event> events = new ArrayList<>(m * 2);
        for (int i = 0; i < m; i++) {
            events.add(new Event(Event.Type.B, reader.nextInt()));
            events.add(new Event(Event.Type.E, reader.nextInt() + 1)); // первый без наблюдения
        }
        Collections.sort(events);

        int supervisorsCount = 0;
        int invisibleBegin = 0;
        int invisibleCount = 0;
        for (Event event : events) {
            int oldSupervisorsCount = supervisorsCount;
            supervisorsCount += event.type == Event.Type.B ? 1 : -1;
            if (supervisorsCount == 0)
                invisibleBegin = event.moment;
            else if (oldSupervisorsCount == 0)
                invisibleCount += event.moment - invisibleBegin;
        }
        invisibleCount += n - invisibleBegin;

        String solution = "" + invisibleCount;

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

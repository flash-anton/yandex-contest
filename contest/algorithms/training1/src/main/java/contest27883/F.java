package contest27883;

import java.io.*;
import java.util.*;

import static java.lang.Math.min;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/27883">Алг 1.0 ДЗ 7 (27883) Сортировка событий</a>
 * <a href="https://contest.yandex.ru/contest/27883/run-report/105840643">OK  0.9s  38.64Mb</a>
 *
 * F. Современники
 * Ограничение времени 	3 секунды
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Группа людей называется современниками, если был такой момент, когда они могли собраться все вместе и обсуждать
 * какой-нибудь важный вопрос. Для этого в тот момент, когда они собрались, каждому из них должно было уже исполниться
 * 18 лет, но еще не исполниться 80 лет.
 *
 * Вам дан список великих людей с датами их жизни.
 * Выведите всевозможные максимальные множества современников.
 * Множество современников будем называть максимальным, если нет другого множества современников, которое включает
 * в себя всех людей из первого множества.
 *
 * Будем считать, что в день своего 18-летия человек уже может принимать участие в такого рода собраниях, а в день
 * 80-летия, равно как и в день своей смерти, — нет.
 *
 * Формат ввода
 * Сначала на вход программы поступает число N — количество людей (1 ≤ N ≤ 10000).
 * Далее в N строках вводится по шесть чисел — первые три задают дату (день, месяц, год) рождения,
 * следующие три — дату смерти (она всегда не ранее даты рождения).
 * День (в зависимости от месяца, а в феврале — еще и года) от 1 до 28, 29, 30 или 31,
 * месяц — от 1 до 12, год — от 1 до 2005.
 *
 * Формат вывода
 * Программа должна вывести все максимальные множества современников.
 * Каждое множество должно быть записано на отдельной строке и содержать номера людей
 * (люди во входных данных нумеруются в порядке их задания, начиная с 1).
 * Номера людей должны разделяться пробелами.
 * Никакое множество не должно быть указано дважды.
 * Если нет ни одного непустого максимального множества, выведите одно число 0.
 * Гарантируется, что входные данные будут таковы, что размер выходных данных для правильного ответа не превысит 2 Мб.
 * </pre>
 */
public class F {
    public static void main(String[] args) throws IOException {
        try (InputStream reader = new BufferedInputStream(System.in, 3_000_000);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            alg(reader, writer);
        }
    }

    public static void alg(InputStream is, BufferedWriter writer) throws IOException {
        Reader reader = new Reader(is);
        int n = reader.nextInt();
        long[] deathOr18 = new long[n];
        long[] deathOr80 = new long[n];
        for (int i = 0; i < n; i++) {
            int[] date = reader.nextInts(new int[3]);
            Calendar c = new GregorianCalendar(date[2], date[1] - 1, date[0]);

            c.add(Calendar.YEAR, 18);
            long eighteen = c.getTimeInMillis();

            c.add(Calendar.YEAR, 62);
            long eighty = c.getTimeInMillis();

            date = reader.nextInts(new int[3]);
            c = new GregorianCalendar(date[2], date[1] - 1, date[0]);
            long death = c.getTimeInMillis();

            deathOr18[i] = min(death, eighteen);
            deathOr80[i] = min(death, eighty);
        }

        String solution = alg1(n, deathOr18, deathOr80);

        writer.write(solution);
        writer.flush();
    }

    static class Event implements Comparable<Event> {
        enum Type {DEATH_OR_EIGHTY, EIGHTEEN}

        final Event.Type type;
        final long moment;
        final int personIndex;

        Event(Event.Type type, long moment, int personIndex) {
            this.type = type;
            this.moment = moment;
            this.personIndex = personIndex;
        }

        @Override
        public int compareTo(Event e) {
            int c = Long.compare(moment, e.moment);
            if (c == 0)
                c = Integer.compare(type.ordinal(), e.type.ordinal());
            return c;
        }
    }

    static List<Event> events(int n, long[] deathOr18, long[] deathOr80) {
        List<Event> events = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (deathOr18[i] != deathOr80[i]) {
                events.add(new Event(Event.Type.EIGHTEEN, deathOr18[i], i + 1));
                events.add(new Event(Event.Type.DEATH_OR_EIGHTY, deathOr80[i], i + 1));
            }
        }
        Collections.sort(events);
        return events;
    }

    public static String alg1(int n, long[] deathOr18, long[] deathOr80) {
        StringBuilder sb = new StringBuilder();

        List<Event> events = events(n, deathOr18, deathOr80);

        Set<Integer> persons = new HashSet<>(n);
        Event lastEvent = new Event(Event.Type.EIGHTEEN, 0, 0); // dummy

        for (Event e : events) {
            if (e.type == Event.Type.EIGHTEEN) {
                persons.add(e.personIndex);
            } else {
                if (lastEvent.type == Event.Type.EIGHTEEN) {
                    for (int person : persons)
                        sb.append(person).append(' ');
                    sb.append('\n');
                }
                persons.remove(e.personIndex);
            }
            lastEvent = e;
        }

        return sb.isEmpty() ? "0" : sb.toString();
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

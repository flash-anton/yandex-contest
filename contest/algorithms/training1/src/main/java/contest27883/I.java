package contest27883;

import java.io.*;
import java.util.*;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/27883">Алг 1.0 ДЗ 7 (27883) Сортировка событий</a>
 * <a href="https://contest.yandex.ru/contest/27883/run-report/105918682">OK  1.234s  41.74Mb</a>
 *
 * I. Автобусы
 * Ограничение времени 	2 секунды
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Новый Президент Тридевятой республики начал свою деятельность с полной ревизии системы общественного транспорта страны.
 * В результате на основе социологических опросов населения было составлено идеальное ежедневное расписание движения
 * междугородних автобусов, утвержденное Парламентом республики.
 *
 * Более того, было решено заменить весь автобусный парк одинаковыми новыми, очень дорогими, но гораздо более надежными,
 * красивыми и удобными машинами.
 *
 * Автобусная сеть страны охватывает N городов, занумерованных целыми числами от 1 до N.
 *
 * Идеальное расписание содержит M ежедневных рейсов, i-й рейс начинается в городе Fi в момент времени Xi
 * и заканчивается в некотором другом городе Gi в момент времени Yi.
 * Продолжительность каждого рейса ненулевая и строго меньше 24 часов.
 * Рейс i выполняется одним из автобусов, находящихся в момент времени Xi в городе Fi.
 *
 * Новые автобусы не требуют ремонта и могут работать круглосуточно, поэтому автобус, прибывший в некоторый момент
 * времени в некоторый город, всегда готов в тот же самый момент времени или позже отправиться в путь для обслуживания
 * любого другого рейса из данного города. Автобус может выехать из города, только выполняя какой-либо рейс из расписания.
 *
 * Предполагается, что расписание будет действовать неограниченное время, поэтому может оказаться так, что его
 * невозможно обслужить никаким конечным числом автобусов.
 *
 * Определите наименьшее количество новых автобусов, достаточное для обеспечения движения по расписанию в течение
 * неограниченного периода времени.
 *
 * Формат ввода
 * В первой строке задаются целые числа N и М (1 ≤ N, M ≤ 100 000) — количество городов и рейсов автобусов соответственно.
 * В каждой из следующих M строк содержится описание рейса автобуса: номер города отправления Fi, время отправления Xi,
 * номер города назначения Gi (Fi ≠ Gi), время прибытия Yi, отделенные друг от друга одним пробелом.
 * Время прибытия и отправления задается в формате HH:MM, где HH — часы от 00 до 23, MM — минуты от 00 до 59.
 *
 * Формат вывода
 * Выведите одно число — минимально необходимое количество автобусов.
 * Если расписание невозможно обслуживать в течение неограниченного периода времени конечным числом автобусов,
 * выведите число -1.
 * </pre>
 */
public class I {
    public static void main(String[] args) throws IOException {
        try (InputStream reader = new BufferedInputStream(System.in, 3_000_000);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            alg(reader, writer);
        }
    }

    public static void alg(InputStream is, BufferedWriter writer) throws IOException {
        Reader reader = new Reader(is);
        int n = reader.nextInt();
        int m = reader.nextInt();
        int[] departureCity = new int[m];
        int[] departureTime = new int[m];
        int[] arrivalCity = new int[m];
        int[] arrivalTime = new int[m];
        for (int i = 0; i < m; i++) {
            departureCity[i] = reader.nextInt() - 1;
            int[] hm = Arrays.stream(reader.nextWord().split(":")).mapToInt(Integer::valueOf).toArray();
            departureTime[i] = hm[0] * 60 + hm[1];
            arrivalCity[i] = reader.nextInt() - 1;
            hm = Arrays.stream(reader.nextWord().split(":")).mapToInt(Integer::valueOf).toArray();
            arrivalTime[i] = hm[0] * 60 + hm[1];
        }

        String solution = alg1(n, m, departureCity, departureTime, arrivalCity, arrivalTime);

        writer.write(solution);
        writer.flush();
    }

    static class Event implements Comparable<Event> {
        enum Type {ARRIVAL, DEPARTURE}

        final Type type;
        final int city;
        final int time;

        Event(Type type, int city, int time) {
            this.type = type;
            this.city = city;
            this.time = time;
        }

        @Override
        public int compareTo(Event e) {
            int c = Integer.compare(time, e.time);
            if (c == 0)
                c = Integer.compare(type.ordinal(), e.type.ordinal());
            return c;
        }
    }

    static boolean isScheduleCorrect(int n, int m, int[] departureCity, int[] arrivalCity) {
        Map<Integer, Integer> busCountInCity = new HashMap<>(n);
        for (int i = 0; i < m; i++) {
            busCountInCity.compute(departureCity[i], (k, v) -> (v == null) ? Integer.valueOf(-1) : ((v == 1) ? null : (v - 1)));
            busCountInCity.compute(arrivalCity[i], (k, v) -> (v == null) ? Integer.valueOf(1) : ((v == -1) ? null : (v + 1)));
        }
        return busCountInCity.values().stream().allMatch(i -> i == 0);
    }

    static List<Event> events(int m, int[] departureCity, int[] departureTime, int[] arrivalCity, int[] arrivalTime) {
        List<Event> events = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            events.add(new Event(Event.Type.DEPARTURE, departureCity[i], departureTime[i]));
            events.add(new Event(Event.Type.ARRIVAL, arrivalCity[i], arrivalTime[i]));
        }
        events.sort(Event::compareTo);
        return events;
    }

    static int busCountInCitiesAtMidnight(int n, List<Event> events) {
        Map<Integer, Integer> busCountInCity = new HashMap<>(n);
        for (Event e : events) {
            if (e.type == Event.Type.ARRIVAL)
                busCountInCity.compute(e.city, (k, v) -> (v == null) ? 1 : (v + 1));
            else
                busCountInCity.compute(e.city, (k, v) -> (v == null || v == 1) ? null : (v - 1));
        }
        return busCountInCity.values().stream().mapToInt(Integer::valueOf).sum();
    }

    static int busCountInRacesAtMidnight(int m, int[] departureTime, int[] arrivalTime) {
        int midnightRaceBusCount = 0;
        for (int i = 0; i < m; i++) {
            if (departureTime[i] > arrivalTime[i])
                midnightRaceBusCount++;
        }
        return midnightRaceBusCount;
    }

    public static String alg1(int n, int m,
                              int[] departureCity, int[] departureTime, int[] arrivalCity, int[] arrivalTime) {
        int requiredBusCount = -1;
        if (isScheduleCorrect(n, m, departureCity, arrivalCity)) {
            List<Event> events = events(m, departureCity, departureTime, arrivalCity, arrivalTime);
            int busCountInCitiesAtMidnight = busCountInCitiesAtMidnight(n, events);
            int busCountInRacesAtMidnight = busCountInRacesAtMidnight(m, departureTime, arrivalTime);
            requiredBusCount = busCountInCitiesAtMidnight + busCountInRacesAtMidnight;
        }
        return "" + requiredBusCount;
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

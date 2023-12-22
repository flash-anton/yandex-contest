package contest51022;

import java.io.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/51022">Стаж бэк сен-дек 2023 (51022)</a>
 * <a href="https://contest.yandex.ru/contest/51022/run-report/102670367">OK  151ms  13.90Mb</a>
 *
 * A. Новая история
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	256Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 * Сейчас активно развивается новая история, основателем которой является Профессор А.С. Багиров.
 * Он выяснил, что на протяжении многих лет на земле вместе с людьми существовали ящеры.
 * Строительство пирамид, захват Байкала и еще много разных событий произошли благодаря ящерам.
 *
 * Учёные ещё не выяснили, сколько времени ящеры существовали на земле.
 * Они находят разные данные в виде даты начала и даты окончания, и чтобы проверить их на корректность,
 * необходимо посчитать, сколько дней ящеры существовали для двух конкретных дат.
 * Календарь ящеров очень похож на григорианский, лишь с тем исключением, что там нет високосных годов.
 *
 * Вам даны дата начала и дата окончания существования ящеров, нужно найти количество полных дней и секунд в неполном дне,
 * чтобы учёные смогли оценить, насколько даты корректны.
 *
 * Формат ввода
 * В первой строке содержатся 6 целых чисел
 * year1,month1,day1,hour1,min1,sec1(1≤year1≤9999,1≤month1≤12,1≤day1≤31,0≤hour1≤23,0≤min1≤59,0≤sec1≤59)—
 * дата начала существования ящеров.
 * Во второй строке содержатся 6 целых чисел
 * year2,month2,day2,hour2,min2,sec2(1≤year2≤9999,1≤month2≤12,1≤day2≤31,0≤hour2≤23,0≤min2≤59,0≤sec2≤59)—
 * дата окончания существования ящеров.
 * Гарантируется, что дата начала меньше,чем дата конца.
 *
 * Формат вывода
 * В первой и единственной строке выведите 2 числа:
 * количество дней, сколько существовали ящеры, а также количество секунд в неполном дне. .
 *
 * Примечания
 * Напоминаем:
 *
 * В календаре древних ящеров:
 *
 *     Нет високосных годов.
 *     В одном году 365 дней.
 *     Год делится на 12 месяцев, количество дней в каждом месяце: [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31].
 *     В одном дне 24 часа (от 0 до 23).
 *     В одном часу 60 минут (от 0 до 59).
 *     В одной минуте 60 секунд (от 0 до 59).
 *
 * Первый тестовый пример.
 *
 *     Года начала и конца совпадают;
 *     Между 12 февраля и 1 марта прошло 17 полных дней;
 *     Начало было в 10:30:01, а конец в 10:31:37 — таким образом дополнительно прошла 1 минута и 36 секунд, то есть 96 секунд.
 *
 * Второй тестовый пример.
 *
 *     Прошло 8008 полных лет;
 *     В каждом году 365 дней, суммарно получается 2922920 дней.
 *     От 20 дня 5 месяца до 20 дня 8 месяца прошли еще 31 + 30 + 31 день - суммарно 92 дня.
 *     От 20 дня 8 месяца до 10 дня 9 месяца прошло еще (31 - 20) + 10 = 21 полный день.
 *     Всего полных дней 2922920 + 92 + 21 = 2923033.
 *     От 10 дня 9 месяца 14:15:16 до 11 дня 9 месяца 12:21:11 прошло 79555 секунд.
 * </pre>
 */
public class A {
    public static void main(String[] args) throws IOException {
        try (InputStream reader = new BufferedInputStream(System.in, 3_000_000);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            alg(reader, writer);
        }
    }

    public static void alg(InputStream is, BufferedWriter writer) throws IOException {
        Scanner reader = new Scanner(is);
        int[] from = reader.nextInts(new int[6]);
        int[] to = reader.nextInts(new int[6]);

        String solution = alg1(from, to);

        writer.write(solution);
        writer.flush();
    }

    public static String alg1(int[] from, int[] to) {
        long start = LocalDateTime.of(2022, from[1], from[2], from[3], from[4]).withSecond(from[5]).toEpochSecond(ZoneOffset.UTC);
        long end = LocalDateTime.of(2023, to[1], to[2], to[3], to[4]).withSecond(to[5]).toEpochSecond(ZoneOffset.UTC);
        long period = end - start;

        long secondsInDay = 60 * 60 * 24;
        long seconds = period % secondsInDay;
        long days = period / secondsInDay + (to[0] - from[0] - 1) * 365L;

        return days + " " + seconds;
    }

    /**
     * "Быстрый" ридер потока.
     */
    public static class Scanner {
        private final InputStream is;
        private int lastReadByte = '\n';

        public Scanner(InputStream is) {
            this.is = is;
        }

        public int nextInt() throws IOException {
            while (lastReadByte == ' ' || lastReadByte == '\n') {
                lastReadByte = is.read();
            }

            int sign = 1;
            if (lastReadByte == '-') {
                sign = -1;
                lastReadByte = is.read();
            }

            int num = 0;
            while (lastReadByte >= '0' && lastReadByte <= '9') {
                num = (num * 10) + sign * (lastReadByte - '0');
                lastReadByte = is.read();
            }
            return num;
        }

        public int[] nextInts(int[] a) throws IOException {
            for (int i = 0; i < a.length; i++) {
                a[i] = nextInt();
            }
            return a;
        }
    }
}

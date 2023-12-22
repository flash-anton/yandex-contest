package contest53033;

import java.io.*;
import java.util.Arrays;
import java.util.function.Predicate;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/53033">Алг 4.0 Финал (53033)</a>
 * <a href="https://contest.yandex.ru/contest/53033/run-report/102067286">OK  316ms  18.45Mb</a>
 *
 * C. Переезд
 * Ограничение времени 	2 секунды
 * Ограничение памяти 	512Mb
 * Ввод 	стандартный ввод или transfer.in
 * Вывод 	стандартный вывод или transfer.out
 *
 * Васина семья переезжает в новую квартиру, а переезд — это всегда хлопоты.
 * Например, Васе совершенно не хочется расставаться со своей коллекцией кружек, которую он собрал, посещая олимпиады.
 * Все-таки, его коллекция самая большая в мире и насчитывает 10^7 экземпляров!
 * Поскольку каждая кружка весит 100 грамм, для ее перевозки Вася хочет нанять грузовик.
 * Однако, на всех дорогах висят знаки, ограничивающие вес транспорта.
 * Кроме того, ровно через 24 часа выходит новый эпизод любимого Васиного сериала, пропускать который он отказывается наотрез!
 * От всей этой неразберихи у Васи голова идет кругом, и он обращается к вам за помощью.
 *
 * Вася хочет перевезти как можно больше кружек за первый же рейс, но если фуру, которая и так весит 3 тонны,
 * полностью нагрузить кружками, то, возможно, придется ехать в объезд.
 * Так сколько же кружек можно довезти до новой квартиры, не нарушая правил дорожного движения и не пропустив начало передачи?
 *
 * Формат ввода
 * В первой строке входного файла указаны два числа N и M — число перекрестков на схеме города и число дорог соответственно (1 ≤ N ≤ 500).
 * В следующих M строках идет описание дорог. Каждая дорога описывается четырьмя числами: ai, bi, ti и wi.
 * ai и bi — это номера перекрестков, которые соединяет дорога, ai ≠ bi, 1 ≤ ai, bi ≤ N. Вася знает, что если есть дорога, соединяющая напрямую два перекрестка, то она ровно одна.
 * ti — это время в минутах, которое тратится на проезд по этой дороге, 0 ≤ ti ≤ 1440.
 * wi — это максимальная масса в граммах, которую можно провозить по этой дороге, 0 ≤ wi ≤ 10^9.
 * Старая квартира Васи находится на этой схеме на перекрестке с номером 1, а новая — на перекрестке с номером N.
 *
 * Формат вывода
 * Выведите ровно одно число — наибольшее количество кружек, которое Вася может увезти за один рейс, не нарушая правил дорожного движения и не опоздав к началу сериала.
 *
 * Примечания
 * Перекрестки могут соединять любое количество дорог, по перекрестку можно проехать в любом направлении
 * (то есть с любой дороги на любую другую), все дороги допускают движение в обе стороны.
 * </pre>
 */
public class C {
    public static void main(String[] args) throws IOException {
        InputStream reader = new BufferedInputStream(System.in, 3_000_000);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        alg(reader, writer);
        reader.close();
        writer.close();
    }

    public static void alg(InputStream reader, BufferedWriter writer) throws IOException {
        Scanner scanner = new Scanner(reader);
        int V = scanner.nextInt(); // N
        int E = scanner.nextInt(); // M

        Road[][] G = new Road[V][V];
        int maxCups = -1;
        int minCups = Integer.MAX_VALUE;

        int[] a = new int[4];
        for (int i = 0; i < E; i++) {
            scanner.nextInts(a);
            int cups = max(0, a[3] - 3_000_000) / 100;
            if (cups > 0) {
                Road r = new Road(a[2], cups);
                G[a[0] - 1][a[1] - 1] = r;
                G[a[1] - 1][a[0] - 1] = r;
            }
            maxCups = max(maxCups, cups);
            minCups = min(minCups, cups);
        }

        String solution = alg1(V, G, minCups, maxCups);

        writer.write(solution);
        writer.flush();
    }

    public static String alg1(int V, Road[][] G, int minCups, int maxCups) {
        if (V == 1) {
            return "10000000";
        } else {
            int maxTime = 1440;
            Predicate<Integer> moveToRight = curCups -> dijkstra(V, G, maxTime, curCups) <= maxTime;
            int possibleCups = binSearchFromLeft(minCups, maxCups + 1, moveToRight);
            return (possibleCups < minCups ? "0" : String.valueOf(possibleCups));
        }
    }

    static int dijkstra(int V, Road[][] G, int maxTime, int minCups) {
        boolean[] visited = new boolean[V];

        int[] minTimeTo = new int[V];
        Arrays.fill(minTimeTo, Integer.MAX_VALUE);
        minTimeTo[0] = 0; // одна начальная точка

        // O(V*V)
        for (int v = 0; v < V; v++) {
            // выбор минимального непосещенного - O(V)
            int A = -1;
            for (int a = 0; a < V; a++) {
                if (!visited[a] && (A == -1 || minTimeTo[a] < minTimeTo[A])) {
                    A = a;
                }
            }
            if (minTimeTo[A] > maxTime) {
                break;
            }

            // отметка о посещении
            visited[A] = true;

            // обход соседей - O(V)
            for (int B = 0; B < G[A].length; B++) {
                Road AB = G[A][B];
                if (AB == null) {
                    continue;
                }

                // обновление минимального значения у непосещенного соседа - O(1)
                int newTimeToB = minTimeTo[A] + AB.time;
                if (newTimeToB < minTimeTo[B] && AB.cups >= minCups) {
                    minTimeTo[B] = newTimeToB;
                }
            }
        }

        return minTimeTo[V - 1];
    }

    public static class Road {
        final int time;
        final int cups;

        Road(int time, int cups) {
            this.time = time;
            this.cups = cups;
        }
    }

    /**
     * Бинарный поиск слева направо. O(logN)<br>
     * [----] -1 => [++--] 1 => [++++] 3
     */
    public static int binSearchFromLeft(int LInclusive, int RExclusive, Predicate<Integer> moveToRight) {
        while (LInclusive < RExclusive) {
            int M = (LInclusive + RExclusive) / 2;
            if (moveToRight.test(M)) {
                LInclusive = M + 1;
            } else {
                RExclusive = M;
            }
        }
        return LInclusive - 1;
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

        private long nextLong() throws IOException {
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

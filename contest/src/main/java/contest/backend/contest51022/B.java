package contest.backend.contest51022;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import static java.lang.Math.abs;
import static java.util.stream.Collectors.toMap;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/51022">Стаж бэк сен-дек 2023 (51022)</a>
 * <a href="https://contest.yandex.ru/contest/51022/run-report/102677618">OK  0.872s  57.14Mb</a>
 *
 * B. Разнообразие
 * Ограничение времени 	2 секунды
 * Ограничение памяти 	256Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Два друга A и B постоянно играют в коллекционную карточную игру (ККИ),
 * поэтому у каждого игрока скопилась довольно большая коллекция карт.
 *
 * Каждая карта в данной игре задаётся целым числом (одинаковые карты — одинаковыми числами, разные карты — разными).
 *
 * Таким образом коллекцию можно представить как неупорядоченный набор целых чисел (с возможными повторениями).
 *
 * После каждого изменения коллекций друзья вычисляют показатель разнообразия следующим образом:
 *
 *     A и B выкладывают на стол все карты из своей коллекции в два раздельных ряда;
 *     Далее друзья итеративно делают следующее:
 *         Если среди лежащих на столе карт игрока A есть такая же карта, как и среди лежащих карт игрока B — каждый игрок убирает данную карту со стола;
 *         Если таковых совпадений нет — процесс заканчивается.
 *     Разнообразием коллекций друзья называют суммарное количество оставшихся карт на столе.
 *
 * Обратите внимание: друзья убирают карты только со стола, карты не удаляются из коллекций при вычислении разнообразия.
 *
 * Даны начальные состояния коллекций игроков, а также Q изменений их коллекций. После каждого изменения необходимо вычислить разнообразие коллекций друзей.
 *
 * Формат ввода
 * В первой строке через пробел заданы числа N, M, Q(1≤N,M,Q≤10^5) — количество карт в коллекциях игрока A и B и количество изменений соответственно.
 *
 * Вторая строка содержит через пробел N целых чисел ai(1≤ai≤10^9) — карты в коллекции игрока A.
 *
 * Третья строка содержит через пробел M целых чисел bj(1≤bj≤10^9) — карты в коллекции игрока B.
 *
 * Далее на каждой из следующих Q строк описано изменение коллекции:
 * через пробел заданы typek playerk cardk(typek=±1;playerk∈(A,B);1≤cardk≤109) — тип k-го изменения, имя игрока и значение карты:
 *     Если type=1, то в коллекцию игрока player добавился экземпляр карты card;
 *     Если type=−1, то из коллекции игрока player удалился один экземпляр карты card.
 *     Гарантируется, что при запросе type=−1 хотя бы один экземпляр карты card присутствует в коллекции игрока player.
 *
 * Формат вывода
 * Необходимо вывести через пробел Q целых чисел — разнообразие коллекций игроков A и B после k-го изменения. .
 *
 * Примечания
 * Первый тестовый пример:
 *
 * Изначально у игроков коллекции A=[1,2], B=[1,2,3,4,5].
 *
 * Рассмотрим изменения коллекций и соответствующие им разнообразия:
 *
 *     A=[1,2,3], B=[1,2,3,4,5] — со стола убирают карты [1,2,3], на столе остались [4,5] игрока B.
 *     A=[1,2,3,4], B=[1,2,3,4,5] — со стола убирают карты [1,2,3,4], осталась только [5] игрока B.
 *     A=[1,2,3,4,5], B=[1,2,3,4,5] — со стола убирают карты [1,2,3,4,5], на столе не осталось карт.
 *     A=[1,2,3,4,5,6], B=[1,2,3,4,5] — со стола убирают карты [1,2,3,4,5], осталась только [6] игрока A.
 *     A=[1,2,3,4,5,6,7], B=[1,2,3,4,5] — со стола убирают карты [1,2,3,4,5], остались только [6,7] игрока A.
 *     A=[2,3,4,5,6,7], B=[1,2,3,4,5] — со стола убирают карты [2,3,4,5], остались только [6,7] игрока A и [1] игрока B.
 *     A=[2,3,4,5,6,7], B=[1,2,3,4,5,7] — со стола убирают карты [2,3,4,5,7], остались только [6] игрока A и [1] игрока B.
 *     A=[2,3,4,5,7], B=[1,2,3,4,5,7] — со стола убирают карты [2,3,4,5,7], осталась только [1] игрока B.
 *     A=[2,3,4,5,7], B=[2,3,4,5,7] — со стола убирают карты [2,3,4,5,7], на столе не осталось карт.
 *     A=[2,3,4,5,7,7], B=[2,3,4,5,7] — со стола убирают карты [2,3,4,5,7], на столе осталась только [7] игрока A.
 *
 * Второй тестовый пример
 *
 * Изначально у игроков коллекции A=[1000,1001,2000], B=[1000,1001,2001].
 *
 * Рассмотрим изменения коллекций и соответствующие им разнообразия:
 *
 *     A=[1000,1001,2000,100000], B=[1000,1001,2001] — со стола убирают карты [1000,1001], остались только [2000,100000] игрока A и [2001] игрока B.
 *     A=[1000,1001,2000,100000], B=[1000,1001] — со стола убирают карты [1000,1001], остались только [2000,100000] игрока A.
 *     A=[1000,1001,2000,100000], B=[1000,1001,2000] — со стола убирают карты [1000,1001,2000], осталась только [2000] игрока A.
 *     A=[1000,1001,2000,100000], B=[1000,1001,2000,100001] — со стола убирают карты [1000,1001,2000], остались только [100000] игрока A и [100001] игрока B.
 *     A=[1,1000,1001,2000,100000], B=[1000,1001,2000,100001] — со стола убирают карты [1000,1001,2000], остались только [1,100000] игрока A и [100001] игрока B.
 * </pre>
 */
public class B {
    public static void main(String[] args) throws IOException {
        try (InputStream reader = new BufferedInputStream(System.in, 3_000_000);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            alg(reader, writer);
        }
    }

    public static void alg(InputStream is, BufferedWriter writer) throws IOException {
        Scanner reader = new Scanner(is);
        int N = reader.nextInt();
        int M = reader.nextInt();
        int Q = reader.nextInt();
        int[] a = reader.nextInts(new int[N]);
        int[] b = reader.nextInts(new int[M]);
        int[][] c = new int[Q][3];
        for (int i = 0; i < Q; i++) {
            c[i][0] = reader.nextInt();
            c[i][1] = reader.nextWord().charAt(0);
            c[i][2] = reader.nextInt();
        }

        String solution = alg1(N, M, Q, a, b, c);

        writer.write(solution);
        writer.flush();
    }

    public static String alg1(int N, int M, int Q, int[] a, int[] b, int[][] c) {

        int difference = N;
        Map<Integer, Integer> A = IntStream.of(a).boxed().collect(toMap(i -> i, i -> 1, Integer::sum, HashMap::new));
        Map<Integer, Integer> B = new HashMap<>();
        for (int bi : b) {
            difference += dif(1, 'B', bi, A, B);
        }

        StringBuilder sb = new StringBuilder();
        for (int[] op : c) {
            difference += dif(op[0], op[1], op[2], A, B);
            sb.append(difference).append(' ');
        }

        return sb.toString();
    }

    public static int dif(int type, int player, int card, Map<Integer, Integer> A, Map<Integer, Integer> B) {
        int difBefore = abs(A.getOrDefault(card, 0) - B.getOrDefault(card, 0));
        (player == 'A' ? A : B).compute(card, (k, v) -> (v == null) ? 1 : (v + type));
        int difAfter = abs(A.getOrDefault(card, 0) - B.getOrDefault(card, 0));
        return difAfter > difBefore ? 1 : -1;
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
    }
}

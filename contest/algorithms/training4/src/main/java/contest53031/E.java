package contest53031;

import java.io.*;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/53031">Алг 4.0 ДЗ 3 (53031) Кратчайшие пути в графах</a>
 * <a href="https://contest.yandex.ru/contest/53031/run-report/98911300">OK  3.348s  113.18Mb</a>
 *
 * E. На санях
 * Ограничение времени 	5 секунд
 * Ограничение памяти 	256Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * В начале XVIII века еще не было самолетов, поездов и автомобилей, поэтому все междугородние зимние поездки совершались на санях.
 * Как известно, с дорогами в России тогда было даже больше проблем, чем сейчас, а именно на N существовавших тогда городов имелась N-1 дорога,
 * каждая из которых соединяла два города. Из каждого города можно было добраться в любой другой (возможно, через промежуточные города).
 *
 * В каждом городе была почтовая станция («ям»), на которой можно было пересесть в другие сани.
 * При этом ямщики могли долго запрягать (для каждого города известно время, которое ямщики в этом городе тратят на подготовку саней к поездке)
 * и быстро ехать (также для каждого города известна скорость, с которой ездят ямщики из него).
 * Можно считать, что количество ямщиков в каждом городе не ограничено.
 *
 * Если бы олимпиада проводилась 300 лет назад, то путь участников занимал бы гораздо большее время, чем сейчас.
 * Допустим, из каждого города в Москву выезжает участник олимпиады и хочет добраться до Москвы за наименьшее время
 * (не обязательно по кратчайшему пути: он может заезжать в любые города, через один и тот же город можно проезжать несколько раз).
 *
 * Сначала он едет с ямщиком из своего города. Приехав в любой город, он может либо сразу ехать дальше, либо пересесть.
 * В первом случае он едет с той же скоростью, с какой ехал раньше.
 * Если сменить ямщика, он сначала ждет, пока ямщик подготовит сани, и только потом едет с ним (с той скоростью, с которой ездит этот ямщик).
 * В пути можно делать сколько угодно пересадок.
 *
 * Определите, какое время необходимо, чтобы все участники олимпиады доехали из своего города в Москву 300 лет назад.
 * Все участники выезжают из своих городов одновременно.
 *
 * Формат ввода
 * В первой строке входного файла дано натуральное число N, не превышающее 2000 — количество городов, соединенных дорогами.
 * Город с номером 1 является столицей.
 * Следующие N строк содержат по два целых числа: Ti и Vi — время подготовки саней в городе i, выраженное в часах,
 * и скорость, с которой ездят ямщики из города i, в километрах в час (0 ≤ Ti ≤ 100, 1 ≤ Vi ≤ 100).
 * Следующие N–1 строк содержат описания дорог того времени.
 * Каждое описание состоит из трех чисел Aj, Bj и Sj,
 * где Aj и Bj — номера соединенных городов, а Sj — расстояние между ними в километрах (1 ≤ Aj ≤ N, 1 ≤ Bj ≤ N, Aj ≠ Bj, 1 ≤ Sj ≤ 10000).
 * Все дороги двусторонние, то есть если из A можно проехать в B, то из B можно проехать в A.
 * Гарантируется, что из всех городов можно добраться в столицу.
 *
 * Формат вывода
 * Сначала выведите одно вещественное число — время в часах, в которое в Москву приедет последний участник.
 * Далее выведите путь участника, который приедет самым последним (если таких участников несколько, выведите путь любого из них).
 * Выведите город, из которого этот участник выехал первоначально, и перечислите в порядке посещения те города, в которых он делал пересадки.
 * Последовательность должна заканчиваться столицей.
 *
 * При проверке ответ будет засчитан, если из трех величин «время путешествия по выведенному пути», «выведенное время» и «правильный ответ» каждые две отличаются менее чем на 0.0001.
 *
 * Примечания
 * 1. Участник из города 1 уже находится на своем месте и тратит на дорогу 0 часов.
 * Участник из города 2 ждет 10 часов ямщика в своем городе, а затем проезжает 300 км от города 2 до города 1 за 10 часов, т.е. тратит на дорогу 20 часов.
 * Участник из города номер 3 ждет ямщика 5 часов, а затем доезжает до города 1 за 10 часов, т.е. тратит на дорогу 15 часов.
 * Участник из города 4 может доехать до города 1 с ямщиком из города 4 за 1 + 40 = 41 час или доехать до города номер 2 за 1 + 10 = 11 часов, прождать там 10 и доехать до столицы за 10 часов.
 * Таким образом, он может добраться до города 1 минимум за 31 час. Это и есть самое большое время и ответ к задаче.
 * 2. Участнику из города 2 выгоднее добраться сначала до третьего города, где ездят быстрее, а потом поехать в столицу, не делая пересадки в своём городе.
 * </pre>
 */
public class E {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        alg(reader, writer);
        reader.close();
        writer.close();
    }

    public static void alg(BufferedReader reader, BufferedWriter writer) throws IOException {
        int N = java.lang.Integer.parseInt(reader.readLine());

        int[][] TV = new int[N + 1][];
        for (int i = 1; i <= N; i++) {
            TV[i] = Arrays.stream(reader.readLine().split(" ")).filter(s -> !s.isEmpty()).mapToInt(java.lang.Integer::parseInt).toArray();
        }

        Map<Integer, Map<Integer, Integer>> S = new HashMap<>(); // <from, <to, weight>>
        for (int i = 0; i < N - 1; i++) {
            int[] a = Arrays.stream(reader.readLine().split(" ")).filter(s -> !s.isEmpty()).mapToInt(java.lang.Integer::parseInt).toArray();
            S.computeIfAbsent(a[0], k -> new HashMap<>()).put(a[1], a[2]);
            S.computeIfAbsent(a[1], k -> new HashMap<>()).put(a[0], a[2]);
        }

        String solution = alg2(N, TV, S);

        writer.write(solution);
        writer.flush();
    }

    public static String alg2(int N, int[][] TV, Map<Integer, Map<Integer, Integer>> S) {
        double[][] times = times(N, TV, S); // N*bfs -> O(N*N)

        Map<Integer, Double> S2 = new HashMap<>(Map.of(1, 0d));
        Map<Integer, List<Integer>> previous = new HashMap<>();
        BiFunction<Double, Double, Double> S2B = java.lang.Double::sum;
        Predicate<Integer> isEnd = dot -> false;
        int F = dijkstra(N, N * N, times, S2, previous, S2B, isEnd); // min(O(ELogV), O(Double*Double))

        double S2F = S2.getOrDefault(F, java.lang.Double.MAX_VALUE);

        List<String> track = new ArrayList<>();
        int p = F;
        while (p != -1) {
            track.add("" + p);
            List<Integer> a = previous.getOrDefault(p, new ArrayList<>());
            p = a.isEmpty() ? -1 : a.get(0);
        }

        return String.format(Locale.ENGLISH, "%.10f\n%s", S2F, String.join(" ", track));
    }

    private static double[][] times(int N, int[][] TV, Map<Integer, Map<Integer, Integer>> S) {
        int[][] paths = new int[N + 1][N + 1];
        double[][] times = new double[N + 1][N + 1];

        for (int I = 1; I <= N; I++) { // O(N*N)
            Set<Integer> visited = new HashSet<>();
            Queue<Integer> unvisited = new ArrayDeque<>(Set.of(I));

            Integer A;
            while ((A = unvisited.poll()) != null) { // O(N)
                visited.add(A);

                for (int B : S.get(A).keySet()) { // 1 or 2
                    if (!visited.contains(B)) {
                        unvisited.add(B);

                        int pathIB = paths[I][A] + S.get(A).get(B);
                        double timeIB = TV[B][0] + (double) pathIB / TV[B][1];

                        paths[I][B] = pathIB;
                        times[I][B] = timeIB;
                    }
                }
            }
        }

        return times;
    }

    // =================================================================================================================

    public static
    Integer dijkstra(int V, int E, double[][] edges,
                     Map<Integer, Double> S2, Map<Integer, List<Integer>> previous, BiFunction<Double, Double, Double> S2B,
                     Predicate<Integer> isEnd) {

        Set<Integer> visited = new HashSet<>();

        Integer lastK = S2.keySet().iterator().next();

        MappedSet<Double, Integer> kSetByCumulativeV = new MappedSet<Double, Integer>(((E * Math.log(V)) < (V * V)) ? new TreeMap<>() : new HashMap<>());
        kSetByCumulativeV.add(S2.get(lastK), lastK);

        Map.Entry<Double, Set<Integer>> firstEntry;
        while ((firstEntry = kSetByCumulativeV.firstEntry()) != null) {
            int A = firstEntry.getValue().iterator().next();
            Double S2A = S2.get(A);
            lastK = A;

            if (isEnd.test(A)) {
                break;
            } else {
                visited.add(A);
                kSetByCumulativeV.remove(firstEntry.getKey(), A);
            }

            for (int B = 1; B <= V; B++) {
                Double A2B = edges[A][B];

                if (visited.contains(B)) {
                    continue;
                }

                Double curS2B = S2.get(B);
                Double newS2B = S2B.apply(S2A, A2B);

                java.lang.Integer newCompareCur = curS2B == null ? null : newS2B.compareTo(curS2B);
                if (newCompareCur == null) {
                    S2.put(B, newS2B);
                    kSetByCumulativeV.add(newS2B, B);
                    previous.compute(B, (k, v) -> new ArrayList<>()).add(A);
                } else if (newCompareCur < 0) {
                    S2.put(B, newS2B);
                    kSetByCumulativeV.remove(curS2B, B);
                    kSetByCumulativeV.add(newS2B, B);
                    previous.compute(B, (k, v) -> new ArrayList<>()).add(A);
                } else if (newCompareCur == 0) {
                    previous.get(B).add(A);
                }
            }
        }

        return lastK;
    }

    public static class MappedSet<K extends Comparable<K>, V> {
        private final Map<K, Set<V>> m;
        private final Supplier<Map.Entry<K, Set<V>>> firstEntry;

        public MappedSet(Map<K, Set<V>> M) {
            m = M;
            firstEntry = (M instanceof SortedMap) ?
                    () -> m.isEmpty() ? null : m.entrySet().iterator().next() :
                    () -> m.entrySet().stream().min(Map.Entry.comparingByKey()).orElse(null);
        }

        // O(1) - HashMap, O(logN) - SortedMap
        public void add(K key, V value) {
            m.computeIfAbsent(key, k -> new HashSet<>()).add(value);
        }

        // O(1) - HashMap, O(logN) - SortedMap
        public void remove(K key, V value) {
            m.computeIfPresent(key, (k, v) -> {
                v.remove(value);
                return v.isEmpty() ? null : v;
            });
        }

        // O(N) - HashMap, O(logN) - SortedMap
        public Map.Entry<K, Set<V>> firstEntry() {
            return firstEntry.get();
        }
    }
}

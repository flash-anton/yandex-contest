package contest53031;

import java.io.*;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.IntStream;

import static java.util.Comparator.comparing;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/53031">Алг 4.0 ДЗ 3 (53031) Кратчайшие пути в графах</a>
 * <a href="https://contest.yandex.ru/contest/53031/run-report/98581294">OK  470ms  25.64Mb</a>
 *
 * D. Автобусы в Васюках
 * 	                    Все языки 	Python 3.6
 * Ограничение времени 	3 секунды 	5 секунд
 * Ограничение памяти 	512Mb 	256Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Между некоторыми деревнями края Васюки ходят автобусы.
 * Поскольку пассажиропотоки здесь не очень большие, то автобусы ходят всего несколько раз в день.
 *
 * Марии Ивановне требуется добраться из деревни d в деревню v как можно быстрее
 * (считается, что в момент времени 0 она находится в деревне d).
 *
 * Формат ввода
 * Сначала вводится число N – общее число деревень (1 <= N <= 100),
 * затем номера деревень d и v,
 * за ними следует количество автобусных рейсов R (0 <= R <= 10000).
 * Далее идут описания автобусных рейсов.
 * Каждый рейс задается номером деревни отправления, временем отправления, деревней назначения и временем прибытия (все времена – целые от 0 до 10000).
 * Если в момент t пассажир приезжает в какую-то деревню, то уехать из нее он может в любой момент времени, начиная с t.
 *
 * Формат вывода
 * Выведите минимальное время, когда Мария Ивановна может оказаться в деревне v.
 * Если она не сможет с помощью указанных автобусных рейсов добраться из d в v, выведите -1.
 * </pre>
 */
public class D {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        alg(reader, writer);
        reader.close();
        writer.close();
    }

    public static void alg(BufferedReader reader, BufferedWriter writer) throws IOException {
        int N = Integer.parseInt(reader.readLine());

        int[] buf = Arrays.stream(reader.readLine().split(" ")).filter(s -> !s.isEmpty()).mapToInt(Integer::parseInt).toArray();
        int d = buf[0];
        int v = buf[1];

        int R = Integer.parseInt(reader.readLine());

        Map<Integer, Map<Integer, Races>> edges = new HashMap<>(); // <from, <to, weight>>
        for (int i = 0; i < R; i++) {
            buf = Arrays.stream(reader.readLine().split(" ")).filter(s -> !s.isEmpty()).mapToInt(Integer::parseInt).toArray();
            int A = buf[0];
            int departureA = buf[1];
            int B = buf[2];
            int arrivalB = buf[3];

            Map<Integer, D.Races> A2 = edges.computeIfAbsent(A, k -> new HashMap<>());
            A2.compute(B, (k, q) -> (q == null) ? new Races(departureA, arrivalB) : q.add(departureA, arrivalB));
        }

        String solution = alg2(N, R, edges, d, v);

        writer.write(solution);
        writer.flush();
    }

    public static String alg1(int V, int E, Map<Integer, Map<Integer, Races>> edges, int S, int F) {
        boolean[] visited = new boolean[V + 1];

        Races[] S2 = new Races[V + 1];
        Arrays.fill(S2, Races.MAX_VALUE);
        S2[S] = Races.MIN_VALUE;

        Integer A;
        while (null != (A = IntStream.range(1, V + 1).boxed()
                .filter(i -> !visited[i] && S2[i] != Races.MAX_VALUE)
                .min(comparing(i -> S2[i])).orElse(null))) {

            Races S2A = S2[A];

            if (A == F) {
                break;
            } else {
                visited[A] = true;
            }

            for (Map.Entry<Integer, Races> e : edges.getOrDefault(A, new HashMap<>()).entrySet()) {
                Integer B = e.getKey();
                Races A2B = e.getValue();

                Races curS2B = S2[B];
                Races newS2B = Races.S2B(S2A, A2B);
                if (newS2B.compareTo(curS2B) < 0) {
                    S2[B] = newS2B;
                }
            }
        }

        Races S2F = S2[F];
        int fin = S2F == Races.MAX_VALUE ? -1 : S2F.arrival;
        return "" + fin;
    }

    public static String alg2(int V, int E, Map<Integer, Map<Integer, Races>> edges, int S, int F) {
        Map<Integer, Races> S2 = new HashMap<>(Map.of(S, Races.MIN_VALUE));
        Map<Integer, List<Integer>> previous = new HashMap<>();
        BiFunction<Races, Races, Races> S2B = Races::S2B;
        Predicate<Integer> isEnd = dot -> dot == F;
        dijkstra(V, E, edges, S2, previous, S2B, isEnd);

        Races S2F = S2.getOrDefault(F, Races.MAX_VALUE);
        int fin = S2F == Races.MAX_VALUE ? -1 : S2F.arrival;
        return "" + fin;
    }

    public static class Races implements Comparable<Races> {
        final Map<Integer, Integer> t = new HashMap<>();
        final int arrival;
        static final Races MIN_VALUE = new Races(0, 0);
        static final Races MAX_VALUE = new Races(0, Integer.MAX_VALUE);

        Races(int departure, int arrival) {
            t.put(departure, arrival);
            this.arrival = arrival;
        }

        Races add(int departure, int arrival) {
            t.compute(departure, (k, v) -> (v == null) ? arrival : Math.min(v, arrival));
            return this;
        }

        static Races S2B(Races S2A, Races A2B) {
            return A2B.t.keySet().stream().filter(departureA -> S2A.arrival <= departureA)
                    .map(A2B.t::get).min(Integer::compareTo)
                    .map(minArrivalB -> new Races(0, minArrivalB)).orElse(Races.MAX_VALUE);
        }

        @Override
        public int compareTo(Races o) {
            return Integer.compare(arrival, o.arrival);
        }
    }

    // =================================================================================================================

    public static <K, V extends Comparable<V>>
    void dijkstra(int V, int E, Map<K, Map<K, V>> edges,
                  Map<K, V> S2, Map<K, List<K>> previous, BiFunction<V, V, V> S2B,
                  Predicate<K> isEnd) {

        Set<K> visited = new HashSet<>();

        MappedSet<V, K> kSetByCumulativeV = new MappedSet<V, K>(((E * Math.log(V)) < (V * V)) ? new TreeMap<>() : new HashMap<>());
        Map.Entry<K, V> ed = S2.entrySet().iterator().next();
        kSetByCumulativeV.add(ed.getValue(), ed.getKey());

        Map.Entry<V, Set<K>> firstEntry;
        while ((firstEntry = kSetByCumulativeV.firstEntry()) != null) {
            K A = firstEntry.getValue().iterator().next();
            V S2A = S2.get(A);

            if (isEnd.test(A)) {
                break;
            } else {
                visited.add(A);
                kSetByCumulativeV.remove(firstEntry.getKey(), A);
            }

            for (Map.Entry<K, V> e : edges.getOrDefault(A, new HashMap<>()).entrySet()) {
                K B = e.getKey();
                V A2B = e.getValue();

                if (visited.contains(B)) {
                    continue;
                }

                V curS2B = S2.get(B);
                V newS2B = S2B.apply(S2A, A2B);

                Integer newCompareCur = curS2B == null ? null : newS2B.compareTo(curS2B);
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

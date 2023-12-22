package contest53031;

import java.io.*;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.IntStream;

import static java.util.Comparator.comparingLong;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/53031">Алг 4.0 ДЗ 3 (53031) Кратчайшие пути в графах</a>
 * <a href="https://contest.yandex.ru/contest/53031/run-report/98346715">OK  3.004s  131.46Mb</a>
 *
 * C. Быстрый алгоритм Дейкстры
 * Ограничение времени 	5 секунд
 * Ограничение памяти 	512Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Вам дано описание дорожной сети страны. Ваша задача – найти длину кратчайшего пути между городами А и B.
 *
 * Формат ввода
 * Сеть дорог задана во входном файле следующим образом:
 * первая строка содержит числа N и K (1 ≤ N ≤ 100000, 0 ≤ K ≤ 300000), где K – количество дорог.
 * Каждая из следующих K строк содержит описание дороги с двусторонним движением – три целых числа ai, bi и li (1 ≤ ai, bi ≤ N, 1 ≤ li ≤ 10^6).
 * Это означает, что имеется дорога длины li, которая ведет из города ai в город bi.
 * В последней строке находятся два числа А и В – номера городов, между которыми надо посчитать кратчайшее расстояние (1 ≤ A, B ≤ N)
 *
 * Формат вывода
 * Выведите одно число – расстояние между нужными городами. Если по дорогам от города А до города В доехать невозможно, выведите –1.
 * </pre>
 */
public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        alg(reader, writer);
        reader.close();
        writer.close();
    }

    public static void alg(BufferedReader reader, BufferedWriter writer) throws IOException {
        int[] buf = Arrays.stream(reader.readLine().split(" ")).filter(s -> !s.isEmpty()).mapToInt(Integer::parseInt).toArray();
        int N = buf[0];
        int K = buf[1];

        Map<Integer, Map<Integer, Long>> edges = new HashMap<>(); // <from, <to, weight>>
        for (int i = 0; i < K; i++) {
            buf = Arrays.stream(reader.readLine().split(" ")).filter(s -> !s.isEmpty()).mapToInt(Integer::parseInt).toArray();
            edges.computeIfAbsent(buf[0], k -> new HashMap<>()).put(buf[1], (long) buf[2]);
            edges.computeIfAbsent(buf[1], k -> new HashMap<>()).put(buf[0], (long) buf[2]);
        }

        buf = Arrays.stream(reader.readLine().split(" ")).filter(s -> !s.isEmpty()).mapToInt(Integer::parseInt).toArray();
        int A = buf[0];
        int B = buf[1];

        String solution = alg2(N, K, edges, A, B);

        writer.write(solution);
        writer.flush();
    }

    public static String alg1(int N, int K, Map<Integer, Map<Integer, Long>> edges, int A, int B) {
        boolean[] visited = new boolean[N + 1];

        long[] weight = new long[N + 1];
        Arrays.fill(weight, Long.MAX_VALUE);
        weight[A] = 0;

        Integer a;
        while (null != (a = IntStream.range(1, N + 1).boxed()
                .filter(i -> !visited[i] && weight[i] != Long.MAX_VALUE)
                .min(comparingLong(i -> weight[i])).orElse(null))) {

            visited[a] = true;

            Long A2a = weight[a];

            for (Map.Entry<Integer, Long> e : edges.getOrDefault(a, new HashMap<>()).entrySet()) {
                Integer b = e.getKey();
                Long a2b = e.getValue();

                long curWeight = weight[b];
                long newWeight = A2a + a2b;
                if (newWeight < curWeight) {
                    weight[b] = newWeight;
                }
            }
        }

        long A2B = weight[B] == Long.MAX_VALUE ? -1 : weight[B];
        return "" + A2B;
    }

    public static String alg2(int N, int K, Map<Integer, Map<Integer, Long>> edges, int A, int B) {
        Map<Integer, Long> cumulativeVByK = new HashMap<>(Map.of(A, 0L));
        Map<Integer, Integer> previousK = new HashMap<>();
        BiFunction<Long, Long, Long> accumulator = Long::sum;
        Predicate<Integer> stopBeforeVisit = dot -> dot == B;
        dijkstra(edges, N, K * 2, cumulativeVByK, previousK, accumulator, stopBeforeVisit);

        Long A2B = cumulativeVByK.getOrDefault(B, -1L);
        return "" + A2B;
    }

    // =================================================================================================================

    public static <K, V extends Comparable<V>>
    void dijkstra(Map<K, Map<K, V>> edges, int V, int E,
                  Map<K, V> cumulativeVByK, Map<K, K> previousK, BiFunction<V, V, V> accumulator,
                  Predicate<K> stopBeforeVisit) {

        Set<K> visited = new HashSet<>();

        MappedSet<V, K> kSetByCumulativeV = new MappedSet<V, K>(((E * Math.log(V)) < (V * V)) ? new TreeMap<>() : new HashMap<>());

        Map.Entry<K, V> e = cumulativeVByK.entrySet().iterator().next();
        kSetByCumulativeV.add(e.getValue(), e.getKey());

        Map.Entry<V, Set<K>> firstEntry;
        while ((firstEntry = kSetByCumulativeV.firstEntry()) != null) {
            K A = firstEntry.getValue().iterator().next();

            kSetByCumulativeV.remove(firstEntry.getKey(), A);
            visited.add(A);

            if (stopBeforeVisit.test(A)) {
                break;
            }

            for (K B : edges.getOrDefault(A, new HashMap<>()).keySet()) {
                if (visited.contains(B)) {
                    continue;
                }

                V curCumulativeV = cumulativeVByK.get(B);
                V newCumulativeV = accumulator.apply(cumulativeVByK.get(A), edges.get(A).get(B));
                boolean requireUpdate = curCumulativeV != null && newCumulativeV.compareTo(curCumulativeV) < 0;
                if (curCumulativeV == null || requireUpdate) {
                    cumulativeVByK.put(B, newCumulativeV);
                    kSetByCumulativeV.add(newCumulativeV, B);
                    previousK.put(B, A);
                }
                if (requireUpdate) {
                    kSetByCumulativeV.remove(curCumulativeV, B);
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

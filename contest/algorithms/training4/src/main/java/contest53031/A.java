package contest53031;

import java.io.*;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.stream.IntStream;

import static java.util.Comparator.comparingInt;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/53031">Алг 4.0 ДЗ 3 (53031) Кратчайшие пути в графах</a>
 * <a href="https://contest.yandex.ru/contest/53031/run-report/98294692">OK  189ms  12.84Mb</a>
 *
 * A. Дейкстра
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Дан ориентированный взвешенный граф. Найдите кратчайшее расстояние от одной заданной вершины до другой.
 *
 * Формат ввода
 * В первой строке содержатся три числа: N, S и F (1≤ N ≤ 100, 1 ≤ S, F ≤ N),
 * где N — количество вершин графа, S — начальная вершина, а F — конечная.
 *
 * В следующих N строках вводится по N чисел, не превосходящих 100, – матрица смежности графа,
 * где -1 означает что ребра между вершинами нет, а любое неотрицательное число — наличие ребра данного веса.
 * На главной диагонали матрицы записаны нули.
 *
 * Формат вывода
 * Выведите искомое расстояние или -1, если пути между указанными вершинами не существует.
 * </pre>
 */
public class A {
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
        int S = buf[1];
        int F = buf[2];
        int edgeCount = 0;
        Map<Integer, Map<Integer, Integer>> edges = new HashMap<>(); // <from, <to, weight>>
        for (int i = 0; i < N; i++) {
            Map<Integer, Integer> e = new HashMap<>();
            int[] edgeWeights = Arrays.stream(reader.readLine().split(" ")).filter(s -> !s.isEmpty()).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < N; j++) {
                int edgeWeight = edgeWeights[j];
                if (i != j && edgeWeight != -1) {
                    edgeCount++;
                    e.put(j + 1, edgeWeight);
                }
            }
            edges.put(i + 1, e);
        }

        String solution = alg2(N, S, F, edgeCount, edges);

        writer.write(solution);
        writer.flush();
    }

    public static String alg1(int N, int S, int F, int edgeCount, Map<Integer, Map<Integer, Integer>> edges) {
        boolean[] visited = new boolean[N + 1];

        int[] weight = new int[N + 1];
        Arrays.fill(weight, Integer.MAX_VALUE);
        weight[S] = 0;

        Integer A;
        while (null != (A = IntStream.range(1, N + 1).boxed()
                .filter(i -> !visited[i] && weight[i] != Integer.MAX_VALUE)
                .min(comparingInt(i -> weight[i])).orElse(null))) {

            visited[A] = true;

            Integer S2A = weight[A];

            for (Map.Entry<Integer, Integer> e : edges.get(A).entrySet()) {
                Integer B = e.getKey();
                Integer A2B = e.getValue();
                weight[B] = Math.min(weight[B], S2A + A2B);
            }
        }

        int S2F = weight[F] == Integer.MAX_VALUE ? -1 : weight[F];
        return "" + S2F;
    }

    public static String alg2(int N, int S, int F, int edgeCount, Map<Integer, Map<Integer, Integer>> edges) {
        Map<Integer, Integer> cumulativeVByK = new HashMap<>(Map.of(S, 0));
        Map<Integer, Integer> previousK = new HashMap<>();
        BiFunction<Integer, Integer, Integer> accumulator = Integer::sum;
        dijkstra(edges, N, edgeCount, cumulativeVByK, previousK, accumulator);

        Integer S2F = cumulativeVByK.getOrDefault(F, -1);
        return "" + S2F;
    }

    // =================================================================================================================

    public static <K, V extends Comparable<V>>
    void dijkstra(Map<K, Map<K, V>> edges, int V, int E, Map<K, V> accumVByK, Map<K, K> prevK, BiFunction<V, V, V> accum) {
        Set<K> visited = new HashSet<>();

        MappedSet<V, K> kSetByCumulativeV = new MappedSet<V, K>(((E * Math.log(V)) < (V * V)) ? new TreeMap<>() : new HashMap<>());

        Map.Entry<K, V> e = accumVByK.entrySet().iterator().next();
        kSetByCumulativeV.add(e.getValue(), e.getKey());

        Map.Entry<V, Set<K>> firstEntry;
        while ((firstEntry = kSetByCumulativeV.firstEntry()) != null) {
            K A = firstEntry.getValue().iterator().next();

            kSetByCumulativeV.remove(firstEntry.getKey(), A);
            visited.add(A);

            for (K B : edges.get(A).keySet()) {
                if (visited.contains(B)) {
                    continue;
                }

                V curCumulativeV = accumVByK.get(B);
                V newCumulativeV = accum.apply(accumVByK.get(A), edges.get(A).get(B));
                boolean requireUpdate = curCumulativeV != null && newCumulativeV.compareTo(curCumulativeV) < 0;
                if (curCumulativeV == null || requireUpdate) {
                    accumVByK.put(B, newCumulativeV);
                    kSetByCumulativeV.add(newCumulativeV, B);
                    prevK.put(B, A);
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

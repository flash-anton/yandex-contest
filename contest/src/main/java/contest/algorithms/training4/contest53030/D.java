package contest.algorithms.training4.contest53030;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/53030">Алг 4.0 ДЗ 2 (53030) Хеши для строк</a>
 * <a href="https://contest.yandex.ru/contest/53030/run-report/97418358">OK  1.95s  156.09Mb</a>
 *
 * D. Кубики в зеркале
 * Ограничение времени 	5 секунд
 * Ограничение памяти 	256Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Привидение Петя любит играть со своими кубиками. Он любит выкладывать их в ряд и разглядывать свое творение.
 * Недавно друзья решили подшутить над Петей и поставили в его игровой комнате зеркало.
 * Известно, что привидения не отражаются в зеркале, а кубики отражаются.
 *
 * Теперь Петя видит перед собой N цветных кубиков, но не знает, какие из этих кубиков настоящие, а какие — отражение в зеркале.
 * Выясните, сколько кубиков может быть у Пети.
 *
 * Петя видит отражение всех кубиков в зеркале и часть кубиков, которая находится перед ним.
 * Часть кубиков может быть позади Пети, их он не видит.
 *
 * Пример на рисунке Петя смотрит вправо, | - зеркало, () - видит справа
 *   (112|211)
 * 1122(1|12211)
 * 112211|(112211)
 *
 * Формат ввода
 * Первая строка входного файла содержит число N (1≤ N ≤1000000 ) и количество различных цветов,
 * в которые могут быть раскрашены кубики — M (1≤ M ≤1000000 ).
 * Следующая строка содержит N целых чисел от 1 до M — цвета кубиков.
 *
 * Формат вывода
 * Выведите в выходной файл все такие K, что у Пети может быть K кубиков.
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
        int[] buf = Arrays.stream(reader.readLine().split(" ")).filter(s -> !s.isEmpty()).mapToInt(Integer::valueOf).toArray();
        int N = buf[0];
        int M = buf[1];
        Integer[] m = Arrays.stream(reader.readLine().split(" ")).filter(s -> !s.isEmpty()).map(Integer::valueOf).toArray(Integer[]::new);

        String solution = alg2(N, M, m);

        writer.write(solution);
        writer.flush();
    }

    public static String alg1(int N, int M, Integer[] m) {
        List<Integer> aLenList = new ArrayList<>();
        for (int L = N / 2; L >= 0; L--) { // O(N*N)
            int i = -1;
            boolean isEqual = true;
            while (++i < L && isEqual) { // O(N)
                int mirrorI = L + i;
                int actualI = L - 1 - i;
                isEqual = Objects.equals(m[mirrorI], m[actualI]);
            }
            if (isEqual) {
                aLenList.add(N - L);
            }
        }
        return aLenList.stream().map(String::valueOf).collect(Collectors.joining(" "));
    }

    /**
     * <pre>
     * Визуализация вариантов для N (| - зеркало, () - видит справа):
     * 1: a|(a)
     * 2: ab|(ba)
     *    (a|a)
     * 3: abc|(cba)
     *    a(b|ba)
     * 4: abcd|(dcba)
     *    ab(c|cba)
     *     (ab|ba)
     * 5: abcde|(edcba)
     *    abc(d|dcba)
     *     a(bc|cba)
     * 6: abcdef|(fedcba)
     *    abcd(e|edcba)
     *     ab(cd|dcba)
     *      (abc|cba)
     *
     * Если последовательность X "симметрична" последовательности Y, то:
     * -  X          .hash()= Y .reverse()          .hash() и  X .reverse()          .hash()= Y          .hash()
     * -  X_         .hash()=_Y .reverse()          .hash() и  X_.reverse()          .hash()=_Y          .hash()
     * - _X          .hash()= Y_.reverse()          .hash() и _X .reverse()          .hash()= Y_         .hash()
     * -  X.sub(a, b).hash()= Y .reverse().sub(a, b).hash() и  X .reverse().sub(a, b).hash()= Y.sub(a, b).hash()
     *
     * Петя видит суффикс A_ прямой последовательности кубиков А и всю симметричную В.
     * Причем |A_|+|B|=N.
     * Поскольку |A_|<=|A| и |A|=|B|, то |A_|=[0, N/2] и |A|=|B|=[N, (N+1)/2].
     * Из указанного диапазона |A_| допустимы те, при которых A_ и _B - симметричны.
     *
     * Исходную последовательность можно принять за B, а за A принять B.reverse().
     * Для обеих последовательностей посчитать префиксные хеши за O(N).
     * Перебрать допустимые |A_|=[0, N/2] за O(N)
     * вычислить offsetA и offsetB, а L = |A_|
     * сравнить A.sub(offsetA, L) и B.sub(offsetB, L) на эквивалентность за O(1)
     * если эквивалентны, то aLenList.add(N-L)
     *
     * Итоговая сложность - O(N)
     * </pre>
     */
    public static String alg2(int N, int M, Integer[] m) {
        PrefixHashes<Integer> phB = new PrefixHashes<>(m, i -> i);
        Integer[] a = IntStream.range(0, N).mapToObj(i -> m[N - 1 - i]).toArray(Integer[]::new);
        PrefixHashes<Integer> phA = new PrefixHashes<>(a, i -> i);

        List<Integer> aLenList = new ArrayList<>();
        for (int L = N / 2; L > 0; L--) { // O(N)
            int offsetA = N - L;
            if (Objects.equals(m[L - 1], m[L]) && PrefixHashes.isSubstrEqual(phA, phB, offsetA, L, L)) { // first O(N), next O(1)
                aLenList.add(N - L);
            }
        }
        aLenList.add(N);

        return aLenList.stream().map(String::valueOf).collect(Collectors.joining(" "));
    }

    // =================================================================================================================

    public static class PrefixHashes<E> {
        final E[] a;
        final ToIntFunction<E> mapper;
        final Map<Integer, Integer> xAndP;
        final Map<Integer, Map<Integer, int[]>> prefixHashes = new HashMap<>();
        final Map<Integer, Map<Integer, int[]>> poweredXsHashes = new HashMap<>();

        public static PrefixHashes<Character> of(String s) {
            Character[] a = IntStream.range(0, s.length()).mapToObj(s::charAt).toArray(Character[]::new);
            return new PrefixHashes<>(a, e -> e);
        }

        public PrefixHashes(E[] a, ToIntFunction<E> mapper) {
            this(a, mapper, Map.of(257, 1_000_000_007, 353, 1_000_000_009));
        }

        public PrefixHashes(E[] a, ToIntFunction<E> mapper, Map<Integer, Integer> xAndP) {
            this.a = Arrays.copyOf(a, a.length);
            this.mapper = mapper;
            this.xAndP = Map.copyOf(xAndP);
        }

        /**
         * Проверка эквивалентности подстрок одной строки за O(1)
         */
        public boolean isSubstrEqual(int offset1, int offset2, int len) {
            AtomicBoolean isEqual = new AtomicBoolean(true);
            xAndP.forEach((x_, p) -> isEqual.compareAndSet(true, isSubstrEqual(x_, p, offset1, offset2, len)));
            return isEqual.get();
        }

        /**
         * Проверка эквивалентности подстрок разных строк за O(1)
         */
        public static <E> boolean isSubstrEqual(PrefixHashes<E> ph1, PrefixHashes<E> ph2, int offset1, int offset2, int len) {
            Map<Integer, Set<Integer>> commonXAndP = new HashMap<>();
            ph1.xAndP.forEach((x_, p) -> commonXAndP.computeIfAbsent(x_, k -> new HashSet<>()).add(p));
            ph2.xAndP.forEach((x_, p) -> commonXAndP.computeIfAbsent(x_, k -> new HashSet<>()).add(p));

            AtomicBoolean isEqual = new AtomicBoolean(true);
            commonXAndP.forEach((x_, pList) -> pList.forEach(p -> {
                int[] h1 = ph1.prefixHashes(x_, p);
                int[] h2 = ph2.prefixHashes(x_, p);
                int[] x = ph1.poweredXsHashes(x_, p);
                isEqual.compareAndSet(true, isSubstrEqual(x, h1, h2, p, offset1, offset2, len));
            }));
            return isEqual.get();
        }

        private static boolean isSubstrEqual(int[] x, int[] h1, int[] h2, int p, int offset1, int offset2, int len) {
            int a1 = (int) ((h1[offset1 + len] + (long) h2[offset2] * x[len]) % p);
            int a2 = (int) ((h2[offset2 + len] + (long) h1[offset1] * x[len]) % p);
            return a1 == a2;
        }

        private boolean isSubstrEqual(int x_, int p, int offset1, int offset2, int len) {
            int[] h = prefixHashes(x_, p);
            int[] x = poweredXsHashes(x_, p);
            return isSubstrEqual(x, h, h, p, offset1, offset2, len);
        }

        private int[] prefixHashes(int x_, int p) {
            return prefixHashes.computeIfAbsent(x_, k -> new HashMap<>()).computeIfAbsent(p, k -> {
                int[] h = new int[a.length + 1];
                for (int i = 1; i < a.length + 1; i++) {
                    h[i] = (int) (((long) h[i - 1] * x_ + mapper.applyAsInt(a[i - 1])) % p);
                }
                return h;
            });
        }

        private int[] poweredXsHashes(int x_, int p) {
            return poweredXsHashes.computeIfAbsent(x_, k -> new HashMap<>()).computeIfAbsent(p, k -> {
                int[] x = new int[a.length + 1];
                x[0] = 1;
                for (int i = 1; i < a.length + 1; i++) {
                    x[i] = (int) (((long) x[i - 1] * x_) % p);
                }
                return x;
            });
        }
    }
}

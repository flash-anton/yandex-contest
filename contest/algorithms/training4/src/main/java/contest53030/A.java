package contest53030;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.ToIntFunction;
import java.util.stream.IntStream;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/53030">Алг 4.0 ДЗ 2 (53030) Хеши для строк</a>
 * <a href="https://contest.yandex.ru/contest/53030/run-report/97353531">OK  1.181s  51.86Mb</a>
 *
 * A. Равенство подстрок
 * Ограничение времени 	15 секунд
 * Ограничение памяти 	512Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Дана строка S, состоящая из строчных латинских букв.
 *
 * Определите, совпадают ли строки одинаковой длины L, начинающиеся с позиций A и B.
 *
 * Формат ввода
 * В первой строке записана S (1 ≤ |S| ≤ 2*10^5), состоящая из строчных латинских букв.
 * Во второй строке записано число Q (1 ≤ Q ≤ 2*10^5) — количество запросов.
 * В следующих Q строках записаны запросы:
 * целые числа L, A и B (1 ≤ L ≤ |S|, 0 ≤ A, B ≤ (|S| - L)) — длина подстрок и позиции, с которых они начинаются.
 *
 * Формат вывода
 * Если строки совпадают — выведите "yes", иначе — "no".
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
        String S = reader.readLine();
        int Q = Integer.parseInt(reader.readLine().trim());
        String[] requests = new String[Q];
        for (int i = 0; i < Q; i++) {
            requests[i] = reader.readLine().trim();
        }

        String solution = alg2(S, Q, requests);

        writer.write(solution);
        writer.flush();
    }

    public static String alg1(String S, int Q, String[] requests) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) { // O (Q * N)
            int[] ints = Arrays.stream(requests[i].split(" ")).mapToInt(Integer::valueOf).toArray();
            int L = ints[0];
            int A = ints[1];
            int B = ints[2];
            String sA = S.substring(A, A + L);
            String sB = S.substring(B, B + L);
            String s = sA.equals(sB) ? "yes" : "no";// O(L) -> O(N)
            sb.append(s).append("\n");
        }
        return sb.toString();
    }

    public static String alg2(String S, int Q, String[] requests) {
        StringBuilder sb = new StringBuilder();
        PrefixHashes<Character> ph = PrefixHashes.of(S);
        for (int i = 0; i < Q; i++) { // O (Q + N)
            int[] ints = Arrays.stream(requests[i].split(" ")).mapToInt(Integer::valueOf).toArray();
            int L = ints[0];
            int A = ints[1];
            int B = ints[2];
            String s = ph.isSubstrEqual(A, B, L) ? "yes" : "no"; // first: O(L)->O(N), other: O(1)
            sb.append(s).append("\n");
        }
        return sb.toString();
    }

    public static String alg3(String S, String S2, int Q, String[] requests) {
        StringBuilder sb = new StringBuilder();
        PrefixHashes<Character> ph1 = PrefixHashes.of(S);
        PrefixHashes<Character> ph2 = PrefixHashes.of(S2);
        for (int i = 0; i < Q; i++) { // O (Q + N)
            int[] ints = Arrays.stream(requests[i].split(" ")).mapToInt(Integer::valueOf).toArray();
            int L = ints[0];
            int A = ints[1];
            int B = ints[2];
            String s = PrefixHashes.isSubstrEqual(ph1, ph2, A, B, L) ? "yes" : "no"; // first: O(L)->O(N), other: O(1)
            sb.append(s).append("\n");
        }
        return sb.toString();
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

package contest53033;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/53033">Алг 4.0 Финал (53033)</a>
 * <a href="https://contest.yandex.ru/contest/53033/run-report/99484335">OK  1.416s  51.15Mb</a>
 *
 * B. Зеркальная z-функция
 * Ограничение времени 	2 секунды
 * Ограничение памяти 	512Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Строка S состоит из N букв. Зеркальная z-функция определяется для индекса i как максимально возможное k, такое что:
 *
 * S[1] + S[2] + S[3] + … + S[k] = S[i] + S[i–1] + S[i–2] + ... + S[i–k+1]
 *
 * Определите значение зеркальной z-функции для всех i от 1 до N.
 * Формат ввода
 *
 * В первой строке записано одно число N (1 ≤ N ≤ 200000). Во второй строке записана строка длиной N символов, состоящая только из заглавных и строчных латинских букв.
 * Формат вывода
 *
 * Выведите N чисел — значения функции для i от 1 до N.
 * </pre>
 */
public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        alg(reader, writer);
        reader.close();
        writer.close();
    }

    public static void alg(BufferedReader reader, BufferedWriter writer) throws IOException {
        int N = Integer.parseInt(reader.readLine().trim());
        String S = reader.readLine();

        String solution = alg4(N, S);

        writer.write(solution);
        writer.flush();
    }

    public static String alg4(int N, String S) {
        byte[] b = new byte[N * 2 + 1];
        System.arraycopy(S.getBytes(), 0, b, N + 1, N);

        b[N] = '#';

        byte[] a = Arrays.copyOfRange(b, N + 1, N * 2 + 1);
        rotate(a);
        System.arraycopy(a, 0, b, 0, N);

        S = new String(b, 0, N * 2 + 1);

        PrefixHashes<Character> ph1 = PrefixHashes.of(S);
        int[] zf = new int[N];
        for (int i = 0; i < N; i++) { // O(NlogN)
            int B = N - 1 - i;
            int L = i + 1;
            int max = 0;
            if (S.charAt(N + 1 + i) == S.charAt(B)) {
                max = binSearchFromLeft(1, L + 1, l -> { // O(logN)
                    return ph1.isSubstrEqual(B, N + 1, l); // first: O(N), other: O(1)
                });
            }
            zf[i] = max;
        }
        return Arrays.stream(zf).mapToObj(String::valueOf).collect(Collectors.joining(" "));
    }

    public static String alg3(int N, String S) {
        byte[] s = new byte[N * 2 + 1];
        System.arraycopy(S.getBytes(), 0, s, N + 1, N);

        s[N] = '#';

        byte[] B = Arrays.copyOfRange(s, N + 1, N * 2 + 1);
        rotate(B);
        System.arraycopy(B, 0, s, 0, N);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int c = zFunction(s, N - i - 1, N + 1)[N + 1];
            sb.append(c).append(' ');
        }

        return sb.toString();
    }

    public static String alg2(int N, String S) {
        byte[] A = S.getBytes();
        rotate(A);
        String rotatedS = new String(A, 0, N);

        PrefixHashes<Character> ph1 = PrefixHashes.of(S);
        PrefixHashes<Character> ph2 = PrefixHashes.of(rotatedS);
        int[] zf = new int[N];
        for (int i = 0; i < N; i++) { // O(NlogN)
            int B = N - 1 - i;
            int L = i + 1;
            int max = 0;
            if (S.charAt(0) == rotatedS.charAt(B)) {
                max = binSearchFromLeft(1, L + 1, l -> { // O(logN)
                    return PrefixHashes.isSubstrEqual(ph1, ph2, 0, B, l); // first: O(N), other: O(1)
                });
            }
            zf[i] = max;
        }
        return Arrays.stream(zf).mapToObj(String::valueOf).collect(Collectors.joining(" "));
    }

    public static String alg1(int N, String S) {
        byte[] B = S.getBytes();
        rotate(B);
        String rotatedS = new String(B, 0, N);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            String s = rotatedS.substring(N - 1 - i, N) + '#' + S;
            int c = zFunction(s, i + 2)[i + 2];
            sb.append(c).append(' ');
        }

        return sb.toString();
    }

    static void rotate(byte[] b) {
        int N = b.length;
        for (int i = 0; i < N / 2; i++) {
            byte t = b[i];
            b[i] = b[N - 1 - i];
            b[N - 1 - i] = t;
        }
    }

    /**
     * Бинарный поиск слева направо. O(logN)<br>
     * [----] -1 => [++--] 1 => [++++] 3
     */
    static int binSearchFromLeft(int LInclusive, int RExclusive, Predicate<Integer> moveToRight) {
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
     * Z-функция от строки - это массив, Z[i] которого - длина наибольшего общего префикса у суффикса строки, начинающегося с i, и самой строки.
     */
    public static int[] zFunction(byte[] s, int offset, int lastIndexInclusive) {
        int n = s.length;
        int[] zf = new int[n];
        int left = offset, right = offset;
        for (int i = offset + 1; i <= lastIndexInclusive; i++) {
            zf[i] = max(0, min(right - i, zf[i - left]));
            while (i + zf[i] < n && s[offset + zf[i]] == s[i + zf[i]]) {
                zf[i]++;
            }
            if (i + zf[i] > right) {
                left = i;
                right = i + zf[i];
            }
        }
        return zf;
    }

    /**
     * Z-функция от строки - это массив, Z[i] которого - длина наибольшего общего префикса у суффикса строки, начинающегося с i, и самой строки.
     */
    public static int[] zFunction(String s, int lastIndexInclusive) {
        int n = s.length();
        int[] zf = new int[n];
        int left = 0, right = 0;
        for (int i = 1; i <= lastIndexInclusive; i++) {
            zf[i] = max(0, min(right - i, zf[i - left]));
            while (i + zf[i] < n && s.charAt(zf[i]) == s.charAt(i + zf[i])) {
                zf[i]++;
            }
            if (i + zf[i] > right) {
                left = i;
                right = i + zf[i];
            }
        }
        return zf;
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

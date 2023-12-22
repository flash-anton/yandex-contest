package contest53030;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.IntStream;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/53030">Алг 4.0 ДЗ 2 (53030) Хеши для строк</a>
 * <a href="https://contest.yandex.ru/contest/53030/run-report/97542030">OK  2.886s  38.39Mb</a>
 *
 * E. Подпалиндромы
 * Ограничение времени 	3 секунды
 * Ограничение памяти 	256Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Строка называется палиндромом, если она читается одинаково как слева направо, так и справа налево.
 * Например, строки abba, ata являются палиндромами.
 *
 * Вам дана строка. Ее подстрокой называется некоторая непустая последовательность подряд идущих символов.
 * Напишите программу, которая определит, сколько подстрок данной строки является палиндромами.
 *
 * Формат ввода
 * Вводится одна строка, состоящая из прописных латинских букв. Длина строки не превышает 100000 символов.
 *
 * Формат вывода
 * Выведите одно число — количество подстрок данной строки, которые являются палиндромами
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
        String S = reader.readLine();

        String solution = alg2(S);

        writer.write(solution);
        writer.flush();
    }

    public static String alg1(String S) {
        long count = 0;
        for (int i = 0; i < S.length(); i++) {
            for (int L = i, R = i; L >= 0 && R < S.length() && S.charAt(L) == S.charAt(R); L--, R++) { // odd
                count++;
            }
            for (int L = i - 1, R = i; L >= 0 && R < S.length() && S.charAt(L) == S.charAt(R); L--, R++) { // even
                count++;
            }
        }
        return "" + count;
    }

    public static String alg2(String S) {
        int N = S.length();
        PrefixHashes<Character> phL = PrefixHashes.of(S);
        Character[] a = IntStream.range(0, N).mapToObj(i -> S.charAt(N - 1 - i)).toArray(Character[]::new);
        PrefixHashes<Character> phR = new PrefixHashes<>(a, e -> e);

        long count = 1; // first letter
        for (int i = 1; i < N; i++) {
            int L = i;

            int oddMaxLen = Math.min(i + 1, N - i); // odd
            if (oddMaxLen > 1 && S.charAt(i - 1) == S.charAt(i + 1)) {
                int R = (N - i) - 1;
                count += binSearchFromLeft(2, oddMaxLen + 1, l -> PrefixHashes.isSubstrEqual(phL, phR, L, R, l));
            } else {
                count++;
            }

            int evenMaxLen = Math.min(i, N - i); // even
            if (S.charAt(i-1) == S.charAt(i)) {
                int R = (N - i);
                count += binSearchFromLeft(1, evenMaxLen + 1, l -> PrefixHashes.isSubstrEqual(phL, phR, L, R, l));
            }
        }
        return "" + count;
    }

    public static String alg3(String S) {
        int N = S.length();
        int n = 2 * N + 1;
        Character[] a = new Character[n];
        Character[] b = new Character[n];
        Arrays.fill(a, '#');
        Arrays.fill(b, '#');
        for (int i = 0; i < N; i++) {
            a[i * 2 + 1] = S.charAt(i);
            b[i * 2 + 1] = S.charAt(N - 1 - i);
        }

        PrefixHashes<Character> phL = new PrefixHashes<>(a, e -> e);
        PrefixHashes<Character> phR = new PrefixHashes<>(b, e -> e);

        long count = 0;
        for (int i = 1; i < n - 1; i++) {
            int L = i;
            int R = (n - i) - 1;
            int minLen = 1;
            int maxLen = Math.min(i + 1, n - i);
            boolean searchRequired;
            if (i % 2 == 1) { // a[i] - letter
                searchRequired = (maxLen > 2) && (a[i + 2] == a[i - 2]);
                if (searchRequired) {
                    minLen = 3;
                } else {
                    count++; // a[L] == b[R]
                }
            } else { // a[i] - #
                searchRequired = a[i + 1] == a[i - 1];
                if (searchRequired) {
                    minLen = 2;
                }
            }
            if (searchRequired) {
                count += binSearchFromLeft(minLen, maxLen + 1, l -> PrefixHashes.isSubstrEqual(phL, phR, L, R, l)) / 2;
            }
        }
        return "" + count;
    }

    // =================================================================================================================

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

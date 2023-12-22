package contest53030;

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
 * <a href="https://contest.yandex.ru/contest/53030">Алг 4.0 ДЗ 2 (53030) Хеши для строк</a>
 * <a href="https://contest.yandex.ru/contest/53030/run-report/97193141">OK  0.617s  73.18Mb</a>
 *
 * C. Z-функция
 * Ограничение времени 	2 секунды
 * Ограничение памяти 	256Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Дана непустая строка S, длина которой N не превышает 10^6. Будем считать, что элементы строки нумеруются от 0 до N-1.
 *
 * Вычислите z-функцию z[i] для всех i от 0 до N-1.
 * z[i] определяется как максимальная длина подстроки, начинающейся с позиции i и совпадающей с префиксом всей строки.
 * z[0] = 0
 *
 * Формат ввода
 * Одна строка длины N, 0 < N ≤ 10^6, состоящая из прописных латинских букв.
 *
 * Формат вывода
 * Выведите N чисел — значения z-функции для каждой позиции, разделённые пробелом.
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
        String S = reader.readLine();

        String solution = alg2(S);

        writer.write(solution);
        writer.flush();
    }

    public static String alg1(String S) {
        int N = S.length();
        int[] zf = new int[N];
        for (int i = 1; i < N; i++) { // O(N*N*LogN)
            int B = i;
            int L = N - B;
            int max = 0;
            if (S.charAt(0) == S.charAt(B)) {
                max = binSearchFromLeft(1, L + 1, l -> { // O(NlogN)
                    return isSubstrEqual(S, 0, B, l); // O(N)
                });
            }
            zf[i] = max;
        }
        if (zf.length > 0) {
            zf[0] = 0;
        }
        return Arrays.stream(zf).mapToObj(String::valueOf).collect(Collectors.joining(" "));
    }

    /**
     * По материалам лекции алгоритм реализуется за O(NlogN), где
     * O(N) на построение хешей для строк и на цикл по нему
     * O(logN) на бинарный поиск по хешам внутри цикла
     * !!! С такой реализацией ловим TL на 92 тесте (97192049 TL 2.064s 87.16Mb 92)
     */
    public static String alg2(String S) {
        PrefixHashes<Character> ph = PrefixHashes.of(S);
        int N = S.length();
        int[] zf = new int[N];
        for (int i = 1; i < N; i++) { // O(NlogN)
            int B = i;
            int L = N - B;
            int max = 0;
            if (S.charAt(0) == S.charAt(B)) {
                max = binSearchFromLeft(1, L + 1, l -> { // O(logN)
                    return ph.isSubstrEqual(0, B, l); // first: O(N), other: O(1)
                });
            }
            zf[i] = max;
        }
        if (zf.length > 0) {
            zf[0] = 0;
        }
        return Arrays.stream(zf).mapToObj(String::valueOf).collect(Collectors.joining(" "));
    }

    /**
     * !!! Z-функция на лекции не рассматривалась
     */
    public static String alg3(String S) {
        int[] zf = zFunction(S);
        if (zf.length > 0) {
            zf[0] = 0;
        }
        return Arrays.stream(zf).mapToObj(String::valueOf).collect(Collectors.joining(" "));
    }

    static boolean isSubstrEqual(String S, int offset1, int offset2, int len) {
        String s1 = S.substring(offset1, offset1 + len);
        String s2 = S.substring(offset2, offset2 + len);
        return s1.equals(s2);
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

    /**
     * Z-функция от строки - это массив, Z[i] которого - длина наибольшего общего префикса у суффикса строки, начинающегося с i, и самой строки.
     */
    static int[] zFunction(String s) {
        int n = s.length();
        int[] zf = new int[n];
        int left = 0, right = 0;
        for (int i = 1; i < n; i++) {
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

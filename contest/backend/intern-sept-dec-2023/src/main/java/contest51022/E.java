package contest51022;

import java.io.*;
import java.util.function.Predicate;

import static java.lang.Math.min;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/51022">Стаж бэк сен-дек 2023 (51022)</a>
 * <a href="https://contest.yandex.ru/contest/51022/run-report/102701159">TL  4.007s 41.30Mb  37</a>
 *
 * E. Близость
 * Язык 	Ограничение времени 	Ограничение памяти 	Ввод 	Вывод
 * Все языки 	4 секунды 	256Mb 	стандартный ввод или input.txt 	стандартный вывод или output.txt
 * Golang 1.21.0 	2 секунды 	256Mb
 * GNU GCC 13.1 C++20 	1 секунда 	256Mb
 * Clang 16.0.0 C++20 	1 секунда 	256Mb
 * Определим близость двух целочисленных массивов как длину их наибольшего совпадающего префикса (см. примечание).
 *
 * Примеры:
 *
 *     Близость [1, 2, 1, 3] и [1, 2, 3, 2] равна 2 — префикс [1, 2] совпадает;
 *     Близость [1, 2, 3] и [3, 2, 1] равна 0.
 *
 * Дано n целочисленных массивов a1,a2,…,an.
 *
 * Необходимо вычислить сумму близостей массивов ai и aj для каждой пары 1≤i<j≤n.
 *
 * Формат ввода
 * Первая строка содержит одно целое число n(1≤n≤3*10^5)  — количество массивов.
 * Каждый массив задаётся двумя строками.
 * Первая строка описания массива содержит единственное целое число ki(1≤k≤3*10^5)  — размер i-го массива.
 * Вторая строка описания содержит ki целых чисел aij(1≤aij≤10^9) — элементы i-го массива.
 * Гарантируется, что ∑n i=1 ki≤3*10^5.
 *
 * Формат вывода
 * Выведите единственное целое число  — суммарную попарную близость массивов.
 * </pre>
 */
public class E {
    public static void main(String[] args) throws IOException {
        try (InputStream reader = new BufferedInputStream(System.in, 3_000_000);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            alg(reader, writer);
        }
    }

    public static void alg(InputStream is, BufferedWriter writer) throws IOException {
        Scanner reader = new Scanner(is);
        int n = reader.nextInt();

        int x_ = 353;
        int p = 1_000_000_009;
        int[] x = new int[300_001];
        x[0] = 1;
        for (int i = 1; i < 300_001; i++) {
            x[i] = (int) (((long) x[i - 1] * x_) % p);
        }

        int[] firstNum = new int[n];
        int[][] h = new int[n][];
        for (int i = 0; i < n; i++) {
            int ki = reader.nextInt();
            h[i] = new int[ki + 1];
            int v = reader.nextInt();
            firstNum[i] = v;
            h[i][1] = (int) (((long) v) % p);
            for (int j = 2; j < ki + 1; j++) {
                v = reader.nextInt();
                h[i][j] = (int) (((long) h[i][j - 1] * x_ + v) % p);
            }
        }

        long s = 0;
        for (int i = 0; i < n - 1; i++) {
            int[] hI = h[i];
            for (int j = i + 1; j < n; j++) {
                int[] hJ = h[j];
                if (firstNum[i] != firstNum[j])
                    continue;

                int minLen = 1;
                int maxLen = min(hI.length, hJ.length);
                int commonLen = binSearchFromLeft(minLen, maxLen,
                        len -> isSubstrEqual(x, hI, hJ, p, len));

                s += commonLen;
            }
        }

        String solution = "" + s;

        writer.write(solution);
        writer.flush();
    }

    private static boolean isSubstrEqual(int[] x, int[] h1, int[] h2, int p, int len) {
        int a1 = h1[len] % p;
        int a2 = h2[len] % p;
        return a1 == a2;
    }

    /**
     * Бинарный поиск слева направо. O(logN)<br>
     * [----] -1 => [++--] 1 => [++++] 3
     */
    public static int binSearchFromLeft(int LInclusive, int RExclusive, Predicate<Integer> moveToRight) {
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
     * "Быстрый" ридер потока.
     */
    public static class Scanner {
        private final InputStream is;
        private int lastReadByte = '\n';

        public Scanner(InputStream is) {
            this.is = is;
        }

        private long nextLong() throws IOException {
            while (lastReadByte == ' ' || lastReadByte == '\n') {
                lastReadByte = is.read();
            }

            int sign = 1;
            if (lastReadByte == '-') {
                sign = -1;
                lastReadByte = is.read();
            }

            long num = 0;
            while (lastReadByte >= '0' && lastReadByte <= '9') {
                num = (num * 10) + sign * (lastReadByte - '0');
                lastReadByte = is.read();
            }
            return num;
        }

        public long[] nextLongs(long[] a) throws IOException {
            for (int i = 0; i < a.length; i++) {
                a[i] = nextLong();
            }
            return a;
        }

        public int nextInt() throws IOException {
            return (int) nextLong();
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

        public String[] nextWords(String[] a) throws IOException {
            for (int i = 0; i < a.length; i++) {
                a[i] = nextWord();
            }
            return a;
        }

        public String readLine() throws IOException {
            if (lastReadByte == '\n') {
                lastReadByte = is.read();
            }

            StringBuilder sb = new StringBuilder();
            while (!(lastReadByte == -1 || lastReadByte == '\n')) {
                sb.append((char) lastReadByte);
                lastReadByte = is.read();
            }
            return sb.toString();
        }
    }
}

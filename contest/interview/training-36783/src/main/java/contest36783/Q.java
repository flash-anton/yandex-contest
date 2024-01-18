package contest36783;

import java.io.*;

import static java.lang.Math.abs;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/36783">Interview training 36783</a>
 * <a href="https://contest.yandex.ru/contest/36783/run-report/105182437">OK  240ms  19.77Mb</a>
 *
 * Q. Прямота
 * Язык 	Ограничение времени 	Ограничение памяти 	Ввод 	Вывод
 * Все языки 	1 секунда 	256Mb 	стандартный ввод или input.txt 	стандартный вывод или output.txt
 * Node.js 14.15.5 	2 секунды 	256Mb
 * Python 3.7.3 	2 секунды 	256Mb
 * Golang 1.20.1 	0.3 секунды 	64Mb
 * GNU c++17 7.3 	0.3 секунды 	64Mb
 * GNU GCC 12.2 C++20 	0.3 секунды 	64Mb
 *
 * Вам даны n точек, расположенных на плоскости.
 * Определите, лежат ли все эти точки на одной прямой.
 *
 * Вы можете воспользоваться заготовками кода для данной задачи из репозитория курса.
 *
 * Формат ввода
 * В первой строке дано число точек n (2≤n≤10^5).
 * В следующих n строках по одной в строке заданы сами точки.
 * Каждая точка задаётся двумя координатами — xi yi, записанными через пробел.
 * Координаты — целые числа, не превосходящие 10^12 по модулю.
 * Точки могут совпадать друг с другом.
 *
 * Формат вывода
 * Выведите «YES», если все n точек лежат на одной прямой, и «NO», если нет.
 * </pre>
 */
public class Q {
    public static void main(String[] args) throws IOException {
        try (InputStream reader = new BufferedInputStream(System.in, 3_000_000);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            alg(reader, writer);
        }
    }

    public static void alg(InputStream is, BufferedWriter writer) throws IOException {
        Reader reader = new Reader(is);
        int n = reader.nextInt();
        long[] x = new long[n];
        long[] y = new long[n];
        for (int i = 0; i < n; i++) {
            x[i] = reader.nextLong();
            y[i] = reader.nextLong();
        }

        String solution = alg2(n, x, y);

        writer.write(solution);
        writer.flush();
    }

    /**
     * 105182437  OK  240ms  19.77Mb
     */
    public static String alg2(int n, long[] x, long[] y) {
        int i = nextDotIndex(n, x, y, 1);
        if (i < n) {
            long[] firstTan = tan(x, y, i);
            while ((i = nextDotIndex(n, x, y, ++i)) < n) {
                long[] iTan = tan(x, y, i);
                if (iTan[0] != firstTan[0] || iTan[1] != firstTan[1])
                    return "NO";
            }
        }
        return "YES";
    }

    /**
     * 105181068  OK  238ms  20.51Mb
     */
    public static String alg1(int n, long[] x, long[] y) {
        int secondDotIndex = nextDotIndex(n, x, y, 1);
        if (secondDotIndex >= n - 1) { // 1 or 2 uniq dots
            return "YES";
        }

        long[] firstVector = rightUpVector(x, y, secondDotIndex);

        long gcd = gcd(abs(firstVector[0]), abs(firstVector[1]));
        long[] smallerCommonVector = {firstVector[0] / gcd, firstVector[1] / gcd};

        int i = secondDotIndex;
        while ((i = nextDotIndex(n, x, y, ++i)) < n) {
            long[] iVector = rightUpVector(x, y, i);
            if (!isSameDirection(smallerCommonVector, iVector))
                return "NO";
        }

        return "YES";
    }

    static int nextDotIndex(int n, long[] x, long[] y, int i) {
        for (; i < n; i++)
            if (x[i] != x[0] || y[i] != y[0])
                return i;
        return n;
    }

    static long[] rightUpVector(long[] x, long[] y, int i) {
        long x0i = x[i] - x[0];
        long y0i = y[i] - y[0];
        if (x0i < 0) {
            x0i *= -1;
            y0i *= -1;
        } else if (x0i == 0 && y0i < 0) {
            y0i *= -1;
        }
        return new long[]{x0i, y0i};
    }

    static long gcd(long big, long small) {
        return (small == 0) ? big : gcd(small, big % small);
    }

    static long[] tan(long[] x, long[] y, int i) {
        long[] vector = rightUpVector(x, y, i);
        long gcd = gcd(vector[0], vector[1]);
        return new long[]{vector[0] / gcd, vector[1] / gcd};
    }

    static boolean isSameDirection(long[] smallerCommonVector, long[] vector) {
        long i = smallerCommonVector[0];
        long j = smallerCommonVector[1];
        boolean mustBeVertical = i == 0;
        boolean mustBeHorizontal = j == 0;

        long I = vector[0];
        long J = vector[1];
        boolean isVertical = I == 0;
        boolean isHorizontal = J == 0;

        if (mustBeVertical)
            return isVertical;

        if (mustBeHorizontal)
            return isHorizontal;

        // must be diagonal
        boolean isNotDiagonal = I == 0 || J == 0;
        if (isNotDiagonal)
            return false;

        // must have same i-/j-direction
        boolean iDifferentDirection = (i < 0) ? (I > 0) : (I < 0);
        boolean jDifferentDirection = (j > 0) ? (J < 0) : (J > 0);
        if (iDifferentDirection || jDifferentDirection)
            return false;

        // must have same i-/j-scale
        long iScale = I / i;
        long jScale = J / j;
        if (iScale != jScale)
            return false;

        // must be similar
        boolean iSimilar = I % i == 0;
        boolean jSimilar = J % j == 0;
        return iSimilar && jSimilar;
    }

    /**
     * "Быстрый" ридер потока.
     */
    public static class Reader {
        private final InputStream is;
        private int lastReadByte = '\n';

        public Reader(InputStream is) {
            this.is = is;
        }

        public long nextLong() throws IOException {
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

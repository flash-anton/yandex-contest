package contest57974;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Math.abs;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/57974">Intern 2024-02-06 job fair (57974)</a>
 * <a href="https://contest.yandex.ru/contest/57974/run-report/106349409">OK  1.662s  92.40Mb</a>
 *
 * D. Округление
 * Ограничение времени 	10 секунд
 * Ограничение памяти 	1Gb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Иногда при округлении к ближайшему всех слагаемых по отдельности получается совсем другая сумма:
 * round(2/3)+round(2/3)+round(2/3)≠2.
 *
 * Необходимо придумать метод округления,
 * при котором суммарная абсолютная разность элементов двух последовательностей минимальна,
 * а сумма совпадает.
 *
 * Заданы натуральные числа a1, a2, …, an и x.
 * Последовательность bi определяется по следующей формуле: bi=(x*ai)/(a1+a2+…+an).
 *
 * Нужно найти последовательность целых чисел ci такую, что при i=[1,n] ∑∣ci−bi∣ минимальна и ∑ci=x.
 *
 * Формат ввода
 * В первой строке записаны два целых числа n и x (1≤n≤1000000, 1≤x≤1000000).
 * Во второй строке записаны n целых чисел a1, a2, …, an (1≤ai≤1000000).
 *
 * Формат вывода
 * Выведите n целых чисел c1, c2, …, cn (0≤ci≤x). Числа в сумме должны давать значение x.
 * Если подходящих последовательностей несколько, выведите любую из них.
 * </pre>
 */
public class D {
    public static void main(String[] args) throws IOException {
        try (InputStream reader = new BufferedInputStream(System.in, 3_000_000);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            alg(reader, writer);
        }
    }

    public static void alg(InputStream is, BufferedWriter writer) throws IOException {
        Reader reader = new Reader(is);
        int n = reader.nextInt();
        int x = reader.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = reader.nextInt();
        }

        String solution = alg2(n, x, a);

        writer.write(solution);
        writer.flush();
    }

    /**
     * 106349409  OK  1.662s  92.40Mb
     */
    private static String alg2(int n, int x, int[] a) {
        long aSum = 0;
        for (int i = 0; i < n; i++) {
            aSum += a[i];
        }

        int[] floor = new int[n];
        long[] floorError = new long[n];
        int[] ceil = new int[n];
        long[] ceilError = new long[n];
        long[] errorDif = new long[n];
        boolean[] isFloorInC = new boolean[n];
        int[] c = new int[n];
        int cSum = 0;
        for (int i = 0; i < n; i++) {
            long xMulA = (long) x * a[i];
            floor[i] = (int) (xMulA / aSum);
            floorError[i] = xMulA % aSum;
            ceil[i] = floor[i];
            ceilError[i] = 0;
            if (floorError[i] != 0) {
                ceil[i] = floor[i] + 1;
                ceilError[i] = aSum - floorError[i];
            }
            errorDif[i] = abs(floorError[i] - ceilError[i]);
            isFloorInC[i] = floorError[i] <= ceilError[i];
            c[i] = isFloorInC[i] ? floor[i] : ceil[i];
            cSum += c[i];
        }

        int[] iSortedByErrorDif = IntStream.range(0, n)
                .boxed().sorted(comparing(i -> errorDif[i])).mapToInt(i -> i)
                .toArray();
        boolean mustFloorInC = cSum < x;
        int cChanger = cSum < x ? 1 : -1;
        for (int i : iSortedByErrorDif) {
            if (cSum == x) {
                break;
            }
            if ((mustFloorInC == isFloorInC[i]) && (floor[i] != ceil[i])) {
                c[i] += cChanger;
                cSum += cChanger;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int ci : c) {
            sb.append(ci).append(' ');
        }
        return sb.toString();
    }

    /**
     * 106346286  OK  2.477s  152.43Mb
     */
    private static String alg1(int n, int x, int[] a) {
        long aSum = Arrays.stream(a).mapToLong(ai -> ai).sum();
        long[] bMulASum = Arrays.stream(a).mapToLong(ai -> (long) x * ai).toArray();

        int[] cFloor = Arrays.stream(bMulASum).mapToInt(bMulASumI -> (int) (bMulASumI / aSum)).toArray();
        long[] cFloorError = Arrays.stream(bMulASum).map(bMulASumI -> bMulASumI % aSum).toArray();

        int[] cCeil = IntStream.range(0, n).map(i -> cFloorError[i] == 0 ? cFloor[i] : (cFloor[i] + 1)).toArray();
        long[] cCeilError = IntStream.range(0, n).mapToLong(i -> cFloorError[i] == 0 ? 0 : (aSum - cFloorError[i])).toArray();

        long[] cErrorDif = IntStream.range(0, n).mapToLong(i -> abs(cFloorError[i] - cCeilError[i])).toArray();

        Boolean[] isCFloorWithMinError = IntStream.range(0, n).mapToObj(i -> cFloorError[i] <= cCeilError[i]).toArray(Boolean[]::new);
        Boolean[] isCCeilWithMinError = IntStream.range(0, n).mapToObj(i -> !isCFloorWithMinError[i]).toArray(Boolean[]::new);

        int[] c = IntStream.range(0, n).map(i -> isCFloorWithMinError[i] ? cFloor[i] : cCeil[i]).toArray();
        int cSum = Arrays.stream(c).sum();

        List<Integer> iSortedByErrorDif = IntStream.range(0, n).boxed().sorted(comparing(i -> cErrorDif[i])).collect(toList());

        for (int iSorted = 0; iSorted < n && cSum < x; iSorted++) { // floor -> ceil
            int i = iSortedByErrorDif.get(iSorted);
            if (isCFloorWithMinError[i] && (cFloor[i] != cCeil[i])) {
                c[i]++;
                cSum++;
            }
        }

        for (int iSorted = 0; iSorted < n && cSum > x; iSorted++) { // ceil -> floor
            int i = iSortedByErrorDif.get(iSorted);
            if (isCCeilWithMinError[i] && (cFloor[i] != cCeil[i])) {
                c[i]--;
                cSum--;
            }
        }

        return Arrays.stream(c).mapToObj(String::valueOf).collect(Collectors.joining(" "));
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

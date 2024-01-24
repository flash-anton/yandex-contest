package contest27883;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

import static java.lang.Math.max;
import static java.util.stream.Collectors.toCollection;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/27883">Алг 1.0 ДЗ 7 (27883) Сортировка событий</a>
 * <a href="https://contest.yandex.ru/contest/27883/run-report/105716895">OK  216ms  17.55Mb</a>
 *
 * C. Рассадка в аудитории
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Экзамен по берляндскому языку проходит в узкой и длинной аудитории.
 * На экзамен пришло N студентов. Все они посажены в ряд.
 * Таким образом, позиция каждого человека задается координатой на оси Ox (эта ось ведет вдоль длинной аудитории).
 * Два человека могут разговаривать, если расстояние между ними меньше или равно D.
 * Какое наименьшее количество типов билетов должен подготовить преподаватель,
 * чтобы никакие два студента с одинаковыми билетами не могли разговаривать?
 * Выведите способ раздачи преподавателем билетов.
 *
 * Формат ввода
 * В первой строке входного файла содержится два целых числа N, D (1 ≤ N ≤ 10000; 0 ≤ D ≤ 10^6).
 * Вторая строка содержит последовательность различных целых чисел X1, X2, ..., XN,
 * где Xi (0 ≤ Xi ≤ 10^6) обозначает координату вдоль оси Ox i-го студента.
 *
 * Формат вывода
 * В первую строчку выходного файла выведите количество вариантов,
 * а во вторую, разделяя пробелами, номера вариантов студентов в том порядке, в каком они перечислены во входном файле.
 * </pre>
 */
public class C {
    public static void main(String[] args) throws IOException {
        try (InputStream reader = new BufferedInputStream(System.in, 3_000_000);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            alg(reader, writer);
        }
    }

    public static void alg(InputStream is, BufferedWriter writer) throws IOException {
        Reader reader = new Reader(is);
        int n = reader.nextInt();
        int d = reader.nextInt();
        int[] x = reader.nextInts(new int[n]);

        String solution = alg1(n, d, x);

        writer.write(solution);
        writer.flush();
    }

    public static String alg1(int n, int d, int[] x) {
        int[] xSorted = Arrays.copyOf(x, n);
        Arrays.sort(xSorted);

        int maxXCountInDWindow = maxXCountInDWindow(n, d, xSorted);

        Map<Integer, Integer> variantsByX = variantsByX(n, d, xSorted, maxXCountInDWindow);

        StringBuilder sb = new StringBuilder();
        sb.append(maxXCountInDWindow).append('\n');
        for (int xi : x)
            sb.append(variantsByX.get(xi)).append(' ');

        return sb.toString();
    }

    static int maxXCountInDWindow(int n, int d, int[] xSorted) {
        int xCount = 0;
        int maxXCount = 0;
        int L = 0;
        int R = 0;
        while (R < n) {
            int xl = xSorted[L];
            int xr = xSorted[R];
            int distance = xr - xl;
            if (distance <= d) {
                xCount++;
                maxXCount = max(maxXCount, xCount);
                R++;
            } else {
                xCount--;
                L++;
            }
        }
        return maxXCount;
    }

    static Map<Integer, Integer> variantsByX(int n, int d, int[] xSorted, int maxXCountInDWindow) {
        Queue<Integer> unusedVariants = IntStream.range(1, maxXCountInDWindow + 1).boxed().collect(toCollection(ArrayDeque::new));
        Queue<Integer> inuseVariants = new ArrayDeque<>(maxXCountInDWindow);
        Map<Integer, Integer> variantsByX = new HashMap<>(n);
        int L = 0;
        int R = 0;
        while (R < n) {
            int xl = xSorted[L];
            int xr = xSorted[R];
            int distance = xr - xl;
            if (distance <= d) {
                int variant = unusedVariants.remove();
                inuseVariants.offer(variant);
                variantsByX.put(xr, variant);
                R++;
            } else {
                int variant = inuseVariants.remove();
                unusedVariants.offer(variant);
                L++;
            }
        }
        return variantsByX;
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

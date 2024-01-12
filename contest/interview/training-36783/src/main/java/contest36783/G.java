package contest36783;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/36783">Interview training 36783</a>
 * <a href="https://contest.yandex.ru/contest/36783/run-report/104788186">OK  1.029s  93.91Mb</a>
 *
 * G. Пути в дереве
 * Язык 	Ограничение времени 	Ограничение памяти 	Ввод 	Вывод
 * Все языки 	2 секунды 	256Mb 	стандартный ввод или input.txt 	стандартный вывод или output.txt
 * Python 3.7.3 	4.1 секунда 	256Mb
 * Golang 1.20.1 	0.6 секунд 	64Mb
 * GNU c++17 7.3 	0.6 секунд 	64Mb
 * GNU GCC 12.2 C++20 	0.6 секунд 	64Mb
 *
 * Дано укоренённое дерево на N вершинах и число X. В каждой вершине записано число — её вес.
 *
 * Назовём восходящим путь ai,p(ai),p(p(ai)),..., где p(a) — родитель вершины a.
 * Проще говоря, восходящий путь — это путь, который начинается с некоторой вершины и двигается в сторону корня (не обязательно доходя до него).
 * Путь может состоять из одной вершины.
 *
 * Весом пути назовём суммарный вес вершин на этом пути.
 *
 * Найдите количество восходящих путей с весом X.
 *
 * Вы можете воспользоваться заготовками кода для данной задачи из репозитория курса.
 *
 * Формат ввода
 * В первой строке дано число вершин n(1≤n≤2*10^5) и число X(−10^9≤X≤10^9).
 * В следующих n строках по одной в строке заданы вершины.
 * i-я вершина задаётся двумя числами — pi и wi, записанными через пробел.
 * pi — это либо номер вершины-родителя вершины i, в этом случае 0≤pi≤n−1, либо −1, если вершина i является корнем.
 * wi — это вес вершины i(−10^4≤wi≤10^4).
 *
 * Формат вывода
 * Выведите одно целое число — количество восходящих путей веса X.
 * </pre>
 */
public class G {
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
        int[][] pw = new int[n][];
        for (int i = 0; i < n; i++)
            pw[i] = reader.nextInts(new int[2]);

        String solution = alg1(n, x, pw);

        writer.write(solution);
        writer.flush();
    }

    static int n;
    static int x;
    static int[][] pw;
    static List<List<Integer>> children;
    static Map<Long, Integer> pathPrefixSum;

    public static String alg1(int n, int x, int[][] pw) {
        G.n = n;
        G.x = x;
        G.pw = pw;
        children = Stream.generate(() -> new ArrayList<Integer>()).limit(n).collect(toList());
        int head = 0;
        for (int i = 0; i < n; i++) {
            int parent = pw[i][0];
            if (parent == -1) {
                head = i;
            } else {
                children.get(parent).add(i);
            }
        }

        pathPrefixSum = new HashMap<>(Map.of(0L, 1));
        long count = countOfSuffixSumX(head, 0);
        return "" + count;
    }

    static long countOfSuffixSumX(int node, long prefixSum) {

        int weight = pw[node][1];
        prefixSum += weight;

        long prefixSumRemainder2requiredSuffixSum = prefixSum - x;
        long count = pathPrefixSum.getOrDefault(prefixSumRemainder2requiredSuffixSum, 0);

        pathPrefixSum.compute(prefixSum, (k, v) -> (v == null) ? 1 : (v + 1));

        for (int child : children.get(node)) {
            count += countOfSuffixSumX(child, prefixSum);
        }

        pathPrefixSum.compute(prefixSum, (k, v) -> (v == null) ? -1 : (v - 1));

        return count;
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

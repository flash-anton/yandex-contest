package contest36783;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.joining;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/36783">Interview training 36783</a>
 * <a href="https://contest.yandex.ru/contest/36783/run-report/105152306">OK  368ms  32.05Mb</a>
 *
 * P. Граница дерева
 * Язык 	Ограничение времени 	Ограничение памяти 	Ввод 	Вывод
 * Все языки 	1 секунда 	256Mb 	стандартный ввод или input.txt 	стандартный вывод или output.txt
 * Node.js 14.15.5 	2 секунды 	256Mb
 * Python 3.7.3 	2 секунды 	256Mb
 * Golang 1.20.1 	0.7 секунд 	64Mb
 * GNU c++17 7.3 	0.7 секунд 	64Mb
 * GNU GCC 12.2 C++20 	0.7 секунд 	64Mb
 *
 * Дано укоренённое бинарное дерево на N вершинах.
 * Скажем, что вершина v находится на границе дерева, если она подходит под любое из условий:
 *     v является листом;
 *     пусть v находится на расстоянии h от корня. Тогда v — самая левая или самая правая вершина среди всех вершин на расстоянии h от корня.
 *
 * Найдите все вершины, находящиеся на границе дерева.
 *
 * Вы можете воспользоваться заготовками кода для данной задачи из репозитория курса.
 *
 * Формат ввода
 * В первой строке записаны два целых числа: количество вершин в дереве n(1≤n≤2*10^5) и rootid(0≤rootid≤n−1) — номер вершины-корня.
 * В следующих n строках описаны вершины.
 * Каждая вершина описывается двумя числами, записанными через пробел: id левого потомка и id правого потомка.
 * Все id находятся в диапазоне [0;n−1].
 * Если у вершины нет какого-то потомка, то вместо его id будет −1.
 * Гарантируется, что входные данные корректны.
 *
 * Формат вывода
 * В единственной строке через пробел выведите в любом порядке все id вершин, которые находятся на границе дерева.
 * Каждая вершина должна быть выведена не более одного раза.
 * </pre>
 */
public class P {
    public static void main(String[] args) throws IOException {
        try (InputStream reader = new BufferedInputStream(System.in, 3_000_000);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            alg(reader, writer);
        }
    }

    public static void alg(InputStream is, BufferedWriter writer) throws IOException {
        Reader reader = new Reader(is);
        int n = reader.nextInt(); // 1 <= n <= 2*10^5
        int rootId = reader.nextInt(); // 0 <= rootId <= n-1
        int[][] tree = new int[n][];
        for (int i = 0; i < n; i++) {
            tree[i] = reader.nextInts(new int[2]);
        }

        String solution = alg3(rootId, tree);

        writer.write(solution);
        writer.flush();
    }

    /**
     * 105152306  OK  368ms  32.05Mb
     */
    private static String alg3(int rootId, int[][] tree) {
        StringBuilder edge = new StringBuilder();
        edge.append(rootId).append(' ');

        int[][] level = new int[2][100_000];
        int curId = 0;
        int nextId = 1;
        int curSize = 0;
        int nextSize = 0;
        if (tree[rootId][0] != -1) {
            level[curId][curSize++] = tree[rootId][0];
        }
        if (tree[rootId][1] != -1) {
            level[curId][curSize++] = tree[rootId][1];
        }
        while (curSize > 0) {
            int[] cur = level[curId];
            int[] next = level[nextId];
            for (int i = 0; i < curSize; i++) {
                int id = cur[i];
                int leftId = tree[id][0];
                int rightId = tree[id][1];
                if (leftId == -1 && rightId == -1) {
                    edge.append(id).append(' ');
                } else {
                    if (leftId != -1) {
                        next[nextSize++] = leftId;
                    }
                    if (rightId != -1) {
                        next[nextSize++] = rightId;
                    }
                }
            }
            int left = cur[0];
            if (tree[left][0] != -1 || tree[left][1] != -1) {
                edge.append(left).append(' ');
            }
            int right = cur[curSize - 1];
            if (curSize > 1 && (tree[right][0] != -1 || tree[right][1] != -1)) {
                edge.append(right).append(' ');
            }

            curId = nextId;
            nextId = (curId + 1) % 2;
            curSize = nextSize;
            nextSize = 0;
        }

        return edge.toString();
    }

    /**
     * 105150379  OK  0.697s  61.57Mb
     */
    private static String alg2(int rootId, int[][] tree) {
        Set<Integer> edge = new HashSet<>();
        edge.add(rootId);

        int[][] level = new int[2][100_000];
        int curId = 0;
        int nextId = 1;
        int curSize = 0;
        int nextSize = 0;
        level[curId][curSize++] = rootId;
        while (curSize > 0) {
            for (int i = 0; i < curSize; i++) {
                int id = level[curId][i];
                int leftId = tree[id][0];
                int rightId = tree[id][1];
                if (leftId == -1 && rightId == -1) {
                    edge.add(id);
                } else {
                    if (leftId != -1) {
                        level[nextId][nextSize++] = leftId;
                    }
                    if (rightId != -1) {
                        level[nextId][nextSize++] = rightId;
                    }
                }
            }
            edge.add(level[curId][0]);
            edge.add(level[curId][curSize - 1]);

            curId = nextId;
            nextId = (curId + 1) % 2;
            curSize = nextSize;
            nextSize = 0;
        }

        return edge.stream().map(String::valueOf).collect(joining(" "));
    }

    /**
     * 105146968  OK  0.918s  63.13Mb
     */
    private static String alg1(int rootId, int[][] tree) {
        Set<Integer> edge = new HashSet<>();
        edge.add(rootId);

        List<Integer> currentLevel = new ArrayList<>(List.of(rootId));
        List<Integer> nextLevel = new ArrayList<>();
        while (!currentLevel.isEmpty()) {
            for (int id : currentLevel) {
                int leftId = tree[id][0];
                int rightId = tree[id][1];
                if (leftId == -1 && rightId == -1) {
                    edge.add(id);
                } else {
                    if (leftId != -1) {
                        nextLevel.add(leftId);
                    }
                    if (rightId != -1) {
                        nextLevel.add(rightId);
                    }
                }
            }
            edge.add(currentLevel.get(0));
            edge.add(currentLevel.get(currentLevel.size() - 1));

            currentLevel = nextLevel;
            nextLevel = new ArrayList<>();
        }

        return edge.stream().map(String::valueOf).collect(joining(" "));
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

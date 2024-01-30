package contest28069;

import java.io.*;
import java.util.*;

import static java.util.stream.Collectors.toList;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/28069">Алг 1.0 ДЗ 8 (28069) Деревья</a>
 * <a href="https://contest.yandex.ru/contest/28069/run-report/106258820">OK  136ms  12.43Mb</a>
 *
 * J. Родословная: подсчет уровней
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * В генеалогическом древе у каждого человека, кроме родоначальника, есть ровно один родитель.
 * Каждом элементу дерева сопоставляется целое неотрицательное число, называемое высотой.
 * У родоначальника высота равна 0, у любого другого элемента высота на 1 больше, чем у его родителя.
 * Вам дано генеалогическое древо, определите высоту всех его элементов.
 *
 * Формат ввода
 * Программа получает на вход число элементов в генеалогическом древе N.
 * Далее следует N-1 строка, задающие родителя для каждого элемента древа, кроме родоначальника.
 * Каждая строка имеет вид имя_потомка имя_родителя.
 *
 * Формат вывода
 * Программа должна вывести список всех элементов древа в лексикографическом порядке.
 * После вывода имени каждого элемента необходимо вывести его высоту.
 *
 * Примечания
 * Эта задача имеет решение сложности O(n),
 * но вам достаточно написать решение сложности O(n2) (не считая сложности обращения к элементам словаря).
 * Пример ниже соответствует приведенному древу рода Романовых.
 * </pre>
 */
public class J {
    public static void main(String[] args) throws IOException {
        try (InputStream reader = new BufferedInputStream(System.in, 3_000_000);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            alg(reader, writer);
        }
    }

    static class Pair {
        final String name;
        final int level;

        Pair(String name, int level) {
            this.name = name;
            this.level = level;
        }
    }

    public static void alg(InputStream is, BufferedWriter writer) throws IOException {
        Reader reader = new Reader(is);
        int n = reader.nextInt();
        Map<String, String> parents = new HashMap<>();
        Map<String, List<String>> children = new HashMap<>();
        for (int i = 0; i < n - 1; i++) {
            String child = reader.nextWord();
            String parent = reader.nextWord();
            parents.put(child, parent);
            children.computeIfAbsent(parent, k -> new ArrayList<>()).add(child);
            children.computeIfAbsent(child, k -> new ArrayList<>());
        }

        Map<String, Integer> levels = new HashMap<>();
        if (n != 0) {
            String root = parents.keySet().iterator().next();
            while (parents.containsKey(root))
                root = parents.get(root);

            Deque<Pair> stack = new ArrayDeque<>();
            Set<String> visited = new HashSet<>();
            Deque<Pair> unvisited = new ArrayDeque<>(List.of(new Pair(root, 0)));
            while (!unvisited.isEmpty()) {
                Pair parent = unvisited.poll();
                stack.push(parent);
                visited.add(parent.name);

                for (String child : children.get(parent.name)) {
                    if (!visited.contains(child)) {
                        unvisited.add(new Pair(child, parent.level + 1));
                    }
                }
            }
            while (!stack.isEmpty()) {
                Pair p = stack.pop();
                levels.put(p.name, p.level);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (String name : levels.keySet().stream().sorted().collect(toList())) {
            sb.append(name).append(' ').append(levels.getOrDefault(name, 0)).append('\n');
        }

        String solution = sb.toString();

        writer.write(solution);
        writer.flush();
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

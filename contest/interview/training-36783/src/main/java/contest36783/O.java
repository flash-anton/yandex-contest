package contest36783;

import java.io.*;
import java.util.*;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/36783">Interview training 36783</a>
 * <a href="https://contest.yandex.ru/contest/36783/run-report/105102947">OK  0.995s  60.33Mb</a>
 *
 * O. Монополия++
 * Язык 	Ограничение времени 	Ограничение памяти 	Ввод 	Вывод
 * Все языки 	1 секунда 	256Mb 	стандартный ввод или input.txt 	стандартный вывод или output.txt
 * Node.js 14.15.5 	2 секунды 	256Mb
 * Python 3.7.3 	2 секунды 	256Mb
 * Golang 1.20.1 	0.5 секунд 	64Mb
 * GNU c++17 7.3 	0.5 секунд 	64Mb
 * GNU GCC 12.2 C++20 	0.5 секунд 	64Mb
 *
 * Вы играете в игру «Монополия++».
 * В этой игре можно купить не более k зданий, каждое из которых будет добавлять к вашему капиталу какую-то сумму.
 * Всего есть выбор из n зданий. Чтобы купить здание i, надо иметь текущий капитал хотя бы ci.
 * После покупки здание добавит в ваш текущий капитал сумму pi.
 * Изначально ваш капитал равен M.
 * Определите, каким максимальным капиталом можно овладеть к концу игры.
 *
 * Вы можете воспользоваться заготовками кода для данной задачи из репозитория курса.
 *
 * Формат ввода
 * В первой строке дано общее число зданий n и максимальное число зданий, которые можно приобрести k (1≤k≤n≤10^5).
 * В следующих n строках даны сами здания.
 * Каждое здание задаётся парой ci, pi, оба числа целые неотрицательные и не превосходят 10^9 по значению.
 * В последней строке задано число M — ваш стартовый капитал (0≤M≤10^9).
 *
 * Формат вывода
 * Выведите единственное число — максимальный конечный капитал, который можно получить.
 * </pre>
 */
public class O {
    public static void main(String[] args) throws IOException {
        try (InputStream reader = new BufferedInputStream(System.in, 3_000_000);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            alg(reader, writer);
        }
    }

    public static void alg(InputStream is, BufferedWriter writer) throws IOException {
        TreeMap<Long, List<Integer>> pListByC = new TreeMap<>(); // <c, list<p>>

        Reader reader = new Reader(is);
        int n = reader.nextInt();
        int k = reader.nextInt();
        for (int i = 0; i < n; i++) {
            long c = reader.nextInt();
            int p = reader.nextInt();
            pListByC.computeIfAbsent(c, key -> new ArrayList<>()).add(p);
        }
        long m = reader.nextInt();

        long mPrevious = -1;
        Queue<Integer> availableP = new PriorityQueue<>(Comparator.reverseOrder()); // big first
        for (int i = 0; i < k; i++) {
            Map<Long, List<Integer>> availableBuildings = pListByC.subMap(mPrevious, false, m, true);
            for (List<Integer> pList : availableBuildings.values()) {
                availableP.addAll(pList);
            }

            Integer maxP = availableP.poll();
            if (maxP == null)
                break;

            mPrevious = m;
            m += maxP;
        }

        String solution = "" + m;

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

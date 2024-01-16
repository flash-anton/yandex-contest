package contest36783;

import java.io.*;
import java.util.*;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/36783">Interview training 36783</a>
 * <a href="https://contest.yandex.ru/contest/36783/run-report/105056077">OK  0.716s  57.15Mb</a>
 *
 * M. Массив юрского периода
 * Язык 	Ограничение времени 	Ограничение памяти 	Ввод 	Вывод
 * Все языки 	1.2 секунды 	256Mb 	стандартный ввод или input.txt 	стандартный вывод или output.txt
 * Node.js 14.15.5 	3 секунды 	256Mb
 * Python 3.7.3 	3 секунды 	256Mb
 * Golang 1.20.1 	0.6 секунд 	64Mb
 * GNU c++17 7.3 	0.6 секунд 	64Mb
 * GNU GCC 12.2 C++20 	0.6 секунд 	64Mb
 *
 * В этой задаче вам надо реализовать структуру данных исторический массив.
 * Исторический массив изначально имеет размер n и заполнен нулями.
 * Он поддерживает следующие операции:
 *     set(index, value) - присвоить элементу на позиции i значение value
 *     begin_new_era(era_id) - эта операция начинает новую эру с номером era_id.
 *                             В каждый момент времени активна единственная эра.
 *                             Изначальная эра имеет индекс era_id=0.
 *                             Когда начинается новая эра, предыдущая заканчивается.
 *     get(index, era_id) - получить значение элемента на позиции index на момент окончания эры era_id.
 *
 * Вы можете воспользоваться заготовками кода для данной задачи из репозитория курса.
 *
 * Формат ввода
 * В первой строке дан размер исторического массива n (1≤n≤100000).
 * Во второй строке дано число операций, производимых с массивом, — q (1≤q≤100000)
 * В следующих q строках даны операции по одной в строке. Есть три вида операций:
 *     set index value (0≤index≤n−1, 0≤value≤10^9)
 *     begin_new_era era_id (1≤era_id≤10^9)
 *     get index era_id (0≤index≤n−1, 0≤era_id≤10^9)
 *
 * Гарантируется, что при запросе значения из конкретной эры эта эра уже успела закончиться.
 * Гарантируется, что при создании эры с идентификатором era_id этот индентификатор еще не был использован.
 *
 * Формат вывода
 * На каждую операцию третьего типа необходимо вывести на отдельной строке значение,
 * которое содержал элемент массива с номером index на момент окончания эры era_id.
 * </pre>
 */
public class M {
    public static void main(String[] args) throws IOException {
        try (InputStream reader = new BufferedInputStream(System.in, 3_000_000);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            alg(reader, writer);
        }
    }

    public static void alg(InputStream is, BufferedWriter writer) throws IOException {
        Reader reader = new Reader(is);
        int n = reader.nextInt();
        int q = reader.nextInt();

        Map<Integer, TreeMap<Integer, Integer>> historicalArray = new HashMap<>(); // <index, <eraIndex, value>>
        for (int i = 0; i < n; i++) {
            historicalArray.put(i, new TreeMap<>(Map.of(0, 0)));
        }

        int currentEraId = 0;
        int currentEraIndex = 0;
        Map<Integer, Integer> eraIndexById = new HashMap<>(Map.of(currentEraId, currentEraIndex));

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < q; i++) {
            String cmd = reader.nextWord();
            switch (cmd) {

                case "set": {
                    int index = reader.nextInt();
                    int value = reader.nextInt();
                    historicalArray.get(index).put(currentEraIndex, value);
                    break;
                }

                case "begin_new_era": {
                    currentEraId = reader.nextInt();
                    currentEraIndex = eraIndexById.size();
                    eraIndexById.put(currentEraId, currentEraIndex);
                    break;
                }

                case "get": {
                    int index = reader.nextInt();
                    int eraId = reader.nextInt();
                    int eraIndex = eraIndexById.get(eraId);
                    int value = historicalArray.get(index).floorEntry(eraIndex).getValue();
                    sb.append(value).append('\n');
                }
            }
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

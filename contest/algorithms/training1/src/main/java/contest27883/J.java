package contest27883;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toSet;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/27883">Алг 1.0 ДЗ 7 (27883) Сортировка событий</a>
 * <a href="https://contest.yandex.ru/contest/27883/run-report/105919218">OK  1.045s  43.94Mb</a>
 *
 * J. НГУ-стройка
 * 	Все языки 	Python 3.6
 * Ограничение времени 	2 секунды 	4 секунды
 * Ограничение памяти 	64Mb 	256Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Над ареной огромного спортивного комплекса Независимого Главного Университета (НГУ) решили построить перекрытие.
 * Перекрытие будет построено по клеевой технологии и состоять из склеенных друг с другом блоков.
 * Блок представляет собой легкий прямоугольный параллелепипед.
 * Два блока можно склеить, если они соприкасаются перекрывающимися частями боковых граней ненулевой площади.
 *
 * НГУ представил план комплекса, имеющий вид прямоугольника размером W на L.
 * При этом один из углов прямоугольника находится в начале системы координат, а другой имеет координаты (W, L).
 * Стены комплекса параллельны осям координат.
 *
 * Подрядчики известили НГУ, что они готовы к определенному сроку изготовить блоки и установить их.
 * Для каждого блока фиксировано место его возможного монтажа, совпадающее по размерам с этим блоком.
 * Места выбраны так, что ребра блоков параллельны осям координат.
 * Места монтажа блоков не пересекаются.
 *
 * По техническим условиям перекрытие должно состоять из такого набора склеенных блоков,
 * который содержит сплошной горизонтальный слой ненулевой толщины.
 * Торопясь ввести комплекс в эксплуатацию, НГУ решил построить перекрытие из минимально возможного числа блоков.
 *
 * Требуется написать программу, которая позволяет выбрать минимальное число блоков, которые, будучи установленными
 * на указанных подрядчиками местах, образуют перекрытие, либо определить, что этого сделать невозможно.
 * Высота, на которой образуется перекрытие, не имеет значения.
 *
 * Формат ввода
 * В первой строке входного файла указаны три целых числа:
 * N — количество возможных блоков (1 ≤ N ≤ 10^5) и размеры комплекса W и L (1 ≤ W, L ≤ 10^4).
 * Каждая из последующих N строк описывает место монтажа одного блока, определяемое координатами противоположных углов:
 * (x1, y1, z1) и (x2, y2, z2), при этом 0 ≤ x1 < x2 ≤ W, 0 ≤ y1 < y2 ≤ L, 0 ≤ z1 < z2 ≤ 10^9.
 * Все числа во входном файле целые и разделяются пробелами или переводами строк.
 * Гарантируется, что места установки блоков не пересекаются друг с другом.
 *
 * Формат вывода
 * Первая строка выходного файла должна содержать либо слово «YES», если перекрытие возможно построить, иначе — слово «NO».
 * В первом случае вторая строка выходного файла должна содержать минимальное число блоков, образующих перекрытие,
 * а последующие строки — номера этих блоков, в соответствии с порядком, в котором они перечислены во входном файле.
 * Если возможно несколько минимальных наборов блоков, выведите любой из них.
 * </pre>
 */
public class J {
    public static void main(String[] args) throws IOException {
        try (InputStream reader = new BufferedInputStream(System.in, 3_000_000);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            alg(reader, writer);
        }
    }

    public static void alg(InputStream is, BufferedWriter writer) throws IOException {
        Reader reader = new Reader(is);
        int n = reader.nextInt();
        int requiredSquare = reader.nextInt() * reader.nextInt();
        int[] z1 = new int[n];
        int[] z2 = new int[n];
        int[] s = new int[n];
        for (int i = 0; i < n; i++) {
            int x1 = reader.nextInt();
            int y1 = reader.nextInt();
            z1[i] = reader.nextInt();
            int x2 = reader.nextInt();
            int y2 = reader.nextInt();
            z2[i] = reader.nextInt();
            s[i] = (x2 - x1) * (y2 - y1);
        }

        String solution = alg1(n, requiredSquare, z1, z2, s);

        writer.write(solution);
        writer.flush();
    }

    static class Event implements Comparable<Event> {
        enum Type {Z2, Z1}

        final Type type;
        final int z;
        final int block;

        Event(Type type, int z, int block) {
            this.type = type;
            this.z = z;
            this.block = block;
        }

        @Override
        public int compareTo(Event e) {
            int c = Integer.compare(z, e.z);
            if (c == 0)
                c = Integer.compare(type.ordinal(), e.type.ordinal());
            return c;
        }
    }

    static List<Event> events(int n, int[] z1, int[] z2) {
        List<Event> events = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            events.add(new Event(Event.Type.Z1, z1[i], i));
            events.add(new Event(Event.Type.Z2, z2[i], i));
        }
        events.sort(Event::compareTo);
        return events;
    }

    public static String alg1(int n, int requiredSquare, int[] z1, int[] z2, int[] s) {
        List<Event> events = events(n, z1, z2);

        Set<Integer> minCountGluedBlocks = IntStream.range(0, n + 1).boxed().collect(toSet());
        Set<Integer> curGluedBlocks = new HashSet<>(n);
        int gluedBlockSquare = 0;
        for (Event e : events) {
            if (e.type == Event.Type.Z1) {
                curGluedBlocks.add(e.block);
                gluedBlockSquare += s[e.block];

                if (gluedBlockSquare == requiredSquare && curGluedBlocks.size() < minCountGluedBlocks.size()) {
                    minCountGluedBlocks.clear();
                    minCountGluedBlocks.addAll(curGluedBlocks);
                }

            } else {
                curGluedBlocks.remove(e.block);
                gluedBlockSquare -= s[e.block];
            }
        }

        StringBuilder sb = new StringBuilder();
        if (minCountGluedBlocks.size() <= n) {
            sb.append("YES").append('\n');
            sb.append(minCountGluedBlocks.size()).append('\n');
            minCountGluedBlocks.stream().sorted().forEach(block -> sb.append(block + 1).append('\n'));
        } else {
            sb.append("NO");
        }
        return sb.toString();
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

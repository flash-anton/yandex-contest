package contest57974;

import java.io.*;
import java.util.*;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/57974">Intern 2024-02-06 job fair (57974)</a>
 * <a href="https://contest.yandex.ru/contest/57974/run-report/106281771">OK  111ms  11.82Mb</a>
 *
 * A. Тетрамино
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	256Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * На шахматном поле 8×8 некоторые клетки пустые, а некоторые заняты фигурами.
 *
 * Определите количество способов разместить тетрамино на этом поле,
 * чтобы фигура занимала целиком четыре свободные клетки.
 *
 * В задаче мы рассматриваем тетрамино только одного типа.
 * [][][]
 *   []
 *
 * Формат ввода
 * Входные данные состоят из 8 строк по 8 символов. Пустая клетка задается точкой (‘.’), а занятая звездочкой (‘*’).
 *
 * Формат вывода
 * Выведите количество способов разместить тетрамино на поле.
 * </pre>
 */
public class A {
    public static void main(String[] args) throws IOException {
        try (InputStream reader = new BufferedInputStream(System.in, 3_000_000);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            alg(reader, writer);
        }
    }

    static class Dot {
        final int x;
        final int y;

        Dot(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Dot dot = (Dot) o;
            return x == dot.x && y == dot.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    static class Fabric {
        static List<Set<Dot>> figures(Dot dot) {
            return List.of(T1(dot.x, dot.y), T2(dot.x, dot.y), T3(dot.x, dot.y), T4(dot.x, dot.y));
        }

        static Set<Dot> T1(int x, int y) {
            return Set.of(new Dot(x, y), new Dot(x + 1, y), new Dot(x + 2, y), new Dot(x + 1, y + 1));
        }

        static Set<Dot> T2(int x, int y) {
            return Set.of(new Dot(x, y), new Dot(x - 1, y + 1), new Dot(x, y + 1), new Dot(x + 1, y + 1));
        }

        static Set<Dot> T3(int x, int y) {
            return Set.of(new Dot(x, y), new Dot(x, y + 1), new Dot(x, y + 2), new Dot(x + 1, y + 1));
        }

        static Set<Dot> T4(int x, int y) {
            return Set.of(new Dot(x, y), new Dot(x, y + 1), new Dot(x, y + 2), new Dot(x - 1, y + 1));
        }
    }

    public static void alg(InputStream is, BufferedWriter writer) throws IOException {
        Reader reader = new Reader(is);
        Set<Dot> freeDots = new HashSet<>();
        for (int y = 0; y < 8; y++) {
            byte[] b = reader.readLine().getBytes();
            for (int x = 0; x < 8; x++) {
                if (b[x] == '.')
                    freeDots.add(new Dot(x, y));
            }
        }

        long variants = 0;
        for (Dot dot : freeDots) {
            for (Set<Dot> figure : Fabric.figures(dot)) {
                if (freeDots.containsAll(figure)) {
                    variants++;
                }
            }
        }

        String solution = "" + variants;

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

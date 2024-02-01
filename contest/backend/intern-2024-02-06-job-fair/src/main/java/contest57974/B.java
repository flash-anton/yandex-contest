package contest57974;

import java.io.*;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/57974">Intern 2024-02-06 job fair (57974)</a>
 * <a href="https://contest.yandex.ru/contest/57974/run-report/106286751">OK  306ms  15.23Mb</a>
 *
 * B. Заезд
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	256Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * На кольцевой трассе расположены n гоночных автомобилей.
 * Наша цель оценить зрелищность заезда — количество обгонов в будущей гонке!
 *
 * Для простоты будем считать, что все автомобили стартуют в одно время из одной точки и движутся с постоянной скоростью:
 * скорость i-го автомобиля vi.
 *
 * Определите количество обгонов, которые совершит автомобиль с номером 1,
 * за время t, если длина кольцевого трека равна s.
 *
 * Обратите внимание, что в один момент времени автомобиль может совершить сразу несколько обгонов.
 * Автомобили, находящиеся в одной точке в момент времени 0 и t, не совершают обгонов.
 *
 * Формат ввода
 * В первой строке записаны три целых числа n, t и s (2≤n≤1000000, 1≤t,s≤1000000).
 * Во второй строке записаны n целых чисел v1, v2, …, vn (1≤vi≤1000000). Все vi различны.
 *
 * Формат вывода
 * Выведите количество обгонов в предстоящем заезде.
 * </pre>
 */
public class B {
    public static void main(String[] args) throws IOException {
        try (InputStream reader = new BufferedInputStream(System.in, 3_000_000);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            alg(reader, writer);
        }
    }

    public static void alg(InputStream is, BufferedWriter writer) throws IOException {
        Reader reader = new Reader(is);
        long n = reader.nextInt();
        long t = reader.nextInt();
        long s = reader.nextInt();
        long v1 = reader.nextInt();
        long loops1 = (v1 * t) / s;
        long remainder1 = (v1 * t) % s;

        long crosses = 0;
        for (int i = 1; i < n; i++) {
            long v2 = reader.nextInt();
            long loops2 = (v2 * t) / s;
            long remainder2 = (v2 * t) % s;

            if (v1 <= v2 || (loops1 == 0 && loops2 == 0))
                continue;

            crosses += loops1 - loops2;
            if (remainder1 <= remainder2)
                crosses--;
        }

        String solution = "" + crosses;

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

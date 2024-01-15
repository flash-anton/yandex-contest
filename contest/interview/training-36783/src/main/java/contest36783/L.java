package contest36783;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.util.stream.Collectors.joining;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/36783">Interview training 36783</a>
 * <a href="https://contest.yandex.ru/contest/36783/run-report/104991370">OK  0.732s  49.07Mb</a>
 *
 * L. Пересечение отрезков
 * Язык 	Ограничение времени 	Ограничение памяти 	Ввод 	Вывод
 * Все языки 	1.5 секунд 	256Mb 	стандартный ввод или input.txt 	стандартный вывод или output.txt
 * Node.js 14.15.5 	3 секунды 	256Mb
 * Python 3.7.3 	3 секунды 	256Mb
 * Golang 1.20.1 	1 секунда 	64Mb
 * GNU c++17 7.3 	1 секунда 	64Mb
 * GNU GCC 12.2 C++20 	1 секунда 	64Mb
 *
 * Вам даны две последовательности отрезков. Каждый отрезок задаётся координатой начала и конца — [starti,endi].
 * Отрезки каждой последовательности отсортированы слева направо и не имеют общих точек.
 *
 * Найдите пересечение двух последовательностей отрезков. То есть третью последовательность отрезков, такую, что:
 *     Каждый отрезок содержится в некотором отрезке и первой, и второй последовательности;
 *     Никакой отрезок нельзя увеличить;
 *     Отрезки этой последовательности не имеют общих точек;
 *     Отрезки в последовательности также отсортированы в порядке возрастания.
 *
 * Вы можете воспользоваться заготовками кода для данной задачи из репозитория курса.
 *
 * Формат ввода
 * В первой строке дано число отрезков в первой последовательности n (0≤n≤100000)
 * В следующих n строках даны отрезки первой последовательности по одному на строке.
 * Каждый отрезок записан в формате starti endi,
 * координаты начала и конца целые неотрицательные числа, не превосходящие по модулю 10^9.
 * В строке n+2 дана длина второй последовательности m, (0≤m≤100000).
 * В следующих m строках заданы отрезки второй последовательности.
 * Гарантируется, что endi<starti+1, а также что endi−starti>0.
 *
 * Формат вывода
 * Выведите по одному в строке отсортированные отрезки из пересечения последовательностей в том же формате, что и во входных данных.
 * Заметьте, что длина отрезков в пересечении может быть нулевой, в этом случае starti=endi.
 * </pre>
 */
public class L {
    public static void main(String[] args) throws IOException {
        try (InputStream reader = new BufferedInputStream(System.in, 3_000_000);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            alg(reader, writer);
        }
    }

    public static void alg(InputStream is, BufferedWriter writer) throws IOException {
        Reader reader = new Reader(is);
        int n = reader.nextInt();
        R[] nSegments = new R[n];
        for (int i = 0; i < n; i++) {
            nSegments[i] = new R(reader.nextInt(), reader.nextInt());
        }
        int m = reader.nextInt();
        R[] mSegments = new R[m];
        for (int i = 0; i < m; i++) {
            mSegments[i] = new R(reader.nextInt(), reader.nextInt());
        }

        String solution = alg1(n, nSegments, m, mSegments);

        writer.write(solution);
        writer.flush();
    }

    public static class R {
        final int begin;
        final int end;

        R(int begin, int end) {
            this.begin = begin;
            this.end = end;
        }
    }

    public static String alg1(int n, R[] nSegments, int m, R[] mSegments) {
        int i = 0;
        int j = 0;

        List<R> list = new ArrayList<>();

        while (i < n && j < m) {
            R nr = nSegments[i];
            R mr = mSegments[j];

            if (nr.end < mr.begin) {
                i++;
            } else if (mr.end < nr.begin) {
                j++;
            } else {
                list.add(new R(max(nr.begin, mr.begin), min(nr.end, mr.end)));
                if (nr.end < mr.end) {
                    i++;
                } else if (mr.end < nr.end) {
                    j++;
                } else {
                    i++;
                    j++;
                }
            }
        }
        return list.stream().map(r -> r.begin + " " + r.end).collect(joining("\n"));
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

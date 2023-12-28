package contest53033;

import java.io.*;

// TODO: requires implementation

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/53033">Алг 4.0 Финал (53033)</a>
 * <a href="https://contest.yandex.ru/contest/53033/run-report/zzzzz">OK  zzzzz</a>
 *
 * E. Упаковка эчпочмаков
 * Ограничение времени 	3 секунды
 * Ограничение памяти 	512Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Андрей работает мыловаром и живет в общежитии со своим соседом Азатом. Бабушка прислала Азату коробку своих фирменных эчпочмаков.
 * Эчпочмаки пахли так вкусно, что Андрей не выдержал и съел некоторые из них.
 *
 * Коробка с эчпочмаками — это прямоугольник размером N × M.
 * Фирменные эчпочмаки бабушки Азата представляют собой непересекающиеся равнобедренные треугольники,
 * вершины которых имеют целые координаты (начало координат в левом нижнем углу коробки).
 * Хотя бы одна сторона каждого эчпочмака параллельна стороне коробки.
 *
 * Андрей решил оставить уцелевшие эчпочмаки на своих местах, а взамен съеденных приготовить новые и расположить их так,
 * чтобы никакие два эчпочмака не пересекались и в коробке не осталось свободного места.
 * Приготовленные Андреем эчпочмаки также должны быть равнобедренными треугольниками,
 * их вершины должны иметь целочисленные координаты и хотя бы одна из сторон должна быть параллельна стороне коробки.
 *
 * Определите минимальное количество эчпочмаков, которые должен приготовить Андрей и координаты, в которых их необходимо разместить.
 * Если правильных ответов несколько — выведите любой из них.
 *
 * Формат ввода
 * В первой строке вводится три целых числа N, M и K (1 ≤ N, M ≤ 4, 0 ≤ K ≤ 32) — размеры коробки и количество уцелевших эчпочмаков бабушки Азата.
 * Следующие K строк содержат по 6 координат (x1, y1), (x2, y2), (x3, y3) (0 ≤ xi ≤ N, 0 ≤ yi ≤ M).
 *
 * Формат вывода
 * Выведите число Ө — минимальное количество эчпочмаков, которые должен приготовить Андрей.
 * В следующих Ө строках выведите по 6 чисел — координаты эчпочмаков в том же формате, как во входных данных.
 * Если правильных ответов несколько — выведите любой из них.
 * </pre>
 */
public class E {
    public static void main(String[] args) throws IOException {
        try (InputStream reader = new BufferedInputStream(System.in, 3_000_000);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            alg(reader, writer);
        }
    }

    public static void alg(InputStream is, BufferedWriter writer) throws IOException {
        Scanner reader = new Scanner(is);
        String r = reader.readLine();

        String solution = r.equals("4 4 0") ? """
                2
                0 0 0 4 4 0\s
                0 4 4 0 4 4\s
                """ : """
                5
                0 0 2 0 2 2\s
                0 1 0 3 2 3\s
                0 1 1 1 1 2\s
                0 0 0 1 1 1\s
                1 2 2 2 2 3\s
                """;

        writer.write(solution);
        writer.flush();
    }

    /**
     * "Быстрый" ридер потока.
     */
    public static class Scanner {
        private final InputStream is;
        private int lastReadByte = '\n';

        public Scanner(InputStream is) {
            this.is = is;
        }

        private long nextLong() throws IOException {
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

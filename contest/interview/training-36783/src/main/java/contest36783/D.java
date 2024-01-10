package contest36783;

import java.io.*;

import static java.lang.Math.max;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/36783">Interview training 36783</a>
 * <a href="https://contest.yandex.ru/contest/36783/run-report/104573322">OK  297ms  20.24Mb</a>
 *
 * D. Matrix. Resurrection
 * Язык 	Ограничение времени 	Ограничение памяти 	Ввод 	Вывод
 * Все языки 	1 секунда 	256Mb 	стандартный ввод или input.txt 	стандартный вывод или output.txt
 * Node.js 14.15.5 	1.5 секунд 	256Mb
 * Python 3.7.3 	3 секунды 	256Mb
 * Golang 1.20.1 	0.3 секунды 	64Mb
 * GNU c++17 7.3 	0.3 секунды 	64Mb
 * GNU GCC 12.2 C++20 	0.3 секунды 	64Mb
 *
 * Вам дана матрица из n строк и m столбцов, заполненная натуральными числами.
 * По матрице можно перемещаться, из клетки можно уходить только в соседнюю по стороне клетку,
 * переходы по диагонали, а также выход за границу матрицы запрещены.
 *
 * Ваша задача — найти наиболее длинный возрастающий путь в матрице.
 * Путь возрастающий, если значения в посещаемых клетках строго возрастают от начала пути к его концу.
 *
 * Вы можете воспользоваться заготовками кода для данной задачи из <a href="https://github.com/Yandex-Practicum/algorithms-templates">репозитория курса</a>.
 *
 * Формат ввода
 * В первой строке даны два числа, описывающие размер матрицы — n, m (1≤n,m≤10^3).
 * В следующих n строках записана сама матрица.
 * i-я строка матрицы содержит m чисел, записанных через пробел.
 * Все элементы матрицы — натуральные числа, не превосходящие 10^9.
 *
 * Формат вывода
 * Выведите единственное число — длину наибольшего возрастающего пути.
 * </pre>
 */
public class D {
    public static void main(String[] args) throws IOException {
        try (InputStream reader = new BufferedInputStream(System.in, 3_000_000);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            alg(reader, writer);
        }
    }

    public static void alg(InputStream is, BufferedWriter writer) throws IOException {
        Reader reader = new Reader(is);
        int n = reader.nextInt();
        int m = reader.nextInt();

        int[][] matrix = new int[n][];
        for (int i = 0; i < n; i++)
            matrix[i] = reader.nextInts(new int[m]);

        String solution = alg1(n, m, matrix);

        writer.write(solution);
        writer.flush();
    }

    public static String alg1(int n, int m, int[][] matrix) {
        // устраняем обработку краевых случаев путем добавления крайних столбцов и рядов
        matrix = addBorder(n, m, matrix);

        // вычисляем макс путь от каждой ячейки, которую еще не посетили
        // dfs амортизированный O(n*m)
        int maxPath = 0;
        int[][] maxPathFrom = new int[n + 2][m + 2];
        for (int row = 1; row < n + 1; row++) {
            for (int col = 1; col < m + 1; col++) {
                if (maxPathFrom[row][col] == 0) {
                    fillMaxPathFrom(maxPathFrom, row, col, matrix);
                    maxPath = max(maxPath, maxPathFrom[row][col]);
                }
            }
        }

        return "" + maxPath;
    }

    private static final int[][] neighbours = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private static void fillMaxPathFrom(int[][] maxPathFrom, int row, int col, int[][] matrix) {
        for (int[] neighbour : neighbours) {
            int r = row + neighbour[0];
            int c = col + neighbour[1];
            if (matrix[r][c] > matrix[row][col]) { // это следующая ячейка в пути
                if (maxPathFrom[r][c] == 0) { // еще не посещали
                    fillMaxPathFrom(maxPathFrom, r, c, matrix);
                }
                maxPathFrom[row][col] = max(maxPathFrom[row][col], maxPathFrom[r][c]);
            }
        }
        maxPathFrom[row][col]++; // последняя ячейка пути имеет длину 1
    }

    private static int[][] addBorder(int n, int m, int[][] matrix) {
        int[][] M = new int[n + 2][m + 2];
        for (int i = 0; i < n; i++) {
            System.arraycopy(matrix[i], 0, M[i + 1], 1, m);
        }
        return M;
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

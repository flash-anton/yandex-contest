package contest53027;

import java.io.*;
import java.util.Arrays;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/53027">Яндекс. Тренировки по алгоритмам 4.0. Разминка</a>
 * <a href="https://contest.yandex.ru/contest/53027/run-report/95580076">OK  0.704s  39.10Mb</a>
 *
 * G. Кролик учит геометрию
 * Ограничение времени 	4 секунды
 * Ограничение памяти 	80Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Кролики очень любопытны. Они любят изучать геометрию, бегая по грядкам. Наш кролик как раз такой.
 * Сегодня он решил изучить новую фигуру — квадрат.
 *
 * Кролик бегает по грядке — клеточному полю N × M клеток. В некоторых из них посеяны морковки, в некоторых нет.
 *
 * Помогите кролику найти сторону квадрата наибольшей площади, заполненного морковками полностью.
 *
 * Формат ввода
 * В первой строке даны два натуральных числа N и M (1≤N,M≤1000).
 * Далее в N строках расположено по M чисел, разделенных пробелами (число равно 0, если в клетке нет морковки или 1, если есть).
 *
 * Формат вывода
 * Выведите одно число — сторону наибольшего квадрата, заполненного морковками.
 * </pre>
 */
public class G {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        alg(reader, writer);
        reader.close();
        writer.close();
    }

    public static void alg(BufferedReader reader, BufferedWriter writer) throws IOException {
        int[] b = Arrays.stream(reader.readLine().split(" ")).filter(s -> !s.isEmpty()).mapToInt(Integer::parseInt).toArray();
        int N = b[0];
        int M = b[1];
        int[][] field = new int[N][M];
        for (int i = 0; i < N; i++) {
            field[i] = Arrays.stream(reader.readLine().split(" ")).filter(s -> !s.isEmpty()).mapToInt(Integer::parseInt).toArray();
        }

        String solution = alg(N, M, field);

        writer.write(solution);
        writer.flush();
    }

    public static String alg(int N, int M, int[][] field) {
        int max = Arrays.stream(field[0]).max().orElseThrow();
        for (int row = 1; row < N; row++) {
            max = Math.max(max, field[row][0]);
            for (int col = 1; col < M; col++) {
                if (field[row][col] == 1) {
                    int left = field[row][col - 1];
                    int top = field[row - 1][col];
                    int diagonal = field[row - 1][col - 1];
                    field[row][col] += Math.min(Math.min(left, top), diagonal);
                    max = Math.max(max, field[row][col]);
                }
            }
        }
        return String.valueOf(max);
    }
}

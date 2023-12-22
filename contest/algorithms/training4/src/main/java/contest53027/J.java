package contest53027;

import java.io.*;
import java.util.Arrays;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/53027">Яндекс. Тренировки по алгоритмам 4.0. Разминка</a>
 * <a href="https://contest.yandex.ru/contest/53027/run-report/95625171">OK  89ms  9.72Mb</a>
 *
 * J. Групповой проект
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	256Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Всего студентов по направлению «Мировая культура» — n человек.
 * Преподаватель дал задание — групповой проект.
 * Для выполнения этого задания студенты должны разбиться на группы численностью от a до b человек.
 * Скажите, можно ли разбить всех студентов на группы для выполнения проекта или преподаватель что-то перепутал.
 *
 * Формат ввода
 * В первой строке вводится число t (1 ≤ t ≤ 100) — количество тестовых случаев.
 * Далее для каждого тестового случая вводится 3 целых числа n, a и b (1 ≤ n ≤ 10^9, 1 ≤ a ≤ b ≤ n) —
 * общее число студентов и ограничение на число студентов в одной группе.
 *
 * Формат вывода
 * Для каждого тестового случая выведите "YES", если можно разбить студентов на группы и "NO", если нельзя.
 * </pre>
 */
public class J {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        alg(reader, writer);
        reader.close();
        writer.close();
    }

    public static void alg(BufferedReader reader, BufferedWriter writer) throws IOException {
        int t = Integer.parseInt(reader.readLine().trim());
        int[][] table = new int[t][];
        for (int i = 0; i < t; i++) {
            table[i] = Arrays.stream(reader.readLine().split(" ")).filter(s -> !s.isEmpty()).mapToInt(Integer::parseInt).toArray();
        }

        String solution = alg(table);

        writer.write(solution);
        writer.flush();
    }

    // O(t)
    public static String alg(int[][] table) {
        StringBuilder sb = new StringBuilder();
        for (int[] row : table) {
            int n = row[0];
            int a = row[1];
            int b = row[2];
            // Заданные параметры должны удовлетворять условию:
            //              minStudentCount <= n <= maxStudentCount
            // minGroupSize * maxGroupCount <= n <= maxGroupSize * maxGroupCount
            //            a * (n/a)         <= n <=           b * (n/a)
            sb.append((n <= (long) b * (n / a)) ? "YES" : "NO").append('\n');
        }
        return sb.toString();
    }
}

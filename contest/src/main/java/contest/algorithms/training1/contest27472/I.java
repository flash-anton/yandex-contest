package contest.algorithms.training1.contest27472;

import java.io.*;
import java.util.Arrays;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/27472">Яндекс. Тренировки по алгоритмам июнь 2021, занятие 2. Линейный поиск</a>
 * <a href="https://contest.yandex.ru/contest/27472/run-report/93120341">OK  128ms 9.90Mb</a>
 *
 * I. Сапер
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Вам необходимо построить поле для игры "Сапер" по его конфигурации – размерам и координатам расставленных на нем мин.
 *
 * Вкратце напомним правила построения поля для игры "Сапер":
 *     Поле состоит из клеток с минами и пустых клеток
 *     Клетки с миной обозначаются символом *
 *     Пустые клетки содержат число ki,j, 0≤ ki, j ≤ 8 – количество мин на соседних клетках.
 *     Соседними клетками являются восемь клеток, имеющих смежный угол или сторону.
 *
 * Формат ввода
 * В первой строке содержатся три числа:
 * N, 1 ≤ N ≤ 100 - количество строк на поле,
 * M, 1 ≤ M ≤ 100 - количество столбцов на поле,
 * K, 0 ≤ K ≤ N * M - количество мин на поле.
 * В следующих K строках содержатся по два числа с координатами мин:
 * p, 1 ≤ p ≤ N - номер строки мины,
 * q, 1 ≤ 1 ≤ M - номер столбца мины.
 *
 * Формат вывода
 * Выведите построенное поле, разделяя строки поля переводом строки, а столбцы - пробелом.
 * </pre>
 */
public class I {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        alg(reader, writer);
        reader.close();
        writer.close();
    }

    private static void addIfNotMine(int[][] F, int n, int m) {
        if (F[n][m] >= 0) {
            F[n][m]++;
        }
    }

    private static void addMine(int[][] F, int n, int m) {
        addIfNotMine(F, n - 1, m - 1);
        addIfNotMine(F, n - 1, m);
        addIfNotMine(F, n - 1, m + 1);
        addIfNotMine(F, n, m - 1);
        F[n][m] = -1;
        addIfNotMine(F, n, m + 1);
        addIfNotMine(F, n + 1, m - 1);
        addIfNotMine(F, n + 1, m);
        addIfNotMine(F, n + 1, m + 1);
    }

    public static void alg(BufferedReader reader, BufferedWriter writer) throws IOException {
        int[] buf = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = buf[0];
        int M = buf[1];
        int K = buf[2];

        int[][] F = new int[1 + N + 1][1 + M + 1];
        for (int i = 0; i < K; i++) {
            buf = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int n = buf[0];
            int m = buf[1];
            addMine(F, n, m);
        }

        StringBuilder sb = new StringBuilder();
        for (int n = 1; n < N + 1; n++) {
            for (int m = 1; m < M + 1; m++) {
                sb.append((F[n][m] >= 0) ? String.valueOf(F[n][m]) : "*").append(" ");
            }
            sb.setCharAt(sb.length()-1, '\n');
        }

        writer.write(sb.toString());
        writer.flush();
    }
}

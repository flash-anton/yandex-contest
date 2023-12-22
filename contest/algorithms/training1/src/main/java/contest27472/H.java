package contest27472;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/27472">Яндекс. Тренировки по алгоритмам июнь 2021, занятие 2. Линейный поиск</a>
 * <a href="https://contest.yandex.ru/contest/27472/run-report/93118365">OK  469ms  25.09Mb</a>
 *
 * H. Наибольшее произведение трех чисел
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * В данном списке из n ≤ 105 целых чисел найдите три числа,произведение которых максимально.
 *
 * Решение должно иметь сложность O(n), где n - размер списка.
 *
 * Выведите три искомых числа в любом порядке.
 * </pre>
 */
public class H {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        alg(reader, writer);
        reader.close();
        writer.close();
    }

    private static String join(int a, int b, int c) {
        return String.format("%d %d %d", a, b, c);
    }

    public static void alg(BufferedReader reader, BufferedWriter writer) throws IOException {
        int[] n = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        List<Integer> positive = new ArrayList<>();
        List<Integer> negative = new ArrayList<>();
        int countOf0 = 0;
        for (int e : n) {
            if (e > 0) {
                positive.add(e);
                positive.sort(Comparator.reverseOrder()); // 3 2 1
                if (positive.size() > 3) {
                    positive.remove(3);
                }
            } else if (e < 0) {
                negative.add(e);
                negative.sort(Comparator.naturalOrder()); // (-9 -8) (-3 -2 -1)
                if (negative.size() > 5) {
                    negative.remove(2);
                }
            } else {
                countOf0++;
            }
        }

        int[] P = positive.stream().mapToInt(Integer::intValue).toArray(); // 3 2 1
        int[] N = negative.stream().mapToInt(Integer::intValue).toArray(); // (-9 -8) (-3 -2 -1)
        int[] K = negative.stream().sorted(Comparator.reverseOrder()).mapToInt(Integer::intValue).toArray(); // (-1 -2 -3) (-8 -9)

        String solution = switch (P.length) {
            case 3 -> switch (N.length) {
                case 5, 4, 3, 2 -> (N[0] * N[1] > P[1] * P[2]) ? join(P[0], N[1], N[0]) : join(P[0], P[1], P[2]);
                default -> join(P[0], P[1], P[2]);
            };
            case 2 -> switch (N.length) {
                case 5, 4, 3, 2 -> join(P[0], N[1], N[0]);
                default -> (countOf0 > 0) ? join(P[0], P[1], 0) : join(P[0], P[1], N[0]);
            };
            case 1 -> switch (N.length) {
                case 5, 4, 3, 2 -> join(P[0], N[1], N[0]);
                default -> (countOf0 > 1) ? join(P[0], 0, 0) : join(P[0], 0, N[0]);
            };
            default -> switch (N.length) {
                case 5, 4, 3 -> (countOf0 == 0) ? join(K[0], K[1], K[2]) : join(0, N[1], N[0]);
                default -> (countOf0 > 2) ? "0 0 0" : (countOf0 > 1) ? ("0 0 " + N[0]) : join(0, N[1], N[0]);
            };
        };

        writer.write(solution);
        writer.flush();
    }
}

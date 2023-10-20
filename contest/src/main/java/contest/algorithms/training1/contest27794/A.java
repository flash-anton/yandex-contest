package contest.algorithms.training1.contest27794;

import java.io.*;
import java.util.Arrays;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/27794">Яндекс. Тренировки по алгоритмам июнь 2021, занятие 5. Префиксные суммы и два указателя</a>
 * <a href="https://contest.yandex.ru/contest/27794/run-report/93904390">OK  474ms  34.71Mb</a>
 *
 * A. Стильная одежда
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Глеб обожает шоппинг. Как-то раз он загорелся идеей подобрать себе майку и штаны так, чтобы выглядеть в них
 * максимально стильно. В понимании Глеба стильность одежды тем больше, чем меньше разница в цвете элементов его одежды.
 *
 * В наличии имеется N (1 ≤ N ≤ 100 000) маек и M (1 ≤ M ≤ 100 000) штанов,
 * про каждый элемент известен его цвет (целое число от 1 до 10 000 000).
 * Помогите Глебу выбрать одну майку и одни штаны так, чтобы разница в их цвете была как можно меньше.
 *
 * Формат ввода
 * Сначала вводится информация о майках:
 * в первой строке целое число N (1 ≤ N ≤ 100 000) и во второй N целых чисел от 1 до 10 000 000 — цвета имеющихся в наличии маек.
 * Гарантируется, что номера цветов идут в возрастающем порядке (в частности, цвета никаких двух маек не совпадают).
 * Далее в том же формате идёт описание штанов:
 * их количество M (1 ≤ M ≤ 100 000) и в следующей строке M целых чисел от 1 до 10 000 000 в возрастающем порядке — цвета штанов.
 *
 * Формат вывода
 * Выведите пару неотрицательных чисел — цвет майки и цвет штанов, которые следует выбрать Глебу.
 * Если вариантов выбора несколько, выведите любой из них.
 * </pre>
 */
public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        alg(reader, writer);
        reader.close();
        writer.close();
    }

    public static void alg(BufferedReader reader, BufferedWriter writer) throws IOException {
        int N = Integer.parseInt(reader.readLine());
        int[] n = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int M = Integer.parseInt(reader.readLine());
        int[] m = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        String solution = alg1(N, n, M, m);

        writer.write(solution);
        writer.flush();
    }

    public static String alg1(int N, int[] n, int M, int[] m) {
        int bestColorDifference = Math.abs(n[0] - m[0]);
        int bestTShirtColor = n[0];
        int bestPantsColor = m[0];
        int j = 0;
        for (int i = 0; i < N; i++) {
            while (j < M) {
                int colorDifference = Math.abs(n[i] - m[j]);
                if (colorDifference < bestColorDifference) {
                    bestColorDifference = colorDifference;
                    bestTShirtColor = n[i];
                    bestPantsColor = m[j];
                }

                if (m[j] < n[i]) {
                    j++;
                } else {
                    break;
                }
            }
        }
        return bestTShirtColor + " " + bestPantsColor;
    }

    public static String slow(int N, int[] n, int M, int[] m) {
        int bestColorDifference = Math.abs(n[0] - m[0]);
        int bestTShirtColor = n[0];
        int bestPantsColor = m[0];
        for (int tShirtColor : n) {
            for (int pantsColor : m) {
                int colorDifference = Math.abs(tShirtColor - pantsColor);
                if (colorDifference < bestColorDifference) {
                    bestColorDifference = colorDifference;
                    bestTShirtColor = tShirtColor;
                    bestPantsColor = pantsColor;
                }
            }
        }
        return bestTShirtColor + " " + bestPantsColor;
    }
}

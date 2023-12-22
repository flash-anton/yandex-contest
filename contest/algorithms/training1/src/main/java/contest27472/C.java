package contest27472;

import java.io.*;
import java.util.Arrays;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/27472">Яндекс. Тренировки по алгоритмам июнь 2021, занятие 2. Линейный поиск</a>
 * <a href="https://contest.yandex.ru/contest/27472/run-report/92949517">OK  115ms  9.44Mb</a>
 *
 * C. Ближайшее число
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Напишите программу, которая находит в массиве элемент, самый близкий по величине к  данному числу.
 *
 * Формат ввода
 * В первой строке задается одно натуральное число N, не превосходящее 1000 – размер массива.
 * Во второй строке содержатся N чисел – элементы массива (целые числа, не превосходящие по модулю 1000).
 * В третьей строке вводится одно целое число x, не превосходящее по модулю 1000.
 *
 * Формат вывода
 * Вывести значение элемента массива, ближайшее к x. Если таких чисел несколько, выведите любое из них.
 * </pre>
 */
public class C {
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
        int x = Integer.parseInt(reader.readLine());

        int v = n[0];
        int dif = Math.abs(x - v);
        for (int i = 1; i < N; i++) {
            int curDif = Math.abs(x - n[i]);
            if (curDif < dif) {
                v = n[i];
                dif = curDif;
            }
        }

        writer.write(Integer.toString(v));
        writer.flush();
    }
}

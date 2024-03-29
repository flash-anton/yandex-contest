package contest27794;

import java.io.*;
import java.util.Arrays;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/27794">Яндекс. Тренировки по алгоритмам июнь 2021, занятие 5. Префиксные суммы и два указателя</a>
 * <a href="https://contest.yandex.ru/contest/27794/run-report/94160081">OK  479ms  50.79Mb</a>
 *
 * D. Город Че
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * В центре города Че есть пешеходная улица - одно из самых популярных мест для прогулок жителей города.
 * По этой улице очень приятно гулять, ведь вдоль улицы расположено n забавных памятников.
 *
 * Девочке Маше из города Че нравятся два мальчика из ее школы, и она никак не может сделать выбор между ними. Чтобы
 * принять окончательное решение, она решила назначить обоим мальчикам свидание в одно и то же время. Маша хочет выбрать
 * два памятника на пешеходной улице, около которых мальчики будут ее ждать. При этом она хочет выбрать такие памятники,
 * чтобы мальчики не увидели друг друга. Маша знает, что из-за тумана мальчики увидят друг друга только в том случае,
 * если они будут на расстоянии не более r метров.
 *
 * Маше заинтересовалась, а сколько способов есть выбрать два различных памятника для организации свиданий.
 *
 * Формат ввода
 * В первой строке входного файла находятся два целых числа n и r (2 ≤ n ≤ 300000, 1 ≤ r ≤ 10^9) - количество памятников
 * и максимальное расстояние, на котором мальчики могут увидеть друг друга.
 *
 * Во второй строке задано n положительных чисел d1, …, dn, где di - расстояние от i-го памятника до начала улицы.
 * Все памятники находятся на разном расстоянии от начала улицы. Памятники приведены в порядке возрастания расстояния
 * от начала улицы (1 ≤ d1, d2, …, dn ≤ 10^9).
 *
 * Формат вывода
 * Выведите одно число - число способов выбрать два памятника для организации свиданий.
 *
 * Примечания
 * В приведенном примере Маша может выбрать памятники 1 и 4 или памятники 2 и 4.
 * </pre>
 */
public class D {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        alg(reader, writer);
        reader.close();
        writer.close();
    }

    public static void alg(BufferedReader reader, BufferedWriter writer) throws IOException {
        int[] buf = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::valueOf).toArray();
        int n = buf[0];
        int r = buf[1];
        int[] d = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::valueOf).toArray();

        String solution = alg1(n, r, d);

        writer.write(solution);
        writer.flush();
    }

    public static String alg1(int n, int r, int[] d) {
        long count = 0;
        int R = 0;
        for (int L = 0; L < n; L++) {
            while ((L == R) || (R < n) && (d[R] - d[L] <= r)) {
                R++;
            }
            count += n - R;
        }
        return "" + count;
    }

    public static String alg2(int n, int r, int[] v) {
        int counter = 0, first = 0, second = 1;
        for (; first < n - 1; first++) {
            for (; second < n; second++) {
                if (v[second] - v[first] > r) {
                    counter += n - second;
                    break;
                }
            }
        }
        return "" + counter;
    }
}

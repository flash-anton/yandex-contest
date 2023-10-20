package contest.algorithms.training1.contest27665;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/27665">Яндекс. Тренировки по алгоритмам июнь 2021, занятие 4. Словари и сортировка подсчетом</a>
 * <a href="https://contest.yandex.ru/contest/27665/run-report/93429517">OK  0.792s  38.97Mb</a>
 *
 * E. Пирамида
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Для строительства двумерной пирамиды используются прямоугольные блоки, каждый из которых характеризуется шириной и высотой.
 * Можно поставить один блок на другой, только если ширина верхнего блока строго меньше ширины нижнего (блоки нельзя поворачивать).
 * Самым нижним в пирамиде может быть блок любой ширины.
 * По заданному набору блоков определите, пирамиду какой наибольшей высоты можно построить из них.
 *
 * Формат ввода
 * В первой строке входных данных задается число N — количество блоков (1≤N≤100000).
 * В следующих N строках задаются пары натуральных чисел wi и hi (1≤wi,hi≤10^9) — ширина и высота блока соответственно.
 *
 * Формат вывода
 * Выведите одно целое число — максимальную высоту пирамиды.
 *
 * Примечания
 * В примере пирамида будет состоять из двух блоков: нижним блоком будет блок номер 3, а верхним — блок номер 2.
 * Блок номер 1 нельзя использовать вместе с блоком номер 3.
 * </pre>
 */
public class E {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        alg(reader, writer);
        reader.close();
        writer.close();
    }

    public static void alg(BufferedReader reader, BufferedWriter writer) throws IOException {
        reader.readLine();

        Map<Integer, Integer> m = new HashMap<>();
        reader.lines().forEach(line -> {
            int[] b = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
            m.compute(b[0], (k,v) -> (v == null) ? b[1] : Math.max(b[1], v));
        });

        long max = m.keySet().stream().mapToLong(m::get).sum();

        writer.write(String.valueOf(max));
        writer.flush();
    }
}

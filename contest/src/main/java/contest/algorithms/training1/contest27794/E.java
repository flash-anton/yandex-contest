package contest.algorithms.training1.contest27794;

import java.io.*;
import java.util.Arrays;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/27794">Яндекс. Тренировки по алгоритмам июнь 2021, занятие 5. Префиксные суммы и два указателя</a>
 * <a href="https://contest.yandex.ru/contest/27794/run-report/94166265">OK  483ms  39.01Mb</a>
 *
 * E. Красота превыше всего
 * Ограничение времени 	2 секунды
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * В парке города Питсбурга есть чудесная аллея, состоящая из N посаженных в один ряд деревьев, каждое одного из K сортов.
 * В связи с тем, что Питсбург принимает открытый чемпионат Байтландии по программированию, было решено построить огромную
 * арену для проведения соревнований. Так, согласно этому плану вся аллея подлежала вырубке. Однако министерство деревьев
 * и кустов воспротивилось этому решению, и потребовало оставить некоторые из деревьев в покое. Согласно новому плану
 * строительства все деревья, которые не будут вырублены, должны образовывать один непрерывный отрезок, являющийся
 * подотрезком исходного. Каждого из K видов деревьев требуется сохранить хотя бы по одному экземпляру. На вас возложена
 * задача найти отрезок наименьшей длины, удовлетворяющий указанным ограничениям.
 *
 * Формат ввода
 * В первой строке входного файла находятся два числа N и K (1 ≤ N, K ≤ 250000).
 * Во второй строке входного файла следуют N чисел (разделенных пробелами),
 * i-ое число второй строки задает цвет i-ого слева дерева в аллее.
 * Гарантируется, что присутствует хотя бы одно дерево каждого цвета
 *
 * Формат вывода
 * В выходной файл выведите два числа, координаты левого и правого концов отрезка минимальной длины, удовлетворяющего условию.
 * Если оптимальных ответов несколько, выведите любой.
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
        int[] buf = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::valueOf).toArray();
        int N = buf[0];
        int K = buf[1];
        int[] n = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::valueOf).toArray();

        int minDistance = N + 1;
        int minDistanceL = 0;
        int minDistanceR = 0;

        int[] treeCountBySort = new int[1 + K];
        int treeSortCount = 0;

        int R = 0;
        for (int L = 0; L < N; L++) {
            while ((R == L) || (R < N) && (treeSortCount < K)) {
                int treeSort = n[R];
                treeCountBySort[treeSort]++;
                if (treeCountBySort[treeSort] == 1) {
                    treeSortCount++;
                }

                R++;
            }

            int distance = R - L;
            if ((treeSortCount == K) && (distance < minDistance)) {
                minDistance = distance;
                minDistanceL = L;
                minDistanceR = R - 1;
            }

            int treeSort = n[L];
            treeCountBySort[treeSort]--;
            if (treeCountBySort[treeSort] == 0) {
                treeSortCount--;
            }
        }

        int minL = minDistanceL + 1;
        int minR = minDistanceR + 1;

        writer.write(minL + " " + minR);
        writer.flush();
    }
}

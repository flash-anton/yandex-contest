package contest27794;

import java.io.*;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/27794">Яндекс. Тренировки по алгоритмам июнь 2021, занятие 5. Префиксные суммы и два указателя</a>
 * <a href="https://contest.yandex.ru/contest/27794/run-report/93954417">OK  0.624s  34.53Mb</a>
 *
 * C. Туризм
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Александр недавно увлекся горным туризмом.
 * Ему уже надоело покорять отдельные горные пики, и он собирается покорить самую настоящую горную цепь!
 *
 * Напомним, что Александр живет в плоском мире. Горная цепь состоит из отрезков, соединяющих точки на плоскости,
 * каждая из которых находится строго правее предыдущей (x-координата следующей точки больше, чем у предыдущей).
 * Трассой на горной цепи называется её часть между двумя фиксированными концами отрезков.
 *
 * Участок, на котором при движении по трассе координата y (высота) всегда возрастает, называется подъемом,
 * величиной подъема называется разность высот между начальной и конечной точками участка.
 *
 * Туристическая компания предлагает на выбор несколько трасс на одной горной цепи. Александр из-за финансовых трудностей
 * может выбрать для поездки только одну из этих трасс. Вы решили помочь ему с выбором. Александру важно для каждой трассы
 * определить суммарную высоту подъемов на ней. Обратите внимание, что трасса может идти как слева-направо, так и справа-налево.
 *
 * Формат ввода
 * В первой строке входного файла содержится единственное число N — количество точек ломаной, задающей горную цепь (1 ≤ N ≤ 30 000).
 * Далее в N строках содержатся описания точек, каждое из которых состоит из двух целых чисел, xi и yi (1 ≤ xi, yi ≤ 30 000).
 * В следующей строке находится число M — количество трасс (1 ≤ M ≤ 30 000).
 * Далее в M строках содержатся описания трасс. Каждое описание представляет собой два целых числа, si и fi,
 * они обозначают номера вершин начала и конца трассы, соответственно (1 ≤ si ≤ N, 1 ≤ fi ≤ N).
 * Начало и конец трассы могут совпадать.
 *
 * Гарантируется, что во входном файле задана именно горная цепь.
 *
 * Формат вывода
 * Для каждой трассы выведите одно число — суммарную высоту подъемов на данной трассе.
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

    private static void readColumns(BufferedReader reader, int[] column1, int[] column2) throws IOException {
        for (int row = 0; row < column1.length; row++) {
            int[] n = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            column1[row] = n[0];
            column2[row] = n[1];
        }
    }

    public static void alg(BufferedReader reader, BufferedWriter writer) throws IOException {
        int N = Integer.parseInt(reader.readLine());
        int[] x = new int[N];
        int[] y = new int[N];
        readColumns(reader, x, y);
        int M = Integer.parseInt(reader.readLine());
        int[] start = new int[M];
        int[] end = new int[M];
        readColumns(reader, start, end);

        // 2 отдельные префиксные суммы изменений y - для подъемов и спусков
        int[] ascending = new int[N + 1];
        int[] descending = new int[N + 1];
        for (int i = 1; i < N; i++) {
            int delta = y[i] - y[i - 1];
            ascending[i + 1] = ascending[i] + Math.max(delta, 0);
            descending[i + 1] = descending[i] + Math.min(delta, 0);
        }

        int[] h = new int[M];
        for (int i = 0; i < M; i++) {
            int[] prefixSum = (start[i] < end[i]) ? ascending : descending;
            h[i] = Math.abs(prefixSum[end[i]] - prefixSum[start[i]]);
        }

        writer.write(Arrays.stream(h).mapToObj(String::valueOf).collect(Collectors.joining("\n")));
        writer.flush();
    }
}

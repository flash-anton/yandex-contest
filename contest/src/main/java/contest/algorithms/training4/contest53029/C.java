package contest.algorithms.training4.contest53029;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/53029">Алг 4.0 ДЗ 1 (53029) Сортировки: быстрая, слиянием и поразрядная</a>
 * <a href="https://contest.yandex.ru/contest/53029/run-report/96769019">OK  2.925s  196.59Mb</a>
 *
 * C. Слияние
 * Ограничение времени 	5 секунд
 * Ограничение памяти 	512Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Базовый алгоритм для сортировки слиянием — алгоритм слияния двух упорядоченных массивов в один упорядоченный массив.
 * Эта операция выполняется за линейное время с линейным потреблением памяти.
 * Реализуйте слияние двух массивов в качестве первого шага для написания сортировки слиянием.
 *
 * Формат ввода
 * В первой строке входного файла содержится число N — количество элементов первого массива (0 ≤ N ≤ 10^6).
 * Во второй строке содержатся N целых чисел ai, разделенных пробелами, отсортированные по неубыванию (-10^9 ≤ ai ≤ 10^9).
 * В третьей строке входного файла содержится число M — количество элементов второго массива (0 ≤ M ≤ 10^6).
 * В третьей строке содержатся M целых чисел bi, разделенных пробелами, отсортированные по неубыванию (-10^9 ≤ bi ≤ 10^9).
 *
 * Формат вывода
 * Выведите результат слияния этих двух массивов, то есть M + N целых чисел, разделенных пробелами, в порядке неубывания.
 *
 * Примечания
 * Для решения этой задачи советуем реализовать функцию, которая принимает на вход
 * две пары итераторов, задающие два массива, и итератор на начало буфера, в который необходимо записывать результат.
 * Итераторы можно заменить на передачу массивов и индексов в них.
 * В таком виде вам будет удобно использовать эту функцию для реализации сортировки.
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
        int N = Integer.parseInt(reader.readLine().trim());
        Integer[] a = Arrays.stream(reader.readLine().split(" ")).filter(s -> !s.isEmpty()).map(Integer::valueOf).toArray(Integer[]::new);
        int M = Integer.parseInt(reader.readLine().trim());
        Integer[] b = Arrays.stream(reader.readLine().split(" ")).filter(s -> !s.isEmpty()).map(Integer::valueOf).toArray(Integer[]::new);

        String solution = alg1(N, a, M, b);

        writer.write(solution);
        writer.flush();
    }

    public static String alg1(int N, Integer[] a, int M, Integer[] b) {
        Integer[] c = new Integer[N + M];
        merge(Comparator.naturalOrder(), a, 0, N, b, 0, M, c, 0);
        return Arrays.stream(c).map(String::valueOf).collect(Collectors.joining(" "));
    }

    public static String alg2(int N, Integer[] a, int M, Integer[] b) {
        List<Integer> aList = Arrays.asList(a);
        List<Integer> bList = Arrays.asList(b);
        List<Integer> cList = Arrays.asList(new Integer[N + M]);
        merge(Comparator.naturalOrder(), aList.listIterator(), aList.listIterator(N),
                bList.listIterator(), bList.listIterator(M), cList.listIterator());
        return cList.stream().map(String::valueOf).collect(Collectors.joining(" "));
    }

    // =================================================================================================================

    /**
     * Слияние (устойчивое) двух упорядоченных массивов в один упорядоченный.
     */
    public static <E> void merge(Comparator<E> cmp, E[] a, int aL, int aR, E[] b, int bL, int bR, E[] c, int cL) {
        while (aL < aR || bL < bR) {
            int r;
            if (aL < aR && bL < bR) {
                r = cmp.compare(a[aL], b[bL]);
            } else {
                r = aL < aR ? -1 : 1;
            }

            if (r <= 0) {
                c[cL++] = a[aL++];
            } else {
                c[cL++] = b[bL++];
            }
        }
    }

    /**
     * Слияние (устойчивое) двух упорядоченных списков в один упорядоченный.
     */
    public static <E> void merge(Comparator<E> cmp, ListIterator<E> aL, ListIterator<E> aR,
                                 ListIterator<E> bL, ListIterator<E> bR, ListIterator<E> cL) {
        int ar = aR.nextIndex();
        int br = bR.nextIndex();

        E aPrevious = next(aL, ar);
        E bPrevious = next(bL, br);
        while (nonNull(aPrevious) || nonNull(bPrevious)) {
            int r;
            if (nonNull(aPrevious) && nonNull(bPrevious)) {
                r = cmp.compare(aPrevious, bPrevious);
            } else {
                r = nonNull(aPrevious) ? -1 : 1;
            }

            if (r <= 0) {
                nextThenSet(cL, aPrevious);
                aPrevious = next(aL, ar);
            } else {
                nextThenSet(cL, bPrevious);
                bPrevious = next(bL, br);
            }
        }
    }

    // =================================================================================================================

    /**
     * Перемещение итератора к следующему элементу и получение его значения (null при достижении границы).
     */
    public static <E> E next(ListIterator<E> it, int nextIndexBound) {
        boolean hasNext = it.hasNext() && it.nextIndex() < nextIndexBound;
        return hasNext ? it.next() : null;
    }

    /**
     * Перемещение итератора к следующему элементу и изменение его значения.
     */
    public static <E> void nextThenSet(ListIterator<E> it, E e) {
        it.next();
        it.set(e);
    }
}

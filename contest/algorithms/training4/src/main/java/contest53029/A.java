package contest53029;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Predicate;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/53029">Алг 4.0 ДЗ 1 (53029) Сортировки: быстрая, слиянием и поразрядная</a>
 * <a href="https://contest.yandex.ru/contest/53029/run-report/96768512">OK  0.918s  112.85Mb</a>
 *
 * A. Partition
 * Ограничение времени 	2 секунды
 * Ограничение памяти 	256Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Базовым алгоритмом для быстрой сортировки является алгоритм partition, который разбивает набор элементов на две части
 * относительно заданного предиката.
 * По сути элементы массива просто меняются местами так, что левее некоторой точки в нем после этой операции лежат элементы,
 * удовлетворяющие заданному предикату, а справа — не удовлетворяющие ему.
 * Например, при сортировке можно использовать предикат «меньше опорного», что при оптимальном выборе опорного элемента
 * может разбить массив на две примерно равные части.
 *
 * Напишите алгоритм partition в качестве первого шага для написания быстрой сортировки.
 *
 * Формат ввода
 * В первой строке входного файла содержится число N — количество элементов массива (0 ≤ N ≤ 10^6).
 * Во второй строке содержатся N целых чисел ai, разделенных пробелами (-10^9 ≤ ai ≤ 10^9).
 * В третьей строке содержится опорный элемент x (-10^9 ≤ x ≤ 10^9).
 * Заметьте, что x не обязательно встречается среди ai.
 *
 * Формат вывода
 * Выведите результат работы вашего алгоритма при использовании предиката «меньше x»:
 * в первой строке выведите число элементов массива, меньших x, а во второй — количество всех остальных.
 *
 * Примечания
 * Чтобы решить советуем реализовать функцию, которая принимает на вход предикат и пару итераторов, задающих массив (или массив и два индекса в нём),
 * а возвращает точку разбиения, то есть итератор (индекс) на конец части, которая содержащих элементы, удовлетворяющие заданному предикату.
 * В таком виде вам будет удобно использовать эту функцию для реализации сортировки.
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
        int N = Integer.parseInt(reader.readLine().trim());
        Integer[] a = Arrays.stream(reader.readLine().split(" ")).filter(s -> !s.isEmpty()).map(Integer::valueOf).toArray(Integer[]::new);
        int x = Integer.parseInt(reader.readLine().trim());

        String solution = alg1(N, a, x);

        writer.write(solution);
        writer.flush();
    }

    public static String alg1(int N, Integer[] a, int x) {
        int firstNotSmallElementIndex = partition(e -> e < x, a, 0, N);
        return firstNotSmallElementIndex + "\n" + (N - firstNotSmallElementIndex);
    }

    public static String alg2(int N, Integer[] a, int x) {
        List<Integer> list = Arrays.asList(a);
        ListIterator<Integer> firstNotSmallElementIterator = partition(e -> e < x, list.listIterator(0), list.listIterator(N));
        return firstNotSmallElementIterator.nextIndex() + "\n" + (N - firstNotSmallElementIterator.nextIndex());
    }

    public static String alg3(int N, Integer[] a, int x) {
        int firstNotSmallElementIndex = partition3(Comparator.naturalOrder(), a, 0, N, x)[0];
        return firstNotSmallElementIndex + "\n" + (N - firstNotSmallElementIndex);
    }

    public static String alg4(int N, Integer[] a, int x) {
        List<Integer> list = Arrays.asList(a);
        int firstNotSmallElementIterator = partition3(Comparator.naturalOrder(), list, x)[0];
        return firstNotSmallElementIterator + "\n" + (N - firstNotSmallElementIterator);
    }

    // =================================================================================================================

    /**
     * Перестановка (неустойчивая) влево элементов массива, удовлетворяющих заданному предикату.
     * Возвращает индекс, слева от которого все элементы удовлетворяют предикату.
     */
    public static <E> int partition(Predicate<E> isLeftElement, E[] a, int L, int R) {
        while (L < R) {
            E r = a[--R];
            if (isLeftElement.test(r)) {
                a[R] = a[L++];
                a[L - 1] = r;
                R++;
            }
        }
        return L;
    }

    /**
     * Перестановка (неустойчивая) влево элементов связного списка, удовлетворяющих заданному предикату.
     * Возвращает итератор, слева от которого все элементы удовлетворяют предикату.
     */
    public static <E> ListIterator<E> partition(Predicate<E> isLeftElement, ListIterator<E> L, ListIterator<E> R) {
        while (L.nextIndex() < R.nextIndex()) {
            E r = R.previous();
            if (isLeftElement.test(r)) {
                R.set(L.next());
                L.set(r);
                R.next();
            }
        }
        return L;
    }

    /**
     * Перестановка (неустойчивая) элементов массива: слева элементы меньше опорного, справа - больше.
     * Возвращает индекс первого элемента, равного опорному, и первого элемента больше опорного.
     */
    public static <E> int[] partition3(Comparator<E> c, E[] a, int L, int R, E pivot) {
        int LEqual = L;
        int LGreater = L;
        for (int i = L; i < R; i++) {
            E e = a[i];
            int r = c.compare(e, pivot);
            if (r == 0) {
                a[i] = a[LGreater];
                a[LGreater++] = e;
            } else if (r < 0) {
                a[i] = a[LGreater];
                a[LGreater++] = a[LEqual];
                a[LEqual++] = e;
            }
        }
        return new int[]{LEqual, LGreater};
    }

    /**
     * Перестановка (неустойчивая) элементов связного списка: слева элементы меньше опорного, справа - больше.
     * Возвращает индекс первого элемента, равного опорному, и первого элемента больше опорного.
     */
    public static <E> int[] partition3(Comparator<E> c, List<E> a, E pivot) {
        ListIterator<E> RSmaller = a.listIterator();
        ListIterator<E> REqual = a.listIterator();
        for (ListIterator<E> i = a.listIterator(); i.hasNext(); ) {
            E e = i.next();
            int r = c.compare(e, pivot);
            if (r == 0) {
                i.set(REqual.next());
                REqual.set(e);
            } else if (r < 0) {
                i.set(REqual.next());
                REqual.set(RSmaller.next());
                RSmaller.set(e);
            }
        }
        return new int[]{RSmaller.nextIndex(), REqual.nextIndex()};
    }
}

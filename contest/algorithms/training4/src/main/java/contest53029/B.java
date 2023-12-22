package contest53029;

import java.io.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/53029">Алг 4.0 ДЗ 1 (53029) Сортировки: быстрая, слиянием и поразрядная</a>
 * <a href="https://contest.yandex.ru/contest/53029/run-report/96768697">OK  2.594s  151.87Mb</a>
 *
 * B. Быстрая сортировка
 * Ограничение времени 	10 секунд
 * Ограничение памяти 	512Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Реализуйте быструю сортировку, используя алгоритм из предыдущей задачи.
 * На каждом шаге выбирайте опорный элемент и выполняйте partition относительно него.
 * Затем рекурсивно запуститесь от двух частей, на которые разбился исходный массив.
 *
 * Формат ввода
 * В первой строке входного файла содержится число N — количество элементов массива (0 ≤ N ≤ 10^6).
 * Во второй строке содержатся N целых чисел ai, разделенных пробелами (-10^9 ≤ ai ≤ 10^9).
 *
 * Формат вывода
 * Выведите результат сортировки, то есть N целых чисел, разделенных пробелами.
 *
 * Примечания
 * Используйте функцию, реализованную в предыдущей задаче.
 * </pre>
 */
public class B {
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

        String solution = alg1(N, a);

        writer.write(solution);
        writer.flush();
    }

    public static String alg1(int N, Integer[] a) {
        quickSort(Comparator.naturalOrder(), a, 0, N);
        return Arrays.stream(a).map(String::valueOf).collect(Collectors.joining(" "));
    }

    public static String alg2(int N, Integer[] a) {
        List<Integer> list = Arrays.asList(a);
        quickSort(Comparator.naturalOrder(), list.listIterator(0), list.listIterator(N));
        return list.stream().map(String::valueOf).collect(Collectors.joining(" "));
    }

    public static String alg3(int N, Integer[] a) {
        quickSort3(Comparator.naturalOrder(), a, 0, N);
        return Arrays.stream(a).map(String::valueOf).collect(Collectors.joining(" "));
    }

    public static String alg4(int N, Integer[] a) {
        List<Integer> list = Arrays.asList(a);
        quickSort3(Comparator.naturalOrder(), list);
        return list.stream().map(String::valueOf).collect(Collectors.joining(" "));
    }

    // =================================================================================================================

    /**
     * Быстрая сортировка (неустойчивая) массива
     */
    public static <E> void quickSort(Comparator<E> c, E[] a, int L, int R) {
        if (R - L > 1) {
            E pivot = a[ThreadLocalRandom.current().nextInt(L, R)];
            Predicate<E> isSmaller = e -> c.compare(e, pivot) < 0;
            Predicate<E> isEqual = e -> c.compare(e, pivot) == 0;

            int LEqual = partition(isSmaller, a, L, R);
            quickSort(c, a, L, LEqual);

            int LGreater = partition(isEqual, a, LEqual, R);
            quickSort(c, a, LGreater, R);
        }
    }

    /**
     * Быстрая сортировка (неустойчивая) связного списка
     */
    public static <E> void quickSort(Comparator<E> c, ListIterator<E> L, ListIterator<E> R) {
        int l = L.nextIndex();
        int r = R.nextIndex();
        if (r - l > 1) {
            int pivotIndex = ThreadLocalRandom.current().nextInt(l, r);
            ListIterator<E> it = (pivotIndex < (l + r) / 2) ? L : R;
            E pivot = move(it, pivotIndex).next();

            int LEqual = partition(e -> c.compare(e, pivot) < 0, move(L, l), move(R, r)).nextIndex();
            quickSort(c, move(L, l), move(R, LEqual));

            int LGreater = partition(e -> c.compare(e, pivot) == 0, move(L, LEqual), move(R, r)).nextIndex();
            quickSort(c, move(L, LGreater), move(R, r));
        }
    }

    /**
     * Быстрая сортировка (неустойчивая) массива
     */
    public static <E> void quickSort3(Comparator<E> c, E[] a, int L, int R) {
        if (R - L > 1) {
            E pivot = a[ThreadLocalRandom.current().nextInt(L, R)];
            int[] EG = partition3(c, a, L, R, pivot);
            quickSort3(c, a, L, EG[0]);
            quickSort3(c, a, EG[1], R);
        }
    }

    /**
     * Быстрая сортировка (неустойчивая) связного списка
     */
    public static <E> void quickSort3(Comparator<E> c, List<E> a) {
        if (a.size() > 1) {
            E pivot = a.get(ThreadLocalRandom.current().nextInt(a.size()));
            int[] EG = partition3(c, a, pivot);
            quickSort3(c, a.subList(0, EG[0]));
            quickSort3(c, a.subList(EG[1], a.size()));
        }
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

    // =================================================================================================================

    /**
     * Перемещение итератора до nextIndex.
     */
    public static <E> ListIterator<E> move(ListIterator<E> it, int nextIndex) {
        while (it.nextIndex() < nextIndex) {
            it.next();
        }
        while (it.nextIndex() > nextIndex) {
            it.previous();
        }
        return it;
    }
}

package contest.algorithms.training4.contest53029;

import java.io.*;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/53029">Алг 4.0 ДЗ 1 (53029) Сортировки: быстрая, слиянием и поразрядная</a>
 * <a href="https://contest.yandex.ru/contest/53029/run-report/97113095">OK  228ms  15.10Mb</a>
 *
 * E. Поразрядная сортировка
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Поразрядная сортировка является одним из видов сортировки, которые работают практически за линейное от размера сортируемого массива время.
 * Такая скорость достигается за счет того, что эта сортировка использует внутреннюю структуру сортируемых объектов.
 * Изначально этот алгоритм использовался для сортировки перфокарт.
 * Первая его компьютерная реализация была создана в университете MIT Гарольдом Сьюардом (Harold Н. Seward).
 *
 * Опишем алгоритм подробнее.
 * Пусть задан массив строк s1 ,..., si причём все строки имеют одинаковую длину m.
 * Работа алгоритма состоит из m фаз.
 * На i -ой фазе строки сортируются по i -ой с конца букве.
 * Происходит это следующим образом.
 * Будем, для простоты, в этой задаче рассматривать строки из цифр от 0 до 9.
 * Для каждой цифры создается «корзина» («bucket»), после чего строки si распределяются по «корзинам» в соответствии с i-ой цифрой с конца.
 * Строки, у которых i-ая с конца цифра равна j попадают в j-ую корзину
 * (например, строка 123 на первой фазе попадет в третью корзину, на второй — во вторую, на третьей — в первую).
 * После этого элементы извлекаются из корзин в порядке увеличения номера корзины.
 * Таким образом, после первой фазы строки отсортированы по последней цифре, после двух фаз — по двум последним, ..., после m фаз — по всем.
 * При этом важно, чтобы элементы в корзинах сохраняли тот же порядок, что и в исходном массиве (до начала этой фазы).
 * Например, если массив до первой фазы имеет вид: 111, 112, 211, 311, то элементы по корзинам распределятся следующим образом:
 * в первой корзине будет 111, 211, 311, а второй: 112.
 *
 * Напишите программу, детально показывающую работу этого алгоритма на заданном массиве.
 *
 * Формат ввода
 * Первая строка входного файла содержит целое число n (1≤ n ≤1000) .
 * Последующие n строк содержат каждая по одной строке si .
 * Длины всех si , одинаковы и не превосходят 20.
 * Все si состоят только из цифр от 0 до 9.
 *
 * Формат вывода
 * В выходной файл выведите исходный массив строк в,
 * состояние «корзин» после распределения элементов по ним для каждой фазы и отсортированный массив.
 * Следуйте формату, приведенному в примере.
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
        int n = Integer.parseInt(reader.readLine().trim());
        String[] s = new String[n];
        for (int i = 0; i < n; i++) {
            s[i] = reader.readLine();
        }

        Logger<String, Integer> log = new StringBuilderLogger<>();
        alg1(s, log);

        writer.write(log.toString());
        writer.flush();
    }

    public static String[] alg1(String[] s, Logger<String, Integer> log) {
        ArrayDeque<Integer> radixes = Stream.iterate(s[0].length() - 1, i -> i >= 0, i -> --i)
                .collect(toCollection(ArrayDeque::new));
        List<Integer> bucketSequence = IntStream.range(0, 10).boxed().collect(toList());

        List<String> a = radixSort(log, List.of(s), radixes::poll, (ss, radix) -> ss.charAt(radix) - '0', bucketSequence);

        return a.toArray(String[]::new);
    }

    // =================================================================================================================

    /**
     * Поразрядная (посвойственная) сортировка элементов с логированием операций.
     */
    public static <E, R> List<E> radixSort(Logger<E, R> log,
                                           List<E> a, Supplier<Integer> radixSupplier, BiFunction<E, Integer, R> toBucket,
                                           List<R> bucketSequence) {
        log.initialArray(a);

        Integer radix;
        while ((radix = radixSupplier.get()) != null) {
            a = phase(log, a, radix, toBucket, bucketSequence);
        }

        log.sortedArray(a);

        return a;
    }

    /**
     * Разложение элементов по корзинам, соответствующим указанному разряду (свойству) элемента.
     */
    public static <E, R> Map<R, List<E>> parse(List<E> a, Integer radix, BiFunction<E, Integer, R> toBucket) {
        Map<R, List<E>> buckets = new HashMap<>();
        for (E e : a) {
            R bucket = toBucket.apply(e, radix);
            buckets.computeIfAbsent(bucket, k -> new ArrayList<>()).add(e);
        }
        return buckets;
    }

    /**
     * Слияние элементов из корзин в единый список, с сохранением взаимного порядка.
     */
    public static <E, R> List<E> merge(Map<R, List<E>> buckets, List<R> bucketSequence) {
        List<E> a = new ArrayList<>();
        for (R bucket : bucketSequence) {
            a.addAll(buckets.getOrDefault(bucket, List.of()));
        }
        return a;
    }

    /**
     * Фаза разложения элементов по корзинам и их последующего слияния для одного разряда (свойства) элемента.
     */
    public static <E, R> List<E> phase(Logger<E, R> log,
                                       List<E> a, Integer radix, BiFunction<E, Integer, R> toBucket,
                                       List<R> bucketSequence) {
        Map<R, List<E>> buckets = parse(a, radix, toBucket);
        log.phase(buckets, bucketSequence);
        return merge(buckets, bucketSequence);
    }

    // =================================================================================================================

    /**
     * Интерфейс логера операций алгоритма.
     */
    public interface Logger<E, R> {
        void initialArray(List<E> a);

        void phase(Map<R, List<E>> buckets, List<R> bucketSequence);

        void sortedArray(List<E> a);
    }

    /**
     * Заглушка логера операций алгоритма.
     */
    public static class DummyLogger<E, R> implements Logger<E, R> {
        @Override
        public void initialArray(List<E> a) {
        }

        @Override
        public void phase(Map<R, List<E>> buckets, List<R> bucketSequence) {
        }

        @Override
        public void sortedArray(List<E> a) {
        }
    }

    /**
     * Логер операций алгоритма, записывающий лог в {@link StringBuilder}.
     */
    public static class StringBuilderLogger<E, R> implements Logger<E, R> {
        private final StringBuilder sb = new StringBuilder();
        private int phase = 0;

        @Override
        public void initialArray(List<E> a) {
            sb.append("Initial array:\n");
            String s = a.stream().map(E::toString).collect(joining(", "));
            sb.append(s).append("\n");
        }

        @Override
        public void phase(Map<R, List<E>> buckets, List<R> bucketSequence) {
            sb.append("**********\n");
            sb.append("Phase ").append(++phase).append("\n");
            for (R bucket : bucketSequence) {
                List<E> list = buckets.getOrDefault(bucket, List.of());
                String s = list.isEmpty() ? "empty" : list.stream().map(E::toString).collect(joining(", "));
                sb.append("Bucket ").append(bucket).append(": ").append(s).append("\n");
            }
        }

        @Override
        public void sortedArray(List<E> a) {
            sb.append("**********\n");
            sb.append("Sorted array:\n");
            String s = a.stream().map(E::toString).collect(joining(", "));
            sb.append(s);
        }

        @Override
        public String toString() {
            return sb.toString();
        }
    }
}

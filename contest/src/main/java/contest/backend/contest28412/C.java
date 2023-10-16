package contest.backend.contest28412;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/28412/enter">Тренировочный контест: разработка бэкенда</a>
 *
 * C. Расстояние
 *
 * Язык 	Ограничение времени 	Ограничение памяти 	Ввод 	Вывод
 * Все языки 	            2 секунды 	512Mb 	стандартный ввод или input.txt 	стандартный вывод или output.txt
 * Python 3.7.3         	4 секунды 	512Mb
 * Python 3.7 (PyPy 7.3.3) 	4 секунды 	512Mb
 * Python 2.7 	            4 секунды 	512Mb
 * PHP 7.3.5 	            4 секунды 	512Mb
 *
 * Рассмотрим целочисленный массив a длины n. Назовём расстоянием от индекса i до множества индексов S
 * величину dist(i,S)=∑j∈S∣ai−aj∣.
 * Зафиксируем целое число k. Рассмотрим функцию f(i)=min dist(i,S), где минимум берётся по множествам S размера k,
 * не содержащим индекс i.
 * Определите значение f(i) для всех i от 1 до n.
 *
 * Формат ввода
 * В первой строке заданы два целых числа n и k (2≤n≤300000, 1≤k<n), описанные в условии.
 * Во второй строке содержится n целых чисел ai (1≤ai≤10^9) — элементы массива a.
 *
 * Формат вывода
 * Выведите n целых чисел: значения f(i) для i=1,i=2,…,i=n.
 *
 *  1	ok	159ms / 10.46Mb	-
 * 	2	ok	157ms / 10.66Mb	-
 * 	3	ok	156ms / 10.52Mb	-
 * 	4	ok	155ms / 10.39Mb	-
 * 	5	ok	159ms / 10.49Mb	-
 * 	6	ok	156ms / 10.68Mb	-
 * 	7	ok	156ms / 10.68Mb	-
 * 	8	ok	165ms / 10.63Mb	-
 * 	9	ok	161ms / 10.68Mb	-
 * 	10	ok	164ms / 10.63Mb	-
 * 	11	ok	164ms / 10.50Mb	-
 * 	12	ok	161ms / 10.71Mb	-
 * 	13	ok	190ms / 11.18Mb	-
 * 	14	ok	460ms / 32.15Mb	-
 * 	15	ok	499ms / 34.23Mb	-
 * 	16	time-limit-exceeded	2.076s / 45.27Mb	-
 * </pre>
 */
public class C {
    public static void main(String[] args) throws IOException {
        try (InputStream reader = new BufferedInputStream(System.in);
             OutputStream writer = new BufferedOutputStream(System.out)) {
            Main(reader, writer);
        }
    }

    static long f(int[] a, int[] countArr, int i, int k) {
        long f = 0;
        int kRest = k - (countArr[i] - 1); // exclude i to i abs
        if (kRest <= 0) {
            return 0;
        }

        int ai = a[i];

        int left = i - 1;
        int absLeft = (left == -1) ? -1 : (ai - a[left]);

        int right = (i + 1 == a.length) ? -1 : (i + 1);
        int absRight = (right == -1) ? -1 : (a[right] - ai);

        while (kRest > 0) {

            boolean useLeft = left != -1 && (right == -1 || absLeft < absRight);

            int j = useLeft ? left : right;
            int abs = useLeft ? absLeft : absRight;
            int count = Math.min(kRest, countArr[j]);

            f += (long) abs * count;
            kRest -= count;

            if (useLeft) {
                left--;
                absLeft = (left == -1) ? -1 : (ai - a[left]);
            } else {
                right = (++right == a.length) ? -1 : right;
                absRight = (right == -1) ? -1 : (a[right] - ai);
            }
        }

        return f;
    }

    public static void Main(InputStream reader, OutputStream writer) throws IOException {
        int n = readInt(reader);
        int k = readInt(reader);

        int[] a = new int[n];
        Map<Integer, Integer> countByA = new HashMap<>(n, 1);
        for (int i = 0; i < n; i++) {
            int ai = readInt(reader);
            a[i] = ai;
            countByA.merge(ai, 1, Integer::sum);
        }

        int[] aSortedDistinct = countByA.keySet().parallelStream().mapToInt(Integer::intValue).sorted().toArray();
        int[] countArr = Arrays.stream(aSortedDistinct).map(countByA::get).toArray();

        Map<Integer, byte[]> fWritableByA = IntStream.range(0, aSortedDistinct.length).parallel().boxed()
                .collect(Collectors.toMap(
                        i -> aSortedDistinct[i],
                        i -> String.valueOf(f(aSortedDistinct, countArr, i, k)).concat(" ").getBytes()));

        for (int ai : a) {
            writer.write(fWritableByA.get(ai));
        }
    }

    private static int readInt(InputStream reader) throws IOException {
        byte[] buf = new byte[11]; // 11 == String.valueOf(Integer.MIN_VALUE).length()
        int size = 0;

        int b;
        while ((b = reader.read()) != -1 && b != '\n' && b != ' ') {
            buf[size++] = (byte) b;
        }

        return Integer.parseInt(new String(buf, 0, size));
    }
}

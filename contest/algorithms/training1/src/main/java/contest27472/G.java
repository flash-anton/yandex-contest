package contest27472;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/27472">Яндекс. Тренировки по алгоритмам июнь 2021, занятие 2. Линейный поиск</a>
 * <a href="https://contest.yandex.ru/contest/27472/run-report/92999904">OK  422ms  24.15Mb</a>
 *
 * G. Наибольшее произведение двух чисел
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Дан список, заполненный произвольными целыми числами.
 * Найдите в этом списке два числа, произведение которых максимально. Выведите эти числа в порядке неубывания.
 * Список содержит не менее двух элементов. Числа подобраны так, что ответ однозначен.
 *
 * Решение должно иметь сложность O(n), где n - размер списка.
 * </pre>
 */
public class G {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        alg(reader, writer);
        reader.close();
        writer.close();
    }

    private static void add(List<Integer> list, int e, Comparator<Integer> c) {
        list.add(e);
        list.sort(c);
        while (list.size() > 2) {
            list.remove(2);
        }
    }

    public static void alg(BufferedReader reader, BufferedWriter writer) throws IOException {
        int[] n = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        List<Integer> positiveList = new ArrayList<>();
        List<Integer> negativeList = new ArrayList<>();
        for (int e : n) {
            if (e > 0) {
                add(positiveList, e, Comparator.reverseOrder());
            } else if (e < 0) {
                add(negativeList, e, Comparator.naturalOrder());
            }
        }
        positiveList.sort(Comparator.naturalOrder());

        int positiveSize = positiveList.size();
        int negativeSize = negativeList.size();

        long positiveMul = positiveList.stream().mapToLong(Integer::longValue).reduce(1, (a, b) -> a * b);
        long negativeMul = negativeList.stream().mapToLong(Integer::longValue).reduce(1, (a, b) -> a * b);

        String positiveStr = positiveList.stream().map(String::valueOf).collect(Collectors.joining(" "));
        String negativeStr = negativeList.stream().map(String::valueOf).collect(Collectors.joining(" "));

        String solution = switch (positiveSize + negativeSize) {
            case 1 -> negativeStr + " 0 " + positiveStr;
            case 2 -> negativeStr + " " + positiveStr;
            case 3 -> negativeSize == 2 ? negativeStr : positiveStr;
            case 4 -> negativeMul > positiveMul ? negativeStr : positiveStr;
            default -> "0 0"; // case 0
        };

        writer.write(solution);
        writer.flush();
    }
}

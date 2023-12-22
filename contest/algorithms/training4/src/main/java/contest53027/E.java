package contest53027;

import java.io.*;
import java.util.Arrays;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/53027">Яндекс. Тренировки по алгоритмам 4.0. Разминка</a>
 * <a href="https://contest.yandex.ru/contest/53027/run-report/95526072">OK  303ms  25.37Mb</a>
 *
 * E. Средний уровень
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	256Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * В группе учатся n студентов, каждый из которых имеет свой рейтинг ai. Им нужно выбрать старосту;
 * для этого студенты хотят выбрать старосту таким образом чтобы суммарный уровень недовольства группы был минимальный.
 *
 * Если выбрать j-го старостой, то уровень недовольства i-го студента равен ∣ai−aj∣.
 * Например, если в группе есть три студента с рейтингами 1, 3 и 4 и в качестве старосты выбирают второго,
 * то уровень недовольства группы будет равен |1−3|+|3−3|+|4−3|=3.
 *
 * Вычислите уровень недовольства группы при выборе каждого из студентов старостой.
 *
 * Формат ввода
 * В первой строке дано единственное целое число n (1≤n≤10^5)  — количество студентов в группе.
 * Во второй строке даны n целых чисел a1,a2,…,an, идущих по неубыванию (0≤a1≤a2≤…≤an≤10^4)  — рейтинги студентов.
 *
 * Формат вывода
 * Выведите n чисел через пробел, i-е из которых будет обозначать уровень недовольства группы при выборе i-го студента старостой.
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
        int[] a = Arrays.stream(reader.readLine().split(" ")).filter(s -> !s.isEmpty()).mapToInt(Integer::parseInt).toArray();

        String solution = alg(a);

        writer.write(solution);
        writer.flush();
    }

    public static String alg(int[] a) {
        int[] prefixSum = new int[a.length + 1];
        for (int i = 0; i < a.length; i++) {
            prefixSum[i + 1] = prefixSum[i] + a[i];
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < a.length; i++) {
            int leftDiscomfort = i * a[i] - prefixSum[i];

            int rightSum = prefixSum[a.length] - prefixSum[i + 1];
            int rightCount = a.length - i - 1;
            int rightDiscomfort = rightSum - rightCount * a[i];

            sb.append(leftDiscomfort + rightDiscomfort).append(' ');
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}

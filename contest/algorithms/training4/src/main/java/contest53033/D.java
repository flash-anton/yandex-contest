package contest53033;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.nCopies;
import static java.util.Collections.reverseOrder;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/53033">Алг 4.0 Финал (53033)</a>
 * <a href="https://contest.yandex.ru/contest/53033/run-report/100032301">OK  231ms  12.77Mb</a>
 *
 * D. Кирпичи
 * Ограничение времени 	3 секунды
 * Ограничение памяти 	512Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Вася решил выложить бордюр из кипричей для дорожки на своем участке. Расположенный по соседству кирпичный завод выпускает кирпичи длиной A1, A2, …, Am. Промоутеры завода раздают потенциальным клиентам по 2 кирпича бесплатно. Вася взял по 2 кирпича каждого типа и теперь хочет узнать, может ли он выложить из них бордюр длиной N и толщиной в один кирпич.
 * Формат ввода
 *
 * Сначала вводится число N (1 ≤ N ≤ 109), затем — число M (1 ≤ M ≤ 15) и далее M попарно различных чисел A1, A2, …, AM (1 ≤ Ai ≤ 109).
 * Формат вывода
 *
 * Выведите сначала K — количество кипричей, которое нужно использовать для выкладывания бордюра, если можно выложить бордюр длиной ровно N. Далее выведите K чисел, задающих длины использованных кирпичей. Если решений несколько, выведите вариант, в котором Вася использует наименьшее количество кирпичей. Если таких вариантов несколько, выведите любой из них.
 *
 * Если для выкладывания бордюра придется обязательно разломить какой-то кирпич, то выведите одно число 0. Если же у Васи не хватит кипричей, чтобы выложить бордюр, выведите одно число –1 (минус один)..
 * </pre>
 */
public class D {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        alg(reader, writer);
        reader.close();
        writer.close();
    }

    public static void alg(BufferedReader reader, BufferedWriter writer) throws IOException {
        int[] a = Arrays.stream(reader.readLine().split(" ")).filter(s -> !s.isEmpty()).mapToInt(Integer::parseInt).toArray();
        int N = a[0];
        int M = a[1];

        long[] A = Arrays.stream(reader.readLine().split(" ")).filter(s -> !s.isEmpty()).mapToLong(Long::valueOf).toArray();

        String solution = alg1(N, M, A);

        writer.write(solution);
        writer.flush();
    }

    public static String alg1(long N, int M, long[] A) {
        long totalLength = 2 * Arrays.stream(A).sum();
        if (totalLength < N) {
            return "-1";
        }

        BricksSet min = new BricksSet(nCopies(M, 2L).stream().mapToLong(Long::longValue).toArray(), totalLength, 2 * M);
        BricksSet cur = new BricksSet(new long[M], 0, 0);
        f(N, M, A, min, cur, 0);
        if (totalLength > N && min.totalLength != N) {
            return "0";
        }

        List<Long> list = new ArrayList<>(min.totalCount);
        for (int i = 0; i < M; i++) {
            long length = A[i];
            while (min.count[i]-- > 0) {
                list.add(length);
            }
        }

        String s = list.stream().sorted(reverseOrder()).map(String::valueOf).collect(Collectors.joining(" "));
        return min.totalCount + "\n" + s;
    }

    static void f(long N, int M, long[] A, BricksSet min, BricksSet cur, int index) {
        if (index < M) {
            long length = A[index];
            int nextIndex = index + 1;
            f(N, M, A, min, cur.change(index, length, 2), nextIndex);
            f(N, M, A, min, cur.change(index, length, -1), nextIndex);
            f(N, M, A, min, cur.change(index, length, -1), nextIndex);
        } else if (cur.totalLength == N && cur.totalCount < min.totalCount) {
            min.count = Arrays.copyOf(cur.count, M);
            min.totalLength = cur.totalLength;
            min.totalCount = cur.totalCount;
        }
    }

    static class BricksSet {
        long[] count;
        long totalLength;
        int totalCount;

        public BricksSet(long[] count, long totalLength, int totalCount) {
            this.count = count;
            this.totalLength = totalLength;
            this.totalCount = totalCount;
        }

        BricksSet change(int index, long length, int deltaCount) {
            count[index] += deltaCount;
            totalLength += deltaCount * length;
            totalCount += deltaCount;
            return this;
        }
    }
}

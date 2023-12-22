package contest27844;

import java.io.*;
import java.util.Arrays;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/27844">Яндекс. Тренировки по алгоритмам июнь 2021, занятие 6. Бинарный поиск</a>
 * <a href="https://contest.yandex.ru/contest/27844/run-report/94985778">OK  145ms  9.84Mb</a>
 *
 * F. Очень легкая задача
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Сегодня утром жюри решило добавить в вариант олимпиады еще одну, Очень Легкую Задачу.
 * Ответственный секретарь Оргкомитета напечатал ее условие в одном экземпляре, и теперь ему нужно до начала олимпиады успеть сделать еще N копий.
 * В его распоряжении имеются два ксерокса, один из которых копирует лист за х секунд, а другой – за y.
 * (Разрешается использовать как один ксерокс, так и оба одновременно. Можно копировать не только с оригинала, но и с копии.)
 * Помогите ему выяснить, какое минимальное время для этого потребуется.
 *
 * Формат ввода
 * На вход программы поступают три натуральных числа N, x и y, разделенные пробелом (1 ≤ N ≤ 2 × 10^8, 1 ≤ x, y ≤ 10).
 *
 * Формат вывода
 * Выведите одно число – минимальное время в секундах, необходимое для получения N копий.
 * </pre>
 */
public class F {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        alg(reader, writer);
        reader.close();
        writer.close();
    }

    public static void alg(BufferedReader reader, BufferedWriter writer) throws IOException {
        int[] buf = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = buf[0];
        int x = buf[1];
        int y = buf[2];

        int t = Math.min(x, y);
        N--;

        int L = 0;
        int R = N + 1;
        while (L < R) {
            int M = (L + R) / 2;
            if (x * M < y * (N - M)) {
                L = M + 1;
            } else {
                R = M;
            }
        }

        // При n=L-1, (x * n) < (y * (N - n))  =>  общее время = (y * (N - L + 1))
        // При n=L,  (x * n) >= (y * (N - n))  =>  общее время = (x * L)
        // Из них нужно выбрать минимальное
        t += Math.min(x * L, y * (N - L + 1));

        writer.write("" + t);
        writer.flush();
    }
}

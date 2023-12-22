package contest27794;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/27794">Яндекс. Тренировки по алгоритмам июнь 2021, занятие 5. Префиксные суммы и два указателя</a>
 * <a href="https://contest.yandex.ru/contest/27794/run-report/94329473">OK  0.67s  36.53Mb</a>
 *
 * G. Счет в гипершашках
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Андрей работает судьей на чемпионате по гипершашкам. В каждой игре в гипершашки участвует три игрока. По ходу игры
 * каждый из игроков набирает некоторое положительное целое число баллов. Если после окончания игры первый игрок набрал
 * a баллов, второй — b, а третий c, то говорят, что игра закончилась со счетом a:b:c.
 *
 * Андрей знает, что правила игры гипершашек устроены таким образом, что в результате игры баллы любых двух игроков
 * различаются не более чем в k раз.
 *
 * После матча Андрей показывает его результат, размещая три карточки с очками игроков на специальном табло. Для этого у
 * него есть набор из n карточек, на которых написаны числа x1, x2, …, xn. Чтобы выяснить, насколько он готов к чемпионату,
 * Андрей хочет понять, сколько различных вариантов счета он сможет показать на табло, используя имеющиеся карточки.
 *
 * Требуется написать программу, которая по числу k и значениям чисел на карточках, которые имеются у Андрея, определяет
 * количество различных вариантов счета, которые Андрей может показать на табло.
 *
 * Формат ввода
 * Первая строка входного файла содержит два целых числа: n и k (3 ≤ n ≤ 100000, 1 ≤ k ≤ 10^9).
 * Вторая строка входного файла содержит n целых чисел x1, x2, …, xn (1 ≤ xi ≤ 10^9).
 *
 * Формат вывода
 * Выходной файл должен содержать одно целое число — искомое количество различных вариантов счета.
 *
 * Примечания
 * В приведенном примере Андрей сможет показать следующие варианты счета: 1:1:2, 1:2:1, 2:1:1, 1:2:2, 2:1:2, 2:2:1, 2:2:3, 2:3:2, 3:2:2.
 * Другие тройки чисел, которые можно составить с использованием имеющихся карточек, не удовлетворяют заданному условию,
 * что баллы любых двух игроков различаются не более чем в k = 2 раза.
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

    public static void alg(BufferedReader reader, BufferedWriter writer) throws IOException {
        int[] buf = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = buf[0];
        int k = buf[1];
        int[] x = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Arrays.sort(x);

        long combinations = 0;

        Map<Integer, Integer> counts = new HashMap<>(); // <карта, количество>
        int S = 0; // кол-во уникальных карт, встречающихся 1 раз
        int U = 0; // кол-во уникальных карт

        int R = 0;
        for (int L = 0; L < n - 2; L++) {
            while ((R == L) || (R < n) && ((double) x[R] / x[L] <= k)) {
                int count = counts.compute(x[R], (key, v) -> (v == null) ? 1 : ++v);
                if (R - L >= 2) {
                    combinations += combinations(count - 1, U, S);
                }
                S += (count == 1) ? 1 : (count == 2) ? -1 : 0;
                U += (count == 1) ? 1 : 0;
                R++;
            }

            Integer count = counts.computeIfPresent(x[L], (key, v) -> (v == 1) ? null : --v);
            S -= (count == null) ? 1 : (count == 1) ? -1 : 0;
            U -= (count == null) ? 1 : 0;
        }

        writer.write("" + combinations);
        writer.flush();
    }

    /**
     * Подсчет кол-ва новых комбинаций, образующихся при добавлении в набор очередной карты.
     *
     * @param count - кол-во карт в наборе, идентичных добавляемой, ДО добавления.
     * @param U     - кол-во уникальных карт в наборе ДО добавления очередной.
     * @param S     - кол-во карт, встречающихся в наборе 1 раз, ДО добавления очередной.
     * @return кол-во новых комбинаций.
     */
    private static long combinations(int count, long U, long S) {
        // D - кол-во карт, встречающихся в наборе 2 раза, ДО добавления очередной.
        // T - кол-во карт, встречающихся в наборе больше 2 раз, ДО добавления очередной.
        // U = S+D+T
        return switch (count) {
            //   1     * S           * ((S-1)+D+T) // new,    S, rest
            // + 1     * (D+T)       * (S+D+T)     // new,   !S, all
            // + S     * 1           * ((S-1)+D+T) //   S,  new, rest
            // + (D+T) * 1           * (S+D+T)     //  !S,  new, all
            // + S     * ((S-1)+D+T) * 1           //   S, rest, new
            // + (D+T) * (S+D+T)     * 1           //  !S,  all, new
            case 0 -> 3 * U * U - 3 * S; // 3S((S-1)+D+T) + 3(D+T)(S+D+T) = 3SU - 3S + 3(D+T)U = 3UU-3S

            //   1           * 1           * ((S-1)+D+T) // exist S, same S, rest
            // + 1           * ((S-1)+D+T) * 1           // exist S,   rest, same S
            // + ((S-1)+D+T) * 1           * 1           //    rest, same S, exist S
            case 1 -> 3 * U - 3; // 3((S-1)+D+T) = 3U-3

            //   1 * 1 * 1 // exist D, same D, same D
            case 2 -> 1;

            default -> 0;
        };
    }
}

package contest.algorithms.training4.contest53027;

import java.io.*;
import java.math.BigInteger;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/53027">Яндекс. Тренировки по алгоритмам 4.0. Разминка</a>
 * <a href="https://contest.yandex.ru/contest/53027/run-report/95552786">OK  282ms  27.94Mb</a>
 *
 * F. Лифт
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	256Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Тридцатого декабря все сотрудники известной IT-компании отправляются праздновать Новый год!
 * На парковке офиса сотрудников уже ожидают автобусы, чтобы отвезти их в ресторан.
 *
 * Известно, что на i-м этаже работает ai сотрудников, а парковка расположена на нулевом этаже.
 *
 * Изначально лифт расположен на этаже с парковкой.
 *
 * Какое минимальное количество времени лифт будет перевозить всех людей на парковку?
 * Известно, что лифт движется со скоростью один этаж в секунду, а посадка и высадка происходит мгновенно.
 *
 * Формат ввода
 * В первой строке дано единственное целое число k(1≤k≤10^9)  — количество людей, которое вмещает лифт за одну поездку.
 * Во второй строке дано единственное целое число n(1≤k≤10^5)  — количество этажей в здании.
 * В следующих n строках дано по одному целому неотрицательному числу ai(0≤ai≤10^9), которое обозначает количество сотрудников на этаже номер i.
 *
 * Формат вывода
 * Выведите единственное целое число  — минимальное количество секунд, которое необходимо, чтобы все сотрудники оказались на парковке.
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
        int k = Integer.parseInt(reader.readLine().trim());
        int n = Integer.parseInt(reader.readLine().trim());
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(reader.readLine().trim());
        }

        String solution = alg(k, a);

        writer.write(solution);
        writer.flush();
    }

    // O(N)
    public static String alg(int k, int[] a) {
        BigInteger totalTime = BigInteger.ZERO;
        long peopleRemainder = 0;
        long upFloor = 0;
        for (int i = a.length - 1; i >= 0; i--) {
            long floor = i + 1;

            if (peopleRemainder == 0) {
                // выше не осталось людей - лифт может подниматься до текущего этажа
                upFloor = floor;
            }

            peopleRemainder += a[i]; // люди сверху и на текущем этаже

            if (peopleRemainder >= k) {
                // один раз подняться до последнего этажа, на котором оставались люди, и спуститься с заполнением на следующих этажах
                totalTime = totalTime.add(BigInteger.valueOf(upFloor * 2)); // поднялся до последнего этажа, на котором были люди, и спустился
                peopleRemainder -= k; // спускаясь до текущего этажа, полностью заполнился
                upFloor = floor; // следующий раз подниматься не выше текущего этажа
            }

            if (peopleRemainder >= k) {
                // несколько раз подняться до текущего этажа и спустить k людей
                totalTime = totalTime.add(BigInteger.valueOf((peopleRemainder / k) * (floor * 2))); // несколько раз поднялся до текущего этажа и спустил k людей
                peopleRemainder %= k; // осталось людей на текущем этаже
            }
        }
        if (peopleRemainder != 0) {
            // один раз подняться до последнего этажа, на котором оставались люди, и спуститься с заполнением на следующих этажах
            totalTime = totalTime.add(BigInteger.valueOf(upFloor * 2));
        }
        return totalTime.toString();
    }
}

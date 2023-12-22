package contest27844;

import java.io.*;
import java.util.*;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/27844">Яндекс. Тренировки по алгоритмам июнь 2021, занятие 6. Бинарный поиск</a>
 * <a href="https://contest.yandex.ru/contest/27844/run-report/95174371">OK  446ms  20.30Mb</a>
 *
 * I. Субботник
 * Ограничение времени 	2 секунды
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * В классе учатся N человек.
 * Классный руководитель получил указание направить на субботник R бригад по С человек в каждой.
 *
 * Все бригады на субботнике будут заниматься переноской бревен. Каждое бревно одновременно несут все члены одной бригады.
 * При этом бревно нести тем удобнее, чем менее различается рост членов этой бригады.
 *
 * Числом неудобства бригады будем называть разность между ростом самого высокого и ростом самого низкого членов этой
 * бригады (если в бригаде только один человек, то эта разница равна 0).
 *
 * Классный руководитель решил сформировать бригады так, чтобы максимальное из чисел неудобства сформированных бригад
 * было минимально. Помогите ему в этом!
 *
 * Рассмотрим следующий пример.
 * Пусть в классе 8 человек, рост которых в сантиметрах равен 170, 205, 225, 190, 260, 130, 225, 160,
 * и необходимо сформировать две бригады по три человека в каждой.
 * Тогда одним из вариантов является такой:
 * 1 бригада: люди с ростом 225, 205, 225
 * 2 бригада: люди с ростом 160, 190, 170
 * При этом число неудобства первой бригады будет равно 20, а число неудобства второй — 30.
 * Максимальное из чисел неудобств будет 30, и это будет наилучший возможный результат.
 *
 * Формат ввода
 * Сначала вводятся натуральные числа N, R и C — количество человек в классе, количество бригад и количество человек в каждой бригаде (1 ≤ R∙C ≤ N ≤ 100 000).
 * Далее вводятся N целых чисел — рост каждого из N учеников. Рост ученика — натуральное число, не превышающее 1 000 000 000.
 *
 * Формат вывода
 * Выведите одно число — наименьшее возможное значение максимального числа неудобства сформированных бригад.
 * </pre>
 */
public class I {
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
        int R = buf[1];
        int C = buf[2];
        int[] n = new int[N];
        for (int i = 0; i < N; i++) {
            n[i] = Integer.parseInt(reader.readLine());
        }

        Arrays.sort(n); // O(N logN)

        int[] discomforts = new int[N - (C - 1)];
        for (int i = 0; i < discomforts.length; i++) { // O(N)
            discomforts[i] = n[i + (C - 1)] - n[i];
        }

        int l = 0;
        int r = n[N - 1] - n[0] + 1; // +1 т.к. r exclusive
        while (l < r) { // O(log(10^9))
            int m = (l + r) / 2;
            int maxTeamCount = maxTeamCount(discomforts, C, m); // O(N)
            // дискомфорт можно увеличить, если при нем еще невозможно составить R команд
            if (maxTeamCount < R) {
                l = m + 1;
            } else {
                r = m;
            }
        }

        // l - дискомфорт, при котором уже можно сформировать R бригад по C учеников
        int solution = l;

        writer.write("" + solution);
        writer.flush();
    }

    static int maxTeamCount(int[] discomforts, int teamSize, int maxDiscomfortInclusive) {
        int teamCount = 0;
        for (int i = 0; i < discomforts.length; ) {
            if (discomforts[i] <= maxDiscomfortInclusive) {
                teamCount++;
                i += teamSize;
            } else {
                i++;
            }
        }
        return teamCount;
    }
}

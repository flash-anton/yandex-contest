package contest.algorithms.training1.contest27663;

import java.io.*;
import java.util.*;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/27663">Яндекс. Тренировки по алгоритмам июнь 2021, занятие 3. Множества</a>
 * <a href="https://contest.yandex.ru/contest/27663/run-report/93271526">OK  138ms  10.84Mb</a>
 *
 * H. Злые свинки
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Вы никогда не задумывались, почему в Angry Birds у птиц нет крыльев? Тем же вопросом задались разработчики новой игры.
 * В их версии смысл игры прямо противоположен Angry Birds: зеленая свинка стреляет по злым птицам из лазерного ружья
 * (завязка явно не хуже исходной игры).
 *
 * Птицы в игре представляются точками на плоскости. Выстрел сбивает только ближайшую птицу находящуюся на линии огня.
 * При этом сбитая птица падая сбивает всех птиц, находящихся ровно под ней. Две птицы не могут находиться в одной точке.
 * По заданному расположению птиц необходимо определить, какое минимальное количество выстрелов необходимо,
 * чтобы все птицы были сбиты.
 *
 * Формат ввода
 * Первая строка входного файла содержит единственное целое число N (1≤N≤1000) — количество птиц.
 * Следующие N строк содержат по два натуральных числа каждая xi, yi — координаты i-ой птицы (0<x,y≤10^9).
 * Свинка находится в точке с координатами (0, 0).
 *
 * Формат вывода
 * Единственная строка выходного файла должна содержать одно целое число — минимальное количество выстрелов,
 * необходимое для того, чтобы сбить всех птиц.
 * </pre>
 */
public class H {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        alg(reader, writer);
        reader.close();
        writer.close();
    }

    public static void alg(BufferedReader reader, BufferedWriter writer) throws IOException {
        int N = Integer.parseInt(reader.readLine());

        // Стратегия выстрелов по птицам:
        // - каждую на OY.
        // - верхушку вертикалей (получится, если поворачивать лазер от OY к OX и стрелять сразу при обнаружении цели).

        int OY = 0;

        Set<Integer> verticals = new HashSet<>();
        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(reader.readLine().split(" ")[0]);

            if (x == 0) {
                OY++;
            } else {
                verticals.add(x);
            }
        }

        writer.write(Integer.toString(OY + verticals.size()));
        writer.flush();
    }
}

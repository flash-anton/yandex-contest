package org.example.contest50834;

import java.io.*;
import java.util.*;

/**
 * <pre>
 * Intern week offer 2023 — бэкенд
 * https://contest.yandex.ru/contest/50834/enter
 *
 * D. Раскопки и плитка
 *
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	512Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * В городе М есть два любимых занятия: укладывать плитку и проводить непонятные раскопки тротуара. До начала очередного
 * сезона ремонтов на всех тротуарах была уложена плитка. После проведения раскопок тротуар непригоден для использования,
 * пока на нем не уложат плитку. Всего в городе М k тротуаров.
 * От каждого тротуара без плитки жители города М ежедневно получают одну единицу печали. Если тротуар был раскопан в
 * день a, а плитка на нём была уложена в день b, то жители города получат b−a единиц печали. Плитку можно уложить и в
 * день раскопок, тогда жители получат 0 единиц печали. Тротуар, на котором проводились раскопки может быть снова
 * раскопан и, если на нем была плитка, то она будет сломана.
 * Все раскопки и укладки плитки должны завершиться до выборов мэра города М, при этом на всех раскопанных тротуарах
 * перед выборами должна быть уложена плитка. Всего работ по раскопкам тротуаров планируется n.
 * Руководителю компании-подрядчика по укладке плитки попал в руке план раскопок тротуаров с указанием номера тротуара и
 * номера дня, когда тротуар будет раскопан. К сожалению, в бюджете выделены средства только для m укладок плитки на
 * тротуарах. Зато в компании достаточно трудолюбивых работников чтобы уложить любое количество плитки в любой из дней.
 * Определите, какое наименьшее суммарное количество единиц печали получат жители города М до выборов мэра.
 *
 * Формат ввода
 * В первой строке вводятся числа k,n и m (1≤k≤1000, 1≤n,m≤100000) — количество тротуаров, количество раскопок и
 * количество укладок плитки, на которые выделены средства в бюджете.
 * В следующих n строках заданы пары чисел di,wi (1≤di≤10^9, 1≤wi≤K) — день, в который будут проведены раскопки и номер
 * тротуара, на которых они будут проводиться. Эти пары заданы в порядке неубывания di.
 *
 * Формат вывода
 * Выведите одно число — минимальное суммарное количество единиц печали. Если до выборов останутся раскопанные
 * тротуары — выведите −1, как признак бесконечной печали жителей.
 *
 * Примечания
 * В первом примере на тротуарах 1 и 3 нужно уложить плитку в день раскопок, а тротуар 2 ремонтируется на шестой день,
 * сразу после проведения раскопок.
 *
 * WA 421ms 30.08Mb 13
 * </pre>
 */
public class D {
    private final InputStream in;
    private final OutputStream out;

    public D(InputStream in, OutputStream out) {
        this.in = in;
        this.out = out;
    }

    public void execute() throws IOException {
        byte[] buf = new byte[32];
        int k = readInt(buf);
        int n = readInt(buf);
        int m = readInt(buf);

        int lastDay = 0;
        Map<Integer, ArrayList<Integer>> days = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int di = readInt(buf);
            int wi = readInt(buf);
            days.computeIfAbsent(wi, key -> new ArrayList<>()).add(di);
            lastDay = di;
        }
        lastDay++;

        int sadness = -1;
        if (days.keySet().size() <= m) {
            for (ArrayList<Integer> dList : days.values()) {
                for (int i = 0; i < dList.size() - 1; i++) {
                    int dDif = dList.get(i + 1) - dList.get(i);
                    dList.set(i, dDif);
                }
                int dDif = lastDay - dList.get(dList.size() - 1);
                dList.set(dList.size() - 1, dDif);
            }

            PriorityQueue<Integer> p = new PriorityQueue<>(Comparator.reverseOrder());
            for (ArrayList<Integer> dList : days.values()) {
                p.offer(dList.remove(dList.size() - 1));
            }
            while (!p.isEmpty() && m-- > 0) {
                p.poll();
            }

            for (ArrayList<Integer> dList : days.values()) {
                while (!dList.isEmpty())
                    p.offer(dList.remove(0));
            }
            while (!p.isEmpty() && m-- > 0) {
                p.poll();
            }

            sadness = p.stream().mapToInt(Integer::intValue).sum();
        }

        buf = Integer.toString(sadness).getBytes();
        out.write(buf, 0, buf.length);
    }

    private int readInt(byte[] buf) throws IOException {
        int size = 0;
        byte b;
        while ((b = (byte) in.read()) != -1 && b != '\n' && b != ' ')
            buf[size++] = b;
        return Integer.parseInt(new String(buf, 0, size));
    }

    public static void main(String[] args) throws IOException {
        try (InputStream in = new BufferedInputStream(System.in);
             OutputStream out = new BufferedOutputStream(System.out)) {
            new D(in, out).execute();
        }
    }
}

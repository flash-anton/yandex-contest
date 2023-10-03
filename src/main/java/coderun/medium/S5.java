package coderun.medium;

import java.io.*;
import java.util.*;

/**
 * <pre>
 * <a href="https://coderun.yandex.ru/problem/cafe">5. Кафе</a>
 * средняя dynamic programming 2D
 *
 * Около Петиного университета недавно открылось новое кафе, в котором действует следующая система скидок: при каждой
 * покупке более чем на 100 рублей покупатель получает купон, дающий право на один бесплатный обед (при покупке на сумму
 * 100 рублей и меньше такой купон покупатель не получает).
 *
 * Однажды Пете на глаза попался прейскурант на ближайшие N дней. Внимательно его изучив, он решил, что будет обедать в
 * этом кафе все N дней, причем каждый день он будет покупать в кафе ровно один обед. Однако стипендия у Пети небольшая,
 * и поэтому он хочет по максимуму использовать предоставляемую систему скидок так, чтобы его суммарные затраты были
 * минимальны. Требуется найти минимально возможную суммарную стоимость обедов и номера дней, в которые Пете следует
 * воспользоваться купонами.
 *
 * Формат ввода
 * В первой строке входного файла записано целое число N (0 ≤ N ≤ 100). В каждой из последующих N строк записано одно
 * целое число, обозначающее стоимость обеда в рублях на соответствующий день. Стоимость — неотрицательное целое число,
 * не превосходящее 300.
 *
 * Формат вывода
 * В первой строке выдайте минимальную возможную суммарную стоимость обедов.
 * Во второй строке выдайте два числа K1 и K2 — количество купонов, которые останутся неиспользованными у Пети после
 * этих N дней и количество использованных им купонов соответственно.
 *
 * В последующих K2 строках выдайте в возрастающем порядке номера дней, когда Пете следует воспользоваться купонами.
 * Если существует несколько решений с минимальной суммарной стоимостью, то выдайте то из них, в котором значение K1
 * максимально (на случай, если Петя когда-нибудь ещё решит заглянуть в это кафе). Если таких решений несколько,
 * выведите любое из них.
 *
 * 22  OK  285 мс / 1000 мс  14.1 Мб / 64 Мб
 * </pre>
 */
public class S5 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        alg(reader, writer);
        reader.close();
        writer.close();
    }

    public static void alg(BufferedReader reader, BufferedWriter writer) throws IOException {
        byte N = Byte.parseByte(reader.readLine());
        int[] prices = new int[N];
        for (int i = 0; i < N; i++) {
            prices[i] = Integer.parseInt(reader.readLine());
        }

        State s = new State(0, 0);
        Map<State, State> next = new HashMap<>();
        Balance b = getFinalBalance(prices, s, new HashMap<>(), next);

        List<Integer> couponDays = new ArrayList<>();
        while (s.day < prices.length) {
            int coupons = s.coupons;
            if ((s = next.get(s)).coupons < coupons) {
                couponDays.add(s.day);
            }
        }

        if (b.isWrong()) {
            writer.write("0\n0 0");
        } else {
            writer.write(String.format("%d\n%d %d", b.money, b.coupons, couponDays.size()));
            for (int day : couponDays) {
                writer.write("\n" + day);
            }
        }
        writer.flush();
    }

    static Balance getFinalBalance(int[] prices, State s, Map<State, Balance> results, Map<State, State> next) {
        if (s.day >= prices.length || s.coupons < 0) {
            return Balance.WRONG;
        }

        if (results.containsKey(s)) {
            return results.get(s);
        }

        Balance b = new Balance(s.coupons, 0);

        Balance b1 = b.pay(prices[s.day]);
        Balance b2 = b.useCoupon();
        State s1 = new State(s.day + 1, b1.coupons);
        State s2 = new State(s.day + 1, b2.coupons);

        if (s.day < prices.length - 1) {
            b1 = getFinalBalance(prices, s1, results, next).add(0, b1.money);
            b2 = getFinalBalance(prices, s2, results, next);
        }

        b = Balance.best(b1, b2);
        results.put(s, b);
        next.put(s, b == b1 ? s1 : s2);
        return b;
    }

    record State(int day, int coupons) {
    }

    record Balance(int coupons, int money) {
        static final Balance WRONG = new Balance(Integer.MIN_VALUE, Integer.MIN_VALUE);

        static Balance best(Balance a, Balance b) {
            boolean isAWrong = a.isWrong();
            boolean isBWrong = b.isWrong();
            return (isAWrong && isBWrong) ? WRONG
                    : isAWrong ? b
                    : isBWrong ? a
                    : a.money < b.money ? a
                    : b.money < a.money ? b
                    : a.coupons >= b.coupons ? a
                    : b;
        }

        boolean isWrong() {
            return this == WRONG || money < 0 || coupons < 0;
        }

        Balance pay(int price) {
            return add(price > 100 ? 1 : 0, price);
        }

        Balance useCoupon() {
            return add(-1, 0);
        }

        Balance add(int coupons, int money) {
            Balance b = new Balance(this.coupons + coupons, this.money + money);
            return (this.isWrong() || b.isWrong()) ? WRONG : b;
        }
    }
}

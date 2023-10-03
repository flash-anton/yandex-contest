package coderun.easy;

import java.io.*;
import java.util.*;

/**
 * <pre>
 * <a href="https://coderun.yandex.ru/problem/pin">1. Гвоздики</a>
 * легкая dynamic programming 1D
 *
 * В дощечке в один ряд вбиты гвоздики. Любые два гвоздика можно соединить ниточкой. Требуется соединить некоторые пары
 * гвоздиков ниточками так, чтобы к каждому гвоздику была привязана хотя бы одна ниточка, а суммарная длина всех ниточек
 * была минимальна.
 *
 * Формат ввода
 * В первой строке входных данных записано целое число N (2≤N≤100) — количество гвоздиков.
 * В следующей строке задано N попарно различных целых чисел ai (0≤ai≤10^4) — координаты гвоздиков.
 *
 * Формат вывода
 * Выведите единственное число — минимальную суммарную длину всех ниточек.
 *
 * ОК  87 мс / 1000 мс  8.5 Мб / 64 Мб
 * </pre>
 */
public class S1 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        alg(reader, writer);
        reader.close();
        writer.close();
    }

    public static void alg(BufferedReader reader, BufferedWriter writer) throws IOException {
        int N = Integer.parseInt(reader.readLine());
        String[] aStr = reader.readLine().trim().split(" ");

        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = Integer.parseInt(aStr[i]);
        }

        Arrays.sort(a);

        int M = N - 1;
        int[] d = new int[M];
        for (int i = 0; i < M; i++) {
            d[i] = a[i + 1] - a[i];
        }

        int[] cash0 = new int[M+1];
        Arrays.fill(cash0, -1);
        int[] cash1 = new int[M];
        Arrays.fill(cash1, -1);
        int sum = minSum(d, 0, true, cash0, cash1);

        writer.write(Integer.toString(sum));
        writer.flush();
    }

    private static int minSum(int[] d, int i, boolean mandatory, int[] cash0, int[] cash1) {
        int[] cash = mandatory ? cash1 : cash0;
        if (cash[i] >= 0) {
            return cash[i];
        }

        int minSum = 0;
        if (i == 0) {
            minSum = d[0] + minSum(d, 1, false, cash0, cash1);
        } else if (i < d.length - 1) {
            // 1 0 ...
            // 1 1 ...
            // 0 1 ... if current is not mandatory
            int next0 = minSum(d, i + 1, false, cash0, cash1);
            int next1 = minSum(d, i + 1, true, cash0, cash1);
            minSum = d[i] + Integer.min(next0, next1);
            if (!mandatory) {
                minSum = Integer.min(minSum, next1);
            }
        } else if (i == d.length - 1) {
            minSum = d[i];
        }

        return cash[i] = minSum;
    }
}

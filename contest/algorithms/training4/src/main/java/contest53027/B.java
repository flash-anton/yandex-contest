package contest53027;

import java.io.*;
import java.util.Arrays;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/53027">Яндекс. Тренировки по алгоритмам 4.0. Разминка</a>
 * <a href="https://contest.yandex.ru/contest/53027/run-report/95493899">OK  119ms  12.06Mb</a>
 *
 * B. Сложить две дроби
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Даны две рациональные дроби: a/b и c/d. Сложите их и результат представьте в виде несократимой дроби m/n.
 *
 * Формат ввода
 * Программа получает на вход 4 натуральных числа a, b, c, d, каждое из которых не больше 100.
 *
 * Формат вывода
 * Программа должна вывести два натуральных числа m и n такие, что m/n=a/b+c/d и дробь m/n – несократима.
 * </pre>
 */
public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        alg(reader, writer);
        reader.close();
        writer.close();
    }

    public static void alg(BufferedReader reader, BufferedWriter writer) throws IOException {
        int[] ints = Arrays.stream(reader.readLine().split(" ")).filter(s -> !s.isEmpty()).mapToInt(Integer::parseInt).toArray();
        int a = ints[0];
        int b = ints[1];
        int c = ints[2];
        int d = ints[3];

        String solution = alg(a, b, c, d);

        writer.write(solution);
        writer.flush();
    }

    public static String alg(int a, int b, int c, int d) {
        int m = a * d + c * b;
        int n = b * d;
        int gcd = gcd(m, n);
        return (m / gcd) + " " + (n / gcd);
    }

    public static int gcd(int a, int b) {
        return (b == 0) ? a : gcd(b, a % b);
    }
}

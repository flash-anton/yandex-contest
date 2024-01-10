package contest36783;

import java.io.*;
import java.util.Arrays;

import static java.lang.Math.max;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/36783">Interview training 36783</a>
 * <a href="https://contest.yandex.ru/contest/36783/run-report/104538622">OK  288ms  19.68Mb</a>
 *
 * B. Card Counter
 * Язык 	Ограничение времени 	Ограничение памяти 	Ввод 	Вывод
 * Все языки 	0.25 секунд 	256Mb 	стандартный ввод или input.txt 	стандартный вывод или output.txt
 * Node.js 14.15.5 	1 секунда 	256Mb
 * Python 3.7.3 	1 секунда 	256Mb
 * OpenJDK Java 11 	0.5 секунд 	256Mb
 * C# (MS .NET 6.0 + ASP) 	1 секунда 	256Mb
 * OpenJDK Java 15 	0.5 секунд 	256Mb
 * C# (MS .NET 5.0 + ASP) 	1 секунда 	256Mb
 *
 * На стол в ряд выложены карточки, на каждой карточке написано натуральное число.
 * За один ход разрешается взять карточку либо с левого, либо с правого конца ряда.
 * Всего можно сделать k ходов.
 * Итоговый счет равен сумме чисел на выбранных карточках.
 * Определите, какой максимальный счет можно получить по итогам игры.
 *
 * Вы можете воспользоваться заготовками кода для данной задачи из <a href="https://github.com/Yandex-Practicum/algorithms-templates">репозитория курса</a>
 *
 * Формат ввода
 * В первой строке записано число карточек n (1≤n≤10^5).
 * Во второй строке записано число ходов k (1≤k≤n).
 * В третьей строке через пробел даны числа, записанные на карточках.
 * i-е по счету число записано на i-й слева карточке. Все числа натуральные и не превосходят 10^4.
 *
 * Формат вывода
 * Выведите единственное число —- максимальную сумму очков, которую можно набрать, сделав k ходов. .
 * </pre>
 */
public class B {
    public static void main(String[] args) throws IOException {
        try (InputStream reader = new BufferedInputStream(System.in, 3_000_000);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            alg(reader, writer);
        }
    }

    public static void alg(InputStream is, BufferedWriter writer) throws IOException {
        Reader reader = new Reader(is);
        int n = reader.nextInt();
        int k = reader.nextInt();
        int[] a = reader.nextInts(new int[n]);

        String solution = alg1(n, k, a);

        writer.write(solution);
        writer.flush();
    }

    public static String alg1(int n, int k, int[] a) {
        int sum = Arrays.stream(a, n - k, n).sum();
        int maxSum = sum;
        for (int L = 0, R = n - k; L < k; L++, R++) {
            sum = sum - a[R] + a[L];
            maxSum = max(maxSum, sum);
        }
        return "" + maxSum;
    }

    /**
     * "Быстрый" ридер потока.
     */
    public static class Reader {
        private final InputStream is;
        private int lastReadByte = '\n';

        public Reader(InputStream is) {
            this.is = is;
        }

        public long nextLong() throws IOException {
            while (lastReadByte == ' ' || lastReadByte == '\n') {
                lastReadByte = is.read();
            }

            int sign = 1;
            if (lastReadByte == '-') {
                sign = -1;
                lastReadByte = is.read();
            }

            long num = 0;
            while (lastReadByte >= '0' && lastReadByte <= '9') {
                num = (num * 10) + sign * (lastReadByte - '0');
                lastReadByte = is.read();
            }
            return num;
        }

        public long[] nextLongs(long[] a) throws IOException {
            for (int i = 0; i < a.length; i++) {
                a[i] = nextLong();
            }
            return a;
        }

        public int nextInt() throws IOException {
            return (int) nextLong();
        }

        public int[] nextInts(int[] a) throws IOException {
            for (int i = 0; i < a.length; i++) {
                a[i] = nextInt();
            }
            return a;
        }

        public String nextWord() throws IOException {
            while (lastReadByte == ' ' || lastReadByte == '\n') {
                lastReadByte = is.read();
            }

            StringBuilder sb = new StringBuilder();
            while (!(lastReadByte == -1 || lastReadByte == ' ' || lastReadByte == '\n')) {
                sb.append((char) lastReadByte);
                lastReadByte = is.read();
            }
            return sb.toString();
        }

        public String[] nextWords(String[] a) throws IOException {
            for (int i = 0; i < a.length; i++) {
                a[i] = nextWord();
            }
            return a;
        }

        public String readLine() throws IOException {
            if (lastReadByte == '\n') {
                lastReadByte = is.read();
            }

            StringBuilder sb = new StringBuilder();
            while (!(lastReadByte == -1 || lastReadByte == '\n')) {
                sb.append((char) lastReadByte);
                lastReadByte = is.read();
            }
            return sb.toString();
        }
    }
}

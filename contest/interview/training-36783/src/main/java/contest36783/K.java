package contest36783;

import java.io.*;
import java.util.regex.Pattern;

import static java.lang.Math.max;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/36783">Interview training 36783</a>
 * <a href="https://contest.yandex.ru/contest/36783/run-report/104987711">OK  300ms  28.08Mb</a>
 *
 * K. Разрыв шаблона
 * Язык 	Ограничение времени 	Ограничение памяти 	Ввод 	Вывод
 * Все языки 	1 секунда 	256Mb 	стандартный ввод или input.txt 	стандартный вывод или output.txt
 * Node.js 14.15.5 	2 секунды 	256Mb
 * Python 3.7.3 	2 секунды 	256Mb
 * Golang 1.20.1 	0.3 секунды 	64Mb
 * GNU c++17 7.3 	0.3 секунды 	64Mb
 * GNU GCC 12.2 C++20 	0.3 секунды 	64Mb
 *
 * В этой задаче вам надо определить, подходит ли строка под конкретный шаблон. Шаблон задаётся в следующем формате:
 *     Символ «?» соответствует одному вхождению любого символа;
 *     Символ «*» соответствует произвольному числу любых символов, в том числе нулю символов;
 *     Остальные символы шаблона должны совпадать с символами строки;
 *
 * Вы можете воспользоваться заготовками кода для данной задачи из репозитория курса.
 *
 * Формат ввода
 * В первой строке дан шаблон.
 * Во второй — проверяемая строка.
 * Шаблон и строка не превосходят в длину 2000 символов и не могут быть пустыми.
 * Строка состоит только из строчных латинских букв.
 * Шаблон состоит из строчных латинских букв и знаков «?» и «*».
 *
 * Формат вывода
 * Выведите «YES», если строка подходит под шаблон, и «NO», если не подходит. .
 * </pre>
 */
public class K {
    public static void main(String[] args) throws IOException {
        try (InputStream reader = new BufferedInputStream(System.in, 3_000_000);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            alg(reader, writer);
        }
    }

    public static void alg(InputStream is, BufferedWriter writer) throws IOException {
        Reader reader = new Reader(is);
        String template = reader.nextWord();
        String request = reader.nextWord();

        String solution = alg3(template, request);

        writer.write(solution);
        writer.flush();
    }

    /**
     * 104987711  OK  300ms  28.08Mb
     */
    public static String alg3(String template, String request) {
        template = template.replaceAll("\\*+", "*");

        int T = template.length();
        int R = request.length();
        int[][] buf = new int[1 + T][1 + R];

        char t = template.charAt(0);
        char r = request.charAt(0);
        if (t == '?' || t == r) {
            buf[1][1] = 1;
        }

        for (int ti = 1; ti < T; ti++) {
            int[] prev = buf[ti];
            int[] cur = buf[ti + 1];

            t = template.charAt(ti);
            if (t == '*') {
                for (int ri = 0; ri < R; ri++) {
                    cur[ri + 1] = max(cur[ri], prev[ri + 1]);
                }
            } else {
                for (int ri = 0; ri < R; ri++) {
                    r = request.charAt(ri);
                    if (t == '?' || t == r) {
                        cur[ri + 1] = prev[ri] + 1;
                    }
                }
            }
        }

        int letters = (int) template.chars().filter(c -> c != '*').count();

        return  buf[T][R] == letters ? "YES" : "NO";
    }

    /**
     * 104951053  TL  1.084s  18.79Mb  28
     */
    public static String alg1(String template, String request) {
        template = template.replaceAll("\\*+", ".*").replaceAll("\\?", ".");
        boolean matches = Pattern.matches(template, request);
        return matches ? "YES" : "NO";
    }

    /**
     * 104950678  TL  1.085s  17.45Mb  31
     */
    public static String alg2(String template, String request) {
        template = template.replaceAll("\\*+", "*");
        boolean matches = matches(template, request, 0, 0);
        return matches ? "YES" : "NO";
    }

    static boolean matches(String template, String request, int templateIndex, int requestIndex) {
        while (templateIndex < template.length() && requestIndex < request.length()) {
            char t = template.charAt(templateIndex);
            char r = request.charAt(requestIndex);

            if (t == '?' || t == r) {
                templateIndex++;
                requestIndex++;

            } else if (t == '*') {
                templateIndex++;
                for (int i = requestIndex; i <= request.length(); i++) {
                    if (matches(template, request, templateIndex, i)) {
                        return true;
                    }
                }
                return false;

            } else { // t != r
                return false;
            }
        }

        if (templateIndex == template.length() && requestIndex == request.length())
            return true;

        if (templateIndex == template.length()) // requestIndex != request.length()
            return false;

        if (template.length() - templateIndex > 1)
            return false;

        return template.charAt(templateIndex) == '*';
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

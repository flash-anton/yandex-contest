package contest36783;

import java.io.*;
import java.util.ArrayDeque;

import static java.lang.Math.abs;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/36783">Interview training 36783</a>
 * <a href="https://contest.yandex.ru/contest/36783/run-report/104622279">OK  279ms  21.80Mb</a>
 *
 * F. Хорошие строки
 * Язык 	Ограничение времени 	Ограничение памяти 	Ввод 	Вывод
 * Все языки 	1 секунда 	256Mb 	стандартный ввод или input.txt 	стандартный вывод или output.txt
 * Python 3.7.3 	2 секунды 	256Mb
 * Golang 1.20.1 	0.1 секунда 	64Mb
 * GNU c++17 7.3 	0.1 секунда 	64Mb
 * GNU GCC 12.2 C++20 	0.1 секунда 	64Mb
 *
 * Назовем строку хорошей, если в ней нет двух соседних букв, которые различаются только регистром.
 * Например, строка «abba» хорошая, а строка «aBba» нет.
 *
 * Со строкой можно делать преобразование:
 * если два соседних символа обозначают одну и ту же букву, но записаны в разных регистрах, то их можно удалить.
 * При этом строка «схлопнется», то есть пробелов при удалении не образуется.
 *
 * Цепочкой таких преобразований можно превратить любую строку в хорошую.
 *
 * По заданной строке найдите хорошую строку, в которую ее можно превратить.
 *
 * Вы можете воспользоваться заготовками кода для данной задачи из репозитория курса.
 *
 * Формат ввода
 * На вход подаётся строка, состоящая из больших и маленьких латинских букв. Длина строки не превосходит 10^5.
 *
 * Формат вывода
 * Выведите хорошую строку, в которую можно превратить данную.
 * </pre>
 */
public class F {
    public static void main(String[] args) throws IOException {
        try (InputStream reader = new BufferedInputStream(System.in, 3_000_000);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            alg(reader, writer);
        }
    }

    public static void alg(InputStream is, BufferedWriter writer) throws IOException {
        Reader reader = new Reader(is);
        String s = reader.readLine();

        String solution = alg1(s);

        writer.write(solution);
        writer.flush();
    }

    public static String alg1(String s) {
        StringBuilder sb = new StringBuilder();

        int DIF = 'a' - 'A';

        for (char c : s.toCharArray()) {
            if (sb.length() == 0) {
                sb.append(c);
            } else {
                int i = sb.length() - 1;
                char C = sb.charAt(i);
                if (abs(c - C) == DIF) {
                    sb.deleteCharAt(i);
                } else {
                    sb.append(c);
                }
            }
        }

        return sb.toString();
    }

    public static String alg2(String s) {
        ArrayDeque<Character> stack = new ArrayDeque<>();

        int DIF = 'a' - 'A';

        for (char c : s.toCharArray()) {
            Character cOnStack = stack.peek();
            if (cOnStack == null || abs(c - cOnStack) != DIF) {
                stack.push(c);
            } else {
                stack.pop();
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pollLast());
        }

        return sb.toString();
    }

    public static String alg3(String s) {
        StringBuilder sb = new StringBuilder(s);

        int DIF = 'a' - 'A';

        int i = 0;
        while (i < sb.length() - 1) {
            char a = sb.charAt(i);
            char b = sb.charAt(i + 1);
            int dif = abs(a - b);
            if (dif == DIF) {
                sb.delete(i, i + 2);
                if (i > 0) {
                    i--;
                }
            } else {
                i++;
            }
        }

        return sb.toString();
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

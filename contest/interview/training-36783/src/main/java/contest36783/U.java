package contest36783;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/36783">Interview training 36783</a>
 * <a href="https://contest.yandex.ru/contest/36783/run-report/105318919">OK  497ms  47.34Mb</a>
 *
 * U. Опять скобочные последовательности
 * Язык 	Ограничение времени 	Ограничение памяти 	Ввод 	Вывод
 * Все языки 	1.5 секунд 	256Mb 	стандартный ввод или input.txt 	стандартный вывод или output.txt
 * Node.js 14.15.5 	3 секунды 	256Mb
 * Python 3.7.3 	3 секунды 	256Mb
 * Golang 1.20.1 	1 секунда 	64Mb
 * GNU c++17 7.3 	1 секунда 	64Mb
 *
 * В этой задаче под правильной скобочной последовательностью (ПСП) понимается правильная скобочная последовательность
 * из круглых и квадратных скобок, где ни одна пара квадратных скобок не может содержать пару круглых скобок.
 *
 * По данному числу n выведите все ПСП из n скобок в лексикографическом порядке.
 *
 * Упорядоченность символов следующая: ’(’ < ’[’ < ’)’ < ’]’
 *
 * Вы можете воспользоваться заготовками кода для данной задачи из репозитория курса.
 *
 * Формат ввода
 * В единственной строке дано число скобок n, 0≤n≤16.
 *
 * Формат вывода
 * Выведите правильные скобочные последовательности в лексикографическом порядке по одной в строке.
 * Скобки внутри одной строки идут подряд и не разделяются пробелами .
 * </pre>
 */
public class U {
    public static void main(String[] args) throws IOException {
        try (InputStream reader = new BufferedInputStream(System.in, 3_000_000);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            alg(reader, writer);
        }
    }

    public static void alg(InputStream is, BufferedWriter writer) throws IOException {
        Reader reader = new Reader(is);
        int n = reader.nextInt();

        StringBuilder result = new StringBuilder();
        if (n % 2 == 0)
            f(n, new ArrayDeque<>(), new ArrayDeque<>(), 0, result);
        String solution = result.toString();

        writer.write(solution);
        writer.flush();
    }

    static void f(int n, Deque<Character> brackets, Deque<Character> forClose, int openedSquareBracketCount, StringBuilder result) {
        if (n == brackets.size() + forClose.size()) {
            Iterator<Character> it = brackets.descendingIterator();
            while (it.hasNext()) {
                result.append(it.next());
            }
            it = forClose.iterator();
            while (it.hasNext()) {
                result.append(it.next());
            }
            result.append('\n');

        } else {
            if (openedSquareBracketCount == 0) {
                brackets.push('(');
                forClose.push(')');
                f(n, brackets, forClose, openedSquareBracketCount, result);
                forClose.pop();
                brackets.pop();
            }

            brackets.push('[');
            forClose.push(']');
            f(n, brackets, forClose, openedSquareBracketCount + 1, result);
            forClose.pop();
            brackets.pop();

            if (!forClose.isEmpty()) {
                char closeBracket = forClose.pop();
                brackets.push(closeBracket);
                f(n, brackets, forClose, openedSquareBracketCount - (closeBracket == ']' ? 1 : 0), result);
                brackets.pop();
                forClose.push(closeBracket);
            }
        }
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

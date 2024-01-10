package contest36783;

import java.io.*;
import java.util.*;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/36783">Interview training 36783</a>
 * <a href="https://contest.yandex.ru/contest/36783/run-report/104595204">OK  238ms  18.65Mb</a>
 *
 * E. Римлянин
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	256Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Вам дано число, записанное римскими цифрами. Получите это же число в обычной записи (арабскими цифрами).
 *
 * Римская запись чисел может включать следующие символы:
 *     ’I’ — 1
 *     ’V’ — 5
 *     ’X’ — 10
 *     ’L’ — 50
 *     ’C’ — 100
 *     ’D’ — 500
 *     ’M’ — 1000
 *
 * Цифры ’I’, ’X’, ’C’ и ’M’ нельзя использовать более трех раз подряд.
 * Цифры ’V’, ’L’ и ’D’ нельзя использовать более одного раза во всей записи числа.
 *
 * Обыкновенно цифры записывают по убыванию слева направо. Например, число 350 будет записано как ’CCCL’.
 *
 * Однако есть исключения:
 *     Чтобы получить ’4’ или ’9’, надо поставить ’I’ перед ’V’ или ’X’ соответственно
 *     Чтобы получить ’40’ или ’90’, надо поставить ’X’ перед ’L’ или ’C’.
 *     Чтобы получить ’400’ или ’900’, надо поставить ’C’ перед ’D’ или ’M’.
 *
 * Вы можете воспользоваться заготовками кода для данной задачи из репозитория курса.
 *
 * Формат ввода
 * В единственной строке дана запись числа римскими цифрами. Длина записи не превосходит 15.
 *
 * Формат вывода
 * Выведите число, записанное арабскими цифрами. Если запись числа некорректная, то выведите -1.
 * </pre>
 */
public class E {
    public static void main(String[] args) throws IOException {
        try (InputStream reader = new BufferedInputStream(System.in, 3_000_000);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            alg(reader, writer);
        }
    }

    public static void alg(InputStream is, BufferedWriter writer) throws IOException {
        Reader reader = new Reader(is);
        String romeNumber = reader.readLine().strip();

        String solution = alg1(romeNumber);

        writer.write(solution);
        writer.flush();
    }

    static class Num {
        final String roman;
        final int arabian;

        Num(String roman, int arabian) {
            this.roman = roman;
            this.arabian = arabian;
        }
    }


    static Num extract(String s, char one, char five, char ten) {
        StringBuilder roman = new StringBuilder();
        int arabian = 0;

        Map<Character, Integer> char2num = Map.of(one, 1, five, 5, ten, 10);

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (arabian == 1 && Set.of(five, ten).contains(c)) {
                roman.append(c);
                arabian = char2num.get(c) - 1;

            } else if (arabian == 0 && Set.of(one, five).contains(c)) {
                roman.append(c);
                arabian = char2num.get(c);

            } else if (Set.of(1, 2, 5, 6, 7).contains(arabian) && c == one) {
                roman.append(c);
                arabian += 1;

            } else {
                return new Num(roman.toString(), arabian);
            }
        }
        return new Num(roman.toString(), arabian);
    }

    public static String alg1(String romeNumber) {
        Num thousands = extract(romeNumber, 'M', '-', '+');
        romeNumber = romeNumber.substring(thousands.roman.length());

        Num hundreds = extract(romeNumber, 'C', 'D', 'M');
        romeNumber = romeNumber.substring(hundreds.roman.length());

        Num tens = extract(romeNumber, 'X', 'L', 'C');
        romeNumber = romeNumber.substring(tens.roman.length());

        Num ones = extract(romeNumber, 'I', 'V', 'X');
        romeNumber = romeNumber.substring(ones.roman.length());

        if (romeNumber.isEmpty()) {
            return "" + (thousands.arabian * 1000 + hundreds.arabian * 100 + tens.arabian * 10 + ones.arabian);
        } else {
            return "-1";
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

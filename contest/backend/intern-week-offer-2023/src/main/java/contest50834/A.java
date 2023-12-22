package contest50834;

import java.io.*;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/50834/enter">Intern week offer 2023 — бэкенд</a>
 *
 * A. USB-порты
 *
 * Ограничение времени 	2 секунды
 * Ограничение памяти 	256Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Вася подсчитал, что у него есть m гаджетов, которые нужно подключить к USB и всего n USB-портов на компьютере. В
 * ближайшем интернет-магазине продаются разветвители с одного USB на два за c2 рублей и USB-хабы с одного USB на 5 по
 * c5 рублей.
 * Разветвители и хабы можно подключать друг к другу и к USB-портам компьютера. Определите, какое минимальное количество
 * рублей нужно потратить Васе, чтобы подключить все USB устройства. При этом можно обеспечить возможность подключить и
 * больше m гаджетов, главное минимизировать цену.
 *
 * Формат ввода
 * В первой строке входных данных записано число n (1≤n≤10^15) — количество USB-портов у компьютера. Во второй строке
 * записано число m (1≤m≤10^15) — количество USB-гаджетов.
 * В следующих двух строках записаны целые числа c2 и c5 (1≤c2,c5≤1000)
 *
 * Формат вывода
 * Выведите одно целое число — минимальное количество рублей, которое надо потратить для покупки необходимого количества
 * разветвителей и хабов.
 *
 * OK 89ms 8.45Mb
 * </pre>
 */
public class A {
    private final InputStream in;
    private final OutputStream out;

    public A(InputStream in, OutputStream out) {
        this.in = in;
        this.out = out;
    }

    public void execute() throws IOException {
        byte[] buf = new byte[20];
        long n = readLong(buf);
        long m = readLong(buf);
        int c2 = (int) readLong(buf); // +1 USB price
        int c5 = (int) readLong(buf); // +4 USB price

        long total = 0;
        long mRemainder = m - n;

        if (mRemainder > 0) {
            if (c2 * 4 < c5) {
                total = mRemainder * c2;
            } else {
                total = mRemainder / 4 * c5;
                mRemainder %= 4;

                if (mRemainder > 0) {
                    if (c2 * mRemainder < c5) {
                        total += mRemainder * c2;
                    } else {
                        total += c5;
                    }
                }
            }
        }

        buf = Long.toString(total).getBytes();
        out.write(buf, 0, buf.length);
    }

    private long readLong(byte[] buf) throws IOException {
        int size = 0;
        byte b;
        while ((b = (byte) in.read()) != -1 && b != '\n')
            buf[size++] = b;
        return Long.parseLong(new String(buf, 0, size));
    }

    public static void main(String[] args) throws IOException {
        try (InputStream in = new BufferedInputStream(System.in);
             OutputStream out = new BufferedOutputStream(System.out)) {
            new A(in, out).execute();
        }
    }
}

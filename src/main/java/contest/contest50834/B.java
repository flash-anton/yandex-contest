package contest.contest50834;

import java.io.*;
import java.util.Arrays;

/**
 * <pre>
 * Intern week offer 2023 — бэкенд
 * https://contest.yandex.ru/contest/50834/enter
 *
 * B. Ровный забор
 *
 * Ограничение времени 	2 секунды
 * Ограничение памяти 	256Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Вася разобрал на даче старый сарай на n досок одинаковой ширины и толщины, но разной длины. Он решил построить из них
 * забор. Высота забора не имеет для Васи особого значения, главное чтобы он был как можно более ровным. Неровность
 * забора определяется как разница высот между самой короткой и самой длинной доской, использованной для постройки
 * забора. После тщательного измерения оказалось, что k из n досок лишние и их можно не использовать для строительства
 * забора.
 * Определите минимальную неровность забора, который можно построить из n−k досок.
 *
 * Формат ввода
 * В первой строке входных данных записаны два числа n и k (0≤k<n≤200000) — количество досок от разбора сарая и
 * количество лишних досок.
 * Во второй строке записаны n целых чисел li (1≤li≤10^9) — длины досок.
 *
 * Формат вывода
 * Выведите единственное целое число — минимальную неровность забора.
 *
 * Примечания
 * В первом примере нет лишних досок, поэтому неровность равна 19−15=4.
 * Во втором примере можно не использовать первую и четвертую доску для строительства забора.
 *
 * WA 101ms 9.21Mb 6
 * </pre>
 */
public class B {
    private final InputStream in;
    private final OutputStream out;

    public B(InputStream in, OutputStream out) {
        this.in = in;
        this.out = out;
    }

    public void execute() throws IOException {
        byte[] buf = new byte[10];
        int n = readInt(buf);
        int k = readInt(buf);
        int[] l = new int[n];
        for (int i = 0; i < n; i++)
            l[i] = readInt(buf);

        Arrays.sort(l);

        for (int i = n - 1; i > 0; i--)
            l[i] -= l[i - 1];
        l[0] = 0;

        int i = 1;
        int j = n-1;
        while (k-- > 0) {
            if (l[i] > l[j]) l[i++] = 0;
            else             l[j--] = 0;
        }

        int min = Arrays.stream(l).sum();

        buf = Integer.toString(min).getBytes();
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
            new B(in, out).execute();
        }
    }
}

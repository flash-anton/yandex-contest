package org.example.contest3;

import java.io.*;

/**
 * <pre>
 * Ознакомительный контест
 * https://contest.yandex.ru/contest/3/enter
 *
 * C. A+B 3
 *
 * Ограничение времени 	2 секунды
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод
 * Вывод 	стандартный вывод
 *
 * Даны два числа A и B. Вам нужно вычислить их сумму A+B.
 * В этой задаче вам нужно читать из стандартного ввода и выводить ответ в стандартный вывод.
 *
 * Формат ввода
 * Первая строка входа содержит числа A и B (−2*10^9≤A,B≤2*10^9) разделенные пробелом
 *
 * Формат вывода
 * В единственной строке выхода выведите сумму чисел A+B
 * </pre>
 */
public class C {
    public static void main(String[] args) throws IOException {
        try (InputStream reader = new BufferedInputStream(System.in);
             OutputStream writer = new BufferedOutputStream(System.out)) {
            Main(reader, writer);
        }
    }

    public static void Main(InputStream reader, OutputStream writer) throws IOException {
        long A = readInt(reader);
        long B = readInt(reader);
        long sum = A + B;
        writer.write(String.valueOf(sum).getBytes());
    }

    private static int readInt(InputStream reader) throws IOException {
        byte[] arr = new byte[11];
        byte size = 0;

        byte b;
        while ((b = (byte) reader.read()) != -1 && b != '\n' && b != ' ') {
            arr[size++] = b;
        }

        return Integer.parseInt(new String(arr, 0, size));
    }
}

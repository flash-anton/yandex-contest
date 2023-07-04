package org.example.contest3;

import java.io.*;

/**
 * <pre>
 * Ознакомительный контест
 * https://contest.yandex.ru/contest/3/enter
 *
 * B. A+B 2
 *
 * Ограничение времени 	2 секунды
 * Ограничение памяти 	64Mb
 * Ввод 	input.txt
 * Вывод 	output.txt
 *
 * Даны два числа A и B. Вам нужно вычислить их сумму A+B.
 * В этой задаче вам нужно читать из файла и выводить ответ в файл.
 *
 * Формат ввода
 * Первая строка входа содержит числа A и B (−2*10^9≤A,B≤2*10^9) разделенные пробелом
 *
 * Формат вывода
 * В единственной строке выхода выведите сумму чисел A+B
 * </pre>
 */
public class B {
    public static void main(String[] args) throws IOException {
        try (InputStream reader = new BufferedInputStream(new FileInputStream("input.txt"));
             OutputStream writer = new BufferedOutputStream(new FileOutputStream("output.txt"))) {
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

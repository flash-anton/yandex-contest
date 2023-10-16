package contest.contest8458;

import java.io.*;

/**
 * <pre>
 * Подготовка к собеседованию в Яндекс
 * https://contest.yandex.ru/contest/8458/enter
 *
 * E. Анаграммы
 *
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	20Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Даны две строки, состоящие из строчных латинских букв.
 * Требуется определить, являются ли эти строки анаграммами, т. е. отличаются ли они только порядком следования символов.
 *
 * Формат ввода
 * Входной файл содержит две строки строчных латинских символов, каждая не длиннее 100 000 символов.
 * Строки разделяются символом перевода строки.
 *
 * Формат вывода
 * Выходной файл должен содержать единицу, если строки являются анаграммами, и ноль в противном случае.
 * </pre>
 */
public class E {
    public static void main(String[] args) throws IOException {
        try (InputStream reader = new BufferedInputStream(System.in);
             OutputStream writer = new BufferedOutputStream(System.out)) {
            Main(reader, writer);
        }
    }

    public static void Main(InputStream reader, OutputStream writer) throws IOException {
        byte b;

        int[] lettersCount = new int[26];
        while ((b = (byte) reader.read()) != '\n') {
            lettersCount[b - 'a']++;
        }

        while ((b = (byte) reader.read()) != '\n') {
            if (--lettersCount[b - 'a'] < 0) {
                writer.write('0');
                return;
            }
        }

        for (b = 0; b < lettersCount.length; b++) {
            if (lettersCount[b] > 0) {
                writer.write('0');
                return;
            }
        }

        writer.write('1');
    }
}

package org.example.contest8458;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * <pre>
 * Подготовка к собеседованию в Яндекс
 * https://contest.yandex.ru/contest/8458/enter
 *
 * B. Последовательно идущие единицы
 *
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Требуется найти в бинарном векторе самую длинную последовательность единиц и вывести её длину.
 * Желательно получить решение, работающее за линейное время и при этом проходящее по входному массиву только один раз.
 *
 * Формат ввода
 * Первая строка входного файла содержит одно число n, n ≤ 10000.
 * Каждая из следующих n строк содержит ровно одно число — очередной элемент массива.
 *
 * Формат вывода
 * Выходной файл должен содержать единственное число — длину самой длинной последовательности единиц во входном массиве.
 * </pre>
 */
public class B {
    public static void main(String[] args) {
        Main(System.in, System.out);
    }

    public static void Main(InputStream reader, OutputStream writer) {
        Scanner scanner = new Scanner(reader);
        int n = scanner.nextInt();

        int maxSequence = 0;
        int currentSequence = 0;
        while (n-- > 0) {
            if (scanner.nextInt() == 1) {
                currentSequence++;
            } else {
                maxSequence = Math.max(maxSequence, currentSequence);
                currentSequence = 0;
            }
        }
        maxSequence = Math.max(maxSequence, currentSequence);

        new PrintStream(writer).println(maxSequence);
    }
}

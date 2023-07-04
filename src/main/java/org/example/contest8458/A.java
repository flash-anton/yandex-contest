package org.example.contest8458;

import java.io.*;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <pre>
 * Подготовка к собеседованию в Яндекс
 * https://contest.yandex.ru/contest/8458/enter
 *
 * A. Камни и украшения [тестовая задача]
 *
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Даны две строки строчных латинских символов: строка J и строка S.
 * Символы, входящие в строку J, — «драгоценности», входящие в строку S — «камни».
 * Нужно определить, какое количество символов из S одновременно являются «драгоценностями».
 * Проще говоря, нужно проверить, какое количество символов из S входит в J.
 *
 * Это разминочная задача, к которой мы размещаем готовые решения.
 * Она очень простая и нужна для того, чтобы вы могли познакомиться с нашей автоматической системой проверки решений.
 * Ввод и вывод осуществляется через файлы, либо через стандартные потоки ввода-вывода, как вам удобнее.
 *
 * Python: https://pastebin.com/d9xet5Xe. В качестве языка выбирайте Python 3.7.
 * C++: https://pastebin.com/e5wMVV1u. Можно использовать GNU c++ 14 4.9.
 * C#: https://pastebin.com/UZU4iCB0. Язык: Mono C# 5.2.0.
 * Go: https://pastebin.com/vjVezxTg. Язык: gcc go.
 * Java: https://pastebin.com/SbLfafuv. Подойдёт, например, язык Java 8.
 * Kotlin: https://pastebin.com/Ruj3W2Er. Язык: Kotlin.
 * Swift: https://pastebin.com/Jh5PPwM6. Язык: Swift 4.1.1.
 * JavaScript: https://pastebin.com/B5YhwWFK. Язык: Node JS 8.9.4
 *
 * Формат ввода
 * На двух первых строках входного файла содержатся две строки строчных латинских символов: строка J и строка S.
 * Длина каждой не превосходит 100 символов.
 *
 * Формат вывода
 * Выходной файл должен содержать единственное число — количество камней, являющихся драгоценностями.
 * </pre>
 */
public class A {
    public static void main(String[] args) {
        Main(System.in, System.out);
    }

    public static void Main(InputStream reader, OutputStream writer) {
        Scanner scanner = new Scanner(reader);
        String J = scanner.nextLine();
        String S = scanner.nextLine();

        Set<Integer> uniqueJ = J.chars().boxed().collect(Collectors.toUnmodifiableSet());
        long SJ = S.chars().filter(uniqueJ::contains).count();
        new PrintStream(writer).println(SJ);
    }
}

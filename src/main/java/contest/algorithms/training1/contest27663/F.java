package contest.algorithms.training1.contest27663;

import java.io.*;
import java.util.*;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/27663">Яндекс. Тренировки по алгоритмам июнь 2021, занятие 3. Множества</a>
 * <a href="https://contest.yandex.ru/contest/27663/run-report/93256331">OK  246ms  12.85Mb</a>
 *
 * F. Инопланетный геном
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Геном жителей системы Тау Кита содержит 26 видов оснований, для обозначения которых будем использовать буквы
 * латинского алфавита от A до Z, а сам геном записывается строкой из латинских букв. Важную роль в геноме играют пары
 * соседних оснований, например, в геноме «ABBACAB» можно выделить следующие пары оснований: AB, BB, BA, AC, CA, AB.
 *
 * Степенью близости одного генома другому геному называется количество пар соседних оснований первого генома,
 * которые встречаются во втором геноме.
 *
 * Формат ввода
 * Вам даны два генома, определите степень близости первого генома второму геному.
 * Программа получает на вход две строки, состоящие из заглавных латинских букв.
 * Каждая строка непустая, и её длина не превосходит 10^5.
 *
 * Формат вывода
 * Программа должна вывести одно целое число – степень близости генома,
 * записанного в первой строке, геному, записанному во второй строке.
 *
 * Примечания
 * Следующие пары оснований первого генома встречаются во втором геноме: AB, BB, CA, AB.
 * Обратите внимание на то, что пара AB в первом геноме встречается два раза, поэтому и подсчитана в ответе два раза.
 *
 * Система оценивания:
 * Решение, правильно работающее только для случаев, когда длины данных строк не превосходят 100, будет оцениваться в 60 баллов.
 * </pre>
 */
public class F {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        alg(reader, writer);
        reader.close();
        writer.close();
    }

    public static void alg(BufferedReader reader, BufferedWriter writer) throws IOException {
        byte[] a = reader.readLine().getBytes();
        byte[] b = reader.readLine().getBytes();

        Map<Byte, Set<Byte>> bPairs = new HashMap<>();
        for (int i = 0; i < b.length - 1; i++) {
            bPairs.computeIfAbsent(b[i], k -> new HashSet<>()).add(b[i + 1]);
        }

        int proximity = 0;
        for (int i = 0; i < a.length - 1; i++) {
            if (bPairs.getOrDefault(a[i], Set.of()).contains(a[i + 1])) {
                proximity++;
            }
        }

        writer.write(Integer.toString(proximity));
        writer.flush();
    }
}

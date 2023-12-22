package contest53027;

import java.io.*;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/53027">Яндекс. Тренировки по алгоритмам 4.0. Разминка</a>
 * <a href="https://contest.yandex.ru/contest/53027/run-report/95515897">OK  108ms  10.95Mb</a>
 *
 * D. Анаграмма?
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Задано две строки, нужно проверить, является ли одна анаграммой другой.
 * Анаграммой называется строка, полученная из другой перестановкой букв.
 *
 * Формат ввода
 * Строки состоят из строчных латинских букв, их длина не превосходит 100000. Каждая записана в отдельной строке.
 *
 * Формат вывода
 * Выведите "YES" если одна из строк является анаграммой другой и "NO" в противном случае.
 * </pre>
 */
public class D {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        alg(reader, writer);
        reader.close();
        writer.close();
    }

    public static void alg(BufferedReader reader, BufferedWriter writer) throws IOException {
        String a = reader.readLine().trim();
        String b = reader.readLine().trim();

        String solution = alg(a, b);

        writer.write(solution);
        writer.flush();
    }

    // O(N)
    public static String alg(String a, String b) {
        int difference = 0;

        int[] countByLetter = new int[26];
        for (byte c : a.getBytes()) {
            if (++countByLetter[c - 'a'] == 1) {
                difference++;
            }
        }

        for (byte c : b.getBytes()) {
            int count = --countByLetter[c - 'a'];
            if (count == 0) {
                difference--;
            } else if (count == -1) {
                difference++;
            }
        }

        return (difference == 0) ? "YES" : "NO";
    }
}

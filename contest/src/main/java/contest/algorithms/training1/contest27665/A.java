package contest.algorithms.training1.contest27665;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/27665">Яндекс. Тренировки по алгоритмам июнь 2021, занятие 4. Словари и сортировка подсчетом</a>
 * <a href="https://contest.yandex.ru/contest/27665/run-report/93423825">OK  0.589s  47.41Mb</a>
 *
 * A. Словарь синонимов
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	256Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Вам дан словарь, состоящий из пар слов. Каждое слово является синонимом к парному ему слову.
 * Все слова в словаре различны. Для одного данного слова определите его синоним.
 *
 * Формат ввода
 * Программа получает на вход количество пар синонимов N.
 * Далее следует N строк, каждая строка содержит ровно два слова-синонима. После этого следует одно слово.
 *
 * Формат вывода
 * Программа должна вывести синоним к данному слову. Примечание
 *
 * Примечания
 * Эту задачу можно решить и без словарей (сохранив все входные данные в списке), но решение со словарем будет более простым.
 * </pre>
 */
public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        alg(reader, writer);
        reader.close();
        writer.close();
    }

    public static void alg(BufferedReader reader, BufferedWriter writer) throws IOException {
        int N = Integer.parseInt(reader.readLine());
        Map<String, String> m = new HashMap<>(N * 2, 1);
        for (int i = 0; i < N; i++) {
            String[] s = reader.readLine().split(" ");
            m.put(s[0], s[1]);
            m.put(s[1], s[0]);
        }
        String word = reader.readLine();
        String synonym = m.get(word);

        writer.write(synonym);
        writer.flush();
    }
}

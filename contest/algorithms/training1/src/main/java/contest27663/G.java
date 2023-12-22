package contest27663;

import java.io.*;
import java.util.*;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/27663">Яндекс. Тренировки по алгоритмам июнь 2021, занятие 3. Множества</a>
 * <a href="https://contest.yandex.ru/contest/27663/run-report/93260817">OK  268ms  17.90Mb</a>
 *
 * G. Черепахи
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Широко известна следующая задача для младших школьников.
 * Три черепахи ползут по дороге.
 * Одна черепаха говорит: “Впереди меня две черепахи”.
 * Другая черепаха говорит: “Позади меня две черепахи”.
 * Третья черепаха говорит: “Впереди меня две черепахи и позади меня две черепахи”.
 * Как такое может быть?
 * Ответ: третья черепаха врет!
 *
 * По дороге одна за другой движутся N черепах.
 * Каждая черепаха говорит фразу вида: “Впереди меня ai черепах, а позади меня bi черепах”.
 * Ваша задача определить, сколько самое большее количество черепах могут говорить правду.
 *
 * Формат ввода
 * В первой строке вводится целое число N (1 ≤ N ≤ 10000) строк, содержащих целые числа ai и bi,
 * по модулю не превосходящие 10000, описывающие высказывание i-ой черепахи.
 *
 * Формат вывода
 *
 * Выведите целое число M – максимальное количество черепах, которые могут говорить правду.
 * </pre>
 */
public class G {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        alg(reader, writer);
        reader.close();
        writer.close();
    }

    public static void alg(BufferedReader reader, BufferedWriter writer) throws IOException {
        int N = Integer.parseInt(reader.readLine());

        Set<String> trueStatements = new HashSet<>();
        for (int i = 0; i < N; i++) {
            String statement = reader.readLine();

            int[] n = Arrays.stream(statement.split(" ")).mapToInt(Integer::parseInt).toArray();
            int a = n[0];
            int b = n[1];

            if ((a >= 0) && (a < N) && (b >= 0) && (b < N) && (a + b == N - 1)) {
                trueStatements.add(statement);
            }
        }

        writer.write(Integer.toString(trueStatements.size()));
        writer.flush();
    }
}

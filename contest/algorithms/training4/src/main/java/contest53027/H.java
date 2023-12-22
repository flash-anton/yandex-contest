package contest53027;

import java.io.*;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/53027">Яндекс. Тренировки по алгоритмам 4.0. Разминка</a>
 * <a href="https://contest.yandex.ru/contest/53027/run-report/95590303">OK  57ms  8.54Mb</a>
 *
 * H. Результаты контеста
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Чтобы оценить качество обучения программированию, в каждой группы студентов подсчитывается один параметр —
 * суммарное количество решенных студентами задач.
 *
 * Известно, что в первой группе суммарное количество решенных на контесте задач равно a, а во второй — b.
 * Всего на контесте было предложено n задач, а также известно, что каждый студент решил не менее одной (и не более n) задач.
 *
 * По заданным a, b и n определите, могло ли в первой группе быть строго больше студентов, чем во второй.
 *
 * Формат ввода
 * Вводятся три целых числа a, b, n (1 ≤ a, b, n ≤ 10000).
 *
 * Формат вывода
 * Выведите "Yes" если в первой группе могло быть строго больше студентов, чем во второй, и "No" в противном случае.
 * </pre>
 */
public class H {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        alg(reader, writer);
        reader.close();
        writer.close();
    }

    public static void alg(BufferedReader reader, BufferedWriter writer) throws IOException {
        int a = Integer.parseInt(reader.readLine().trim());
        int b = Integer.parseInt(reader.readLine().trim());
        int n = Integer.parseInt(reader.readLine().trim());

        String solution = alg(a, b, n);

        writer.write(solution);
        writer.flush();
    }

    public static String alg(int a, int b, int n) {
        // В первой группе может быть строго больше студентов, чем во второй, только в том случае, если
        // максимально возможное кол-во студентов первой группы строго больше минимально возможного кол-ва студентов второй.
        // Максимально возможное кол-во студентов первой группы - каждый решил по 1 задаче.
        // Минимально возможное кол-во студентов второй группы - каждый решил по n задач, один мог решить не все.
        int minTeamBSize = b / n + ((b%n == 0) ? 0 : 1);
        return (a > minTeamBSize) ? "Yes" : "No";
    }
}

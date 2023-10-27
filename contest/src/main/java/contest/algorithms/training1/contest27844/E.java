package contest.algorithms.training1.contest27844;

import java.io.*;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/27844">Яндекс. Тренировки по алгоритмам июнь 2021, занятие 6. Бинарный поиск</a>
 * <a href="https://contest.yandex.ru/contest/27844/run-report/94898385">OK  132ms  9.50Mb</a>
 *
 * E. Улучшение успеваемости
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * В лицее на уроках информатики ответы учеников оцениваются целым числом баллов от 2 до 5 .
 * Итоговая оценка по информатике выставляется как среднее арифметическое оценок на всех уроках, округленное до ближайшего целого числа.
 * Если среднее значение находится ровно посередине между двумя целыми числами, то оценка округляется вверх.
 *
 * Примеры округления оценок приведены в таблице.
 *     2 3 5  ->      (2+3+5)/3 = 3+1/3  ->  3
 *   3 3 4 4  ->    (3+3+4+4)/4 = 3+1/2  ->  4
 * 5 5 5 3 5  ->  (5+5+5+3+5)/5 = 4+3/5  ->  5
 *
 * Все ученики лицея стремятся получить итоговую оценку по информатике не ниже 4 баллов.
 * К сожалению, один из учеников получил на уроках a двоек, b троек и c четверок.
 * Теперь он планирует получить несколько пятерок, причем хочет, чтобы итоговая оценка была не меньше 4 баллов.
 * Ему надо понять, какое минимальное количество пятерок ему необходимо получить, чтобы добиться своей цели.
 *
 * Требуется написать программу, которая по заданным целым неотрицательные числам a, b и c определяет минимальное количество
 * пятерок, которое необходимо получить ученику, чтобы его итоговая оценка по информатике была не меньше 4 баллов.
 *
 * Формат ввода
 * Входные данные содержат три строки.
 * Первая строка содержит целое неотрицательное число a ,
 * вторая строка содержит целое неотрицательное число b ,
 * третья строка содержит целое неотрицательное число c (0 ≤ a, b, c ≤ 10^15, a + b + c ≥ 1).
 *
 * Формат вывода
 * Выходные данные должны содержать одно число:
 * минимальное число пятерок, которое необходимо получить ученику, чтобы итоговая оценка была не меньше 4 баллов.
 * </pre>
 */
public class E {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        alg(reader, writer);
        reader.close();
        writer.close();
    }

    public static void alg(BufferedReader reader, BufferedWriter writer) throws IOException {
        long a = Long.parseLong(reader.readLine());
        long b = Long.parseLong(reader.readLine());
        long c = Long.parseLong(reader.readLine());

        long fivesCount = binarySearchSolution(a, b, c);

        writer.write("" + fivesCount);
        writer.flush();
    }

    public static long mathSolution(long a, long b, long c) {
        long f = 3 * a + b - c;
        return (f <= 0) ? 0 : (f / 3 + (f % 3 == 0 ? 0 : 1));
    }

    public static long binarySearchSolution(long a, long b, long c) {
        long abcSum = (2 * a) + (3 * b) + (4 * c);
        long abcCount = a + b + c;

        // Чтобы ученик получил 4, ср.арифм. должна быть 3.5
        // При a=10^15, b=0, c=0, потребуется 10^15 пятерок.
        // При a=10^15, b=10^15, c=0, потребуется 4/3*10^15 пятерок.
        // При a=10^15, b=10^15, c=b=10^15, потребуется 10^15 пятерок.
        long L = 0;
        long R = (long) (Math.pow(10, 15) * 4 / 3 + 2); // +1 т.к. R exclusive, +1 т.к. 4/3*10^15 > (long) 4/3*10^15
        while (L < R) {
            long M = (L + R) / 2;
            if (isEnough(abcSum, abcCount, M)) {
                R = M;
            } else {
                L = M + 1;
            }
        }
        return L;
    }

    static boolean isEnough(long abcSum, long abcCount, long fivesCount) {
        return 2 * (abcSum + 5 * fivesCount) >= 7 * (abcCount + fivesCount);
    }
}

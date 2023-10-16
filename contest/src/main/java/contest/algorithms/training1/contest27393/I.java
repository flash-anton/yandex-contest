package contest.algorithms.training1.contest27393;

import java.io.*;
import java.util.Arrays;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/27393">Яндекс. Тренировки по алгоритмам июнь 2021, занятие 1. Сложность, тестирование, особые случаи</a>
 * <a href="https://contest.yandex.ru/contest/27393/run-report/92514983">OK  94ms  8.69Mb</a>
 *
 * I. Узник замка Иф
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * За многие годы заточения узник замка Иф проделал в стене прямоугольное отверстие размером D × E. Замок Иф сложен из
 * кирпичей, размером A × B × C. Определите, сможет ли узник выбрасывать кирпичи в море через это отверстие,
 * если стороны кирпича должны быть параллельны сторонам отверстия.
 *
 * Формат ввода
 * Программа получает на вход числа A, B, C, D, E.
 *
 * Формат вывода
 * Программа должна вывести слово YES или NO.
 * </pre>
 */
public class I {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        alg(reader, writer);
        reader.close();
        writer.close();
    }

    public static void alg(BufferedReader reader, BufferedWriter writer) throws IOException {
        int A = Integer.parseInt(reader.readLine());
        int B = Integer.parseInt(reader.readLine());
        int C = Integer.parseInt(reader.readLine());
        int D = Integer.parseInt(reader.readLine());
        int E = Integer.parseInt(reader.readLine());

        int[] brickSizes = {A, B, C};
        Arrays.sort(brickSizes);
        int[] wallHoleSizes = {D, E};
        Arrays.sort(wallHoleSizes);

        boolean isBrickSmallerThanWallHole = (brickSizes[0] <= wallHoleSizes[0]) && (brickSizes[1] <= wallHoleSizes[1]);

        writer.write(isBrickSmallerThanWallHole ? "YES" : "NO");
        writer.flush();
    }
}

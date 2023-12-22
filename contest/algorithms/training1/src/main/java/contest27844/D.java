package contest27844;

import java.io.*;
import java.util.Arrays;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/27844">Яндекс. Тренировки по алгоритмам июнь 2021, занятие 6. Бинарный поиск</a>
 * <a href="https://contest.yandex.ru/contest/27844/run-report/94869650">OK  144ms  9.94Mb</a>
 *
 * D. Космическое поселение
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Для освоения Марса требуется построить исследовательскую базу.
 * База должна состоять из n одинаковых модулей, каждый из которых представляет собой прямоугольник.
 *
 * Каждый модуль представляет собой жилой отсек, который имеет форму прямоугольника размером a на b метров.
 * Для повышения надежности модулей инженеры могут добавить вокруг каждого модуля слой дополнительной защиты.
 * Толщина этого слоя должна составлять целое число метров, и все модули должны иметь одинаковую толщину дополнительной защиты.
 * Модуль с защитой, толщина которой равна d метрам, будет иметь форму прямоугольника размером (a+2d)(b+2d) метров.
 *
 * Все модули должны быть расположены на заранее подготовленном прямоугольном поле размером w*h метров.
 * При этом они должны быть организованы в виде регулярной сетки:
 * их стороны должны быть параллельны сторонам поля, и модули должны быть ориентированы одинаково.
 *
 * Требуется написать программу, которая по заданным количеству и размеру модулей, а также размеру поля для их размещения,
 * определяет максимальную толщину слоя дополнительной защиты, который можно добавить к каждому модулю.
 *
 * Формат ввода
 * Входной файл содержит пять разделенных пробелами целых чисел: n, a, b, w и h (1 ≤ n, a, b, w, h ≤ 10^18).
 * Гарантируется, что без дополнительной защиты все модули можно разместить в поселении описанным образом.
 *
 * Формат вывода
 * Выходной файл должен содержать одно целое число: максимальную возможную толщину дополнительной защиты.
 * Если дополнительную защиту установить не удастся, требуется вывести число 0.
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
        long[] buf = Arrays.stream(reader.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        long n = buf[0];
        long a = buf[1];
        long b = buf[2];
        long w = buf[3];
        long h = buf[4];

        long L = 0;
        long R = (long) Math.pow(10, 18);
        while (L < R) {
            long M = (L + R) / 2;
            if (isValid(n, a, b, w, h, M)) {
                L = M + 1;
            } else {
                R = M;
            }
        }
        long d = (L == 0) ? 0 : (L - 1);

        writer.write("" + d);
        writer.flush();
    }

    static boolean isValid(long n, long a, long b, long w, long h, long d) {
        a += 2 * d;
        b += 2 * d;
        return isValid(n, a, b, w, h) || isValid(n, b, a, w, h);
    }

    static boolean isValid(long n, long a, long b, long w, long h) {
        if (w < a) {
            return false;
        } else {
            long columns = w / a;
            long rows = (n / columns) + (n % columns == 0 ? 0 : 1);
            return h / rows >= b;
        }
    }
}

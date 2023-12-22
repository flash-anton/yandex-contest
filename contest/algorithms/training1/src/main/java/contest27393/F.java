package contest27393;

import java.io.*;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/27393">Яндекс. Тренировки по алгоритмам июнь 2021, занятие 1. Сложность, тестирование, особые случаи</a>
 * <a href="https://contest.yandex.ru/contest/27393/run-report/92494387">OK  147ms  11.34Mb</a>
 *
 * F. Расстановка ноутбуков
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * В школе решили на один прямоугольный стол поставить два прямоугольных ноутбука. Ноутбуки нужно поставить так, чтобы
 * их стороны были параллельны сторонам стола. Определите, какие размеры должен иметь стол, чтобы оба ноутбука на него
 * поместились, и площадь стола была минимальна.
 *
 * Формат ввода
 * Вводится четыре натуральных числа, первые два задают размеры одного ноутбука, а следующие два — размеры второго.
 * Числа не превышают 1000.
 *
 * Формат вывода
 * Выведите два числа — размеры стола. Если возможно несколько ответов, выведите любой из них (но только один).
 *
 * Примечания
 * В примерах указаны всевозможные ответы на поставленную задачу. Ваша программа должна вывести один из них.
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
        String[] buf = reader.readLine().split(" ");
        int a = Integer.parseInt(buf[0]);
        int b = Integer.parseInt(buf[1]);
        int c = Integer.parseInt(buf[2]);
        int d = Integer.parseInt(buf[3]);

        Table smaller1 = Table.smaller(new Table(a, b, c, d), new Table(a, b, d, c));
        Table smaller2 = Table.smaller(new Table(b, a, c, d), new Table(b, a, d, c));

        writer.write(Table.smaller(smaller1, smaller2).toString());
        writer.flush();
    }

    private static final class Table {
        final int width;
        final int height;
        final int square;

        Table(int width1, int height1, int width2, int height2) {
            width = width1 + width2;
            height = Math.max(height1, height2);
            square = width * height;
        }

        static Table smaller(Table a, Table b) {
            return a.square <= b.square ? a : b;
        }

        @Override
        public String toString() {
            return width + " " + height;
        }
    }
}

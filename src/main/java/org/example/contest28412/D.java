package org.example.contest28412;

import java.io.*;

/**
 * <pre>
 * Тренировочный контест: разработка бэкенда
 * https://contest.yandex.ru/contest/28412/enter
 *
 * D. Посчитать графы
 *
 * 	                    Все языки 	PHP 7.3.5
 * Ограничение времени 	2 секунды 	4 секунды
 * Ограничение памяти 	512Mb 	512Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Назовём неориентированный граф простым, если в нём нет петель и кратных рёбер. Назовём простой неориентированный граф
 * хорошим, если в нём ровно у одной вершины степень равна n−1, то есть в графе есть ровно одна вершина, соединённая со
 * всеми остальными ребром.
 * Дано число n, требуется посчитать количество хороших графов на n вершинах. Два графа называются различными, если
 * существует пара вершин (u,v) такая, что в одном графе есть ребро (u,v), а в другом нет.
 * Так как ответ может быть крайне большим, выведите отстаток от его деления на 10^9+7.
 *
 * Формат ввода
 * В единственной строке задано одно целое число n (1≤n≤5000).
 *
 * Формат вывода
 * Выведите одно число — ответ на задачу по модулю 10^9+7.
 *
 * </pre>
 */
public class D {
    public static void main(String[] args) throws IOException {
        try (InputStream reader = new BufferedInputStream(System.in);
             OutputStream writer = new BufferedOutputStream(System.out)) {
            Main(reader, writer);
        }
    }

    public static void Main(InputStream reader, OutputStream writer) throws IOException {
        int n = readInt(reader);
        writer.write(Integer.toString(n).getBytes());
    }

    private static int readInt(InputStream reader) throws IOException {
        byte[] buf = new byte[11]; // 11 == String.valueOf(Integer.MIN_VALUE).length()
        int size = 0;

        int b;
        while ((b = reader.read()) != -1 && b != '\n' && b != ' ') {
            buf[size++] = (byte) b;
        }

        return Integer.parseInt(new String(buf, 0, size));
    }
}

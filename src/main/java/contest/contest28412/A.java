package contest.contest28412;

import java.io.*;

/**
 * <pre>
 * Тренировочный контест: разработка бэкенда
 * https://contest.yandex.ru/contest/28412/enter
 *
 * A. Андрей и кислота
 *
 * Ограничение времени 	2 секунды
 * Ограничение памяти 	512Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Андрей работает в секретной химической лаборатории, в которой производят опасную кислоту с удивительными свойствами.
 * У Андрея есть n бесконечно больших резервуаров, расположенных в один ряд. Изначально в каждом резервуаре находится
 * некоторое количество кислоты. Начальство Андрея требует, чтобы во всех резервуарах содержался одинаковый объем
 * кислоты. К сожалению, разливающий аппарат несовершенен. За одну операцию он способен разлить по одному литру кислоты
 * в каждый из первых k (1≤k≤n) резервуаров. Обратите внимание, что для разных операций k могут быть разными. Поскольку
 * кислота очень дорогая, Андрею не разрешается выливать кислоту из резервуаров. Андрей просит вас узнать, можно ли
 * уравнять объемы кислоты в резервуарах, и, если это возможно, то посчитать минимальное количество операций, которое
 * потребуется, чтобы этого достичь.
 *
 * Формат ввода
 * Первая строка содержит число n (1≤n≤100000) — количество резервуаров.
 * Во второй строке содержатся n целых чисел ai (1≤ai≤10^9), где ai означает исходный объём кислоты в i-м резервуаре
 * в литрах.
 *
 * Формат вывода
 * Если объемы кислоты в резервуарах можно уравнять, выведите минимальное количество операций, необходимых для этого.
 * Если это невозможно, выведите «-1».
 *
 *  OK 190ms 17.89Mb
 * </pre>
 */
public class A {
    public static void main(String[] args) throws IOException {
        try (InputStream reader = new BufferedInputStream(System.in);
             OutputStream writer = new BufferedOutputStream(System.out)) {
            Main(reader, writer);
        }
    }

    public static void Main(InputStream reader, OutputStream writer) throws IOException {
        int n = readInt(reader);

        int min = readInt(reader);
        int max = min;
        for (int i = 1; i < n; i++) {
            int current = readInt(reader);
            if (current < max) {
                writer.write("-1".getBytes());
                return;
            }
            max = current;
        }

        writer.write(String.valueOf(max-min).getBytes());
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

package contest.contest8458;

import java.io.*;
import java.util.*;

/**
 * <pre>
 * Подготовка к собеседованию в Яндекс
 * https://contest.yandex.ru/contest/8458/enter
 *
 * G. Интересное путешествие
 *
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Не секрет, что некоторые программисты очень любят путешествовать. Хорошо всем известный программист Петя тоже очень
 * любит путешествовать, посещать музеи и осматривать достопримечательности других городов.
 *
 * Для перемещений между из города в город он предпочитает использовать машину. При этом он заправляется только на
 * станциях в городах, но не на станциях по пути. Поэтому он очень аккуратно выбирает маршруты, чтобы машина не заглохла
 * в дороге. А ещё Петя очень важный член команды, поэтому он не может себе позволить путешествовать слишком долго. Он
 * решил написать программу, которая поможет ему с выбором очередного путешествия. Но так как сейчас у него слишком
 * много других задач, он попросил вас помочь ему.
 *
 * Расстояние между двумя городами считается как сумма модулей разности по каждой из координат. Дороги есть между всеми
 * парами городов.
 *
 * Формат ввода
 * В первой строке входных данных записано количество городов n (2≤n≤1000). В следующих n строках даны два целых числа:
 * координаты каждого города, не превосходящие по модулю миллиарда. Все города пронумерованы числами от 1 до n в порядке
 * записи во входных данных.
 * В следующей строке записано целое положительное число k, не превосходящее двух миллиардов, — максимальное расстояние
 * между городами, которое Петя может преодолеть без дозаправки машины.
 * В последней строке записаны два различных числа — номер города, откуда едет Петя, и номер города, куда он едет.
 *
 * Формат вывода
 * Если существуют пути, удовлетворяющие описанным выше условиям, то выведите минимальное количество дорог, которое
 * нужно проехать, чтобы попасть из начальной точки маршрута в конечную. Если пути не существует, выведите -1.
 * </pre>
 */
public class G {
    public static void main(String[] args) throws IOException {
        try (InputStream reader = new BufferedInputStream(System.in);
             OutputStream writer = new BufferedOutputStream(System.out)) {
            Main(reader, writer);
        }
    }

    public static void Main(InputStream reader, OutputStream writer) throws IOException {
        int n = readInt(reader);

        int[][] townXY = new int[n][2];
        for (int i = 0; i < n; i++) {
            townXY[i][0] = readInt(reader);
            townXY[i][1] = readInt(reader);
        }

        int k = readInt(reader);

        int from = readInt(reader) - 1;

        int to = readInt(reader) - 1;

        // =============

        HashSet<Integer> checked = new HashSet<>(); // townIndex

        LinkedHashMap<Integer, Integer> unchecked = new LinkedHashMap<>(); // townIndex -> roadNumber
        unchecked.put(from, 0);

        while (!unchecked.isEmpty()) {
            int townIndex = unchecked.keySet().iterator().next();
            int roadNumber = unchecked.remove(townIndex);

            if (townIndex == to) {
                writer.write(String.valueOf(roadNumber).getBytes());
                return;
            }

            checked.add(townIndex);

            roadNumber++;
            for (int i = 0; i < n; i++) {
                if (checked.contains(i) || unchecked.containsKey(i) || distance(townXY, townIndex, i) > k) {
                    continue;
                }
                unchecked.put(i, roadNumber);
            }
        }

        writer.write("-1".getBytes());
    }

    private static int readInt(InputStream reader) throws IOException {
        byte[] arr = new byte[11];
        byte size = 0;

        byte b;
        while ((b = (byte) reader.read()) != -1 && b != '\n' && b != ' ') {
            arr[size++] = b;
        }

        return Integer.parseInt(new String(arr, 0, size));
    }

    private static long distance(int[][] townXY, int i, int j) {
        return (long) Math.abs(townXY[i][0] - townXY[j][0]) + Math.abs(townXY[i][1] - townXY[j][1]);
    }
}

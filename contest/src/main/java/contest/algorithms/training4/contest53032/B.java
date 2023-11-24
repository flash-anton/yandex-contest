package contest.algorithms.training4.contest53032;

import java.io.*;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/53032">Алг 4.0 ДЗ 4 (53032) Перебор и методы его оптимизации</a>
 * <a href="https://contest.yandex.ru/contest/53032/run-report/99014962">OK  0.858s  10.03Mb</a>
 *
 * B. Затерянный мир
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Территория зоопарка Юрского периода «Затерянный мир» представляет собой решётку N × N, в каждой клетке которой находится вольер для динозавра.
 * Директор зоопарка Степан Савельев планирует расселить в зоопарке N динозавров.
 * Вольеры отделены друг от друга невысоким забором.
 * Сотрудникам зоопарка известно, что динозавр не покидает пределов своего вольера, и не ломает забор, если он не видит на территории парка других динозавров.
 * Зрительный аппарат у динозавров таков, что он видит всех динозавров, которые находятся на одной строке, на одном столбце или на одной диагонали с ним.
 * Если же динозавр видит другого ящера, то ломает забор и вступает в борьбу.
 * Директор зоопарка не хочет терпеть убытки, поэтому просит вас посчитать количество способов так расселить динозавров в зоопарке, чтобы никакой ящер не видел остальных динозавров.
 *
 * Формат ввода
 * Задано единственное число N (N ≤ 10).
 *
 * Формат вывода
 * Необходимо вывести количество способов, которыми можно расселить в зоопарке N динозавров, чтобы у зоопарка не было убытков.
 * </pre>
 */
public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        alg(reader, writer);
        reader.close();
        writer.close();
    }

    public static void alg(BufferedReader reader, BufferedWriter writer) throws IOException {
        int N = Integer.parseInt(reader.readLine().trim());

        String solution = alg1(N);

        writer.write(solution);
        writer.flush();
    }

    public static String alg1(int N) {
        return "" + f1(N, 0, new int[N + 1], new boolean[N + 1], new boolean[N + 1], new boolean[2 * N - 1], new boolean[2 * N - 1]);
    }

    static long f1(int N, int cellInUse, int[] cellIndex, boolean[] columnInUse, boolean[] rowInUse, boolean[] diag1InUse, boolean[] diag2InUse) {
        long variants = 0;
        if (cellInUse < N) {
            int n = N - 1;
            for (int i = cellIndex[cellInUse]; i < N * (N - 1) + cellInUse + 1; i++) {
                int c = i / N + 1;
                int r = i % N + 1;
                int d1 = c + r - 2;
                int d2 = c - r + n;
                if (!(columnInUse[c] || rowInUse[r] || diag1InUse[d1] || diag2InUse[d2])) {
                    cellIndex[cellInUse + 1] = i;

                    columnInUse[c] = true;
                    rowInUse[r] = true;
                    diag1InUse[d1] = true;
                    diag2InUse[d2] = true;

                    variants += f1(N, cellInUse + 1, cellIndex, columnInUse, rowInUse, diag1InUse, diag2InUse);

                    columnInUse[c] = false;
                    rowInUse[r] = false;
                    diag1InUse[d1] = false;
                    diag2InUse[d2] = false;
                }
            }
        } else {
            variants++;
        }
        return variants;
    }
}

package contest27393;

import java.io.*;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/27393">Яндекс. Тренировки по алгоритмам июнь 2021, занятие 1. Сложность, тестирование, особые случаи</a>
 * <a href="https://contest.yandex.ru/contest/27393/run-report/92505788">OK  96ms  8.63Mb</a>
 *
 * G. Детали
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Имеется N кг металлического сплава. Из него изготавливают заготовки массой K кг каждая. После этого из каждой заготовки
 * вытачиваются детали массой M кг каждая (из каждой заготовки вытачивают максимально возможное количество деталей).
 * Если от заготовок после этого что-то остается, то этот материал возвращают к началу производственного цикла и сплавляют
 * с тем, что осталось при изготовлении заготовок. Если того сплава, который получился, достаточно для изготовления
 * хотя бы одной заготовки, то из него снова изготавливают заготовки, из них – детали и т.д. Напишите программу, которая
 * вычислит, какое количество деталей может быть получено по этой технологии из имеющихся исходно N кг сплава.
 *
 * Формат ввода
 * Вводятся N, K, M. Все числа натуральные и не превосходят 200.
 *
 * Формат вывода
 * Выведите одно число — количество деталей, которое может получиться по такой технологии.
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
        String[] buf = reader.readLine().split(" ");
        int N = Integer.parseInt(buf[0]);
        int K = Integer.parseInt(buf[1]);
        int M = Integer.parseInt(buf[2]);

        int details = fast(N, K, M);

        writer.write(Integer.toString(details));
        writer.flush();
    }

    public static int fast(int N, int K, int M) {
        if (N < K || K < M) {
            return 0;
        }

        int detailsPerBlank = K / M;
        int detailsAlloyPerBlank = detailsPerBlank * M;
        int alloyRemainderPerBlank = K % M;

        int blanks = N / detailsAlloyPerBlank;
        int alloyReminder = N % detailsAlloyPerBlank;
        if (alloyReminder < alloyRemainderPerBlank) {
            blanks--;
        }

        return blanks * detailsPerBlank;
    }


    /**
     * Медленный эталонный алгоритм для сравнения ответов.
     */
    public static int slow(int N, int K, int M) {
        if (N < K || K < M) {
            return 0;
        }

        int detailsPerBlank = K / M;
        int remainderPerBlank = K % M;
        int details = 0;
        while (N >= K) {
            int blanks = N / K;
            int remainder = N % K;
            details += blanks * detailsPerBlank;
            N = remainder + blanks * remainderPerBlank;
        }
        return details;
    }
}

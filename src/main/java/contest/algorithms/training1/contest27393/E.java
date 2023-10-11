package contest.algorithms.training1.contest27393;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/27393">Яндекс. Тренировки по алгоритмам июнь 2021, занятие 1. Сложность, тестирование, особые случаи</a>
 * <a href="https://contest.yandex.ru/contest/27393/run-report/92439902">OK  178ms  11.36Mb</a>
 *
 * E. Скорая помощь
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Бригада скорой помощи выехала по вызову в один из отделенных районов. К сожалению, когда диспетчер получил вызов, он
 * успел записать только адрес дома и номер квартиры K1, а затем связь прервалась. Однако он вспомнил, что по этому же
 * адресу дома некоторое время назад скорая помощь выезжала в квартиру K2, которая расположена в подъезда P2 на этаже N2.
 * Известно, что в доме M этажей и количество квартир на каждой лестничной площадке одинаково. Напишите программу,
 * которая вычисляет номер подъезда P1 и номер этажа N1 квартиры K1.
 *
 * Формат ввода
 * Во входном файле записаны пять положительных целых чисел K1, M, K2, P2, N2. Все числа не превосходят 10^6.
 *
 * Формат вывода
 * Выведите два числа P1 и N1. Если входные данные не позволяют однозначно определить P1 или N1, вместо соответствующего
 * числа напечатайте 0. Если входные данные противоречивы, напечатайте два числа –1 (минус один).
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
        String[] buf = reader.readLine().split(" ");
        long K1 = Integer.parseInt(buf[0]) - 1;
        long M = Integer.parseInt(buf[1]);
        long K2 = Integer.parseInt(buf[2]) - 1;
        long P2 = Integer.parseInt(buf[3]) - 1;
        long N2 = Integer.parseInt(buf[4]) - 1;

        String solution = fast(K1, M, K2, P2, N2);

        writer.write(solution);
        writer.flush();
    }

    public static String fast(long K1, long M, long K2, long P2, long N2) {
        // K = CMP + CN + A  |  C > A >= 0  |  M > N >= 0  |  P >= 0

        // K = CMP + CN + A   =>   K = C(MP + N) + A    =>   C = K div (MP + N)
        long minC;
        long maxC;
        if (N2 >= M) { // выше крыше
            minC = 0;
            maxC = 0;

        } else if (P2 == 0 && N2 == 0) { // 1 подъезд, 1 этаж
            minC = K2 + 1;
            maxC = 1_000_000;

        } else {
            long MP2N2 = M * P2 + N2;
            long MP2N2_1 = MP2N2 + 1;

            maxC = K2 / MP2N2; // первая кв-ра на этаже K = C(MP+N) => maxC = K div (MP+N)

            minC = (K2 + 1) / MP2N2_1; // последняя кв-ра на этаже K+1 = C(MP+N+1) => minC = (K+1) div (MP+N+1)
            if (K2 + 1 > minC * MP2N2_1) { // кв-ра дальше последней K+1 > minC(MP+N+1) => minC++
                minC++;
            }
        }

        if (minC == 0 || maxC == 0 || (maxC < minC)) {
            return "-1 -1";
        }

        // K = CMP + CN + A   =>   P = K div CM
        // K = CMP + CN + A   =>   N = (K div C) - MP
        long minP1 = K1 / (minC * M);
        long minN1 = (K1 / minC) - (M * minP1);
        long maxP1 = K1 / (maxC * M);
        long maxN1 = (K1 / maxC) - (M * maxP1);

        long P1;
        long N1;
        if (minP1 == maxP1) {
            P1 = minP1; // в одном подъезде
            if (minN1 != maxN1) { // на разных этажах
                N1 = -1;
            } else { // на одном этаже
                N1 = minN1;
            }
        } else {
            P1 = -1; // в разных подъездах
            if (minN1 != maxN1) { // на разных этажах
                N1 = -1;
            } else {
                N1 = minN1; // на одном этаже
                for (long C = minC + 1; C < maxC - 1; C++) {
                    long P = K1 / (C * M);
                    long N = (K1 / C) - (M * P);
                    if (N != N1) { // на разных этажах
                        N1 = -1;
                        break;
                    }
                }
            }
        }

        return (P1 + 1) + " " + (N1 + 1);
    }

    /**
     * Медленный эталонный алгоритм для сравнения ответов.
     */
    public static String slow(long K1, long M, long K2, long P2, long N2, long maxExclusive) {
        Set<Long> cSet = new HashSet<>();
        for (long C = 1; C < maxExclusive; C++) {
            for (long A2 = 0; A2 < C; A2++) {
                if (K2 == calcK(C, M, P2, N2, A2)) {
                    cSet.add(C);
                }
            }
        }

        Map<Long, Set<Long>> pn = new HashMap<>();
        Map<Long, Set<Long>> np = new HashMap<>();
        for (long C : cSet) {
            for (long P1 = 0; P1 < maxExclusive - 1; P1++) {
                for (long N1 = 0; N1 < M; N1++) {
                    for (int A1 = 0; A1 < C; A1++) {
                        if (K1 == calcK(C, M, P1, N1, A1)) {
                            pn.computeIfAbsent(P1, k -> new HashSet<>()).add(N1);
                            np.computeIfAbsent(N1, k -> new HashSet<>()).add(P1);
                        }
                    }
                }
            }
        }

        if (pn.isEmpty()) {
            return "-1 -1";
        }

        long P1 = (pn.size() == 1) ? pn.keySet().toArray(new Long[0])[0] : -1;
        long N1 = (np.size() == 1) ? np.keySet().toArray(new Long[0])[0] : -1;
        return (P1 + 1) + " " + (N1 + 1);
    }

    /**
     * K = CMP+CN+A  |  C > A >= 0  |  M > N >= 0  |  P >= 0  |  K == -1 if illegal params
     */
    private static long calcK(long C, long M, long P, long N, long A) {
        if ((C > A && A >= 0) && (M > N && N >= 0) && (P >= 0)) {
            return (C * M * P) + (C * N) + A;
        }
        return -1;
    }
}

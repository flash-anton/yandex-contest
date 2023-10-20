package contest.algorithms.training1.contest27794;

import java.io.*;
import java.util.Arrays;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/27794">Яндекс. Тренировки по алгоритмам июнь 2021, занятие 5. Префиксные суммы и два указателя</a>
 * <a href="https://contest.yandex.ru/contest/27794/run-report/93945734">OK  316ms  21.65Mb</a>
 *
 * B. Сумма номеров
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Вася очень любит везде искать своё счастливое число K. Каждый день он ходит в школу по улице, вдоль которой
 * припарковано N машин. Он заинтересовался вопросом, сколько существует наборов машин, стоящих подряд на местах с L до R,
 * что сумма их номеров равна K. Помогите Васе узнать ответ на его вопрос.
 *
 * Например, если число N=5, K=17, а номера машин равны 17, 7, 10, 7, 10, то существует 4 набора машин:
 * 17 (L=1,R=1),
 * 7, 10 (L=2,R=3),
 * 10, 7 (L=3,R=4),
 * 7, 10 (L=4,R=5)
 *
 * Формат ввода
 * В первой строке входных данных задаются числа N и K (1≤N≤100000, 1≤K≤10^9).
 * Во второй строке содержится N чисел, задающих номера машин. Номера машин могут принимать значения от 1 до 999 включительно.
 *
 * Формат вывода
 * Необходимо вывести одно число — количество наборов.
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
        int K = Integer.parseInt(reader.readLine().split(" ")[1]);
        int[] n = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int count = 0;
        int sum = 0;
        int R = 0;
        for (int L = 0; L < n.length; L++) {
            for (; R < n.length && ((L == R) || (sum + n[R] <= K)); R++) {
                if ((sum += n[R]) == K) {
                    count++;
                }
            }
            if ((sum -= n[L]) == K) {
                count++;
            }
        }

        writer.write(String.valueOf(count));
        writer.flush();
    }
}

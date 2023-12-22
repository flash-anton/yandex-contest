package contest27844;

import java.io.*;
import java.util.Arrays;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/27844">Яндекс. Тренировки по алгоритмам июнь 2021, занятие 6. Бинарный поиск</a>
 * <a href="https://contest.yandex.ru/contest/27844/run-report/94839559">OK  147ms  9.89Mb</a>
 *
 * C. Дипломы
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Когда Петя учился в школе, он часто участвовал в олимпиадах по информатике, математике и физике.
 * Так как он был достаточно способным мальчиком и усердно учился, то на многих из этих олимпиад он получал дипломы.
 * К окончанию школы у него накопилось n дипломов, причём, как оказалось, все они имели одинаковые размеры: w — в ширину и h — в высоту.
 * Сейчас Петя учится в одном из лучших российских университетов и живёт в общежитии со своими одногруппниками.
 * Он решил украсить свою комнату, повесив на одну из стен свои дипломы за школьные олимпиады.
 * Так как к бетонной стене прикрепить дипломы достаточно трудно, то он решил купить специальную доску из пробкового дерева,
 * чтобы прикрепить её к стене, а к ней — дипломы. Для того чтобы эта конструкция выглядела более красиво, Петя хочет,
 * чтобы доска была квадратной и занимала как можно меньше места на стене.
 * Каждый диплом должен быть размещён строго в прямоугольнике размером w на h.
 * Дипломы запрещается поворачивать на 90 градусов.
 * Прямоугольники, соответствующие различным дипломам, не должны иметь общих внутренних точек.
 * Требуется написать программу, которая вычислит минимальный размер стороны доски, которая потребуется Пете для размещения всех своих дипломов.
 *
 * Формат ввода
 * Входной файл содержит три целых числа: w, h, n (1 ≤ w, h, n ≤ 10^9).
 *
 * Формат вывода
 * В выходной файл необходимо вывести ответ на поставленную задачу.
 * </pre>
 */
public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        alg(reader, writer);
        reader.close();
        writer.close();
    }

    public static void alg(BufferedReader reader, BufferedWriter writer) throws IOException {
        int[] buf = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int w = buf[0];
        int h = buf[1];
        int n = buf[2];

        long divisor = 1;
        long current = minEdge(w, h, n, divisor);
        long last = current;
        while (++divisor <= n && current <= last) {
            last = current;
            current = minEdge(w, h, n, divisor);
        }

        writer.write("" + last);
        writer.flush();
    }

    static long minEdge(int w, int h, int n, long divisor) {
        long quotient = (n / divisor) + (n % divisor == 0 ? 0 : 1);
        long edge1 = Math.max(h * divisor, w * quotient);
        long edge2 = Math.max(h * quotient, w * divisor);
        return Math.min(edge1, edge2);
    }
}

package contest53027;

import java.io.*;
import java.util.Arrays;
import java.util.Locale;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/53027">Яндекс. Тренировки по алгоритмам 4.0. Разминка</a>
 * <a href="https://contest.yandex.ru/contest/53027/run-report/95510022">OK  137ms  12.38Mb</a>
 *
 * C. Путешествие по Москве
 * Ограничение времени 	2 секунды
 * Ограничение памяти 	256Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Мэрия Москвы основательно подготовилась к празднованию тысячелетия города в 2147 году, построив под столицей
 * бесконечную асфальтированную площадку, чтобы заменить все существующие в городе автомобильные дороги.
 * В память о кольцевых и радиальных дорогах разрешили двигаться по площадке только двумя способами:
 * 1. В сторону точки начала координат или от неё. При этом из точки начала координат разрешено двигаться в любом направлении.
 * 2. Вдоль окружности с центром в начале координат и радиусом, который равен текущему расстоянию до начала координат.
 * Двигаться вдоль такой окружности разрешается в любом направлении (по или против часовой стрелки).
 *
 * Вам, как ведущему программисту ответственной инстанции поручено разработать модуль,
 * который будет определять кратчайший путь из точки A, с координатами (xA, yA) в точку B с координатами (xB, yB).
 * Считайте, что менять направление движения можно произвольное количество раз, но оно должно всегда соответствовать одному из двух описанных выше вариантов.
 *
 * Формат ввода
 * В первой строке ввода заданы четыре целых числа xA, yA, xB и yB, по модулю не превосходящие 10^6.
 *
 * Формат вывода
 * Выведите одно число — минимальное расстояние, которое придётся преодолеть по пути из точки A в точку B, если не нарушать
 * правил дорожного движения. Ваш ответ будет принят, если его абсолютная или относительная погрешность не превосходит 10^-6.
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
        int[] a = Arrays.stream(reader.readLine().split(" ")).filter(s -> !s.isEmpty()).mapToInt(Integer::parseInt).toArray();

        String solution = alg(a[0], a[1], a[2], a[3]);

        writer.write(solution);
        writer.flush();
    }

    public static String alg(int xA, int yA, int xB, int yB) {
        double track;
        if (xA == xB && yA == yB) {
            track = 0;
        } else if ((xA == 0 && yA == 0) || (xB == 0 && yB == 0)) {
            track = Math.sqrt((long) xA * xA + (long) yA * yA + (long) xB * xB + (long) yB * yB);
        } else {
            double rA = Math.sqrt((long) xA * xA + (long) yA * yA);
            double rB = Math.sqrt((long) xB * xB + (long) yB * yB);
            double rMin = Math.min(rA, rB);
            double rDelta = Math.max(rA, rB) - rMin;

            double angleA = Math.atan2(yA, xA);
            double angleB = Math.atan2(yB, xB);
            double angleDelta = Math.max(angleA, angleB) - Math.min(angleA, angleB);
            if (angleDelta > Math.PI) {
                angleDelta = 2 * Math.PI - angleDelta;
            }

            track = rDelta + Math.min(2, angleDelta) * rMin;
        }
        return String.format(Locale.ENGLISH, "%.12f", track);
    }
}

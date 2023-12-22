package contest27844;

import java.io.*;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/27844">Яндекс. Тренировки по алгоритмам июнь 2021, занятие 6. Бинарный поиск</a>
 * <a href="https://contest.yandex.ru/contest/27844/run-report/95000336">OK  131ms  9.50Mb</a>
 *
 * G. Площадь
 * Язык 	Ограничение времени 	Ограничение памяти 	Ввод 	Вывод
 * Все языки 	0.5 секунд 	64Mb 	стандартный ввод или input.txt 	стандартный вывод или output.txt
 * GNU C11 7.3 	0.3 секунды 	256Mb
 * GNU c++ 11 4.9 	0.3 секунды 	256Mb
 * GNU c++ 11 x32 4.9 	0.3 секунды 	256Mb
 * GCC 5.4.0 C++14 	0.3 секунды 	256Mb
 * GNU c++ 14 4.9 	0.3 секунды 	256Mb
 * GNU c++17 7.3 	0.3 секунды 	256Mb
 * GCC C++17 	0.3 секунды 	256Mb
 *
 * Городская площадь имеет размер n× m и покрыта квадратной плиткой размером 1× 1.
 *
 * При плановой замене плитки выяснилось, что новой плитки недостаточно для покрытия всей площади,
 * поэтому было решено покрыть плиткой только дорожку по краю площади,
 * а в центре площади разбить прямоугольную клумбу (см. рисунок к примеру).
 *
 * При этом дорожка должна иметь одинаковую ширину по всем сторонам площади.
 *
 * Определите максимальную ширину дорожки, которую можно выложить из имеющихся плиток.
 *
 * Формат ввода
 * Первая и вторая строки входных данных содержат по одному числу n и m (3≤ n ≤ 2× 10^9, 3≤ m ≤ 2× 10^9) — размеры площади.
 * Третья строка содержит количество имеющихся плиток t, 1≤ t< nm.
 *
 * Обратите внимание, что значение t может быть больше, чем возможное значение 32-битной целочисленной переменной,
 * поэтому необходимо использовать 64-битные числа (тип int64 в языке Pascal, тип long long в C и C++, тип long в Java и C#).
 *
 * Формат вывода
 * Программа должна вывести единственное число — максимальную ширину дорожки, которую можно выложить из имеющихся плиток.
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
        long n = Long.parseLong(reader.readLine());
        long m = Long.parseLong(reader.readLine());
        long t = Long.parseLong(reader.readLine());

        long solution = alg(n, m, t);

        writer.write("" + solution);
        writer.flush();
    }

    static long alg(long n, long m, long t) {
        long halfP = n + m;

        long L = 0;
        long R = Math.min(n, m) / 2 + 1; // +1 т.к R exclusive
        while (L < R) {
            long M = (L + R) / 2;
            long doubleM = M * 2;
            long S = doubleM * (halfP - doubleM);
            if (S < t) {
                L = M + 1;
            } else {
                R = M;
            }
        }

        // L указывает на S >= t
        // если S == t, то искомое - L, иначе - (L-1)
        long doubleM = L * 2;
        long S = doubleM * (halfP - doubleM);
        return (S == t) ? L : (L - 1);
    }

    static long slow(long n, long m, long t) {
        long halfP = n + m;

        for (long M = 0; M < Math.min(n, m); M++) {
            long doubleM = M * 2;
            long S = doubleM * (halfP - doubleM);
            if (S == t) {
                return M;
            } else if (S > t) {
                return M - 1;
            }
        }
        throw new IllegalArgumentException(String.format("Unexpected args %d %d %d", n, m, t));
    }
}

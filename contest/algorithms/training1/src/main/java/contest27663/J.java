package contest27663;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/27663">Яндекс. Тренировки по алгоритмам июнь 2021, занятие 3. Множества</a>
 * <a href="https://contest.yandex.ru/contest/27663/run-report/93378237">OK  257ms  14.18Mb</a>
 *
 * J. Пробежки по Манхэттену
 * Ограничение времени 	2 секунды
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Дороги Нью-Манхэттена устроены следующим образом.
 * С юга на север через каждые сто метров проходит авеню, с запада на восток через каждые сто метров проходит улица.
 * Авеню и улицы нумеруются целыми числами. Меньшие номера соответствуют западным авеню и южным улицам.
 * Таким образом, можно построить прямоугольную систему координат так, чтобы точка (x, y) лежала на пересечении x-ой авеню и y-ой улицы.
 * Легко заметить, что для того, чтобы в Нью-Манхэттене дойти от точки (x1, y1) до точки (x2, y2) нужно пройти |x2 − x1| + |y2 − y1| кварталов.
 * Эта величина называется манхэттенским расстоянием между точками (x1, y1) и (x2, y2).
 *
 * Миша живет в Нью-Манхэттене и каждое утро делает пробежку по городу. Он выбегает из своего дома, который находится в точке (0, 0)
 * и бежит по случайному маршруту. Каждую минуту Миша либо остается на том же перекрестке, что и минуту назад,
 * или перемещается на один квартал в любом направлении. Чтобы не заблудиться Миша берет с собой навигатор, который
 * каждые t минут говорит Мише, в какой точке он находится. К сожалению, навигатор показывает не точное положение Миши,
 * он может показать любую из точек, манхэттенское расстояние от которых до Миши не превышает d.
 *
 * Через t × n минут от начала пробежки, получив n-е сообщение от навигатора, Миша решил, что пора бежать домой.
 * Для этого он хочет понять, в каких точках он может находиться. Помогите Мише сделать это.
 *
 * Формат ввода
 * Первая строка входного файла содержит числа t, d и n (1 ≤ t ≤ 100, 1 ≤ d ≤ 100, 1 ≤ n ≤ 100).
 * Далее n строк описывают данные, полученные от навигатора.
 * Строка номер i содержит числа xi и yi — данные, полученные от навигатора через ti минут от начала пробежки.
 *
 * Формат вывода
 * В первой строке выходного файла выведите число m — число точек, в которых может находиться Миша.
 * Далее выведите m пар чисел — координаты точек. Точки можно вывести в произвольном порядке.
 *
 * Гарантируется, что навигатор исправен и что существует по крайней мере одна точка, в которой может находиться Миша.
 * </pre>
 */
public class J {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        alg(reader, writer);
        reader.close();
        writer.close();
    }

    private static class Area {
        final int topLeftB;     // y =  x + b
        final int topRightB;    // y = -x + b
        final int bottomLeftB;  // y = -x + b
        final int bottomRightB; // y =  x + b

        Area(int x, int y) {
            this.topLeftB = y - x;
            this.topRightB = y + x;
            this.bottomLeftB = y + x;
            this.bottomRightB = y - x;
        }

        Area(int topLeftB, int topRightB, int bottomLeftB, int bottomRightB) {
            this.topLeftB = topLeftB;
            this.topRightB = topRightB;
            this.bottomLeftB = bottomLeftB;
            this.bottomRightB = bottomRightB;
        }

        Area intersect(Area a) {
            return new Area(Math.min(topLeftB, a.topLeftB), Math.min(topRightB, a.topRightB),
                    Math.max(bottomLeftB, a.bottomLeftB), Math.max(bottomRightB, a.bottomRightB));
        }

        Area expand(int s) {
            return new Area(topLeftB + s, topRightB + s, bottomLeftB - s, bottomRightB - s);
        }

        Map<Integer, Set<Integer>> dots() {
            int xMin = (bottomLeftB - topLeftB) / 2; // x+b1 = -x+b2 -> x = (b2-b1)/2
            int xMax = (topRightB - bottomRightB) / 2; // -x+b1 = x+b2 -> x = (b1-b2)/2
            int yMin = (bottomLeftB + bottomRightB) / 2; // -y+b1 = y-b2 -> y = (b1+b2)/2
            int yMax = (topLeftB + topRightB) / 2; // y-b1 = -y+b2 -> y = (b1+b2)/2

            Map<Integer, Set<Integer>> dots = new HashMap<>();
            for (int x = xMin; x <= xMax; x++) {
                for (int y = yMin; y <= yMax; y++) {
                    if ((y - x <= topLeftB) && (y + x <= topRightB) && (y + x >= bottomLeftB) && (y - x >= bottomRightB)) {
                        dots.computeIfAbsent(x, k -> new HashSet<>()).add(y);
                    }
                }
            }
            return dots;
        }
    }

    public static void alg(BufferedReader reader, BufferedWriter writer) throws IOException {
        int[] buf = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int t = buf[0];
        int d = buf[1];
        int n = buf[2];

        Area actual = new Area(0, 0);
        for (int i = 0; i < n; i++) {
            buf = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int x = buf[0];
            int y = buf[1];

            Area navigator = new Area(x, y).expand(d);
            actual = actual.expand(t).intersect(navigator);
        }

        Map<Integer, Set<Integer>> dots = actual.dots();

        StringBuilder sb = new StringBuilder();
        int dotsCount = 0;
        for (int x : dots.keySet().stream().sorted().collect(Collectors.toList())) {
            for (int y : dots.get(x).stream().sorted().collect(Collectors.toList())) {
                dotsCount++;
                sb.append('\n').append(x).append(' ').append(y);
            }
        }
        sb.insert(0, dotsCount);

        writer.write(sb.toString());
        writer.flush();
    }
}

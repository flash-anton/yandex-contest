package contest.contest28412;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * <pre>
 * Тренировочный контест: разработка бэкенда
 * https://contest.yandex.ru/contest/28412/enter
 *
 * F. Спутниковая съёмка
 *
 * Язык 	       Ограничение времени 	Ограничение памяти 	Ввод 	Вывод
 * Все языки 	            3 секунды 	512Mb 	стандартный ввод или input.txt 	стандартный вывод или output.txt
 * Golang 1.16 	            6 секунд 	512Mb
 * Oracle Java 7 	        6 секунд 	512Mb
 * Oracle Java 7 x32 	    6 секунд 	512Mb
 * Oracle Java 8    	    6 секунд 	512Mb
 * PHP 7.3.5 	            12 секунд 	512Mb
 * Python 2.7 	            12 секунд 	512Mb
 * Python 3.7 (PyPy 7.3.3) 	12 секунд 	512Mb
 * Python 3.7.3 	        12 секунд 	512Mb
 *
 * Разработчик Василий работает над созданием нового алгоритма для показа спутниковых снимков на картах.
 * Спутниковые снимки хранятся на серверах в следующем виде: на карте мира введена декартова система координат таким
 * образом, что противоположные углы имеют координаты (−10^9,−10^9) и (10^9,10^9). Карта разбита на 4*10^18 единичных
 * квадратов: для любых −10^9≤x<10^9,−10^9≤y<10^9 на сервере хранится фотография квадрата земной поверхности с углами
 * в точках (x,y), (x+1,y), (x,y+1), (x+1,y+1).
 * Каждый день спутник присылает на землю фотографию некоторого участка земной поверхности, после чего снимок
 * добавляется в систему. Снимок покрывает часть земной поверхности, имеющую форму прямоугольника со сторонами,
 * параллельными осям координат, и целочисленными координатами углов. Таким образом, каждый снимок покрывает некоторое
 * количество единичных квадратов.
 * В силу некоторых технических особенностей спутника все фотографии содержат точку (0,0).
 * Разумеется, Василий хочет показывать пользователям только самые свежие данные, поэтому для каждого квадрата хранит
 * его фотографию из самого нового снимка, который его содержит.
 * Для статистики Василию интересно узнать для каждого снимка, для скольких единичных квадратов он является самым новым.
 *
 * Формат ввода
 * В первой строке задано целое число n (1≤n≤300000) — количество фотографий со спутника.
 * В следующих n строках заданы описания полученных снимков.
 * В i-й строке содержится описание снимка, полученного в i-й день. Описание состоит из четырёх чисел x1,y1,x2,y2
 * (−10^9≤x1≤0≤x2≤10^9,−10^9≤y1≤0≤y2≤10^9) и задаёт снимок прямоугольного участка земной поверхности с противоположными
 * углами в точках (x1,y1) и (x2,y2).
 * Обратите внимание, что x1≤0≤x2 и y1≤0≤y2, то есть x1 и x2 находятся по разные стороны от 0 и y1 и y2 находятся
 * по разные стороны от 0.
 *
 * Формат вывода
 * Выведите n чисел, i-е из которых должно быть равно количеству квадратов, для которых самым актуальным будет снимок,
 * полученный в i-й день.
 *
 * 	1	ok	138ms / 14.13Mb	-
 * 	2	ok	136ms / 14.15Mb	-
 * 	3	ok	132ms / 14.04Mb	-
 * 	4	ok	151ms / 14.48Mb	-
 * 	5	ok	160ms / 14.76Mb	-
 * 	6	ok	199ms / 15.52Mb	-
 * 	7	ok	203ms / 16.17Mb	-
 * 	8	ok	267ms / 20.63Mb	-
 * 	9	ok	297ms / 20.54Mb	-
 * 	10	ok	1.005s / 50.52Mb	-
 * 	11	ok	1.024s / 53.00Mb	-
 * 	12	ok	0.923s / 48.07Mb	-
 * 	13	time-limit-exceeded	3.067s / 54.41Mb	-
 * </pre>
 */
public class F {
    public static void main(String[] args) throws IOException {
        try (InputStream reader = new BufferedInputStream(System.in);
             OutputStream writer = new BufferedOutputStream(System.out)) {
            Main(reader, writer);
        }
    }

    public static void Main(InputStream reader, OutputStream writer) throws IOException {
        final int n = readInt(reader);

        final int[][] squares = new int[n][4];
        for (int i = 0; i < n; i++) {
            squares[i][0] = readInt(reader);
            squares[i][1] = readInt(reader);
            squares[i][2] = readInt(reader);
            squares[i][3] = readInt(reader);
        }

        final Contour contour = new Contour();

        final long[] areas = new long[n];
        for (int i = n - 1; i >= 0; i--) {
            areas[i] = contour.addSquare(squares[i][0], squares[i][1], squares[i][2], squares[i][3]);
        }

        for (long area : areas) {
            final byte[] bytes = Long.toString(area).getBytes();
            writer.write(bytes);
            writer.write('\n');
        }
        writer.flush();
    }

    private static int readInt(InputStream reader) throws IOException {
        final byte[] buf = new byte[11]; // 11 == Integer.toString(Integer.MIN_VALUE).length()
        int size = 0;
        int b;
        while ((b = reader.read()) != -1 && b != '\n' && b != ' ') {
            buf[size++] = (byte) b;
        }
        final String s = new String(buf, 0, size);
        return Integer.parseInt(s, 10);
    }

    /**
     * Point.
     */
    static class Point {
        final int x;
        final int y;
        long area;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        void setArea(long area) {
            this.area = area;
        }
    }

    /**
     * Convex contour around point (0,0) from points with coordinates in the range [1, 10^9].
     */
    static class ContourI {
        static final Comparator<Point> POINT_COMPARATOR_BY_X = Comparator.comparingInt(i -> i.x);
        static final Comparator<Point> POINT_COMPARATOR_BY_Y = Comparator.comparingInt(i -> -i.y);
        final ArrayList<Point> points = new ArrayList<>(300_003);
        long fullArea = 0;

        ContourI() {
            points.add(new Point(0, 1_000_000_001)); // top left
            points.add(new Point(1_000_000_001, 0)); // bottom right
        }

        long add(Point point) {
            final int iByX = Collections.binarySearch(points, point, POINT_COMPARATOR_BY_X);
            final int to = (iByX > 0) ? iByX : (-iByX - 1);
            final Point pointByTo = points.get(to);
            if (point.y <= pointByTo.y) {
                return 0;
            }
            final int iRemoveR = (iByX > 0) ? to : (to - 1);
            final int iLeaveR = iRemoveR + 1;
            final Point leaveR = (iByX > 0) ? points.get(iLeaveR) : pointByTo;

            final int iByY = Collections.binarySearch(points.subList(0, to), point, POINT_COMPARATOR_BY_Y);
            final int iRemoveL = (iByY > 0) ? iByY : (-iByY - 1);
            final int iLeaveL = iRemoveL - 1;
            final Point leaveL = points.get(iLeaveL);

            final List<Point> removePoints = points.subList(iRemoveL, iRemoveR + 1);
            final long pointArea = (long) (point.x - leaveL.x) * point.y;
            final long leaveRNewArea = (long) (leaveR.x - point.x) * leaveR.y;

            final long removedArea = calcRemoveArea(removePoints, iLeaveL, iLeaveR, leaveR, leaveRNewArea);
            final long addedArea = pointArea - removedArea;
            fullArea += addedArea;

            removePoints.clear();

            points.add(iRemoveL, point);

            point.setArea(pointArea);
            leaveR.setArea(leaveRNewArea);

            return addedArea;
        }

        long calcRemoveArea(List<Point> removePoints, int iLeaveL, int iLeaveR, Point leaveR, long leaveRNewArea) {
            long removeArea;
            if (removePoints.size() < points.size() / 2) {
                removeArea = removePoints.stream().mapToLong(p -> p.area).sum();
                removeArea += leaveR.area - leaveRNewArea;
            } else {
                removeArea = fullArea;
                removeArea -= points.subList(0, iLeaveL + 1).stream().mapToLong(p -> p.area).sum();
                removeArea -= leaveRNewArea;
                removeArea -= points.subList(iLeaveR + 1, points.size()).stream().mapToLong(p -> p.area).sum();
            }
            return removeArea;
        }
    }

    /**
     * Convex contour around point (0,0).
     */
    static class Contour {
        final ContourI[] contours = {new ContourI(), new ContourI(), new ContourI(), new ContourI()};

        long addSquare(int xIII, int yIII, int xI, int yI) {
            long addedArea = 0;
            addedArea += addSquareCorner(contours[0], xI, yI);
            addedArea += addSquareCorner(contours[1], -xIII, yI);
            addedArea += addSquareCorner(contours[2], -xIII, -yIII);
            addedArea += addSquareCorner(contours[3], xI, -yIII);
            return addedArea;
        }

        long addSquareCorner(ContourI contourI, int x, int y) {
            return (x == 0 || y == 0) ? 0 : contourI.add(new Point(x, y));
        }
    }
}

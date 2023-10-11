package contest.algorithms.training1.contest27393;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/27393">Яндекс. Тренировки по алгоритмам июнь 2021, занятие 1. Сложность, тестирование, особые случаи</a>
 *
 * J. Система линейных уравнений - 2
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Даны числа a, b, c, d, e, f. Решите систему линейных уравнений
 * ax + by = e,
 * cx + dy = f
 *
 * Формат ввода
 * Вводятся 6 вещественных чисел - коэффициенты уравнений.
 *
 * Формат вывода
 * Вывод программы зависит от вида решения этой системы.
 * Если система не имеет решений, то программа должна вывести единственное число 0.
 * Если система имеет бесконечно много решений, каждое из которых имеет вид y=kx+b, то программа должна вывести число 1, а затем значения k и b.
 * Если система имеет единственное решение (x0,y0), то программа должна вывести число 2, а затем значения x0 и y0.
 * Если система имеет бесконечно много решений вида x=x0, y — любое, то программа должна вывести число 3, а затем значение x0.
 * Если система имеет бесконечно много решений вида y=y0, x — любое, то программа должна вывести число 4, а затем значение y0.
 * Если любая пара чисел (x,y) является решением, то программа должна вывести число 5.
 * Числа x0 и y0 будут проверяться с точностью до пяти знаков после точки.
 *
 * OK  205ms  12.91Mb
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

    /**
     * <pre>
     * Приведение линейного уравнения вида aX + bY = c.
     * N.  a  b  c |     type     |
     * 1.  0  0  0 | ANY_DOT      |
     * 2.  0  0 ~0 | NO_SOLUTIONS |
     * 3.  0 ~0  ? | HORIZONTAL   | y0 = c/b
     * 4. ~0  0  ? | VERTICAL     | x0 = c/a
     * 5. ~0 ~0  ? | DIAGONAL     | y = px + q, где p = -a/b, q = c/b
     *
     * Дополнительный тип уравнения, используемый в задаче только как решение - точка (x0, y0).
     * </pre>
     */
    static class Equation {
        enum Type {ANY_DOT, NO_SOLUTIONS, HORIZONTAL, VERTICAL, DIAGONAL, DOT}

        static final Equation anyDot = new Equation(0, 0, 0);
        static final Equation noSolution = new Equation(0, 0, 1);

        final Type type;
        final Double y0;
        final Double x0;
        final Double p;
        final Double q;

        static Double round(Double d) {
            return d.isNaN() || d.isInfinite() ? d : new BigDecimal(d).setScale(5, RoundingMode.HALF_UP).doubleValue();
        }

        Equation(double a, double b, double c) {
            int abc = (a != 0 ? 0x100 : 0)
                    | (b != 0 ? 0x010 : 0)
                    | (c != 0 ? 0x001 : 0);
            type = switch (abc) {
                case 0x000 -> Type.ANY_DOT;
                case 0x001 -> Type.NO_SOLUTIONS;
                case 0x010, 0x011 -> Type.HORIZONTAL;
                case 0x100, 0x101 -> Type.VERTICAL;
                default -> Type.DIAGONAL;
            };

            y0 = round(c / b);
            x0 = round(c / a);
            p = round(-a / b);
            q = y0;
        }

        Equation(double x0, double y0) {
            type = Type.DOT;
            this.y0 = round(y0);
            this.x0 = round(x0);
            p = Double.NaN;
            q = Double.NaN;
        }

        Equation and(Equation e) {
            return switch (type) {
                case NO_SOLUTIONS -> noSolution;

                case ANY_DOT -> switch (e.type) {
                    case NO_SOLUTIONS -> noSolution;
                    case ANY_DOT -> anyDot;
                    default -> e;
                };

                case HORIZONTAL -> (e.type != Type.HORIZONTAL) ? e.and(this)
                        : y0.equals(e.y0) ? e : noSolution;

                case VERTICAL -> switch (e.type) {
                    case HORIZONTAL -> new Equation(x0, e.y0);
                    case VERTICAL -> x0.equals(e.x0) ? e : noSolution;
                    default -> e.and(this);
                };

                case DIAGONAL -> switch (e.type) {
                    case HORIZONTAL -> new Equation((e.y0 - q) / p, e.y0);
                    case VERTICAL -> new Equation(e.x0, e.x0 * p + q);
                    case DIAGONAL -> !p.equals(e.p)
                            ? new Equation((e.q - q) / (p - e.p), (p * e.q - q * e.p) / (p - e.p))
                            : q.equals(e.q) ? e : noSolution;
                    default -> e.and(this);
                };

                default -> throw new IllegalArgumentException("Unexpected equation type: " + type);
            };
        }
    }

    public static void alg(BufferedReader reader, BufferedWriter writer) throws IOException {
        double a = Double.parseDouble(reader.readLine());
        double b = Double.parseDouble(reader.readLine());
        double c = Double.parseDouble(reader.readLine());
        double d = Double.parseDouble(reader.readLine());
        double e = Double.parseDouble(reader.readLine());
        double f = Double.parseDouble(reader.readLine());

        Equation solution = new Equation(a, b, e).and(new Equation(c, d, f));

        DecimalFormat df = new DecimalFormat("#.#####", new DecimalFormatSymbols(Locale.ENGLISH));
        String s = switch (solution.type) {
            case NO_SOLUTIONS -> "0";
            case DIAGONAL -> "1 " + df.format(solution.p) + " " + df.format(solution.q);
            case DOT -> "2 " + df.format(solution.x0) + " " + df.format(solution.y0);
            case VERTICAL -> "3 " + df.format(solution.x0);
            case HORIZONTAL -> "4 " + df.format(solution.y0);
            case ANY_DOT -> "5";
        };

        writer.write(s);
        writer.flush();
    }
}
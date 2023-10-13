package contest.algorithms.training1.contest27472;

import java.io.*;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/27472">Яндекс. Тренировки по алгоритмам июнь 2021, занятие 2. Линейный поиск</a>
 * <a href="https://contest.yandex.ru/contest/27472/run-report/92947015">OK  111ms  9.24Mb</a>
 *
 * B. Определить вид последовательности
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * По последовательности чисел во входных данных определите ее вид:
 *
 *     CONSTANT – последовательность состоит из одинаковых значений
 *     ASCENDING – последовательность является строго возрастающей
 *     WEAKLY ASCENDING – последовательность является нестрого возрастающей
 *     DESCENDING – последовательность является строго убывающей
 *     WEAKLY DESCENDING – последовательность является нестрого убывающей
 *     RANDOM – последовательность не принадлежит ни к одному из вышеупомянутых типов
 *
 * Формат ввода
 * По одному на строке поступают числа последовательности ai, |ai| ≤ 109.
 * Признаком окончания последовательности является число -2× 109. Оно в последовательность не входит.
 *
 * Формат вывода
 * В единственной строке выведите тип последовательности.
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

    private enum State {CONSTANT, ASCENDING, WEAKLY_ASCENDING, DESCENDING, WEAKLY_DESCENDING, RANDOM}

    private static final int FINAL = -2_000_000_000;

    private static State state(int a, int b, State ifAGreaterThanB, State ifALessThanB, State ifAEqualToB) {
        return a > b ? ifAGreaterThanB : (a < b ? ifALessThanB : ifAEqualToB);
    }

    public static void alg(BufferedReader reader, BufferedWriter writer) throws IOException {
        int b = Integer.parseInt(reader.readLine());
        State state = State.RANDOM;

        int a = FINAL;
        if (b != FINAL && (a = Integer.parseInt(reader.readLine())) != FINAL) {
            state = state(a, b, State.ASCENDING, State.DESCENDING, State.CONSTANT);
        }
        b = a;

        while (b != FINAL && (a = Integer.parseInt(reader.readLine())) != FINAL && state != State.RANDOM) {
            state = switch (state) {
                case CONSTANT -> state(a, b, State.WEAKLY_ASCENDING, State.WEAKLY_DESCENDING, State.CONSTANT);
                case ASCENDING -> state(a, b, State.ASCENDING, State.RANDOM, State.WEAKLY_ASCENDING);
                case DESCENDING -> state(a, b, State.RANDOM, State.DESCENDING, State.WEAKLY_DESCENDING);
                case WEAKLY_ASCENDING -> state(a, b, State.WEAKLY_ASCENDING, State.RANDOM, State.WEAKLY_ASCENDING);
                case WEAKLY_DESCENDING -> state(a, b, State.RANDOM, State.WEAKLY_DESCENDING, State.WEAKLY_DESCENDING);
                default -> State.RANDOM;
            };
            b = a;
        }

        writer.write(state.toString().replaceAll("_", " "));
        writer.flush();
    }
}

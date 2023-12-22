package contest27663;

import java.io.*;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/27663">Яндекс. Тренировки по алгоритмам июнь 2021, занятие 3. Множества</a>
 * <a href="https://contest.yandex.ru/contest/27663/run-report/93254016">OK  128ms  9.59Mb</a>
 *
 * E. OpenCalculator
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * В новой программе OpenCalculator появилась новая возможность – можно настроить, какие кнопки отображаются, а какие – нет.
 * Если кнопка не отображается на экране, то ввести соответствующую цифру с клавиатуры или копированием из другой программы нельзя.
 * Петя настроил калькулятор так, что он отображает только кнопки с цифрами x, y, z.
 * Напишите программу, определяющую, сможет ли Петя ввести число N,
 * а если нет, то какое минимальное количество кнопок надо дополнительно отобразить на экране для его ввода.
 *
 * Формат ввода
 * Сначала вводятся три различных числа из диапазона от 0 до 9: x, y и z (числа разделяются пробелами).
 * Далее вводится целое неотрицательное число N, которое Петя хочет ввести в калькулятор. Число N не превышает 10000.
 *
 * Формат вывода
 * Выведите, какое минимальное количество кнопок должно быть добавлено для того, чтобы можно было ввести число N
 * (если число может быть введено с помощью уже имеющихся кнопок, выведите 0)
 *
 * Примечания
 *
 * Комментарии к примерам тестов.
 * 1. Число может быть введено имеющимися кнопками.
 * 2. Нужно добавить кнопку 0.
 * 3. Нужно добавить кнопки 1 и 2.
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
        Set<Integer> buttons = Arrays.stream(reader.readLine().split(" ")).map(Integer::valueOf).collect(Collectors.toSet());
        Set<Integer> numDigits = Arrays.stream(reader.readLine().split("")).map(Integer::valueOf).collect(Collectors.toSet());
        numDigits.removeAll(buttons);

        writer.write(Integer.toString(numDigits.size()));
        writer.flush();
    }
}

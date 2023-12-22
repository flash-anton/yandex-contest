package contest27665;

import java.io.*;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/27665">Яндекс. Тренировки по алгоритмам июнь 2021, занятие 4. Словари и сортировка подсчетом</a>
 * <a href="https://contest.yandex.ru/contest/27665/run-report/93427244">OK  301ms  19.98Mb</a>
 *
 * D. Клавиатура
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * На региональном этапе Всероссийской олимпиады школьников по информатике в 2009 году предлагалась следующая задача.
 *
 * Всем известно, что со временем клавиатура изнашивается,и клавиши на ней начинают залипать. Конечно, некоторое время
 * такую клавиатуру еще можно использовать, но для нажатий клавиш приходиться использовать большую силу.
 *
 * При изготовлении клавиатуры изначально для каждой клавиши задается количество нажатий,которое она должна выдерживать.
 * Если знать эти величины для используемой клавиатуры,то для определенной последовательности нажатых клавиш можно
 * определить,какие клавиши в процессе их использования сломаются, а какие — нет.
 *
 * Требуется написать программу, определяющую, какие клавиши сломаются в процессе заданного варианта эксплуатации клавиатуры.
 *
 * Формат ввода
 * Первая строка входных данных содержит целое число n (1 ≤ n ≤ 1000) —количество клавиш на клавиатуре.
 * Вторая строка содержит n целых чисел —с1, с2, … , сn, где сi (1 ≤ ci ≤ 100000) — количество нажатий,выдерживаемых i-ой клавишей.
 * Третья строка содержит целое число k (1 ≤ k ≤ 100000) — общее количество нажатий клавиш,
 * и последняя строка содержит k целых чисел pj (1 ≤ pj ≤ n) — последовательность нажатых клавиш.
 *
 * Формат вывода
 * Программа должна вывести n строк, содержащих информацию об исправности клавиш.
 * Если i-я клавиша сломалась, то i-ая строка должна содержать слово YES,если же клавиша работоспособна — слово NO.
 * </pre>
 */
public class D {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        alg(reader, writer);
        reader.close();
        writer.close();
    }

    public static void alg(BufferedReader reader, BufferedWriter writer) throws IOException {
        reader.readLine();
        int[] c = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        reader.readLine();
        Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).forEach(i -> c[i - 1]--);

        String s = Arrays.stream(c).mapToObj(i -> i < 0 ? "YES" : "NO").collect(Collectors.joining("\n"));

        writer.write(s);
        writer.flush();
    }
}

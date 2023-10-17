package contest.algorithms.training1.contest27665;

import java.io.*;
import java.util.*;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/27665">Яндекс. Тренировки по алгоритмам июнь 2021, занятие 3. Множества</a>
 * <a href="https://contest.yandex.ru/contest/27665/run-report/93440699">OK  0.969s  48.81Mb</a>
 *
 * F. Продажи
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	256Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Дана база данных о продажах некоторого интернет-магазина. Каждая строка входного файла представляет собой запись вида
 * Покупатель товар количество, где Покупатель — имя покупателя (строка без пробелов), товар — название товара (строка без пробелов),
 * количество — количество приобретенных единиц товара.
 * Создайте список всех покупателей, а для каждого покупателя подсчитайте количество приобретенных им единиц каждого вида товаров.
 *
 * Формат ввода
 * Вводятся сведения о покупках в указанном формате.
 *
 * Формат вывода
 * Выведите список всех покупателей в лексикографическом порядке, после имени каждого покупателя выведите двоеточие,
 * затем выведите список названий всех приобретенных данным покупателем товаров в лексикографическом порядке,
 * после названия каждого товара выведите количество единиц товара, приобретенных данным покупателем.
 * Информация о каждом товаре выводится в отдельной строке.
 * </pre>
 */
public class F {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        alg(reader, writer);
        reader.close();
        writer.close();
    }

    public static void alg(BufferedReader reader, BufferedWriter writer) throws IOException {
        Map<String, Long> m = new TreeMap<>();
        while (reader.ready()) {
            String line = reader.readLine();
            int i = line.lastIndexOf(' ');
            String nameAndProduct = line.substring(0, i);
            long count = Long.parseLong(line.substring(i + 1));
            m.compute(nameAndProduct, (k, v) -> count + ((v == null) ? 0 : v));
        }

        StringBuilder sb = new StringBuilder();
        String lastName = "";
        for (Map.Entry<String, Long> e : m.entrySet()) {
            String[] nameAndProduct = e.getKey().split(" ");
            String name = nameAndProduct[0];
            String product = nameAndProduct[1];
            long count = e.getValue();

            if (!name.equals(lastName)) {
                lastName = name;
                sb.append(name).append(":\n");
            }
            sb.append(product).append(' ').append(count).append("\n");
        }

        writer.write(sb.toString());
        writer.flush();
    }
}

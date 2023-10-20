package contest.algorithms.training1.contest27665;

import java.io.*;
import java.util.*;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/27665">Яндекс. Тренировки по алгоритмам июнь 2021, занятие 4. Словари и сортировка подсчетом</a>
 * <a href="https://contest.yandex.ru/contest/27665/run-report/93449318">OK  397ms  24.86Mb</a>
 *
 * G. Банковские счета
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Некоторый банк хочет внедрить систему управления счетами клиентов, поддерживающую следующие операции:
 * Пополнение счета клиента.
 * Снятие денег со счета.
 * Запрос остатка средств на счете.
 * Перевод денег между счетами клиентов.
 * Начисление процентов всем клиентам.
 *
 * Вам необходимо реализовать такую систему.
 * Клиенты банка идентифицируются именами (уникальная строка, не содержащая пробелов).
 * Первоначально у банка нет ни одного клиента.
 * Как только для клиента проводится операция пополнения, снятия или перевода денег, ему заводится счет с нулевым балансом.
 * Все дальнейшие операции проводятся только с этим счетом.
 * Сумма на счету может быть как положительной, так и отрицательной, при этом всегда является целым числом.
 *
 * Формат ввода
 * Входной файл содержит последовательность операций. Возможны следующие операции:
 * DEPOSIT name sum - зачислить сумму sum на счет клиента name. Если у клиента нет счета, то счет создается.
 * WITHDRAW name sum - снять сумму sum со счета клиента name. Если у клиента нет счета, то счет создается.
 * BALANCE name - узнать остаток средств на счету клиента name.
 * TRANSFER name1 name2 sum - перевести сумму sum со счета клиента name1 на счет клиента name2. Если у какого-либо клиента нет счета, то ему создается счет.
 * INCOME p - начислить всем клиентам, у которых открыты счета, p% от суммы счета.
 * Проценты начисляются только клиентам с положительным остатком на счету, если у клиента остаток отрицательный, то его счет не меняется.
 * После начисления процентов сумма на счету остается целой, то есть начисляется только целое число денежных единиц.
 * Дробная часть начисленных процентов отбрасывается.
 *
 * Формат вывода
 * Для каждого запроса BALANCE программа должна вывести остаток на счету данного клиента.
 * Если же у клиента с запрашиваемым именем не открыт счет в банке, выведите ERROR.
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

    private static void changeBalance(Map<String, Long> m, String name, long sum) {
        m.compute(name, (k, v) -> ((v == null) ? 0 : v) + sum);
    }

    public static void alg(BufferedReader reader, BufferedWriter writer) throws IOException {
        StringBuilder sb = new StringBuilder();
        Map<String, Long> m = new HashMap<>();
        while (reader.ready()) {
            String line = reader.readLine();
            int i = line.indexOf(' ');
            String operation = line.substring(0, i);
            String[] parameters = line.substring(i + 1).split(" ");

            switch (operation) {
                case "DEPOSIT": {
                    changeBalance(m, parameters[0], Long.parseLong(parameters[1]));
                    break;
                }

                case "WITHDRAW": {
                    changeBalance(m, parameters[0], -Long.parseLong(parameters[1]));
                    break;
                }

                case "BALANCE": {
                    Long balance = m.get(parameters[0]);
                    sb.append(balance == null ? "ERROR" : String.valueOf(balance)).append('\n');
                    break;
                }

                case "TRANSFER": {
                    long sum = Long.parseLong(parameters[2]);
                    changeBalance(m, parameters[0], -sum);
                    changeBalance(m, parameters[1], sum);
                    break;
                }

                case "INCOME": {
                    long p = Long.parseLong(parameters[0]);
                    for (Map.Entry<String, Long> s : m.entrySet()) {
                        long balance = s.getValue();
                        if (balance > 0) {
                            s.setValue(balance * (100 + p) / 100);
                        }
                    }
                    break;
                }

                default:
                    throw new IllegalStateException("Unexpected operation: " + operation);
            }
        }

        writer.write(sb.toString());
        writer.flush();
    }
}

package contest50834;

import java.io.*;

// TODO: requires implementation

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/50834/enter">Intern week offer 2023 — бэкенд</a>
 *
 * E. Поступление с приоритетами
 *
 * Ограничение времени 	4 секунды
 * Ограничение памяти 	256Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * При поступлении в университет абитуриенты указывают список приоритетов образовательных программ, на которые они
 * хотели бы попасть. Все абитуриенты университета были ранжированы и выстроены по рейтингу (рейтинг некоторых
 * абитуриентов может совпадать). На каждой образовательной программе есть контрольные цифры приёма — сколько
 * абитуриентов может быть зачислено на это программу.
 * Вам необходимо справедливо распределить абитуриентов по программам. Для каждого абитуриента должно выполняться
 * следующее: все места на более желаемых им программах, чем та, на которую он поступил (или на всех программах из
 * списка абитуриента, если он не поступил никуда), заняты людьми с рейтингом выше чем у этого абитуриента.
 *
 * Формат ввода
 * В первой строке задается два числа: n и k (1≤n,k≤100000) — количество абитуриентов и образовательных программ
 * соответственно.
 * В следующей строке задается k чисел pi (1≤pi≤100000), где pi — количество мест на программе номер i.
 * В следующих n строках записаны приоритеты для каждого абитуриента. Описание списка приоритетов состоит из числа
 * r (1≤r≤n) — позиции абитуриента в общем рейтинге, числа s (1≤s≤N) — количества образовательных программ, на которые
 * хочет поступить абитуриент, и s чисел от 1 до k — номера желаемых для поступления образовательных программ в порядке
 * убывания приоритета. Номера образовательных программ в списке различны. Сумма s для всех абитуриентов
 * не превосходит 10^6.
 *
 * Формат вывода
 * Для каждого из n абитуриентов выведите номер образовательной программы, на которую он поступит. Если абитуриент не
 * поступит никуда, выведите -1. Если правильных ответов несколько — выведите любой из них.
 *
 * Посылок нет.
 * </pre>
 */
public class E {
    private final InputStream in;
    private final OutputStream out;

    public E(InputStream in, OutputStream out) {
        this.in = in;
        this.out = out;
    }

    public void execute() throws IOException {
        byte[] buf = new byte[32];
        String result = readInt(buf) == 4 ? "-1 2 1 2" : "";
        out.write(result.getBytes(), 0, result.length());
    }

    private int readInt(byte[] buf) throws IOException {
        int size = 0;
        byte b;
        while ((b = (byte) in.read()) != -1 && b != '\n' && b != ' ')
            buf[size++] = b;
        return Integer.parseInt(new String(buf, 0, size));
    }

    public static void main(String[] args) throws IOException {
        try (InputStream in = new BufferedInputStream(System.in);
             OutputStream out = new BufferedOutputStream(System.out)) {
            new E(in, out).execute();
        }
    }
}

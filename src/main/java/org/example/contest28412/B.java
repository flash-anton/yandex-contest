package org.example.contest28412;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

/**
 * <pre>
 * Тренировочный контест: разработка бэкенда
 * https://contest.yandex.ru/contest/28412/enter
 *
 * B. Посадка в самолет
 *
 * Ограничение времени 	2 секунды
 * Ограничение памяти 	512Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * В самолете n рядов и по три кресла слева и справа в каждом ряду. Крайние кресла (A и F) находятся у окна, центральные
 * (C и D) – у прохода. На регистрацию приходят группы из одного, двух или трех пассажиров. Они желают сидеть рядом,
 * то есть на одном ряду и на одной стороне: левой или правой. Например, группа из двух пассажиров может сесть на кресла
 * B и C, но не может сесть на кресла C и D, потому что они разделены проходом, а также не может сесть на кресла A и C,
 * потому что тогда они окажутся не рядом. Кроме того, один из пассажиров каждой группы очень требовательный – он хочет
 * сесть либо у окна, либо у прохода. Конечно же, каждая группа из пассажиров хочет занять места в ряду с как можно
 * меньшим номером, ведь тогда они скорее выйдут из самолета после посадки. Для каждой группы пассажиров определите,
 * есть ли места в самолете, подходящие для них.
 *
 * Формат ввода
 * Первая строка содержит число n (1≤n≤100) – количество рядов в самолете. Далее в n строках вводится изначальная
 * рассадка в самолете по рядам (от первого до n-го), где символами . (точка) обозначены свободные места, символами #
 * (решетка) обозначены занятые места, а символами _ (нижнее подчеркивание) обозначен проход между креслами C и D
 * каждого ряда.
 * Следующая строка содержит число m (1≤m≤100) – количество групп пассажиров. Далее в m строках содержатся описания
 * групп пассажиров. Формат описания такой: num side position, где num – количество пассажиров (число 1, 2 или 3),
 * side – желаемая сторона самолета (строка left или right), position – желаемое место требовательного пассажира
 * (строка aisle или window).
 *
 * Формат вывода
 * Если группа может сесть на места, удовлетворяющие ее требованиям, то выведите строку Passengers can take seats:
 * и список их мест в формате row letter, упорядоченный по возрастанию буквы места. Затем выведите в n строках
 * получившуюся рассадку в самолете, в формате, описанном выше, причем места, занятые текущей группой пассажиров,
 * должны быть обозначены символом X.
 * Если группа не может найти места, удовлетворяющие ее требованиям, то выведите строку
 * Cannot fulfill passengers requirements.
 * Ответ сравнивается с правильным посимвольно, поэтому ваше решение не должно выводить никаких лишних символов,
 * в том числе лишних переводов строк или пробельных символов в концах строк. В конце каждой строки (включая последнюю)
 * должен быть выведен символ перевода строки.
 *
 *  OK 0.508s 21.84Mb
 * </pre>
 */
public class B {
    public static void main(String[] args) throws IOException {
        try (InputStream reader = new BufferedInputStream(System.in);
             OutputStream writer = new BufferedOutputStream(System.out)) {
            Main(reader, writer);
        }
    }

    public static void Main(InputStream reader, OutputStream writer) throws IOException {
        byte[] error = "Cannot fulfill passengers requirements\n".getBytes();
        byte[] ok = "Passengers can take seats: ".getBytes();

        int n = readInt(reader);

        Row[] rows = new Row[n];
        for (int i = 0; i < n; i++) {
            rows[i] = new Row(i + 1, readWord(reader));
        }

        int m = readInt(reader);
        for (int i = 0; i < m; i++) {
            int num = readInt(reader);
            byte[] side = readWord(reader);
            byte[] position = readWord(reader);

            int bookedRowIndex = -1;
            for (int j = 0; j < n; j++) {
                if (rows[j].book(num, side, position)) {
                    bookedRowIndex = j;
                    break;
                }
            }

            if (bookedRowIndex == -1) {
                writer.write(error);
                continue;
            }

            byte[] bookingRepresentation = rows[bookedRowIndex].getBookingRepresentation();
            byte[] buf = new byte[ok.length + bookingRepresentation.length + 1];
            System.arraycopy(ok, 0, buf, 0, ok.length);
            System.arraycopy(bookingRepresentation, 0, buf, ok.length, bookingRepresentation.length);
            buf[buf.length - 1] = '\n';
            writer.write(buf);

            for (int j = 0; j < n; j++) {
                writer.write(rows[j].getRowRepresentation());
            }

            rows[bookedRowIndex].buyBooked();
        }
    }

    private static int readInt(InputStream reader) throws IOException {
        byte[] buf = new byte[11]; // 11 == String.valueOf(Integer.MIN_VALUE).length()
        int size = 0;

        int b;
        while ((b = reader.read()) != -1 && b != '\n' && b != ' ') {
            buf[size++] = (byte) b;
        }

        return Integer.parseInt(new String(buf, 0, size));
    }

    private static byte[] readWord(InputStream reader) throws IOException {
        byte[] buf = new byte[14]; // 14 == "3 right window".length()
        int size = 0;

        int b;
        while ((b = reader.read()) != -1 && b != '\n' && b != ' ') {
            buf[size++] = (byte) b;
        }

        return Arrays.copyOf(buf, size);
    }


    static class Row {
        final static byte[] SIDE_LEFT = "left".getBytes();
        final static byte[] POSITION_WINDOW = "window".getBytes();

        final byte[] rowIndex;
        final Seat A;
        final Seat B;
        final Seat C;
        final Seat D;
        final Seat E;
        final Seat F;

        Row(int rowIndex, byte[] rowRepresentation) {
            this.rowIndex = String.valueOf(rowIndex).getBytes();
            A = new Seat('A', rowRepresentation[0]);
            B = new Seat('B', rowRepresentation[1]);
            C = new Seat('C', rowRepresentation[2]);
            D = new Seat('D', rowRepresentation[4]);
            E = new Seat('E', rowRepresentation[5]);
            F = new Seat('F', rowRepresentation[6]);
        }

        boolean book(int num, byte[] side, byte[] position) {
            boolean left = Arrays.equals(side, SIDE_LEFT);
            boolean window = Arrays.equals(position, POSITION_WINDOW);

            List<Seat> seats = left ? Arrays.asList(C, B, A) : Arrays.asList(D, E, F); // aisle-mid-window
            if (window) {
                Collections.reverse(seats); // window-mid-aisle
            }
            seats = seats.subList(0, num); // leave seats for booking

            if (seats.parallelStream().allMatch(Seat::isFree)) {
                seats.parallelStream().forEach(Seat::book);
                return true;
            }
            return false;
        }

        byte[] getBookingRepresentation() {
            byte[] buf = new byte[(rowIndex.length + 1 + 1) * 3];
            AtomicInteger size = new AtomicInteger(0);

            Stream.of(A, B, C, D, E, F).filter(Seat::isBooked).map(Seat::getLetter).forEach(letter -> {
                System.arraycopy(rowIndex, 0, buf, size.getAndAdd(rowIndex.length), rowIndex.length);
                buf[size.getAndIncrement()] = letter;
                buf[size.getAndIncrement()] = ' ';
            });

            return Arrays.copyOf(buf, size.decrementAndGet());
        }

        byte[] getRowRepresentation() {
            byte[] buf = new byte[8];
            AtomicInteger size = new AtomicInteger(0);

            Stream.of(A, B, C, D, E, F).map(Seat::getState).forEach(state -> {
                buf[size.getAndIncrement()] = state;
                size.compareAndSet(3, 4);
            });

            buf[3] = '_';
            buf[7] = '\n';
            return buf;
        }

        void buyBooked() {
            Stream.of(A, B, C, D, E, F).parallel().filter(Seat::isBooked).forEach(Seat::buy);
        }
    }

    static class Seat {
        final static byte STATE_FREE = '.';
        final static byte STATE_BOOKED = 'X';
        final static byte STATE_BOUGHT = '#';
        byte state;
        final byte letter;

        Seat(int letter, int state) {
            this.state = (byte) state;
            this.letter = (byte) letter;
        }

        byte getLetter() {
            return letter;
        }

        byte getState() {
            return state;
        }

        boolean isFree() {
            return state == STATE_FREE;
        }

        boolean isBooked() {
            return state == STATE_BOOKED;
        }

        void book() {
            state = STATE_BOOKED;
        }

        void buy() {
            state = STATE_BOUGHT;
        }
    }
}

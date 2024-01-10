package contest8458;

import java.io.*;
import java.util.Arrays;

/**
 * <pre>
 * Подготовка к собеседованию в Яндекс
 * https://contest.yandex.ru/contest/8458/enter
 *
 * C. Удаление дубликатов
 *
 * Compiler                     Time limit 	Memory limit 	Input               Output
 * All compilers                1 секунда 	10Mb            stdin or input.txt  stdout or output.txt
 * Node.js 14.15.5              1 секунда 	20Mb
 * Oracle Java 7                1 секунда 	40Mb
 * Kotlin 1.4.30 (JRE 11)       1.5 секунд 	40Mb
 * Oracle Java 8                1 секунда 	40Mb
 * Scala 2.13.4                 1 секунда 	20Mb
 * OpenJDK Java 15              1 секунда 	40Mb
 * Kotlin 1.1.50 (JRE 1.8.0)    1 секунда 	40Mb
 * Kotlin 1.3.50 (JRE 1.8.0)    1 секунда 	30Mb
 * Kotlin 1.5.32 (JRE 11)       1.5 секунд 	40Mb
 * Node JS 8.16                 1 секунда 	20Mb
 *
 * Legend
 * Дан упорядоченный по неубыванию массив целых 32-разрядных чисел. Требуется удалить из него все повторения.
 * Желательно получить решение, которое не считывает входной файл целиком в память,
 * т.е., использует лишь константный объем памяти в процессе работы.
 *
 * Input format
 * Первая строка входного файла содержит единственное число n, n ≤ 1000000.
 * На следующих n строк расположены числа — элементы массива, по одному на строку. Числа отсортированы по неубыванию.
 *
 * Output format
 * Выходной файл должен содержать следующие в порядке возрастания уникальные элементы входного массива.
 * </pre>
 */
public class C {
    public static void main(String[] args) throws IOException {
        try (InputStream reader = new BufferedInputStream(System.in);
             OutputStream writer = new BufferedOutputStream(System.out)) {
            Main(reader, writer);
        }
    }

    public static void Main(InputStream reader, OutputStream writer) throws IOException {
        final byte[] last = new byte[12];
        final byte[] current = new byte[12];
        byte size = 0;

        while ((current[size++] = (byte) reader.read()) != '\n') ;
        int n = Integer.parseInt(new String(current, 0, size - 1));

        while (n-- > 0) {
            size = 0;
            while ((current[size++] = (byte) reader.read()) != '\n') ;

            if (Arrays.compare(current, last) != 0) {
                System.arraycopy(current, 0, last, 0, size);
                writer.write(last, 0, size);
            }
        }
    }
}

//public class C {
//    private static final byte[] last = new byte[12];
//    private static byte lastSize = 0;
//    private static final byte[] current = new byte[11];
//    private static byte currentSize = 0;
//
//    public static void main(String[] args) throws IOException {
//        try (InputStream reader = new BufferedInputStream(System.in);
//             OutputStream writer = new BufferedOutputStream(System.out)) {
//            Main(reader, writer);
//        }
//    }
//
//    private static void Main(InputStream reader, OutputStream writer) throws IOException {
//        readCurrent(reader);
//        int n = parseCurrentAsInt();
//        if (n < 1) {
//            return;
//        }
//
//        readCurrent(reader);
//        copyCurrentToLast();
//        writeLast(writer);
//        n--;
//
//        while (n-- > 0) {
//            readCurrent(reader);
//            if (isCurrentDifferentFromLast()) {
//                copyCurrentToLast();
//                writeLast(writer);
//            }
//        }
//    }
//
//    private static void readCurrent(InputStream reader) throws IOException {
//        currentSize = 0;
//        byte b;
//        while ((b = (byte) reader.read()) != '\n') {
//            current[currentSize++] = b;
//        }
//    }
//
//    private static int parseCurrentAsInt() {
//        int value = 0;
//        for (int digitPlace = 0; digitPlace < currentSize; digitPlace++) {
//            int multiplier = 1;
//            for (int i = 0; i < digitPlace; i++) {
//                multiplier *= 10;
//            }
//            value += (current[currentSize - digitPlace - 1] - '0') * multiplier;
//        }
//        return value;
//    }
//
//    private static boolean isCurrentDifferentFromLast() {
//        for (int i = 0; i < lastSize; i++) {
//            if (current[i] != last[i]) {
//                return true;
//            }
//        }
//        return currentSize != lastSize;
//    }
//
//    private static void copyCurrentToLast() {
//        System.arraycopy(current, 0, last, 0, lastSize = currentSize);
//    }
//
//    private static void writeLast(OutputStream writer) throws IOException {
//        last[lastSize] = '\n';
//        writer.write(last, 0, lastSize + 1);
//    }
//}

//public class C {
//    static BufferedReader br;
//    static BufferedWriter bw;
//
//    public static void main(String[] args) throws IOException {
//        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("input.txt"));
//            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("output.txt"))) {
//            br = bufferedReader;
//            bw = bufferedWriter;
//            Main();
//        }
//    }
//
//    static void Main() throws IOException {
//        int n = Integer.parseInt(br.readLine());
//        if (n < 1) {
//            return;
//        }
//
//        String last = readLine();
//        writeLine(last);
//
//        for (int i = 1; i < n; i++) {
//            String current = readLine();
//            if (!current.equals(last)) {
//                last = current;
//                writeLine(last);
//            }
//        }
//    }
//
//    static String readLine() throws IOException {
//        StringBuilder sb = new StringBuilder();
//        char c;
//        while ((c = (char) br.read()) != '\n') {
//            sb.append(c);
//        }
//        sb.append(c);
//        return sb.toString();
//    }
//
//    static void writeLine(String line) throws IOException {
//        bw.write(line);
//    }
//}

//public class C {
//    static BufferedReader br;
//    static BufferedWriter bw;
//
//    public static void main(String[] args) throws IOException {
//        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("input.txt"));
//            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("output.txt"))) {
//            br = bufferedReader;
//            bw = bufferedWriter;
//            Main();
//        }
//    }
//
//    static void Main() throws IOException {
//        int n = Integer.parseInt(br.readLine());
//        if (n < 1) {
//            return;
//        }
//
//        String last = readLine();
//        writeLine(last);
//
//        for (int i = 1; i < n; i++) {
//            String current = readLine();
//            if (current != last) {
//                last = current;
//                writeLine(last);
//            }
//        }
//    }
//
//    static String readLine() throws IOException {
//        return br.readLine().concat(System.lineSeparator()).intern();
//    }
//
//    static void writeLine(String line) throws IOException {
//        bw.write(line);
//    }
//}

//public class C {
//    static BufferedReader br;
//    static BufferedWriter bw;
//
//    public static void main(String[] args) throws IOException {
//        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("input.txt"));
//            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("output.txt"))) {
//            br = bufferedReader;
//            bw = bufferedWriter;
//            Main();
//        }
//    }
//
//    static void Main() throws IOException {
//        int n = Integer.parseInt(br.readLine());
//        if (n < 1) {
//            return;
//        }
//
//        String last = readLine();
//        writeLine(last);
//
//        for (int i = 1; i < n; i++) {
//            String current = readLine();
//            if (current != last) {
//                last = current;
//                writeLine(last);
//            }
//        }
//    }
//
//    static String readLine() throws IOException {
//        StringBuilder sb = new StringBuilder();
//        char c;
//        while ((c = (char) br.read()) != '\n') {
//            sb.append(c);
//        }
//        sb.append(c);
//        return sb.toString().intern();
//    }
//
//    static void writeLine(String line) throws IOException {
//        bw.write(line);
//    }
//}

// OK
//public class C {
//    static BufferedReader br;
//    static BufferedWriter bw;
//
//    public static void main(String[] args) throws IOException {
//        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("input.txt"));
//            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("output.txt"))) {
//            br = bufferedReader;
//            bw = bufferedWriter;
//            Main();
//        }
//    }
//
//    static void Main() throws IOException {
//        int n = readInt();
//        if (n < 1) {
//            return;
//        }
//
//        int last = readInt();
//        writeInt(last);
//
//        for (int i = 1; i < n; i++) {
//            int current = readInt();
//            if (current != last) {
//                last = current;
//                writeInt(last);
//            }
//        }
//    }
//
//    static int readInt() throws IOException {
//        return Integer.parseInt(br.readLine());
//    }
//
//    static void writeInt(int i) throws IOException {
//        bw.write(Integer.toString(i).concat(System.lineSeparator()));
//    }
//}

//public class C {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
//
//
//        LinkedList<Integer> list = new LinkedList<>();
//        for (int i = 0; i < n; i++) {
//            Integer value = scanner.nextInt();
//            if (!value.equals(list.peekLast())) {
//                list.add(value);
//            }
//        }
//
//        list.forEach(System.out::println);
//    }
//}

//public class C {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
//        if (n == 0) {
//            return;
//        }
//
//        n--;
//        int lastOut = scanner.nextInt();
//        System.out.println(lastOut);
//
//        while (n-- > 0) {
//            int value = scanner.nextInt();
//            if (value != lastOut) {
//                lastOut = value;
//                System.out.println(lastOut);
//            }
//        }
//    }
//}

//import java.util.*;
//
//public class C {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
//
//        Set<Integer> out = new TreeSet<>();
//        while (n-- > 0) {
//            out.add(scanner.nextInt());
//        }
//
//        out.forEach(System.out::println);
//    }
//}
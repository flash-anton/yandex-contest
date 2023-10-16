package contest.contest8458;

import java.io.*;

/**
 * <pre>
 * Подготовка к собеседованию в Яндекс
 * https://contest.yandex.ru/contest/8458/enter
 *
 * D. Генерация скобочных последовательностей
 *
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	20Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Дано целое число n. Требуется вывести все правильные скобочные последовательности длины 2*n,
 * упорядоченные лексикографически (см. https://ru.wikipedia.org/wiki/Лексикографический_порядок).
 * В задаче используются только круглые скобки.
 * Желательно получить решение, которое работает за время,
 * пропорциональное общему количеству правильных скобочных последовательностей в ответе,
 * и при этом использует объём памяти, пропорциональный n.
 *
 * Формат ввода
 * Единственная строка входного файла содержит целое число n, 0 ≤ n ≤ 11
 *
 * Формат вывода
 * Выходной файл содержит сгенерированные правильные скобочные последовательности, упорядоченные лексикографически.
 * </pre>
 */
public class D {
    public static void main(String[] args) throws IOException {
        try (InputStream reader = new BufferedInputStream(System.in);
             OutputStream writer = new BufferedOutputStream(System.out)) {
            Main(reader, writer);
        }
    }

    public static void Main(InputStream reader, OutputStream writer) throws IOException {
        byte[] arr = new byte[23];
        byte size = 0;
        while ((arr[size++] = (byte) reader.read()) != '\n') ;

        byte n = Byte.parseByte(new String(arr, 0, size - 1));

        f(arr, n, (byte) 0, (byte) 0, writer);
    }

    static void f(byte[] arr, byte n, byte opened, byte closed, OutputStream writer) throws IOException {
        byte index = (byte) (opened + closed);

        if (opened < n) {
            arr[index] = '(';
            f(arr, n, (byte) (opened + 1), closed, writer);
        }

        if (closed < opened) {
            arr[index] = ')';
            f(arr, n, opened, (byte) (closed + 1), writer);
        }

        if (closed == n) {
            arr[index] = '\n';
            writer.write(arr, 0, index + 1);
        }
    }
}

//public class D {
//    static BufferedReader br;
//    static BufferedWriter bw;
//    static byte n;
//    static char[] arr;
//
//    public static void main(String[] args) throws IOException {
//        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input.txt"));
//             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("output.txt"))) {
//            br = bufferedReader;
//            bw = bufferedWriter;
//            Main();
//        }
//    }
//
//    static void Main() throws IOException {
//        n = Byte.parseByte(br.readLine());
//        arr = new char[n * 2];
//        f(0, 0);
//    }
//
//    static void f(int opened, int closed) throws IOException {
//        int index = opened + closed;
//
//        if (opened < n) {
//            arr[index] = '(';
//            f(opened + 1, closed);
//        }
//
//        if (closed < opened) {
//            arr[index] = ')';
//            f(opened, closed + 1);
//        }
//
//        if (closed == n) {
//            bw.write(arr);
//            bw.write(System.lineSeparator());
//        }
//    }
//}

// OK
//public class D {
//    static BufferedReader br;
//    static BufferedWriter bw;
//
//    public static void main(String[] args) throws IOException {
//        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input.txt"));
//             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("output.txt"))) {
//            br = bufferedReader;
//            bw = bufferedWriter;
//            Main();
//        }
//    }
//
//    static void Main() throws IOException {
//        byte n = Byte.parseByte(br.readLine());
//        char[] arr = new char[n * 2];
//        f(arr, 0, 0);
//    }
//
//    static void f(char[] arr, int opened, int closed) throws IOException {
//        int n = arr.length / 2;
//        int index = opened + closed;
//
//        if (opened < n) {
//            arr[index] = '(';
//            f(arr, opened + 1, closed);
//        }
//
//        if (closed < opened) {
//            arr[index] = ')';
//            f(arr, opened, closed + 1);
//        }
//
//        if (closed == n) {
//            bw.write(arr);
//            bw.write(System.lineSeparator());
//        }
//    }
//}

// OK
//public class D {
//    public static byte n = 0;
//    public static char[] arr = new char[0];
//    public static BufferedReader br;
//    public static BufferedWriter bw;
//
//    public static void main(String[] args) throws IOException {
//        br = new BufferedReader(new FileReader("input.txt"));
//        bw = new BufferedWriter(new FileWriter("output.txt"));
//
//        n = Byte.parseByte(br.readLine());
//        arr = new char[n * 2];
//        sequenceGenerator(0);
//
//        bw.close();
//        br.close();
//    }
//
//    public static void sequenceGenerator(int length) throws IOException {
//        int opened = count(arr, length, '(');
//        int closed = length - opened;
//
//        if (opened < n) {
//            arr[length] = '(';
//            sequenceGenerator(length + 1);
//        }
//
//        if (closed < opened) {
//            arr[length] = ')';
//            sequenceGenerator(length + 1);
//        }
//
//        if (closed == n) {
//            bw.write(arr);
//            bw.write(System.lineSeparator());
//        }
//    }
//
//    public static int count(char[] arr, int length, char item) {
//        int count = 0;
//        for (int i = 0; i < length; i++) {
//            if (arr[i] == item) {
//                count++;
//            }
//        }
//        return count;
//    }
//}

// OK
//public class D {
//    public static byte n = 0;
//    public static char[] arr = new char[0];
//
//    public static void main(String[] args) {
//        n = new Scanner(System.in).nextByte();
//        arr = new char[n * 2];
//        sequenceGenerator(0);
//    }
//
//    public static void sequenceGenerator(int length) {
//        int opened = count(arr, length, '(');
//        int closed = length - opened;
//
//        if (opened < n) {
//            arr[length] = '(';
//            sequenceGenerator(length + 1);
//        }
//
//        if (closed < opened) {
//            arr[length] = ')';
//            sequenceGenerator(length + 1);
//        }
//
//        if (closed == n) {
//            System.out.println(arr);
//        }
//    }
//
//    public static int count(char[] arr, int length, char item) {
//        int count = 0;
//        for (int i = 0; i < length; i++) {
//            if (arr[i] == item) {
//                count++;
//            }
//        }
//        return count;
//    }
//}

//public class D {
//    public static byte n = 0;
//    public static BitSet bitSet = new BitSet();
//
//    public static void main(String[] args) {
//        try (Scanner scanner = new Scanner(System.in)) {
//            n = scanner.nextByte();
//        }
//        bitSet = new BitSet(n * 2);
//        f(new byte[3]);
//    }
//
//    public static void f(byte[] param) { // length | opened << 8 | closed << 16
//        if (param[1] < n) {
//            byte[] p = Arrays.copyOf(param, param.length);
//            bitSet.set(p[0]);
//            p[0]++;
//            p[1]++;
//            f(p);
//        }
//
//        if (param[2] < param[1]) {
//            byte[] p = Arrays.copyOf(param, param.length);
//            bitSet.clear(p[0]);
//            p[0]++;
//            p[2]++;
//            f(p);
//        }
//
//        if (param[2] == n) {
//            for (int i = 0; i < n*2; i++) {
//                System.out.print(bitSet.get(i) ? '(' : ')');
//            }
//            System.out.println();
//        }
//    }
//}

//public class D {
//    public static byte n = 0;
//    public static char[] arr = new char[0];
//
//    public static void main(String[] args) {
//        n = new Scanner(System.in).nextByte();
//        arr = new char[n * 2];
//        sequenceGenerator(0);
//    }
//
//    public static void sequenceGenerator(int length) {
//        int opened = count(arr, length, '(');
//        int closed = length - opened;
//
//        if (opened < n) {
//            arr[length] = '(';
//            sequenceGenerator(length + 1);
//        }
//
//        if (closed < opened) {
//            arr[length] = ')';
//            sequenceGenerator(length + 1);
//        }
//
//        if (closed == n) {
//            System.out.println(arr);
//        }
//    }
//
//    public static int count(char[] arr, int length, char item) {
//        return (int) IntStream.range(0, length).parallel().mapToObj(i -> arr[i]).filter(b -> b == item).count();
//    }
//}

//public class D {
//    public static int N = 0;
//
//    public static void main(String[] args) {
//        N = new Scanner(System.in).nextInt();
//        sequenceGenerator(new byte[N*2], 0, 0);
//    }
//
//    public static void sequenceGenerator(byte[] str, int len, int opened) {
//        int closed = len - opened;
//
//        if (opened < N) {
//            str[len] = '(';
//            sequenceGenerator(str, len+1, opened + 1);
//        }
//
//        if (closed < opened) {
//            str[len] = ')';
//            sequenceGenerator(str, len+1, opened);
//        }
//
//        if (closed == N) {
//            System.out.println(new String(str));
//        }
//    }
//}

//public class D {
//    public static int N = 0;
//
//    public static void main(String[] args) {
//        N = new Scanner(System.in).nextInt();
//        sequenceGenerator(new byte[0], 0);
//    }
//
//    public static void sequenceGenerator(byte[] str, int opened) {
//        int closed = str.length - opened;
//
//        if (opened < N) {
//            byte[] dst = add(str, (byte) '(');
//            sequenceGenerator(dst, opened + 1);
//        }
//
//        if (closed < opened) {
//            byte[] dst = add(str, (byte) ')');
//            sequenceGenerator(dst, opened);
//        }
//
//        if (closed == N) {
//            System.out.println(new String(str));
//        }
//    }
//
//    public static byte[] add(byte[] str, byte b) {
//        byte[] dst = new byte[str.length + 1];
//        System.arraycopy(str, 0, dst, 0, str.length);
//        dst[str.length] = b;
//        return dst;
//    }
//}

//public class D {
//    public static int N = 0;
//
//    public static void main(String[] args) {
//        N = new Scanner(System.in).nextInt();
//        sequenceGenerator(new StringBuilder(), 0);
//    }
//
//    public static void sequenceGenerator(StringBuilder sb, int opened) {
//        int closed = sb.length() - opened;
//        if (opened < N) {
//            sequenceGenerator(new StringBuilder(sb).append('('), opened + 1);
//        }
//        if (closed < opened) {
//            sequenceGenerator(new StringBuilder(sb).append(')'), opened);
//        }
//        if (closed == N) {
//            System.out.println(sb);
//        }
//    }
//}
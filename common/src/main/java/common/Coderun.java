package common;

import java.io.*;
import java.util.Arrays;

public class Coderun {
    public static void main(String[] args) throws IOException {
        try (InputStream reader = new BufferedInputStream(System.in);
             OutputStream writer = new BufferedOutputStream(System.out)) {
            Main(reader, writer);
        }
    }

    public static void Main(InputStream reader, OutputStream writer) throws IOException {
        byte[] number = readLongBytes(reader);
        writer.write(number);
    }

    private static byte[] readLongBytes(InputStream reader) throws IOException {
        byte[] buf = new byte[20]; // 20 == String.valueOf(Long.MIN_VALUE).length()
        int size = 0;

        int b;
        while ((b = reader.read()) != -1 && b != '\n' && b != ' ') {
            buf[size++] = (byte) b;
        }

        return Arrays.copyOf(buf, size);
    }
}

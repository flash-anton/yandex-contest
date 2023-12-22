package contest27844;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.function.BiConsumer;

@DisplayName("Алг 1.0 ДЗ 6 (27844) Бинарный поиск - B")
class BTest extends ContestChecker {
    private static final BiConsumer<InputStream, OutputStream> algorithm = (reader, writer) -> {
        try {
            B.alg(new BufferedReader(new InputStreamReader(reader)), new BufferedWriter(new OutputStreamWriter(writer)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    };

    @Override
    public BiConsumer<InputStream, OutputStream> getTaskAlgorithm() {
        return algorithm;
    }

    @Test
    void example1() {
        check("""
                5 5
                1 3 5 7 9\s
                2 4 8 1 6\s
                """, """
                1
                3
                7
                1
                5
                """);
    }

    @Test
    void example2() {
        check("""
                6 11
                1 1 4 4 8 120\s
                1 2 3 4 5 6 7 8 63 64 65\s
                """, """
                1
                1
                4
                4
                4
                4
                8
                8
                8
                8
                120
                """);
    }

    @Test
    void example3() {
        check("""
                10 10
                -5 1 1 3 5 5 8 12 13 16\s
                0 3 7 -17 23 11 0 11 15 7\s
                """, """
                1
                3
                8
                -5
                16
                12
                1
                12
                16
                8
                """);
    }
}
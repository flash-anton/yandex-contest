package contest27663;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.function.BiConsumer;

@DisplayName("Алг 1.0 ДЗ 3 (27663) Множества - J")
class JTest extends ContestChecker {
    private static final BiConsumer<InputStream, OutputStream> algorithm = (reader, writer) -> {
        try {
            J.alg(new BufferedReader(new InputStreamReader(reader)), new BufferedWriter(new OutputStreamWriter(writer)));
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
                2 1 5
                0 1
                -2 1
                -2 3
                0 3
                2 5
                """, """
                2
                1 5
                2 4
                """);
    }

    @Test
    void example2() {
        check("""
                1 1 1
                0 0
                """, """
                5
                -1 0
                0 -1
                0 0
                0 1
                1 0
                """);
    }

    @Test
    void example3() {
        check("""
                1 10 1
                0 0
                """, """
                5
                -1 0
                0 -1
                0 0
                0 1
                1 0
                """);
    }
}
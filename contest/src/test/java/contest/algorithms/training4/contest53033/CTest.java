package contest.algorithms.training4.contest53033;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.function.BiConsumer;

@DisplayName("Алг 4.0 Финал (53033) - C")
class CTest extends ContestChecker {
    private static final BiConsumer<InputStream, OutputStream> algorithm = (reader, writer) -> {
        try {
            C.alg(reader, new BufferedWriter(new OutputStreamWriter(writer)));
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
                3 3
                2 3 40 3000299
                1 3 4 3000056
                1 2 10 3000201
                """, """
                2
                """);
    }

    @Test
    void test2() {
        check("""
                1 0
                """, """
                10000000
                """);
    }

    @Test
    void test7() {
        check("""
                3 1
                1 2 1 3000100
                """, """
                0
                """);
    }
}
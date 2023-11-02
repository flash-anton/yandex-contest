package contest.algorithms.training1.contest27472;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.function.BiConsumer;

@DisplayName("Алг 1.0 ДЗ 2 (27472) Линейный поиск - B")
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
                -530
                -530
                -530
                -530
                -530
                -530
                -2000000000
                """, """
                CONSTANT
                """);
    }

    @Test
    void test1() {
        check("""
                -2000000000
                """, """
                RANDOM
                """);
    }

    @Test
    void test2() {
        check("""
                1
                -2000000000
                """, """
                RANDOM
                """);
    }

    @Test
    void test3() {
        check("""
                1
                1
                -2000000000
                """, """
                CONSTANT
                """);
    }
}
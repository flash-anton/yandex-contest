package contest.algorithms.training1.contest27794;

import common.ContestChecker;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.function.BiConsumer;

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
                3
                0 0
                2 2
                -2 2
                """, """
                1
                """);
    }

    @Test
    void example2() {
        check("""
                4
                0 0
                1 1
                1 0
                0 1
                """, """
                4
                """);
    }

    @Test
    void test1() {
        check("""
                5
                0 0
                0 2
                2 2
                2 0
                1 1
                """, """
                8
                """);
    }
}
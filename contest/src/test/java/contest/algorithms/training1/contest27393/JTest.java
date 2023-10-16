package contest.algorithms.training1.contest27393;

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
                1
                0
                0
                1
                3
                3
                """, """
                2 3 3
                """);
    }

    @Test
    void example2() {
        check("""
                1
                1
                2
                2
                1
                2
                """, """
                1 -1 1
                """);
    }

    @Test
    void example3() {
        check("""
                0
                2
                0
                4
                1
                2
                """, """
                4 0.5
                """);
    }

    @Test
    void test1() {
        check("""
                3
                0
                6
                0
                0
                0
                """, """
                3 0
                """);
    }
}
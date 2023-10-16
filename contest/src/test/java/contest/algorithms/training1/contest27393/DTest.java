package contest.algorithms.training1.contest27393;

import common.ContestChecker;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.function.BiConsumer;

class DTest extends ContestChecker {
    private static final BiConsumer<InputStream, OutputStream> algorithm = (reader, writer) -> {
        try {
            D.alg(new BufferedReader(new InputStreamReader(reader)), new BufferedWriter(new OutputStreamWriter(writer)));
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
                """, """
                0
                """);
    }

    @Test
    void example2() {
        check("""
                1
                2
                3
                """, """
                7
                """);
    }

    @Test
    void example3() {
        check("""
                1
                2
                -3
                """, """
                NO SOLUTION
                """);
    }

    @Test
    void example4() {
        check("""
                0
                4
                2
                """, """
                MANY SOLUTIONS
                """);
    }
}
package contest.algorithms.training1.contest27472;

import common.ContestChecker;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.function.BiConsumer;

class FTest extends ContestChecker {
    private static final BiConsumer<InputStream, OutputStream> algorithm = (reader, writer) -> {
        try {
            F.alg(new BufferedReader(new InputStreamReader(reader)), new BufferedWriter(new OutputStreamWriter(writer)));
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
                9
                1 2 3 4 5 4 3 2 1
                """, """
                0
                """);
    }

    @Test
    void example2() {
        check("""
                5
                1 2 1 2 2
                """, """
                3
                1 2 1
                """);
    }

    @Test
    void example3() {
        check("""
                5
                1 2 3 4 5
                """, """
                4
                4 3 2 1
                """);
    }

    @Test
    void test1() {
        check("""
                1
                1
                """, """
                0
                """);
    }
}
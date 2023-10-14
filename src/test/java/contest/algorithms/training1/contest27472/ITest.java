package contest.algorithms.training1.contest27472;

import common.ContestChecker;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.function.BiConsumer;

class ITest extends ContestChecker {
    private static final BiConsumer<InputStream, OutputStream> algorithm = (reader, writer) -> {
        try {
            I.alg(new BufferedReader(new InputStreamReader(reader)), new BufferedWriter(new OutputStreamWriter(writer)));
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
                3 2 2
                1 1
                2 2
                """, """
                * 2
                2 *
                1 1
                """);
    }

    @Test
    void example2() {
        check("""
                2 2 0
                """, """
                0 0
                0 0
                """);
    }

    @Test
    void example3() {
        check("""
                4 4 4
                1 3
                2 1
                4 2
                4 4
                """, """
                1 2 * 1
                * 2 1 1
                2 2 2 1
                1 * 2 *
                """);
    }
}
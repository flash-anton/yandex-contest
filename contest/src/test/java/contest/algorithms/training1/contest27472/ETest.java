package contest.algorithms.training1.contest27472;

import common.ContestChecker;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.function.BiConsumer;

class ETest extends ContestChecker {
    private static final BiConsumer<InputStream, OutputStream> algorithm = (reader, writer) -> {
        try {
            E.alg(new BufferedReader(new InputStreamReader(reader)), new BufferedWriter(new OutputStreamWriter(writer)));
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
                7
                10 20 15 10 30 5 1
                """, """
                6
                """);
    }

    @Test
    void example2() {
        check("""
                3
                15 15 10
                """, """
                1
                """);
    }

    @Test
    void example3() {
        check("""
                3
                10 15 20
                """, """
                0
                """);
    }

    @Test
    void example4() {
        check("""
                3
                10 15 10
                """, """
                0
                """);
    }
}
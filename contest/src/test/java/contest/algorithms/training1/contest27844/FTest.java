package contest.algorithms.training1.contest27844;

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
                4 1 1
                """, """
                3
                """);
    }

    @Test
    void example2() {
        check("""
                5 1 2
                """, """
                4
                """);
    }

    @Test
    void test4() {
        check("""
                4 3 7
                """, """
                10
                """);
    }
}
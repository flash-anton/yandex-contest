package contest.algorithms.training1.contest27794;

import common.ContestChecker;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.function.BiConsumer;

class HTest extends ContestChecker {
    private static final BiConsumer<InputStream, OutputStream> algorithm = (reader, writer) -> {
        try {
            H.alg(new BufferedReader(new InputStreamReader(reader)), new BufferedWriter(new OutputStreamWriter(writer)));
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
                3 1
                abb
                """, """
                2 1
                """);
    }

    @Test
    void example2() {
        check("""
                5 2
                ababa
                """, """
                4 1
                """);
    }

    @Test
    void test1() {
        check("""
                5 1
                aaaaa
                """, """
                1 1
                """);
    }

    @Test
    void test2() {
        check("""
                1 1
                a
                """, """
                1 1
                """);
    }
}
package coderun.easy;

import common.ContestChecker;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.function.BiConsumer;

class S8Test extends ContestChecker {
    private static final BiConsumer<InputStream, OutputStream> algorithm = (reader, writer) -> {
        try {
            S8.alg(new BufferedReader(new InputStreamReader(reader)), new BufferedWriter(new OutputStreamWriter(writer)));
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
                6 4
                3 1
                1 2
                5 4
                2 3
                """, """
                3
                3
                1 2 3\s
                2
                4 5\s
                1
                6\s
                """);
    }

    @Test
    void example2() {
        check("""
                6 4
                4 2
                1 4
                6 4
                3 6
                """, """
                2
                5
                1 2 3 4 6\s
                1
                5\s
                """);
    }
}
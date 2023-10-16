package contest.algorithms.training1.contest27663;

import common.ContestChecker;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.function.BiConsumer;

class CTest extends ContestChecker {
    private static final BiConsumer<InputStream, OutputStream> algorithm = (reader, writer) -> {
        try {
            C.alg(new BufferedReader(new InputStreamReader(reader)), new BufferedWriter(new OutputStreamWriter(writer)));
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
                4 3
                0
                1
                10
                9
                1
                3
                0
                """, """
                2
                0 1
                2
                9 10
                1
                3
                """);
    }

    @Test
    void example2() {
        check("""
                2 2
                1
                2
                2
                3
                """, """
                1
                2
                1
                1
                1
                3
                """);
    }

    @Test
    void example3() {
        check("""
                0 0
                """, """
                0
                                
                0
                                
                0
                                
                """);
    }
}
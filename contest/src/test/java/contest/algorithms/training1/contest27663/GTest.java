package contest.algorithms.training1.contest27663;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.function.BiConsumer;

@DisplayName("Алг 1.0 ДЗ 3 (27663) Множества - G")
class GTest extends ContestChecker {
    private static final BiConsumer<InputStream, OutputStream> algorithm = (reader, writer) -> {
        try {
            G.alg(new BufferedReader(new InputStreamReader(reader)), new BufferedWriter(new OutputStreamWriter(writer)));
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
                2 0
                0 2
                2 2
                """, """
                2
                """);
    }

    @Test
    void example2() {
        check("""
                5
                0 4
                1 3
                2 2
                3 1
                4 0
                """, """
                5
                """);
    }

    @Test
    void example3() {
        check("""
                10
                9 1
                8 1
                7 2
                6 2
                5 3
                4 4
                3 6
                2 7
                1 9
                0 8
                                
                """, """
                4
                """);
    }
}
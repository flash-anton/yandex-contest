package contest.algorithms.training1.contest27663;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.function.BiConsumer;

@DisplayName("Алг 1.0 ДЗ 3 (27663) Множества - A")
class ATest extends ContestChecker {
    private static final BiConsumer<InputStream, OutputStream> algorithm = (reader, writer) -> {
        try {
            A.alg(new BufferedReader(new InputStreamReader(reader)), new BufferedWriter(new OutputStreamWriter(writer)));
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
                1 2 3 2 1
                """, """
                3
                """);
    }

    @Test
    void example2() {
        check("""
                1 2 3 4 5 6 7 8 9 10
                """, """
                10
                """);
    }

    @Test
    void example3() {
        check("""
                1 2 3 4 5 1 2 1 2 7 3
                """, """
                6
                """);
    }
}
package contest.algorithms.training1.contest27663;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.function.BiConsumer;

@DisplayName("Алг 1.0 ДЗ 3 (27663) Множества - E")
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
                1 2 3
                1123
                """, """
                0
                """);
    }

    @Test
    void example2() {
        check("""
                1 2 3
                1001
                """, """
                1
                """);
    }

    @Test
    void example3() {
        check("""
                5 7 3
                123
                """, """
                2
                """);
    }
}
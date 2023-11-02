package contest.algorithms.training1.contest27472;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.function.BiConsumer;

@DisplayName("Алг 1.0 ДЗ 2 (27472) Линейный поиск - D")
class DTest extends ContestChecker {
    private static final BiConsumer<InputStream, OutputStream> algorithm = (reader, writer) -> {
        try {
            D.alg(new BufferedReader(new InputStreamReader(reader)), new BufferedWriter(new OutputStreamWriter(writer)));
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
                1 2 3 4 5
                """, """
                0
                """);
    }

    @Test
    void example2() {
        check("""
                5 4 3 2 1
                """, """
                0
                """);
    }

    @Test
    void example3() {
        check("""
                1 5 1 5 1
                """, """
                2
                """);
    }
}
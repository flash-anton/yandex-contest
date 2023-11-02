package contest.algorithms.training1.contest27844;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.function.BiConsumer;

@DisplayName("Алг 1.0 ДЗ 6 (27844) Бинарный поиск - D")
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
                1 1 1 1 1
                """, """
                0
                """);
    }

    @Test
    void example2() {
        check("""
                1 1 1 3 3
                """, """
                1
                """);
    }

    @Test
    void example3() {
        check("""
                11 3 2 21 25
                """, """
                2
                """);
    }

    @Test
    void test27() {
        check("""
                1000000000 1000000000 1000000000 1000000000000000000 1000000000000000000
                """, """
                15810776602472
                """);
    }
}
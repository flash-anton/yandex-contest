package contest27883;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.function.BiConsumer;

@DisplayName("Алг 1.0 ДЗ 7 (27883) - G")
class GTest extends ContestChecker {
    private static final BiConsumer<InputStream, OutputStream> algorithm = (reader, writer) -> {
        try {
            G.alg(reader, new BufferedWriter(new OutputStreamWriter(writer)));
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
                1 2
                2 1 1
                1 1 2
                """, """
                1
                0 1\s
                """);
    }

    @Test
    void example2() {
        check("""
                2 2
                1 1 1
                1 1 1
                """, """
                1
                1 1\s
                """);
    }

    @Test
    void example3() {
        check("""
                3 2
                2 2 5
                1 1 10
                """, """
                4
                2 1\s
                """);
    }

    @Test
    void test1() {
        check("""
                0 2
                2 2 5
                1 1 10
                """, """
                0
                0 0\s
                """);
    }
}
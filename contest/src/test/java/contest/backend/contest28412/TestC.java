package contest.backend.contest28412;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.function.BiConsumer;

@DisplayName("28412-C")
public class TestC extends ContestChecker {
    @Override
    public BiConsumer<InputStream, OutputStream> getTaskAlgorithm() {
        return (reader, writer) -> {
            try {
                C.Main(reader, writer);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    @Test
    void example1() {
        check("""
                4 2
                1 2 3 4
                """, """
                3 2 2 3
                """);
    }

    @Test
    void example2() {
        check("""
                5 3
                3 2 5 1 2
                """, """
                4 2 8 4 2
                """);
    }

    @Test
    void example3() {
        check("""
                6 2
                3 2 1 101 102 103
                """, """
                3 2 3 3 2 3
                """);
    }

    @Test
    void test1() {
        check("""
                9 2
                2147483647 3 2 1 2147483647 101 102 103 2147483647
                """, """
                0 3 2 3 0 3 2 3 0
                """);
    }

    @Test
    void test3() {
        check("""
                4 2
                4 3 2 1
                """, """
                3 2 2 3
                """);
    }
}

package contest.backend.contest28412;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.function.BiConsumer;

@DisplayName("28412-A")
public class TestA extends ContestChecker {
    @Override
    public BiConsumer<InputStream, OutputStream> getTaskAlgorithm() {
        return (reader, writer) -> {
            try {
                A.Main(reader, writer);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    @Test
    void example1() {
        check("""
                2
                1 2
                """, """
                1
                """);
    }

    @Test
    void example2() {
        check("""
                5
                1 1 5 5 5
                """, """
                4
                """);
    }

    @Test
    void example3() {
        check("""
                3
                3 2 1
                """, """
                -1
                """);
    }

    @Test
    void test1() {
        check("""
                5
                1 2 3 4 5
                """, """
                4
                """);
    }
}

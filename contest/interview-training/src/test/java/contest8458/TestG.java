package contest8458;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.function.BiConsumer;

@DisplayName("8458-G")
public class TestG extends ContestChecker {
    @Override
    public BiConsumer<InputStream, OutputStream> getTaskAlgorithm() {
        return (reader, writer) -> {
            try {
                G.Main(reader, writer);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    @Test
    void example1() {
        check("""
                7
                0 0
                0 2
                2 2
                0 -2
                2 -2
                2 -1
                2 1
                2
                1 3
                """, """
                2
                """);
    }

    @Test
    void example2() {
        check("""
                4
                0 0
                1 0
                0 1
                1 1
                2
                1 4
                """, """
                1
                """);
    }

    @Test
    void example3() {
        check("""
                4
                0 0
                2 0
                0 2
                2 2
                1
                1 4
                """, """
                -1
                """);
    }
}

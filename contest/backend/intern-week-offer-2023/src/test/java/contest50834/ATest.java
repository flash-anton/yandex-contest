package contest50834;

import common.ContestChecker;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.function.BiConsumer;

class ATest extends ContestChecker {
    private static final BiConsumer<InputStream, OutputStream> algorithm = (reader, writer) -> {
        try {
            new A(reader, writer).execute();
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
                1
                3
                1
                10
                """, """
                2
                """);
    }

    @Test
    void example2() {
        check("""
                2
                4
                9
                10
                """, """
                10
                """);
    }

    @Test
    void example3() {
        check("""
                3
                8
                9
                10
                """, """
                19
                """);
    }
}
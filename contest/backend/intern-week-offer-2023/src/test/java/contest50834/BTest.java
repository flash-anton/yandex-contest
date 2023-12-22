package contest50834;

import common.ContestChecker;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.function.BiConsumer;

class BTest extends ContestChecker {
    private static final BiConsumer<InputStream, OutputStream> algorithm = (reader, writer) -> {
        try {
            new B(reader, writer).execute();
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
                2 0
                15 19
                """, """
                4
                """);
    }

    @Test
    void example2() {
        check("""
                7 2
                1 11 6 41 15 13 14
                """, """
                9
                """);
    }
}
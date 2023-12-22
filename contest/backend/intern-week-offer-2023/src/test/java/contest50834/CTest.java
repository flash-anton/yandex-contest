package contest50834;

import common.ContestChecker;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.function.BiConsumer;

class CTest extends ContestChecker {
    private static final BiConsumer<InputStream, OutputStream> algorithm = (reader, writer) -> {
        try {
            new C(reader, writer).execute();
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
                3
                0
                0
                1
                """, """
                3
                """);
    }

    @Test
    void example2() {
        check("""
                8
                0
                1
                2
                0
                4
                5
                6
                4
                """, """
                7
                """);
    }
}
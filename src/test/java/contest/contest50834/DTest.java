package contest.contest50834;

import common.ContestChecker;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.function.BiConsumer;

class DTest extends ContestChecker {
    private static final BiConsumer<InputStream, OutputStream> algorithm = (reader, writer) -> {
        try {
            new D(reader, writer).execute();
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
                5 4 3
                1 2
                2 3
                2 1
                6 2
                """, """
                5
                """);
    }

    @Test
    void example2() {
        check("""
                2 4 2
                1 1
                1 2
                3 1
                4 2
                """, """
                5
                """);
    }
}
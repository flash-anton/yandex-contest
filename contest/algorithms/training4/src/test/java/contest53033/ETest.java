package contest53033;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.function.BiConsumer;

@DisplayName("Алг 4.0 Финал (53033) - E")
class ETest extends ContestChecker {
    private static final BiConsumer<InputStream, OutputStream> algorithm = (reader, writer) -> {
        try {
            E.alg(reader, new BufferedWriter(new OutputStreamWriter(writer)));
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
                4 4 0
                """, """
                2
                0 0 0 4 4 0\s
                0 4 4 0 4 4\s
                """);
    }

    @Test
    void example2() {
        check("""
                2 3 1
                1 1 2 2 1 2
                """, """
                5
                0 0 2 0 2 2\s
                0 1 0 3 2 3\s
                0 1 1 1 1 2\s
                0 0 0 1 1 1\s
                1 2 2 2 2 3\s
                """);
    }
}
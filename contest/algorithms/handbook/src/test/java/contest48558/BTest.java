package contest48558;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.function.BiConsumer;

@DisplayName("Alg handbook 3.3 (48558) - B")
class BTest extends ContestChecker {
    private static final BiConsumer<InputStream, OutputStream> algorithm = (reader, writer) -> {
        try {
            B.alg(reader, new BufferedWriter(new OutputStreamWriter(writer)));
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
                4 4
                """, """
                Loose
                """);
    }

    @Test
    void example2() {
        check("""
                17 72
                """, """
                Win
                """);
    }

    @Test
    void example3() {
        check("""
                7 5
                """, """
                Win
                """);
    }
}
package contest48557;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.function.BiConsumer;

@DisplayName("Alg handbook 3.2 (48557) - A")
class ATest extends ContestChecker {
    private static final BiConsumer<InputStream, OutputStream> algorithm = (reader, writer) -> {
        try {
            A.alg(reader, new BufferedWriter(new OutputStreamWriter(writer)));
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
                1 3
                2 3
                4 5
                """, """
                2
                """);
    }

    @Test
    void example2() {
        check("""
                5
                1 2
                2 3
                4 5
                4 5
                5 6
                """, """
                2
                """);
    }

    @Test
    void example3() {
        check("""
                2
                1 50
                49 50
                """, """
                1
                """);
    }
}
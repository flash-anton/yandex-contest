package contest36783;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.function.BiConsumer;

@DisplayName("Interview training 36783 - O")
class OTest extends ContestChecker {
    private static final BiConsumer<InputStream, OutputStream> algorithm = (reader, writer) -> {
        try {
            O.alg(reader, new BufferedWriter(new OutputStreamWriter(writer)));
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
                5 3
                1 1
                3 3
                8 10
                4 1
                1 2
                1
                """, """
                7
                """);
    }

    @Test
    void example2() {
        check("""
                2 1
                1 10
                0 20
                1
                """, """
                21
                """);
    }

    @Test
    void example3() {
        check("""
                2 2
                2 2
                3 3
                1
                """, """
                1
                """);
    }
}
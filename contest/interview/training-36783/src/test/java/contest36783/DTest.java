package contest36783;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.function.BiConsumer;

@DisplayName("Interview training 36783 - D")
class DTest extends ContestChecker {
    private static final BiConsumer<InputStream, OutputStream> algorithm = (reader, writer) -> {
        try {
            D.alg(reader, new BufferedWriter(new OutputStreamWriter(writer)));
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
                2 3
                10 8 5
                10 5 4
                """, """
                4
                """);
    }

    @Test
    void example2() {
        check("""
                2 2
                1 1
                1 1
                """, """
                1
                """);
    }

    @Test
    void example3() {
        check("""
                2 2
                10 9
                9 11
                """, """
                2
                """);
    }
}
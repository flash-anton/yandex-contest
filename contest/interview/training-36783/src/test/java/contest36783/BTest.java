package contest36783;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.function.BiConsumer;

@DisplayName("Interview training 36783 - B")
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
                7
                3
                5 8 2 1 3 4 11
                """, """
                24
                """);
    }

    @Test
    void example2() {
        check("""
                5
                5
                1 2 3 4 5
                """, """
                15
                """);
    }

    @Test
    void example3() {
        check("""
                7
                4
                1 1 9 2 2 2 6
                """, """
                17
                """);
    }
}
package contest27883;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.function.BiConsumer;

@DisplayName("Алг 1.0 ДЗ 7 (27883) - C")
class CTest extends ContestChecker {
    private static final BiConsumer<InputStream, OutputStream> algorithm = (reader, writer) -> {
        try {
            C.alg(reader, new BufferedWriter(new OutputStreamWriter(writer)));
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
                4 1
                11 1 12 2
                """, """
                2
                1 1 2 2\s
                """);
    }

    @Test
    void example2() {
        check("""
                4 0
                11 1 12 2
                """, """
                1
                1 1 1 1\s
                """);
    }

    @Test
    void example4() {
        check("""
                10 3
                12 13 17 0 5 14 6 7 16 4
                """, """
                4
                2 3 2 1 3 4 4 1 1 2\s
                """);
    }
}
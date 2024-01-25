package contest27883;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.function.BiConsumer;

@DisplayName("Алг 1.0 ДЗ 7 (27883) - E")
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
                3
                1 0 23 0
                12 0 12 0
                22 0 2 0
                """, """
                120
                """);
    }

    @Test
    void example2() {
        check("""
                2
                9 30 14 0
                14 15 21 0
                """, """
                0
                """);
    }

    @Test
    void example3() {
        check("""
                2
                14 00 18 00
                10 00 14 01
                """, """
                1
                """);
    }
}
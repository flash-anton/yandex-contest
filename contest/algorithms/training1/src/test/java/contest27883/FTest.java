package contest27883;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.function.BiConsumer;

@DisplayName("Алг 1.0 ДЗ 7 (27883) - F")
class FTest extends ContestChecker {
    private static final BiConsumer<InputStream, OutputStream> algorithm = (reader, writer) -> {
        try {
            F.alg(reader, new BufferedWriter(new OutputStreamWriter(writer)));
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
                2 5 1988 13 11 2005
                1 1 1 1 1 30
                1 1 1910 1 1 1990
                """, """
                2\s
                3\s
                """);
    }

    @Test
    void example2() {
        check("""
                3
                2 5 1968 13 11 2005
                1 1 1 1 1 30
                1 1 1910 1 1 1990
                """, """
                2\s
                1 3\s
                """);
    }

    @Test
    void example3() {
        check("""
                3
                2 5 1988 13 11 2005
                1 1 1 1 1 10
                2 1 1910 1 1 1928
                """, """
                0
                """);
    }
}
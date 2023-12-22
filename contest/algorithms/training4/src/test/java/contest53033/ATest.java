package contest53033;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.function.BiConsumer;

@DisplayName("Алг 4.0 Финал (53033) - A")
class ATest extends ContestChecker {
    private static final BiConsumer<InputStream, OutputStream> algorithm = (reader, writer) -> {
        try {
            A.alg(new BufferedReader(new InputStreamReader(reader)), new BufferedWriter(new OutputStreamWriter(writer)));
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
                1
                """, """
                1
                """);
    }

    @Test
    void example2() {
        check("""
                2
                """, """
                4
                """);
    }

    @Test
    void example3() {
        check("""
                4
                """, """
                9
                """);
    }

    @Test
    void test1() {
        check("""
                3
                """, """
                8
                """);
    }
}
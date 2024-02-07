package contest48568;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.function.BiConsumer;

@DisplayName("Alg handbook 3.4 (48568) - A")
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
    void test1() {
        check("""
                2
                """, """
                3
                1 2
                1 3
                2 3
                """);
    }

    @Test
    void example1() {
        check("""
                3
                """, """
                7
                1 3
                1 2
                3 2
                1 3
                2 1
                2 3
                1 3
                """);
    }

    @Test
    void example2() {
        check("""
                4
                """, """
                15
                1 2
                1 3
                2 3
                1 2
                3 1
                3 2
                1 2
                1 3
                2 3
                2 1
                3 1
                2 3
                1 2
                1 3
                2 3
                """);
    }

    @Test
    void example3() {
        check("""
                5
                """, """
                31
                1 3
                1 2
                3 2
                1 3
                2 1
                2 3
                1 3
                1 2
                3 2
                3 1
                2 1
                3 2
                1 3
                1 2
                3 2
                1 3
                2 1
                2 3
                1 3
                2 1
                3 2
                3 1
                2 1
                2 3
                1 3
                1 2
                3 2
                1 3
                2 1
                2 3
                1 3
                """);
    }
}
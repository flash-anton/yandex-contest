package contest57974;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.function.BiConsumer;

@DisplayName("Intern 2024-02-06 job fair (57974) - B")
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
                5 10 10
                10 2 3 4 5
                """, """
                22
                """);
    }

    @Test
    void example2() {
        check("""
                10 10 10
                1 2 3 4 5 6 7 8 9 10
                """, """
                0
                """);
    }

    @Test
    void example3() {
        check("""
                2 1 1
                1000000 1
                """, """
                999998
                """);
    }
}
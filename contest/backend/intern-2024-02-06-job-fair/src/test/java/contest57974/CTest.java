package contest57974;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.function.BiConsumer;

@DisplayName("Intern 2024-02-06 job fair (57974) - C")
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
                5
                1 2 3 4 5
                """, """
                3/1
                """);
    }

    @Test
    void example2() {
        check("""
                3
                3 1 2
                """, """
                5/3
                """);
    }

    @Test
    void example3() {
        check("""
                7
                7 4 1 2 3 6 5
                """, """
                31/3
                """);
    }
}
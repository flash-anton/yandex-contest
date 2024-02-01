package contest57974;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.function.BiConsumer;

@DisplayName("Intern 2024-02-06 job fair (57974) - D")
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
                3 2
                1 1 1
                """, """
                0 1 1
                """);
    }

    @Test
    void example2() {
        check("""
                5 1
                6 2 3 4 5
                """, """
                1 0 0 0 0
                """);
    }

    @Test
    void example3() {
        check("""
                3 40
                100 12 23
                """, """
                30 3 7
                """);
    }

    @Test
    void example4() {
        check("""
                6 21
                1 2 3 4 5 6
                """, """
                1 2 3 4 5 6
                """);
    }

    @Test
    void example5() {
        check("""
                1 13
                17
                """, """
                13
                """);
    }
}
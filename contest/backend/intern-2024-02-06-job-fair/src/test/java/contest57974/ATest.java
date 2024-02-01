package contest57974;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.function.BiConsumer;

@DisplayName("Intern 2024-02-06 job fair (57974) - A")
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
    void example1() {
        check("""
                ....****
                ....****
                ....****
                ....****
                ****....
                ****....
                ****....
                ****....
                """, """
                48
                """);
    }

    @Test
    void example2() {
        check("""
                ********
                ********
                ********
                ********
                ********
                ********
                ********
                ********
                """, """
                0
                """);
    }

    @Test
    void example3() {
        check("""
                ********
                ********
                ********
                ********
                ********
                ********
                ******..
                ........
                """, """
                1
                """);
    }
}
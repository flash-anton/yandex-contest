package contest27883;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.function.BiConsumer;

@DisplayName("Алг 1.0 ДЗ 7 (27883) - H")
class HTest extends ContestChecker {
    private static final BiConsumer<InputStream, OutputStream> algorithm = (reader, writer) -> {
        try {
            H.alg(reader, new BufferedWriter(new OutputStreamWriter(writer)));
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
                2
                3 0 3000 2500 7000 2700 10000
                2 0 3000 2700 10000
                """, """
                Wrong Answer
                Accepted
                """);
    }
}
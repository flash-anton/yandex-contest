package contest.backend.contest51022;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.function.BiConsumer;

@DisplayName("Стаж бэк июл-дек 2023 (51022) - A")
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
                2
                1 2
                2
                1 3
                3
                1 2 3
                """, """
                4
                """);
    }

    @Test
    void example2() {
        check("""
                3
                1
                5
                2
                1 2
                3
                5 1 2
                """, """
                1
                """);
    }
}
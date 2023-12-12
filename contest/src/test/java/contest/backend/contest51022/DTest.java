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
                5
                A B B A B
                0 1 1 2 3 4 4 5 5 3 2 0
                """, """
                0 0 0 2 0
                """);
    }

    @Test
    void example2() {
        check("""
                4
                A B A A
                0 1 2 3 3 4 4 2 1 0
                """, """
                0 1 1 1
                """);
    }
}
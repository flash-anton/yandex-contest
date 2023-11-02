package contest.algorithms.training1.contest27794;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.function.BiConsumer;

@DisplayName("Алг 1.0 ДЗ 5 (27794) Префиксные суммы и два указателя - B")
class BTest extends ContestChecker {
    private static final BiConsumer<InputStream, OutputStream> algorithm = (reader, writer) -> {
        try {
            B.alg(new BufferedReader(new InputStreamReader(reader)), new BufferedWriter(new OutputStreamWriter(writer)));
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
                5 17
                17 7 10 7 10
                """, """
                4
                """);
    }

    @Test
    void example2() {
        check("""
                5 10
                1 2 3 4 1
                """, """
                2
                """);
    }
}
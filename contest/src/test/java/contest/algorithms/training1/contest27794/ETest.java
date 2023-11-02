package contest.algorithms.training1.contest27794;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.function.BiConsumer;

@DisplayName("Алг 1.0 ДЗ 5 (27794) Префиксные суммы и два указателя - E")
class ETest extends ContestChecker {
    private static final BiConsumer<InputStream, OutputStream> algorithm = (reader, writer) -> {
        try {
            E.alg(new BufferedReader(new InputStreamReader(reader)), new BufferedWriter(new OutputStreamWriter(writer)));
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
                5 3
                1 2 1 3 2
                """, """
                2 4
                """);
    }

    @Test
    void example2() {
        check("""
                6 4
                2 4 2 3 3 1
                """, """
                2 6
                """);
    }
}
package contest.algorithms.training1.contest27794;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.function.BiConsumer;

@DisplayName("Алг 1.0 ДЗ 5 (27794) Префиксные суммы и два указателя - C")
class CTest extends ContestChecker {
    private static final BiConsumer<InputStream, OutputStream> algorithm = (reader, writer) -> {
        try {
            C.alg(new BufferedReader(new InputStreamReader(reader)), new BufferedWriter(new OutputStreamWriter(writer)));
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
                7
                2 1
                4 5
                7 4
                8 2
                9 6
                11 3
                15 3
                1
                2 6
                """, """
                4
                """);
    }

    @Test
    void example2() {
        check("""
                6
                1 1
                3 2
                5 6
                7 2
                10 4
                11 1
                3
                5 6
                1 4
                4 2
                """, """
                0
                5
                4
                """);
    }
}
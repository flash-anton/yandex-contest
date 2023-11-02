package contest.algorithms.training1.contest27844;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.function.BiConsumer;

@DisplayName("Алг 1.0 ДЗ 6 (27844) Бинарный поиск - A")
class ATest extends ContestChecker {
    private static final BiConsumer<InputStream, OutputStream> algorithm = (reader, writer) -> {
        try {
            A.alg(new BufferedReader(new InputStreamReader(reader)), new BufferedWriter(new OutputStreamWriter(writer)));
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
                10 10
                1 61 126 217 2876 6127 39162 98126 712687 1000000000
                100 6127 1 61 200 -10000 1 217 10000 1000000000
                """, """
                NO
                YES
                YES
                YES
                NO
                NO
                YES
                YES
                NO
                YES
                """);
    }

    @Test
    void example2() {
        check("""
                10 10
                -8 -6 -4 -4 -2 -1 0 2 3 3
                8 3 -3 -2 2 -1 2 9 -8 0
                """, """
                NO
                YES
                NO
                YES
                YES
                YES
                YES
                NO
                YES
                YES
                """);
    }

    @Test
    void example3() {
        check("""
                10 5
                1 2 3 4 5 6 7 8 9 10
                -2 0 4 9 12
                """, """
                NO
                NO
                YES
                YES
                NO
                """);
    }
}
package contest27472;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.function.BiConsumer;

@DisplayName("Алг 1.0 ДЗ 2 (27472) Линейный поиск - H")
class HTest extends ContestChecker {
    private static final BiConsumer<InputStream, OutputStream> algorithm = (reader, writer) -> {
        try {
            H.alg(new BufferedReader(new InputStreamReader(reader)), new BufferedWriter(new OutputStreamWriter(writer)));
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
                3 5 1 7 9 0 9 -3 10
                """, """
                10 9 9
                """);
    }

    @Test
    void example2() {
        check("""
                -5 -30000 -12
                """, """
                -5 -12 -30000
                """);
    }

    @Test
    void example3() {
        check("""
                1 2 3
                """, """
                3 2 1
                """);
    }

    @Test
    void test1() {
        check("""
                -5 -3 -2 -7 -8
                """, """
                -2 -3 -5
                """);
    }

    @Test
    void test16() {
        check("""
                -70 -68 -29 -45 -34 -88 -32 -38 -25 -95 -68 -96 -28 -45 -59 -95 -73 -8 -37 -49 -47 -9 -61 -91 -11 -90 -16 -25 -17 -4 -14 -38 -38 -85 -46 -16 -22 -63 -54 -14 -51 -4 -20 -44 -74 -78 -83 -91 -35 -43 -49 -89 -100 -27 -39 -95 -90 -90 -92 -48 -44 -4 -37 -13 -73 -28 -61 -32 -95 -43 -83 -43 -51 -19
                """, """
                -4 -4 -4
                """);
    }
}
package contest27844;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiConsumer;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Алг 1.0 ДЗ 6 (27844) Бинарный поиск - E")
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
                2
                0
                0
                """, """
                2
                """);
    }

    @Test
    void test1() {
        check("""
                1000000000000000
                1000000000000000
                0
                """, """
                1333333333333334
                """);
    }

    @Test
    void test12() {
        check("""
                0
                0
                3
                """, """
                0
                """);
    }

    @Test
    void test32() {
        check("""
                1000000000000000
                1000000000000000
                1000000000000000
                """, """
                1000000000000000
                """);
    }

    @Test
    void stressTest() {
        ThreadLocalRandom r = ThreadLocalRandom.current();
        long max = 1_000_000_000_000_001L;
        for (int i = 0; i < 100_000; i++) {
            long a = r.nextLong(max);
            long b = r.nextLong(max);
            long c = r.nextLong(max);
            a += (a + b + c > 0) ? 0 : 1;

            long expected = E.mathSolution(a, b, c);
            long actual = E.binarySearchSolution(a, b, c);

            assertEquals(expected, actual, String.format("\n%d\n%d\n%d\n", a, b, c));
        }
    }
}
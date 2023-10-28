package contest.algorithms.training1.contest27844;

import common.ContestChecker;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiConsumer;

import static org.junit.jupiter.api.Assertions.*;

class GTest extends ContestChecker {
    private static final BiConsumer<InputStream, OutputStream> algorithm = (reader, writer) -> {
        try {
            G.alg(new BufferedReader(new InputStreamReader(reader)), new BufferedWriter(new OutputStreamWriter(writer)));
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
                6
                7
                38
                """, """
                2
                """);
    }

    @Test
    void test1() {
        check("""
                6
                7
                21
                """, """
                0
                """);
    }

    @Test
    void test2() {
        check("""
                42
                42
                1762
                """, """
                20
                """);
    }

    @Test
    void test7() {
        check("""
                51
                51
                2600
                """, """
                25
                """);
    }

    @Test
    void stressTest() {
        ThreadLocalRandom r = ThreadLocalRandom.current();
        long nmMin = 3;
        long nmMax = 11;
        long tMin = 1;
        for (int i = 0; i < 100_000; i++) {
            long n = r.nextLong(nmMin, nmMax);
            long m = r.nextLong(nmMin, nmMax);
            long t = r.nextLong(tMin, n * m);

            long expected = G.slow(n, m, t);
            long actual = G.alg(n, m, t);

            assertEquals(expected, actual, String.format("\n%s\n%s\n%s\n", n, m, t));
        }
    }
}
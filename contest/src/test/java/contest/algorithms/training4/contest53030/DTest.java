package contest.algorithms.training4.contest53030;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.time.Duration;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiConsumer;

import static java.util.stream.Collectors.joining;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Алг 4.0 ДЗ 2 (53030) Хеши для строк - D")
class DTest extends ContestChecker {
    private static final BiConsumer<InputStream, OutputStream> algorithm = (reader, writer) -> {
        try {
            D.alg(new BufferedReader(new InputStreamReader(reader)), new BufferedWriter(new OutputStreamWriter(writer)));
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
                6 2
                1 1 2 2 1 1
                """, """
                3 5 6
                """);
    }

    @Test
    void test1() {
        check("""
                4 4
                1 1 1 2
                """, """
                3 4
                """);
    }

    @Test
    public void stressTest() {
        ThreadLocalRandom r = ThreadLocalRandom.current();
        for (int i = 0; i < 100_000; i++) {
            int N = r.nextInt(1, 11);
            int M = r.nextInt(1, 11);
            Integer[] m = r.ints(N, 1, M + 1).boxed().toArray(Integer[]::new);

            String expected = D.alg1(N, M, m);
            String actual = D.alg2(N, M, m);

            assertEquals(expected, actual, () -> String.format("\n%d %d\n%s\n", N, M,
                    Arrays.stream(m).map(String::valueOf).collect(joining(" "))));
        }
    }

    @Test
    void testTL() {
        ThreadLocalRandom r = ThreadLocalRandom.current();
        for (int i = 0; i < 1; i++) {
            int N = 1_000_000;
            int M = 1_000_000;
            Integer[] m = r.ints(N, 1, M + 1).boxed().toArray(Integer[]::new);

            assertAll(
                    () -> assertTimeout(Duration.ofSeconds(5), () -> D.alg2(N, M, m), "alg2() is too slow")
            );
        }
    }

    @Test
    void testCompareTL_OneColorInBeginning() {
        ThreadLocalRandom r = ThreadLocalRandom.current();
        for (int i = 0; i < 3; i++) {
            int N = 40_000;
            int M = 1_000_000;
            Integer[] m = r.ints(N, 1, M + 1).boxed().toArray(Integer[]::new);
            Arrays.fill(m, 0, N / 2, 0);

            long t1 = duration(() -> D.alg1(N, M, m));
            long t2 = duration(() -> D.alg2(N, M, m));

            assertAll(
                    () -> assertTrue(t1 > t2, "duration alg1() < alg2(): " + t1 + " < " + t2)
            );
        }
    }

    @Test
    void testCompareTL_UniqColors() {
        ThreadLocalRandom r = ThreadLocalRandom.current();
        for (int i = 0; i < 3; i++) {
            int n = 1_000_000;
            int M = 1_000_000;
            Integer[] m = r.ints(n, 1, M + 1).distinct().boxed().toArray(Integer[]::new);
            int N = m.length;

            long t1 = duration(() -> D.alg1(N, M, m));
            long t2 = duration(() -> D.alg2(N, M, m));

            assertAll(
                    () -> assertTrue(t1 < t2, "duration alg1() > alg2(): " + t1 + " > " + t2)
            );
        }
    }

    static long duration(Runnable r) {
        long t = System.currentTimeMillis();
        r.run();
        return System.currentTimeMillis() - t;
    }
}
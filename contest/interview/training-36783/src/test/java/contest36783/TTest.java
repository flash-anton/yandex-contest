package contest36783;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiConsumer;

import static java.time.Duration.ofSeconds;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Interview training 36783 - T")
class TTest extends ContestChecker {
    private static final BiConsumer<InputStream, OutputStream> algorithm = (reader, writer) -> {
        try {
            T.alg(reader, new BufferedWriter(new OutputStreamWriter(writer)));
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
                1 8 3 1 4
                """, """
                12
                """);
    }

    @Test
    void example2() {
        check("""
                3
                4 8 1024
                """, """
                1032
                """);
    }

    @Test
    void example3() {
        check("""
                2
                10 10
                """, """
                0
                """);
    }

    @Test
    void test1() {
        check("""
                3
                1638609585 2087125130 374725011
                """, """
                2013195042
                """);
    }

    @Test
    public void stressTest() {
        ThreadLocalRandom r = ThreadLocalRandom.current();
        for (int i = 0; i < 100_000; i++) {
            int n = r.nextInt(1, 11);
            List<Integer> x = r.longs(n, 1, 0x80000000L)
                    .mapToObj(k -> (int) k).collect(toList());

            String expected = T.alg1(n, x);
            String actual = T.alg2(n, x);

            assertEquals(expected, actual, () -> "\n" + n + "\n" + x + "\n");
        }
    }

    @Test
    void testTL() {
        ThreadLocalRandom r = ThreadLocalRandom.current();
        for (int i = 0; i < 5; i++) {
            int n = 100_000;
            List<Integer> x = r.longs(n, 1, 0x80000000L)
                    .mapToObj(k -> (int) k).collect(toList());

            assertAll(
//                    () -> assertTimeout(ofSeconds(1), () -> T.alg1(n, x), "alg1() is too slow"),
                    () -> assertTimeout(ofSeconds(1), () -> T.alg2(n, x), "alg2() is too slow")
            );
        }
    }
}
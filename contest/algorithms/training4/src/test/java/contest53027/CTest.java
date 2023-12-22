package contest53027;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.*;
import java.time.Duration;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiConsumer;

import static org.junit.jupiter.api.Assertions.assertTimeout;

@DisplayName("Алг 4.0 ДЗ 0 (53027) Разминка - C")
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
                0 5 4 3
                """, """
                4.636476090008
                """);
    }

    @Test
    void example2() {
        check("""
                0 5 4 -3
                """, """
                10.000000000000
                """);
    }

    @Test
    void test1() {
        check("""
                0 0 0 0
                """, """
                0.000000000000
                """);
    }

    @ParameterizedTest
    @ValueSource(strings = {"0 0 3 -4", "-4 3 0 0"})
    void test2(String args) {
        check(args, "5.000000000000");
    }

    @ParameterizedTest
    @ValueSource(strings = {"4 3 4 -3", "-4 3 -4 -3", "3 4 -3 4", "3 -4 -3 -4"})
    void test3(String args) {
        check(args, String.format(Locale.ENGLISH, "%.12f", 2 * Math.atan2(3, 4) * 5));
    }

    @Test
    void testTL() {
        ThreadLocalRandom r = ThreadLocalRandom.current();
        for (int i = 0; i < 10_000; i++) {
            int[] a = r.ints(4, -1_000_000, 1_000_001).toArray();
            assertTimeout(Duration.ofSeconds(2), () -> C.alg(a[0], a[1], a[2], a[3]), "alg() is too slow");
        }
    }
}
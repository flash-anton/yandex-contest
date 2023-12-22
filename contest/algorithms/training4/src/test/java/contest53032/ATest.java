package contest53032;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiConsumer;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Алг 4.0 ДЗ 4 (53032) Перебор и методы его оптимизации - A")
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
                1
                """, """
                1
                """);
    }

    @Test
    void example2() {
        check("""
                2
                """, """
                12
                21
                """);
    }

    @Test
    void example3() {
        check("""
                3
                """, """
                123
                132
                213
                231
                312
                321
                """);
    }

    @Test
    public void stressTest() {
        ThreadLocalRandom r = ThreadLocalRandom.current();
        for (int i = 0; i < 10_000; i++) {
            int N = r.nextInt(1, 5);

            String expected = A.alg1(N);
            String actual2 = A.alg2(N);

            assertAll(
                    () -> assertEquals(expected, actual2)
            );
        }
    }

    @Test
    void testTL1() {
        for (int i = 0; i < 1; i++) {
            assertAll(
                    () -> assertTimeout(Duration.ofSeconds(2), () -> A.alg1(10), "alg1() is too slow")
            );
        }
    }

    @Test
    void testTL2() {
        for (int i = 0; i < 1; i++) {
            assertAll(
                    () -> assertTimeout(Duration.ofSeconds(2), () -> A.alg2(10), "alg2() is too slow")
            );
        }
    }
}
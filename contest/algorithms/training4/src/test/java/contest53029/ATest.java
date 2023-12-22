package contest53029;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.time.Duration;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Алг 4.0 ДЗ 1 (53029) Сортировки: быстрая, слиянием и поразрядная - A")
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
                5
                1 9 4 2 3
                3
                """, """
                2
                3
                """);
    }

    @Test
    void example2() {
        check("""
                0
                                
                0
                """, """
                0
                0
                """);
    }

    @Test
    void example3() {
        check("""
                1
                0
                0
                """, """
                0
                1
                """);
    }

    @Test
    public void stressTest() {
        ThreadLocalRandom r = ThreadLocalRandom.current();
        for (int i = 0; i < 100_000; i++) {
            int N = r.nextInt(0, 11);
            Integer[] a = r.ints(N, -1_000_000_000, 1_000_000_001).boxed().toArray(Integer[]::new);
            int x = r.nextInt(-1_000_000_000, 1_000_000_001);

            String m = String.format("\n%d\n%s\n%d", N, Arrays.stream(a).map(String::valueOf).collect(Collectors.joining(" ")), x);

            String expected = A.alg1(N, Arrays.copyOf(a, N), x);
            String actual2 = A.alg2(N, Arrays.copyOf(a, N), x);
            String actual3 = A.alg3(N, Arrays.copyOf(a, N), x);
            String actual4 = A.alg4(N, Arrays.copyOf(a, N), x);

            assertAll(
                    () -> assertEquals(expected, actual2, m),
                    () -> assertEquals(expected, actual3, m),
                    () -> assertEquals(expected, actual4, m)
            );
        }
    }

    @Test
    void testTL() {
        ThreadLocalRandom r = ThreadLocalRandom.current();
        for (int i = 0; i < 10; i++) {
            int N = 1_000_000;
            Integer[] a = r.ints(N, -1_000_000_000, 1_000_000_001).boxed().toArray(Integer[]::new);
            int x = r.nextInt(-1_000_000_000, 1_000_000_001);

            assertAll(
                    () -> assertTimeout(Duration.ofSeconds(2), () -> A.alg1(N, Arrays.copyOf(a, N), x), "alg1() is too slow"),
                    () -> assertTimeout(Duration.ofSeconds(2), () -> A.alg2(N, Arrays.copyOf(a, N), x), "alg2() is too slow"),
                    () -> assertTimeout(Duration.ofSeconds(2), () -> A.alg3(N, Arrays.copyOf(a, N), x), "alg3() is too slow"),
                    () -> assertTimeout(Duration.ofSeconds(2), () -> A.alg4(N, Arrays.copyOf(a, N), x), "alg4() is too slow")
            );
        }
    }
}
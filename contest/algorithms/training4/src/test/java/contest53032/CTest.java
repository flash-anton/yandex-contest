package contest53032;

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

@DisplayName("Алг 4.0 ДЗ 4 (53032) Перебор и методы его оптимизации - C")
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
                2
                0 1
                1 0
                """, """
                1
                2 1
                """);
    }

    @Test
    void example2() {
        check("""
                3
                0 1 2
                1 0 2
                2 2 0
                """, """
                4
                2 2 1
                """);
    }

    @Test
    void example3() {
        check("""
                4
                0 10 3 0
                10 0 7 2
                3 7 0 9
                0 2 9 0
                """, """
                26
                2 1 2 1
                """);
    }

    @Test
    void stressTest() {
        ThreadLocalRandom r = ThreadLocalRandom.current();
        for (int i = 0; i < 1; i++) {
            StringBuilder sb = new StringBuilder();

            int N = r.nextInt(1, 5);
            sb.append(N).append('\n');

            int[][] G = new int[N][N];
            for (int A = 0; A < N; A++) {
                for (int B = A + 1; B < N; B++) {
                    int weight = r.nextInt(950, 1001);
                    G[A][B] = weight;
                    G[B][A] = weight;
                }
                sb.append(Arrays.stream(G[A]).mapToObj(String::valueOf).collect(joining(" "))).append('\n');
            }

            String expected = C.alg1(N, G);
            String actual2 = C.alg2(N, G);

            assertAll(
                    () -> assertEquals(expected, actual2, sb.toString())
            );
        }
    }

    @Test
    void testTL1() {
        ThreadLocalRandom r = ThreadLocalRandom.current();
        for (int i = 0; i < 1; i++) {
            int N = 20;
            int[][] G = new int[N][N];
            for (int A = 0; A < N; A++) {
                for (int B = A + 1; B < N; B++) {
                    int weight = r.nextInt(950, 1001);
                    G[A][B] = weight;
                    G[B][A] = weight;
                }
            }

            assertAll(
                    () -> assertTimeout(Duration.ofSeconds(1), () -> C.alg1(N, G), "alg1() is too slow")
            );
        }
    }

    @Test
    void testTL2() {
        ThreadLocalRandom r = ThreadLocalRandom.current();
        for (int i = 0; i < 1; i++) {
            int N = 20;
            int[][] G = new int[N][N];
            for (int A = 0; A < N; A++) {
                for (int B = A + 1; B < N; B++) {
                    int weight = r.nextInt(950, 1001);
                    G[A][B] = weight;
                    G[B][A] = weight;
                }
            }

            assertAll(
                    () -> assertTimeout(Duration.ofSeconds(1), () -> C.alg2(N, G), "alg2() is too slow")
            );
        }
    }
}
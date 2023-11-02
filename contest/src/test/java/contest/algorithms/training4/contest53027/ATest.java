package contest.algorithms.training4.contest53027;

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

@DisplayName("Алг 4.0 ДЗ 0 (53027) Разминка - A")
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
                10 5
                1 1 1 2 2 2 3 3 3 10
                0 1
                0 3
                3 4
                7 9
                3 7
                """, """
                NOT FOUND
                2
                NOT FOUND
                10
                3
                """);
    }

    @Test
    void example2() {
        check("""
                4 2
                1 1 1 2
                0 2
                0 3
                """, """
                NOT FOUND
                2
                """);
    }

    @Test
    public void stressTest() {
        ThreadLocalRandom r = ThreadLocalRandom.current();
        for (int i = 0; i < 100_000; i++) {
            int N = r.nextInt(1, 11);
            int M = r.nextInt(1, 11);
            int[] a = r.ints(N, 0, 1001).toArray();
            int[][] req = new int[M][2];
            for (int j = 0; j < M; j++) {
                req[j][0] = r.nextInt(N);
                req[j][1] = r.nextInt(req[j][0], N);
            }

            String expected = A.alg2(a, req);
            String actual = A.alg3(a, req);

            assertEquals(expected, actual, () -> {
                StringBuilder sb = new StringBuilder("\n");
                sb.append(N).append(" ").append(M).append("\n");
                sb.append(Arrays.stream(a).mapToObj(String::valueOf).collect(Collectors.joining(" "))).append("\n");
                for (int[] re : req) {
                    sb.append(re[0]).append(" ").append(re[1]).append("\n");
                }
                return sb.toString();
            });
        }
    }

    @Test
    void testTL() {
        ThreadLocalRandom r = ThreadLocalRandom.current();
        for (int i = 0; i < 100; i++) {
            int N = 100;
            int M = 1000;
            int[] a = r.ints(N, 0, 1001).toArray();
            int[][] req = new int[M][2];
            for (int j = 0; j < M; j++) {
                req[j][0] = r.nextInt(N);
                req[j][1] = r.nextInt(req[j][0], N);
            }

            assertAll(
                    () -> assertTimeout(Duration.ofSeconds(1), () -> A.alg1(a, req), "alg1() is too slow"),
                    () -> assertTimeout(Duration.ofSeconds(1), () -> A.alg2(a, req), "alg2() is too slow"),
                    () -> assertTimeout(Duration.ofSeconds(1), () -> A.alg3(a, req), "alg3() is too slow")
            );
        }
    }
}
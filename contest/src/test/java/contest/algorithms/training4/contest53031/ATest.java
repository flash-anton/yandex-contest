package contest.algorithms.training4.contest53031;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Алг 4.0 ДЗ 3 (53031) Кратчайшие пути в графах - A")
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
                3 2 1
                0 1 1
                4 0 1
                2 1 0
                """, """
                3
                """);
    }

    @Test
    public void stressTest() {
        ThreadLocalRandom r = ThreadLocalRandom.current();
        for (int i = 0; i < 10_000; i++) {
            int N = r.nextInt(1, 5);
            int S = r.nextInt(1, N + 1);
            int F = r.nextInt(1, N + 1);
            StringBuilder msg = new StringBuilder("\n" + N + " " + S + " " + F + "\n");

            int edgeCount = 0;
            Map<Integer, Map<Integer, Integer>> edges = new HashMap<>(); // <from, <to, weight>>
            for (int j = 0; j < N; j++) {
                Map<Integer, Integer> e = new HashMap<>();
                int[] edgeWeights = r.ints(N, -1, 100).toArray();
                edgeWeights[j] = 0;
                for (int k = 0; k < N; k++) {
                    int edgeWeight = edgeWeights[k];
                    if (j != k && edgeWeight != -1) {
                        edgeCount++;
                        e.put(k + 1, edgeWeight);
                    }
                }
                edges.put(j + 1, e);

                msg.append(Arrays.stream(edgeWeights).boxed().map(String::valueOf).collect(Collectors.joining(" ")));
                msg.append("\n");
            }
            int ec = edgeCount;

            String expected = A.alg1(N, S, F, ec, edges);
            String actual2 = A.alg2(N, S, F, ec, edges);

            assertAll(
                    () -> assertEquals(expected, actual2, msg.toString())
            );
        }
    }

    @Test
    void testTL() {
        ThreadLocalRandom r = ThreadLocalRandom.current();
        for (int i = 0; i < 100; i++) {
            int N = 200;
            int S = 1;
            int F = 200;
            int edgeCount = 0;
            Map<Integer, Map<Integer, Integer>> edges = new HashMap<>(); // <from, <to, weight>>
            for (int j = 0; j < N; j++) {
                Map<Integer, Integer> e = new HashMap<>();
                int[] edgeWeights = r.ints(N, -1, 100).toArray();
                edgeWeights[j] = 0;
                for (int k = 0; k < N; k++) {
                    int edgeWeight = edgeWeights[k];
                    if (j != k && edgeWeight != -1) {
                        edgeCount++;
                        e.put(k + 1, edgeWeight);
                    }
                }
                edges.put(j + 1, e);
            }
            int ec = edgeCount;

            assertAll(
                    () -> assertTimeout(Duration.ofSeconds(1), () -> A.alg2(N, S, F, ec, edges), "alg2() is too slow")
            );
        }
    }
}
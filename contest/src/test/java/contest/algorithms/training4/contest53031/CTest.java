package contest.algorithms.training4.contest53031;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiConsumer;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Алг 4.0 ДЗ 3 (53031) Кратчайшие пути в графах - C")
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
                6 4
                1 2 7
                2 4 8
                4 5 1
                4 3 100
                3 1
                """, """
                115
                """);
    }

    @Test
    public void stressTest() {
        ThreadLocalRandom r = ThreadLocalRandom.current();
        for (int i = 0; i < 10_000; i++) {
            int N = r.nextInt(1, 5);
            int K = r.nextInt(0, (N * (N - 1) / 2) + 1);
            StringBuilder msg = new StringBuilder("\n" + N + " " + K + "\n");

            Map<Integer, Map<Integer, Long>> edges = new HashMap<>(); // <from, <to, weight>>
            int v = 0;
            while (v < K) {
                int a = r.nextInt(1, N);
                int b = r.nextInt(a + 1, N + 1);
                long l = r.nextInt(1, 1_000_001);
                Map<Integer, Long> e = edges.computeIfAbsent(a, key -> new HashMap<>());
                if (!e.containsKey(b)) {
                    e.put(b, l);
                    edges.computeIfAbsent(b, key -> new HashMap<>()).put(a, l);
                    v++;
                    msg.append(a).append(" ").append(b).append(" ").append(l).append("\n");
                }
            }

            int A = r.nextInt(N) + 1;
            int B = ((A - 1) + r.nextInt(0, N)) % N + 1;
            msg.append(A).append(" ").append(B);

            String expected = C.alg1(N, K, edges, A, B);
            String actual2 = C.alg2(N, K, edges, A, B);

            assertAll(
                    () -> assertEquals(expected, actual2, msg.toString())
            );
        }
    }

    @Test
    void testTL() {
        ThreadLocalRandom r = ThreadLocalRandom.current();
        for (int i = 0; i < 5; i++) {
            int N = 775;
            Map<Integer, Map<Integer, Long>> edges = new HashMap<>(); // <from, <to, weight>>
            int edgesCount = 0;
            for (int a = 1; a < N; a++) {
                for (int b = a + 1; b <= N; b++) {
                    long l = r.nextInt(1, 1_000_001);
                    edges.computeIfAbsent(a, key -> new HashMap<>()).put(b, l);
                    edges.computeIfAbsent(b, key -> new HashMap<>()).put(a, l);
                    edgesCount++;
                }
            }
            int K = edgesCount;

            int A = r.nextInt(N) + 1;
            int B = ((A - 1) + r.nextInt(0, N)) % N + 1;

            assertAll(
                    () -> assertTimeout(Duration.ofSeconds(5), () -> C.alg2(N, K, edges, A, B), "alg2() is too slow")
            );
        }
    }
}
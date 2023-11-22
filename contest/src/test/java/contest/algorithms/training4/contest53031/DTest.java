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

@DisplayName("Алг 4.0 ДЗ 3 (53031) Кратчайшие пути в графах - D")
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
                3
                1 3
                4
                1 0 2 5
                1 1 2 3
                2 3 3 5
                1 1 3 10
                """, """
                5
                """);
    }

    @Test
    public void stressTest() {
        ThreadLocalRandom r = ThreadLocalRandom.current();
        for (int i = 0; i < 10_000; i++) {
            int N = r.nextInt(1, 5);
            StringBuilder msg = new StringBuilder("\n" + N + "\n");

            int d = r.nextInt(N) + 1;
            int v = ((d - 1) + r.nextInt(0, N)) % N + 1;
            msg.append(d).append(" ").append(v).append("\n");

            int R = r.nextInt(0, (N * N) + 1);
            msg.append(R).append("\n");

            Map<Integer, Map<Integer, D.Races>> edges = new HashMap<>(); // <from, <to, weight>>
            int R1 = 0;
            while (R1 < R) {
                int[] dot = r.ints(2, 1, N + 1).toArray();
                int A = dot[0];
                int B = dot[1];
                int S = r.nextInt(0, 9);
                int F = r.nextInt(S + 1, 10);
                Map<Integer, D.Races> A2 = edges.computeIfAbsent(A, k -> new HashMap<>());
                A2.compute(B, (k, q) -> (q == null) ? new D.Races(S, F) : q.add(S, F));
                R1++;
                msg.append(A).append(" ").append(S).append(" ").append(B).append(" ").append(F).append("\n");
            }

            String expected = D.alg1(N, R, edges, d, v);
            String actual2 = D.alg2(N, R, edges, d, v);

            assertAll(
                    () -> assertEquals(expected, actual2, msg.toString())
            );
        }
    }

    @Test
    void testTL() {
        ThreadLocalRandom r = ThreadLocalRandom.current();
        for (int i = 0; i < 5; i++) {
            int N = 100;

            Map<Integer, Map<Integer, D.Races>> edges = new HashMap<>(); // <from, <to, weight>>
            int edgesCount = 0;
            for (int A = 1; A <= N; A++) {
                for (int B = 1; B <= N; B++) {
                    for (int j = 0; j < 3; j++) {
                        int S = r.nextInt(0, 9);
                        int F = r.nextInt(S + 1, 10);
                        Map<Integer, D.Races> A2 = edges.computeIfAbsent(A, k -> new HashMap<>());
                        A2.compute(B, (k, q) -> (q == null) ? new D.Races(S, F) : q.add(S, F));
                        edgesCount++;
                    }
                }
            }

            int R = edgesCount;

            int A = r.nextInt(N) + 1;
            int B = ((A - 1) + r.nextInt(0, N)) % N + 1;

            assertAll(
                    () -> assertTimeout(Duration.ofSeconds(3), () -> D.alg2(N, R, edges, A, B), "alg2() is too slow")
            );
        }
    }

    @Test
    void test3() {
        check("""
                6
                1 5
                100
                5 8 1 18
                6 16 5 18
                4 8 1 11
                6 6 3 7
                1 10 5 17
                6 7 4 16
                5 14 3 14
                3 19 3 19
                1 3 1 13
                6 19 3 19
                3 3 2 9
                1 3 6 8
                4 9 5 17
                2 18 5 18
                2 8 6 12
                4 15 5 17
                4 16 4 19
                5 3 1 12
                5 13 5 15
                6 1 5 6
                1 5 3 13
                2 13 5 14
                2 7 5 16
                4 10 4 10
                2 3 3 5
                3 1 1 3
                2 9 6 19
                3 17 5 19
                4 3 2 8
                4 12 2 19
                3 3 6 10
                5 1 2 3
                2 4 1 19
                5 6 5 13
                5 18 5 18
                4 1 5 8
                2 10 1 16
                6 14 1 16
                5 6 2 13
                6 17 3 17
                1 7 2 11
                5 14 5 17
                1 1 5 2
                6 9 1 18
                2 2 3 4
                1 17 4 19
                1 19 1 19
                5 16 1 19
                4 3 5 6
                3 19 2 19
                4 16 2 18
                2 12 4 16
                3 17 3 19
                1 3 2 15
                3 3 4 5
                1 7 5 15
                1 9 5 19
                2 17 1 18
                1 13 1 17
                2 6 3 10
                1 1 1 18
                4 11 2 17
                1 13 3 14
                4 9 1 11
                6 9 3 9
                1 1 2 19
                4 2 5 5
                2 3 6 7
                3 4 5 13
                5 2 1 19
                6 18 1 19
                4 11 4 18
                6 10 1 10
                3 10 6 14
                4 6 3 11
                6 1 2 3
                3 2 5 9
                5 17 2 18
                5 4 1 6
                5 13 4 14
                3 5 1 5
                2 17 5 18
                3 1 6 15
                4 10 3 10
                6 14 5 14
                4 17 2 17
                1 13 4 17
                5 5 2 8
                5 12 1 19
                5 0 2 4
                3 2 5 12
                1 11 3 15
                3 8 1 16
                3 3 4 19
                3 0 4 5
                1 7 5 14
                5 16 5 16
                4 16 5 19
                4 18 5 18
                6 13 1 18
                """, """
                2
                """);
    }
}
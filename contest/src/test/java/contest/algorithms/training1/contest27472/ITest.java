package contest.algorithms.training1.contest27472;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Алг 1.0 ДЗ 2 (27472) Линейный поиск - I")
class ITest extends ContestChecker {
    private static final BiConsumer<InputStream, OutputStream> algorithm = (reader, writer) -> {
        try {
            I.alg(new BufferedReader(new InputStreamReader(reader)), new BufferedWriter(new OutputStreamWriter(writer)));
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
                3 2 2
                1 1
                2 2
                """, """
                * 2
                2 *
                1 1
                """);
    }

    @Test
    void example2() {
        check("""
                2 2 0
                """, """
                0 0
                0 0
                """);
    }

    @Test
    void example3() {
        check("""
                4 4 4
                1 3
                2 1
                4 2
                4 4
                """, """
                1 2 * 1
                * 2 1 1
                2 2 2 1
                1 * 2 *
                """);
    }

    @Test
    public void stressTest() {
        ThreadLocalRandom r = ThreadLocalRandom.current();
        int N = 6;
        int M = 6;
        int K = 5;
        int[][] mines = new int[K][2];
        for (int i = 0; i < 100_000; i++) {
            Map<Integer, Set<Integer>> cache = new HashMap<>();
            int k = 0;
            while (k < K) {
                int n = r.nextInt(1, N + 1);
                int m = r.nextInt(1, M + 1);
                if (cache.computeIfAbsent(n, key -> new HashSet<>()).add(m)) {
                    mines[k][0] = n;
                    mines[k][1] = m;
                    k++;
                }
            }

            String c1 = I.alg1(N, M, K, mines);
            String c2 = I.alg2(N, M, K, mines);
            assertEquals(c1, c2, String.format("\n%s %s %s\n%s\n\nExpected:\n%s\n\nActual:\n%s\n", N, M, K,
                    Arrays.stream(mines).map(b -> b[0] + " " + b[1]).collect(Collectors.joining("\n")), c1, c2));
        }
    }
}
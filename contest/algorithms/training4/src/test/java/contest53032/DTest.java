package contest53032;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiConsumer;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Алг 4.0 ДЗ 4 (53032) Перебор и методы его оптимизации - D")
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
                1
                0
                """, """
                0
                """);
    }

    @Test
    void example2() {
        check("""
                2
                0 1
                1 0
                """, """
                2
                """);
    }

    @Test
    void example3() {
        check("""
                2
                0 85\s
                85 0\s
                """, """
                170
                """);
    }

    @Test
    void testTL() {
        ThreadLocalRandom r = ThreadLocalRandom.current();
        for (int i = 0; i < 100; i++) {
            int N = 10;
            int E = r.nextInt(N * (N - 1) / 2);
            int[][] G = new int[N][N];
            for (int A = 0; A < N && E > 0; A++) {
                for (int B = A + 1; B < N && E > 0; B++) {
                    int weight = r.nextInt(950, 1001);
                    G[A][B] = weight;
                    G[B][A] = weight;
                    E--;
                }
            }

            assertAll(
                    () -> assertTimeout(Duration.ofSeconds(2), () -> D.alg1(N, G), "alg1() is too slow")
            );
        }
    }
}
package contest27844;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiConsumer;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Алг 1.0 ДЗ 6 (27844) Бинарный поиск - K")
class KTest extends ContestChecker {
    private static final BiConsumer<InputStream, OutputStream> algorithm = (reader, writer) -> {
        try {
            K.alg(new BufferedReader(new InputStreamReader(reader)), new BufferedWriter(new OutputStreamWriter(writer)));
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
                3 6
                1 3 1 0 5
                0 2 1 1 100
                1 6 8 5 11
                """, """
                7
                10
                9
                """);
    }

    @Test
    void testTL() {
        ThreadLocalRandom r = ThreadLocalRandom.current();
        for (int i = 0; i < 100; i++) {
            int N = r.nextInt(2, 201);
            int L = r.nextInt(1, 50_001);
            int[][] P = new int[N][5];
            for (int j = 0; j < N; j++) {
                int x1 = r.nextInt(-1_000_000_000, 1_000_000_000);
                int m = r.nextInt(1, Math.min(40_0001, (1_000_000_000 - x1) / L + 2));
                int d1 = r.nextInt(m);
                int a = r.nextInt(m);
                int c = r.nextInt(m);
                P[j] = new int[]{x1, d1, a, c, m};
            }

            assertTimeout(Duration.ofMillis(5_000), () -> K.alg(N, L, P));
        }
    }
}
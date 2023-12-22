package contest27393;

import common.ContestChecker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiConsumer;

@DisplayName("Алг 1.0 ДЗ 1 (27393) Сложность, тестирование, особые случаи - G")
class GTest extends ContestChecker {
    private static final BiConsumer<InputStream, OutputStream> algorithm = (reader, writer) -> {
        try {
            G.alg(new BufferedReader(new InputStreamReader(reader)), new BufferedWriter(new OutputStreamWriter(writer)));
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
                10 5 2
                """, """
                4
                """);
    }

    @Test
    void example2() {
        check("""
                13 5 3
                """, """
                3
                """);
    }

    @Test
    void example3() {
        check("""
                14 5 3
                """, """
                4
                """);
    }

    @Test
    void test1() {
        check("""
                55 31 13
                """, """
                2
                """);
    }

    @Test
    void stressTest() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int maxExclusive = 20;
        long t = System.currentTimeMillis();
        while (System.currentTimeMillis() - t < 3000) {
            int N = random.nextInt(1, maxExclusive);
            int K = random.nextInt(1, maxExclusive);
            int M = random.nextInt(1, maxExclusive);

            String params = String.format("%s %s %s", N, K, M);
            String expected = params + " > " + G.slow(N, K, M);
            String actual = params + " > " + G.fast(N, K, M);
            Assertions.assertEquals(expected, actual);
        }
    }
}
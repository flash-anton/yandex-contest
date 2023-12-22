package contest27393;

import common.ContestChecker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiConsumer;

@DisplayName("Алг 1.0 ДЗ 1 (27393) Сложность, тестирование, особые случаи - E")
class ETest extends ContestChecker {
    private static final BiConsumer<InputStream, OutputStream> algorithm = (reader, writer) -> {
        try {
            E.alg(new BufferedReader(new InputStreamReader(reader)), new BufferedWriter(new OutputStreamWriter(writer)));
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
                89 20 41 1 11
                """, """
                2 3
                """);
    }

    @Test
    void example2() {
        check("""
                11 1 1 1 1
                """, """
                0 1
                """);
    }

    @Test
    void example3() {
        check("""
                3 2 2 2 1
                """, """
                -1 -1
                """);
    }

    @Test
    void test1() {
        check("""
                5 4 9 2 2
                """, """
                -1 -1
                """);
    }

    @Test
    void test2() {
        check("""
                7 8 9 1 3
                """, """
                1 0
                """);
    }

    @Test
    void test3() {
        check("""
                9 4 2 1 1
                """, """
                0 0
                """);
    }

    @Test
    void test4() {
        check("""
                9 2 4 1 2
                """, """
                0 1
                """);
    }

    @Test
    void stressTest() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        long maxExclusive = 10;
        long t = System.currentTimeMillis();
        while (System.currentTimeMillis() - t < 5000) {
            long K1 = random.nextLong(1, maxExclusive) - 1;
            long M = random.nextLong(1, maxExclusive);
            long K2 = random.nextLong(1, maxExclusive) - 1;
            long P2 = random.nextLong(1, maxExclusive) - 1;
            long N2 = random.nextLong(1, maxExclusive) - 1;

            String params = String.format("%s %s %s %s %s", K1 + 1, M, K2 + 1, P2 + 1, N2 + 1);
            String expected = params + " > " + E.slow(K1, M, K2, P2, N2, maxExclusive);
            String actual = params + " > " + E.fast(K1, M, K2, P2, N2);
            Assertions.assertEquals(expected, actual);
        }
    }
}
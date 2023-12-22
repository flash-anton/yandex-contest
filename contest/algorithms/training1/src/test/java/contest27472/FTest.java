package contest27472;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Алг 1.0 ДЗ 2 (27472) Линейный поиск - F")
class FTest extends ContestChecker {
    private static final BiConsumer<InputStream, OutputStream> algorithm = (reader, writer) -> {
        try {
            F.alg(new BufferedReader(new InputStreamReader(reader)), new BufferedWriter(new OutputStreamWriter(writer)));
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
                9
                1 2 3 4 5 4 3 2 1
                """, """
                0
                """);
    }

    @Test
    void example2() {
        check("""
                5
                1 2 1 2 2
                """, """
                3
                1 2 1
                """);
    }

    @Test
    void example3() {
        check("""
                5
                1 2 3 4 5
                """, """
                4
                4 3 2 1
                """);
    }

    @Test
    void test1() {
        check("""
                1
                1
                """, """
                0
                """);
    }

    @Test
    public void stressTest() {
        Random r = ThreadLocalRandom.current();
        for (int i = 0; i < 1_000_000; i++) {
            int[] n = r.ints(10, 1, 10).toArray();
            int c1 = F.alg1(n.length, n);
            int c2 = F.alg2(n.length, n);
            assertEquals(c1, c2, String.format("%nn = %s%n", Arrays.stream(n).mapToObj(String::valueOf).collect(Collectors.joining())));
        }
    }
}
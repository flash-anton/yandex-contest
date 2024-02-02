package contest27794;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Алг 1.0 ДЗ 5 (27794) Префиксные суммы и два указателя - D")
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
                4 4
                1 3 5 8
                """, """
                2
                """);
    }

    @Test
    void exampleOn7WA() {
        check("""
                10 54
                11 38 46 49 57 59 62 65 67 72
                """, """
                2
                """);
    }

    @Test
    void test1() {
        check("""
                3 4
                1 7 16
                """, """
                3
                """);
    }

    @Test
    public void stressTest() {
        ThreadLocalRandom r = ThreadLocalRandom.current();
        for (int i = 0; i < 100_000; i++) {
            int N = r.nextInt(2, 11);
            int R = r.nextInt(1, 11);
            int[] d = new int[N];
            d[0] = 1;
            for (int j = 1; j < N; j++) {
                int lastD = d[j - 1];
                d[j] = r.nextInt(lastD + 1, lastD + 11);
            }

            String expected = D.alg1(N,R,d);
            String actual = D.alg2(N,R,d);

            assertEquals(expected, actual, () -> "\n" + N + " " + R + "\n" +
                    Arrays.stream(d).mapToObj(String::valueOf).collect(Collectors.joining(" ")) + "\n");
        }
    }
}
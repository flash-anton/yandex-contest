package contest27794;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiConsumer;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Алг 1.0 ДЗ 5 (27794) Префиксные суммы и два указателя - A")
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
                2
                3 4
                3
                1 2 3
                """, """
                3 3
                """);
    }

    @Test
    void example2() {
        check("""
                2
                4 5
                3
                1 2 3
                """, """
                4 3
                """);
    }

    @Test
    void example3() {
        check("""
                5
                1 2 3 4 5
                5
                1 2 3 4 5
                """, """
                1 1
                """);
    }

    @Test
    void stressTest() {
        ThreadLocalRandom r = ThreadLocalRandom.current();
        int N = 7;
        int M = 9;
        IntSupplier supplier = () -> r.nextInt(1, N * M);
        for (int i = 0; i < 100_000; i++) {
            int[] n = IntStream.generate(supplier).distinct().limit(N).sorted().toArray();
            int[] m = IntStream.generate(supplier).distinct().limit(M).sorted().toArray();
            String c1 = A.slow(N, n, M, m);
            String c2 = A.alg1(N, n, M, m);
            assertEquals(c1, c2, () -> {
                String nStr = Arrays.stream(n).mapToObj(String::valueOf).collect(Collectors.joining(" "));
                String mStr = Arrays.stream(m).mapToObj(String::valueOf).collect(Collectors.joining(" "));
                return String.format("\n%d\n%s\n%d\n%s\n", N, nStr, M, mStr);
            });
        }
    }
}
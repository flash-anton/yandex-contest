package contest.algorithms.training4.contest53029;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.io.*;
import java.time.Duration;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Алг 4.0 ДЗ 1 (53029) Сортировки: быстрая, слиянием и поразрядная - C")
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
                5
                1 3 5 5 9
                3
                2 5 6
                """, """
                1 2 3 5 5 5 6 9
                """);
    }

    @Test
    void example2() {
        check("""
                1
                0
                0
                                
                """, """
                0
                """);
    }

    @Test
    void example3() {
        check("""
                0
                                
                1
                0
                """, """
                0
                """);
    }

    static class ArgProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            return Stream.of(
                    Arguments.of("", "   0 \n     \n 0 \n    "),
                    Arguments.of("4 5", "2 \n 4 5 \n 0 \n    "),
                    Arguments.of("4 5", "0 \n     \n 2 \n 4 5"),
                    Arguments.of("4 5", "1 \n 4   \n 1 \n   5"),
                    Arguments.of("4 5", "1 \n   5 \n 1 \n 4  ")
            );
        }
    }

    @ParameterizedTest
    @ArgumentsSource(ArgProvider.class)
    void test(String expected, String req) {
        check(req, expected);
    }

    @Test
    public void stressTest() {
        ThreadLocalRandom r = ThreadLocalRandom.current();
        for (int i = 0; i < 100_000; i++) {
            int N = r.nextInt(0, 11);
            Integer[] a = r.ints(N, -1_000_000_000, 1_000_000_001).boxed().toArray(Integer[]::new);
            int M = r.nextInt(0, 11);
            Integer[] b = r.ints(M, -1_000_000_000, 1_000_000_001).boxed().toArray(Integer[]::new);

            String expected = C.alg1(N, Arrays.copyOf(a, N), M, Arrays.copyOf(b, M));
            String actual = C.alg2(N, Arrays.copyOf(a, N), M, Arrays.copyOf(b, M));

            assertEquals(expected, actual, () -> String.format("\n%d\n%s\n%d\n%s",
                    N, Arrays.stream(a).map(String::valueOf).collect(Collectors.joining(" ")),
                    M, Arrays.stream(b).map(String::valueOf).collect(Collectors.joining(" "))));
        }
    }

    @Test
    void testTL() {
        ThreadLocalRandom r = ThreadLocalRandom.current();
        for (int i = 0; i < 1; i++) {
            int N = 1_000_000;
            Integer[] a = r.ints(N, -1_000_000_000, 1_000_000_001).boxed().toArray(Integer[]::new);
            int M = 1_000_000;
            Integer[] b = r.ints(M, -1_000_000_000, 1_000_000_001).boxed().toArray(Integer[]::new);

            assertAll(
                    () -> assertTimeout(Duration.ofSeconds(5), () -> C.alg1(N, Arrays.copyOf(a, N), M, Arrays.copyOf(b, M)), "alg1() is too slow"),
                    () -> assertTimeout(Duration.ofSeconds(5), () -> C.alg2(N, Arrays.copyOf(a, N), M, Arrays.copyOf(b, M)), "alg2() is too slow")
            );
        }
    }
}
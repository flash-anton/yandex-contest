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

@DisplayName("Алг 4.0 ДЗ 1 (53029) Сортировки: быстрая, слиянием и поразрядная - D")
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
                5
                1 5 2 4 3
                """, """
                1 2 3 4 5
                """);
    }

    static class ArgProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            return Stream.of(
                    Arguments.of("", "0 \n "),
                    Arguments.of("45", "1 \n 45"),

                    Arguments.of("5 5", "2 \n 5 5"),
                    Arguments.of("4 5", "2 \n 4 5"),
                    Arguments.of("4 5", "2 \n 5 4"),

                    Arguments.of("5 5 5", "3 \n 5 5 5"),

                    Arguments.of("4 5 5", "3 \n 4 5 5"),
                    Arguments.of("4 5 5", "3 \n 5 4 5"),
                    Arguments.of("4 5 5", "3 \n 5 5 4"),

                    Arguments.of("5 5 6", "3 \n 5 5 6"),
                    Arguments.of("5 5 6", "3 \n 5 6 5"),
                    Arguments.of("5 5 6", "3 \n 6 5 5"),

                    Arguments.of("4 5 6", "3 \n 4 5 6"),
                    Arguments.of("4 5 6", "3 \n 4 6 5"),
                    Arguments.of("4 5 6", "3 \n 5 4 6"),
                    Arguments.of("4 5 6", "3 \n 5 6 4"),
                    Arguments.of("4 5 6", "3 \n 6 4 5"),
                    Arguments.of("4 5 6", "3 \n 6 5 4")
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

            String expected = D.alg1(N, Arrays.copyOf(a, N));
            String actual = D.alg2(N, Arrays.copyOf(a, N));

            assertEquals(expected, actual, () -> String.format("\n%d\n%s", N,
                    Arrays.stream(a).map(String::valueOf).collect(Collectors.joining(" "))));
        }
    }

    @Test
    void testTL() {
        ThreadLocalRandom r = ThreadLocalRandom.current();
        for (int i = 0; i < 1; i++) {
            int N = 1_000_000;
            Integer[] a = r.ints(N, -1_000_000_000, 1_000_000_001).boxed().toArray(Integer[]::new);

            assertAll(
                    () -> assertTimeout(Duration.ofSeconds(15), () -> D.alg1(N, Arrays.copyOf(a, N)), "alg1() is too slow"),
                    () -> assertTimeout(Duration.ofSeconds(15), () -> D.alg2(N, Arrays.copyOf(a, N)), "alg2() is too slow")
            );
        }
    }
}
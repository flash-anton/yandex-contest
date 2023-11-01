package contest.algorithms.training4.contest53027;

import common.ContestChecker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.io.*;
import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTimeout;

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
                4 5
                0 0 0 1 0
                0 1 1 1 0
                0 0 1 1 0
                1 0 1 0 0
                """, """
                2
                """);
    }

    @Test
    void example2() {
        check("""
                10 10
                1 1 1 1 1 1 1 1 1 1
                1 1 1 1 1 1 1 1 1 1
                1 1 1 1 1 1 1 1 1 1
                1 1 1 1 1 1 1 1 1 1
                1 1 1 1 1 1 1 1 1 1
                1 1 1 1 1 1 1 1 1 1
                1 1 1 1 1 1 1 1 1 1
                1 1 1 1 1 1 1 1 1 1
                1 1 1 1 1 1 1 1 1 1
                1 1 1 1 1 1 1 1 1 1
                """, """
                10
                """);
    }

    @Test
    void example3() {
        check("""
                10 10
                0 0 0 0 0 0 0 0 0 0
                0 0 0 0 0 0 0 0 0 0
                0 0 0 0 0 0 0 0 0 0
                0 0 0 0 0 0 0 0 0 0
                0 0 0 0 0 0 0 0 0 0
                0 0 0 0 0 0 0 0 0 0
                0 0 0 0 0 0 0 0 0 0
                0 0 0 0 0 0 0 0 0 0
                0 0 0 0 0 0 0 0 0 0
                0 0 0 0 0 0 0 0 0 0
                """, """
                0
                """);
    }

    static class ArgProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            return Stream.of(
                    Arguments.of("0", "1 1 \n 0"),
                    Arguments.of("0", "2 1 \n 0   \n 0"),
                    Arguments.of("0", "1 2 \n 0 0"),
                    Arguments.of("0", "2 2 \n 0 0 \n 0 0"),

                    Arguments.of("1", "1 1 \n 1"),
                    Arguments.of("1", "2 1 \n 1   \n 0"),
                    Arguments.of("1", "2 1 \n 0   \n 1"),
                    Arguments.of("1", "1 2 \n 1 0"),
                    Arguments.of("1", "1 2 \n 0 1"),
                    Arguments.of("1", "2 2 \n 0 0 \n 0 1"),
                    Arguments.of("1", "2 2 \n 0 0 \n 1 0"),
                    Arguments.of("1", "2 2 \n 0 0 \n 1 1"),
                    Arguments.of("1", "2 2 \n 0 1 \n 0 0"),
                    Arguments.of("1", "2 2 \n 0 1 \n 0 1"),
                    Arguments.of("1", "2 2 \n 0 1 \n 1 0"),
                    Arguments.of("1", "2 2 \n 0 1 \n 1 1"),
                    Arguments.of("1", "2 2 \n 1 0 \n 0 0"),
                    Arguments.of("1", "2 2 \n 1 0 \n 0 1"),
                    Arguments.of("1", "2 2 \n 1 0 \n 1 0"),
                    Arguments.of("1", "2 2 \n 1 0 \n 1 1"),
                    Arguments.of("1", "2 2 \n 1 1 \n 0 0"),
                    Arguments.of("1", "2 2 \n 1 1 \n 0 1"),
                    Arguments.of("1", "2 2 \n 1 1 \n 1 0"),

                    Arguments.of("2", "2 2 \n 1 1 \n 1 1")
            );
        }
    }

    @ParameterizedTest
    @ArgumentsSource(ArgProvider.class)
    void test(String expected, String req) {
        check(req, expected);
    }

    @Test
    void testTL() {
        ThreadLocalRandom r = ThreadLocalRandom.current();
        for (int i = 0; i < 100; i++) {
            int N = 1_000;
            int M = 1_000;
            int[][] field = new int[N][];
            for (int j = 0; j < N; j++) {
                field[j] = r.ints(M, 0, 2).toArray();
            }
            assertTimeout(Duration.ofSeconds(4), () -> G.alg(N, M, field), "alg() is too slow");
        }
    }
}
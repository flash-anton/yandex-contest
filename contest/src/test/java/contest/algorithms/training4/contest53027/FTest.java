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
                2
                3
                3
                0
                1
                """, """
                8
                """);
    }

    static class ArgProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            return Stream.of(
                    Arguments.of("1 1 5", "10"),
                    Arguments.of("1 2 1000000000 1000000000", "6000000000"),

                    Arguments.of("3 2 0 0", "0"),

                    Arguments.of("3 2 0 2", "4"),
                    Arguments.of("3 2 1 2", "4"),
                    Arguments.of("3 2 2 2", "6"),
                    Arguments.of("3 2 4 2", "6"),
                    Arguments.of("3 2 5 2", "8"),

                    Arguments.of("3 2 0 3", "4"),
                    Arguments.of("3 2 1 3", "6"),
                    Arguments.of("3 2 3 3", "6"),
                    Arguments.of("3 2 4 3", "8"),

                    Arguments.of("3 2 0 4", "8"),
                    Arguments.of("3 2 2 4", "8"),
                    Arguments.of("3 2 3 4", "10")
            );
        }
    }

    @ParameterizedTest
    @ArgumentsSource(ArgProvider.class)
    void test(String in, String expected) {
        check(in.replaceAll(" ", "\n"), expected);
    }

    @Test
    void testTL() {
        ThreadLocalRandom r = ThreadLocalRandom.current();
        for (int i = 0; i < 100; i++) {
            int[] a = r.ints(100_000, 0, 1_000_000_001).toArray();
            assertTimeout(Duration.ofSeconds(1), () -> F.alg(1, a), "alg is too slow");
        }
    }
}
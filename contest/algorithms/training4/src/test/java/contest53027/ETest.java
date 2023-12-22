package contest53027;

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
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Алг 4.0 ДЗ 0 (53027) Разминка - E")
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
                3
                1 3 4
                """, """
                5 3 4
                """);
    }

    @Test
    void example2() {
        check("""
                5
                3 7 8 10 15
                """, """
                28 16 15 17 32
                """);
    }

    static class ArgProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            return Stream.of(
                    Arguments.of(4, "4 4 4 4", "0 0 0 0"),
                    Arguments.of(4, "4 4 5 5", "2 2 2 2"),
                    Arguments.of(4, "0 2 2 5", "9 5 5 11")
            );
        }
    }

    @ParameterizedTest
    @ArgumentsSource(ArgProvider.class)
    void test(int n, String aStr, String expected) {
        check(n + "\n" + aStr, expected);
    }

    @Test
    void testTL() {
        ThreadLocalRandom r = ThreadLocalRandom.current();
        for (int i = 0; i < 50; i++) {
            int[] a = r.ints(100_000, 0, 10_001).sorted().toArray();
            assertTimeout(Duration.ofSeconds(1), () -> E.alg(a), "alg() is too slow");
        }
    }
}
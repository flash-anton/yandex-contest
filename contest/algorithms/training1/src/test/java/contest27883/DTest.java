package contest27883;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

@DisplayName("Алг 1.0 ДЗ 7 (27883) - D")
class DTest extends ContestChecker {
    private static final BiConsumer<InputStream, OutputStream> algorithm = (reader, writer) -> {
        try {
            D.alg(reader, new BufferedWriter(new OutputStreamWriter(writer)));
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
                4
                1 11
                1 3
                6 15
                1 6
                """, """
                3 1 6
                """);
    }

    @Test
    void example2() {
        check("""
                1
                1 10
                """, """
                1 1 10
                """); // actual 1 3 25
    }

    @Test
    void example3() {
        check("""
                3
                1 10
                11 20
                21 30
                """, """
                2 1 11
                """); // actual 2 1 22
    }

    @Test
    void example54() {
        check("""
                25
                25 32
                28 35
                25 32
                28 35
                25 32
                28 35
                25 32
                28 35
                25 32
                28 35
                25 32
                28 35
                25 32
                28 35
                25 32
                28 35
                25 32
                28 35
                25 32
                28 35
                25 32
                28 35
                25 32
                28 35
                53 60
                """, """
                24 25 30
                """);
    }

    static class ArgProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            return Stream.of(
                    Arguments.of("1 2 11", "1\n 2 11"),
                    Arguments.of("2 1 6", "2\n 1 6\n 2 20"),
                    Arguments.of("1 6 15", "2\n 5 7\n 6 15"),
                    Arguments.of("2 6 11", "2\n 6 16\n 6 16"),
                    Arguments.of("2 5 14", "2\n 7 19\n 5 19"),
                    Arguments.of("2 4 9", "2\n 7 16\n 4 9"),
                    Arguments.of("2 5 10", "2\n 5 10\n 9 17"),
                    Arguments.of("2 5 10", "2\n 5 10\n 9 16")
            );
        }
    }

    @ParameterizedTest
    @ArgumentsSource(ArgProvider.class)
    void test(String expected, String req) {
        check(req, expected);
    }
}
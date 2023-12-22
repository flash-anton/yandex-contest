package contest53033;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.io.*;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

@DisplayName("Алг 4.0 Финал (53033) - D")
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
                5 2
                1 2
                """, """
                3
                2 2 1\s
                """);
    }

    @Test
    void example2() {
        check("""
                7 2
                1 2
                """, """
                -1
                """);
    }

    @Test
    void example3() {
        check("""
                5 2
                3 4
                """, """
                0
                """);
    }

    static class ArgProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            return Stream.of(
                    Arguments.of(" 7 4\n5 4 3 1", "2\n4 3"),
                    Arguments.of(" 1 2\n2 4", "0"),
                    Arguments.of(" 2 2\n2 4", "1\n2"),
                    Arguments.of(" 3 2\n2 4", "0"),
                    Arguments.of(" 4 2\n2 4", "1\n4"),
                    Arguments.of(" 5 2\n2 4", "0"),
                    Arguments.of(" 6 2\n2 4", "2\n4 2"),
                    Arguments.of(" 7 2\n2 4", "0"),
                    Arguments.of(" 8 2\n2 4", "2\n4 4"),
                    Arguments.of(" 9 2\n2 4", "0"),
                    Arguments.of("10 2\n2 4", "3\n4 4 2"),
                    Arguments.of("11 2\n2 4", "0"),
                    Arguments.of("12 2\n2 4", "4\n4 4 2 2"),
                    Arguments.of("13 2\n2 4", "-1")
            );
        }
    }

    @ParameterizedTest
    @ArgumentsSource(ArgProvider.class)
    void test(String req, String expected) {
        check(req, expected);
    }
}
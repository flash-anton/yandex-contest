package contest36783;

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

@DisplayName("Interview training 36783 - Q")
class QTest extends ContestChecker {
    private static final BiConsumer<InputStream, OutputStream> algorithm = (reader, writer) -> {
        try {
            Q.alg(reader, new BufferedWriter(new OutputStreamWriter(writer)));
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
                32 6
                29 2
                32 6
                29 2
                35 10
                """, """
                YES
                """);
    }

    @Test
    void example2() {
        check("""
                2
                -22 -75
                -30 -70
                """, """
                YES
                """);
    }

    @Test
    void example3() {
        check("""
                4
                86 -93
                88 -91
                70 -81
                86 -93
                """, """
                NO
                """);
    }

    static class ArgProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            return Stream.of(
                    Arguments.of("YES", "2 \n 3 4 \n 3 4"),
                    Arguments.of("YES", "3 \n -2 3 \n -2 3 \n 5 -2"),
                    Arguments.of("YES", "3 \n 2 -5 \n 4 2 \n 2 -5"),

                    Arguments.of("YES", "3 \n -2 3 \n 0 4 \n  2  5"),
                    Arguments.of("YES", "3 \n -2 3 \n -4 2 \n 0 4 \n  2  5"),
                    Arguments.of("YES", "3 \n 0 0 \n 1 1 \n -2 -2"),
                    Arguments.of("YES", "3 \n 0 0 \n -1 -1 \n  2  2"),
                    Arguments.of("YES", "3 \n 0 0 \n -1 -1 \n -2 -2"),
                    Arguments.of("NO", "3 \n 0 0 \n 1 1 \n  0  1"),
                    Arguments.of("NO", "3 \n 0 0 \n 1 1 \n  0 -1"),
                    Arguments.of("NO", "3 \n 0 0 \n 1 1 \n  1 0"),
                    Arguments.of("NO", "3 \n 0 0 \n 1 1 \n -1 0"),

                    Arguments.of("YES", "3 \n 0 0 \n 0 1 \n 0  2"),
                    Arguments.of("YES", "3 \n 0 0 \n 0 1 \n 0 -2"),
                    Arguments.of("NO", "3 \n 0 0 \n 0 1 \n  1  1"),
                    Arguments.of("NO", "3 \n 0 0 \n 0 1 \n  1 -1"),
                    Arguments.of("NO", "3 \n 0 0 \n 0 1 \n -1  1"),
                    Arguments.of("NO", "3 \n 0 0 \n 0 1 \n -1 -1"),

                    Arguments.of("YES", "3 \n 0 0 \n 1 0 \n  2 0"),
                    Arguments.of("YES", "3 \n 0 0 \n 1 0 \n -2 0"),
                    Arguments.of("NO", "3 \n 0 0 \n 1 0 \n  1  1"),
                    Arguments.of("NO", "3 \n 0 0 \n 1 0 \n  1 -1"),
                    Arguments.of("NO", "3 \n 0 0 \n 1 0 \n -1  1"),
                    Arguments.of("NO", "3 \n 0 0 \n 1 0 \n -1 -1")
            );
        }
    }

    @ParameterizedTest
    @ArgumentsSource(ArgProvider.class)
    void test(String expected, String req) {
        check(req, expected);
    }
}
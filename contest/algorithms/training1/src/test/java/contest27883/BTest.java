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

@DisplayName("Алг 1.0 ДЗ 7 (27883) - B")
class BTest extends ContestChecker {
    private static final BiConsumer<InputStream, OutputStream> algorithm = (reader, writer) -> {
        try {
            B.alg(reader, new BufferedWriter(new OutputStreamWriter(writer)));
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
                3 2
                0 5
                -3 2
                7 10
                1 6
                """, """
                2 0
                """);
    }

    static class ArgProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            return Stream.of(
                    Arguments.of("0", "1 1 \n 0 0 \n -1"),
                    Arguments.of("1", "1 1 \n 0 0 \n 0"),
                    Arguments.of("0", "1 1 \n 0 0 \n 1"),

                    Arguments.of("0 1 0", "1 3 \n 0 1 \n -1 0 2"),
                    Arguments.of("1 1 0", "1 3 \n 0 1 \n 0 0 -1"),
                    Arguments.of("1 0 0", "1 3 \n 0 1 \n 1 -1 2"),

                    Arguments.of("0 2 1", "2 3 \n 0 1 \n 0 2 \n -1 0 2"),
                    Arguments.of("2 2 0", "2 3 \n 0 1 \n 0 2 \n 0 0 -1"),
                    Arguments.of("2 0 1", "2 3 \n 0 1 \n 0 2 \n 1 -1 2"),

                    Arguments.of("0 1 1", "2 3 \n 0 1 \n 1 2 \n -1 0 2"),
                    Arguments.of("1 1 0", "2 3 \n 0 1 \n 1 2 \n 0 0 -1"),
                    Arguments.of("2 0 1", "2 3 \n 0 1 \n 1 2 \n 1 -1 2"),

                    Arguments.of("0 1 1", "2 3 \n 1 0 \n 2 1 \n -1 0 2"),
                    Arguments.of("1 1 0", "2 3 \n 1 0 \n 2 1 \n 0 0 -1"),
                    Arguments.of("2 0 1", "2 3 \n 1 0 \n 2 1 \n 1 -1 2")
            );
        }
    }

    @ParameterizedTest
    @ArgumentsSource(ArgProvider.class)
    void test(String expected, String req) {
        check(req, expected);
    }
}
package contest48568;

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

@DisplayName("Alg handbook 3.4 (48568) - B")
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
                3
                """, """
                5
                """);
    }

    @Test
    void example2() {
        check("""
                4
                """, """
                9
                """);
    }

    @Test
    void example3() {
        check("""
                5
                """, """
                13
                """);
    }

    // https://dl.acm.org/doi/pdf/10.1145/126459.126460
    static class ArgProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            return Stream.of(
                    Arguments.of("1", "1"),
                    Arguments.of("2", "3"),
                    Arguments.of("3", "5"),
                    Arguments.of("4", "9"),
                    Arguments.of("5", "13"),
                    Arguments.of("6", "17"),
                    Arguments.of("7", "25"),
                    Arguments.of("8", "33"),
                    Arguments.of("9", "41"),
                    Arguments.of("10", "49")
            );
        }
    }

    @ParameterizedTest
    @ArgumentsSource(ArgProvider.class)
    void test(String req, String expected) {
        check(req, expected);
    }
}
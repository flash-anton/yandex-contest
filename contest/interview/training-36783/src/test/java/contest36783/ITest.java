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

@DisplayName("Interview training 36783 - I")
class ITest extends ContestChecker {
    private static final BiConsumer<InputStream, OutputStream> algorithm = (reader, writer) -> {
        try {
            I.alg(reader, new BufferedWriter(new OutputStreamWriter(writer)));
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
                5 2 4
                1 2 3 4 5
                """, """
                1 4 3 2 5
                """);
    }

    static class ArgProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            return Stream.of(
                    Arguments.of("5 1 1 \n 1 2 3 4 5", "1 2 3 4 5"),
                    Arguments.of("5 1 2 \n 1 2 3 4 5", "2 1 3 4 5"),
                    Arguments.of("5 1 3 \n 1 2 3 4 5", "3 2 1 4 5"),
                    Arguments.of("5 1 5 \n 1 2 3 4 5", "5 4 3 2 1"),
                    Arguments.of("5 2 2 \n 1 2 3 4 5", "1 2 3 4 5"),
                    Arguments.of("5 2 3 \n 1 2 3 4 5", "1 3 2 4 5"),
                    Arguments.of("5 2 4 \n 1 2 3 4 5", "1 4 3 2 5"),
                    Arguments.of("5 2 5 \n 1 2 3 4 5", "1 5 4 3 2")
            );
        }
    }

    @ParameterizedTest
    @ArgumentsSource(ArgProvider.class)
    void test(String req, String expected) {
        check(req, expected);
    }
}
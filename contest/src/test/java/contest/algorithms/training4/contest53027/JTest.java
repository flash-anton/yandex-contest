package contest.algorithms.training4.contest53027;

import common.ContestChecker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.io.*;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

class JTest extends ContestChecker {
    private static final BiConsumer<InputStream, OutputStream> algorithm = (reader, writer) -> {
        try {
            J.alg(new BufferedReader(new InputStreamReader(reader)), new BufferedWriter(new OutputStreamWriter(writer)));
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
                10 2 3
                11 7 8
                28 4 6
                3 1 2
                """, """
                YES
                NO
                YES
                YES
                """);
    }

    static class ArgProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            return Stream.of(
                    Arguments.of("7 1 7", "YES"),
                    Arguments.of("7 1 6", "YES"),
                    Arguments.of("7 1 5", "YES"),
                    Arguments.of("7 1 4", "YES"),
                    Arguments.of("7 1 3", "YES"),
                    Arguments.of("7 1 2", "YES"),
                    Arguments.of("7 1 1", "YES"),

                    Arguments.of("7 2 7", "YES"),
                    Arguments.of("7 2 6", "YES"),
                    Arguments.of("7 2 5", "YES"),
                    Arguments.of("7 2 4", "YES"),
                    Arguments.of("7 2 3", "YES"),
                    Arguments.of("7 2 2", "NO"),

                    Arguments.of("7 3 7", "YES"),
                    Arguments.of("7 3 6", "YES"),
                    Arguments.of("7 3 5", "YES"),
                    Arguments.of("7 3 4", "YES"),
                    Arguments.of("7 3 3", "NO"),

                    Arguments.of("7 4 7", "YES"),
                    Arguments.of("7 4 6", "NO"),
                    Arguments.of("7 4 5", "NO"),
                    Arguments.of("7 4 4", "NO"),

                    Arguments.of("7 5 7", "YES"),
                    Arguments.of("7 5 6", "NO"),
                    Arguments.of("7 5 5", "NO"),

                    Arguments.of("7 6 7", "YES"),
                    Arguments.of("7 6 6", "NO"),

                    Arguments.of("7 7 7", "YES"),


                    Arguments.of("5 1 5", "YES"),
                    Arguments.of("5 1 4", "YES"),
                    Arguments.of("5 1 3", "YES"),
                    Arguments.of("5 1 2", "YES"),
                    Arguments.of("5 1 1", "YES"),

                    Arguments.of("5 2 5", "YES"),
                    Arguments.of("5 2 4", "YES"),
                    Arguments.of("5 2 3", "YES"),
                    Arguments.of("5 2 2", "NO"),

                    Arguments.of("5 3 5", "YES"),
                    Arguments.of("5 3 4", "NO"),
                    Arguments.of("5 3 3", "NO"),

                    Arguments.of("5 4 5", "YES"),
                    Arguments.of("5 4 4", "NO"),

                    Arguments.of("5 5 5", "YES"),


                    Arguments.of("4 1 4", "YES"),
                    Arguments.of("4 1 3", "YES"),
                    Arguments.of("4 1 2", "YES"),
                    Arguments.of("4 1 1", "YES"),

                    Arguments.of("4 2 4", "YES"),
                    Arguments.of("4 2 3", "YES"),
                    Arguments.of("4 2 2", "YES"),

                    Arguments.of("4 3 4", "YES"),
                    Arguments.of("4 3 3", "NO"),

                    Arguments.of("4 4 4", "YES")
            );
        }
    }

    @ParameterizedTest
    @ArgumentsSource(ArgProvider.class)
    void test(String req, String expected) {
        check("1\n" + req, expected);
    }
}
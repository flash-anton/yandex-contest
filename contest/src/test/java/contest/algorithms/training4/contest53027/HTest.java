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

class HTest extends ContestChecker {
    private static final BiConsumer<InputStream, OutputStream> algorithm = (reader, writer) -> {
        try {
            H.alg(new BufferedReader(new InputStreamReader(reader)), new BufferedWriter(new OutputStreamWriter(writer)));
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
                60
                30
                4
                """, """
                Yes
                """);
    }

    @Test
    void example2() {
        check("""
                30
                30
                1
                """, """
                No
                """);
    }

    @Test
    void example3() {
        check("""
                30
                150
                4
                """, """
                No
                """);
    }

    static class ArgProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            return Stream.of(
                    Arguments.of("No", "1 1 3"),  //                   {1}={1}
                    Arguments.of("No", "1 2 3"),  //                   {1}={2}     {1}<{1,1}
                    Arguments.of("No", "1 3 3"),  //                   {1}={3}     {1}<{1,1,1}
                    Arguments.of("No", "1 4 3"),  //                   {1}<{3,1}   {1}<{1,1,1,1}
                    Arguments.of("Yes", "2 1 3"), // >   {1,1}>{1}     {2}={1}
                    Arguments.of("Yes", "2 2 3"), // >   {1,1}>{2}     {2}={2}     {2}<{1,1}
                    Arguments.of("Yes", "2 3 3"), // >   {1,1}>{3}     {2}={3}     {2}<{1,1,1}
                    Arguments.of("No", "2 4 3"),  //     {1,1}={3,1}   {2}<{3,1}   {2}<{1,1,1,1}
                    Arguments.of("Yes", "3 1 3"), // > {1,1,1}>{1}     {3}={1}
                    Arguments.of("Yes", "3 2 3"), // > {1,1,1}>{2}     {3}={2}     {3}<{1,1}
                    Arguments.of("Yes", "3 3 3"), // > {1,1,1}>{3}     {3}={3}     {3}<{1,1,1}
                    Arguments.of("Yes", "3 4 3"), // > {1,1,1}>{3,1}   {3}<{3,1}   {3}<{1,1,1,1}
                    Arguments.of("Yes", "3 5 3"), // > {1,1,1}>{3,2}   {3}<{3,2}   {3}<{1,1,1,1,1}
                    Arguments.of("Yes", "3 6 3"), // > {1,1,1}>{3,3}   {3}<{3,3}   {3}<{1,1,1,1,1,1}
                    Arguments.of("No", "3 7 3")   //   {1,1,1}={3,3,1} {3}<{3,3,1} {3}<{1,1,1,1,1,1,1}
            );
        }
    }

    @ParameterizedTest
    @ArgumentsSource(ArgProvider.class)
    void test(String expected, String req) {
        check(req.replaceAll(" ", "\n"), expected);
    }
}
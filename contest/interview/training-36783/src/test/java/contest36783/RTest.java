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

@DisplayName("Interview training 36783 - R")
class RTest extends ContestChecker {
    private static final BiConsumer<InputStream, OutputStream> algorithm = (reader, writer) -> {
        try {
            R.alg(reader, new BufferedWriter(new OutputStreamWriter(writer)));
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
                8
                """, """
                Pasha
                """);
    }

    @Test
    void example2() {
        check("""
                3
                """, """
                Mark
                """);
    }

    /** <pre>
     first 1 -> fail
     first 2 -> win
     first 3 -> fail
     first 4 -> win
     first 5 -> fail
     first 6 -> win
     first 7 -> fail
     first 8 -> win
     first 9 -> fail
     first 10 -> win
     first 11 -> fail
     first 12 -> win
     first 13 -> fail
     first 14 -> win
     first 15 -> fail
     first 16 -> win

     first 2 -> isPrime -> get 1 -> 2-1=1  =>  second 1 -> fail  =>  first 2 -> win

     first 3 -> isPrime -> get 1 -> 3-1=2  =>  second 2 -> win  =>  first 3 -> fail

     first 4 -> 1*2*2 -> get 1 -> 4-1=3  =>  second 3 -> fail  =>  first win +
     first 4 -> 1*2*2 -> get 2 -> 4-2=2  =>  second 2 -> win  =>  first fail

     first 5 -> isPrime -> get 1 -> 5-1=4  =>  second 4 -> win  =>  first fail

     first 6 -> 1*2*3 -> get 1 -> 6-1=5  =>  second 5 -> fail  =>  first win
     first 6 -> 1*2*3 -> get 2 -> 6-2=4  =>  second 4 -> win  =>  first fail
     first 6 -> 1*2*3 -> get 3 -> 6-3=3  =>  second 3 -> fail  =>  first win +

     first 7 -> isPrime -> get 1 -> 7-1=6  =>  second 6 -> win  =>  first fail

     first 8 -> 1*2*2*2 -> get 1 -> 8-1=7  =>  second 7 -> fail  =>  first win +
     first 8 -> 1*2*2*2 -> get 2 -> 8-2=6  =>  second 6 -> win  =>  first fail
     first 8 -> 1*2*2*2 -> get 2*2=4 -> 8-4=4  =>  second 4 -> win  =>  first fail

     first 9 -> 1*3*3 -> get 1 -> 9-1=8  =>  second 8 -> win  =>  first fail
     first 9 -> 1*3*3 -> get 3 -> 9-3=6  =>  second 6 -> win  =>  first fail

     first 10 -> 1*2*5 -> get 1 -> 9-1=8  =>  second 8 -> win  =>  first fail
     first 10 -> 1*2*5 -> get 2 -> 9-2=7  =>  second 7 -> fail  =>  first win +
     first 10 -> 1*2*5 -> get 5 -> 9-5=4  =>  second 4 -> win  =>  first fail

     first 11 -> isPrime -> get 1 -> 11-1=10  =>  second 10 -> win  =>  first fail

     first 12 -> 1*2*2*3 -> get 1 -> 12-1=11  =>  second 11 -> fail  =>  first win +
     first 12 -> 1*2*2*3 -> get 2 -> 12-2=10  =>  second 10 -> win  =>  first fail
     first 12 -> 1*2*2*3 -> get 3 -> 12-3=9  =>  second 9 -> fail  =>  first win +
     first 12 -> 1*2*2*3 -> get 2*2=4 -> 12-4=8  =>  second 8 -> win  =>  first fail
     first 12 -> 1*2*2*3 -> get 2*3=6 -> 12-6=6  =>  second 6 -> win  =>  first fail

     first 13 -> isPrime -> get 1 -> 13-1=12  =>  second 12 -> win  =>  first fail

     first 14 -> 1*2*7 -> get 1 -> 14-1=13  =>  second 13 -> fail  =>  first win +
     first 14 -> 1*2*7 -> get 2 -> 14-2=12  =>  second 12 -> win  =>  first fail
     first 14 -> 1*2*7 -> get 7 -> 14-7=7  =>  second 7 -> fail  =>  first win +

     first 15 -> 1*3*5 -> get 1 -> 15-1=14  =>  second 14 -> win  =>  first fail
     first 15 -> 1*3*5 -> get 3 -> 15-3=12  =>  second 12 -> win  =>  first fail
     first 15 -> 1*3*5 -> get 3 -> 15-5=10  =>  second 10 -> win  =>  first fail

     first 16 -> 1*2*2*2*2 -> get 1 -> 16-1=15  =>  second 15 -> fail  =>  first win +
     first 16 -> 1*2*2*2*2 -> get 2 -> 16-2=14  =>  second 14 -> win  =>  first fail
     first 16 -> 1*2*2*2*2 -> get 4 -> 16-4=12  =>  second 12 -> win  =>  first fail
     first 16 -> 1*2*2*2*2 -> get 8 -> 16-8=8  =>  second 8 -> win  =>  first fail
     </pre>*/
    static class ArgProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            return Stream.of(
                    Arguments.of("1", "Mark"),
                    Arguments.of("2", "Pasha"),
                    Arguments.of("3", "Mark"),
                    Arguments.of("4", "Pasha"),
                    Arguments.of("5", "Mark"),
                    Arguments.of("6", "Pasha"),
                    Arguments.of("7", "Mark"),
                    Arguments.of("8", "Pasha"),
                    Arguments.of("9", "Mark"),
                    Arguments.of("10", "Pasha"),
                    Arguments.of("11", "Mark"),
                    Arguments.of("12", "Pasha"),
                    Arguments.of("13", "Mark"),
                    Arguments.of("14", "Pasha"),
                    Arguments.of("15", "Mark"),
                    Arguments.of("16", "Pasha")
            );
        }
    }

    @ParameterizedTest
    @ArgumentsSource(ArgProvider.class)
    void test(String req, String expected) {
        check(req, expected);
    }
}
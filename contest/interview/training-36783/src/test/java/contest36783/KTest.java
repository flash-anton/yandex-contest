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
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Interview training 36783 - K")
class KTest extends ContestChecker {
    private static final BiConsumer<InputStream, OutputStream> algorithm = (reader, writer) -> {
        try {
            K.alg(reader, new BufferedWriter(new OutputStreamWriter(writer)));
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
                *a*
                adce
                """, """
                YES
                """);
    }

    @Test
    void example2() {
        check("""
                *
                xyz
                """, """
                YES
                """);
    }

    @Test
    void example3() {
        check("""
                algorithms
                algorithmus
                """, """
                NO
                """);
    }

    @Test
    void example4() {
        check("""
                ?
                k
                """, """
                YES
                """);
    }

    static class ArgProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            return Stream.of(
                    Arguments.of("*a*b*\n    121aba122bbc12211", "YES"),
                    Arguments.of("*a*b?b*\n  121aba122bbc12211", "NO"),
                    Arguments.of("*a*?b?b*\n  121acbcb12211", "YES"),
                    Arguments.of("*a*?b?b*\n  121abcb12211", "NO"),
                    Arguments.of("*d*\n  cd", "YES"),
                    Arguments.of("d*\n  d", "YES"),
                    Arguments.of("j\n  jefrryxwef", "NO"),
                    Arguments.of("d\n  dzd", "NO"),
                    Arguments.of("jr\n  jrajr", "NO"),
                    Arguments.of("*\n  yfyomwqva", "YES"),
                    Arguments.of("h\n  h", "YES")
            );
        }
    }

    @ParameterizedTest
    @ArgumentsSource(ArgProvider.class)
    void test(String req, String expected) {
        check(req, expected);
    }

    @Test
    public void stressTest() {
        ThreadLocalRandom r = ThreadLocalRandom.current();
        for (int i = 0; i < 100_000; i++) {
            int T = r.nextInt(1, 11);
            int R = r.nextInt(1, 11);

            StringBuilder t = new StringBuilder();
            for (int j = 0; j < T; j++) {
                char c = (char) r.nextInt('a' - 1, 'z' + 1);
                t.append(c < 'a' ? '*' : c <= 'z' ? c : '?');
            }
            String template = t.toString();

            StringBuilder req = new StringBuilder();
            for (int j = 0; j < R; j++) {
                char c = (char) r.nextInt('a', 'z');
                req.append(c);
            }
            String request = req.toString();

            String expected = K.alg1(template, request);
            String actual = K.alg3(template, request);

            assertEquals(expected, actual, () -> "\n" + template + " " + request + "\n");
        }
    }
}
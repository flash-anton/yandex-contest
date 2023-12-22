package contest53030;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.io.*;
import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Алг 4.0 ДЗ 2 (53030) Хеши для строк - B")
class BTest extends ContestChecker {
    private static final BiConsumer<InputStream, OutputStream> algorithm = (reader, writer) -> {
        try {
            B.alg(new BufferedReader(new InputStreamReader(reader)), new BufferedWriter(new OutputStreamWriter(writer)));
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
                zzz
                """, """
                1
                """);
    }

    @Test
    void example2() {
        check("""
                bcabcab
                """, """
                3
                """);
    }

    static class ArgProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            return Stream.of(
                    Arguments.of("1", "0"),
                    Arguments.of("1", "00"),
                    Arguments.of("1", "000"),
                    Arguments.of("2", "01"),
                    Arguments.of("2", "010"),
                    Arguments.of("2", "0101"),
                    Arguments.of("2", "01010"),
                    Arguments.of("2", "010101"),
                    Arguments.of("3", "012"),
                    Arguments.of("3", "0120"),
                    Arguments.of("3", "01201"),
                    Arguments.of("3", "012012"),
                    Arguments.of("3", "0120120"),
                    Arguments.of("3", "01201201"),
                    Arguments.of("3", "012012012")
            );
        }
    }

    @ParameterizedTest
    @ArgumentsSource(ArgProvider.class)
    void test(String expected, String req) {
        check(req, expected);
    }

    @Test
    public void stressTest() {
        ThreadLocalRandom r = ThreadLocalRandom.current();
        for (int i = 0; i < 100_000; i++) {
            int N = r.nextInt(1, 11);
            String S = str(N);

            String expected = B.alg1(S);
            String actual = B.alg2(S);

            assertEquals(expected, actual, "\n" + S + "\n");
        }
    }

    @Test
    void testTL() {
        for (int i = 0; i < 100; i++) {
            String S = str(5_000);

            assertAll(
                    () -> assertTimeout(Duration.ofSeconds(1), () -> B.alg1(S), "alg1() is too slow"),
                    () -> assertTimeout(Duration.ofSeconds(1), () -> B.alg2(S), "alg2() is too slow")
            );
        }
    }

    private static String str(int size) {
        byte[] s = new byte[size];
        for (int i = 0; i < size; i++) {
            s[i] = (byte) ('a' + ThreadLocalRandom.current().nextInt(0, 27));
        }
        return new String(s);
    }
}
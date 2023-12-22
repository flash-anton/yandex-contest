package contest53030;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiConsumer;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Алг 4.0 ДЗ 2 (53030) Хеши для строк - E")
class ETest extends ContestChecker {
    private static final BiConsumer<InputStream, OutputStream> algorithm = (reader, writer) -> {
        try {
            E.alg(new BufferedReader(new InputStreamReader(reader)), new BufferedWriter(new OutputStreamWriter(writer)));
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
                aaa
                """, """
                6
                """);
    }

    @Test
    void example2() {
        check("""
                aba
                """, """
                4
                """);
    }

    @Test
    public void stressTest_alg2() {
        ThreadLocalRandom r = ThreadLocalRandom.current();
        for (int i = 0; i < 10_000; i++) {
            int N = r.nextInt(1, 11);
            String S = str(N);

            String expected = E.alg1(S);
            String actual = E.alg2(S);

            assertEquals(expected, actual, S);
        }
    }

    @Test
    public void stressTest_alg3() {
        ThreadLocalRandom r = ThreadLocalRandom.current();
        for (int i = 0; i < 10_000; i++) {
            int N = r.nextInt(1, 11);
            String S = str(N);

            String expected = E.alg1(S);
            String actual = E.alg3(S);

            assertEquals(expected, actual, S);
        }
    }

    @Test
    void testTL_RandomLetter_alg2() {
        String S = str(100_000);
        assertTimeout(Duration.ofSeconds(3), () -> E.alg2(S));
    }

    @Test
    void testTL_RandomLetter_alg3() {
        String S = str(100_000);
        assertTimeout(Duration.ofSeconds(3), () -> E.alg3(S));
    }

    @Test
    void testTL_OneLetter_alg2() {
        String S = "a".repeat(100_000);
        assertTimeout(Duration.ofSeconds(3), () -> E.alg2(S));
    }

    @Test
    void testTL_OneLetter_alg3() {
        String S = "a".repeat(100_000);
        assertTimeout(Duration.ofSeconds(3), () -> E.alg3(S));
    }

    private static String str(int size) {
        byte[] s = new byte[size];
        for (int i = 0; i < size; i++) {
            s[i] = (byte) ('a' + ThreadLocalRandom.current().nextInt(0, 26));
        }
        return new String(s);
    }
}
package contest53030;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiConsumer;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Алг 4.0 ДЗ 2 (53030) Хеши для строк - C")
class CTest extends ContestChecker {
    private static final BiConsumer<InputStream, OutputStream> algorithm = (reader, writer) -> {
        try {
            C.alg(new BufferedReader(new InputStreamReader(reader)), new BufferedWriter(new OutputStreamWriter(writer)));
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
                abracadabra
                """, """
                0 0 0 1 0 1 0 4 0 0 1\s
                """);
    }

    @Test
    public void stressTest() {
        ThreadLocalRandom r = ThreadLocalRandom.current();
        for (int i = 0; i < 100_000; i++) {
            int N = r.nextInt(0, 11);
            String S = str(N);

            String expected = C.alg1(S);
            String actual2 = C.alg2(S);
            String actual3 = C.alg3(S);
            String actual4 = C.alg4(S);

            assertAll(
                    () -> assertEquals(expected, actual2, "\n" + S + "\n"),
                    () -> assertEquals(expected, actual3, "\n" + S + "\n"),
                    () -> assertEquals(expected, actual4, "\n" + S + "\n")
            );
        }
    }

    @Test
    void testTL() {
        for (int i = 0; i < 5; i++) {
            String S = str(1_000_000);
            assertAll(
                    () -> assertTimeout(Duration.ofSeconds(2), () -> C.alg2(S), "alg2() is too slow"),
                    () -> assertTimeout(Duration.ofSeconds(2), () -> C.alg3(S), "alg3() is too slow"),
                    () -> assertTimeout(Duration.ofSeconds(2), () -> C.alg4(S), "alg4() is too slow")
            );
        }
    }

    @Test
    void testCompareTL() {
        for (int i = 0; i < 5; i++) {
            String S = str(1_000_000);

            long t2 = duration(() -> C.alg2(S));
            long t4 = duration(() -> C.alg4(S));

            assertTrue(t2 > t4, "duration alg2() < alg4(): " + t2 + " < " + t4);
        }
    }

    private static String str(int size) {
        byte[] s = new byte[size];
        for (int i = 0; i < size; i++) {
            s[i] = (byte) ('a' + ThreadLocalRandom.current().nextInt(0, 27));
        }
        return new String(s);
    }

    static long duration(Runnable r) {
        long t = System.currentTimeMillis();
        r.run();
        return System.currentTimeMillis() - t;
    }
}
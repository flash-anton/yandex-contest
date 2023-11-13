package contest.algorithms.training4.contest53030;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiConsumer;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Алг 4.0 ДЗ 2 (53030) Хеши для строк - A")
class ATest extends ContestChecker {
    private static final BiConsumer<InputStream, OutputStream> algorithm = (reader, writer) -> {
        try {
            A.alg(new BufferedReader(new InputStreamReader(reader)), new BufferedWriter(new OutputStreamWriter(writer)));
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
                acabaca
                3
                4 3 2
                3 4 0
                2 0 1
                """, """
                no
                yes
                no
                """);
    }

    @Test
    void example2() {
        check("""
                caeabaeadedcbdcdccec
                10
                13 4 3
                2 12 15
                10 1 3
                3 8 15
                13 5 6
                7 2 6
                9 8 8
                19 0 0
                19 0 0
                6 7 13
                """, """
                no
                no
                no
                no
                no
                no
                yes
                yes
                yes
                no
                """);
    }

    @Test
    public void stressTest() {
        ThreadLocalRandom r = ThreadLocalRandom.current();
        for (int i = 0; i < 100_000; i++) {
            int N = r.nextInt(2, 11);
            String S = str(N);
            int Q = r.nextInt(1, 11);
            String[] requests = new String[Q];
            for (int j = 0; j < Q; j++) {
                int L = r.nextInt(1, N);
                int A = r.nextInt(1, (N - L) + 1);
                int B = r.nextInt(1, (N - L) + 1);
                requests[j] = String.format("%d %d %d", L, A, B);
            }

            String msg = "\n" + S + "\n" + Q + "\n" + String.join("\n", requests);

            String expected = A.alg1(S, Q, requests);
            String actual1 = A.alg2(S, Q, requests);

            String S2 = 'a' + S.substring(1);
            String actual2 = A.alg3(S, S2, Q, requests);

            assertAll(
                    () -> assertEquals(expected, actual1, msg),
                    () -> assertEquals(expected, actual2, msg)
            );
        }
    }

    @Test
    void testTL() {
        ThreadLocalRandom r = ThreadLocalRandom.current();
        for (int i = 0; i < 1; i++) {
            int N = 200_000;
            String S = str(N);
            int Q = 200_000;
            String[] requests = new String[Q];
            for (int j = 0; j < Q; j++) {
                int L = r.nextInt(1, N + 1);
                int A = r.nextInt(0, (N - L) + 1);
                int B = r.nextInt(0, (N - L) + 1);
                requests[j] = String.format("%d %d %d", L, A, B);
            }

            assertAll(
                    () -> assertTimeout(Duration.ofSeconds(15), () -> A.alg1(S, Q, requests), "alg1() is too slow"),
                    () -> assertTimeout(Duration.ofSeconds(15), () -> A.alg2(S, Q, requests), "alg2() is too slow"),
                    () -> assertTimeout(Duration.ofSeconds(15), () -> A.alg3(S, S, Q, requests), "alg3() is too slow")
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
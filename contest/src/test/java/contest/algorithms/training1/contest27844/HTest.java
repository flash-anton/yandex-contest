package contest.algorithms.training1.contest27844;

import common.ContestChecker;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiConsumer;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
                4 11
                802
                743
                457
                539
                """, """
                200
                """);
    }

    @Test
    void stressTest() {
        ThreadLocalRandom r = ThreadLocalRandom.current();
        int nkMin = 1;
        int nkMax = 21;
        int lMin = 100;
        int lMax = 201;
        for (int i = 0; i < 100_000; i++) {
            int n = r.nextInt(nkMin, nkMax);
            int k = r.nextInt(nkMin, nkMax);
            int[] l = r.ints(n, lMin, lMax).toArray();

            int expected = H.slow(k, l);
            int actual = H.alg(k, l);

            assertEquals(expected, actual, () -> {
                StringBuilder sb = new StringBuilder();
                sb.append(n).append(" ").append(k).append("\n");
                for (int L : l) {
                    sb.append(L).append("\n");
                }
                return sb.toString();
            });
        }
    }
}
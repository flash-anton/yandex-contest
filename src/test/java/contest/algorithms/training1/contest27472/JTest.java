package contest.algorithms.training1.contest27472;

import common.ContestChecker;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.function.BiConsumer;

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
                3
                440
                220 closer
                300 further
                """, """
                30.0 260.0
                """);
    }

    @Test
    void example2() {
        check("""
                4
                554
                880 further
                440 closer
                622 closer
                """, """
                531.0 660.0
                """);
    }
}
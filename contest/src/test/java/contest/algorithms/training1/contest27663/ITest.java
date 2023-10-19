package contest.algorithms.training1.contest27663;

import common.ContestChecker;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.function.BiConsumer;

class ITest extends ContestChecker {
    private static final BiConsumer<InputStream, OutputStream> algorithm = (reader, writer) -> {
        try {
            I.alg(new BufferedReader(new InputStreamReader(reader)), new BufferedWriter(new OutputStreamWriter(writer)));
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
                3
                Russian
                English
                Japanese
                2
                Russian
                English
                1
                English
                """, """
                1
                English
                3
                Russian
                Japanese
                English
                """);
    }
}
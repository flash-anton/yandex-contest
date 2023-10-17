package contest.algorithms.training1.contest27665;

import common.ContestChecker;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.function.BiConsumer;

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
                one two one tho three
                """, """
                0 0 1 0 0
                """);
    }

    @Test
    void example2() {
        check("""
                She sells sea shells on the sea shore;
                The shells that she sells are sea shells I'm sure.
                So if she sells sea shells on the sea shore,
                I'm sure that the shells are sea shore shells.
                                
                """, """
                0 0 0 0 0 0 1 0 0 1 0 0 1 0 2 2 0 0 0 0 1 2 3 3 1 1 4 0 1 0 1 2 4 1 5 0 0
                """);
    }

    @Test
    void example3() {
        check("""
                aba aba; aba @?"
                """, """
                0 0 1 0
                """);
    }
}
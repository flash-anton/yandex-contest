package coderun.medium;

import org.example.ContestChecker;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.function.BiConsumer;

class S5Test extends ContestChecker {
    private static final BiConsumer<InputStream, OutputStream> algorithm = (reader, writer) -> {
        try {
            S5.alg(new BufferedReader(new InputStreamReader(reader)), new BufferedWriter(new OutputStreamWriter(writer)));
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
                5
                35
                40
                101
                59
                63
                """, """
                235
                0 1
                5
                """);
    }

    @Test
    void test1() {
        check("""
                5
                35
                40
                101
                59
                3
                """, """
                179
                0 1
                4
                """);
    }
}
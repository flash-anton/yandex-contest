package org.example.contest50834;

import org.example.ContestChecker;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.function.BiConsumer;

class ETest extends ContestChecker {
    private static final BiConsumer<InputStream, OutputStream> algorithm = (reader, writer) -> {
        try {
            new E(reader, writer).execute();
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
                4 2
                1 5
                3 1 1
                1 1 2
                2 2 1 2
                3 2 1 2
                """, """
                -1 2 1 2
                """);
    }
}
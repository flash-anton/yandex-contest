package org.example.contest28412;

import org.example.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.function.BiConsumer;

@DisplayName("28412-F")
public class TestF extends ContestChecker {
    @Override
    public BiConsumer<InputStream, OutputStream> getTaskAlgorithm() {
        return (reader, writer) -> {
            try {
                F.Main(reader, writer);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    @Test
    void example1() {
        check("""
                3
                -1 -1 1 1
                -1 0 1 1
                -1 -1 0 1
                """, """
                1
                1
                2
                """);
    }

    @Test
    void example2() {
        check("""
                4
                -3 -3 3 3
                0 0 0 0
                -5 0 4 0
                -1 -4 1 3
                """, """
                24
                0
                0
                14
                """);
    }
}

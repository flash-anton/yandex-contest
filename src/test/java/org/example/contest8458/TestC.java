package org.example.contest8458;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.function.BiConsumer;

@DisplayName("8458-C")
public class TestC extends ContestChecker {
    @Override
    public BiConsumer<InputStream, OutputStream> getTaskAlgorithm() {
        return (reader, writer) -> {
            try {
                C.Main(reader, writer);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    @Test
    void example1() {
        check("""
                5
                2
                4
                8
                8
                8
                """, """
                2
                4
                8
                """);
    }

    @Test
    void example2() {
        check("""
                5
                2
                2
                2
                8
                8
                """, """
                2
                8
                """);
    }

    @Test
    void test1() {
        check("""
                8
                -2147483648
                -2147483648
                -2
                -2
                2
                8
                2147483647
                2147483647
                """, """
                -2147483648
                -2
                2
                8
                2147483647
                """);
    }
}

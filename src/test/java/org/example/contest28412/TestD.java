package org.example.contest28412;

import org.example.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.function.BiConsumer;

@DisplayName("28412-D")
public class TestD extends ContestChecker {
    @Override
    public BiConsumer<InputStream, OutputStream> getTaskAlgorithm() {
        return (reader, writer) -> {
            try {
                D.Main(reader, writer);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    @Test
    void example1() {
        check("""
                1
                """, """
                1
                """);
    }

    @Test
    void example2() {
        check("""
                2
                """, """
                0
                """);
    }

    @Test
    void example3() {
        check("""
                3
                """, """
                3
                """);
    }

    @Test
    void example4() {
        check("""
                4
                """, """
                16
                """);
    }

    @Test
    void example5() {
        check("""
                2021
                """, """
                113707034
                """);
    }

    @Test
    void example6() {
        check("""
                5000
                """, """
                855711688
                """);
    }
}

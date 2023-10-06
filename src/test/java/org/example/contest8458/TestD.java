package org.example.contest8458;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.function.BiConsumer;

@DisplayName("8458-D")
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
                2
                """, """
                (())
                ()()
                """);
    }

    @Test
    void example2() {
        check("""
                3
                """, """
                ((()))
                (()())
                (())()
                ()(())
                ()()()
                """);
    }

    @Test
    void test1() {
        check("""
                0
                """, """
                """);
    }

    @Test
    void test2() {
        check("""
                1
                """, """
                ()
                """);
    }
}

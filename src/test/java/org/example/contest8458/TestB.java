package org.example.contest8458;

import org.example.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.function.BiConsumer;

@DisplayName("8458-B")
public class TestB extends ContestChecker {
    @Override
    public BiConsumer<InputStream, OutputStream> getTaskAlgorithm() {
        return (reader, writer) -> {
            try {
                B.Main(reader, writer);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    @Test
    void example1() {
        check("""
                5
                1
                0
                1
                0
                1
                """, "1");
    }

    @Test
    void test1() {
        check("""
                0
                """, "0");
    }

    @Test
    void test2() {
        check("""
                1
                0
                """, "0");
    }

    @Test
    void test3() {
        check("""
                1
                1
                """, "1");
    }

    @Test
    void test4() {
        check("""
                5
                1
                1
                1
                0
                1
                """, "3");
    }

    @Test
    void test5() {
        check("""
                5
                1
                0
                1
                1
                1
                """, "3");
    }

    @Test
    void test6() {
        check("""
                6
                1
                0
                1
                1
                0
                1
                """, "2");
    }
}

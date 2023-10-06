package org.example.contest8458;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.function.BiConsumer;

@DisplayName("8458-E")
public class TestE extends ContestChecker {
    @Override
    public BiConsumer<InputStream, OutputStream> getTaskAlgorithm() {
        return (reader, writer) -> {
            try {
                E.Main(reader, writer);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    @Test
    void example1() {
        check("""
                qiu
                iuq
                """, """
                1
                """);
    }

    @Test
    void example2() {
        check("""
                zprl
                zprc
                """, """
                0
                """);
    }

    @Test
    void test1() {
        check("""
                                
                                
                """, """
                1
                """);
    }

    @Test
    void test2() {
        check("""
                                
                q
                """, """
                0
                """);
    }

    @Test
    void test3() {
        check("""
                q
                                
                """, """
                0
                """);
    }
}

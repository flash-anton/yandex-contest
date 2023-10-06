package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import common.ContestChecker;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.function.BiConsumer;

@DisplayName("contestNumber-task")
public class TestTemplate extends ContestChecker {
    @Override
    public BiConsumer<InputStream, OutputStream> getTaskAlgorithm() {
        return (reader, writer) -> {
            try {
                Template.Main(reader, writer);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    @Test
    void example1() {
        check("""
                123
                """, """
                123
                """);
    }

    @Test
    void test1() {
        check("""
                  
                """, """
                                
                """);
    }
}

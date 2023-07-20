package org.example.contest28412;

import org.example.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.function.BiConsumer;

@DisplayName("28412-E")
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
                4
                =>>?
                ^??>
                ^??^
                ^^^?
                """, """
                Yes
                4 1 2 3 4
                2 2 4
                1 3
                1 4
                """);
    }

    @Test
    void example2() {
        check("""
                2
                !?
                ?=
                """, """
                No
                """);
    }
}

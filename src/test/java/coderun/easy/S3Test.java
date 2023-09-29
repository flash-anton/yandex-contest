package coderun.easy;

import org.example.ContestChecker;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.function.BiConsumer;

class S3Test extends ContestChecker {
    private static final BiConsumer<InputStream, OutputStream> algorithm = (reader, writer) -> {
        try {
            S3.alg(new BufferedReader(new InputStreamReader(reader)), new BufferedWriter(new OutputStreamWriter(writer)));
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
                5 5
                9 9 9 9 9
                3 0 0 0 0
                9 9 9 9 9
                6 6 6 6 8
                9 9 9 9 9
                """, """
                74
                D D R R R R D D
                """);
    }

    @Test
    void test1() {
        check("""
                1 2
                1 1
                """, """
                2
                R
                """);
    }

    @Test
    void test2() {
        check("""
                2 1
                1
                1
                """, """
                2
                D
                """);
    }

    @Test
    void test3() {
        check("""
                1 1
                1
                """, """
                1
                
                """);
    }

    @Test
    void test4() {
        checkException("""
                2 2
                1 101
                1 1
                """,
                new IllegalArgumentException("Число 101 в клетке [0,1] не соответствует условию"));
    }

    @Test
    void test5() {
        checkException("""
                2 2
                1 1
                1 -1
                """,
                new IllegalArgumentException("Число -1 в клетке [1,1] не соответствует условию"));
    }
}
package coderun.easy;

import org.example.ContestChecker;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.function.BiConsumer;

class S6Test extends ContestChecker {
    private static final BiConsumer<InputStream, OutputStream> algorithm = (reader, writer) -> {
        try {
            S6.alg(new BufferedReader(new InputStreamReader(reader)), new BufferedWriter(new OutputStreamWriter(writer)));
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
                3
                1 2 3
                3
                2 3 1
                """, """
                2 3
                """);
    }

    @Test
    void example2() {
        check("""
                3
                1 2 3
                3
                3 2 1
                """, """
                1
                """);
    }

    @Test
    void test1() {
        check("""
                3
                1 2 3
                3
                4 4 4
                """, """
                
                """);
    }

    @Test
    void test2() {
        check("""
                3
                1 2 3
                1
                2
                """, """
                2
                """);
    }

    @Test
    void test4() {
        check("""
                1
                1
                1
                2
                """, """
                
                """);
    }

    @Test
    void test5() {
        check("""
                1
                2
                3
                1 2 3
                """, """
                2
                """);
    }

    @Test
    void test6() {
        check("""
                8
                1 2 3 1 2 4 3 5
                3
                2 4 1
                """, """
                2 1
                """);
    }

    @Test
    void test7() {
        check("""
                6
                1 2 3 4 5 6
                6
                1 2 3 7 5 6
                """, """
                1 2 3 5 6
                """);
    }

    @Test
    void test8() {
        check("""
                5
                1 2 3 1 1
                5
                1 2 3 2 2
                """, """
                1 2 3
                """);
    }

    @Test
    void test9() {
        check("""
                3
                10000 1 2
                3
                1 2 10000
                """, """
                1 2
                """);
    }
}
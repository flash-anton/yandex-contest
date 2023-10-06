package coderun.easy;

import common.ContestChecker;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.function.BiConsumer;

class S4Test extends ContestChecker {
    private static final BiConsumer<InputStream, OutputStream> algorithm = (reader, writer) -> {
        try {
            S4.alg(new BufferedReader(new InputStreamReader(reader)), new BufferedWriter(new OutputStreamWriter(writer)));
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
                3 2
                """, """
                1
                """);
    }

    @Test
    void example2() {
        check("""
                31 34
                """, """
                293930
                """);
    }

    @Test
    void test1() {
        check("""
                2 3
                """, """
                1
                """);
    }

    @Test
    void test2() {
        check("""
                4 4
                """, """
                2
                """);
    }

    @Test
    void test3() {
        check("""
                6 5
                """, """
                3
                """);
    }

    @Test
    void test4() {
        check("""
                9 8
                """, """
                10
                """);
    }

    @Test
    void test5() {
        check("""
                8 9
                """, """
                10
                """);
    }

    @Test
    void test6() {
        check("""
                49 49
                """, """
                601080390
                """);
    }

    @Test
    void test7() {
        check("""
                1 1
                """, """
                1
                """);
    }

    @Test
    void test8() {
        check("""
                2 1
                """, """
                0
                """);
    }
}
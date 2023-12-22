package contest27794;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.function.BiConsumer;

@DisplayName("Алг 1.0 ДЗ 5 (27794) Префиксные суммы и два указателя - I")
class ITest extends ContestChecker {
    private static final BiConsumer<InputStream, OutputStream> algorithm = (reader, writer) -> {
        try {
            I.alg(new BufferedReader(new InputStreamReader(reader)), new BufferedWriter(new OutputStreamWriter(writer)));
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
                2
                zabacabab
                """, """
                5
                """);
    }

    @Test
    void example2() {
        check("""
                2
                abc
                """, """
                0
                """);
    }

    @Test
    void test7() {
        check("""
                7
                abbabbababbabbababbababbabbababbabbababbababbabbababbababbabbababbabbababbababbabbababbab
                """, """
                40
                """);
    }
}
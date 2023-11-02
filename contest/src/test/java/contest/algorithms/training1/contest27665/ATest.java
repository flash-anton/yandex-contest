package contest.algorithms.training1.contest27665;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.function.BiConsumer;

@DisplayName("Алг 1.0 ДЗ 4 (27665) Словари и сортировка подсчетом - A")
class ATest extends ContestChecker {
    private static final BiConsumer<InputStream, OutputStream> algorithm = (reader, writer) -> {
        try {
            A.alg(new BufferedReader(new InputStreamReader(reader)), new BufferedWriter(new OutputStreamWriter(writer)));
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
                Hello Hi
                Bye Goodbye
                List Array
                Goodbye
                """, """
                Bye
                """);
    }

    @Test
    void example2() {
        check("""
                1
                beep Car
                Car
                """, """
                beep
                """);
    }

    @Test
    void example3() {
        check("""
                2
                Ololo Ololo
                Numbers 1234567890
                Numbers
                """, """
                1234567890
                """);
    }
}
package contest51022;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.function.BiConsumer;

@DisplayName("Стаж бэк июл-дек 2023 (51022) - A")
class BTest extends ContestChecker {
    private static final BiConsumer<InputStream, OutputStream> algorithm = (reader, writer) -> {
        try {
            B.alg(reader, new BufferedWriter(new OutputStreamWriter(writer)));
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
                2 5 10
                1 2
                1 2 3 4 5
                1 A 3
                1 A 4
                1 A 5
                1 A 6
                1 A 7
                -1 A 1
                1 B 7
                -1 A 6
                -1 B 1
                1 A 7
                """, """
                2 1 0 1 2 3 2 1 0 1\s
                """);
    }

    @Test
    void example2() {
        check("""
                3 3 5
                1000 2000 1001
                1001 2001 1000
                1 A 100000
                -1 B 2001
                1 B 2000
                1 B 100001
                1 A 1
                """, """
                3 2 1 2 3\s
                """);
    }

    @Test
    void example3() {
        check("""
                3 3 20
                1 6 7
                2 4 5
                1 A 2
                1 B 1
                1 B 8
                1 B 5
                1 A 3
                1 A 2
                1 B 10
                1 A 9
                1 A 8
                1 B 7
                -1 A 1
                -1 B 5
                -1 B 5
                -1 B 4
                -1 A 6
                -1 A 8
                -1 A 2
                -1 B 8
                -1 B 10
                -1 A 2
                """, """
                5 4 5 6 7 8 9 10 9 8 9 8 7 6 5 6 5 4 3 4\s
                """);
    }
}
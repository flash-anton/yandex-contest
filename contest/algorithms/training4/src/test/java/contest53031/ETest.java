package contest53031;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.function.BiConsumer;

@DisplayName("Алг 4.0 ДЗ 3 (53031) Кратчайшие пути в графах - E")
class ETest extends ContestChecker {
    private static final BiConsumer<InputStream, OutputStream> algorithm = (reader, writer) -> {
        try {
            E.alg(new BufferedReader(new InputStreamReader(reader)), new BufferedWriter(new OutputStreamWriter(writer)));
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
                4
                1 1
                10 30
                5 40
                1 10
                1 2 300
                1 3 400
                2 4 100
                """, """
                31.0000000000
                4 2 1
                """);
    }

    @Test
    void example2() {
        check("""
                3
                1 1
                0 10
                0 55
                1 2 100
                2 3 10
                """, """
                3.0000000000
                2 3 1
                """);
    }
}
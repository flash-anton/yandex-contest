package contest27794;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.function.BiConsumer;

@DisplayName("Алг 1.0 ДЗ 5 (27794) Префиксные суммы и два указателя - F")
class FTest extends ContestChecker {
    private static final BiConsumer<InputStream, OutputStream> algorithm = (reader, writer) -> {
        try {
            F.alg(new BufferedReader(new InputStreamReader(reader)), new BufferedWriter(new OutputStreamWriter(writer)));
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
                1
                800
                1
                800 1000
                """, """
                1000
                """);
    }

    @Test
    void example2() {
        check("""
                3
                1 2 3
                4
                1 10
                1 5
                10 7
                2 3
                """, """
                13
                """);
    }
}
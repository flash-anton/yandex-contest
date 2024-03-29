package contest27393;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.function.BiConsumer;

@DisplayName("Алг 1.0 ДЗ 1 (27393) Сложность, тестирование, особые случаи - B")
class BTest extends ContestChecker {
    private static final BiConsumer<InputStream, OutputStream> algorithm = (reader, writer) -> {
        try {
            B.alg(new BufferedReader(new InputStreamReader(reader)), new BufferedWriter(new OutputStreamWriter(writer)));
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
                4
                5
                                
                """, """
                YES
                """);
    }

    @Test
    void example2() {
        check("""
                3
                5
                4
                                
                                
                """, """
                YES
                """);
    }

    @Test
    void example3() {
        check("""
                4
                5
                3
                """, """
                YES
                """);
    }
}
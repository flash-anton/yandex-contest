package contest53032;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.time.Duration;
import java.util.function.BiConsumer;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTimeout;

@DisplayName("Алг 4.0 ДЗ 4 (53032) Перебор и методы его оптимизации - B")
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
                8
                """, """
                92
                """);
    }

    @Test
    void test1() {
        check("""
                5
                """, """
                10
                """);
    }

    @Test
    void testTL() {
        for (int i = 0; i < 1; i++) {
            assertAll(
                    () -> assertTimeout(Duration.ofSeconds(1), () -> B.alg1(10), "alg1() is too slow")
            );
        }
    }
}
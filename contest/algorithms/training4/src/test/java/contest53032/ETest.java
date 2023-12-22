package contest53032;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.time.Duration;
import java.util.function.BiConsumer;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTimeout;

@DisplayName("Алг 4.0 ДЗ 4 (53032) Перебор и методы его оптимизации - E")
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
                """, """
                (())
                ([])
                ()()
                ()[]
                [()]
                [[]]
                []()
                [][]
                """);
    }

    @Test
    void testTL() {
        for (int i = 0; i < 10; i++) {
            int n = 16;
            assertAll(
                    () -> assertTimeout(Duration.ofSeconds(2), () -> E.alg1(n), "alg1() is too slow")
            );
        }
    }
}
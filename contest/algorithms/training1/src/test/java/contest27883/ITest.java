package contest27883;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.function.BiConsumer;

@DisplayName("Алг 1.0 ДЗ 7 (27883) - I")
class ITest extends ContestChecker {
    private static final BiConsumer<InputStream, OutputStream> algorithm = (reader, writer) -> {
        try {
            I.alg(reader, new BufferedWriter(new OutputStreamWriter(writer)));
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
                2 2
                2 20:00 1 10:00
                1 08:00 2 21:00
                """, """
                3
                """);
    }

    @Test
    void example2() {
        check("""
                2 2
                1 09:00 2 20:00
                2 20:00 1 09:00
                """, """
                1
                """);
    }

    @Test
    void example3() {
        check("""
                3 4
                3 03:52 1 08:50
                1 18:28 3 21:53
                2 03:58 3 09:00
                3 14:59 2 21:13
                """, """
                2
                """);
    }

    @Test
    void example12() {
        check("""
                50045 10
                2591 20:00 41620 14:12
                7960 02:01 22983 16:28
                7960 18:31 28152 20:00
                41620 21:35 7960 09:17
                28152 14:30 7960 07:46
                22983 09:26 28152 16:48
                20453 16:39 7960 01:21
                7960 01:13 28152 09:49
                28152 19:33 20453 16:09
                28152 10:48 2591 22:59
                """, """
                9
                """);
    }
}
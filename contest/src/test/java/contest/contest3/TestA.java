package contest.contest3;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.function.BiConsumer;

@DisplayName("3-A")
public class TestA extends ContestChecker {
    @Override
    public BiConsumer<InputStream, OutputStream> getTaskAlgorithm() {
        return (reader, writer) -> {
            try {
                A.Main(reader, writer);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    @Test
    void example1() {
        check("2 2", "4");
    }

    @Test
    void example2() {
        check("57 43", "100");
    }

    @Test
    void example3() {
        check("123456789 673243342", "796700131");
    }

    @Test
    void test1() {
        check("-2000000000 -2000000000", "-4000000000");
    }

    @Test
    void test2() {
        check("2000000000 2000000000", "4000000000");
    }
}

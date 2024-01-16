package contest36783;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.function.BiConsumer;

@DisplayName("Interview training 36783 - M")
class MTest extends ContestChecker {
    private static final BiConsumer<InputStream, OutputStream> algorithm = (reader, writer) -> {
        try {
            M.alg(reader, new BufferedWriter(new OutputStreamWriter(writer)));
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
                6
                9
                set 0 3
                set 1 8
                begin_new_era 6000
                get 0 0
                get 1 0
                set 0 9
                begin_new_era 1000000
                get 0 6000
                get 0 0
                """, """
                3
                8
                9
                3
                """);
    }

    @Test
    void example2() {
        check("""
                1
                12
                set 0 1
                set 0 2
                begin_new_era 1000
                set 0 4
                set 0 100
                begin_new_era 666
                set 0 7
                set 0 42
                begin_new_era 424242
                get 0 0
                get 0 666
                get 0 1000
                """, """
                2
                42
                100
                """);
    }
}
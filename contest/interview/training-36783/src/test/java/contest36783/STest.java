package contest36783;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.function.BiConsumer;

@DisplayName("Interview training 36783 - S")
class STest extends ContestChecker {
    private static final BiConsumer<InputStream, OutputStream> algorithm = (reader, writer) -> {
        try {
            S.alg(reader, new BufferedWriter(new OutputStreamWriter(writer)));
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
                127.0.0.1
                """, """
                IPv4
                """);
    }

    @Test
    void example2() {
        check("""
                256.0.0.0
                """, """
                Error
                """);
    }

    @Test
    void example3() {
        check("""
                2001:0db8:85a3:00:0:8a2e:0370:7334
                """, """
                IPv6
                """);
    }

    @Test
    void example4() {
        check("""
                2001:0db8:85a3:0:030:8a2e:0370:7334
                """, """
                IPv6
                """);
    }
}
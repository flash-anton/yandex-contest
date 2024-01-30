package contest28069;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.function.BiConsumer;

@DisplayName("Алг 1.0 ДЗ 8 (28069) - I")
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
                9
                Alexei Peter_I
                Anna Peter_I
                Elizabeth Peter_I
                Peter_II Alexei
                Peter_III Anna
                Paul_I Peter_III
                Alexander_I Paul_I
                Nicholaus_I Paul_I
                """, """
                Alexander_I 0
                Alexei 1
                Anna 4
                Elizabeth 0
                Nicholaus_I 0
                Paul_I 2
                Peter_I 8
                Peter_II 0
                Peter_III 3
                """);
    }
}
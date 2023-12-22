package contest27393;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.function.BiConsumer;

@DisplayName("Алг 1.0 ДЗ 1 (27393) Сложность, тестирование, особые случаи - C")
class CTest extends ContestChecker {
    private static final BiConsumer<InputStream, OutputStream> algorithm = (reader, writer) -> {
        try {
            C.alg(new BufferedReader(new InputStreamReader(reader)), new BufferedWriter(new OutputStreamWriter(writer)));
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
                8(495)430-23-97
                +7-4-9-5-43-023-97
                4-3-0-2-3-9-7
                8-495-430
                """, """
                YES
                YES
                NO
                """);
    }

    @Test
    void example2() {
        check("""
                86406361642
                83341994118
                86406361642
                83341994118
                """, """
                NO
                YES
                NO
                """);
    }

    @Test
    void example3() {
        check("""
                +78047952807
                +78047952807
                +76147514928
                88047952807
                """, """
                YES
                NO
                YES
                """);
    }
}
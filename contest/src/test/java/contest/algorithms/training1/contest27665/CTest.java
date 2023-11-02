package contest.algorithms.training1.contest27665;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.function.BiConsumer;

@DisplayName("Алг 1.0 ДЗ 4 (27665) Словари и сортировка подсчетом - C")
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
                apple orange banana banana orange
                """, """
                banana
                """);
    }

    @Test
    void example2() {
        check("""
                oh you touch my tralala mmm my ding ding dong
                """, """
                ding
                """);
    }

    @Test
    void example3() {
        check("""
                q w e r t y u i o p
                a s d f g h j k l
                z x c v b n m
                """, """
                a
                """);
    }
}
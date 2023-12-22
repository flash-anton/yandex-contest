package contest53027;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.*;
import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Алг 4.0 ДЗ 0 (53027) Разминка - D")
class DTest extends ContestChecker {
    private static final BiConsumer<InputStream, OutputStream> algorithm = (reader, writer) -> {
        try {
            D.alg(new BufferedReader(new InputStreamReader(reader)), new BufferedWriter(new OutputStreamWriter(writer)));
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
                dusty
                study
                """, """
                YES
                """);
    }

    @Test
    void example2() {
        check("""
                rat
                bat
                """, """
                NO
                """);
    }

    @ParameterizedTest
    @ValueSource(strings = {"qwe\newq", "aaab\nbaaa", "aab\naba"})
    void testYES(String args) {
        check(args, "YES");
    }

    @ParameterizedTest
    @ValueSource(strings = {"qwe\newqq", "qwee\newq"})
    void testNO(String args) {
        check(args, "NO");
    }

    @Test
    void testTL() {
        ThreadLocalRandom r = ThreadLocalRandom.current();
        for (int i = 0; i < 100; i++) {
            int aLen = r.nextInt(99_990, 100_001);
            int bLen = r.nextInt(99_990, 100_001);
            String a = r.ints(aLen, 'a', 'z' + 1).mapToObj(Character::toString).collect(Collectors.joining());
            String b = r.ints(bLen, 'a', 'z' + 1).mapToObj(Character::toString).collect(Collectors.joining());

            assertTimeout(Duration.ofSeconds(1), () -> D.alg(a, b), "alg() is too slow");
        }
    }
}
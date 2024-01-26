package contest27883;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.function.BiConsumer;

@DisplayName("Алг 1.0 ДЗ 7 (27883) - J")
class JTest extends ContestChecker {
    private static final BiConsumer<InputStream, OutputStream> algorithm = (reader, writer) -> {
        try {
            J.alg(reader, new BufferedWriter(new OutputStreamWriter(writer)));
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
                1 10 10
                0 0 0 10 10 10
                """, """
                YES
                1
                1\s
                """);
    }

    @Test
    void example2() {
        check("""
                2 10 10
                0 0 0 10 5 5
                0 5 5 10 10 10
                """, """
                NO
                """);
    }

    @Test
    void example10() {
        check("""
                75 40 41
                0 0 30 8 41 42
                7 0 69 40 41 79
                1 11 2 3 27 8
                37 26 52 39 38 54
                0 14 8 1 15 25
                0 13 6 1 14 23
                36 5 47 38 7 49
                31 27 42 35 35 50
                28 23 67 36 27 69
                0 15 3 1 41 19
                29 0 45 31 22 57
                1 15 12 3 41 13
                1 3 10 3 15 22
                2 36 23 4 40 27
                1 2 22 3 6 24
                12 0 1 23 38 23
                12 39 11 40 41 14
                5 35 23 7 39 25
                23 0 11 30 38 25
                35 31 2 37 35 4
                0 28 44 1 40 57
                2 28 48 3 41 56
                22 0 52 26 4 56
                8 15 25 9 41 44
                3 5 76 5 21 78
                1 28 51 2 29 62
                0 4 8 1 13 17
                1 29 43 2 41 62
                26 0 53 29 10 66
                1 0 43 3 28 59
                24 3 2 26 35 6
                5 26 67 15 40 69
                32 0 46 34 22 57
                8 0 23 9 15 38
                26 10 53 29 41 60
                9 0 31 25 41 41
                32 10 1 34 12 3
                1 8 1 3 10 3
                34 0 54 40 41 58
                2 0 70 7 41 75
                8 0 2 12 41 22
                11 17 27 21 29 31
                24 26 43 26 28 51
                1 0 62 2 41 73
                12 38 2 40 39 16
                3 28 50 4 41 60
                0 0 46 1 28 61
                30 32 2 32 36 4
                3 0 42 4 28 59
                36 2 44 38 4 52
                21 4 43 24 41 59
                2 37 76 6 39 78
                31 0 52 32 22 64
                0 0 4 1 4 23
                25 5 25 40 14 45
                25 0 29 40 5 41
                36 6 64 38 18 66
                0 40 44 1 41 65
                0 4 25 2 14 27
                29 22 50 34 41 66
                33 0 4 40 38 23
                6 0 49 19 41 67
                25 14 35 40 41 36
                30 0 7 33 38 20
                28 17 67 36 21 69
                3 0 2 8 41 19
                1 0 9 3 3 18
                37 21 42 39 25 48
                0 0 69 1 41 74
                19 4 51 21 41 62
                24 4 54 26 41 65
                1 29 6 3 37 10
                27 24 2 37 30 4
                4 0 48 6 41 63
                19 0 43 22 4 63
                """, """
                YES
                4
                2
                40
                44
                69
                """);
    }
}
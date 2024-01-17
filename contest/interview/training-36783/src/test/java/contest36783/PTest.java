package contest36783;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Arrays;
import java.util.Set;
import java.util.function.BiConsumer;

import static java.util.stream.Collectors.toSet;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Interview training 36783 - P")
class PTest extends ContestChecker {
    private static final BiConsumer<InputStream, OutputStream> algorithm = (reader, writer) -> {
        try {
            P.alg(reader, new BufferedWriter(new OutputStreamWriter(writer)));
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
        String question = """
                10 0
                1 2
                3 4
                5 6
                7 -1
                8 -1
                -1 -1
                9 -1
                -1 -1
                -1 -1
                -1 -1
                """.strip();
        String expectedAnswer = """
                5 8 9 1 0 7 3 2 6
                """.strip();

        ByteArrayOutputStream writer = new ByteArrayOutputStream();
        getTaskAlgorithm().accept(new ByteArrayInputStream(question.getBytes()), writer);
        String actualAnswer = writer.toString().strip();

        Set<Integer> expected = Arrays.stream(expectedAnswer.split(" ")).map(Integer::valueOf).collect(toSet());
        Set<Integer> actual = Arrays.stream(actualAnswer.split(" ")).map(Integer::valueOf).collect(toSet());

        assertEquals(expected, actual);
    }
}
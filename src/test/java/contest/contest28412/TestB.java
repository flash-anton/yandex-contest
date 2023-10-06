package contest.contest28412;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.function.BiConsumer;

@DisplayName("28412-B")
public class TestB extends ContestChecker {
    @Override
    public BiConsumer<InputStream, OutputStream> getTaskAlgorithm() {
        return (reader, writer) -> {
            try {
                B.Main(reader, writer);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    @Test
    void example1() {
        check("""
                4
                ..._.#.
                .##_...
                .#._.##
                ..._...
                7
                2 left aisle
                3 right window
                2 left window
                3 left aisle
                1 right window
                2 right window
                1 right window
                """, """
                Passengers can take seats: 1B 1C
                .XX_.#.
                .##_...
                .#._.##
                ..._...
                Passengers can take seats: 2D 2E 2F
                .##_.#.
                .##_XXX
                .#._.##
                ..._...
                Passengers can take seats: 4A 4B
                .##_.#.
                .##_###
                .#._.##
                XX._...
                Cannot fulfill passengers requirements
                Passengers can take seats: 1F
                .##_.#X
                .##_###
                .#._.##
                ##._...
                Passengers can take seats: 4E 4F
                .##_.##
                .##_###
                .#._.##
                ##._.XX
                Cannot fulfill passengers requirements
                """);
    }
}

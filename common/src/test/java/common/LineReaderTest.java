package common;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import static common.LineReader.*;
import static org.junit.jupiter.api.Assertions.*;

class LineReaderTest {
    static class BR extends BufferedReader {
        public BR(String data) {
            super(new BufferedReader(new InputStreamReader(new ByteArrayInputStream(data.getBytes()))));
        }
    }

    @Test
    void test() throws IOException {
        int lines = 2;
        BR br = new BR("""
                1
                2 2
                3
                3
                4 4
                4 4
                                
                1777000777000777000
                           
                1777000777000777000
                2777000777000777000 2777000777000777000
                3777000777000777000
                3777000777000777000
                4777000777000777000 4777000777000777000
                4777000777000777000 4777000777000777000
                           
                9777000777000777000
                """);

        assertEquals(1, intFromLine(br));
        assertArrayEquals(new int[]{2, 2}, intsFromLine(br));
        assertArrayEquals(new int[]{3, 3}, intFromEachLine(br, lines));
        assertArrayEquals(new int[][]{{4, 4}, {4, 4}}, intsFromEachLine(br, lines));
        skipLine(br);
        assertThrows(NumberFormatException.class, () -> intFromLine(br));
        skipLine(br);
        assertEquals(1777000777000777000L, longFromLine(br));
        assertArrayEquals(new long[]{2777000777000777000L, 2777000777000777000L}, longsFromLine(br));
        assertArrayEquals(new long[]{3777000777000777000L, 3777000777000777000L}, longFromEachLine(br, lines));
        assertArrayEquals(new long[][]{{4777000777000777000L, 4777000777000777000L}, {4777000777000777000L, 4777000777000777000L}}, longsFromEachLine(br, lines));
        skipLine(br);
        assertThrows(NumberFormatException.class, () -> longFromLine(br));
    }
}
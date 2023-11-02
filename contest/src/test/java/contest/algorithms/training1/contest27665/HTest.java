package contest.algorithms.training1.contest27665;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Алг 1.0 ДЗ 4 (27665) Словари и сортировка подсчетом - H")
class HTest extends ContestChecker {
    private static final BiConsumer<InputStream, OutputStream> algorithm = (reader, writer) -> {
        try {
            H.alg(new BufferedReader(new InputStreamReader(reader)), new BufferedWriter(new OutputStreamWriter(writer)));
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
                4 11
                cAda
                AbrAcadAbRa
                """, """
                2
                """);
    }

    @Test
    public void stressTest() {
        int w = 3;
        int s = 5;
        for (int i = 0; i < 100_000; i++) {
            byte[] W = randomLetters(w);
            byte[] S = randomLetters(s);
            int c1 = H.alg1(W, S);
            int c2 = H.alg2(W, S);
            assertEquals(c1, c2, String.format("\n%d %d\n%s\n%s\n", w, s, new String(W), new String(S)));
        }
    }

    private byte[] randomLetters(int size) {
        int[] c = {'A', '[', 'a', '{'};
        ThreadLocalRandom r = ThreadLocalRandom.current();
        List<Integer> list = r.ints(size, c[0], c[1]).boxed().collect(Collectors.toList());
        list.addAll(r.ints(size, c[2], c[3]).boxed().collect(Collectors.toList()));
        Collections.shuffle(list, r);
        list = list.subList(0, size);

        byte[] byteArray = new byte[size];
        for (int i = 0; i < size; i++) {
            byteArray[i] = (byte) list.get(i).intValue();
        }
        return byteArray;
    }
}
package contest36783;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiConsumer;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Interview training 36783 - E")
class ETest extends ContestChecker {
    private static final BiConsumer<InputStream, OutputStream> algorithm = (reader, writer) -> {
        try {
            E.alg(reader, new BufferedWriter(new OutputStreamWriter(writer)));
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
                VIV
                """, """
                -1
                """);
    }

    @Test
    void example2() {
        check("""
                II
                """, """
                2
                """);
    }

    @Test
    void example3() {
        check("""
                MCMLXI
                """, """
                1961
                """);
    }

    @Test
    public void stressTest1() {
        ThreadLocalRandom r = ThreadLocalRandom.current();
        for (int i = 0; i < 100_000; i++) {
            int arabic = r.nextInt(1, 4000);
            String roman  = arabic2roman(arabic);

            String expected = "" + arabic;
            String actual = E.alg1(roman);

            assertEquals(expected, actual, () -> "\n" + roman + " " + arabic + "\n");
        }
    }

    @Test
    public void stressTest2() {
        ThreadLocalRandom r = ThreadLocalRandom.current();
        List<String> a = List.of("I", "V", "X", "L", "C", "D", "M");
        for (int i = 0; i < 100_000; i++) {
            StringBuilder roman = new StringBuilder();
            int len = r.nextInt(0, 16);
            while (len > 0) {
                int ind = r.nextInt(0, 7);
                int cnt = r.nextInt(0, len + 1);
                roman.append(a.get(ind).repeat(cnt));
                len -= cnt;
            }

            String actualArabic = E.alg1(roman.toString());

            if (!actualArabic.equals("-1")) {
                String romanActual = arabic2roman(Integer.parseInt(actualArabic));
                assertEquals(roman.toString(), romanActual, () -> "\n" + roman + " " + actualArabic + "\n");
            }
        }
    }

    static String arabic2roman(int arabic) {
        StringBuilder roman = new StringBuilder();

        int a = arabic / 1000;
        switch (a) {
            case 1:
                roman.append("M");
                break;
            case 2:
                roman.append("MM");
                break;
            case 3:
                roman.append("MMM");
                break;
        }
        arabic -= 1000 * a;

        a = arabic / 100;
        switch (a) {
            case 1:
                roman.append("C");
                break;
            case 2:
                roman.append("CC");
                break;
            case 3:
                roman.append("CCC");
                break;
            case 4:
                roman.append("CD");
                break;
            case 5:
                roman.append("D");
                break;
            case 6:
                roman.append("DC");
                break;
            case 7:
                roman.append("DCC");
                break;
            case 8:
                roman.append("DCCC");
                break;
            case 9:
                roman.append("CM");
                break;
        }
        arabic -= 100 * a;

        a = arabic / 10;
        switch (a) {
            case 1:
                roman.append("X");
                break;
            case 2:
                roman.append("XX");
                break;
            case 3:
                roman.append("XXX");
                break;
            case 4:
                roman.append("XL");
                break;
            case 5:
                roman.append("L");
                break;
            case 6:
                roman.append("LX");
                break;
            case 7:
                roman.append("LXX");
                break;
            case 8:
                roman.append("LXXX");
                break;
            case 9:
                roman.append("XC");
                break;
        }
        arabic -= 10 * a;

        a = arabic;
        switch (a) {
            case 1:
                roman.append("I");
                break;
            case 2:
                roman.append("II");
                break;
            case 3:
                roman.append("III");
                break;
            case 4:
                roman.append("IV");
                break;
            case 5:
                roman.append("V");
                break;
            case 6:
                roman.append("VI");
                break;
            case 7:
                roman.append("VII");
                break;
            case 8:
                roman.append("VIII");
                break;
            case 9:
                roman.append("IX");
                break;
        }

        return roman.toString();
    }
}
package contest28069;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.function.BiConsumer;

@DisplayName("Алг 1.0 ДЗ 8 (28069) - J")
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
                Alexander_I 4
                Alexei 1
                Anna 1
                Elizabeth 1
                Nicholaus_I 4
                Paul_I 3
                Peter_I 0
                Peter_II 2
                Peter_III 2
                """);
    }

    @Test
    void example2() {
        check("""
                10
                AQHFYP MKFXCLZBT
                AYKOTYQ QIUKGHWCDC
                IWCGKHMFM WPLHJL
                MJVAURUDN QIUKGHWCDC
                MKFXCLZBT IWCGKHMFM
                PUTRIPYHNQ UQNGAXNP
                QIUKGHWCDC WPLHJL
                UQNGAXNP WPLHJL
                YURTPJNR QIUKGHWCDC
                """, """
                AQHFYP 3
                AYKOTYQ 2
                IWCGKHMFM 1
                MJVAURUDN 2
                MKFXCLZBT 2
                PUTRIPYHNQ 2
                QIUKGHWCDC 1
                UQNGAXNP 1
                WPLHJL 0
                YURTPJNR 2
                """);
    }

    @Test
    void example10() {
        check("""
                10
                BFNRMLH CSZMPFXBZ
                CSZMPFXBZ IHWBQDJ
                FMVQTU FUXATQUGIG
                FUXATQUGIG IRVAVMQKN
                GNVIZ IQGIGUJZ
                IHWBQDJ LACXYFQHSQ
                IQGIGUJZ JMUPNYRQD
                IRVAVMQKN GNVIZ
                JMUPNYRQD BFNRMLH
                """, """
                BFNRMLH 3
                CSZMPFXBZ 2
                FMVQTU 9
                FUXATQUGIG 8
                GNVIZ 6
                IHWBQDJ 1
                IQGIGUJZ 5
                IRVAVMQKN 7
                JMUPNYRQD 4
                LACXYFQHSQ 0
                """);
    }
}
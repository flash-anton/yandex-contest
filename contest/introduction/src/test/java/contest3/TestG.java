package contest3;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.function.BiConsumer;

@DisplayName("3-G")
public class TestG extends ContestChecker {
    @Override
    public BiConsumer<InputStream, OutputStream> getTaskAlgorithm() {
        return (reader, writer) -> {
            try {
                G.Main(reader, writer);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    @Test
    void example1() {
        check("""
                ab
                aabbccd
                """, "4");
    }

    @Test
    void test1() {
        check("""
                                
                aabbccd
                """, "0");
    }

    @Test
    void test2() {
        check("""
                ab
                                
                """, "0");
    }

    @Test
    void test3() {
        check("""
                ab
                cde
                """, "0");
    }

    @Test
    void test4() {
        check("""
                ab
                cacaddeeaffba
                """, "5");
    }
}

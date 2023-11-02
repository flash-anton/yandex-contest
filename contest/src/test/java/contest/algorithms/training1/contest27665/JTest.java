package contest.algorithms.training1.contest27665;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.function.BiConsumer;

@DisplayName("Алг 1.0 ДЗ 4 (27665) Словари и сортировка подсчетом - J")
class JTest extends ContestChecker {
    private static final BiConsumer<InputStream, OutputStream> algorithm = (reader, writer) -> {
        try {
            J.alg(new BufferedReader(new InputStreamReader(reader)), new BufferedWriter(new OutputStreamWriter(writer)));
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
                0 yes no
                int main() {
                  int a;
                  int b;
                  scanf("%d%d", &a, &b);
                  printf("%d", a + b);
                }
                """, """
                int
                """);
    }

    @Test
    void example2() {
        check("""
                0 yes no
                #define INT int
                int main() {
                  INT a, b;
                  scanf("%d%d", &a, &b);
                  printf("%d %d", a + b, 0);
                }
                                
                """, """
                d
                """);
    }

    @Test
    void example3() {
        check("""
                6 no no
                program
                var
                begin
                end
                while
                for
                program sum;
                var
                  A, B: integer;
                begin
                  read(A, b);
                  writeln(a + b);
                end.
                                
                """, """
                a
                """);
    }

    @Test
    void example4() {
        check("""
                1 yes yes
                _
                a = 0h
                b = 0h
                c = 0h
                                
                """, """
                0h
                """);
    }
}
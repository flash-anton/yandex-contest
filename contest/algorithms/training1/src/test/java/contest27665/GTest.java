package contest27665;

import common.ContestChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.function.BiConsumer;

@DisplayName("Алг 1.0 ДЗ 4 (27665) Словари и сортировка подсчетом - G")
class GTest extends ContestChecker {
    private static final BiConsumer<InputStream, OutputStream> algorithm = (reader, writer) -> {
        try {
            G.alg(new BufferedReader(new InputStreamReader(reader)), new BufferedWriter(new OutputStreamWriter(writer)));
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
                DEPOSIT Ivanov 100
                INCOME 5
                BALANCE Ivanov
                TRANSFER Ivanov Petrov 50
                WITHDRAW Petrov 100
                BALANCE Petrov
                BALANCE Sidorov
                """, """
                105
                -50
                ERROR
                """);
    }

    @Test
    void example2() {
        check("""
                BALANCE Ivanov
                BALANCE Petrov
                DEPOSIT Ivanov 100
                BALANCE Ivanov
                BALANCE Petrov
                DEPOSIT Petrov 150
                BALANCE Petrov
                DEPOSIT Ivanov 10
                DEPOSIT Petrov 15
                BALANCE Ivanov
                BALANCE Petrov
                DEPOSIT Ivanov 46
                BALANCE Ivanov
                BALANCE Petrov
                DEPOSIT Petrov 14
                BALANCE Ivanov
                BALANCE Petrov
                """, """
                ERROR
                ERROR
                100
                ERROR
                150
                110
                165
                156
                165
                156
                179
                """);
    }

    @Test
    void example3() {
        check("""
                BALANCE Ivanov
                BALANCE Petrov
                DEPOSIT Ivanov 100
                BALANCE Ivanov
                BALANCE Petrov
                DEPOSIT Petrov 150
                BALANCE Petrov
                DEPOSIT Ivanov 10
                DEPOSIT Petrov 15
                BALANCE Ivanov
                BALANCE Petrov
                DEPOSIT Ivanov 46
                BALANCE Ivanov
                BALANCE Petrov
                DEPOSIT Petrov 14
                BALANCE Ivanov
                BALANCE Petrov
                """, """
                ERROR
                ERROR
                100
                ERROR
                150
                110
                165
                156
                165
                156
                179
                """);
    }
}
package org.example;

import java.io.*;
import java.util.function.BiConsumer;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Валидатор заданий.
 * "\r\n" заменяется на "\n".
 * '\n' - разделитель строк. Может быть несколько в конце вопроса/ответа.
 */
public abstract class ContestChecker {
    private static final String n = "\n";
    private static final String rn = "\r\n";

    public abstract BiConsumer<InputStream, OutputStream> getTaskAlgorithm();

    public void check(String question, String expectedAnswer) {
        question = question.replaceAll(rn, n);
        expectedAnswer = expectedAnswer.replaceAll(rn, n).trim();

        ByteArrayOutputStream writer = new ByteArrayOutputStream();
        getTaskAlgorithm().accept(new ByteArrayInputStream(question.getBytes()), writer);
        String actualAnswer = writer.toString().replaceAll(rn, n).trim();

        assertEquals(expectedAnswer, actualAnswer);
    }
}
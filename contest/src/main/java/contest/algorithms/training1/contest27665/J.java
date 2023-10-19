package contest.algorithms.training1.contest27665;

import java.io.*;
import java.util.*;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/27665">Яндекс. Тренировки по алгоритмам июнь 2021, занятие 3. Множества</a>
 * <a href="https://contest.yandex.ru/contest/27665/run-report/93563365">OK  148ms  10.41Mb</a>
 *
 * J. Дополнительная проверка на списывание
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Преподаватель курса ОиМП заказал у одного известного психолога полное психологическое обследование всех студентов,
 * поступивших на ФНК с целью выяснить их склонность к списыванию еще до начала занятий и отчислить их за списывание еще
 * до того как они приступят к занятиям и смогут позорить ФНК своими преступлениями. Психолог, привлеченный для проведения
 * обследования, известен своим инновационным методом, позволяющим понять склонность к списыванию студента по наиболее
 * часто используемому им в программах идентификатору. Помогите известному психологу определить, какие из студентов
 * потенциально являются преступниками. Напишите программу, которая по приведенной программе выяснит наиболее часто
 * используемый в ней идентификатор.
 *
 * Поскольку разные студенты на тестировании пишут программы на разных языках программирования, ваша программа должна
 * уметь работать с произвольным языком. Поскольку в разных языках используются различные ключевые слова, то список
 * ключевых слов в анализируемом языке предоставляется на вход программе. Все последовательности из латинских букв,
 * цифр и знаков подчеркивания, которые не являются ключевыми словами и содержат хотя бы один символ, не являющийся цифрой,
 * могут быть идентификаторами. При этом в некоторых языках идентификаторы могут начинаться с цифры, а в некоторых - нет.
 * Если идентификатор не может начинаться с цифры, то последовательность, начинающаяся с цифры, идентификатором не является.
 * Кроме этого, задано, является ли язык чувствительным к регистру символов, используемых в идентификаторах и ключевых словах.
 *
 * Формат ввода
 * В первой строке вводятся число n - количество ключевых слов в языке (0 <= n <= 50) и два слова C и D, каждое из которых равно либо "yes", либо "no".
 * Слово C равно "yes", если идентификаторы и ключевые слова в языке чувствительны к регистру символов, и "no", если нет.
 * Слово D равно "yes", если идентификаторы в языке могут начинаться с цифры, и "no", если нет.
 * Следующие n строк содержат по одному слову, состоящему из букв латинского алфавита и символов подчеркивания - ключевые слова.
 * Все ключевые слова непусты, различны, при этом, если язык не чувствителен к регистру, то различны и без учета регистра.
 * Длина каждого ключевого слова не превышает 50 символов.
 * Далее до конца входных данных идет текст программы. Он содержит только символы с ASCII-кодами от 32 до 126 и переводы строки.
 * Размер входных данных не превышает 10 килобайт. В программе есть хотя бы один идентификатор.
 *
 * Формат вывода
 * Выведите идентификатор, встречающийся в программе максимальное число раз. Если таких идентификаторов несколько,
 * следует вывести тот, который встречается в первый раз раньше. Если язык во входных данных не чувствителен к регистру,
 * то можно выводить идентификатор в любом регистре.
 * </pre>
 */
public class J {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        alg(reader, writer);
        reader.close();
        writer.close();
    }

    private static String considerCaseSensitivity(String word, boolean isCaseSensitive) {
        return isCaseSensitive ? word : word.toLowerCase();
    }

    private static boolean isLetter(int c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    private static boolean isDigit(int c) {
        return c >= '0' && c <= '9';
    }

    private static boolean isUnderscore(int c) {
        return c == '_';
    }

    private static void putId(Map<String, Integer> m, String word,
                              boolean isCaseSensitive, boolean isDigitStartAllowed, Set<String> keyWords) {
        if (!word.isEmpty()) {
            word = considerCaseSensitivity(word, isCaseSensitive);

            boolean isNotKeyWord = !keyWords.contains(word);
            boolean startsWithAllowedChar = isDigitStartAllowed || !isDigit(word.charAt(0));
            boolean hasNonDigit = false;
            for (byte ch : word.getBytes()) {
                hasNonDigit |= !isDigit(ch);
            }

            if (isNotKeyWord && startsWithAllowedChar && hasNonDigit) {
                m.compute(word, (k, v) -> (v == null) ? 1 : ++v);
            }
        }
    }

    public static void alg(BufferedReader reader, BufferedWriter writer) throws IOException {
        String[] s = reader.readLine().split(" ");
        int keyWordsCount = Integer.parseInt(s[0]);
        boolean isCaseSensitive = s[1].equals("yes");
        boolean isDigitStartAllowed = s[2].equals("yes");

        Set<String> keyWords = new HashSet<>();
        for (int i = 0; i < keyWordsCount; i++) {
            keyWords.add(considerCaseSensitivity(reader.readLine(), isCaseSensitive));
        }

        Map<String, Integer> m = new LinkedHashMap<>();
        StringBuilder wordBuffer = new StringBuilder();
        int c;
        while ((c = reader.read()) != -1) {
            if (isLetter(c) || isDigit(c) || isUnderscore(c)) {
                wordBuffer.append((char) c);
            } else {
                putId(m, wordBuffer.toString(), isCaseSensitive, isDigitStartAllowed, keyWords);
                wordBuffer = new StringBuilder();
            }
        }
        putId(m, wordBuffer.toString(), isCaseSensitive, isDigitStartAllowed, keyWords);

        String id = "";
        int maxOccurrenceFrequency = 0;
        for (Map.Entry<String, Integer> e : m.entrySet()) {
            int occurrenceFrequency = e.getValue();
            if (occurrenceFrequency > maxOccurrenceFrequency) {
                maxOccurrenceFrequency = occurrenceFrequency;
                id = e.getKey();
            }
        }

        writer.write(id);
        writer.flush();
    }
}

package contest.algorithms.training1.contest27665;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/27665">Яндекс. Тренировки по алгоритмам июнь 2021, занятие 3. Множества</a>
 * <a href="https://contest.yandex.ru/contest/27665/run-report/93530806">OK  356ms  23.63Mb</a>
 *
 * I. Контрольная по ударениям
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Учительница задала Пете домашнее задание — в заданном тексте расставить ударения в словах, после чего поручила Васе
 * проверить это домашнее задание. Вася очень плохо знаком с данной темой, поэтому он нашел словарь, в котором указано,
 * как ставятся ударения в словах. К сожалению, в этом словаре присутствуют не все слова. Вася решил, что в словах,
 * которых нет в словаре, он будет считать, что Петя поставил ударения правильно, если в этом слове Петей поставлено
 * ровно одно ударение. Оказалось, что в некоторых словах ударение может быть поставлено больше, чем одним способом.
 * Вася решил, что в этом случае если то, как Петя поставил ударение, соответствует одному из приведенных в словаре вариантов,
 * он будет засчитывать это как правильную расстановку ударения, а если не соответствует, то как ошибку. Вам дан словарь,
 * которым пользовался Вася и домашнее задание, сданное Петей.
 * Ваша задача — определить количество ошибок, которое в этом задании насчитает Вася.
 *
 * Формат ввода
 * Вводится сначала число N — количество слов в словаре (0≤N≤20000).
 * Далее идет N строк со словами из словаря. Каждое слово состоит не более чем из 30 символов.
 * Все слова состоят из маленьких и заглавных латинских букв. В каждом слове заглавная ровно одна буква — та, на которую попадает ударение.
 * Слова в словаре расположены в алфавитном порядке. Если есть несколько возможностей расстановки ударения в одном и том же слове,
 * то эти варианты в словаре идут в произвольном порядке.
 * Далее идет упражнение, выполненное Петей. Упражнение представляет собой строку текста, суммарным объемом не более 300000 символов.
 * Строка состоит из слов, которые разделяются между собой ровно одним пробелом. Длина каждого слова не превышает 30 символов.
 * Все слова состоят из маленьких и заглавных латинских букв (заглавными обозначены те буквы, над которыми Петя поставил ударение).
 * Петя мог по ошибке в каком-то слове поставить более одного ударения или не поставить ударения вовсе.
 *
 * Формат вывода
 * Выведите количество ошибок в Петином тексте, которые найдет Вася.
 *
 * Примечания
 *
 * Примечания к примерам тестов
 * 1. В слове cannot, согласно словарю возможно два варианта расстановки ударения.
 * Эти варианты в словаре могут быть перечислены в любом порядке (т.е. как сначала cAnnot, а потом cannOt, так и наоборот).
 * Две ошибки, совершенные Петей — это слова be (ударение вообще не поставлено) и fouNd (ударение поставлено неверно).
 * Слово thE отсутствует в словаре, но поскольку в нем Петя поставил ровно одно ударение, признается верным.
 * 2. Неверно расставлены ударения во всех словах, кроме The (оно отсутствует в словаре, в нем поставлено ровно одно ударение).
 * В остальных словах либо ударные все буквы (в слове PAGE), либо не поставлено ни одного ударения.
 * </pre>
 */
public class I {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        alg(reader, writer);
        reader.close();
        writer.close();
    }

    private static int emphasisCount(String word) {
        int count = 0;
        for (byte c : word.getBytes()) {
            if (c >= 'A' && c <= 'Z') {
                count++;
            }
        }
        return count;
    }

    public static void alg(BufferedReader reader, BufferedWriter writer) throws IOException {
        Map<String, Set<String>> dict = new HashMap<>();
        int N = Integer.parseInt(reader.readLine());
        for (int i = 0; i < N; i++) {
            String word = reader.readLine();
            dict.computeIfAbsent(word.toLowerCase(), k -> new HashSet<>()).add(word);
        }

        int errors = 0;
        String[] task = reader.readLine().split(" ");
        for (String word : task) {
            Set<String> dictWords = dict.getOrDefault(word.toLowerCase(), Set.of());
            boolean unknownWith1Emphasis = dictWords.isEmpty() && emphasisCount(word) == 1;
            boolean permissible = dictWords.contains(word);
            if (!word.isEmpty() && !unknownWith1Emphasis && !permissible) {
                errors++;
            }
        }

        writer.write(String.valueOf(errors));
        writer.flush();
    }
}

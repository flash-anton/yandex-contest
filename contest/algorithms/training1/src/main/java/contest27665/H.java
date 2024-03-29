package contest27665;

import java.io.*;
import java.util.Arrays;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/27665">Яндекс. Тренировки по алгоритмам июнь 2021, занятие 4. Словари и сортировка подсчетом</a>
 * <a href="https://contest.yandex.ru/contest/27665/run-report/93465697">OK  396ms  23.81Mb</a>
 *
 * H. Расшифровка письменности Майя
 * Ограничение времени 	1 секунда
 * Ограничение памяти 	64Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Расшифровка письменности Майя оказалась более сложной задачей, чем предполагалось ранними исследованиями.
 * На протяжении более чем двух сотен лет удалось узнать не так уж много. Основные результаты были получены за последние 30 лет.
 *
 * Письменность Майя основывается на маленьких рисунках, известных как значки, которые обозначают звуки.
 * Слова языка Майя обычно записываются с помощью этих значков, которые располагаются рядом друг с другом в некотором порядке.
 *
 * Одна из проблем расшифровки письменности Майя заключается в определении этого порядка. Рисуя значки некоторого слова,
 * писатели Майя иногда выбирали позиции для значков, исходя скорее из эстетических взглядов, а не определенных правил.
 * Это привело к тому, что, хотя звуки для многих значков известны, археологи не всегда уверены, как должно произноситься записанное слово.
 *
 * Археологи ищут некоторое слово W. Они знают значки для него, но не знают все возможные способы их расположения.
 * Поскольку они знают, что Вы приедете на IOI ’06, они просят Вас о помощи. Они дадут Вам g значков, составляющих слово W,
 * и последовательность S всех значков в надписи, которую они изучают, в порядке их появления.
 * Помогите им, подсчитав количество возможных появлений слова W.
 *
 * Задание
 * Напишите программу, которая по значкам слова W и по последовательности S значков надписи подсчитывает количество всех
 * возможных вхождений слова W в S, то есть количество всех различных позиций идущих подряд g значков в последовательности S,
 * которые являются какой-либо перестановкой значков слова W .
 *
 * Формат ввода
 * 1 ≤ g ≤ 3 000, g – количество значков в слове W
 * g ≤ |S| ≤ 3 000 000 где |S| – количество значков в последовательности S
 *
 * На вход программы поступают данные в следующем формате:
 * СТРОКА 1: Содержит два числа, разделенных пробелом – g и |S|.
 * СТРОКА 2: Содержит g последовательных символов, с помощью которых записывается слово W . Допустимы символы: ‘a’-‘z’ и ‘A’-‘Z’; большие и маленькие буквы считаются различными.
 * СТРОКА 3: Содержит |S| последовательных символов, которые представляют значки в надписи. Допустимы символы: ‘a’-‘z’ и ‘A’-‘Z’; большие и маленькие буквы считаются различными.
 *
 * Формат вывода
 * Единственная строка выходных данных программы должна содержать количество возможных вхождений слова W в S.
 * </pre>
 */
public class H {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        alg(reader, writer);
        reader.close();
        writer.close();
    }

    public static void alg(BufferedReader reader, BufferedWriter writer) throws IOException {
        reader.readLine();
        byte[] W = reader.readLine().getBytes();
        byte[] S = reader.readLine().getBytes();

        int count = alg1(W, S);

        writer.write(String.valueOf(count));
        writer.flush();
    }

    public static int alg1(byte[] W, byte[] S) {
        int[] word = word(W);
        int[] window = window(W, S);

        int count = Arrays.equals(word, window) ? 1 : 0;

        for (int i = W.length; i < S.length; i++) {
            window[S[i]]++;
            window[S[i - W.length]]--;
            count += Arrays.equals(word, window) ? 1 : 0;
        }
        return count;
    }

    public static int alg2(byte[] W, byte[] S) {
        int[] word = word(W);
        int[] window = window(W, S);

        int windowDifferences = 0;
        for (int i = 0; i < 128; i++) {
            windowDifferences += window[i] != word[i] ? 1 : 0;
        }

        int count = windowDifferences == 0 ? 1 : 0;

        for (int i = W.length, j = 0; i < S.length; i++, j++) {
            windowDifferences += (window[S[i]]++ == word[S[i]]) ? 1 : (window[S[i]] == word[S[i]]) ? -1 : 0;
            windowDifferences += (window[S[j]]-- == word[S[j]]) ? 1 : (window[S[j]] == word[S[j]]) ? -1 : 0;
            count += windowDifferences == 0 ? 1 : 0;
        }
        return count;
    }

    private static int[] word(byte[] W) {
        int[] word = new int[128];
        for (byte c : W) {
            word[c]++;
        }
        return word;
    }

    private static int[] window(byte[] W, byte[] S) {
        int[] window = new int[128];
        for (int i = 0; i < W.length; i++) {
            window[S[i]]++;
        }
        return window;
    }
}

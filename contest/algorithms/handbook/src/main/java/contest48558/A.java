package contest48558;

import java.io.*;
import java.util.Arrays;

/**
 * <pre>
 * <a href="https://education.yandex.ru/handbook/algorithms">Основы алгоритмов</a>
 * <a href="https://new.contest.yandex.ru/48558">Alg handbook 3.3 (48558) Динамическое программирование</a>
 * OK  116ms / 1s  12.15MB / 256MB
 *
 * A. Камни
 *
 * Вы играете в игру <<Камни>>: игру для двух игроков с двумя наборами камней по n и m штук.
 * С каждым ходом один игрок может взять один камень (из любого набора) или два камня (по одному из обоих).
 * Когда камень забрали, он выходит из игры.
 * Побеждает игрок, который заберет последний камень.
 * Первый ход за вами.
 *
 * Вы и ваш оппонент играете оптимально.
 *
 * Формат ввода
 * В первой строке содержится два числа n (1≤n≤10), m (1≤m≤10) — количество ваших камней и количество камней у вашего
 * оппонента.
 *
 * Формат вывода
 * В единственной строке выведите Loose, если вы заведомо проиграете, и Win, иначе.
 * </pre>
 */
public class A {
    public static void main(String[] args) throws IOException {
        try (InputStream reader = new BufferedInputStream(System.in, 3_000_000);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            alg(reader, writer);
        }
    }

    public static void alg(InputStream is, BufferedWriter writer) throws IOException {
        Reader reader = new Reader(is);
        int n = reader.nextInt();
        int m = reader.nextInt();

        int[] nGetRule = {0, 1, 1};
        int[] mGetRule = {1, 0, 1};
        int rulesCount = nGetRule.length;
        int nGetRuleMax = Arrays.stream(nGetRule).max().getAsInt();
        int mGetRuleMax = Arrays.stream(mGetRule).max().getAsInt();
        boolean[][] isLooser = new boolean[nGetRuleMax + n + 1][mGetRuleMax + m + 1]; // false - Winner
        for (int i = nGetRuleMax; i < nGetRuleMax + n + 1; i++) {
            for (int j = mGetRuleMax; j < mGetRuleMax + m + 1; j++) {
                boolean isOpponentAlwaysWin = true;
                for (int k = 0; k < rulesCount; k++) {
                    int iNext = i - nGetRule[k];
                    int jNext = j - mGetRule[k];
                    isOpponentAlwaysWin &= !isLooser[iNext][jNext];
                }
                isLooser[i][j] = isOpponentAlwaysWin;
            }
        }

        String solution = isLooser[nGetRuleMax + n][mGetRuleMax + m] ? "Loose" : "Win";

        writer.write(solution);
        writer.flush();
    }

    /**
     * "Быстрый" ридер потока.
     */
    public static class Reader {
        private final InputStream is;
        private int lastReadByte = '\n';

        public Reader(InputStream is) {
            this.is = is;
        }

        public long nextLong() throws IOException {
            while (lastReadByte == ' ' || lastReadByte == '\n') {
                lastReadByte = is.read();
            }

            int sign = 1;
            if (lastReadByte == '-') {
                sign = -1;
                lastReadByte = is.read();
            }

            long num = 0;
            while (lastReadByte >= '0' && lastReadByte <= '9') {
                num = (num * 10) + sign * (lastReadByte - '0');
                lastReadByte = is.read();
            }
            return num;
        }

        public long[] nextLongs(long[] a) throws IOException {
            for (int i = 0; i < a.length; i++) {
                a[i] = nextLong();
            }
            return a;
        }

        public int nextInt() throws IOException {
            return (int) nextLong();
        }

        public int[] nextInts(int[] a) throws IOException {
            for (int i = 0; i < a.length; i++) {
                a[i] = nextInt();
            }
            return a;
        }

        public String nextWord() throws IOException {
            while (lastReadByte == ' ' || lastReadByte == '\n') {
                lastReadByte = is.read();
            }

            StringBuilder sb = new StringBuilder();
            while (!(lastReadByte == -1 || lastReadByte == ' ' || lastReadByte == '\n')) {
                sb.append((char) lastReadByte);
                lastReadByte = is.read();
            }
            return sb.toString();
        }

        public String[] nextWords(String[] a) throws IOException {
            for (int i = 0; i < a.length; i++) {
                a[i] = nextWord();
            }
            return a;
        }

        public String readLine() throws IOException {
            if (lastReadByte == '\n') {
                lastReadByte = is.read();
            }

            StringBuilder sb = new StringBuilder();
            while (!(lastReadByte == -1 || lastReadByte == '\n')) {
                sb.append((char) lastReadByte);
                lastReadByte = is.read();
            }
            return sb.toString();
        }
    }
}

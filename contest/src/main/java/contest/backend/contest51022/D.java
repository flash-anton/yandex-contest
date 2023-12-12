package contest.backend.contest51022;

import java.io.*;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/51022">Стаж бэк сен-дек 2023 (51022)</a>
 * <a href="https://contest.yandex.ru/contest/51022/run-report/zzzzz">OK  zzzzz</a>
 *
 * D. Межпланетная организация
 * 	Все языки 	GNU GCC 13.1 C++20 	Clang 16.0.0 C++20
 * Ограничение времени 	5 секунд 	2 секунды 	2 секунды
 * Ограничение памяти 	512Mb 	512Mb 	512Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Межпланетная организация имеет иерархическую древовидную структуру:
 *
 *     Корнем иерархии является генеральный директор;
 *     У каждого сотрудника 0 или более непосредственных подчиненных;
 *     Каждый сотрудник, кроме генерального директора, является непосредственным подчиненным ровно одному сотруднику.
 *
 * Каждый сотрудник, кроме генерального директора, говорит либо на языке A, либо на языке B. Директор говорит на двух языках для управления всей организацией.
 *
 * Структура всей организации хранится в текстовом документе. Каждый сотрудник представлен уникальным идентификатором - целым числом от 0 до N включительно, где 0 - идентификатор генерального директора.
 *
 * Каждый сотрудник представлен в документе ровно два раза. Между первым и вторым вхождением идентификатора сотрудника в аналогичном формате представлены все его подчиненные.
 *
 * Если у сотрудника нет подчиненных, то два его идентификатора расположены один за другим.
 *
 * Например, если
 *
 *     генеральный директор имеет в прямом подчинении сотрудника 1;
 *     сотрудник 1 имеет в прямом подчинении сотрудника 2;
 *     сотрудник 2 имеет в прямом подчинении сотрудников 3 и 4;
 *
 * то документ будет представлен в виде строки:
 *
 * 0123344210
 *
 * Если при этом сотрудники 1, 3, 4 говорят на языке A, а сотрудник 2 говорит на языке B, то вся организация выглядит так:
 *     0
 *     |
 *    1A
 *    |
 *   2B
 *  /  \
 * 3A  4A
 *
 * Назовем языковым барьером сотрудника X минимальное количество начальников между X и его начальником с таким же языком.
 *
 * В нашем случае сотрудники 2, 3 и 4 - имеют языковой барьер 1,
 * т.к. у каждого из них начальник говорит на неизвестном для них языке, а сразу следующий начальник говорит на том же языке, что они.
 *
 * В то же время сотрудник 1 имеет языковой барьер равный 0, поскольку его начальник - это директор, который знает два языка.
 *
 * Вычислить языковой барьер для каждого сотрудника в компании.
 *
 * Формат ввода
 * В первой строке задано целое число N(1≤N≤10^6) — количество сотрудников (без генерального директора).
 * Во второй строке через пробел задано N символов Li(Li∈{A,B}) — язык i-го сотрудника.
 * В третьей строке через пробел задано 2⋅(N+1) целых чисел Pj(0≤Pj≤N) — иерархия организации в описанном в условии формате.
 * Гарантируется, что первый и последний элементы иерархии равны 0.
 *
 * Формат вывода
 * Выведите N целых чисел через пробел — языковой барьер каждого сотрудника от 1 до N включительно. .
 *
 * Примечания
 * Первый тестовый пример.
 * Соответствует рисунку ниже.
 *     0
 *    / \
 *  1A  2B
 *      |
 *     3B
 *    /  \
 *   4A  5B
 *
 * Все сотрудники, кроме сотрудника 4, имеют нулевой языковой барьер:
 *
 * Сотрудник 4 имеет языковой барьер равный 2, т.к. ближайший начальник,
 * говорящий с ним на одном языке — генеральный директор 0, а между директором и данным сотрудником ровно 2 начальника (3 и 2).
 *
 * Второй тестовый пример.
 * Соответствует примеру, описанному в условии задачи.
 * </pre>
 */
public class D {
    public static void main(String[] args) throws IOException {
        try (InputStream reader = new BufferedInputStream(System.in, 3_000_000);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            alg(reader, writer);
        }
    }

    public static void alg(InputStream is, BufferedWriter writer) throws IOException {
        Scanner reader = new Scanner(is);
        reader.readLine();

        int N = reader.nextInt();

        int[] a = reader.nextInts(new int[N]);
        int A = a[0];
        int B = a[1];

        long[][] req = new long[A][B];
        for (int i = 0; i < N; i++) {
            reader.nextLongs(req[i]);
            req[i][0] = A % B;
        }

        String solution = alg1(a, req);

        writer.write(solution);
        writer.flush();
    }

    public static String alg1(int[] a, long[][] req) {
        return "" + req.length + a.length;
    }

    public static String alg2(int[] a, long[][] req) {
        return "" + req.length + a.length;
    }


    /**
     * "Быстрый" ридер потока.
     */
    public static class Scanner {
        private final InputStream is;
        private int lastReadByte = '\n';

        public Scanner(InputStream is) {
            this.is = is;
        }

        private long nextLong() throws IOException {
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

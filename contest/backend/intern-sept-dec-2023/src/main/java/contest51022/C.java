package contest51022;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.util.stream.Collectors.toSet;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/51022">Стаж бэк сен-дек 2023 (51022)</a>
 * <a href="https://contest.yandex.ru/contest/51022/run-report/102692161">WA  124ms  12.44Mb  14</a>
 *
 * C. Запрос к таблице
 * 	Все языки 	Python 3.9 (PyPy 7.3.11) 	Python 3.11.4
 * Ограничение времени 	2 секунды 	4 секунды 	4 секунды
 * Ограничение памяти 	512Mb 	512Mb 	512Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * Петя пришел на стажировку в Яндекс, и первая его задача была познакомиться с SQL.
 *
 * У Пети есть табличка, состоящая из N строк и M столбцов, значениями которой являются целые числа.
 * Каждой колонке соответствует уникальное имя — строка из латинских символов.
 *
 * Пете задан запрос из Q ограничений вида: ColumnNamek qk valk.
 *
 * qk может принимать два значения:
 *     > — учитывать только те строки, где значения в ColumnNamek строго больше valk;
 *     < — учитывать только те строки, где значения в ColumnNamek строго меньше valk.
 *
 * Задача Пети заключается в том, чтоб посчитать сумму во всех строках, которые удовлетворяют всем ограничениям.
 * Юный стажер уже написал скрипт и вычислил ответ.
 * Но Петя волнуется, что где-то ошибся, поэтому просит вас перепроверить его вычисления.
 *
 * Формат ввода
 * На первой строке вводятся 3 числа N, M, Q(1≤N×M≤3*10^5,1≤Q≤10^5) — количество строк, столбцов в таблице и количество ограничений в запросе.
 * В следующей строке вводятся через пробел M слов, состоящих из латинских маленьких букв — название соответствующей колонки, каждая строка по длине не превосходит L(1≤L≤10)
 * Далее вводятся N строк, в каждой через пробел M целых чисел aij(−10^9≤aij≤10^9) — элементы i-ой строки.
 * Потом вводятся Q строк — ограничения к запросу.
 * Каждая строка имеет вид ColumnNamek qk valk (qk∈(<,>);−10^9≤valk≤10^9) — k-ое ограничение в формате, описанном в условии задачи.
 * Гарантируется, что ColumnNamek соответствует имени одной из колонок таблицы.
 *
 * Формат вывода
 * Выведите единственное значение S — сумму всех чисел в строках, удовлетворяющих всем заданным ограничениям.
 * Если никакая строка не удовлетворяет всем ограничениям — выведите в ответ 0..
 *
 * Примечания
 * Первый тестовый пример:
 *
 * В табличке есть две строки:
 *
 *     (a=1,b=1);
 *     (a=2,b=2);
 *
 * Рассмотрим ограничения из запроса:
 *
 *     первому ограничению «a<3» соответствуют обе строки: (1<3) и (2<3);
 *     второму ограничению «b>1» соответствует только вторая строка: неверно, что (1>1), но верно (2>1);
 *     третьему ограничению «b<3» соответствуют обе строки: (1<3) и (2<3).
 *
 * Так как первая строка не соответствует одному из ограничений, она в ответе не учитывается.
 *
 * В результате в ответ входит только вторая строка, сумма всех чисел в ней: 2+2=4
 * </pre>
 */
public class C {
    public static void main(String[] args) throws IOException {
        try (InputStream reader = new BufferedInputStream(System.in, 3_000_000);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            alg(reader, writer);
        }
    }

    public static void alg(InputStream is, BufferedWriter writer) throws IOException {
        Scanner reader = new Scanner(is);
        int N = reader.nextInt();
        int M = reader.nextInt();
        int Q = reader.nextInt();

        Map<String, Integer> columnByName = new HashMap<>();
        for (int column = 0; column < M; column++)
            columnByName.put(reader.nextWord(), column);

        long[] rowSum = new long[N];
        List<TreeMap<Integer, Set<Integer>>> rowsByCellByColumn = new ArrayList<>(M);
        for (int column = 0; column < M; column++)
            rowsByCellByColumn.add(new TreeMap<>());
        for (int row = 0; row < N; row++) {
            long sum = 0;
            for (int column = 0; column < M; column++) {
                int cell = reader.nextInt();
                sum += cell;
                rowsByCellByColumn.get(column).computeIfAbsent(cell, k -> new HashSet<>()).add(row);
            }
            rowSum[row] = sum;
        }

        HashMap<Integer, Query> queryByColumn = new HashMap<>();
        for (int q = 0; q < Q; q++) {
            String columnName = reader.nextWord();
            int column = columnByName.get(columnName);
            char sign = reader.nextWord().charAt(0);
            int val = reader.nextInt();
            Query qi = new Query(column, sign, val);
            if(!queryByColumn.computeIfAbsent(column, k -> qi).merge(qi.min, qi.max).valid()) {
                queryByColumn = new HashMap<>();
                break;
            }
        }

        Set<Integer> rows = queryByColumn.isEmpty() ? new HashSet<>() : IntStream.range(0, M).boxed().collect(toSet());
        for (Query query : queryByColumn.values()) {
            NavigableMap<Integer, Set<Integer>> rowsByCell = rowsByCellByColumn.get(query.column);
            rowsByCell = rowsByCell.tailMap(query.min, false);
            rowsByCell = rowsByCell.headMap(query.max, false);
            Set<Integer> r = rowsByCell.values().stream().flatMap(Collection::stream).collect(toSet());
            rows.retainAll(r);
        }

        long result = 0;
        for (Integer row : rows) {
            result += rowSum[row];
        }

        String solution = "" + result;

        writer.write(solution);
        writer.flush();
    }

    public static class Query {
        public final int column;
        public int min = Integer.MIN_VALUE;
        public int max = Integer.MAX_VALUE;

        public Query(int column, char sign, int val) {
            this.column = column;
            if (sign == '<') {
                max = val;
            } else {
                min = val;
            }
        }

        public Query merge(int min, int max) {
            this.min = max(this.min, min);
            this.max = min(this.max, max);
            return this;
        }

        public boolean valid() {
            return min < max;
        }
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

        public int nextInt() throws IOException {
            while (lastReadByte == ' ' || lastReadByte == '\n') {
                lastReadByte = is.read();
            }

            int sign = 1;
            if (lastReadByte == '-') {
                sign = -1;
                lastReadByte = is.read();
            }

            int num = 0;
            while (lastReadByte >= '0' && lastReadByte <= '9') {
                num = (num * 10) + sign * (lastReadByte - '0');
                lastReadByte = is.read();
            }
            return num;
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
    }
}

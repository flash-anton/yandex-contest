package contest36783;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.joining;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/36783">Interview training 36783</a>
 * <a href="https://contest.yandex.ru/contest/36783/run-report/104793487">OK  0.825s  80.53Mb</a>
 *
 * I. Частичный разворот
 * Ограничение времени 	2 секунды
 * Ограничение памяти 	256Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * В этой задаче по умолчанию выбран компилятор Make.
 * Решение отправляется в виде файла с расширением, соответствующим используемому языку.
 *
 * В этой задаче требуется сделать разворот части односвязного списка.
 * Каждая вершина списка описывается структурой Node, каждый экземпляр хранит указатель на следующую вершину или null
 * (nullptr, None, nil), если вершина последняя.
 * По заданным индексам from и to разверните все вершины на отрезке с from до to включительно.
 * Заметьте, что нумерация в индексах from и to с единицы.
 *
 * Вы можете воспользоваться заготовками кода для данной задачи из репозитория курса.
 *
 * Пример для списка из пяти вершин с разворотом от второй до четвертой вершины представлен на рисунке ниже.
 * left = 2, right = 4
 * 1 2 3 4 5
 * 1 4 3 2 5
 *
 * Формат ввода
 * Реализуйте функцию Reverse, которая принимает голову списка и два целочисленных индекса.
 * Подробные сигнатуры указаны в шаблонах на <a href="https://disk.yandex.ru/d/HsNguf7WCm5MZw">диске</a>.
 * Гарантируется, что в списке есть хотя бы одна вершина. Суммарное число вершин не превосходит 10^5.
 *
 * Формат вывода
 * Функция Reverse должна возвращать голову изменённого списка.
 * Создавать вершины заново нельзя.
 * </pre>
 */
public class I {
    public static void main(String[] args) throws IOException {
        try (InputStream reader = new BufferedInputStream(System.in, 3_000_000);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            alg(reader, writer);
        }
    }

    public static class Node {
        public final int val;
        public Node next;

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }

    public static void alg(InputStream is, BufferedWriter writer) throws IOException {
        Reader reader = new Reader(is);
        int n = reader.nextInt();
        int left = reader.nextInt();
        int right = reader.nextInt();
        Node[] a = new Node[n];
        a[0] = new Node(reader.nextInt(),null);
        for (int i = 1; i < n; i++) {
            a[i] = new Node(reader.nextInt());
        }
        for (int i = 0; i < n - 1; i++) {
            a[i].next = a[i + 1];
        }

        Node head = Reverse(a[0], left, right);
        for (int i = 0; i < n; i++) {
            a[i] = head;
            head = head.next;
        }

        String solution = Arrays.stream(a).map(i -> "" + i.val).collect(joining(" "));

        writer.write(solution);
        writer.flush();
    }

    public static Node Reverse(Node head, int left, int right) {
        left--;
        right--;

        List<Node> a = new ArrayList<>();
        for (Node it = head; it != null; it = it.next)
            a.add(it);

        if (left == 0)
            head = a.get(right);
        else
            a.get(left - 1).next = a.get(right);

        a.get(left).next = a.get(right).next;

        for (int i = left; i < right; i++)
            a.get(i + 1).next = a.get(i);

        return head;
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

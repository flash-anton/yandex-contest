package contest36783;

import java.util.*;

/**
 * <pre>
 * <a href="https://contest.yandex.ru/contest/36783">Interview training 36783</a>
 * <a href="https://contest.yandex.ru/contest/36783/run-report/105063458">OK  2.34s  138.85Mb</a>
 *
 * N. Атака клонов
 * Ограничение времени 	4 секунды
 * Ограничение памяти 	256Mb
 * Ввод 	стандартный ввод или input.txt
 * Вывод 	стандартный вывод или output.txt
 *
 * В этой задаче по умолчанию выбран компилятор Make.
 * Решение отправляется в виде файла с расширением, соответствующим используемому языку.
 *
 * В этой задаче требуется создать копию связного графа. Оригинальный граф задается одной вершиной.
 * Вершина содержит свое уникальное значение – value, и список соседей neighbours.
 * Граф будет считаться копией, если в графе-копии есть связь между вершинами со значениями v1 и v2 тогда и только тогда,
 * когда она есть в оригинальном графе.
 * Все вершины графа-копии должны быть созданы заново, то есть нельзя переиспользовать вершины из оригинального графа.
 * Создавайте новые вершины с помощью публичных конструкторов и фабричных методов, указанных в шаблонах.
 *
 * Вы можете воспользоваться заготовками кода для данной задачи из репозитория курса.
 *
 * Формат ввода
 * Функция cloneGraph принимает стартовую вершину, которая принадлежит оригинальному графу.
 * Точные сигнатуры вы найдете в шаблонах, расположенных на <a href="https://disk.yandex.ru/d/SN0w3_jkf0JMsQ">диске</a>.
 * Гарантируется, что число вершин и рёбер графа не превосходит 10^5.
 *
 * Формат вывода
 * Функция cloneGraph должна возвращать вершину, являющуюся копией стартовой вершины в оригинальном графе.
 * </pre>
 */
public class N {
    public static class Node {
        public final int val;
        public final List<Node> neighbours;

        public Node(int val) {
            this.val = val;
            this.neighbours = new ArrayList<>();
        }
    }

    public static Node cloneGraph(Node node) {
        Map<Integer, Node> cloneByOriginVal = new HashMap<>();
        Queue<Node> unvisited = new ArrayDeque<>(Collections.singletonList(node));
        while (!unvisited.isEmpty()) {
            Node origin = unvisited.poll();
            if (!cloneByOriginVal.containsKey(origin.val)) {
                Node clone = new Node(origin.val);
                cloneByOriginVal.put(origin.val, clone);
                unvisited.addAll(origin.neighbours);
            }
        }

        Map<Integer, Node> fullCloneByOriginVal = new HashMap<>();
        unvisited = new ArrayDeque<>(Collections.singletonList(node));
        while (!unvisited.isEmpty()) {
            Node origin = unvisited.poll();
            if (!fullCloneByOriginVal.containsKey(origin.val)) {
                Node clone = cloneByOriginVal.get(origin.val);
                fullCloneByOriginVal.put(origin.val, clone);
                unvisited.addAll(origin.neighbours);

                origin.neighbours.forEach(originNeighbor -> {
                    Node cloneNeighbor = cloneByOriginVal.get(originNeighbor.val);
                    clone.neighbours.add(cloneNeighbor);
                });
            }
        }

        return cloneByOriginVal.get(node.val);
    }
}

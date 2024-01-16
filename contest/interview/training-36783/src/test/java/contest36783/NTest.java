package contest36783;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Interview training 36783 - N")
class NTest {
    @Test
    public void test() {
        N.Node node1 = new N.Node(1);
        N.Node node2 = new N.Node(2);
        N.Node node3 = new N.Node(3);
        node1.neighbours.addAll(List.of(node2, node3));
        node2.neighbours.addAll(List.of(node1, node3));
        node3.neighbours.addAll(List.of(node1, node2));

        N.Node clone1 = N.cloneGraph(node1);
        N.Node clone2 = clone1.neighbours.get(0);
        N.Node clone3 = clone1.neighbours.get(1);
        clone1.neighbours.clear();
        clone2.neighbours.clear();
        clone3.neighbours.clear();

        System.out.println(node1.neighbours + ":" + clone1.neighbours);
        System.out.println(node2.neighbours + ":" + clone2.neighbours);
        System.out.println(node3.neighbours + ":" + clone3.neighbours);

        assertAll(
                () -> assertEquals(node1.val, clone1.val),
                () -> assertNotEquals(node1.neighbours, clone1.neighbours),
                () -> assertEquals(node2.val, clone2.val),
                () -> assertNotEquals(node2.neighbours, clone2.neighbours),
                () -> assertEquals(node3.val, clone3.val),
                () -> assertNotEquals(node3.neighbours, clone3.neighbours)
        );
    }
}
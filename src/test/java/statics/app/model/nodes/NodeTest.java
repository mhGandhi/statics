package statics.app.model.nodes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Objects;

class NodeTest {

    @Test
    void testEquals() {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(2);

        Assertions.assertEquals(node2, node3);
        Assertions.assertNotEquals(node1,node2);
    }

    @Test
    void testHashCode() {
        int id = (int)Math.round(Math.random()*1000);
        Node node = new Node(id);

        Assertions.assertEquals(node.hashCode(), Objects.hash(id));
    }
}
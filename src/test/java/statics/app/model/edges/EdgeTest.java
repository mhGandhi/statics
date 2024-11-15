package statics.app.model.edges;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import statics.app.model.nodes.Node;

class EdgeTest {

    @Test
    void getStart() {
        Node n1 = new Node(1);
        Node n2 = new Node(2);

        Edge e = new Edge(n1,n2);

        Assertions.assertEquals(n1,e.getStart());
    }

    @Test
    void getEnd() {
        Node n1 = new Node(1);
        Node n2 = new Node(2);

        Edge e = new Edge(n1,n2);

        Assertions.assertEquals(n2,e.getEnd());
    }
}
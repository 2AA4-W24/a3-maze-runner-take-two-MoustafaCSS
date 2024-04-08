package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NodeTest {

    @Test
    void nodeConstructorAndGetPositionTest() {
        Position position = new Position(5, 10);
        Node node = new Node(position);
        assertNotNull(node.getPosition(), "Node position should not be null.");
        assertEquals(position, node.getPosition(), "Node position should match the one provided to constructor.");
    }

    @Test
    void nodeEqualityTest() {
        Position position1 = new Position(5, 10);
        Node node1 = new Node(position1);

        Position position2 = new Position(5, 10);
        Node node2 = new Node(position2);

        assertEquals(node1, node2, "Two nodes with the same position should be considered equal.");
    }

    @Test
    void nodeInequalityTest() {
        Position position1 = new Position(5, 10);
        Node node1 = new Node(position1);

        Position position2 = new Position(10, 5);
        Node node2 = new Node(position2);

        assertNotEquals(node1, node2, "Two nodes with different positions should not be considered equal.");
    }

    @Test
    void nodeHashCodeConsistencyTest() {
        Position position1 = new Position(5, 10);
        Node node1 = new Node(position1);
        Node node2 = new Node(position1);

        assertEquals(node1.hashCode(), node2.hashCode(), "Hash codes of two nodes with the same position should be the same.");
    }
}

package ca.mcmaster.se2aa4.mazerunner;

import java.util.Objects;

public class Node {
    private final Position position;

    public Node(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;
        Node node = (Node) o;
        return Objects.equals(getPosition(), node.getPosition());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPosition());
    }
}
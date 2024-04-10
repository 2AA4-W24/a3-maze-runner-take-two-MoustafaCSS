package ca.mcmaster.se2aa4.mazerunner;

public interface GraphTraversal {
    Path traverse(Graph graph, Node start, Node end);
}
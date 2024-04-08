package ca.mcmaster.se2aa4.mazerunner;

public class GraphBasedSolver implements MazeSolver {
    private final GraphTraversal traversal;

    public GraphBasedSolver(GraphTraversal traversal) {
        this.traversal = traversal;
    }

    @Override
    public Path solve(Maze maze) {
        Graph graph = new Graph(maze);
        Node startNode = new Node(maze.getStart());
        Node endNode = new Node(maze.getEnd());
        return traversal.traverse(graph, startNode, endNode);
    }

}

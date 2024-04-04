package ca.mcmaster.se2aa4.mazerunner;

import java.util.*;

public class Graph {
    private Map<Node, List<Node>> adjacencyList = new HashMap<>();

    public Graph(Maze maze) {
        for (int y = 0; y < maze.getSizeY(); y++) {
            for (int x = 0; x < maze.getSizeX(); x++) {
                Position position = new Position(x, y);

                if (!maze.isWall(position)) {
                    Node node = new Node(position);
                    adjacencyList.putIfAbsent(node, new ArrayList<>());
                    
                    for (Position neighborPos : maze.getNeighborPosition(position)) {
                        if (!maze.isWall(neighborPos)) {
                            Node neighborNode = new Node(neighborPos);
                            adjacencyList.get(node).add(neighborNode);
                        }
                    }
                }
            }
        }
    }

    public List<Node> getNeighbors(Node node) {
        return adjacencyList.getOrDefault(node, Collections.emptyList());
    }
}

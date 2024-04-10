package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.nio.file.Paths;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class GraphTest {
    private Maze maze;
    private Graph graph;

    @BeforeEach
    void setUp() throws Exception {
        String pathToMaze = Paths.get("examples", "small.maz.txt").toString();
        maze = new Maze(pathToMaze);
        graph = new Graph(maze);
    }

    @Test
    void graphCorrectlyRepresentsMaze() {
        Node node = new Node(new Position(3, 3)); 
        List<Node> neighbors = graph.getNeighbors(node);

        int expectedNeighborCount = 4;
        assertEquals(expectedNeighborCount, neighbors.size(), "A Node in the middle of the maze should have 4 neighbors.");
    }

    @Test
    void graphHandlesWallsCorrectly() {
        
        Node wallNode = new Node(new Position(0, 0)); 
        List<Node> neighbors = graph.getNeighbors(wallNode);

        assertTrue(neighbors.isEmpty(), "A wall node should have 0 neighbors.");
    }

}

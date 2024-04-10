package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BFSTraversalTest {

    private BFSTraversal bfsSolver = new BFSTraversal();

    @Test
    void testSolveTinyMaze() {
        try {
            Maze maze = new Maze("./examples/tiny.maz.txt"); 
            Path solution = bfsSolver.traverse(new Graph(maze), new Node(maze.getStart()), new Node(maze.getEnd()));
            
            assertEquals("3F L 4F R 3F", solution.getFactorizedForm(), "solution should be the same as the expected path");

        } catch (Exception e) {
            fail("exception: " + e.getMessage());
        }
    }

    @Test
    void testSolveSmallMaze() {
        try {
            Maze maze = new Maze("./examples/small.maz.txt"); 
            Path solution = bfsSolver.traverse(new Graph(maze), new Node(maze.getStart()), new Node(maze.getEnd()));

            assertEquals("F L F R 2F L 6F R 4F R 2F L 2F R 2F L F", solution.getFactorizedForm(), "solution should be the same as the expected path");
            
        } catch (Exception e) {
            fail("exception: " + e.getMessage());
        }
    }
}

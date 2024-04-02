package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BFSSolverTest {

    private BFSSolver solver;

    @BeforeEach
    void setUp() {
        solver = new BFSSolver();
    }

    @Test
    void testSolveSmallMaze() {
        try {
            Maze maze = new Maze("./examples/small.maz.txt");
            Path solution = solver.solve(maze);

            assertNotNull(solution, "Solution should not be null.");
            assertFalse(solution.getPathSteps().isEmpty(), "Solution path should not be empty.");
            assertTrue(maze.validatePath(solution), "Solution path should be valid.");
        } catch (Exception e) {
            fail("Exception should not be thrown: " + e.getMessage());
        }
    }

    @Test
    void testSolveNoSolutionMaze() {
        Exception exception = assertThrows(Exception.class, () -> {
            Maze maze = new Maze("./examples/nosolution.maz.txt");
            solver.solve(maze);
        });
    }

}

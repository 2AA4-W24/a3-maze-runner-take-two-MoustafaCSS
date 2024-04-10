package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MazeTest {
    Maze maze;

    @BeforeEach
    void setUp() throws Exception {
        maze = new Maze("./examples/small.maz.txt");
    }

    @Test
    void testIsValidMove() {
        assertTrue(maze.isValidMove(new Position(1, 1)));
        assertFalse(maze.isValidMove(new Position(0, 0)));
    }

    @Test
    void testGetNeighborPosition() {
        List<Position> neighbors = maze.getNeighborPosition(new Position(1, 1));
        assertFalse(neighbors.isEmpty(), "Neighbors should not be empty");
    }

    @Test
    void testGetMazeLoadingTime() {
        String loadingTime = maze.getMazeLoadingTime();
        assertNotNull(loadingTime, "Loading time should not be null");
        assertTrue(loadingTime.matches("\\d+\\.\\d{2}"), "Loading time should be two decimal places");
    }
}

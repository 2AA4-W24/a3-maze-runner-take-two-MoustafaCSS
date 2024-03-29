package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class BFSSolver implements MazeSolver {
    private static final Logger logger = LogManager.getLogger(BFSSolver.class);

    private static class State {
        Position position;
        Direction direction;
        Path path;

        State(Position position, Direction direction, Path path) {
            this.position = position;
            this.direction = direction;
            this.path = path;
        }
    }

    @Override
    public Path solve(Maze maze) {
        Queue<State> queue = new LinkedList<>();
        Set<Position> visited = new HashSet<>();

        queue.add(new State(maze.getStart(), Direction.RIGHT, new Path())); //direction always right? ask prof
        visited.add(maze.getStart());

        while (!queue.isEmpty()) {
            State currentState = queue.poll();
            Position currentPosition = currentState.position;
            Direction currentDirection = currentState.direction;
            Path currentPath = currentState.path;

            if (currentPosition.equals(maze.getEnd())) {
                return currentPath;
            }

        }

        logger.error("No path found from start to end in the maze.");
        return new Path(); // Return an empty path if no path is found
    }
}

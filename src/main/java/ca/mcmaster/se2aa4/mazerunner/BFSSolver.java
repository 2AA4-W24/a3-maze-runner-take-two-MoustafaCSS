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

            Position forwardPos = currentPosition.move(currentDirection);   //F
            if (!visited.contains(forwardPos) && maze.isValidMove(forwardPos)) {
                visited.add(forwardPos);
                Path newPath = new Path(currentPath.getFactorizedForm());
                newPath.addStep('F');
                queue.add(new State(forwardPos, currentDirection, newPath));
            }

            Direction rightDirection = currentDirection.turnRight();    //RF
            Position rightPos = currentPosition.move(rightDirection);
            if (!visited.contains(rightPos) && maze.isValidMove(rightPos)) {
                visited.add(rightPos);
                Path newPath = new Path(currentPath.getFactorizedForm());
                newPath.addStep('R'); 
                newPath.addStep('F'); 
                queue.add(new State(rightPos, rightDirection, newPath));
            }

            Direction leftDirection = currentDirection.turnLeft();  //LF
            Position leftPos = currentPosition.move(leftDirection);
            if (!visited.contains(leftPos) && maze.isValidMove(leftPos)) {
                visited.add(leftPos);
                Path newPath = new Path(currentPath.getFactorizedForm());
                newPath.addStep('L'); 
                newPath.addStep('F'); 
                queue.add(new State(leftPos, leftDirection, newPath));
            }
        }

        logger.error("No path found");
        return new Path(); 
    }
}

package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*; //ask prof about using * because it is not used in other classes

public class BFSSolver implements MazeSolver {
    
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Path solve(Maze maze) {
        Path path = new Path();
        Queue<Position> queue = new LinkedList<>();
        queue.add(maze.getStart());

        Map<Position, Position> predecessors = new HashMap<>();
        boolean[][] visited = new boolean[maze.getSizeY()][maze.getSizeX()];
        visited[maze.getStart().y()][maze.getStart().x()] = true;

        while (!queue.isEmpty()) {
            Position current = queue.poll();
            /////////

        }

        logger.error("No path found"); //if you reach here, no path has been found to solve the maze
        return null; 
    }

}

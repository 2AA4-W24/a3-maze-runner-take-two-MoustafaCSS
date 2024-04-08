package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Maze {
    private static final Logger logger = LogManager.getLogger();

    private final List<List<Boolean>> maze = new ArrayList<>();

    private final Position start;
    private final Position end;
    private long mazeLoadingTime;

    public Maze(String filePath) throws Exception {
        long startTime = System.currentTimeMillis();
        logger.debug("Reading the maze from file " + filePath);
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            List<Boolean> newLine = new ArrayList<>();
            for (int idx = 0; idx < line.length(); idx++) {
                if (line.charAt(idx) == '#') {
                    newLine.add(true);
                } else if (line.charAt(idx) == ' ') {
                    newLine.add(false);
                }
            }
            maze.add(newLine);
        }
        start = findStart();
        end = findEnd();
        mazeLoadingTime = System.currentTimeMillis() - startTime;
    }

    private Position findStart() throws Exception {
        for (int i = 0; i < maze.size(); i++) {
            Position pos = new Position(0, i);
            if (!isWall(pos)) {
                return pos;
            }
        }
        throw new Exception("Invalid maze (no start position available)");
    }

    private Position findEnd() throws Exception {
        for (int i = 0; i < maze.size(); i++) {
            Position pos = new Position(maze.getFirst().size() - 1, i);
            if (!isWall(pos)) {
                return pos;
            }
        }
        throw new Exception("Invalid maze (no end position available)");
    }

    public Boolean isWall(Position pos) {
        return maze.get(pos.y()).get(pos.x());
    }

    public Position getStart() {
        return start;
    }

    public Position getEnd() {
        return end;
    }

    public int getSizeX() {     //horizontal size
        return this.maze.getFirst().size();
    }

    public int getSizeY() {     //vertical size
        return this.maze.size();
    }

    public Boolean validatePath(Path path) {        //return if path is valid
        return validatePathDir(path, getStart(), Direction.RIGHT, getEnd()) || validatePathDir(path, getEnd(), Direction.LEFT, getStart());
    }

    private Boolean validatePathDir(Path path, Position startPos, Direction startDir, Position endPos) {        //check if path is valid
        Position pos = startPos;
        Direction dir = startDir;
        for (char c : path.getPathSteps()) {
            switch (c) {
                case 'F' -> {
                    pos = pos.move(dir);

                    if (pos.x() >= getSizeX() || pos.y() >= getSizeY() || pos.x() < 0 || pos.y() < 0) {
                        return false;
                    }
                    if (isWall(pos)) {
                        return false;
                    }
                }
                case 'R' -> dir = dir.turnRight();
                case 'L' -> dir = dir.turnLeft();
            }
            logger.debug("Current Position: " + pos);
        }
        
        return pos.equals(endPos);
    }

    public boolean isValidMove(Position position) { 
        if (position.x() < 0 || position.x() >= getSizeX() ||
            position.y() < 0 || position.y() >= getSizeY()) {
            return false; // Out of bounds
        }
        return !isWall(position);
    }

    public List<Position> getNeighborPosition(Position position) {
        List<Position> neighbors = new ArrayList<>();

        Position up = new Position(position.x(), position.y() - 1);
        if (isValidMove(up) && !isWall(up)) {
            neighbors.add(up);
        }

        Position down = new Position(position.x(), position.y() + 1);
        if (isValidMove(down) && !isWall(down)) {
            neighbors.add(down);
        }

        Position left = new Position(position.x() - 1, position.y());
        if (isValidMove(left) && !isWall(left)) {
            neighbors.add(left);
        }

        Position right = new Position(position.x() + 1, position.y());
        if (isValidMove(right) && !isWall(right)) {
            neighbors.add(right);
        }

        return neighbors;
    }

    public String getMazeLoadingTime() {
        double mazeLoadingTimeDouble = mazeLoadingTime; 
        return String.format("%.2f", mazeLoadingTimeDouble);
    }
}

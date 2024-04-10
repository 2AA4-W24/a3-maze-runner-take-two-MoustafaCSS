package ca.mcmaster.se2aa4.mazerunner;

public class MazeSolvingResults {
    private final Path path;
    private final long executionTime; 

    public MazeSolvingResults(Path path, long executionTime) {
        this.path = path;
        this.executionTime = executionTime;
    }

    public Path getPath() {
        return path;
    }

    public long getExecutionTime() {
        return executionTime;
    }
}
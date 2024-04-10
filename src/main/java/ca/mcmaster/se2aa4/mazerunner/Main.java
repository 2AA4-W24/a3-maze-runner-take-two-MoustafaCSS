package ca.mcmaster.se2aa4.mazerunner;

import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("** Starting Maze Runner");
        CommandLineParser parser = new DefaultParser();

        CommandLine cmd = null;
        try {
            cmd = parser.parse(getParserOptions(), args);
            String filePath = cmd.getOptionValue('i');
            Maze maze = new Maze(filePath);

            if (cmd.getOptionValue("p") != null) {
                logger.info("Validating path");
                Path path = new Path(cmd.getOptionValue("p"));
                if (maze.validatePath(path)) {
                    System.out.println("correct path");
                } else {
                    System.out.println("incorrect path");
                }
            } else {
                String method = cmd.getOptionValue("method", "BFS");
                Path path = solveMaze(method, maze);
                System.out.println(path.getFactorizedForm());

                if (cmd.hasOption("baseline")) {
                    String baseline = cmd.getOptionValue("baseline");
                    
                    System.out.println("Time to load in the maze from the file: " + maze.getMazeLoadingTime() + " milliseconds" );
                    
                    MazeSolvingResults baselineResult = benchmarkMazeSolving(baseline, maze);
                    MazeSolvingResults methodResult = benchmarkMazeSolving(method, maze); 
                
                    System.out.println("Time to solve the maze using the " + method + " method: " + methodResult.getExecutionTime() + " ms");
                    System.out.println("Time to solve the maze using the " + baseline + " method: " + baselineResult.getExecutionTime() + " ms");
                
                    SpeedUp speedUpCalculator = new SpeedUp();
                    String speedUp = speedUpCalculator.calculateSpeedUp(methodResult.getPath(), baselineResult.getPath());
                    System.out.println("The " + method + " algorithm is " + speedUp + " times faster than " + baseline);
                }
            }
        } catch (Exception e) {
            System.err.println("MazeSolver failed.  Reason: " + e.getMessage());
            logger.error("MazeSolver failed.  Reason: " + e.getMessage());
            logger.error("PATH NOT COMPUTED");
        }

        logger.info("End of MazeRunner");
    }

    private static MazeSolvingResults benchmarkMazeSolving(String method, Maze maze) throws Exception {
        long startTime = System.currentTimeMillis();
        Path path = solveMaze(method, maze);
        long solvingTime = System.currentTimeMillis() - startTime;
        return new MazeSolvingResults(path, solvingTime);
    }

    private static Path solveMaze(String method, Maze maze) throws Exception {
        MazeSolver solver = null;

        switch (method) {
            case "righthand" -> {
                logger.debug("RightHand algorithm chosen.");
                solver = new RightHandSolver();
            }
            case "tremaux" -> {
                logger.debug("Tremaux algorithm chosen.");
                solver = new TremauxSolver();
            }
            case "BFS" -> {
                logger.debug("BFS algorithm chosen.");
                solver = new GraphBasedSolver(new BFSTraversal()); 
            }
            default -> {
                throw new Exception("Maze solving method '" + method + "' not supported."); 
            }
        }
    
        logger.info("Computing path");
        return solver.solve(maze);

    }

    private static Options getParserOptions() {
        Options options = new Options();

        Option fileOption = new Option("i", true, "File that contains maze");
        fileOption.setRequired(true);
        options.addOption(fileOption);

        options.addOption(new Option("p", true, "Path to be verified in maze"));
        options.addOption(new Option("method", true, "Specify which path computation algorithm will be used"));
        options.addOption(new Option("baseline", true, "Specify which path computation algorithm will be used as the baseline"));

        return options;
    }
}
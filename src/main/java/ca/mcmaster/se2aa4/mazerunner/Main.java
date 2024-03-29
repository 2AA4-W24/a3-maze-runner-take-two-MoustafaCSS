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
        try {       //tech debt too clumpy maybe fix
            cmd = parser.parse(getParserOptions(), args);
            String filePath = cmd.getOptionValue("i");
            String baselineMethod = cmd.getOptionValue("baseline");
            String method = cmd.getOptionValue("method", "righthand"); //2x

            long startTime = System.currentTimeMillis();
            Maze maze = new Maze(filePath);     
            long mazeLoadingTime = System.currentTimeMillis() - startTime;
            logger.info(String.format("Maze loading time: %.2f ms", (double) mazeLoadingTime));

            if (cmd.getOptionValue("p") != null) {
                logger.info("Validating path");
                Path path = new Path(cmd.getOptionValue("p"));
                if (maze.validatePath(path)) {
                    System.out.println("correct path");
                } else {
                    System.out.println("incorrect path");
                }
            } else {
                method = cmd.getOptionValue("method", "righthand"); //2x
                Path path = solveMaze(method, maze);
                System.out.println(path.getFactorizedForm());
            }
            Path methodPath = benchmarkMazeSolving(method, maze);

            if (baselineMethod != null) {
                Path baselinePath = benchmarkMazeSolving(baselineMethod, maze);
                double speedup = calculateSpeedup(baselinePath, methodPath);
                System.out.printf("Speedup: %.2f\n", speedup);
            }
        } catch (ParseException e) {
            logger.error("Parsing command line failed. Reason: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("MazeSolver failed.  Reason: " + e.getMessage());
            logger.error("MazeSolver failed.  Reason: " + e.getMessage());
            logger.error("PATH NOT COMPUTED");
        }

        logger.info("End of MazeRunner");
    }

    private static Path benchmarkMazeSolving(String method, Maze maze) throws Exception {
        long startTime = System.currentTimeMillis();
        Path path = solveMaze(method, maze);
        long solvingTime = System.currentTimeMillis() - startTime;
        logger.info(String.format("Time using %s: %.2f ms", method, (double) solvingTime));
        return path;
    }

    private static double calculateSpeedup(Path baselinePath, Path methodPath) {
        int baselineInstructions = baselinePath.getPathSteps().size();
        int methodInstructions = methodPath.getPathSteps().size();
        return (double) baselineInstructions / methodInstructions;
    }

    private static Path solveMaze(String method, Maze maze) throws Exception {
        MazeSolver solver;
        switch (method) {
            case "righthand":
                solver = new RightHandSolver();
                break;
            case "tremaux":
                solver = new TremauxSolver();
                break;
            case "BFS":
                solver = new BFSSolver();
                break;
            default:
                throw new Exception("Maze solving method '" + method + "' not supported.");
        }
        return solver.solve(maze);
    }

    private static Options getParserOptions() {
        Options options = new Options();

        options.addOption(new Option("i", true, "File that contains maze"));
        options.addOption(new Option("p", true, "Path to be verified in maze"));
        options.addOption(new Option("method", true, "Specify which path computation algorithm will be used"));
        options.addOption(new Option("baseline", true, "Baseline maze solving method for comparison"));

        return options;
    }
}

package ca.mcmaster.se2aa4.mazerunner;

public class Metrics {

    public String calculateSpeedUp(Path baselinePath, Path methodPath) {
        int baselineLength = baselinePath.getPathSize(baselinePath);
        int methodLength = methodPath.getPathSize(methodPath);
        double speedUp = (double) methodLength / baselineLength;
        return String.format("%.2f", speedUp);
    }
          
}
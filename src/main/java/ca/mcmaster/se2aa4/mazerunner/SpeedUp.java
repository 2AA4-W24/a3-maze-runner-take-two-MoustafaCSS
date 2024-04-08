package ca.mcmaster.se2aa4.mazerunner;

public class SpeedUp {

    public String calculatepeedUp(Path baselinePath, Path methodPath) {
        int baselineLength = baselinePath.getPathSize(baselinePath);
        int methodLength = methodPath.getPathSize(methodPath);
        double speedUp = (double) methodLength / baselineLength;
        return String.format("%.2f", speedUp);
    }
          
}
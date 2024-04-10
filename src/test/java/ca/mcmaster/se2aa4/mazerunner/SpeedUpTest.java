package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SpeedUpTest {

    @Test
    void calculateSpeedUpTest() {
        Path baselinePath = new Path();
        for (int i = 0; i < 10; i++) {
            baselinePath.addStep('F'); 
        }

        Path methodPath = new Path();
        for (int i = 0; i < 5; i++) {
            methodPath.addStep('F'); 
        }

        SpeedUp speedUpCalculator = new SpeedUp();
        String speedUpResult = speedUpCalculator.calculateSpeedUp(methodPath, baselinePath);
        assertEquals("2.00", speedUpResult, "speedup should be '2.00' for paths of length 10 and 5.");
    }
}

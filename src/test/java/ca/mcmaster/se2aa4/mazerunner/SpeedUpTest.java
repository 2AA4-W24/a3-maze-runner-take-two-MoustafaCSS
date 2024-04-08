package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SpeedUpTest {

    @Test
    void calculateSpeedUpTest() {
        // Create baseline and method paths with known sizes
        Path baselinePath = new Path();
        for (int i = 0; i < 100; i++) {
            baselinePath.addStep('F'); // Simulate a path of length 100
        }

        Path methodPath = new Path();
        for (int i = 0; i < 50; i++) {
            methodPath.addStep('F'); // Simulate a path of length 50
        }

        SpeedUp speedUpCalculator = new SpeedUp();
        String speedUpResult = speedUpCalculator.calculateSpeedUp(methodPath, baselinePath);

        // Since the method path is half the length of the baseline path, expect a speedUp of 2.00
        assertEquals("2.00", speedUpResult, "The speedUp calculation should return '2.00' for paths of length 100 and 50.");
    }
}

package TrainingUtils;

import edu.wpi.first.wpilibj.smartdashboard.MechanismLigament2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismRoot2d;
import edu.wpi.first.wpilibj.util.Color8Bit;

import static frc.robot.Constants.ROBOT_MECHANISM;

public class LedConstants {

    public static class LedSimulationConstants {

        public static final MechanismRoot2d ledRoot = ROBOT_MECHANISM.getRoot("Led", 0.5, 1.1);

        public static final double ledSize = 0.06;

        public static void initializeLedMechanism(MechanismLigament2d[] led) {
            MechanismRoot2d lastRoot = ledRoot;
            for (int i = 0; i < led.length; i++) {
                led[i] = lastRoot.append(
                        new MechanismLigament2d("led" + i, ledSize, 0, 5, new Color8Bit())
                );
                lastRoot = ROBOT_MECHANISM.getRoot("Led" + i, 0.5 + (i + 1) * ledSize, 1.1);
            }
        }
    }
}
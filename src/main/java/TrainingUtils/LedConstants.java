package TrainingUtils;

import edu.wpi.first.wpilibj.smartdashboard.Mechanism2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismLigament2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismRoot2d;
import edu.wpi.first.wpilibj.util.Color8Bit;
import org.littletonrobotics.junction.mechanism.LoggedMechanism2d;
import org.littletonrobotics.junction.mechanism.LoggedMechanismLigament2d;
import org.littletonrobotics.junction.mechanism.LoggedMechanismRoot2d;

public class LedConstants {

    public static class LedSimulationConstants {

        public static final LoggedMechanism2d ROBOT_MECHANISM = new LoggedMechanism2d(2, 2);

        public static final LoggedMechanismRoot2d ledRoot = ROBOT_MECHANISM.getRoot("Led", 0.5, 1.1);

        public static final double ledSize = 0.06;

        public static void initializeLedMechanism(LoggedMechanismLigament2d[] led) {
            LoggedMechanismRoot2d lastRoot = ledRoot;
            for (int i = 0; i < led.length; i++) {
                led[i] = lastRoot.append(
                        new LoggedMechanismLigament2d("led" + i, ledSize, 0, 5, new Color8Bit())
                );
                lastRoot = ROBOT_MECHANISM.getRoot("Led" + i, 0.5 + (i + 1) * ledSize, 1.1);
            }
        }
    }
}
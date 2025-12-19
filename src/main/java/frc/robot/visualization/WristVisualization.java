package frc.robot.visualization;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;
import frc.robot.subsystems.wrist.Wrist;
import org.littletonrobotics.junction.mechanism.LoggedMechanismLigament2d;
import org.littletonrobotics.junction.mechanism.LoggedMechanismRoot2d;

public class WristVisualization extends VisualizedSubsystem {
    private final Wrist wrist;

    public WristVisualization() {
        wrist = Wrist.getInstance();
    }

    @Override
    void updateVisualization() {
        WristVisualizationMechanism.WRIST.setAngle(wrist.getWristAngle());
    }

    public class WristVisualizationMechanism {
        private static final double X_POSITION = 3;
        private static final double Y_POSITION = 0.1;
        private static final double LIGAMENT_LENGTH = 0.375;
        private static final double LIGAMENT_ANGLE = 0;
        private static final double LIGAMENT_LINE_WIDTH = 5;

        public static final Color8Bit WRIST_LIGAMENT_COLOR = new Color8Bit(Color.kSkyBlue);
        public static final LoggedMechanismRoot2d wristRoot = ROBOT_MECHANISM.getRoot("wrist", X_POSITION, Y_POSITION);
        public static final LoggedMechanismLigament2d WRIST = wristRoot.append(
            new LoggedMechanismLigament2d(
                "wrist",
                LIGAMENT_LENGTH, LIGAMENT_ANGLE,
                LIGAMENT_LINE_WIDTH, WRIST_LIGAMENT_COLOR
            )
        );
    }
}

package Lrobot.Visualization;

import Lrobot.elevator.Elevator;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;
import frc.robot.subsystems.wrist.Wrist;
import org.littletonrobotics.junction.mechanism.LoggedMechanismLigament2d;
import org.littletonrobotics.junction.mechanism.LoggedMechanismRoot2d;

public class WristVisualization extends VisualizedSubsystem {

    private Wrist wrist;

    public WristVisualization() {
        wrist = Wrist.getInstance();
    }

    @Override
    void updateVisualization() {
        WristVisualizationMechanism.WRIST.setAngle(wrist.getWristAngle());
    }

    public class WristVisualizationMechanism {
        private static final double WRIST_X_POSITION = 3;
        private static final double WRIST_Y_POSITION = 0.5;
        private static final double WRIST_LIGAMENT_LENGTH = 0.5;
        private static final double WRIST_LIGAMENT_ANGLE = 90;
        private static final double WRIST_LIGAMENT_LINE_WIDTH = 8;

        public static final Color8Bit WRIST_LIGAMENT_COLOR = new Color8Bit(Color.kSkyBlue);
        public static final LoggedMechanismRoot2d wristRoot = ROBOT_MECHANISM.getRoot("wrist", WRIST_X_POSITION, WRIST_Y_POSITION);
        public static final LoggedMechanismLigament2d WRIST = wristRoot.append(
                new LoggedMechanismLigament2d(
                        "wrist",
                        WRIST_LIGAMENT_LENGTH, WRIST_LIGAMENT_ANGLE,
                        WRIST_LIGAMENT_LINE_WIDTH, WRIST_LIGAMENT_COLOR
                )
        );
    }
}

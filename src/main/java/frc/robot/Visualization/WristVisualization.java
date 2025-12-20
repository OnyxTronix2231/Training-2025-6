package frc.robot.Visualization;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;
import frc.robot.subsystems.arm.Arm;
import org.littletonrobotics.junction.mechanism.LoggedMechanismLigament2d;
import org.littletonrobotics.junction.mechanism.LoggedMechanismRoot2d;

import static frc.robot.subsystems.arm.wrist.WristConstants.WRIST_VISUALIZATION_OFFSET;

public class WristVisualization extends VisualizedSubsystem {
    private final Arm arm;

    public WristVisualization() {
        arm = Arm.getInstance();
    }

    @Override
    void updateVisualization() {
        WristVisualizationMechanism.WRIST.setAngle(arm.getWristAngle() + WRIST_VISUALIZATION_OFFSET);
    }

    public class WristVisualizationMechanism {
        private static final double WRIST_X_POSITION = 1;
        private static final double WRIST_Y_POSITION = 1;
        private static final double WRIST_LIGAMENT_LENGTH = 0.375;
        private static final double WRIST_LIGAMENT_ANGLE = 0;
        private static final double WRIST_LIGAMENT_LINE_WIDTH = 5;
        public static final Color8Bit WRIST_LIGAMENT_COLOR = new Color8Bit(Color.kBlue);

        public static final LoggedMechanismRoot2d wristRoot = ROBOT_MECHANISM.getRoot("wrist", WRIST_X_POSITION, WRIST_Y_POSITION);

        public static final LoggedMechanismLigament2d WRIST = wristRoot.append(
            new LoggedMechanismLigament2d("wrist",
                WRIST_LIGAMENT_LENGTH,
                WRIST_LIGAMENT_ANGLE,
                WRIST_LIGAMENT_LINE_WIDTH,
                WRIST_LIGAMENT_COLOR
            )
        );


    }
}

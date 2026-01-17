package frc.robot.Visualization;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;
import frc.robot.subsystems.arm.Arm;
import org.littletonrobotics.junction.mechanism.LoggedMechanismLigament2d;

public class WristVisualization extends VisualizedSubsystem {
    private final Arm arm;

    public WristVisualization() {
        arm = Arm.getInstance();
    }

    @Override
    void updateVisualization() {
        WristVisualizationMechanism.WRIST.setAngle(arm.getWristAngle());
    }

    public class WristVisualizationMechanism {
        private static final double WRIST_LIGAMENT_LENGTH = 0.5;
        private static final double WRIST_LIGAMENT_ANGLE = 0;
        private static final double WRIST_LIGAMENT_LINE_WIDTH = 8;
        public static final Color8Bit WRIST_LIGAMENT_COLOR = new Color8Bit(Color.kOrange);
        public static final LoggedMechanismLigament2d WRIST = ElevatorVisualization.ElevatorVisualizationMechanism.ELEVATOR.append(
                new LoggedMechanismLigament2d("WRIST", WRIST_LIGAMENT_LENGTH, WRIST_LIGAMENT_ANGLE,
                        WRIST_LIGAMENT_LINE_WIDTH, WRIST_LIGAMENT_COLOR));
    }
}

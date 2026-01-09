package frc.robot.Visualization;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;
import frc.robot.subsystems.arm.ArmSubsystem;
import org.littletonrobotics.junction.mechanism.LoggedMechanismLigament2d;
import org.littletonrobotics.junction.mechanism.LoggedMechanismRoot2d;

public class WristVisualization extends VisualizedSubsystem {
    private final ArmSubsystem armSubsystem;

    public WristVisualization() {
        armSubsystem = ArmSubsystem.getInstance();
    }

    @Override
    void updateVisualization() {
        WristVisualizationMechanism.WRIST.setAngle(armSubsystem.getWristAngle());
    }

    public class WristVisualizationMechanism {
        public static final double WRIST_X_POSITION = 0;
        public static final double WRIST_Y_POSITION = 0;
        public static final LoggedMechanismRoot2d wristRoot = ROBOT_MECHANISM.getRoot("WRIST", WRIST_X_POSITION, WRIST_Y_POSITION);

        private static final double WRIST_LIGAMENT_LENGTH = 0.5;
        private static final double WRIST_LIGAMENT_ANGLE = -90;
        private static final double WRIST_LIGAMENT_LINE_WIDTH = 8;
        public static final Color8Bit WRIST_LIGAMENT_COLOR = new Color8Bit(Color.kOrange);
        public static final LoggedMechanismLigament2d WRIST = ElevatorVisualization.ElevatorVisualizationMechanism.elevatorRoot.append(
                new LoggedMechanismLigament2d("WRIST", WRIST_LIGAMENT_LENGTH, WRIST_LIGAMENT_ANGLE,
                        WRIST_LIGAMENT_LINE_WIDTH, WRIST_LIGAMENT_COLOR));
    }
}

package frc.robot.Visualization;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;
import frc.robot.subsystems.arm.Arm;
import org.littletonrobotics.junction.mechanism.LoggedMechanismLigament2d;
import org.littletonrobotics.junction.mechanism.LoggedMechanismRoot2d;

import static frc.robot.subsystems.arm.elevator.ElevatorConstants.ELEVATOR_VISUALIZATION_OFFSET;
import static frc.robot.subsystems.arm.wrist.WristConstants.WRIST_VISUALIZATION_OFFSET;

public class ArmVisualization extends VisualizedSubsystem {
    private final Arm arm;

    public ArmVisualization() {
        arm = Arm.getInstance();
    }

    @Override
    void updateVisualization() {
        ArmVisualizationMechanism.ELEVATOR.setLength(arm.getElevatorLength() + ELEVATOR_VISUALIZATION_OFFSET);
        ArmVisualizationMechanism.WRIST.setAngle(arm.getWristAngle() + WRIST_VISUALIZATION_OFFSET);
    }

    public class ArmVisualizationMechanism {
        private static final double ELEVATOR_X_POSITION = 2;
        private static final double ELEVATOR_Y_POSITION = 1;
        private static final double ELEVATOR_LIGAMENT_LENGTH = 0.375;
        private static final double ELEVATOR_LIGAMENT_ANGLE = 90;
        private static final double ELEVATOR_LIGAMENT_LINE_WIDTH = 5;
        public static final Color8Bit ELEVATOR_LIGAMENT_COLOR = new Color8Bit(Color.kRed);

        public static final LoggedMechanismRoot2d elevatorRoot = ROBOT_MECHANISM.getRoot("elevator", ELEVATOR_X_POSITION, ELEVATOR_Y_POSITION);

        public static final LoggedMechanismLigament2d ELEVATOR = elevatorRoot.append(
            new LoggedMechanismLigament2d("elevator",
                ELEVATOR_LIGAMENT_LENGTH, ELEVATOR_LIGAMENT_ANGLE,
                ELEVATOR_LIGAMENT_LINE_WIDTH, ELEVATOR_LIGAMENT_COLOR
            )
        );

        private static final double WRIST_LIGAMENT_LENGTH = 0.375;
        private static final double WRIST_LIGAMENT_ANGLE = 0;
        private static final double WRIST_LIGAMENT_LINE_WIDTH = 5;
        public static final Color8Bit WRIST_LIGAMENT_COLOR = new Color8Bit(Color.kBlue);

        public static final LoggedMechanismLigament2d WRIST = ELEVATOR.append(
            new LoggedMechanismLigament2d("wrist",
                WRIST_LIGAMENT_LENGTH, WRIST_LIGAMENT_ANGLE,
                WRIST_LIGAMENT_LINE_WIDTH, WRIST_LIGAMENT_COLOR
            )
        );
    }
}

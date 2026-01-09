package frc.robot.Visualization;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;
import frc.robot.subsystems.arm.ArmSubsystem;
import org.littletonrobotics.junction.mechanism.LoggedMechanismLigament2d;
import org.littletonrobotics.junction.mechanism.LoggedMechanismRoot2d;

public class ElevatorVisualization extends VisualizedSubsystem {
    private final ArmSubsystem arcSubsystem;

    public ElevatorVisualization() {
        arcSubsystem = ArmSubsystem.getInstance();
    }

    @Override
    void updateVisualization() {
        ElevatorVisualizationMechanism.ELEVATOR.setLength(arcSubsystem.getElevatorLength() + 0.2);
    }

    public class ElevatorVisualizationMechanism {
        public static final double ELEVATOR_X_POSITION = 3;
        public static final double ELEVATOR_Y_POSITION = 0;
        public static final LoggedMechanismRoot2d elevatorRoot = ROBOT_MECHANISM.getRoot("elevator", ELEVATOR_X_POSITION, ELEVATOR_Y_POSITION);

        private static final double ELEVATOR_LIGAMENT_LENGTH = 0.2;
        private static final double ELEVATOR_LIGAMENT_ANGLE = 90;
        private static final double ELEVATOR_LIGAMENT_LINE_WIDTH = 8;
        public static final Color8Bit ELEVATOR_LIGAMENT_COLOR = new Color8Bit(Color.kSkyBlue);
        public static final LoggedMechanismLigament2d ELEVATOR = elevatorRoot.append(new LoggedMechanismLigament2d("elevator", ELEVATOR_LIGAMENT_LENGTH, ELEVATOR_LIGAMENT_ANGLE, ELEVATOR_LIGAMENT_LINE_WIDTH, ELEVATOR_LIGAMENT_COLOR));
    }
}

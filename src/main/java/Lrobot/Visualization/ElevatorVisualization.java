package Lrobot.Visualization;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;

import org.littletonrobotics.junction.mechanism.LoggedMechanismLigament2d;
import org.littletonrobotics.junction.mechanism.LoggedMechanismRoot2d;

import java.util.function.DoubleSupplier;

import static Lrobot.elevator.ElevatorConstants.*;

public class ElevatorVisualization extends VisualizedSubsystem {
    DoubleSupplier heightSupplier;

    public ElevatorVisualization(DoubleSupplier heightSupplier) {
        this.heightSupplier = heightSupplier;
    }

    @Override
    void updateVisualization() {
        ElevatorVisualizationMechanism.ELEVATOR.setLength(heightSupplier.getAsDouble() +
                ELEVATOR_VISUALIZATION_OFFSET);
    }


    public class ElevatorVisualizationMechanism {
        private static final double ELEVATOR_X_POSITION = 3;
        private static final double ELEVATOR_Y_POSITION = 0.1;
        private static final double ELEVATOR_LIGAMENT_LENGTH = 0;
        private static final double ELEVATOR_LIGAMENT_ANGLE = 90;
        private static final double ELEVATOR_LIGAMENT_LINE_WIDTH = 8;


        public static final Color8Bit ELEVATOR_LIGAMENT_COLOR = new Color8Bit(Color.kSkyBlue);
        public static final LoggedMechanismRoot2d elevatorRoot = ROBOT_MECHANISM.getRoot("elevator", ELEVATOR_X_POSITION, ELEVATOR_Y_POSITION);
        public static final LoggedMechanismLigament2d ELEVATOR = elevatorRoot.append(
                new LoggedMechanismLigament2d(
                        "elevator",
                        ELEVATOR_LIGAMENT_LENGTH, ELEVATOR_LIGAMENT_ANGLE,
                        ELEVATOR_LIGAMENT_LINE_WIDTH, ELEVATOR_LIGAMENT_COLOR
                )
        );
    }
}
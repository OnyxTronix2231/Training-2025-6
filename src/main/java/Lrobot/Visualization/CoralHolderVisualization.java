package Lrobot.Visualization;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;
import frc.robot.subsystems.coralHolder.CoralHolder;
import org.littletonrobotics.junction.mechanism.LoggedMechanismLigament2d;
import org.littletonrobotics.junction.mechanism.LoggedMechanismRoot2d;

public class CoralHolderVisualization extends VisualizedSubsystem {
    private CoralHolder coralHolder;

    public CoralHolderVisualization(CoralHolder coralHolder) {
        this.coralHolder = coralHolder;
    }

    @Override
    void updateVisualization() {
        for (LoggedMechanismLigament2d wheelOneLigament : CoralHolderVisualizationMechanism.WHEEL_ONE_LIGAMENTS) {
            wheelOneLigament.setAngle(wheelOneLigament.getAngle() + (500 * (coralHolder.getSpinSpeed() / 628.3)));
        }
    }

    public class CoralHolderVisualizationMechanism {
        private static final double X_POSITION = 3;
        private static final double Y_POSITION = 2;
        private static final double LIGAMENT_LENGTH = 0.375;
        private static final double LIGAMENT_LINE_WIDTH = 5;

        public static final Color8Bit WHEEL_COLOR = new Color8Bit(Color.kOrangeRed);

        public static final LoggedMechanismRoot2d firstWheelRoot = ROBOT_MECHANISM.getRoot("firstWheel", X_POSITION, Y_POSITION);


        public static final LoggedMechanismLigament2d WHEEL_ONE_PART_ONE = firstWheelRoot.append(
                new LoggedMechanismLigament2d(
                        "wheelOnePartOne",
                        LIGAMENT_LENGTH, 0,
                        LIGAMENT_LINE_WIDTH, WHEEL_COLOR
                )
        );

        public static final LoggedMechanismLigament2d WHEEL_ONE_PART_TWO = firstWheelRoot.append(
                new LoggedMechanismLigament2d(
                        "wheelOnePartTwo",
                        LIGAMENT_LENGTH, 120,
                        LIGAMENT_LINE_WIDTH, WHEEL_COLOR
                )
        );

        public static final LoggedMechanismLigament2d WHEEL_ONE_PART_THREE = firstWheelRoot.append(
                new LoggedMechanismLigament2d(
                        "wheelOnePartThree",
                        LIGAMENT_LENGTH, 240,
                        LIGAMENT_LINE_WIDTH, WHEEL_COLOR
                )
        );

        public static final LoggedMechanismLigament2d[] WHEEL_ONE_LIGAMENTS = new LoggedMechanismLigament2d[]{
                WHEEL_ONE_PART_ONE, WHEEL_ONE_PART_TWO, WHEEL_ONE_PART_THREE
        };
    }
}

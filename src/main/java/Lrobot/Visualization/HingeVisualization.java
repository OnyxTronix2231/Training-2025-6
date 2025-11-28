package Lrobot.Visualization;

import Lrobot.hinge.HingeJava;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;
import org.littletonrobotics.junction.mechanism.LoggedMechanismLigament2d;
import org.littletonrobotics.junction.mechanism.LoggedMechanismRoot2d;

public class HingeVisualization extends VisualizedSubsystem {

    private HingeJava subsystem;

    public HingeVisualization() {
        subsystem = HingeJava.getInstance();

    }

    @Override
    void updateVisualization() {
        HingeVisualization.HingeVisualizationMechanism.HINGE.setAngle(subsystem.getHingeAngle());
    }

    public class HingeVisualizationMechanism {
        private static final double HINGE_X_POSITION = 3.4;
        private static final double HINGE_Y_POSITION = 0.25;
        private static final double LIGAMENT_LENGTH = 0.375;
        private static final double LIGAMENT_ANGLE = 0;
        private static final double LIGAMENT_LINE_WIDTH = 5;

        public static final Color8Bit LIGAMENT_COLOR = new Color8Bit(Color.kPurple);
        public static final LoggedMechanismRoot2d HINGE_ROOT =
                ROBOT_MECHANISM.getRoot("HINGE_ROOT", HINGE_X_POSITION,HINGE_Y_POSITION);
        public static final LoggedMechanismLigament2d HINGE = HINGE_ROOT.append(
                new LoggedMechanismLigament2d(
                        "HINGE",
                        LIGAMENT_LENGTH, LIGAMENT_ANGLE,
                        LIGAMENT_LINE_WIDTH, LIGAMENT_COLOR
                )
        );
    }

}

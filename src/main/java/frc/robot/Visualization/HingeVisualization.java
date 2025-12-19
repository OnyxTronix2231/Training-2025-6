package frc.robot.Visualization;

import Lrobot.hinge.Hinge;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;
import org.littletonrobotics.junction.mechanism.LoggedMechanismLigament2d;
import org.littletonrobotics.junction.mechanism.LoggedMechanismRoot2d;

public class HingeVisualization extends VisualizedSubsystem {
    private final Hinge hinge;

    public HingeVisualization() {
        hinge = Hinge.getInstance();
    }

    @Override
    void updateVisualization() {
        HingeVisualizationMechanism.HINGE.setAngle(hinge.getHingeAngle());
    }

    public class HingeVisualizationMechanism {
        private static final double X_POSITION = 3.4;
        private static final double Y_POSITION = 0.25;
        private static final double LIGAMENT_LENGTH = 0.375;
        private static final double LIGAMENT_ANGLE = 0;
        private static final double LIGAMENT_LINE_WIDTH = 5;

        public static final Color8Bit HINGE_LIGAMENT_COLOR = new Color8Bit(Color.kOrangeRed);
        public static final LoggedMechanismRoot2d hingeRoot = ROBOT_MECHANISM.getRoot("hinge", X_POSITION, Y_POSITION);
        public static final LoggedMechanismLigament2d HINGE = hingeRoot.append(
            new LoggedMechanismLigament2d(
                "hinge",
                LIGAMENT_LENGTH, LIGAMENT_ANGLE,
                LIGAMENT_LINE_WIDTH, HINGE_LIGAMENT_COLOR
            )
        );
    }
}

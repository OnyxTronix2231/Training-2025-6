package Lrobot.Visualization;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;
import frc.robot.subsystems.wrist.Wrist;
import org.littletonrobotics.junction.mechanism.LoggedMechanismLigament2d;
import org.littletonrobotics.junction.mechanism.LoggedMechanismRoot2d;

public class WristVisualization extends VisualizedSubsystem {

    private Wrist subsystem;

    public WristVisualization() {subsystem = Wrist.getInstance();}

    void updateVisualization() {
        WristVisualization.WristVisualizationMechanism.WRIST.setAngle(subsystem.getWristAngle());
    }


    public class WristVisualizationMechanism {
        private static final double WRIST_X_POSITION = 3.5;
        private static final double WRIST_Y_POSITION = 0.25;
        private static final double LIGAMENT_LENGTH = 0.375;
        private static final double LIGAMENT_ANGLE = 0;
        private static final double LIGAMENT_LINE_WIDTH = 5;

        public static final Color8Bit LIGAMENT_COLOR = new Color8Bit(Color.kBlue);
        public static final LoggedMechanismRoot2d WRIST_ROOT =
                ROBOT_MECHANISM.getRoot("WRIST_ROOT",WRIST_X_POSITION,WRIST_Y_POSITION);
        public static final LoggedMechanismLigament2d WRIST = WRIST_ROOT.append(new LoggedMechanismLigament2d("HINGE",LIGAMENT_LENGTH,LIGAMENT_ANGLE,LIGAMENT_LINE_WIDTH,LIGAMENT_COLOR));
    }
}

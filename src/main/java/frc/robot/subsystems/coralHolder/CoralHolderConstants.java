package frc.robot.subsystems.coralHolder;

import org.littletonrobotics.junction.mechanism.LoggedMechanismLigament2d;

public class CoralHolderConstants {

    public static final String SHUFFLEBOARD_TAB_NAME = "Coral Holder";

    public static class CoralHolderSimulationConstants {

        public static final int CORAL_INTAKE_SIMULATION_ROLLERS_ID = 16;
        public static final double SIMULATION_ROLLERS_P = 1;
        public static final double SIMULATION_ROLLERS_V = 0.13;
        public static final double SIMULATION_ROLLERS_D = 0.001;

        public static final LoggedMechanismLigament2d[] CORAL_INTAKE = new LoggedMechanismLigament2d[4];

        public static final double INTAKE_CORAL_LENGTH = 0.05;

        public static final LoggedMechanismLigament2d[] SECOND_CORAL_SPOKES = new LoggedMechanismLigament2d[4];

        public static final double CORAL_SPOOK_LENGTH = 0.05;

        public static final String CORAL_SPOKES_NAME = "SPOKES";
        public static final int CORAL_SPOKES_WIDTH = 5;
    }

    public static final double CORAL_SCORING_TIMEOUT = 3;

    public static final double SCORE_SPEED = 0.3;

    public static final double INTAKE_SPEED = -0.9;
    public static final double FIX_SPEED_OUT = 0.25;
    public static final double KEEP_SPEED = -0.05;

    public static final double L1_SCORING = 0.6;
    public static final double EJECT_SPEED = 0.2;

    public static final double CORAL_SENSOR_DEBOUNCE_TIME = 0.0;

    public static class CoralHolderRobotAConstants {
    }

    public static class CoralHolderRobotBConstants {
        public static final int CORAL_INTAKE_ROLLERS_ID = 14;

        public static final int OUTER_BEAM_BRAKER_PORT = 9;
        public static final int INNER_BEAM_BRAKER_PORT = 8;
    }


    public static final int OUTER_BEAM_BRAKER_PORT_A = 7;
    public static final int INNER_BEAM_BRAKER_PORT_B = 6;

    public static final double DEBOUNCER_TIME = 0.2;
}

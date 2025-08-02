package frc.robot.subsystems.ballIntake;

import org.littletonrobotics.junction.mechanism.LoggedMechanismLigament2d;


public class BallIntakeConstants {

    public final static double kP = 0.4;
    public final static double kI = 0;
    public final static double kD = 0;
    public final static double kV = 0.128;
    public final static double kS = 0.18;
    public final static int MOTOR_ID = 15;
    public static final int BALL_INSIDE_SYSTEM_THRESHOLD = 90;

    public static final int LIMIT_SWITCH_PORT = 5;

    public static final double BALL_INTAKE_SPEED = 0.6;

    public static final double BALL_DEBOUNCE_TIME = 0.1;

    public static final double PRESSED_BALL_LIMIT_SWITCH = 1;

    public static final double BALL_NET_SPEED = 0.1;

    public static final double BALL_PROCESSOR_ANGLE_SPEED = -0.14;

    public static final double BALL_NET_PROCESSOR_ANGLE_SPEED = -0.55;

    public static class SimulationBallIntake {
        public static final int BALL_INTAKE_SIMULATION_ID = 18;
        public static final double SIMULATION_P = 1;
        public static final double SIMULATION_V = 0.13;
        public static final double SIMULATION_D = 0.001;
        public static final String BALL_SPOKES_NAME = "Ball Spokes";
        public static final LoggedMechanismLigament2d[] BALL_SPOKES = new LoggedMechanismLigament2d[4];

        public static final double BALL_SPOKES_LENGTH = 0.08;
        public static final double BALL_SPOKES_WIDTH = 5;
        public static final LoggedMechanismLigament2d[] SECOND_BALL_SPOKES = new LoggedMechanismLigament2d[4];
        public static final double SECOND_BALL_SPOKES_LENGTH = 0.08;
    }
}

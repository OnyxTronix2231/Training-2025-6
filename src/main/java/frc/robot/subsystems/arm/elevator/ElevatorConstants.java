package frc.robot.subsystems.arm.elevator;

public class ElevatorConstants {
    public static final int MASTER_MOTOR_ID = 1;
    public static final int FOLLOWER_MOTOR_ID = 2;
    public static final int LIMIT_SWITCH_ID = 0;

    public static final double HEIGHT_ERROR_TOLERANCE = 0.02;

    public static final double KP = 0;
    public static final double KI = 0;
    public static final double KD = 0;
    public static final double KG = 0;

    public static final int ELEVATOR_MOTION_MAGIC_DEFAULT_SLOT = 0;

    public static final double RADIUS = 0.0573 / 2.0;

    public static final double RATIO = 56 / 9.0;

    public static double ROTATIONS_TO_METERS(double rotations) {
        return rotations * 2 * Math.PI * RADIUS / RATIO;
    }

    public static double METERS_TO_ROTATIONS(double height) {
        return height / (2 * Math.PI * RADIUS) * RATIO;
    }

    public class SIMULATED_CONSTANTS {
        public static final double SIMULATED_KP = 0;
        public static final double SIMULATED_KI = 0;
        public static final double SIMULATED_KD = 0;
        public static final double SIMULATED_KG = 0;
    }

}

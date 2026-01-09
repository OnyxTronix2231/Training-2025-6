package frc.robot.subsystems.arm.wrist;

public class WristConstants {
    public static final int MOTOR_ID = 3;
    public static final int CANCODER_ID = 4;

    public static final double CANCODER_MAGNET_OFFSET = 0.3017578125;

    public static final int WRIST_MOTION_MAGIC_DEFAULT_SLOT = 0;

    public static final double KP = 0;
    public static final double KI = 0;
    public static final double KD = 0;
    public static final double KG = 0;

    public static final double WRIST_ANGLE_ERROR_TOLERANCE = 2;

    public static final double RATIO = (70 / 10.0) * (60 / 18.0) * (66 / 25.0);

    public class SIMULATED_CONSTANTS {
        public static final double SIMULATED_KP = 0;
        public static final double SIMULATED_KI = 0;
        public static final double SIMULATED_KD = 0;
        public static final double SIMULATED_KG = 0;
    }
}

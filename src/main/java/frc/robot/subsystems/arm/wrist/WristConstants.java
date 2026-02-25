package frc.robot.subsystems.arm.wrist;

import com.ctre.phoenix6.signals.GravityTypeValue;
import com.ctre.phoenix6.signals.StaticFeedforwardSignValue;
import frc.robot.lib.PID.PIDValues;

import java.util.function.DoubleUnaryOperator;

public class WristConstants {
    public static final int MOTOR_ID = 11;
    public static final int CANCODER_ID = 5;

    public static final String LOG_PATH = "Subsystems/Wrist/";

    public static final double CANCODER_MAGNET_OFFSET = 0.3017578125;

    public static final double OPEN_ANGLE = 0;
    public static final double CLOSE_ANGLE = 180;

    public static final int WRIST_FAST_SLOT = 0;
    public static final int WRIST_SLOW_SLOT = 1;

    public static final double KP = 100;
    public static final double SLOW_KP = 20;
    public static final double KI = 1;
    public static final double SLOW_KI = 0;
    public static final double KD = 35;
    public static final double SLOW_KD = 8;
    public static final double KG = 5.8;
    public static final double SLOW_KG = 6;
    public static final double KS = 0;
    public static final double SLOW_KS = 0;
    public static final double KV = 0;
    public static final double SLOW_KV = 0;
    public static final double KA = 0;
    public static final double SLOW_KA = 0;

    public static final PIDValues WRIST_PID_VALUES_FAST = new PIDValues(
            KP,
            KI,
            KD,
            KG,
            KS,
            KV,
            KA,
            GravityTypeValue.Arm_Cosine,
            StaticFeedforwardSignValue.UseClosedLoopSign
    );

    public static final PIDValues WRIST_PID_VALUES_SLOW = new PIDValues(
            SLOW_KP,
            SLOW_KI,
            SLOW_KD,
            SLOW_KG,
            SLOW_KS,
            SLOW_KV,
            SLOW_KA,
            GravityTypeValue.Arm_Cosine,
            StaticFeedforwardSignValue.UseClosedLoopSign
    );

    public static final double MOTION_MAGIC_ACCELERATION = 0;
    public static final double MOTION_MAGIC_SPEED = 0;
    public static final double MOTION_MAGIC_JERK = 0;

    public static final double WRIST_ANGLE_TOLERANCE = 2;

    public static final double RATIO = (70 / 10.0) * (60 / 18.0) * (66 / 25.0);
    public static final DoubleUnaryOperator WRIST_ROTOR_TO_SENSOR = rotations -> rotations / RATIO ;

    public static final double SIMULATED_KP = 30;
    public static final double SIMULATED_KI = 0;
    public static final double SIMULATED_KD = 0;
    public static final double SIMULATED_KG = 0;
    public static final double SIMULATED_KS = 0;
    public static final double SIMULATED_KV = 0;
    public static final double SIMULATED_KA = 0;

    public static final double SIMULATED_MOTION_MAGIC_ACCELERATION = 1;
    public static final double SIMULATED_MOTION_MAGIC_SPEED = 1;
    public static final double SIMULATED_MOTION_MAGIC_JERK = 0;

    public static PIDValues SIMULATED_WRIST_PID_VALUES = new PIDValues(SIMULATED_KP, SIMULATED_KI, SIMULATED_KD, SIMULATED_KG, SIMULATED_KS, SIMULATED_KV, SIMULATED_KA, GravityTypeValue.Arm_Cosine, StaticFeedforwardSignValue.UseClosedLoopSign);

    public static double TOLERANCE_LOWER_ANGLE = 10;
    public static double TOLERANCE_GRAVITY = 7;
    public static double TOLERANCE_SLOW_LOWER_ANGLE = 9;

    public static double TOLERANCE = 0.16;
    public static double VEL_TOL = 0.2;
}

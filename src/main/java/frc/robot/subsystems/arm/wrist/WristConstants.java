package frc.robot.subsystems.arm.wrist;

import com.ctre.phoenix6.signals.GravityTypeValue;
import com.ctre.phoenix6.signals.StaticFeedforwardSignValue;
import frc.robot.lib.PID.PIDValues;

import java.util.function.DoubleUnaryOperator;

public class WristConstants {
    public static final int WRIST_MOTOR_ID = 11;

    public static final int WRIST_CANCODER_ID = 5;

    public static final double WRIST_ALLOWED_ANGLE_ERROR = 2;

    public static final double WRIST_CRUISE_VELOCITY = 1;
    public static final double WRIST_ACCELERATION = 1;

    public static final double WRIST_KP = 0;
    public static final double WRIST_KI = 0;
    public static final double WRIST_KD = 0;
    public static final double WRIST_KG = 0;
    public static final double WRIST_KS = 0;
    public static final double WRIST_KV = 0;
    public static final double WRIST_KA = 0;
    public static final PIDValues WRIST_PID_VALUES = new PIDValues(
        WRIST_KP,
        WRIST_KI,
        WRIST_KD,
        WRIST_KG,
        WRIST_KS,
        WRIST_KV,
        WRIST_KA,
        GravityTypeValue.Arm_Cosine,
        StaticFeedforwardSignValue.UseVelocitySign);

    public static final double SIMULATION_WRIST_CRUISE_VELOCITY = 3.575;
    public static final double SIMULATION_WRIST_ACCELERATION = 1;

    public static final double SIMULATION_WRIST_KP = 1;
    public static final double SIMULATION_WRIST_KI = 0;
    public static final double SIMULATION_WRIST_KD = 0.25;
    public static final double SIMULATION_WRIST_KG = 0;
    public static final double SIMULATION_WRIST_KS = 0;
    public static final double SIMULATION_WRIST_KV = 0;
    public static final double SIMULATION_WRIST_KA = 0;
    public static final PIDValues SIMULATION_WRIST_PID_VALUES = new PIDValues(
        SIMULATION_WRIST_KP,
        SIMULATION_WRIST_KI,
        SIMULATION_WRIST_KD,
        SIMULATION_WRIST_KG,
        SIMULATION_WRIST_KS,
        SIMULATION_WRIST_KV,
        SIMULATION_WRIST_KA,
        GravityTypeValue.Arm_Cosine,
        StaticFeedforwardSignValue.UseVelocitySign);

    public static final double WRIST_CANCODER_OFFSET = 0.3017578125;
    public static final double CONVERSION_RATE_WRIST = 61.6; // (70/10.0)*(60/18.0)*(66/25.0)

    public static final DoubleUnaryOperator ROTATIONS_TO_ANGLE = rotations -> rotations * 360;

    public static double ANGLE_TO_ROTATIONS(double angle) {
        return angle / 360;
    }

    public static double ROTATIONS_TO_ANGLE(double rotations) {
        return rotations * 360;
    }

    public static final double ZEROED_ANGLE = 90;

    public static final double SIMULATION_DT_SECONDS = 0.02;

    public static final double SIMULATION_WRIST_LENGTH_METERS = 0.25;
    public static final double SIMULATION_WRIST_MASS_KG = 0.1;
    public static final int SIMULATION_WRIST_NUM_OF_MOTORS = 1;

    public static final double WRIST_ZERO_OFFSET_DEG = 90;
    public static final double WRIST_VISUALIZATION_OFFSET = -90;
}

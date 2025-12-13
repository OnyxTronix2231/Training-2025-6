package frc.robot.subsystems.wrist;

import com.ctre.phoenix6.signals.GravityTypeValue;
import com.ctre.phoenix6.signals.StaticFeedforwardSignValue;
import frc.robot.lib.PID.PIDValues;

import java.util.function.DoubleUnaryOperator;

public class WristConstants {
    public final static int WRIST_MOTOR_ID = 13;

    public final static int WRIST_ENCODER_ID = 5;
    public final static double WRIST_CANCODER_OFFSET = -0.0114;

    public final static double CONVERSION_RATE_WRIST = 95.04;

    public static final DoubleUnaryOperator ROTATIONS_TO_ANGLE = rotations -> rotations * 360;

    public static double ANGLE_TO_ROTATIONS(double angle) {
        return angle / 360;
    }

    public static double ROTATIONS_TO_ANGLE(double rotations) {
        return rotations * 360;
    }

    public static final double WRIST_KP = 30;
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


    public static final double SIMULATION_WRIST_KP = 0.3;
    public static final double SIMULATION_WRIST_KI = 0;
    public static final double SIMULATION_WRIST_KD = 0;
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

    public static final double SIMULATION_DT_SECONDS = 0.02;

    public static final double SIMULATION_WRIST_LENGTH_METERS = 0.001;
    public static final double SIMULATION_WRIST_MASS_KG = 0.001;
    public static final int SIMULATION_WRIST_NUM_OF_MOTORS = 1;
}
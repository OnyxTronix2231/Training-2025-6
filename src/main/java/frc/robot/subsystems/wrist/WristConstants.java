package frc.robot.subsystems.wrist;

import com.ctre.phoenix6.signals.GravityTypeValue;
import com.ctre.phoenix6.signals.StaticFeedforwardSignValue;
import frc.robot.lib.PID.PIDValues;

import java.util.function.DoubleUnaryOperator;

public class WristConstants {

    public static final int WRIST_MOTOR_ID = 13;
    public static final int WRIST_ENCODER_ID = 5;

    public static final int SIMULATION_WRIST_NUM_OF_MOTORS = 1;


    public static final DoubleUnaryOperator ROTATIONS_TO_LENGTH_SIMULATION = rotations -> ROTATIONS_TO_LENGTH(rotations, true);

    public static final double SIMULATION_WRIST_MASS_KG = 1;
    public static final double SIMULATION_WRIST_LENGTH_METERS = 0.3;

    public static final double RATIO = 56 / 9.0;
    public static final double RADIUS = 0.0573 / 2.0;
    public static final double DIAMETER = RADIUS * 2 * Math.PI;

    public static final double SIMULATION_RADIUS = 1;

    public static final double SIMULATION_DIAMETER = SIMULATION_RADIUS * 2 * Math.PI;


    public static final double WRIST_FORWARD_SOFT_LIMIT_THRESHOLD = 1.3;
    public static final double WRIST_REVERSE_SOFT_LIMIT_THRESHOLD = 0.002;


    public static double ROTATIONS_TO_LENGTH(double rotations, boolean isSimulation) {
        if (isSimulation) {
            return ROTATIONS_TO_LENGTH_WITH_PARAMETERS(rotations, SIMULATION_DIAMETER, RATIO);
        }
        return ROTATIONS_TO_LENGTH_WITH_PARAMETERS(rotations, DIAMETER, RATIO);
    }
    private static double ROTATIONS_TO_LENGTH_WITH_PARAMETERS(double rotations, double diameter, double ratio) {
        return rotations / ratio * diameter;
    }


    public static final DoubleUnaryOperator ROTATIONS_TO_ANGLE = rotations -> rotations*360;

    public static final double CONVERSION_RATE_ARM = 95.04;

    public static final double WRIST_KP = 0;
    public static final double WRIST_KI = 0;
    public static final double WRIST_KD = 0;
    public static final double WRIST_KG = 0;
    public static final double WRIST_KS = 0;
    public static final double WRIST_KV = 0;
    public static final double WRIST_KA = 0;




    public static final double SIMULATION_WRIST_KP = 3.6;
    public static final double SIMULATION_WRIST_KI = 0;
    public static final double SIMULATION_WRIST_KD = 0.05;
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
            GravityTypeValue.Elevator_Static,
            StaticFeedforwardSignValue.UseVelocitySign);


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

    public static final double WRIST_CANCODER_OFFSET = -0.0114;


}


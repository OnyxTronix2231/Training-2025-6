package frc.robot.subsystems.wrist;

import com.ctre.phoenix6.signals.GravityTypeValue;
import com.ctre.phoenix6.signals.StaticFeedforwardSignValue;
import frc.robot.lib.PID.PIDValues;

import java.util.function.DoubleUnaryOperator;

public class WristConstants {

    public static final int WRIST_ENCODER_ID = 5;
    public static final int WRIST_MOTOR_ID = 13;
    public static final double CONVERSION_RATE_ARM = 95.04;

    public static final double WRIST_KP = 0.05;
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
            GravityTypeValue.Elevator_Static,
            StaticFeedforwardSignValue.UseVelocitySign);

    public static DoubleUnaryOperator ROTATIONS_TO_ANGLE = rotations -> rotations*360;

    public static final double SIMULATION_WRIST_LENGTH_METERS = 0.001;
    public static final double SIMULATION_WRIST_MASS_KG = 0.001;
    public static final int SIMULATION_WRIST_NUM_OF_MOTORS = 1;

    public static final double SIMULATED_WRIST_KP = 3.5;
    public static final double SIMULATED_WRIST_KI = 0;
    public static final double SIMULATED_WRIST_KD = 0;
    public static final double SIMULATED_WRIST_KG = 0;
    public static final double SIMULATED_WRIST_KS = 0;
    public static final double SIMULATED_WRIST_KV = 0;
    public static final double SIMULATED_WRIST_KA = 0;
    public static final PIDValues SIMULATED_WRIST_PID_VALUES = new PIDValues(
            SIMULATED_WRIST_KP,
            SIMULATED_WRIST_KI,
            SIMULATED_WRIST_KD,
            SIMULATED_WRIST_KG,
            SIMULATED_WRIST_KS,
            SIMULATED_WRIST_KV,
            SIMULATED_WRIST_KA,
            GravityTypeValue.Elevator_Static,
            StaticFeedforwardSignValue.UseVelocitySign);
}
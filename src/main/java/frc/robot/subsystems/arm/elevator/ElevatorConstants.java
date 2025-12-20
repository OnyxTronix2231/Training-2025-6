package frc.robot.subsystems.arm.elevator;

import com.ctre.phoenix6.signals.GravityTypeValue;
import com.ctre.phoenix6.signals.StaticFeedforwardSignValue;
import frc.robot.lib.PID.PIDValues;

import java.util.function.DoubleUnaryOperator;

public class ElevatorConstants {
    public static final int ELEVATOR_MASTER_MOTOR_ID = 9;
    public static final int ELEVATOR_FOLLOWER_MOTOR_ID = 10;

    public static final int ELEVATOR_LIMIT_SWITCH_ID = 10;

    public static final double ELEVATOR_FORWARD_LIMIT_THRESHOLD = 1.3;
    public static final double ELEVATOR_REVERSE_LIMIT_THRESHOLD = 0.002;
    public static final double ELEVATOR_ALLOWED_LENGTH_ERROR_METERS = 0.02;

    public static final double ELEVATOR_KP = 0;
    public static final double ELEVATOR_KI = 0;
    public static final double ELEVATOR_KD = 0;
    public static final double ELEVATOR_KG = 0;
    public static final double ELEVATOR_KS = 0;
    public static final double ELEVATOR_KV = 0;
    public static final double ELEVATOR_KA = 0;
    public static final PIDValues ELEVATOR_PID_VALUES = new PIDValues(
        ELEVATOR_KP,
        ELEVATOR_KI,
        ELEVATOR_KD,
        ELEVATOR_KG,
        ELEVATOR_KS,
        ELEVATOR_KV,
        ELEVATOR_KA,
        GravityTypeValue.Elevator_Static,
        StaticFeedforwardSignValue.UseClosedLoopSign);

    public static final double SIMULATION_ELEVATOR_KP = 2;
    public static final double SIMULATION_ELEVATOR_KI = 0;
    public static final double SIMULATION_ELEVATOR_KD = 0.2;
    public static final double SIMULATION_ELEVATOR_KG = 0;
    public static final double SIMULATION_ELEVATOR_KS = 0;
    public static final double SIMULATION_ELEVATOR_KV = 0;
    public static final double SIMULATION_ELEVATOR_KA = 0;
    public static final PIDValues SIMULATION_ELEVATOR_PID_VALUES = new PIDValues(
        SIMULATION_ELEVATOR_KP,
        SIMULATION_ELEVATOR_KI,
        SIMULATION_ELEVATOR_KD,
        SIMULATION_ELEVATOR_KG,
        SIMULATION_ELEVATOR_KS,
        SIMULATION_ELEVATOR_KV,
        SIMULATION_ELEVATOR_KA,
        GravityTypeValue.Elevator_Static,
        StaticFeedforwardSignValue.UseClosedLoopSign);

    public static final double RATIO = 56 / 9.0;
    public static final double RADIUS = 0.0573 / 2.0;
    public static final double DIAMETER = RADIUS * 2 * Math.PI;

    public static final double RADIUS_SIMULATION = 1;
    public static final double DIAMETER_SIMULATION = RADIUS_SIMULATION * 2 * Math.PI;

    public static final DoubleUnaryOperator ROTATIONS_TO_LENGTH_ROBOT = rotations -> ROTATIONS_TO_LENGTH(rotations, false);
    public static final DoubleUnaryOperator ROTATIONS_TO_LENGTH_SIMULATION = rotations -> ROTATIONS_TO_LENGTH(rotations, true);

    public static double ROTATIONS_TO_LENGTH(double rotations, boolean isSimulation) {
        if (isSimulation)
            return ROTATIONS_TO_LENGTH_WITH_PARAMETERS(rotations, DIAMETER_SIMULATION, RATIO);
        return ROTATIONS_TO_LENGTH_WITH_PARAMETERS(rotations, DIAMETER, RATIO);
    }

    public static double LENGTH_TO_ROTATIONS(double length, boolean isSimulation) {
        if (isSimulation)
            return LENGTH_TO_ROTATIONS_WITH_PARAMETERS(length, DIAMETER_SIMULATION, RATIO);
        return LENGTH_TO_ROTATIONS_WITH_PARAMETERS(length, DIAMETER, RATIO);
    }

    private static double ROTATIONS_TO_LENGTH_WITH_PARAMETERS(double rotations, double diameter, double ratio) {
        return rotations / ratio * diameter;
    }

    private static double LENGTH_TO_ROTATIONS_WITH_PARAMETERS(double length, double diameter, double ratio) {
        return length / diameter * ratio;
    }

    public static final double ZEROED_HEIGHT = 0.002;

    public static final double SIMULATION_DT_SECONDS = 0.02;

    public static final double SIMULATION_ELEVATOR_LENGTH_METERS = 1;
    public static final double SIMULATION_ELEVATOR_MASS_KG = 0.5;
    public static final double ELEVATOR_VISUALIZATION_OFFSET = 0.2;
    public static final int SIMULATION_ELEVATOR_NUM_OF_MOTORS = 2;
}

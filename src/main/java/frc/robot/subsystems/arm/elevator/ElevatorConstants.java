package frc.robot.subsystems.arm.elevator;

import com.ctre.phoenix6.signals.GravityTypeValue;
import com.ctre.phoenix6.signals.StaticFeedforwardSignValue;
import frc.robot.lib.PID.PIDValues;

import java.util.function.DoubleUnaryOperator;

public class ElevatorConstants {
    public static final String ELEVATOR_SUBSYSTEM_NAME = "arm";
    public static final String ELEVATOR_MOTOR_MASTER_NAME = "elevatorMaster";
    public static final String ELEVATOR_MOTOR_FOLLOWER_NAME = "elevatorFollower";

    public static final int ELEVATOR_MASTER_MOTOR_ID = 9;
    public static final int ELEVATOR_FOLLOWER_MOTOR_ID = 10;

    public static final int ELEVATOR_LIMIT_SWITCH_CHANEL = 6;

    public static final double ELEVATOR_FORWARD_SOFT_LIMIT_THRESHOLD = 1.3;
    public static final double ELEVATOR_REVERSE_SOFT_LIMIT_THRESHOLD = 0.002;

    public static final double ELEVATOR_STATOR_LIMIT = 120;
    public static final double ELEVATOR_SUPPLY_LIMIT = 70;
    public static final double ELEVATOR_SUPPLY_LOWER_LIMIT = 40;
    public static final double ELEVATOR_TIME_LOWER_LIMIT = 1;

    public static final int ELEVATOR_MOTION_MAGIC_DEFAULT_SLOT = 0;

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
            StaticFeedforwardSignValue.UseVelocitySign
    );

    public static final double ELEVATOR_CRUISE_VELOCITY = 86.7;
    public static final double ELEVATOR_ACCELERATION = 250;
    public static final double ELEVATOR_JERK = 0;

    public static final double RATIO = 56 / 9.0;
    public static final double RADIUS = 0.0573 / 2.0;
    public static final double DIAMETER = RADIUS * 2 * Math.PI;

    public static final DoubleUnaryOperator ROTATIONS_TO_LENGTH_ROBOT = rotations -> ROTATIONS_TO_LENGTH(rotations, false);
    public static final DoubleUnaryOperator ROTATIONS_TO_LENGTH_SIMULATION = rotations -> ROTATIONS_TO_LENGTH(rotations, true);

    public static double ROTATIONS_TO_LENGTH(double rotations, boolean isSimulation) {
        if (isSimulation) {
            return ROTATIONS_TO_LENGTH_WITH_PARAMETERS(rotations, SIMULATION_DIAMETER, RATIO);
        }
        return ROTATIONS_TO_LENGTH_WITH_PARAMETERS(rotations, DIAMETER, RATIO);
    }

    public static double LENGTH_TO_ROTATIONS(double length, boolean isSimulation) {
        if (isSimulation) {
            return LENGTH_TO_ROTATIONS_WITH_PARAMETERS(length, SIMULATION_DIAMETER, RATIO);
        }
        return LENGTH_TO_ROTATIONS_WITH_PARAMETERS(length, DIAMETER, RATIO);
    }

    private static double ROTATIONS_TO_LENGTH_WITH_PARAMETERS(double rotations, double diameter, double ratio) {
        return rotations / ratio * diameter;
    }

    private static double LENGTH_TO_ROTATIONS_WITH_PARAMETERS(double length, double diameter, double ratio) {
        return length / diameter * ratio;
    }

    //SIMULATION
    public static final String SIMULATION_ELEVATOR_MOTOR_NAME = "ELEVATOR_MOTOR";

    public static final double SIMULATION_ELEVATOR_KP = 0;
    public static final double SIMULATION_ELEVATOR_KI = 0;
    public static final double SIMULATION_ELEVATOR_KD = 0;
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
            StaticFeedforwardSignValue.UseVelocitySign);

    public static final double SIMULATION_ELEVATOR_CRUISE_VELOCITY = 16;
    public static final double SIMULATION_ELEVATOR_ACCELERATION = 32;
    public static final double SIMULATION_ELEVATOR_JERK = 0;

    public static final double SIMULATION_RADIUS = 1;
    public static final double SIMULATION_DIAMETER = SIMULATION_RADIUS * 2 * Math.PI;

    public static final double SIMULATION_ELEVATOR_VISUALIZATION_OFFSET = 0.2;

    public static final int SIMULATION_ELEVATOR_NUM_OF_MOTORS = 2;
    public static final double SIMULATION_ELEVATOR_MASS_KG = 0.001;
    public static final double SIMULATION_ELEVATOR_LENGTH_METERS = 0.001;
}

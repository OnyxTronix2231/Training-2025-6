package frc.robot.subsystems.arm.wrist;

import com.ctre.phoenix6.signals.GravityTypeValue;
import com.ctre.phoenix6.signals.StaticFeedforwardSignValue;
import frc.robot.lib.PID.PIDValues;

import java.util.function.DoubleUnaryOperator;

public class WristConstants {
    public static final String WRIST_SUBSYSTEM_NAME = "arm";
    public static final String WRIST_MOTOR_NAME = "wrist";

    public static final int WRIST_MOTOR_ID = 11;

    public static final int WRIST_CANCODER_ID = 5;
    public static final double WRIST_CANCODER_OFFSET = 0;

    public static final double WRIST_ROTOR_TO_SENSOR_RATIO = (70 / 10.0) * (60 / 18.0) * (66 / 25.0);

    public static final double WRIST_FORWARD_SOFT_LIMIT_THRESHOLD = 1.3;
    public static final double WRIST_REVERSE_SOFT_LIMIT_THRESHOLD = 0.002;

    public static final double WRIST_STATOR_LIMIT = 120;
    public static final double WRIST_SUPPLY_LIMIT = 70;
    public static final double WRIST_SUPPLY_LOWER_LIMIT = 40;
    public static final double WRIST_TIME_LOWER_LIMIT = 1;

    public static final int WRIST_MOTION_MAGIC_DEFAULT_SLOT = 0;

    public static final double WRIST_KP = 0;
    public static final double WRIST_KI = 0;
    public static final double WRIST_KD = 0;
    public static final double WRIST_KG = 0;
    public static final double WRIST_KS = 0;
    public static final double WRIST_KV = 0;
    public static final double WRIST_KA = 0;
    public static final PIDValues WRIST_PID_VALUES = new PIDValues(
            WRIST_KI,
            WRIST_KD,
            WRIST_KG,
            WRIST_KS,
            WRIST_KV,
            WRIST_KA,
            WRIST_KP,
            GravityTypeValue.Arm_Cosine,
            StaticFeedforwardSignValue.UseVelocitySign
    );

    public static final double WRIST_CRUISE_VELOCITY = 86.7;
    public static final double WRIST_ACCELERATION = 250;
    public static final double WRIST_JERK = 0;

    public static final double RATIO = 56 / 9.0;
    public static final double RADIUS = 0.0573 / 2.0;
    public static final double DIAMETER = RADIUS * 2 * Math.PI;

    public static final DoubleUnaryOperator ROTATIONS_TO_ANGLE = rotations -> rotations * 360;

    public static final double ZEROED_ANGLE = 90;
    public static final double DEFAULT_ANGLE = 90;

    //SIMULATION
    public static final String SIMULATION_WRIST_MOTOR_NAME = "WRIST_MOTOR";

    public static final double SIMULATION_WRIST_KP = 0;
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

    public static final double SIMULATION_WRIST_CRUISE_VELOCITY = 16;
    public static final double SIMULATION_WRIST_ACCELERATION = 32;
    public static final double SIMULATION_WRIST_JERK = 0;

    public static final double SIMULATION_RADIUS = 1;
    public static final double SIMULATION_DIAMETER = SIMULATION_RADIUS * 2 * Math.PI;

    public static final double SIMULATION_WRIST_VISUALIZATION_OFFSET = 0.2;

    public static final double WRIST_ZERO_OFFSET_DEG = 90;

    public static final int SIMULATION_WRIST_NUM_OF_MOTORS = 2;
    public static final double SIMULATION_WRIST_MASS_KG = 0.001;
    public static final double SIMULATION_WRIST_LENGTH_METERS = 0.001;

}

package Lrobot.elevator;


import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;
import org.littletonrobotics.junction.mechanism.LoggedMechanismLigament2d;
import org.littletonrobotics.junction.mechanism.LoggedMechanismRoot2d;

import static Lrobot.Visualization.VisualizedSubsystem.ROBOT_MECHANISM;

public class ElevatorConstants {

    public static final int ELEVATOR_MASTER_MOTOR_ID = 9;
    public static final int ELEVATOR_FOLLOWER_MOTOR_ID = 10;

    public static final int ELEVATOR_LIMIT_SWITCH_CHANEL = 6;

    public static final double ELEVATOR_FORWARD_SOFT_LIMIT_THRESHOLD = 1.3;
    public static final double ELEVATOR_REVERSE_SOFT_LIMIT_THRESHOLD = 0.002;

    public static final double ELEVATOR_KP = 10;
    public static final double ELEVATOR_KI = 0;
    public static final double ELEVATOR_KD = 0;
    public static final double ELEVATOR_KG = 0.035;
    public static final double ELEVATOR_KS = 0;

    public static final double ELEVATOR_CRUISE_VELOCITY = 86.7; //86.7
    public static final double ELEVATOR_ACCELERATION = 250; //500 / 250
    public static final double ELEVATOR_JERK = 0;

    public static final double SIMULATION_ELEVATOR_KP = 2;
    public static final double SIMULATION_ELEVATOR_KI = 0;
    public static final double SIMULATION_ELEVATOR_KD = 0;
    public static final double SIMULATION_ELEVATOR_KG = 0;
    public static final double SIMULATION_ELEVATOR_KS = 0;

    public static final double SIMULATION_ELEVATOR_CRUISE_VELOCITY = 16;
    public static final double SIMULATION_ELEVATOR_ACCELERATION = 32;
    public static final double SIMULATION_ELEVATOR_JERK = 0;


    public static final double RATIO = 56 / 9.0;
    public static final double RADIUS = 0.0573 / 2.0;
    public static final double DIAMETER = RADIUS * 2 * Math.PI;

    public static final double RADIUS_SIMULATION = 1;
    public static final double DIAMETER_SIMULATION = RADIUS_SIMULATION * 2 * Math.PI;


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

    public static final double DEFAULT_HEIGHT = 0.42;
    public static final double ZEROED_HEIGHT = 0.01;
    public static final double PICKUP_CORAL_HEIGHT = 0.37;
    public static final double HOLD_CORAL_HEIGHT = 0.2;

    public static final double HAS_BALL_HEIGHT = 0.02;
    public static final double INTAKE_BALL_GROUND_HEIGHT = 0.02;

    public static final double L1_HEIGHT = 0.47 + 0.02;
    public static final double L2_HEIGHT = 0.23; //0.15 /0.26
    public static final double L3_HEIGHT = 0.59; //0.46 /0.62 //0.55
    public static final double L4_HEIGHT = 1.18; //1.2 /1.3

    public static final double NET_HEIGHT = 1.3;
    public static final double PROCESSOR_HEIGHT = 0.05;

    public static final double MIN_WRIST_MOVING_HEIGHT = 0.45;
    public static final double MIN_WRIST_MOVING_HEIGHT_ABOVE_CORAL_INTAKE_CLOSE = 1;
    public static final double MIN_WRIST_MOVING_HEIGHT_ABOVE_CORAL_INTAKE_OPEN = 0.6;


    public static final double ELEVATOR_HOMING_SPEED = -0.01;

    public static final double ELEVATOR_TOLERANCE = 0.03;
    public static final double OPENING_ARM_ELEVATOR_TOLERANCE = 0.04;

    public static final double ELEVATOR_VISUALIZATION_OFFSET = 0.2;


}

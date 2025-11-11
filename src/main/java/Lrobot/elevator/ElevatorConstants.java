package Lrobot.elevator;


import java.util.function.DoubleUnaryOperator;

public class ElevatorConstants {

    public static final int ELEVATOR_MASTER_MOTOR_ID = 9;
    public static final int ELEVATOR_FOLLOWER_MOTOR_ID = 10;

    public static final int ELEVATOR_LIMIT_SWITCH_CHANEL = 6;

    public static final double ELEVATOR_FORWARD_SOFT_LIMIT_THRESHOLD = 1.3;
    public static final double ELEVATOR_REVERSE_SOFT_LIMIT_THRESHOLD = 0.002;

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

    public static final double SIMULATION_RADIUS = 1;
    public static final double SIMULATION_DIAMETER = SIMULATION_RADIUS * 2 * Math.PI;

    public static final double ELEVATOR_VISUALIZATION_OFFSET = 0.2;

    public static final double SIMULATION_ELEVATOR_LENGTH_METERS = 0.001;
    public static final double SIMULATION_ELEVATOR_MASS_KG = 0.001;
    public static final int SIMULATION_ELEVATOR_NUM_OF_MOTORS = 2;

    public static final double SIMULATION_DT_SECONDS = 0.02;

}

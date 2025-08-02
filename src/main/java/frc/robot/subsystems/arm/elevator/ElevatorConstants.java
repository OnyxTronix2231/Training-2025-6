package frc.robot.subsystems.arm.elevator;

public class ElevatorConstants {

    public static final double DEBOUNCE_TIME = 0.04;

    public static final int ELEVATOR_MASTER_MOTOR_ID = 9;
    public static final int ELEVATOR_SLAVE_MOTOR_ID = 10;

    public static final double ELEVATOR_KP = 3.0;
    public static final double ELEVATOR_KI = 0;
    public static final double ELEVATOR_KD = 0;
    public static final double ELEVATOR_KS = 0.0;
    public static final double ELEVATOR_KG = 0.2;

    public static final double RATIO = (56 / 9.0) * (38 / 22.0);//15;
    public static final double RADIUS = 0.05703 / 2.0;//6.043 / 2.0 + 0.3;
    public static final double DIAMETER = RADIUS * 2 * Math.PI;//cm
    public static final double STRING_WIDTH = 1;
    public static final double ELEVATOR_MOTION_MAGIC_CRUISE_VELOCITY = 80;
    public static final double ELEVATOR_MOTION_MAGIC_ACCELERATION = 550;
    public static final double ELEVATOR_MOTION_MAGIC_JERK = 10000;

    public static final double ELEVATOR_TOLERANCE = 0.03;

    public static double ROTATIONS_TO_LENGTH(double m) {
        return m / RATIO * DIAMETER;
//        return m * RATIO * (2 * Math.PI * (RADIOS + STRING_WIDTH));
    }

    public static double
    LENGTH_TO_ROTATIONS(double distance) {
        return distance / DIAMETER * RATIO;
//        return distance / (RATIO * (2 * Math.PI * (RADIOS + STRING_WIDTH)));
    }

    public static class SimulationElevatorConstants {
        private static final double ELEVATOR_X_POSITION = 2;
        private static final double ELEVATOR_Y_POSITION = 0;
        private static final double LIGAMENT_LENGTH = 0.2;
        private static final double LIGAMENT_ANGLE = 90;
        private static final double LIGAMENT_LINE_WIDTH = 15;

        public static final double SIMULATION_TOLERANCE = 0.03;
        public static final int ELEVATOR_SIMULATION_CONVERSION_RATE = 1;

        public static final double SIMULATION_ELEVATOR_KP = 4;
        public static final double SIMULATION_ELEVATOR_KI = 0;
        public static final double SIMULATION_ELEVATOR_KD = 0.5;
        public static final double SIMULATION_ELEVATOR_KS = 0.02;
        public static final double SIMULATION_ELEVATOR_KG = 0.01;
        public static final double MAGIC_MOTION_ACC = 300;
        public static final double MAGIC_MOTION_CV = 50;


        public static final int ELEVATOR_SIMULATION_MOTOR_ID = 12;
        public static final double STARTING_ELEVATOR_POSITION = 0.7;

        public static final double MIN_HEIGHT_FOR_ARM = 0.25;

        public static final double MIN_HEIGHT_FOR_GROUND_INTAKE = 0.46;

        public static final double ELEVATOR_DEFAULT_HEIGHT = (0.05);

        public static final double ELEVATOR_CLIMBING_LENGTH = 0.5;

        public static final double ELEVATOR_BALL_GROUND_HEIGHT = 0;

        public static final double ELEVATOR_REVERSE_HEIGHT_LIMIT = 0.01;
        public static final double ELEVATOR_FORWARD_HEIGHT_LIMIT = 1.027; // IN METERS
        public static double REAL_FEEDER_INTAKE_HEIGHT = 0.435 + 0.03;

        public static double FAR_INTAKE_HEIGHT = 0.37 - 0.05;

        public static double FEEDER_INTAKE_HEIGHT = REAL_FEEDER_INTAKE_HEIGHT;

        public static boolean isCloseIntake = true;
        public static final double INTAKE_HEIGHT_BY_STATE = FAR_INTAKE_HEIGHT;

        public static final double BALL_NET_SCORE_HEIGHT = 1.5;

        public static final double BALL_PROCESSOR_SCORE_HEIGHT = 0.02;

        public static final double L1_HEIGHT = 0.03;
        public static final double L2_HEIGHT = ELEVATOR_DEFAULT_HEIGHT;
        public static final double L3_HEIGHT = 0.244;
        public static final double L4_HEIGHT = 1.02;

        public static final double ELEVATOR_SECOND_STAGE_MIN_HEIGHT = 0.58 + 0.1;
        public static final double ELEVATOR_Z_POSITION = 0.05;


        public static final int ELEVATOR_MASTER_MOTOR_ID = 9;
        public static final int ELEVATOR_SLAVE_MOTOR_ID = 10;

        public static final double ELEVATOR_NET_HEIGHT = 0.95;

        public static final double RESET_ELEVATOR_SPEED = -0.1;

//        public static DoubleSupplier swerveFactor = () -> (1 - Elevator.getInstance().getLength() * 0.75);

        public static final int LIMITSWTICH_ELEVATOR_PORT = 8;

    }
}

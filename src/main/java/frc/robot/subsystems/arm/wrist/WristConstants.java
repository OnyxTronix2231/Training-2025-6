package frc.robot.subsystems.arm.wrist;

import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation3d;

public class WristConstants {

    public static final int WRIST_MOTOR_ID = 13;
    public static final int WRIST_ENCODER_ID = 5;

    public static final double WRIST_TOLERANCE = 1;

    public static final double WRIST_KP = 100;
    public static final double WRIST_KI = 0;
    public static final double WRIST_KD = 0;
    public static final double WRIST_KG = 0.18;
    public static final double WRIST_KS = 0;

    public static final double WRIST_KP_SLOT1 = 60;

    public static final double WRIST_CANCODER_OFFSET = -0.206787 - 0.046143 - 0.25;

    public static final double WRIST_CLIMBING_POSITION = -25;

    public static final double REVERSE_MAX_VOLTAGE = -8;

    public static final double REEF_BALL_INTAKE_ANGLE = -114 - 5;
    public static final double WRIST_MOTION_MAGIC_CRUISE_VELOCITY = 1.2;
    public static final double WRIST_MOTION_MAGIC_ACCELERATION = 6;
    public static final double WRIST_MOTION_MAGIC_JERK = 70;

    public static final double WRIST_STEROIDS_MOTION_MAGIC_CRUISE_VELOCITY = 3;
    public static final double WRIST_STEROIDS_MOTION_MAGIC_ACCELERATION = 50;
    public static final double WRIST_STEROIDS_MOTION_MAGIC_JERK = 475;

    public static class SimulationWRISTConstants {

        public static final int SIMULATION_MOTOR_ID = 11;
        public static final double KP_SIMULATION = 25;
        public static final double KI_SIMULATION = 0;
        public static final double KD_SIMULATION = 10;
        public static final double KS_SIMULATION = 0.02;
        public static final double ELEVATOR_WRIST_LENGTH = 0.395;
        public static final int ELEVATOR_WRIST_WEIGHT = 2;
        public static final double WRIST_X_ORIGIN_POINT = 0;
        public static final double WRIST_Y_ORIGIN_POINT = 0;
        public static final double WRIST_Z_ORIGIN_POINT = 0.817;
        public static final double ROTATION_AXIS_X = 0.18;
        public static final double ROTATION_AXIS_Z = 0.65;
        public static final Pose3d WRIST_ORIGIN_POSE = new Pose3d(
                WRIST_X_ORIGIN_POINT, WRIST_Y_ORIGIN_POINT, WRIST_Z_ORIGIN_POINT,
                new Rotation3d()
        );

        public static final int ARM_SENSOR_TO_WRIST_RATION = 6;

        public static final double DEFAULT_ANGLE = -90;

        public static final double BALL_INTAKE_ANGLE = -45;

        public static final double FEEDER_INTAKE_ANGLE = -71;

        public static final double FEEDER_INTAKE_ANGLE_BY_STATE = -50;

        public static final double BALL_NET_ANGLE = 200;

        public static final double BALL_PROCESSOR_ANGLE = -50;

        public static final double L1_SCORING_ANGLE = -62 + 5;
        public static final double L2_SCORING_ANGLE = 129;
        public static final double L3_SCORING_ANGLE = 111;
        public static final double L4_SCORING_ANGLE = 136;
        public static final double L4_AUTO_SCORING_ANGLE = 138;

        public static final double CONVERSION_RATE_WRIST = 95.04;

        public static final int WRIST_NET_SCORING_ANGLE = -150;

        public static final double SAFE_WRIST_ANGLE_FOR_ELEVATOR_MOVMENT = 90;

        public static final double PITCH_TOLERANCE = 2;

        public static final double WRIST_GROUND_INTAKE_TOLERANCE = 120;
        public static final double WRIST_SCORE_bALL_TOLERANCE = 25;

        public static final double BALL_SCORING_TIMEOUT = 0.4;

        public static final double FULL_OPEN_WRIST = 90;

        public static final double END_CLOSING_TOLERANCE = 15;

        public static final double WRIST_ANGLE_OPEN_PART1 = 5;
        public static final double WRIST_ANGLE_OPEN_PART2 = -80;
        public static final double WRIST_TOLERANCE_OPEN_PART1 = 5;

    }
}

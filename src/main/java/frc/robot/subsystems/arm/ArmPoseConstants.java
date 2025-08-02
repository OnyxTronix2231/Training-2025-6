package frc.robot.subsystems.arm;

import frc.robot.utils.ArmPosition;

import static frc.robot.subsystems.arm.elevator.ElevatorConstants.SimulationElevatorConstants.*;
import static frc.robot.subsystems.arm.wrist.WristConstants.SimulationWRISTConstants.*;

public final class ArmPoseConstants {
    public static final ArmPosition ZEROED = new ArmPosition(ELEVATOR_DEFAULT_HEIGHT, DEFAULT_ANGLE);

    public static final ArmPosition FEEDER = new ArmPosition(FEEDER_INTAKE_HEIGHT, FEEDER_INTAKE_ANGLE);
    public static final ArmPosition EJECT = new ArmPosition(ELEVATOR_DEFAULT_HEIGHT, DEFAULT_ANGLE);

    public static final ArmPosition L1 = new ArmPosition(L1_HEIGHT, L1_SCORING_ANGLE);
    public static final ArmPosition L2 = new ArmPosition(L2_HEIGHT, L2_SCORING_ANGLE);
    public static final ArmPosition L3 = new ArmPosition(L3_HEIGHT, L3_SCORING_ANGLE);
    public static final ArmPosition L4 = new ArmPosition(L4_HEIGHT, L4_SCORING_ANGLE);
    public static final ArmPosition NET = new ArmPosition(ELEVATOR_NET_HEIGHT, WRIST_NET_SCORING_ANGLE);
    public static final ArmPosition PROCESSOR = new ArmPosition(BALL_PROCESSOR_SCORE_HEIGHT, BALL_PROCESSOR_ANGLE);


//    public static final ArmPosition ARM_UP =
//            new ArmPosition(0.0, Rotation2d.fromDegrees(90.0), STOWED.getWristAngleRot2d());
//
//    // Coral Input
//    public static final ArmPosition GROUND_CORAL_STRAIGHT =
//            new ArmPosition(0.0, Rotation2d.kZero, Rotation2d.fromDegrees(-2.0));
//    public static final ArmPosition GROUND_CORAL_HORIZONTAL =
//            new ArmPosition(0.0, Rotation2d.fromDegrees(4.0), Rotation2d.fromDegrees(-10.0));
//
//    public static final ArmPosition MARK_CORAL = new ArmPosition(0.0, Rotation2d.kZero, Rotation2d.fromDegrees(0.0));
//    public static final ArmPosition STATION_CORAL =
//            new ArmPosition(Units.inchesToMeters(5.6), Rotation2d.fromDegrees(69), Rotation2d.fromDegrees(-31));
//    public static final ArmPosition MARK_PUNCH =
//            new ArmPosition(Units.inchesToMeters(0.0), Rotation2d.fromDegrees(0.0), Rotation2d.fromDegrees(20.0));
//
//    // Coral Output
//    public static final ArmPosition L2_CORAL_BACK =
//            new ArmPosition(0, Rotation2d.fromDegrees(107), Rotation2d.fromDegrees(122));
//    public static final ArmPosition L3_CORAL_BACK =
//            new ArmPosition(Units.inchesToMeters(13), Rotation2d.fromDegrees(100), Rotation2d.fromDegrees(115));
//    public static final ArmPosition L4_CORAL_BACK =
//            new ArmPosition(Units.inchesToMeters(40.5), Rotation2d.fromDegrees(100), Rotation2d.fromDegrees(146));
//
//    public static final ArmPosition L1_CORAL_FRONT =
//            new ArmPosition(0.0, Rotation2d.fromDegrees(37), Rotation2d.fromDegrees(0));
//
//    public static final ArmPosition L1_CORAL_BASE =
//            new ArmPosition(0.0, Rotation2d.fromDegrees(35.0), Rotation2d.fromDegrees(-8.0));
//
//    public static final ArmPosition L1_CORAL_TOP =
//            new ArmPosition(0.0, Rotation2d.fromDegrees(43), Rotation2d.fromDegrees(-20));
//
//    public static final ArmPosition L2_CORAL_FRONT =
//            new ArmPosition(Units.inchesToMeters(9.45), Rotation2d.fromDegrees(38), Rotation2d.fromDegrees(118));
//    public static final ArmPosition L3_CORAL_FRONT =
//            new ArmPosition(Units.inchesToMeters(19), Rotation2d.fromDegrees(56), Rotation2d.fromDegrees(95));
//    public static final ArmPosition L4_CORAL_FRONT =
//            new ArmPosition(Units.inchesToMeters(39.75), Rotation2d.fromDegrees(70.5), Rotation2d.fromDegrees(40));
//
//    // Algae Input
//    public static final ArmPosition L2_REEF_ALGAE_BACK =
//            new ArmPosition(Units.inchesToMeters(7), Rotation2d.fromDegrees(105), Rotation2d.fromDegrees(100));
//    public static final ArmPosition L3_REEF_ALGAE_BACK =
//            new ArmPosition(Units.inchesToMeters(24), Rotation2d.fromDegrees(105), Rotation2d.fromDegrees(112));
//    public static final ArmPosition L2_REEF_ALGAE_FRONT =
//            new ArmPosition(Units.inchesToMeters(11), Rotation2d.fromDegrees(57.6), Rotation2d.fromDegrees(-65));
//    public static final ArmPosition L3_REEF_ALGAE_FRONT =
//            new ArmPosition(Units.inchesToMeters(24), Rotation2d.fromDegrees(65.2), Rotation2d.fromDegrees(-70));
//    public static final ArmPosition L2_REEF_ALGAE_BACK_INTERMEDIATE = new ArmPosition(
//            L2_REEF_ALGAE_BACK.getExtensionLengthMeters(),
//            L2_REEF_ALGAE_BACK.getShoulderAngleRot2d().minus(Rotation2d.fromDegrees(10.0)),
//            L2_REEF_ALGAE_BACK.getWristAngleRot2d());
//    public static final ArmPosition L3_REEF_ALGAE_BACK_INTERMEDIATE = new ArmPosition(
//            L3_REEF_ALGAE_BACK.getExtensionLengthMeters(),
//            L3_REEF_ALGAE_BACK.getShoulderAngleRot2d(),
//            L3_REEF_ALGAE_BACK.getWristAngleRot2d());
//    public static final ArmPosition L2_REEF_ALGAE_FRONT_INTERMEDIATE = new ArmPosition(
//            L2_REEF_ALGAE_FRONT.getExtensionLengthMeters(),
//            L2_REEF_ALGAE_FRONT.getShoulderAngleRot2d(),
//            L2_REEF_ALGAE_FRONT.getWristAngleRot2d());
//    public static final ArmPosition L3_REEF_ALGAE_FRONT_INTERMEDIATE = new ArmPosition(
//            L3_REEF_ALGAE_FRONT.getExtensionLengthMeters(),
//            L3_REEF_ALGAE_FRONT.getShoulderAngleRot2d(),
//            L3_REEF_ALGAE_FRONT.getWristAngleRot2d());
//
//    public static final ArmPosition GROUND_ALGAE =
//            new ArmPosition(0.0, Rotation2d.fromDegrees(32), Rotation2d.fromDegrees(-85));
//    public static final ArmPosition MARK_ALGAE =
//            new ArmPosition(Units.inchesToMeters(0.0), Rotation2d.fromDegrees(49.31), Rotation2d.fromDegrees(-81.38));
//    public static final ArmPosition HP_ALGAE =
//            new ArmPosition(Units.inchesToMeters(14.0), Rotation2d.fromDegrees(85.0), Rotation2d.fromDegrees(-80.0));
//
//    // Algae Output
//    public static final ArmPosition NET_ALGAE =
//            new ArmPosition(Units.inchesToMeters(40.5), Rotation2d.fromDegrees(90), Rotation2d.fromDegrees(-20));
//    public static final ArmPosition NET_ALGAE_PREP =
//            new ArmPosition(Units.inchesToMeters(35.0), Rotation2d.fromDegrees(90), Rotation2d.fromDegrees(-20));
//    public static final ArmPosition PROCESSOR_ALGAE =
//            new ArmPosition(0.0, Rotation2d.fromDegrees(0.0), Rotation2d.fromDegrees(25.0));
//
//    public static final ArmPosition HOLD_CORAL_TELEOP =
//            new ArmPosition(0.0, L4_CORAL_FRONT.getShoulderAngleRot2d(), Rotation2d.fromDegrees(80.0));
//    public static final ArmPosition HOLD_CORAL_AUTO =
//            new ArmPosition(0.0, L4_CORAL_BACK.getShoulderAngleRot2d(), Rotation2d.fromDegrees(100.0));
//    public static final ArmPosition HOLD_ALGAE = new ArmPosition(
//            Units.inchesToMeters(0.0), NET_ALGAE.getShoulderAngleRot2d(), NET_ALGAE.getWristAngleRot2d());
}
package frc.robot.utils;


import frc.robot.subsystems.arm.ArmPoseConstants;

public class ScoringData {

    public enum ReefDirection {
        FAR,
        CLOSE
    }

    public enum BallLevel {
        NET(ArmPoseConstants.NET),
        PROCESSOR(ArmPoseConstants.PROCESSOR);

        private final ArmPosition armPosition;

        BallLevel(ArmPosition armPosition) {
            this.armPosition = armPosition;
        }

        public ArmPosition getArmPosition() {
            return armPosition;
        }
    }

    public enum ReefLevel {
        L1(ArmPoseConstants.L1),
        L2(ArmPoseConstants.L2),
        L3(ArmPoseConstants.L3),
        L4(ArmPoseConstants.L4);

        private final ArmPosition armPosition;

        ReefLevel(ArmPosition armPosition) {
            this.armPosition = armPosition;
        }

        public ArmPosition getArmPosition() {
            return armPosition;
        }
    }

    public enum BallIntakeType {
        GROUND,
        REEF
    }

    public enum BallScoreType {
        NET,
        PROCESSOR
    }
}


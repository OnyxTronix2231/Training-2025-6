package frc.robot.utils;

public class ArmPosition {
    private final double elevatorLengthMeters;
    private final double wristAngle;

    public ArmPosition(double elevatorLengthMeters, double wristAngle) {
        this.elevatorLengthMeters = elevatorLengthMeters;
        this.wristAngle = wristAngle;
    }

    public double getWristAngle() {
        return wristAngle;
    }

    public double getElevatorLengthMeters() {
        return elevatorLengthMeters;
    }
}
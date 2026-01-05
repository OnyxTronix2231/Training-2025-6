package frc.robot.subsystems.arm;

public class ArmPosition {
    private final double elevatorLength;
    private final double wristAngle;

    public ArmPosition(double elevatorLength, double wristAngle) {
        this.elevatorLength = elevatorLength;
        this.wristAngle = wristAngle;
    }

    public double getElevatorLength() {
        return elevatorLength;
    }

    public double getWristAngle() {
        return wristAngle;
    }

    public ArmPosition changePositionBy(double length, double angle) {
        return new ArmPosition(elevatorLength + length, wristAngle + angle);
    }

    public ArmPosition createAClone() {
        return new ArmPosition(elevatorLength, wristAngle);
    }
}

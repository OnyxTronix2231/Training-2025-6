package frc.robot.subsystems.arm;

public class ArmPosition {

    private final double elevatorLength;
    private final double armAngle;

    public ArmPosition(double elevatorLength, double armAngle) {
        this.elevatorLength = elevatorLength;
        this.armAngle = armAngle;
    }

    public double getElevatorLength() {
        return elevatorLength;
    }

    public double getArmAngle() {
        return armAngle;
    }
}
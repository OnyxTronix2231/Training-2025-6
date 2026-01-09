package frc.robot.subsystems.arm;

public class ArmPosition {
    private double wristAngle;
    private double elevatorHeight;

    public ArmPosition(double wristAngle, double elevatorHeight) {
        this.wristAngle = wristAngle;
        this.elevatorHeight = elevatorHeight;
    }

    public double getWristAngle() {
        return wristAngle;
    }

    public void setWristAngle(double wristAngle) {
        this.wristAngle = wristAngle;
    }

    public double getElevatorHeight() {
        return elevatorHeight;
    }

    public void setElevatorHeight(double elevatorHeight) {
        this.elevatorHeight = elevatorHeight;
    }

    public ArmPosition createAClone() {
        return new ArmPosition(wristAngle, elevatorHeight);
    }
}

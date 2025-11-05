package Lrobot.elevator;

public interface ElevatorIO {

    void updateInputs(ElevatorInputs inputs);

    class ElevatorInputs {
        public double elevatorLength;
        public double elevatorMotorPosition;
        public double elevatorSupplyCurrentAmps;
        public double elevatorStatorCurrentAmps;
        public double elevatorAngularVelocityRadPerSec;
        public double elevatorAngularAccelerationRadPerSecSquared;
        public double elevatorMasterMotorTemp;
        public double elevatorFollowerMotorTemp;
    }

    void moveToLength(double length);

    void resetElevatorPosition(double length);

    void setDutyCycle(double dutyCycle);

    void updatePID(double p, double i, double d);
}

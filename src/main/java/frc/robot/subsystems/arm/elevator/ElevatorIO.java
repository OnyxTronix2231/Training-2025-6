package frc.robot.subsystems.arm.elevator;

public interface ElevatorIO {
    void updateInputs(ElevatorIOInputs inputs);

    class ElevatorIOInputs {
        public double elevatorPrimaryMotorDutyCycle;
        public double elevatorPrimaryMotorAppliedVolts;
        public double elevatorPrimaryMotorSupplyCurrentAmps;
        public double elevatorPrimaryMotorStatorCurrentAmps;
        public double elevatorPrimaryMotorAngularVelocityRadPerSec;
        public double elevatorPrimaryMotorAngularAccelerationRadPerSecSquare;
        public double elevatorPrimaryMotorMotorTemp;
        public double elevatorSlaveMotorDutyCycle;
        public double elevatorSlaveMotorAppliedVolts;
        public double elevatorSlaveMotorSupplyCurrentAmps;
        public double elevatorSlaveMotorStatorCurrentAmps;
        public double elevatorSlaveMotorAngularVelocityRadPerSec;
        public double elevatorSlaveMotorAngularAccelerationRadPerSecSquare;
        public double elevatorSlaveMotorMotorTemp;
    }

    void setDutyCycle(double dutyCycle);

    double getHeight();






}

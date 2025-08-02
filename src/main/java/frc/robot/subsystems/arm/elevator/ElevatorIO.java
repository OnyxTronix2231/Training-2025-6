package frc.robot.subsystems.arm.elevator;

import com.ctre.phoenix6.signals.NeutralModeValue;

public interface ElevatorIO {
    void updateInputs(ElevatorIOInputs inputs);

    class ElevatorIOInputs {
        public double elevatorLength;
        public double elevatorAppliedVolts;
        public double elevatorSupplyCurrentAmps;
        public double elevatorStatorCurrentAmps;
        public double elevatorVelocity;
        public double elevatorAcceleration;
        public double elevatorMotorMasterTemp;
        public double elevatorMotorFollowerTemp;

        public boolean isElevatorMicroSwitchPressed;
    }

    void setTargetLength(double length);

    void resetElevatorPosition(double length);

    void setDutyCycle(double dutyCycle);

    void setNeutralMode(NeutralModeValue neutralMode);
}

package frc.robot.subsystems.arm.wrist;

import com.ctre.phoenix6.signals.NeutralModeValue;

public interface WristIO {
    void updateInputs(WristIOInputs inputs);

    class WristIOInputs {
        public double wristAngle;

        public double wristAppliedVolts;
        public double wristSupplyCurrentAmps;
        public double wristStatorCurrentAmps;
        public double wristAngularVelocityRadPerSec;
        public double wristAngularAccelerationRadPerSecSquared;

        public double wristMotorTemp;
    }

    void setTargetAngle(double angle);

    void setDutyCycle(double dutyCycle);

    void resetWristAngle(double angle);

    void setNeutralMode(NeutralModeValue neutralMode);
}

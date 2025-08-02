package frc.robot.subsystems.coralHolder;

public interface CoralHolderIO {

    void updateInputs(CoralHolderIOInputs inputs);

    class CoralHolderIOInputs {

        public double coralHolderAppliedVolts;
        public double coralHolderSupplyCurrentAmps;
        public double coralHolderStatorCurrentAmps;
        public double coralHolderMotorTemp;
        public double coralHolderVelocityRPS;

        public boolean isBackSensorTripped = false;
        public boolean isFrontSensorTripped = false;
    }

    void setDutyCycle(double dutyCycle);
}

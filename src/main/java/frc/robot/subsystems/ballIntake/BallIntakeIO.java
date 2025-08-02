package frc.robot.subsystems.ballIntake;

public interface BallIntakeIO {
    void updateInputs(BallIntakeIOInputs inputs);

    class BallIntakeIOInputs {

        public double ballIntakeAppliedVolts;
        public double ballIntakeSupplyCurrentAmps;
        public double ballIntakeStatorCurrentAmps;
        public double ballIntakeMotorTemp;
        public double ballIntakeVelocityRPS;

        public boolean isBallIntakeMicroSwitchPressed = false;

    }

    void setDutyCycle(double dutyCycle);

    boolean reachedCurrentLimit(BallIntakeIOInputs inputs);
}

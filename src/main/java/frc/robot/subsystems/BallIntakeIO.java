package frc.robot.subsystems;

import frc.robot.lib.OnyxMotorInputs;

public interface BallIntakeIO {
    void updateInputs(BallIntakeIOInputs inputs);

    class BallIntakeIOInputs {
        public boolean isLimitSwitchPressed;

        public OnyxMotorInputs ballIntakeMotorInputs;
    }

    boolean isLimitSwitchPressed();

    void setDutyCycle(double dutyCycle);
}

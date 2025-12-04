package frc.robot.subsystems.ballIntake;

import frc.robot.lib.OnyxMotorInputs;

public interface BallIntakeIO {

    void updateInputs(BallIntakeInputs inputs);

    class BallIntakeInputs {
        boolean isLimitSwitchPressed;

        public OnyxMotorInputs ballIntakeMasterInputs;
    }

    boolean isMicroswitchPressed();

    void setDutyCycle(double dutyCycle);
}

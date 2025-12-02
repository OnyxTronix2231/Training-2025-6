package frc.robot.subsystems.ballIntake;

import frc.robot.lib.OnyxMotorInputs;

public interface BallIntakeIO {

    void updateInputs(BallIntakeInputs inputs);

    class BallIntakeInputs{
        boolean isLimitSwitchPressed;

        OnyxMotorInputs ballIntakeMotorInputs;
    }

    boolean isLimitSwitchPressed();

    void setDutyCycle(double dutyCycle);
}

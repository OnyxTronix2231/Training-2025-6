package frc.robot.subsystems.ballIntake;

import frc.robot.lib.OnyxMotorInputs;

public interface BallIntakeIO {
    void updateInputs(ballIntakeInputs inputs);

    class ballIntakeInputs {
        public boolean isMicroswitchPressed;

        public OnyxMotorInputs ballIntakeInput;
    }

    double getCurrent();

    boolean isMicroswitchPressed();

    void setDutyCycle(double dutyCycle);
}

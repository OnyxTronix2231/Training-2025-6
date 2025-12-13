package Lrobot.elevator;

import frc.robot.lib.OnyxMotorInputs;

public interface ElevatorIO {

    void updateInputs(ElevatorInputs inputs);

    class ElevatorInputs {
        public boolean isFirstSwitchPressed;
        public boolean isSecondSwitchPressed;

        public OnyxMotorInputs elevatorMasterInputs;
        public OnyxMotorInputs elevatorFollowerInputs;
    }

    double getCurrent();

    boolean isFirstSwitchPressed();

    boolean isSecondSwitchPressed();

    void setDutyCycle(double dutyCycle);

    void moveToLength(double length);
}

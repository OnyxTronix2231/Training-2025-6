package Lrobot.elevator;

import frc.robot.lib.OnyxMotorInputs;

public interface ElevatorIO {

    void updateInputs(ElevatorInputs inputs);

    class ElevatorInputs {
        public boolean isMicroSwitchPressed;

        public OnyxMotorInputs elevatorMasterInputs;
        public OnyxMotorInputs elevatorFollowerInputs;
    }

    void setDutyCycle(double dutyCycle);
}

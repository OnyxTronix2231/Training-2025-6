package Lrobot.elevator;

import frc.robot.lib.OnyxMotorInputs;

public interface ElevatorIO {

    void updateInputs(ElevatorInputs inputs);

    class ElevatorInputs {
        public boolean isMicroSwitchPressed;
        public boolean isFirstSensorPressed;
        public boolean isSecondSensorPressed;

        public OnyxMotorInputs elevatorMasterInputs;
        public OnyxMotorInputs elevatorFollowerInputs;
    }

    double getCurrent();

    boolean isMicroswitchPressed();

    boolean isFirstSensorPressed();

    boolean isSecondSensorPressed();

    void setDutyCycle(double dutyCycle);

}

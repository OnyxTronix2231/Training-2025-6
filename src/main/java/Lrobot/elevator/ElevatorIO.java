package Lrobot.elevator;

import frc.robot.lib.OnyxMotorInputs;

public interface ElevatorIO {

    void updateInputs(ElevatorInputs inputs);

    class ElevatorInputs {
        public boolean isMicroSwitchPressed;
        public boolean isSensor1Pressed;
        public boolean isSensor2Pressed;


        public OnyxMotorInputs elevatorMasterInputs;
        public OnyxMotorInputs elevatorFollowerInputs;

    }
    boolean getLimitSwitchValue();

    boolean isSensor1();
    boolean isSensor2();

    double getCurrent();

    boolean isMicroswitchPressed();

    void setDutyCycle(double dutyCycle);
}

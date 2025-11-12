package Lrobot.Hinge;

import Lrobot.elevator.ElevatorIO;
import frc.robot.lib.OnyxMotorInputs;

public interface HingeIO {

    void updateInputs(HingeInputs inputs);

    class HingeInputs {

        public OnyxMotorInputs hingeMotorInputs;

    }
    void setDutyCycle(double dutyCycle);

}

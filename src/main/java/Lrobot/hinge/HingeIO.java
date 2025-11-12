package Lrobot.hinge;

import frc.robot.lib.OnyxMotorInputs;

public interface HingeIO {

    void updateInputs(HingeInputs inputs);

    class HingeInputs {
        public OnyxMotorInputs hingeInputs;
    }

    void setDutyCycle(double dutyCycle);
}

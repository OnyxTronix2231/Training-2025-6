package Lrobot.hinge;


import frc.robot.lib.OnyxMotorInputs;

public interface HingeIO {
    void updateInputs(HingeIO.HingeInputs inputs);

    class HingeInputs {
        public OnyxMotorInputs hingeInputs;
    }
    void setDutyCycle(double dutyCycle);

}

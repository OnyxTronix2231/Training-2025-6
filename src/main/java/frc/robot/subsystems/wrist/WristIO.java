package frc.robot.subsystems.wrist;

import frc.robot.lib.OnyxMotorInputs;

public interface WristIO {

    void updateInputs(WristInputs inputs);

    class WristInputs {
        double encoderPosition;

        public OnyxMotorInputs wristMasterInputs;
    }

    void setDutyCycle(double dutyCycle);

    void moveToAngle(double angle);
}

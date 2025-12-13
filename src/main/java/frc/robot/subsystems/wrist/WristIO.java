package frc.robot.subsystems.wrist;

import frc.robot.lib.OnyxMotorInputs;

public interface WristIO {

    void updateInputs(WristInputs inputs);

    class WristInputs {
        public double encoderPosition;

        public OnyxMotorInputs wristMotorInputs;
    }

    double getEncoderPosition();

    void setDutyCycle(double dutyCycle);

    void moveToAngle(double angle);
}

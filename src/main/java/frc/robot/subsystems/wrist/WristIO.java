package frc.robot.subsystems.wrist;

import frc.robot.lib.OnyxMotorInputs;

public interface WristIO {

    void updateInputs(WristInputs inputs);

    class WristInputs {

        public double encoderPosition;

        public OnyxMotorInputs wristMotor;

    }

    void setDutyCycle(double dutyCycle);

    void moveToAngle(double angle);

}

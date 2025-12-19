package frc.robot.subsystems.arm.wrist;

import frc.robot.lib.OnyxMotorInputs;
import frc.robot.lib.PID.PIDValues;

public interface WristIO {
    void updateInputs(WristInputs inputs);

    class WristInputs {
        public double encoderPosition;

        public OnyxMotorInputs wristInputs;
    }

    void setDutyCycle(double dutyCycle);

    void updatePID(PIDValues pidValues);

    void moveToAngle(double angle);
}

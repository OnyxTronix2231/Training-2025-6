package frc.robot.subsystems.arm.wrist;

import frc.robot.lib.OnyxMotorInputs;
import frc.robot.lib.PID.PIDValues;

public interface WristIO {
    void updateInputs(WristIOInputs inputs);

    class WristIOInputs {
        public OnyxMotorInputs motorInputs;

        public double wristAngle;
    }

    void setDutyCycle(double dutyCycle);

    double getWristAngle();

    void moveWristToAngle(double angle, int slot);

    boolean isOnTarget(double target);

    void updatePIDSlot0(PIDValues PIDValues);

    void updatePIDSlot1(PIDValues PIDValues);

    void setBrakeMode();

    void setCoastMode();
}

package frc.robot.subsystems.arm.wrist;

import frc.robot.lib.OnyxMotorInputs;

public interface WristIO {
    void updateInputs(WristIOInputs inputs);

    class WristIOInputs {
        public OnyxMotorInputs motorInputs;

        public double wristAngle;
    }

    void setDutyCycle(double dutyCycle);

    void stop();

    double getWristAngle();

    void moveWristToAngle(double angle);

    boolean isOnTarget(double target);

    void updatePID(double kP, double kI, double kD, double kG);
}

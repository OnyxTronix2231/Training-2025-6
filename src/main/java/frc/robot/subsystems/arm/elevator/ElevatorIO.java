package frc.robot.subsystems.arm.elevator;

import frc.robot.lib.OnyxMotorInputs;

public interface ElevatorIO {
    void updateInputs(ElevatorIOInputs inputs);

    class ElevatorIOInputs {
        public OnyxMotorInputs masterMotorInputs;
        public OnyxMotorInputs followerMotorInputs;
        public boolean isLimitSwitchPressed;
        public double elevatorHeight;
    }

    void setDutyCycle(double dutyCycle);

    void stop();

    double getHeight();

    void moveElevatorToHeight(double height);

    boolean isOnTarget(double target);

    void updatePID(double kP, double kI, double kD);

}

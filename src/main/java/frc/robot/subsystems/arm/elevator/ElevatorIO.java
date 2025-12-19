package frc.robot.subsystems.arm.elevator;

import frc.robot.lib.OnyxMotorInputs;
import frc.robot.lib.PID.PIDValues;

public interface ElevatorIO {
  void updateInputs(ElevatorInputs inputs);

  class ElevatorInputs {
    public boolean isMicroSwitchPressed;

    public OnyxMotorInputs elevatorMasterInputs;
    public OnyxMotorInputs elevatorFollowerInputs;
  }

  void setDutyCycle(double dutyCycle);

  void updatePID(PIDValues pidValues);

  void moveToLength(double length);
}

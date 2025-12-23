package frc.robot.subsystems.coralHolder;

import frc.robot.lib.OnyxMotorInputs;

public interface CoralHolderIO {
    void updateInputs(CoralHolderInputs inputs);

    class CoralHolderInputs {
        public boolean isOuterSensorDetecting;
        public boolean isInnerSensorDetecting;

        public OnyxMotorInputs coralHolderInputs;
    }

    void setDutyCycle(double dutyCycle);

    boolean isInnerSensorDetecting();

    boolean isOuterSensorDetecting();
}

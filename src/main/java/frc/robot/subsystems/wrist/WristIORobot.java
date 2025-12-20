package frc.robot.subsystems.wrist;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.FeedbackSensorSourceValue;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import frc.robot.lib.OnyxMotorInputs;

import static Lrobot.elevator.ElevatorConstants.LENGTH_TO_ROTATIONS;
import static Lrobot.elevator.ElevatorConstants.ROTATIONS_TO_LENGTH_ROBOT;
import static frc.robot.subsystems.wrist.WristConstants.*;

public class WristIORobot implements WristIO {

    private final TalonFX motor;

    private final OnyxMotorInputs WristMotorInputs;

    private final CANcoder encoder;

    private final PositionVoltage positionController;

    public WristIORobot() {
        motor = new TalonFX(WRIST_MOTOR_ID);

        encoder = new CANcoder(WRIST_ENCODER_ID);

        positionController = new PositionVoltage(0);

        WristMotorInputs = new OnyxMotorInputs(motor, "Wrist", "WristMaster", ROTATIONS_TO_ANGLE);

        WristMotorInputs.updateInputs();

        motor.getConfigurator().apply(getTalonFXConfiguration());
    }

    public TalonFXConfiguration getTalonFXConfiguration(){
        TalonFXConfiguration configs = new TalonFXConfiguration();

        configs.Feedback.FeedbackSensorSource = FeedbackSensorSourceValue.FusedCANcoder;
        configs.Feedback.FeedbackRemoteSensorID = WRIST_ENCODER_ID;
        configs.Feedback.RotorToSensorRatio = CONVERSION_RATE_ARM;

        configs.Slot0 = WRIST_PID_VALUES.pidValuesToSlot0Configs();

        configs.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;

        configs.MotorOutput.NeutralMode = NeutralModeValue.Brake;

        return configs;
    }

    @Override
    public void updateInputs(WristInputs inputs) {
        WristMotorInputs.updateInputs();
        inputs.wristMasterInputs = WristMotorInputs;

        inputs.encoderPosition = encoder.getPosition().getValueAsDouble();
    }

    @Override
    public void setDutyCycle(double dutyCycle) {
        motor.set(dutyCycle);
    }

    @Override
    public void moveToAngle(double angle) {
        motor.setControl(positionController.withPosition(angle/360.0));
    }
}

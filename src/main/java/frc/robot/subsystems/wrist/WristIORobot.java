package frc.robot.subsystems.wrist;

import com.ctre.phoenix6.configs.MagnetSensorConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.FeedbackSensorSourceValue;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.signals.SensorDirectionValue;
import frc.robot.lib.OnyxMotorInputs;

import static frc.robot.subsystems.wrist.WristConstants.*;

public class WristIORobot implements WristIO {
    private final TalonFX motor;

    private final OnyxMotorInputs wristMotorInputs;

    private final CANcoder encoder;

    private final PositionVoltage angleController;

    public WristIORobot() {
        motor = new TalonFX(WRIST_MOTOR_ID);

        wristMotorInputs = new OnyxMotorInputs(motor, "Wrist", "wristMotor", ROTATIONS_TO_ANGLE);

        wristMotorInputs.updateInputs();

        motor.getConfigurator().apply(getTalonFXConfiguration());

        encoder = new CANcoder(WRIST_ENCODER_ID);

        encoder.getConfigurator().apply(getMagnetSensorConfiguration());

        angleController = new PositionVoltage(0);
    }

    public TalonFXConfiguration getTalonFXConfiguration() {
        TalonFXConfiguration configuration = new TalonFXConfiguration();

        configuration.Feedback.FeedbackSensorSource = FeedbackSensorSourceValue.FusedCANcoder;
        configuration.Feedback.FeedbackRemoteSensorID = WRIST_ENCODER_ID;
        configuration.Feedback.RotorToSensorRatio = CONVERSION_RATE_WRIST;

        configuration.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;

        configuration.Slot0 = WRIST_PID_VALUES.pidValuesToSlot0Configs();

        configuration.MotorOutput.NeutralMode = NeutralModeValue.Brake;

        return configuration;
    }

    public MagnetSensorConfigs getMagnetSensorConfiguration() {
        MagnetSensorConfigs configuration = new MagnetSensorConfigs();

        configuration.withMagnetOffset(WRIST_CANCODER_OFFSET);
        configuration.withSensorDirection(SensorDirectionValue.Clockwise_Positive);

        return configuration;
    }

    @Override
    public void updateInputs(WristInputs inputs) {
        wristMotorInputs.updateInputs();
        inputs.wristMotorInputs = wristMotorInputs;

        inputs.encoderPosition = encoder.getPosition().getValueAsDouble();
    }

    @Override
    public double getEncoderPosition() {
        return encoder.getPosition().getValueAsDouble();
    }

    @Override
    public void setDutyCycle(double dutyCycle) {
        motor.set(dutyCycle);
    }

    @Override
    public void moveToAngle(double angle) {
        motor.setControl(angleController.withPosition(ANGLE_TO_ROTATIONS(angle)));
    }
}

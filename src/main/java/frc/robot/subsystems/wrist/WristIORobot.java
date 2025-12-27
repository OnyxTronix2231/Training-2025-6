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

    private final TalonFX wristMotor;

    private final OnyxMotorInputs wristMotorInputs;

    private final CANcoder encoder;

    private final PositionVoltage positionController;

    public WristIORobot() {
        wristMotor = new TalonFX(WRIST_MOTOR_ID);

        wristMotorInputs = new OnyxMotorInputs(wristMotor, "Wrist", "wristMotor", ROTATIONS_TO_ANGLE);

        wristMotorInputs.updateInputs();

        wristMotor.getConfigurator().apply(getTalonFXConfiguration());

        encoder = new CANcoder(WRIST_ENCODER_ID);

        encoder.getConfigurator().apply(getMagnetSensorConfig());

        positionController = new PositionVoltage(0);
    }

    public TalonFXConfiguration getTalonFXConfiguration() {
        TalonFXConfiguration configuration = new TalonFXConfiguration();

        configuration.Slot0 = WRIST_PID_VALUES.pidValuesToSlot0Configs();

        configuration.Feedback.FeedbackSensorSource = FeedbackSensorSourceValue.FusedCANcoder;
        configuration.Feedback.FeedbackRemoteSensorID = WRIST_ENCODER_ID;
        configuration.Feedback.RotorToSensorRatio = CONVERSION_RATE_ARM;

        configuration.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;

        configuration.MotorOutput.NeutralMode = NeutralModeValue.Brake;

        return configuration;
    }

    public MagnetSensorConfigs getMagnetSensorConfig() {
        MagnetSensorConfigs configuration = new MagnetSensorConfigs();
        configuration.withMagnetOffset(WRIST_CANCODER_OFFSET);
        configuration.withSensorDirection(SensorDirectionValue.Clockwise_Positive);
        return configuration;
    }


    public void updateInputs(WristInputs inputs) {
        wristMotorInputs.updateInputs();

        inputs.wristMotor = wristMotorInputs;

        inputs.encoderPosition = encoder.getPosition().getValueAsDouble();
    }

    public void setDutyCycle(double dutyCycle) {
        wristMotor.set(dutyCycle);
    }

    @Override
    public void moveToAngle(double angle) {
        wristMotor.setControl(positionController.withPosition(angle/360));
    }


}

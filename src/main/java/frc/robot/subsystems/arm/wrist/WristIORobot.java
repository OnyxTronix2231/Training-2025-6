package frc.robot.subsystems.arm.wrist;

import com.ctre.phoenix6.configs.MagnetSensorConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.FeedbackSensorSourceValue;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.signals.SensorDirectionValue;
import frc.robot.lib.OnyxMotorInputs;
import frc.robot.lib.PID.PIDValues;

import static frc.robot.subsystems.arm.wrist.WristConstants.*;

public class WristIORobot implements WristIO {
    private final TalonFX motor;

    private final OnyxMotorInputs wristMotorInputs;

    private final CANcoder encoder;

    private final MotionMagicVoltage motionMagicVoltage = new MotionMagicVoltage(0).withSlot(0);

    public WristIORobot() {
        motor = new TalonFX(WRIST_MOTOR_ID);

        wristMotorInputs = new OnyxMotorInputs(motor, "Arm", "wristMotor", ROTATIONS_TO_ANGLE);

        motor.getConfigurator().apply(getTalonFXConfiguration());
        motor.setNeutralMode(NeutralModeValue.Brake);

        encoder = new CANcoder(WRIST_CANCODER_ID);

        encoder.getConfigurator().apply(getMagnetSensorConfigs());
    }

    public TalonFXConfiguration getTalonFXConfiguration() {
        TalonFXConfiguration configuration = new TalonFXConfiguration();

        configuration.Feedback.FeedbackSensorSource = FeedbackSensorSourceValue.FusedCANcoder;
        configuration.Feedback.FeedbackRemoteSensorID = WRIST_CANCODER_ID;
        configuration.Feedback.RotorToSensorRatio = CONVERSION_RATE_WRIST;

        configuration.Slot0 = WRIST_PID_VALUES.pidValuesToSlot0Configs();

        configuration.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;

        configuration.MotionMagic.MotionMagicCruiseVelocity = WRIST_CRUISE_VELOCITY;
        configuration.MotionMagic.MotionMagicAcceleration = WRIST_ACCELERATION;

        return configuration;
    }

    public MagnetSensorConfigs getMagnetSensorConfigs() {
        MagnetSensorConfigs configuration = new MagnetSensorConfigs();

        configuration.withMagnetOffset(WRIST_CANCODER_OFFSET);

        configuration.withSensorDirection(SensorDirectionValue.CounterClockwise_Positive);

        return configuration;
    }

    @Override
    public void updateInputs(WristInputs inputs) {
        wristMotorInputs.updateInputs();
        inputs.wristMotorInputs = wristMotorInputs;

        inputs.encoderPosition = encoder.getPosition().getValueAsDouble();
    }

    @Override
    public void setDutyCycle(double dutyCycle) {
        motor.set(dutyCycle);
    }

    @Override
    public void moveToAngle(double angle) {
        motor.setControl(motionMagicVoltage.withPosition(ANGLE_TO_ROTATIONS(angle)));
    }

    @Override
    public void updatePID(PIDValues pidValues) {
        motor.getConfigurator().apply(pidValues.pidValuesToSlot0Configs());
    }
}

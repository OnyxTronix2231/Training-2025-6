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
import edu.wpi.first.math.util.Units;
import frc.robot.lib.OnyxMotorInputs;
import frc.robot.lib.PID.PIDValues;

import static frc.robot.subsystems.arm.wrist.WristConstants.*;

public class WristIORobot implements WristIO {

    private final TalonFX motor;

    private final OnyxMotorInputs wristMotorInputs;

    private final MotionMagicVoltage motionMagicVoltage = new MotionMagicVoltage(0.0).withSlot(WRIST_MOTION_MAGIC_DEFAULT_SLOT);

    private final CANcoder encoder;

    public WristIORobot() {
        motor = new TalonFX(WRIST_MOTOR_ID);

        wristMotorInputs = new OnyxMotorInputs(motor, WRIST_SUBSYSTEM_NAME, WRIST_MOTOR_NAME, ROTATIONS_TO_ANGLE);

        wristMotorInputs.updateInputs();

        encoder = new CANcoder(WRIST_CANCODER_ID);

        motor.getConfigurator().apply(getTalonFXConfiguration());

        encoder.getConfigurator().apply(getMagnetSensorConfigs());
    }

    public TalonFXConfiguration getTalonFXConfiguration() {
        TalonFXConfiguration configuration = new TalonFXConfiguration();

        configuration.Feedback.FeedbackSensorSource = FeedbackSensorSourceValue.FusedCANcoder;
        configuration.Feedback.FeedbackRemoteSensorID = WRIST_CANCODER_ID;
        configuration.Feedback.RotorToSensorRatio = WRIST_ROTOR_TO_SENSOR_RATIO;

        configuration.Slot0 = WRIST_PID_VALUES.pidValuesToSlot0Configs();

        configuration.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;

        configuration.MotorOutput.NeutralMode = NeutralModeValue.Brake;

        configuration.MotionMagic.MotionMagicCruiseVelocity = WRIST_CRUISE_VELOCITY;
        configuration.MotionMagic.MotionMagicAcceleration = WRIST_ACCELERATION;
        configuration.MotionMagic.MotionMagicJerk = WRIST_JERK;

        configuration.CurrentLimits.StatorCurrentLimitEnable = true;
        configuration.CurrentLimits.StatorCurrentLimit = WRIST_STATOR_LIMIT;

        configuration.CurrentLimits.SupplyCurrentLimitEnable = true;
        configuration.CurrentLimits.SupplyCurrentLimit = WRIST_SUPPLY_LIMIT;

        configuration.CurrentLimits.SupplyCurrentLowerLimit = WRIST_SUPPLY_LOWER_LIMIT;
        configuration.CurrentLimits.SupplyCurrentLowerTime = WRIST_TIME_LOWER_LIMIT;

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
        motor.setControl(motionMagicVoltage.withPosition(Units.degreesToRadians(angle)));
    }

    @Override
    public void updatePID(PIDValues pidValues) {
        pidValues.updatePIDValues(motor);
    }
}
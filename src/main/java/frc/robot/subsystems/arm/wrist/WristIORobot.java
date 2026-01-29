package frc.robot.subsystems.arm.wrist;

import com.ctre.phoenix6.configs.CANcoderConfiguration;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.PositionTorqueCurrentFOC;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.FeedbackSensorSourceValue;
import com.ctre.phoenix6.signals.GravityTypeValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.signals.SensorDirectionValue;
import frc.robot.lib.OnyxMotorInputs;
import frc.robot.lib.PID.PIDValues;

import java.util.function.UnaryOperator;

import static edu.wpi.first.units.Units.Degrees;
import static frc.robot.subsystems.arm.wrist.WristConstants.*;

public class WristIORobot implements WristIO {
    private final TalonFX motor;

    private final CANcoder canCoder;

    private final PositionTorqueCurrentFOC positionTorque = new PositionTorqueCurrentFOC(0).withSlot(WRIST_FAST_SLOT);

    private final OnyxMotorInputs motorInputs;

    public WristIORobot() {
        motor = new TalonFX(WristConstants.MOTOR_ID);
        motor.getConfigurator().apply(getMotorConfiguration());

        canCoder = new CANcoder(CANCODER_ID);
        canCoder.getConfigurator().apply(getCANCoderConfiguration());

        motorInputs = new OnyxMotorInputs(motor, "Arm/Wrist", "Motor", WRIST_ROTOR_TO_SENSOR);
    }

    private TalonFXConfiguration getMotorConfiguration() {
        TalonFXConfiguration config = new TalonFXConfiguration();

        config.Feedback.FeedbackSensorSource = FeedbackSensorSourceValue.FusedCANcoder;
        config.Feedback.FeedbackRemoteSensorID = CANCODER_ID;
        config.Feedback.RotorToSensorRatio = RATIO;

        config.Slot0.kP = KP;
        config.Slot0.kI = KI;
        config.Slot0.kD = KD;
        config.Slot0.kG = KG;
        config.Slot0.GravityType = GravityTypeValue.Arm_Cosine;

        config.MotionMagic.MotionMagicAcceleration = MOTION_MAGIC_ACCELERATION;
        config.MotionMagic.MotionMagicCruiseVelocity = MOTION_MAGIC_SPEED;
        config.MotionMagic.MotionMagicJerk = MOTION_MAGIC_JERK;

        config.MotorOutput.NeutralMode = NeutralModeValue.Brake;

        return config;
    }

    private CANcoderConfiguration getCANCoderConfiguration() {
        CANcoderConfiguration config = new CANcoderConfiguration();

        config.MagnetSensor.MagnetOffset = CANCODER_MAGNET_OFFSET;
        config.MagnetSensor.SensorDirection = SensorDirectionValue.CounterClockwise_Positive;

        return config;

    }

    @Override
    public void updateInputs(WristIOInputs inputs) {
        motorInputs.updateInputs();
        inputs.motorInputs = motorInputs;

        inputs.wristAngle = getWristAngle();
    }

    @Override
    public void setDutyCycle(double dutyCycle) {
        motor.set(dutyCycle);
    }

    @Override
    public double getWristAngle() {
        return motor.getPosition().getValue().in(Degrees);
    }

    @Override
    public void moveWristToAngle(double angle, int slot) {
        motor.setControl(positionTorque.withPosition(angle / 360).withSlot(slot));
    }

    @Override
    public boolean isOnTarget(double target) {
        return Math.abs(getWristAngle() - target) < WRIST_ANGLE_ERROR_TOLERANCE;
    }

    @Override
    public void updatePIDSlot0(PIDValues PIDValues) {
        motor.getConfigurator().apply(PIDValues.pidValuesToSlot0Configs());
    }

    @Override
    public void updatePIDSlot1(PIDValues PIDValues) {
        motor.getConfigurator().apply(PIDValues.pidValuesToSlot1Configs());
    }

    @Override
    public boolean isDetectedPush() {
        return (motor.getStatorCurrent().getValueAsDouble() - motor.getSupplyCurrent().getValueAsDouble()) < TOLERANCE;
    }
}

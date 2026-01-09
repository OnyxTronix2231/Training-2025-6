package frc.robot.subsystems.arm.wrist;

import com.ctre.phoenix6.configs.CANcoderConfiguration;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.FeedbackSensorSourceValue;
import com.ctre.phoenix6.signals.GravityTypeValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.signals.SensorDirectionValue;
import frc.robot.lib.OnyxMotorInputs;

import static edu.wpi.first.units.Units.Degrees;
import static frc.robot.subsystems.arm.wrist.WristConstants.*;

public class WristIORobot implements WristIO {
    private final TalonFX motor;

    private final CANcoder canCoder;

    private final MotionMagicVoltage motionMagicVoltage = new MotionMagicVoltage(0).withSlot(WRIST_MOTION_MAGIC_DEFAULT_SLOT);

    private final OnyxMotorInputs motorInputs;

    public WristIORobot() {
        motor = new TalonFX(WristConstants.MOTOR_ID);
        motor.getConfigurator().apply(getMotorConfiguration());

        canCoder = new CANcoder(CANCODER_ID);
        canCoder.getConfigurator().apply(getCANCoderConfiguration());

        motorInputs = new OnyxMotorInputs(motor, "Arm/Wrist", "Motor");
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
    public void stop() {
        motor.stopMotor();
    }

    @Override
    public double getWristAngle() {
        return motor.getPosition().getValue().in(Degrees);
    }

    @Override
    public void moveWristToAngle(double angle) {
        motor.setControl(motionMagicVoltage.withPosition(angle / 360));
    }

    @Override
    public boolean isOnTarget(double target) {
        return Math.abs(getWristAngle() - target) < WRIST_ANGLE_ERROR_TOLERANCE;
    }

    @Override
    public void updatePID(double kP, double kI, double kD, double kG) {
        motor.getConfigurator().apply(new Slot0Configs().withKP(kP).withKI(kI).withKD(kD).withKG(kG));
    }
}

package frc.robot.subsystems.arm.wrist;

import com.ctre.phoenix6.BaseStatusSignal;
import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.MagnetSensorConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.*;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.units.measure.*;

import static edu.wpi.first.units.Units.Degrees;
import static frc.robot.subsystems.arm.elevator.ElevatorConstants.ELEVATOR_MASTER_MOTOR_ID;
import static frc.robot.subsystems.arm.wrist.WristConstants.SimulationWRISTConstants.CONVERSION_RATE_WRIST;
import static frc.robot.subsystems.arm.wrist.WristConstants.*;

public class WristIORobotB implements WristIO {

    public final TalonFX wristMotor;

    private final CANcoder encoder;

    private final MotionMagicVoltage motionMagicVoltage = new MotionMagicVoltage(0.0).withSlot(0);

    private final StatusSignal<Angle> wristAngle;
    private final StatusSignal<Voltage> wristAppliedVolts;
    private final StatusSignal<Current> wristSupplyCurrentAmps;
    private final StatusSignal<Current> wristStatorCurrentAmps;
    private final StatusSignal<AngularVelocity> wristAngularVelocityRadPerSec;
    private final StatusSignal<AngularAcceleration> wristAngularAccelerationRadPerSecSquared;
    private final StatusSignal<Temperature> wristMotorTemp;


    // TODO CanCoder init
    public WristIORobotB() {
        wristMotor = new TalonFX(WRIST_MOTOR_ID, "DriveTrain");
        encoder = new CANcoder(WRIST_ENCODER_ID, "DriveTrain");

        encoder.getConfigurator().apply(new MagnetSensorConfigs().withMagnetOffset(WRIST_CANCODER_OFFSET + (3 / 360.0))
                .withSensorDirection(SensorDirectionValue.Clockwise_Positive)
                .withAbsoluteSensorDiscontinuityPoint(0.5));

        wristMotor.getConfigurator().apply(getTalonFXConfiguration());

        wristMotor.setNeutralMode(NeutralModeValue.Brake);
        wristMotor.setPosition(MathUtil.inputModulus(-0.5, 0.5, wristMotor.getPosition().getValueAsDouble()));
        wristAngle = wristMotor.getPosition();
        wristAppliedVolts = wristMotor.getMotorVoltage();
        wristSupplyCurrentAmps = wristMotor.getSupplyCurrent();
        wristStatorCurrentAmps = wristMotor.getStatorCurrent();
        wristAngularVelocityRadPerSec = wristMotor.getRotorVelocity();
        wristAngularAccelerationRadPerSecSquared = wristMotor.getAcceleration();
        wristMotorTemp = wristMotor.getDeviceTemp();
    }

    private TalonFXConfiguration getTalonFXConfiguration() {
        TalonFXConfiguration configs = new TalonFXConfiguration();

        configs.Feedback.FeedbackSensorSource = FeedbackSensorSourceValue.FusedCANcoder;
        configs.Feedback.FeedbackRemoteSensorID = WRIST_ENCODER_ID;
        configs.Feedback.RotorToSensorRatio = CONVERSION_RATE_WRIST;

        configs.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;

        configs.Slot0.kP = WRIST_KP;
        configs.Slot0.kI = WRIST_KI;
        configs.Slot0.kD = WRIST_KD;
        configs.Slot0.kG = WRIST_KG;

        configs.MotionMagic.MotionMagicCruiseVelocity = WRIST_MOTION_MAGIC_CRUISE_VELOCITY;
        configs.MotionMagic.MotionMagicAcceleration = WRIST_MOTION_MAGIC_ACCELERATION;
        configs.MotionMagic.MotionMagicJerk = WRIST_MOTION_MAGIC_JERK;

        configs.Slot0.GravityType = GravityTypeValue.Arm_Cosine;
        configs.Slot0.StaticFeedforwardSign = StaticFeedforwardSignValue.UseClosedLoopSign;

        configs.Slot1.kP = WRIST_KP_SLOT1;
        configs.Slot1.kI = WRIST_KI;
        configs.Slot1.kD = WRIST_KD;
        configs.Slot1.kG = WRIST_KG;

        configs.Slot1.GravityType = GravityTypeValue.Arm_Cosine;
        configs.Slot1.StaticFeedforwardSign = StaticFeedforwardSignValue.UseClosedLoopSign;

        return configs;
    }

    @Override
    public void updateInputs(WristIOInputs inputs) {
        BaseStatusSignal.refreshAll(
                wristAngle,
                wristAppliedVolts,
                wristSupplyCurrentAmps,
                wristStatorCurrentAmps,
                wristAngularVelocityRadPerSec,
                wristAngularAccelerationRadPerSecSquared,
                wristMotorTemp);
        inputs.wristAngle = wristAngle.getValue().in(Degrees);
        inputs.wristAppliedVolts = wristAppliedVolts.getValueAsDouble();
        inputs.wristSupplyCurrentAmps = wristSupplyCurrentAmps.getValueAsDouble();
        inputs.wristStatorCurrentAmps = wristStatorCurrentAmps.getValueAsDouble();
        inputs.wristAngularVelocityRadPerSec = wristAngularVelocityRadPerSec.getValueAsDouble();
        inputs.wristAngularAccelerationRadPerSecSquared = wristAngularAccelerationRadPerSecSquared.getValueAsDouble();
        inputs.wristMotorTemp = wristMotorTemp.getValueAsDouble();
    }

    @Override
    public void setTargetAngle(double angle) {
        wristMotor.setControl(motionMagicVoltage.withPosition(angle / 360.0));
    }

    @Override
    public void setDutyCycle(double dutyCycle) {
        wristMotor.set(dutyCycle);
    }

    @Override
    public void resetWristAngle(double angle) {
        wristMotor.setPosition(angle);
    }

    @Override
    public void setNeutralMode(NeutralModeValue neutralMode) {
        wristMotor.setNeutralMode(neutralMode);
    }
}

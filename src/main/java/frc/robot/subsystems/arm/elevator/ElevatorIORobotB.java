package frc.robot.subsystems.arm.elevator;

import com.ctre.phoenix6.BaseStatusSignal;
import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.GravityTypeValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.signals.StaticFeedforwardSignValue;
import edu.wpi.first.units.measure.*;

import static frc.robot.subsystems.arm.elevator.ElevatorConstants.*;
import static frc.robot.subsystems.arm.elevator.ElevatorConstants.SimulationElevatorConstants.ELEVATOR_FORWARD_HEIGHT_LIMIT;
import static frc.robot.subsystems.arm.elevator.ElevatorConstants.SimulationElevatorConstants.ELEVATOR_REVERSE_HEIGHT_LIMIT;

public class ElevatorIORobotB implements ElevatorIO {

    public final TalonFX elevatorMaster;
    public final TalonFX elevatorFollower;

    private final MotionMagicVoltage motionMagicVoltage = new MotionMagicVoltage(0.0).withSlot(0);

    private final StatusSignal<Angle> elevatorPosition;
    private final StatusSignal<Voltage> elevatorAppliedVolts;
    private final StatusSignal<Current> elevatorSupplyCurrentAmps;
    private final StatusSignal<Current> elevatorStatorCurrentAmps;
    private final StatusSignal<AngularVelocity> elevatorVelocity;
    private final StatusSignal<AngularAcceleration> elevatorAcceleration;
    private final StatusSignal<Temperature> elevatorMasterTemp;
    private final StatusSignal<Temperature> elevatorFollowerTemp;

    public ElevatorIORobotB() {
        elevatorMaster = new TalonFX(ELEVATOR_MASTER_MOTOR_ID, "DriveTrain");
        elevatorFollower = new TalonFX(ELEVATOR_SLAVE_MOTOR_ID, "DriveTrain");

        elevatorMaster.getConfigurator().apply(getTalonFXConfiguration());
        elevatorFollower.getConfigurator().apply(getTalonFXConfiguration());

        elevatorFollower.setControl(new Follower(ELEVATOR_MASTER_MOTOR_ID, true));

        elevatorMaster.setNeutralMode(NeutralModeValue.Brake);
        elevatorFollower.setNeutralMode(NeutralModeValue.Brake);

        elevatorPosition = elevatorMaster.getPosition();
        elevatorAppliedVolts = elevatorMaster.getMotorVoltage();
        elevatorSupplyCurrentAmps = elevatorMaster.getSupplyCurrent();
        elevatorStatorCurrentAmps = elevatorMaster.getStatorCurrent();
        elevatorVelocity = elevatorMaster.getRotorVelocity();
        elevatorAcceleration = elevatorMaster.getAcceleration();
        elevatorMasterTemp = elevatorMaster.getDeviceTemp();
        elevatorFollowerTemp = elevatorFollower.getDeviceTemp();
    }

    private TalonFXConfiguration getTalonFXConfiguration() {
        TalonFXConfiguration configs = new TalonFXConfiguration();
        configs.Slot0.kP = ELEVATOR_KP;
        configs.Slot0.kI = ELEVATOR_KI;
        configs.Slot0.kD = ELEVATOR_KD;
        configs.Slot0.kS = ELEVATOR_KS;
        configs.Slot0.kG = ELEVATOR_KG;
        configs.Slot0.GravityType = GravityTypeValue.Elevator_Static;
        configs.Slot0.StaticFeedforwardSign = StaticFeedforwardSignValue.UseClosedLoopSign;

        configs.MotionMagic.MotionMagicCruiseVelocity = ELEVATOR_MOTION_MAGIC_CRUISE_VELOCITY;
        configs.MotionMagic.MotionMagicAcceleration = ELEVATOR_MOTION_MAGIC_ACCELERATION;
        configs.MotionMagic.MotionMagicJerk = ELEVATOR_MOTION_MAGIC_JERK;

        configs.SoftwareLimitSwitch.ForwardSoftLimitEnable = true;
        configs.SoftwareLimitSwitch.ForwardSoftLimitThreshold = LENGTH_TO_ROTATIONS(ELEVATOR_FORWARD_HEIGHT_LIMIT);

        configs.SoftwareLimitSwitch.ReverseSoftLimitEnable = true;
        configs.SoftwareLimitSwitch.ReverseSoftLimitThreshold = LENGTH_TO_ROTATIONS(ELEVATOR_REVERSE_HEIGHT_LIMIT);

        configs.HardwareLimitSwitch.ForwardLimitEnable = false;
        configs.HardwareLimitSwitch.ReverseLimitEnable = false;
        return configs;
    }

    @Override
    public void updateInputs(frc.robot.subsystems.arm.elevator.ElevatorIO.ElevatorIOInputs inputs) {
        BaseStatusSignal.refreshAll(
                elevatorPosition,
                elevatorAppliedVolts,
                elevatorSupplyCurrentAmps,
                elevatorStatorCurrentAmps,
                elevatorVelocity,
                elevatorAcceleration,
                elevatorMasterTemp,
                elevatorFollowerTemp);
        inputs.elevatorLength = ROTATIONS_TO_LENGTH(elevatorPosition.getValueAsDouble());
        inputs.elevatorAppliedVolts = elevatorAppliedVolts.getValueAsDouble();
        inputs.elevatorSupplyCurrentAmps = elevatorSupplyCurrentAmps.getValueAsDouble();
        inputs.elevatorStatorCurrentAmps = elevatorStatorCurrentAmps.getValueAsDouble();
        inputs.elevatorVelocity = elevatorVelocity.getValueAsDouble();
        inputs.elevatorAcceleration = elevatorAcceleration.getValueAsDouble();
        inputs.elevatorMotorMasterTemp = elevatorMasterTemp.getValueAsDouble();
        inputs.elevatorMotorFollowerTemp = elevatorFollowerTemp.getValueAsDouble();
        inputs.isElevatorMicroSwitchPressed = elevatorMaster.getReverseLimit().getValueAsDouble() == 0;
    }

    @Override
    public void setTargetLength(double length) {
        elevatorMaster.setControl(motionMagicVoltage.withPosition(LENGTH_TO_ROTATIONS(length)));
    }

    @Override
    public void resetElevatorPosition(double length) {
        elevatorMaster.setPosition(LENGTH_TO_ROTATIONS(length));
    }

    @Override
    public void setDutyCycle(double dutyCycle) {
        elevatorMaster.set(dutyCycle);
    }

    @Override
    public void setNeutralMode(NeutralModeValue neutralMode) {
        elevatorMaster.setNeutralMode(neutralMode);
        elevatorFollower.setNeutralMode(neutralMode);
    }
}

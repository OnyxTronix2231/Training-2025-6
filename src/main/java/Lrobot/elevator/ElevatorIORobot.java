package Lrobot.elevator;


import com.ctre.phoenix6.BaseStatusSignal;
import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.GravityTypeValue;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.signals.StaticFeedforwardSignValue;
import edu.wpi.first.units.measure.*;
import edu.wpi.first.wpilibj.DigitalInput;

import static Lrobot.elevator.ElevatorConstants.*;

public class ElevatorIORobot implements ElevatorIO {

    private final TalonFX masterMotor;
    private final TalonFX followerMotor;

    private final MotionMagicVoltage motionMagicVoltage = new MotionMagicVoltage(0.0).withSlot(0);

    private final DigitalInput limitSwitch;
    private final StatusSignal<Angle> elevatorMotorPosition;
    private final StatusSignal<Voltage> elevatorAppliedVolts;
    private final StatusSignal<Current> elevatorSupplyCurrentAmps;
    private final StatusSignal<Current> elevatorStatorCurrentAmps;
    private final StatusSignal<AngularVelocity> elevatorAngularVelocityRadPerSec;
    private final StatusSignal<AngularAcceleration> elevatorAngularAccelerationRadPerSecSquared;
    private final StatusSignal<Temperature> elevatorMasterMotorTemp;
    private final StatusSignal<Temperature> elevatorFollowerMotorTemp;

    public ElevatorIORobot() {
        masterMotor = new TalonFX(ELEVATOR_MASTER_MOTOR_ID);
        followerMotor = new TalonFX(ELEVATOR_FOLLOWER_MOTOR_ID);

        masterMotor.getConfigurator().apply(getTalonFXConfiguration());
        followerMotor.getConfigurator().apply(getTalonFXConfiguration());

        followerMotor.setControl(new Follower(ELEVATOR_MASTER_MOTOR_ID, true));

        masterMotor.setNeutralMode(NeutralModeValue.Brake);
        followerMotor.setNeutralMode(NeutralModeValue.Brake);

        limitSwitch = new DigitalInput(ELEVATOR_LIMIT_SWITCH_CHANEL);
        elevatorMotorPosition = masterMotor.getPosition();
        elevatorAppliedVolts = masterMotor.getMotorVoltage();
        elevatorSupplyCurrentAmps = masterMotor.getSupplyCurrent();
        elevatorStatorCurrentAmps = masterMotor.getStatorCurrent();
        elevatorAngularVelocityRadPerSec = masterMotor.getVelocity();
        elevatorAngularAccelerationRadPerSecSquared = masterMotor.getAcceleration();
        elevatorMasterMotorTemp = masterMotor.getDeviceTemp();
        elevatorFollowerMotorTemp = followerMotor.getDeviceTemp();
    }

    public TalonFXConfiguration getTalonFXConfiguration() {
        TalonFXConfiguration configuration = new TalonFXConfiguration();

        configuration.Slot0.kP = ELEVATOR_KP;
        configuration.Slot0.kI = ELEVATOR_KI;
        configuration.Slot0.kD = ELEVATOR_KD;
        configuration.Slot0.kG = ELEVATOR_KG;
        configuration.Slot0.kS = ELEVATOR_KS;
        configuration.Slot0.GravityType = GravityTypeValue.Elevator_Static;
        configuration.Slot0.StaticFeedforwardSign = StaticFeedforwardSignValue.UseClosedLoopSign;

        configuration.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;

        configuration.MotionMagic.MotionMagicCruiseVelocity = ELEVATOR_CRUISE_VELOCITY;
        configuration.MotionMagic.MotionMagicAcceleration = ELEVATOR_ACCELERATION;
        configuration.MotionMagic.MotionMagicJerk = ELEVATOR_JERK;

        configuration.SoftwareLimitSwitch.ForwardSoftLimitEnable = true;
        configuration.SoftwareLimitSwitch.ForwardSoftLimitThreshold = LENGTH_TO_ROTATIONS(ELEVATOR_FORWARD_SOFT_LIMIT_THRESHOLD, false);

        configuration.SoftwareLimitSwitch.ReverseSoftLimitEnable = true;
        configuration.SoftwareLimitSwitch.ReverseSoftLimitThreshold = LENGTH_TO_ROTATIONS(ELEVATOR_REVERSE_SOFT_LIMIT_THRESHOLD, false);

        configuration.HardwareLimitSwitch.ForwardLimitEnable = false;
        configuration.HardwareLimitSwitch.ReverseLimitEnable = false;

        return configuration;
    }

    @Override
    public void updateInputs(ElevatorInputs inputs) {
        BaseStatusSignal.refreshAll(
                elevatorMotorPosition,
                elevatorAppliedVolts,
                elevatorSupplyCurrentAmps,
                elevatorStatorCurrentAmps,
                elevatorAngularVelocityRadPerSec,
                elevatorAngularAccelerationRadPerSecSquared,
                elevatorMasterMotorTemp,
                elevatorFollowerMotorTemp
        );
        inputs.isMicroSwitchPressed = limitSwitch.get();
        inputs.elevatorLength = ROTATIONS_TO_LENGTH(elevatorMotorPosition.getValueAsDouble(), false);
        inputs.elevatorMotorPosition = elevatorMotorPosition.getValueAsDouble();
        inputs.elevatorAppliedVolts = elevatorAppliedVolts.getValueAsDouble();
        inputs.elevatorSupplyCurrentAmps = elevatorSupplyCurrentAmps.getValueAsDouble();
        inputs.elevatorStatorCurrentAmps = elevatorStatorCurrentAmps.getValueAsDouble();
        inputs.elevatorAngularVelocityRadPerSec = elevatorAngularVelocityRadPerSec.getValueAsDouble();
        inputs.elevatorAngularAccelerationRadPerSecSquared = elevatorAngularAccelerationRadPerSecSquared.getValueAsDouble();
        inputs.elevatorMasterMotorTemp = elevatorMasterMotorTemp.getValueAsDouble();
        inputs.elevatorFollowerMotorTemp = elevatorFollowerMotorTemp.getValueAsDouble();

    }

    @Override
    public void moveToLength(double length) {
        masterMotor.setControl(motionMagicVoltage.withPosition(LENGTH_TO_ROTATIONS(length, false)));
    }

    @Override
    public void resetElevatorPosition(double length) {
        masterMotor.setPosition(LENGTH_TO_ROTATIONS(length, false));
    }

    @Override
    public void setDutyCycle(double dutyCycle) {
        masterMotor.set(dutyCycle);
    }

    @Override
    public void updatePID(double p, double i, double d) {
        masterMotor.getConfigurator().apply(new Slot0Configs().withKP(p).withKI(i).withKD(d));
    }
}

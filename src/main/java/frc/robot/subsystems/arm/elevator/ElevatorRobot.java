package frc.robot.subsystems.arm.elevator;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.lib.OnyxMotorInputs;
import frc.robot.lib.PID.PIDValues;

import static frc.robot.subsystems.arm.elevator.ElevatorConstants.*;

public class ElevatorRobot implements ElevatorIO {

    private final TalonFX masterMotor;
    private final TalonFX followerMotor;

    private final OnyxMotorInputs elevatorMasterMotorInputs;
    private final OnyxMotorInputs elevatorFollowerMotorInputs;

    private final DigitalInput limitSwitch;

    private final MotionMagicVoltage motionMagicVoltage = new MotionMagicVoltage(0.0).withSlot(ELEVATOR_MOTION_MAGIC_DEFAULT_SLOT);

    public ElevatorRobot() {
        masterMotor = new TalonFX(ELEVATOR_MASTER_MOTOR_ID);
        followerMotor = new TalonFX(ELEVATOR_FOLLOWER_MOTOR_ID);

        elevatorMasterMotorInputs = new OnyxMotorInputs(masterMotor, ELEVATOR_SUBSYSTEM_NAME, ELEVATOR_MOTOR_MASTER_NAME, ROTATIONS_TO_LENGTH_ROBOT);
        elevatorFollowerMotorInputs = new OnyxMotorInputs(followerMotor, ELEVATOR_SUBSYSTEM_NAME, ELEVATOR_MOTOR_FOLLOWER_NAME, ROTATIONS_TO_LENGTH_ROBOT);

        elevatorMasterMotorInputs.updateInputs();
        elevatorFollowerMotorInputs.updateInputs();

        masterMotor.getConfigurator().apply(getTalonFXConfiguration());
        followerMotor.getConfigurator().apply(getTalonFXConfiguration());

        followerMotor.setControl(new Follower(ELEVATOR_MASTER_MOTOR_ID, true));

        limitSwitch = new DigitalInput(ELEVATOR_LIMIT_SWITCH_CHANEL);
    }

    public TalonFXConfiguration getTalonFXConfiguration() {
        TalonFXConfiguration configuration = new TalonFXConfiguration();

        configuration.Slot0 = ELEVATOR_PID_VALUES.pidValuesToSlot0Configs();

        configuration.MotionMagic.MotionMagicCruiseVelocity = ELEVATOR_CRUISE_VELOCITY;
        configuration.MotionMagic.MotionMagicAcceleration = ELEVATOR_ACCELERATION;
        configuration.MotionMagic.MotionMagicJerk = ELEVATOR_JERK;

        configuration.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;

        configuration.MotorOutput.NeutralMode = NeutralModeValue.Brake;

        configuration.SoftwareLimitSwitch.ForwardSoftLimitEnable = true;
        configuration.SoftwareLimitSwitch.ForwardSoftLimitThreshold = LENGTH_TO_ROTATIONS(ELEVATOR_FORWARD_SOFT_LIMIT_THRESHOLD, false);

        configuration.SoftwareLimitSwitch.ReverseSoftLimitEnable = true;
        configuration.SoftwareLimitSwitch.ReverseSoftLimitThreshold = LENGTH_TO_ROTATIONS(ELEVATOR_REVERSE_SOFT_LIMIT_THRESHOLD, false);

        configuration.HardwareLimitSwitch.ForwardLimitEnable = false;
        configuration.HardwareLimitSwitch.ReverseLimitEnable = false;

        configuration.CurrentLimits.StatorCurrentLimitEnable = true;
        configuration.CurrentLimits.StatorCurrentLimit = ELEVATOR_STATOR_LIMIT;

        configuration.CurrentLimits.SupplyCurrentLimitEnable = true;
        configuration.CurrentLimits.SupplyCurrentLimit = ELEVATOR_SUPPLY_LIMIT;

        configuration.CurrentLimits.SupplyCurrentLowerLimit = ELEVATOR_SUPPLY_LOWER_LIMIT;
        configuration.CurrentLimits.SupplyCurrentLowerTime = ELEVATOR_TIME_LOWER_LIMIT;

        return configuration;
    }

    @Override
    public void updateInputs(ElevatorInputs inputs) {
        elevatorMasterMotorInputs.updateInputs();
        inputs.elevatorMasterInputs = elevatorMasterMotorInputs;

        elevatorFollowerMotorInputs.updateInputs();
        inputs.elevatorFollowerInputs = elevatorFollowerMotorInputs;

        inputs.isMicroSwitchPressed = limitSwitch.get();
    }

    @Override
    public void setDutyCycle(double dutyCycle) {
        masterMotor.set(dutyCycle);
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
    public void updatePID(PIDValues pidValues) {
        pidValues.updatePIDValues(masterMotor);
    }
}

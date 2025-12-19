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

public class ElevatorIORobot implements ElevatorIO {
    private final TalonFX masterMotor;
    private final TalonFX followerMotor;

    private final OnyxMotorInputs elevatorMasterMotorInputs;
    private final OnyxMotorInputs elevatorFollowerMotorInputs;

    private final DigitalInput limitSwitch;

    private final MotionMagicVoltage motionMagicVoltage = new MotionMagicVoltage(0).withSlot(0);

    public ElevatorIORobot() {
        masterMotor = new TalonFX(ELEVATOR_MASTER_MOTOR_ID);
        followerMotor = new TalonFX(ELEVATOR_FOLLOWER_MOTOR_ID);

        elevatorMasterMotorInputs = new OnyxMotorInputs(masterMotor, "Arm", "elevatorMasterMotor", ROTATIONS_TO_LENGTH_ROBOT);
        elevatorFollowerMotorInputs = new OnyxMotorInputs(followerMotor, "Arm", "elevatorFollowerMotor", ROTATIONS_TO_LENGTH_ROBOT);

        masterMotor.getConfigurator().apply(getTalonFXConfiguration());
        followerMotor.getConfigurator().apply(getTalonFXConfiguration());

        masterMotor.setNeutralMode(NeutralModeValue.Brake);
        followerMotor.setNeutralMode(NeutralModeValue.Brake);

        followerMotor.setControl(new Follower(ELEVATOR_MASTER_MOTOR_ID, true));

        limitSwitch = new DigitalInput(ELEVATOR_LIMIT_SWITCH_ID);
    }

    public TalonFXConfiguration getTalonFXConfiguration() {
        TalonFXConfiguration configuration = new TalonFXConfiguration();

        configuration.Slot0 = ELEVATOR_PID_VALUES.pidValuesToSlot0Configs();

        configuration.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;

        configuration.SoftwareLimitSwitch.ForwardSoftLimitEnable = true;
        configuration.SoftwareLimitSwitch.ForwardSoftLimitThreshold = LENGTH_TO_ROTATIONS(ELEVATOR_FORWARD_LIMIT_THRESHOLD, false);

        configuration.SoftwareLimitSwitch.ReverseSoftLimitEnable = true;
        configuration.SoftwareLimitSwitch.ReverseSoftLimitThreshold = LENGTH_TO_ROTATIONS(ELEVATOR_REVERSE_LIMIT_THRESHOLD, false);

        configuration.HardwareLimitSwitch.ForwardLimitEnable = false;
        configuration.HardwareLimitSwitch.ReverseLimitEnable = false;

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
    public void updatePID(PIDValues pidValues) {
        masterMotor.getConfigurator().apply(pidValues.pidValuesToSlot0Configs());
        followerMotor.getConfigurator().apply(pidValues.pidValuesToSlot0Configs());
    }

    @Override
    public void moveToLength(double length) {
        masterMotor.setControl(motionMagicVoltage.withPosition(LENGTH_TO_ROTATIONS(length, false)));
    }
}

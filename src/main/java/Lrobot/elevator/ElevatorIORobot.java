package Lrobot.elevator;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.lib.OnyxMotorInputs;

import static Lrobot.elevator.ElevatorConstants.*;

public class ElevatorIORobot implements ElevatorIO{
    private final TalonFX masterMotor;
    private final TalonFX followerMotor;

    private final OnyxMotorInputs elevatorMasterMotorInputs;
    private final OnyxMotorInputs elevatorFollowerMotorInputs;

    private final DigitalInput limitSwitch;


    public ElevatorIORobot() {
        masterMotor = new TalonFX(ELEVATOR_MASTER_MOTOR_ID);
        followerMotor = new TalonFX(ELEVATOR_FOLLOWER_MOTOR_ID);

        elevatorMasterMotorInputs = new OnyxMotorInputs(masterMotor, "Elevator", "elevatorMaster", ROTATIONS_TO_LENGTH_ROBOT);
        elevatorFollowerMotorInputs = new OnyxMotorInputs(followerMotor, "Elevator","elevatorFollower", ROTATIONS_TO_LENGTH_ROBOT);

        elevatorMasterMotorInputs.updateInputs();
        elevatorFollowerMotorInputs.updateInputs();

        masterMotor.getConfigurator().apply(getTalonFXConfiguration());
        followerMotor.getConfigurator().apply(getTalonFXConfiguration());

        followerMotor.setControl(new Follower(ELEVATOR_MASTER_MOTOR_ID, true));

        limitSwitch = new DigitalInput(ELEVATOR_LIMIT_SWITCH_CHANEL);
    }

    public TalonFXConfiguration getTalonFXConfiguration() {
        TalonFXConfiguration configuration = new TalonFXConfiguration();

        configuration.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;

        configuration.MotorOutput.NeutralMode = NeutralModeValue.Brake;


        configuration.SoftwareLimitSwitch.ForwardSoftLimitEnable = true;
        configuration.SoftwareLimitSwitch.ForwardSoftLimitThreshold = LENGTH_TO_ROTATIONS(ELEVATOR_FORWARD_SOFT_LIMIT_THRESHOLD, true);

        configuration.SoftwareLimitSwitch.ReverseSoftLimitEnable = true;
        configuration.SoftwareLimitSwitch.ReverseSoftLimitThreshold = LENGTH_TO_ROTATIONS(ELEVATOR_REVERSE_SOFT_LIMIT_THRESHOLD, true);

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
    public double getCurrent() {
        return elevatorMasterMotorInputs.getMotorStatorCurrentAmps();
    }

    @Override
    public boolean isMicroswitchPressed() {
        return limitSwitch.get();
    }

    @Override
    public void setDutyCycle(double dutyCycle) {
        masterMotor.set(dutyCycle);
    }

}

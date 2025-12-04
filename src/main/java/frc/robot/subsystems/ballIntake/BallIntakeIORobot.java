package frc.robot.subsystems.ballIntake;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.lib.OnyxMotorInputs;

import static Lrobot.elevator.ElevatorConstants.ELEVATOR_MASTER_MOTOR_ID;
import static Lrobot.elevator.ElevatorConstants.ROTATIONS_TO_LENGTH_ROBOT;
import static frc.robot.subsystems.ballIntake.BallIntakeConstants.BALL_INTAKE_LIMIT_SWITCH_CHANEL;
import static frc.robot.subsystems.ballIntake.BallIntakeConstants.BALL_INTAKE_MOTOR_ID;

public class BallIntakeIORobot implements BallIntakeIO {

    private final TalonFX motor;

    private final OnyxMotorInputs ballIntakeMotorInputs;

    private final DigitalInput limitSwitch;

    public BallIntakeIORobot() {
        motor = new TalonFX(BALL_INTAKE_MOTOR_ID);

        ballIntakeMotorInputs = new OnyxMotorInputs(motor, "BallIntake", "ballIntakeMaster");

        ballIntakeMotorInputs.updateInputs();

        motor.getConfigurator().apply(getTalonFXConfiguration());

        limitSwitch = new DigitalInput(BALL_INTAKE_LIMIT_SWITCH_CHANEL);
    }

    public TalonFXConfiguration getTalonFXConfiguration() {
        TalonFXConfiguration configuration = new TalonFXConfiguration();

        configuration.MotorOutput.NeutralMode = NeutralModeValue.Brake;

        return configuration;
    }

    @Override
    public void updateInputs(BallIntakeInputs inputs) {
        ballIntakeMotorInputs.updateInputs();
        inputs.ballIntakeMasterInputs = ballIntakeMotorInputs;

        inputs.isLimitSwitchPressed = isMicroswitchPressed();
    }

    @Override
    public boolean isMicroswitchPressed() {
        return !limitSwitch.get();
    }

    @Override
    public void setDutyCycle(double dutyCycle) {
        motor.set(dutyCycle);
    }
}

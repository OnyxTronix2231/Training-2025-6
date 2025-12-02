package frc.robot.subsystems.ballIntake;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.lib.OnyxMotorInputs;

import static frc.robot.subsystems.ballIntake.BallIntakeConstants.BALL_INTAKE_MOTOR_ID;
import static frc.robot.subsystems.ballIntake.BallIntakeConstants.LIMIT_SWITCH_ID;

public class BallIntakeIORobot implements BallIntakeIO{
    private final TalonFX motor;

    private final OnyxMotorInputs ballIntakeMotorInputs;

    private final DigitalInput limitSwitch;

    public BallIntakeIORobot() {
        motor = new TalonFX(BALL_INTAKE_MOTOR_ID);

        ballIntakeMotorInputs = new OnyxMotorInputs(motor,"ballIntake","ballIntakeMotor");

        ballIntakeMotorInputs.updateInputs();

        motor.getConfigurator().apply(getTalonFXConfiguration());

        limitSwitch = new DigitalInput(LIMIT_SWITCH_ID);
    }

    public TalonFXConfiguration getTalonFXConfiguration() {
        TalonFXConfiguration configuration = new TalonFXConfiguration();

        configuration.MotorOutput.NeutralMode = NeutralModeValue.Brake;
        return configuration;
    }

        @Override
    public void updateInputs(BallIntakeInputs inputs) {
            ballIntakeMotorInputs.updateInputs();
            inputs.ballIntakeMotorInputs = ballIntakeMotorInputs;

            inputs.isLimitSwitchPressed = limitSwitch.get();
    }

    @Override
    public boolean isLimitSwitchPressed() {
        return limitSwitch.get();
    }

    @Override
    public void setDutyCycle(double dutyCycle) {
        motor.set(dutyCycle);
    }
}

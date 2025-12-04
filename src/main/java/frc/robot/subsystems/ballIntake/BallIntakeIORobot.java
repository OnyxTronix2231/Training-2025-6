package frc.robot.subsystems.ballIntake;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.lib.OnyxMotorInputs;

import static frc.robot.subsystems.ballIntake.BallIntakeConstants.BALL_INTAKE_LIMIT_SWITCH_CHANNEL;
import static frc.robot.subsystems.ballIntake.BallIntakeConstants.MOTOR_ID;

public class BallIntakeIORobot implements BallIntakeIO {
    private final TalonFX motor;
    private final OnyxMotorInputs motorInputs;
    private final DigitalInput limitSwitch;

    public BallIntakeIORobot() {
        motor = new TalonFX(MOTOR_ID);

        motorInputs = new OnyxMotorInputs(motor, "BallIntake", "BallIntakeMotor");

        motorInputs.updateInputs();

        motor.getConfigurator().apply(getTalonFXConfiguration());

        limitSwitch = new DigitalInput(BALL_INTAKE_LIMIT_SWITCH_CHANNEL);
    }

    private TalonFXConfiguration getTalonFXConfiguration() {
        TalonFXConfiguration config = new TalonFXConfiguration();

        return config;
    }

    @Override
    public void updateInputs(BallIntakeInputs inputs) {
        inputs.isLimitSwitchPressed = limitSwitch.get();

        motorInputs.updateInputs();
        inputs.ballIntakeMotorInputs = motorInputs;
    }

    @Override
    public boolean isLimitSwitchPressed() {
        return !limitSwitch.get();
    }

    @Override
    public void setDutyCycle(double dutyCycle) {
        motor.set(dutyCycle);
    }
}

package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.lib.OnyxMotorInputs;

import static frc.robot.subsystems.BallIntakeConstants.LIMIT_SWITCH_ID;
import static frc.robot.subsystems.BallIntakeConstants.MOTOR_ID;

public class BallIntakeIORobot implements BallIntakeIO {
    private TalonFX motor;
    private DigitalInput limitSwitch;

    private OnyxMotorInputs motorInputs;

    public BallIntakeIORobot() {

        motor = new TalonFX(MOTOR_ID);
        limitSwitch = new DigitalInput(LIMIT_SWITCH_ID);

        motorInputs = new OnyxMotorInputs(motor, "BallIntake", "BallIntakeMotor");

        motorInputs.updateInputs();

        motor.getConfigurator().apply(getMotorConfiguration());
    }

    private TalonFXConfiguration getMotorConfiguration() {
        TalonFXConfiguration config = new TalonFXConfiguration();

        return config;
    }

    @Override
    public boolean isLimitSwitchPressed() {
        return limitSwitch.get();
    }

    @Override
    public void setDutyCycle(double dutyCycle) {
        motor.set(dutyCycle);
    }

    @Override
    public void updateInputs(BallIntakeIOInputs inputs) {
        motorInputs.updateInputs();
        inputs.ballIntakeMotorInputs = motorInputs;

        inputs.isLimitSwitchPressed = isLimitSwitchPressed();
    }

}

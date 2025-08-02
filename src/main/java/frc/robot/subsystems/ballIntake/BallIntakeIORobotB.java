package frc.robot.subsystems.ballIntake;

import com.ctre.phoenix6.BaseStatusSignal;
import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Temperature;
import edu.wpi.first.units.measure.Voltage;

import static frc.robot.subsystems.ballIntake.BallIntakeConstants.*;

public class BallIntakeIORobotB implements BallIntakeIO {
    public final TalonFX ballIntakeMotor;

    private final StatusSignal<Voltage> ballIntakeAppliedVolts;
    private final StatusSignal<Current> ballIntakeSupplyCurrentAmps;
    private final StatusSignal<Current> ballIntakeStatorCurrentAmps;
    private final StatusSignal<AngularVelocity> ballIntakeVelocityRPS;
    private final StatusSignal<Temperature> ballIntakeMotorTemp;


    public BallIntakeIORobotB() {
        ballIntakeMotor = new TalonFX(MOTOR_ID, "DriveTrain");
        ballIntakeMotor.getConfigurator().apply(getTalonFXConfiguration());
        ballIntakeMotor.setNeutralMode(NeutralModeValue.Brake);

        ballIntakeAppliedVolts = ballIntakeMotor.getMotorVoltage();
        ballIntakeSupplyCurrentAmps = ballIntakeMotor.getSupplyCurrent();
        ballIntakeStatorCurrentAmps = ballIntakeMotor.getStatorCurrent();
        ballIntakeVelocityRPS = ballIntakeMotor.getRotorVelocity();
        // TODO maybe do something with the temp inside the listener
        ballIntakeMotorTemp = ballIntakeMotor.getDeviceTemp();
    }

    public TalonFXConfiguration getTalonFXConfiguration() {
        TalonFXConfiguration config = new TalonFXConfiguration();
        config.Slot0.kP = kP;
        config.Slot0.kI = kI;
        config.Slot0.kD = kD;
        config.Slot0.kS = kS;
        config.Slot0.kV = kV;
        config.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
        config.HardwareLimitSwitch.ReverseLimitEnable = false;
        return config;
    }


    @Override
    public void updateInputs(BallIntakeIOInputs inputs) {
        BaseStatusSignal.refreshAll(
                ballIntakeAppliedVolts,
                ballIntakeSupplyCurrentAmps,
                ballIntakeStatorCurrentAmps,
                ballIntakeVelocityRPS,
                ballIntakeMotorTemp);
        inputs.ballIntakeAppliedVolts = ballIntakeAppliedVolts.getValueAsDouble();
        inputs.ballIntakeSupplyCurrentAmps = ballIntakeSupplyCurrentAmps.getValueAsDouble();
        inputs.ballIntakeStatorCurrentAmps = ballIntakeStatorCurrentAmps.getValueAsDouble();
        inputs.ballIntakeVelocityRPS = ballIntakeVelocityRPS.getValueAsDouble();
        inputs.ballIntakeMotorTemp = ballIntakeMotorTemp.getValueAsDouble();
    }

    @Override
    public void setDutyCycle(double dutyCycle) {
        ballIntakeMotor.set(dutyCycle);
    }

    @Override
    public boolean reachedCurrentLimit(BallIntakeIOInputs inputs) {
        return inputs.ballIntakeSupplyCurrentAmps > BALL_INSIDE_SYSTEM_THRESHOLD;
    }
}

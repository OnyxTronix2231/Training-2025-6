package frc.robot.subsystems.coralHolder;

import com.ctre.phoenix6.BaseStatusSignal;
import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.math.filter.Debouncer;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Temperature;
import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import org.littletonrobotics.junction.Logger;

import java.sql.SQLOutput;

import static frc.robot.subsystems.coralHolder.CoralHolderConstants.CoralHolderRobotBConstants.CORAL_INTAKE_ROLLERS_ID;


public class CoralHolderIORobotB implements CoralHolderIO {

    public final TalonFX coralHolderMotor;

    private final DigitalInput frontBeamBrake;
    private final DigitalInput backBeamBrake;

    private final Debouncer frontDebouncer;
    private final Debouncer backDebouncer;

    private final StatusSignal<Voltage> coralHolderAppliedVolts;
    private final StatusSignal<Current> coralHolderSupplyCurrentAmps;
    private final StatusSignal<Current> coralHolderStatorCurrentAmps;
    private final StatusSignal<AngularVelocity> coralHolderVelocityRPS;
    private final StatusSignal<Temperature> coralHolderMotorTemp;


    public CoralHolderIORobotB() {
        coralHolderMotor = new TalonFX(CORAL_INTAKE_ROLLERS_ID, "DriveTrain");
        coralHolderMotor.getConfigurator().apply(new TalonFXConfiguration()
                .withMotorOutput(new MotorOutputConfigs().withInverted(InvertedValue.Clockwise_Positive)));
        coralHolderMotor.setNeutralMode(NeutralModeValue.Brake);

        frontBeamBrake = new DigitalInput(8);
        backBeamBrake = new DigitalInput(9);

        frontDebouncer = new Debouncer(0.35, Debouncer.DebounceType.kBoth);
        backDebouncer = new Debouncer(0.3);

        coralHolderAppliedVolts = coralHolderMotor.getMotorVoltage();
        coralHolderSupplyCurrentAmps = coralHolderMotor.getSupplyCurrent();
        coralHolderStatorCurrentAmps = coralHolderMotor.getStatorCurrent();
        coralHolderVelocityRPS = coralHolderMotor.getRotorVelocity();
        // TODO maybe do something with the temp inside the listener
        coralHolderMotorTemp = coralHolderMotor.getDeviceTemp();
    }

    @Override
    public void updateInputs(CoralHolderIOInputs inputs) {
        BaseStatusSignal.refreshAll(
                coralHolderAppliedVolts,
                coralHolderSupplyCurrentAmps,
                coralHolderStatorCurrentAmps,
                coralHolderVelocityRPS,
                coralHolderMotorTemp);
        inputs.coralHolderAppliedVolts = coralHolderAppliedVolts.getValueAsDouble();
        inputs.coralHolderSupplyCurrentAmps = coralHolderSupplyCurrentAmps.getValueAsDouble();
        inputs.coralHolderStatorCurrentAmps = coralHolderStatorCurrentAmps.getValueAsDouble();
        inputs.coralHolderVelocityRPS = coralHolderVelocityRPS.getValueAsDouble();
        inputs.coralHolderMotorTemp = coralHolderMotorTemp.getValueAsDouble();
        inputs.isBackSensorTripped = backDebouncer.calculate(backBeamBrake.get());
        inputs.isFrontSensorTripped = frontDebouncer.calculate(frontBeamBrake.get());

    }

    @Override
    public void setDutyCycle(double dutyCycle) {
        coralHolderMotor.set(dutyCycle);
    }
}
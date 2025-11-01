package Lrobot.elevator;

import com.ctre.phoenix6.BaseStatusSignal;
import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.GravityTypeValue;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.StaticFeedforwardSignValue;
import com.ctre.phoenix6.sim.TalonFXSimState;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.units.measure.*;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;

import static Lrobot.elevator.ElevatorConstants.*;

public class ElevatorIOSimulation implements ElevatorIO {

    private final TalonFX elevatorMotor;
    private final DCMotorSim simulatedMotor;

    private final MotionMagicVoltage motionMagicVoltage = new MotionMagicVoltage(0.0).withSlot(0);


    class SimulatedSensors {
        public static boolean isLimitSwitchPressed;
    }

    private final StatusSignal<Angle> elevatorMotorPosition;
    private final StatusSignal<Voltage> elevatorAppliedVolts;
    private final StatusSignal<Current> elevatorSupplyCurrentAmps;
    private final StatusSignal<Current> elevatorStatorCurrentAmps;
    private final StatusSignal<AngularVelocity> elevatorAngularVelocityRadPerSec;
    private final StatusSignal<AngularAcceleration> elevatorAngularAccelerationRadPerSecSquared;
    private final StatusSignal<Temperature> elevatorMasterMotorTemp;


    public ElevatorIOSimulation() {
        elevatorMotor = new TalonFX(ELEVATOR_MASTER_MOTOR_ID);
        simulatedMotor = new DCMotorSim(LinearSystemId.createDCMotorSystem(DCMotor.getKrakenX60(2),
                SingleJointedArmSim.estimateMOI(0.001, 0.001), RATIO),
                DCMotor.getKrakenX60(2));

        elevatorMotor.getConfigurator().apply(getTalonFXConfiguration());

        SimulatedSensors.isLimitSwitchPressed = false;
        elevatorMotorPosition = elevatorMotor.getPosition();
        elevatorAppliedVolts = elevatorMotor.getMotorVoltage();
        elevatorSupplyCurrentAmps = elevatorMotor.getSupplyCurrent();
        elevatorStatorCurrentAmps = elevatorMotor.getStatorCurrent();
        elevatorAngularVelocityRadPerSec = elevatorMotor.getVelocity();
        elevatorAngularAccelerationRadPerSecSquared = elevatorMotor.getAcceleration();
        elevatorMasterMotorTemp = elevatorMotor.getDeviceTemp();

    }

    public TalonFXConfiguration getTalonFXConfiguration() {
        TalonFXConfiguration configuration = new TalonFXConfiguration();

        configuration.Slot0.kP = SIMULATION_ELEVATOR_KP;
        configuration.Slot0.kI = SIMULATION_ELEVATOR_KI;
        configuration.Slot0.kD = SIMULATION_ELEVATOR_KD;
        configuration.Slot0.kG = SIMULATION_ELEVATOR_KG;
        configuration.Slot0.kS = SIMULATION_ELEVATOR_KS;
        configuration.Slot0.GravityType = GravityTypeValue.Elevator_Static;
        configuration.Slot0.StaticFeedforwardSign = StaticFeedforwardSignValue.UseClosedLoopSign;

        configuration.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;

        configuration.MotionMagic.MotionMagicCruiseVelocity = SIMULATION_ELEVATOR_CRUISE_VELOCITY;
        configuration.MotionMagic.MotionMagicAcceleration = SIMULATION_ELEVATOR_ACCELERATION;
        configuration.MotionMagic.MotionMagicJerk = SIMULATION_ELEVATOR_JERK;

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
        updateMotor();
        BaseStatusSignal.refreshAll(
                elevatorMotorPosition,
                elevatorAppliedVolts,
                elevatorSupplyCurrentAmps,
                elevatorStatorCurrentAmps,
                elevatorAngularVelocityRadPerSec,
                elevatorAngularAccelerationRadPerSecSquared,
                elevatorMasterMotorTemp
        );
        inputs.isMicroSwitchPressed = SimulatedSensors.isLimitSwitchPressed;
        inputs.elevatorLength = ROTATIONS_TO_LENGTH(elevatorMotorPosition.getValueAsDouble(), true);
        inputs.elevatorMotorPosition = elevatorMotorPosition.getValueAsDouble();
        inputs.elevatorAppliedVolts = elevatorAppliedVolts.getValueAsDouble();
        inputs.elevatorSupplyCurrentAmps = elevatorSupplyCurrentAmps.getValueAsDouble();
        inputs.elevatorStatorCurrentAmps = elevatorStatorCurrentAmps.getValueAsDouble();
        inputs.elevatorAngularVelocityRadPerSec = elevatorAngularVelocityRadPerSec.getValueAsDouble();
        inputs.elevatorAngularAccelerationRadPerSecSquared = elevatorAngularAccelerationRadPerSecSquared.getValueAsDouble();
        inputs.elevatorMasterMotorTemp = elevatorMasterMotorTemp.getValueAsDouble();
    }

    public static void setLimitSwitchValue(boolean value) {
        SimulatedSensors.isLimitSwitchPressed = value;
    }

    public static boolean getLimitSwitchValue() {
        return SimulatedSensors.isLimitSwitchPressed;
    }

    @Override
    public void moveToLength(double length) {
        elevatorMotor.setControl(motionMagicVoltage.withPosition(LENGTH_TO_ROTATIONS(length, true)));
    }

    @Override
    public void resetElevatorPosition(double length) {
        elevatorMotor.setPosition(LENGTH_TO_ROTATIONS(length, true));
    }

    @Override
    public void setDutyCycle(double dutyCycle) {
        elevatorMotor.set(dutyCycle);
    }

    @Override
    public void updatePID(double p, double i, double d) {
        elevatorMotor.getConfigurator().apply(new Slot0Configs().withKP(p).withKI(i).withKD(d));
    }

    public void updateMotor() {
        TalonFXSimState motorSimState = elevatorMotor.getSimState();
        elevatorMotor.getSimState().setSupplyVoltage(RobotController.getBatteryVoltage());

        simulatedMotor.setInputVoltage(motorSimState.getMotorVoltage());
        simulatedMotor.update(0.02);

        motorSimState.setRawRotorPosition(simulatedMotor.getAngularPositionRotations());
        motorSimState.setRotorVelocity(
                Units.radiansToRotations(simulatedMotor.getAngularVelocityRadPerSec()));
    }
}

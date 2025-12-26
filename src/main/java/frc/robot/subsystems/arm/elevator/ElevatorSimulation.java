package frc.robot.subsystems.arm.elevator;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.sim.TalonFXSimState;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;
import frc.robot.lib.OnyxMotorInputs;
import frc.robot.lib.PID.PIDValues;

import static Lrobot.elevator.ElevatorConstants.SIMULATION_DT_SECONDS;
import static frc.robot.subsystems.arm.elevator.ElevatorConstants.*;

public class ElevatorSimulation implements ElevatorIO {

    private final TalonFX motor;
    private final DCMotorSim simulatedMotor;

    private final OnyxMotorInputs elevatorMasterMotorInputs;
    private final OnyxMotorInputs elevatorFollowerMotorInputs;

    private final MotionMagicVoltage motionMagicVoltage = new MotionMagicVoltage(0.0).withSlot(ELEVATOR_MOTION_MAGIC_DEFAULT_SLOT);

    class SimulatedSensors {
        public static boolean isLimitSwitchPressed;
    }

    public ElevatorSimulation() {
        motor = new TalonFX(ELEVATOR_MASTER_MOTOR_ID);

        simulatedMotor = new DCMotorSim(LinearSystemId.createDCMotorSystem(DCMotor.getKrakenX60(SIMULATION_ELEVATOR_NUM_OF_MOTORS),
                SingleJointedArmSim.estimateMOI(SIMULATION_ELEVATOR_LENGTH_METERS, SIMULATION_ELEVATOR_MASS_KG), RATIO),
                DCMotor.getKrakenX60(SIMULATION_ELEVATOR_NUM_OF_MOTORS));

        elevatorMasterMotorInputs = new OnyxMotorInputs(motor, ELEVATOR_SUBSYSTEM_NAME, SIMULATION_ELEVATOR_MOTOR_NAME, ROTATIONS_TO_LENGTH_SIMULATION);
        elevatorFollowerMotorInputs = new OnyxMotorInputs();

        motor.getConfigurator().apply(getTalonFXConfiguration());

        SimulatedSensors.isLimitSwitchPressed = false;
    }

    public TalonFXConfiguration getTalonFXConfiguration() {
        TalonFXConfiguration configuration = new TalonFXConfiguration();

        configuration.Slot0 = SIMULATION_ELEVATOR_PID_VALUES.pidValuesToSlot0Configs();

        configuration.MotionMagic.MotionMagicCruiseVelocity = SIMULATION_ELEVATOR_CRUISE_VELOCITY;
        configuration.MotionMagic.MotionMagicAcceleration = SIMULATION_ELEVATOR_ACCELERATION;
        configuration.MotionMagic.MotionMagicJerk = SIMULATION_ELEVATOR_JERK;

        configuration.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;

        configuration.SoftwareLimitSwitch.ForwardSoftLimitEnable = true;
        configuration.SoftwareLimitSwitch.ForwardSoftLimitThreshold = LENGTH_TO_ROTATIONS(ELEVATOR_FORWARD_SOFT_LIMIT_THRESHOLD, true);

        configuration.SoftwareLimitSwitch.ReverseSoftLimitEnable = true;
        configuration.SoftwareLimitSwitch.ReverseSoftLimitThreshold = LENGTH_TO_ROTATIONS(ELEVATOR_REVERSE_SOFT_LIMIT_THRESHOLD, true);

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
        updateMotor();

        elevatorMasterMotorInputs.updateInputs();
        inputs.elevatorMasterInputs = elevatorMasterMotorInputs;

        inputs.elevatorFollowerInputs = elevatorFollowerMotorInputs;

        inputs.isMicroSwitchPressed = SimulatedSensors.isLimitSwitchPressed;
    }

    public static void setLimitSwitchValue(boolean value) {
        SimulatedSensors.isLimitSwitchPressed = value;
    }

    public static boolean getLimitSwitchValue() {
        return SimulatedSensors.isLimitSwitchPressed;
    }

    @Override
    public void setDutyCycle(double dutyCycle) {
        motor.set(dutyCycle);
    }

    @Override
    public void moveToLength(double length) {
        motor.setControl(motionMagicVoltage.withPosition(LENGTH_TO_ROTATIONS(length, true)));
    }

    @Override
    public void resetElevatorPosition(double length) {
        motor.setPosition(LENGTH_TO_ROTATIONS(length, true));
    }

    @Override
    public void updatePID(PIDValues pidValues) {
        pidValues.updatePIDValues(motor);
    }

    public void updateMotor() {
        TalonFXSimState motorSimState = motor.getSimState();
        motor.getSimState().setSupplyVoltage(RobotController.getBatteryVoltage());

        simulatedMotor.setInputVoltage(motorSimState.getMotorVoltage());
        simulatedMotor.update(SIMULATION_DT_SECONDS);

        motorSimState.setRawRotorPosition(simulatedMotor.getAngularPositionRotations());
        motorSimState.setRotorVelocity(Units.radiansToRotations(simulatedMotor.getAngularVelocityRadPerSec()));
    }
}

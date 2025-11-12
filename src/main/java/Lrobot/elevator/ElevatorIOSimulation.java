package Lrobot.elevator;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.sim.TalonFXSimState;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;
import frc.robot.lib.OnyxMotorInputs;

import static Lrobot.elevator.ElevatorConstants.*;

public class ElevatorIOSimulation implements ElevatorIO {

    private final TalonFX motor;
    private final DCMotorSim simulatedMotor;

    private final OnyxMotorInputs elevatorMasterMotorInputs;
    private final OnyxMotorInputs elevatorFollowerMotorInputs;

    class SimulatedSensors {
        public static boolean isLimitSwitchPressed;
    }

    public ElevatorIOSimulation() {
        motor = new TalonFX(ELEVATOR_MASTER_MOTOR_ID);
        simulatedMotor = new DCMotorSim(LinearSystemId.createDCMotorSystem(DCMotor.getKrakenX60(SIMULATION_ELEVATOR_NUM_OF_MOTORS),
                SingleJointedArmSim.estimateMOI(SIMULATION_ELEVATOR_LENGTH_METERS, SIMULATION_ELEVATOR_MASS_KG), RATIO),
                DCMotor.getKrakenX60(SIMULATION_ELEVATOR_NUM_OF_MOTORS));

        elevatorMasterMotorInputs = new OnyxMotorInputs(motor, "Arm", "elevatorMotor", ROTATIONS_TO_LENGTH_SIMULATION);

        elevatorFollowerMotorInputs = new OnyxMotorInputs();

        motor.getConfigurator().apply(getTalonFXConfiguration());

        motor.setNeutralMode(NeutralModeValue.Brake);

        SimulatedSensors.isLimitSwitchPressed = false;

    }

    public TalonFXConfiguration getTalonFXConfiguration() {
        TalonFXConfiguration configuration = new TalonFXConfiguration();

        configuration.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;

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

    public void updateMotor() {
        TalonFXSimState motorSimState = motor.getSimState();
        motor.getSimState().setSupplyVoltage(RobotController.getBatteryVoltage());

        simulatedMotor.setInputVoltage(motorSimState.getMotorVoltage());
        simulatedMotor.update(SIMULATION_DT_SECONDS);

        motorSimState.setRawRotorPosition(simulatedMotor.getAngularPositionRotations());
        motorSimState.setRotorVelocity(Units.radiansToRotations(simulatedMotor.getAngularVelocityRadPerSec()));
    }
}

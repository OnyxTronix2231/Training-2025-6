package frc.robot.subsystems.coralHolder;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
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
import org.littletonrobotics.junction.Logger;

import static frc.robot.subsystems.coralHolder.CoralHolderConstants.*;

public class CoralHolderIOSimulation implements CoralHolderIO {
    private final TalonFX motor;
    private final DCMotorSim simulatedMotor;

    private final OnyxMotorInputs coralHolderMotorInputs;

    class SimulatedSensors {
        public static boolean outerSensorDetecting;
        public static boolean innerSensorDetecting;
    }

    public CoralHolderIOSimulation() {
        motor = new TalonFX(CORAL_HOLDER_MOTOR_ID);

        simulatedMotor = new DCMotorSim(LinearSystemId.createDCMotorSystem(DCMotor.getKrakenX60(CORAL_HOLDER_NUM_OF_MOTORS),
                SingleJointedArmSim.estimateMOI(CORAL_HOLDER_SIMULATION_LENGTH_METERS, CORAL_HOLDER_SIMULATION_WEIGHT_KG), 1),
                DCMotor.getKrakenX60(CORAL_HOLDER_NUM_OF_MOTORS));

        coralHolderMotorInputs = new OnyxMotorInputs(motor, "CoralHolder", "coralHolderMotor");

        motor.getConfigurator().apply(getTalonFXConfiguration());
    }

    public TalonFXConfiguration getTalonFXConfiguration() {
        TalonFXConfiguration configuration = new TalonFXConfiguration();

        configuration.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;

        configuration.MotorOutput.NeutralMode = NeutralModeValue.Brake;

        return configuration;
    }

    public boolean isOuterSensorDetecting() {
        return SimulatedSensors.outerSensorDetecting;
    }

    public boolean isInnerSensorDetecting() {
        return SimulatedSensors.outerSensorDetecting;
    }


    @Override
    public void updateInputs(CoralHolderInputs inputs) {
        updateMotor();

        coralHolderMotorInputs.updateInputs();

        inputs.coralHolderInputs = coralHolderMotorInputs;

        inputs.isOuterSensorDetecting = SimulatedSensors.outerSensorDetecting;
        inputs.isInnerSensorDetecting = SimulatedSensors.innerSensorDetecting;
    }

    private void updateMotor() {
        TalonFXSimState motorSimState = motor.getSimState();
        motor.getSimState().setSupplyVoltage(RobotController.getBatteryVoltage());

        Logger.recordOutput("error", motor.getClosedLoopError().asSupplier().get());

        simulatedMotor.setInputVoltage(motorSimState.getMotorVoltage());
        simulatedMotor.update(SIMULATION_DT_SECONDS);

        motorSimState.setRawRotorPosition(simulatedMotor.getAngularPositionRotations());
        motorSimState.setRotorVelocity(Units.radiansToRotations(simulatedMotor.getAngularVelocityRadPerSec()));
    }

    @Override
    public void setDutyCycle(double dutyCycle) {
        motor.set(dutyCycle);
    }
}

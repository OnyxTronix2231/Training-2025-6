package frc.robot.subsystems.wrist;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.PositionVoltage;
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

import static frc.robot.subsystems.wrist.WristConstants.*;

public class WristIOSimulation implements WristIO {
    private final TalonFX motor;
    private final DCMotorSim simulatedMotor;

    private final OnyxMotorInputs wristMotorInputs;

    private final PositionVoltage angleController;

    public WristIOSimulation() {
        motor = new TalonFX(WRIST_MOTOR_ID);
        simulatedMotor = new DCMotorSim(LinearSystemId.createDCMotorSystem(DCMotor.getKrakenX60(SIMULATION_WRIST_NUM_OF_MOTORS),
                SingleJointedArmSim.estimateMOI(SIMULATION_WRIST_LENGTH_METERS, SIMULATION_WRIST_MASS_KG), 1.0),
                DCMotor.getKrakenX60(SIMULATION_WRIST_NUM_OF_MOTORS));
        wristMotorInputs = new OnyxMotorInputs(motor, "Wrist", "wristMotor", ROTATIONS_TO_ANGLE);

        motor.getConfigurator().apply(getTalonFXConfiguration());

        angleController = new PositionVoltage(0);
    }

    public TalonFXConfiguration getTalonFXConfiguration() {
        TalonFXConfiguration configuration = new TalonFXConfiguration();

        configuration.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;

        configuration.Slot0 = SIMULATION_WRIST_PID_VALUES.pidValuesToSlot0Configs();

        configuration.MotorOutput.NeutralMode = NeutralModeValue.Brake;

        return configuration;
    }

    @Override
    public void updateInputs(WristInputs inputs) {
        updateMotor();
        wristMotorInputs.updateInputs();
        inputs.wristMotorInputs = wristMotorInputs;

        inputs.encoderPosition = 0;
    }

    public void updateMotor() {
        TalonFXSimState motorSimState = motor.getSimState();
        motor.getSimState().setSupplyVoltage(RobotController.getBatteryVoltage());

        simulatedMotor.setInputVoltage(motorSimState.getMotorVoltage());
        simulatedMotor.update(SIMULATION_DT_SECONDS);

        motorSimState.setRawRotorPosition(simulatedMotor.getAngularPositionRotations());
        motorSimState.setRotorVelocity(Units.radiansToRotations(simulatedMotor.getAngularVelocityRadPerSec()));
    }

    @Override
    public double getEncoderPosition() {
        return 0;
    }

    @Override
    public void setDutyCycle(double dutyCycle) {
        motor.set(dutyCycle);
    }

    @Override
    public void moveToAngle(double angle) {
        motor.setControl(angleController.withPosition(ANGLE_TO_ROTATIONS(angle)));
    }
}

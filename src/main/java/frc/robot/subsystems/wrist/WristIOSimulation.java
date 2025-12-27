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

import static Lrobot.elevator.ElevatorConstants.SIMULATION_DT_SECONDS;
import static frc.robot.subsystems.wrist.WristConstants.*;

public class WristIOSimulation implements WristIO {

    private final TalonFX motor;
    private final DCMotorSim simulatedMotor;

    private final OnyxMotorInputs wristMotorInputs;

    private final PositionVoltage positionController;

    public WristIOSimulation() {
        motor = new TalonFX(WRIST_MOTOR_ID);
        simulatedMotor = new DCMotorSim(LinearSystemId.createDCMotorSystem(DCMotor.getKrakenX60(SIMULATION_WRIST_NUM_OF_MOTORS),
                SingleJointedArmSim.estimateMOI(SIMULATION_WRIST_LENGTH_METERS, SIMULATION_WRIST_MASS_KG), RATIO),
                DCMotor.getKrakenX60(SIMULATION_WRIST_NUM_OF_MOTORS));



        wristMotorInputs = new OnyxMotorInputs(motor, "Arm", "WristMotor", ROTATIONS_TO_LENGTH_SIMULATION);

        motor.getConfigurator().apply(getTalonFXConfiguration());

        motor.setNeutralMode(NeutralModeValue.Brake);

        positionController = new PositionVoltage(0);
    }

    public TalonFXConfiguration getTalonFXConfiguration() {
        TalonFXConfiguration configuration = new TalonFXConfiguration();
        configuration.Slot0 = SIMULATION_WRIST_PID_VALUES.pidValuesToSlot0Configs();

        configuration.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;

        return configuration;
    }

    public void updateInputs(WristInputs inputs) {
        updateMotor();
        wristMotorInputs.updateInputs();
        inputs.wristMotor = wristMotorInputs;
    }




    public void updateMotor() {
        TalonFXSimState motorSimState = motor.getSimState();
        motor.getSimState().setSupplyVoltage(RobotController.getBatteryVoltage());

        simulatedMotor.setInputVoltage(motorSimState.getMotorVoltage());
        simulatedMotor.update(SIMULATION_DT_SECONDS);

        motorSimState.setRawRotorPosition(simulatedMotor.getAngularPositionRotations());
        motorSimState.setRotorVelocity(Units.radiansToRotations(simulatedMotor.getAngularVelocityRadPerSec()));
    }

    public void setDutyCycle(double dutyCycle) {
        motor.set(dutyCycle);
    }


    public void moveToAngle(double angle) {
        motor.setControl(positionController.withPosition(angle/360));
    }

}

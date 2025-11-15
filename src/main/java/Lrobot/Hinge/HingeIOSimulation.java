package Lrobot.Hinge;

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

import static Lrobot.Hinge.HingeConstants.*;

public class HingeIOSimulation implements HingeIO {

    private final TalonFX motor;
    private final DCMotorSim simulatedMotor;
    private final OnyxMotorInputs hingeMotorInputs;



    public HingeIOSimulation() {
        motor = new TalonFX(HINGE_ID);
        simulatedMotor = new DCMotorSim(LinearSystemId.createDCMotorSystem(DCMotor.getKrakenX60(SIMULATION_HINGE_NUM_OF_MOTORS),
                SingleJointedArmSim.estimateMOI(SIMULATION_HINGE_LENGTH, SIMULATION_HINGE_MASS_KG), HINGE_RATIO),
                DCMotor.getKrakenX60(SIMULATION_HINGE_NUM_OF_MOTORS));

        hingeMotorInputs = new OnyxMotorInputs(motor, "Hinge", "hingeMotor");
        motor.getConfigurator().apply(getTalonFXConfiguration());
        motor.setNeutralMode(NeutralModeValue.Brake);
    }

    public TalonFXConfiguration getTalonFXConfiguration() {
        TalonFXConfiguration configuration = new TalonFXConfiguration();
        configuration.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
        return configuration;
    }


    @Override
    public void updateInputs(HingeInputs inputs) {
        updateMotor();
        hingeMotorInputs.updateInputs();
        inputs.hingeMotorInputs = hingeMotorInputs;
    }

    @Override
    public void setDutyCycle(double dutyCycle) {
       motor.set(dutyCycle);
    }
    public void updateMotor() {
        TalonFXSimState motorSimState = motor.getSimState();
        motor.getSimState().setSupplyVoltage(RobotController.getBatteryVoltage());

        simulatedMotor.setInputVoltage(motorSimState.getMotorVoltage());
        simulatedMotor.update(HINGE_SIMULATION_DT_SECONDS);

        motorSimState.setRawRotorPosition(simulatedMotor.getAngularPositionRotations());
        motorSimState.setRotorVelocity(Units.radiansToRotations(simulatedMotor.getAngularVelocityRadPerSec()));
    }
}

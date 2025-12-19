package frc.robot.subsystems.arm.wrist;

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

import static frc.robot.subsystems.arm.wrist.WristConstants.*;

public class WristIOSimulation implements WristIO {
    private final TalonFX motor;
    private final DCMotorSim simulatedMotor;

    private final OnyxMotorInputs wristMotorInputs;

    private final MotionMagicVoltage motionMagicVoltage = new MotionMagicVoltage(0).withSlot(0);

    public WristIOSimulation() {
        motor = new TalonFX(WRIST_MOTOR_ID);
        simulatedMotor = new DCMotorSim(LinearSystemId.createDCMotorSystem(DCMotor.getKrakenX60(SIMULATION_WRIST_NUM_OF_MOTORS),
            SingleJointedArmSim.estimateMOI(SIMULATION_WRIST_LENGTH_METERS, SIMULATION_WRIST_MASS_KG), 1),
            DCMotor.getKrakenX60(SIMULATION_WRIST_NUM_OF_MOTORS));

        wristMotorInputs = new OnyxMotorInputs(motor, "Arm", "wristMotor", ROTATIONS_TO_ANGLE);

        motor.getConfigurator().apply(getTalonFXConfiguration());

        motor.setPosition(ANGLE_TO_ROTATIONS(WRIST_ZERO_OFFSET_DEG));


    }

    public TalonFXConfiguration getTalonFXConfiguration() {
        TalonFXConfiguration configuration = new TalonFXConfiguration();

        configuration.Slot0 = SIMULATION_WRIST_PID_VALUES.pidValuesToSlot0Configs();

        configuration.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;

        // TODO MOTION MAGIC

        return configuration;
    }

    @Override
    public void updateInputs(WristInputs inputs) {
        updateMotor();

        wristMotorInputs.updateInputs();

        inputs.wristInputs = wristMotorInputs;
    }

    @Override
    public void setDutyCycle(double dutyCycle) {
        motor.set(dutyCycle);
    }

    @Override
    public void updatePID(PIDValues pidValues) {
        motor.getConfigurator().apply(pidValues.pidValuesToSlot0Configs());
    }

    @Override
    public void moveToAngle(double angle) {
        motor.setControl(motionMagicVoltage.withPosition(ANGLE_TO_ROTATIONS(angle)));
    }


    private void updateMotor() {
        TalonFXSimState motorSimState = motor.getSimState();
        motor.getSimState().setSupplyVoltage(RobotController.getBatteryVoltage());

        simulatedMotor.setInputVoltage(motorSimState.getMotorVoltage());
        simulatedMotor.update(SIMULATION_DT_SECONDS);

        motorSimState.setRawRotorPosition(simulatedMotor.getAngularPositionRotations());
        motorSimState.setRotorVelocity(Units.radiansToRotations(simulatedMotor.getAngularVelocityRadPerSec()));
    }
}

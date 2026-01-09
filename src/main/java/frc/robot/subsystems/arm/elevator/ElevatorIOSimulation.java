package frc.robot.subsystems.arm.elevator;

import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.sim.TalonFXSimState;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;
import frc.robot.lib.OnyxMotorInputs;

import static frc.robot.subsystems.arm.elevator.ElevatorConstants.*;
import static frc.robot.subsystems.arm.elevator.ElevatorConstants.SIMULATED_CONSTANTS.*;

public class ElevatorIOSimulation implements ElevatorIO {
    private final TalonFX motor;
    private final DCMotorSim simulatedMotor;

    private final OnyxMotorInputs masterMotorInputs;
    private final OnyxMotorInputs followerMotorInputs;

    class simulatedSensors {
        public static boolean isLimitSwitchPressed;

        public static void setLimitSwitchPressed(boolean pressed) {
            isLimitSwitchPressed = pressed;
        }
    }

    private final MotionMagicVoltage motionMagicVoltage = new MotionMagicVoltage(0).withSlot(ELEVATOR_MOTION_MAGIC_DEFAULT_SLOT);

    public ElevatorIOSimulation() {
        motor = new TalonFX(MASTER_MOTOR_ID);
        simulatedMotor = new DCMotorSim(LinearSystemId.createDCMotorSystem(DCMotor.getKrakenX60(2),
                                        SingleJointedArmSim.estimateMOI(0.001, 0.001), RATIO),
                DCMotor.getKrakenX60(2));

        masterMotorInputs = new OnyxMotorInputs(motor, "Arm/Elevator", "Master Motor");
        followerMotorInputs = new OnyxMotorInputs(motor, "Arm/Elevator", "Follower Motor");

        motor.getConfigurator().apply(getMotorConfiguration());

        simulatedSensors.setLimitSwitchPressed(true);

    }

    private TalonFXConfiguration getMotorConfiguration() {
        TalonFXConfiguration config = new TalonFXConfiguration();

        config.Slot0.withKP(SIMULATED_KP);
        config.Slot0.withKI(SIMULATED_KI);
        config.Slot0.withKD(SIMULATED_KD);
        config.Slot0.withKG(SIMULATED_KG);

        config.MotorOutput.NeutralMode = NeutralModeValue.Brake;

        return config;
    }

    @Override
    public void updateInputs(ElevatorIOInputs inputs) {
        masterMotorInputs.updateInputs();
        inputs.masterMotorInputs = masterMotorInputs;

        followerMotorInputs.updateInputs();
        inputs.followerMotorInputs = followerMotorInputs;

        inputs.isLimitSwitchPressed = simulatedSensors.isLimitSwitchPressed;

        inputs.elevatorHeight = getHeight();

        updateSimulatedMotor();
    }

    @Override
    public void setDutyCycle(double dutyCycle) {
        motor.set(dutyCycle);
    }

    @Override
    public void stop() {
        motor.stopMotor();
    }

    @Override
    public double getHeight() {
        return ROTATIONS_TO_METERS(motor.getPosition().getValueAsDouble());
    }

    @Override
    public void moveElevatorToHeight(double height) {
        motor.setControl(motionMagicVoltage.withPosition(METERS_TO_ROTATIONS(height)));
    }

    @Override
    public boolean isOnTarget(double target) {
        return Math.abs(target - getHeight()) < HEIGHT_ERROR_TOLERANCE;
    }

    @Override
    public void updatePID(double kP, double kI, double kD) {
        motor.getConfigurator().apply(new Slot0Configs().withKP(kP).withKI(kI).withKD(kD));
    }

    private void updateSimulatedMotor() {
        TalonFXSimState motorSimState = motor.getSimState();
        motorSimState.setSupplyVoltage(RobotController.getBatteryVoltage());

        simulatedMotor.setInputVoltage(motorSimState.getMotorVoltage());
        simulatedMotor.update(0.02);

        motorSimState.setRawRotorPosition(simulatedMotor.getAngularPositionRotations());
        motorSimState.setRotorVelocity(Units.radiansToRotations(simulatedMotor.getAngularVelocityRadPerSec()));

    }

}

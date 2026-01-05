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

import static Lrobot.elevator.ElevatorConstants.SIMULATION_DT_SECONDS;
import static frc.robot.subsystems.arm.wrist.WristConstants.*;

public class WristIOSimulation implements WristIO {

    private final TalonFX motor;
    private final DCMotorSim simulatedMotor;

    private final OnyxMotorInputs wristMotorInputs;

    private final MotionMagicVoltage motionMagicVoltage = new MotionMagicVoltage(0.0).withSlot(WRIST_MOTION_MAGIC_DEFAULT_SLOT);

    public WristIOSimulation() {
        motor = new TalonFX(WRIST_MOTOR_ID);

        simulatedMotor = new DCMotorSim(LinearSystemId.createDCMotorSystem(DCMotor.getKrakenX60(SIMULATION_WRIST_NUM_OF_MOTORS),
                SingleJointedArmSim.estimateMOI(SIMULATION_WRIST_LENGTH_METERS, SIMULATION_WRIST_MASS_KG), RATIO),
                DCMotor.getKrakenX60(SIMULATION_WRIST_NUM_OF_MOTORS));

        wristMotorInputs = new OnyxMotorInputs(motor, WRIST_SUBSYSTEM_NAME, WRIST_MOTOR_MASTER_NAME, ROTATIONS_TO_ANGLE);

        motor.getConfigurator().apply(getTalonFXConfiguration());

        motor.setPosition(Units.degreesToRotations(WRIST_ZERO_OFFSET_DEG));
    }

    public TalonFXConfiguration getTalonFXConfiguration() {
        TalonFXConfiguration configuration = new TalonFXConfiguration();

        configuration.Slot0 = SIMULATION_WRIST_PID_VALUES.pidValuesToSlot0Configs();

        configuration.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
        configuration.MotionMagic.MotionMagicCruiseVelocity = SIMULATION_WRIST_CRUISE_VELOCITY;
        configuration.MotionMagic.MotionMagicAcceleration = SIMULATION_WRIST_ACCELERATION;
        configuration.MotionMagic.MotionMagicJerk = SIMULATION_WRIST_JERK;

        configuration.CurrentLimits.StatorCurrentLimitEnable = true;
        configuration.CurrentLimits.StatorCurrentLimit = WRIST_STATOR_LIMIT;

        configuration.CurrentLimits.SupplyCurrentLimitEnable = true;
        configuration.CurrentLimits.SupplyCurrentLimit = WRIST_SUPPLY_LIMIT;

        configuration.CurrentLimits.SupplyCurrentLowerLimit = WRIST_SUPPLY_LOWER_LIMIT;
        configuration.CurrentLimits.SupplyCurrentLowerTime = WRIST_TIME_LOWER_LIMIT;
        return configuration;
    }

    @Override
    public void updateInputs(WristInputs inputs) {
        updateMotor();
        wristMotorInputs.updateInputs();
        inputs.wristMotorInputs = wristMotorInputs;

        inputs.encoderPosition = 0;
    }

    @Override
    public void setDutyCycle(double dutyCycle) {
        motor.set(dutyCycle);
    }

    @Override
    public void moveToAngle(double angle) {
        motor.setControl(motionMagicVoltage.withPosition(Units.degreesToRotations(angle)));
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

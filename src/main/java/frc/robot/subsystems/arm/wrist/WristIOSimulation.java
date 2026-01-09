package frc.robot.subsystems.arm.wrist;

import com.ctre.phoenix6.configs.CANcoderConfiguration;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.FeedbackSensorSourceValue;
import com.ctre.phoenix6.signals.GravityTypeValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.signals.SensorDirectionValue;
import com.ctre.phoenix6.sim.TalonFXSimState;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;
import frc.robot.lib.OnyxMotorInputs;

import static edu.wpi.first.units.Units.Degrees;
import static frc.robot.subsystems.arm.wrist.WristConstants.*;
import static frc.robot.subsystems.arm.wrist.WristConstants.SIMULATED_CONSTANTS.*;

public class WristIOSimulation implements WristIO {
    private final TalonFX motor;
    private final DCMotorSim simulatedMotor;

    private final CANcoder canCoder = new CANcoder(CANCODER_ID);

    private final OnyxMotorInputs motorInputs;

    private final MotionMagicVoltage motionMagicVoltage = new MotionMagicVoltage(0).withSlot(WRIST_MOTION_MAGIC_DEFAULT_SLOT);

    public WristIOSimulation() {
        motor = new TalonFX(MOTOR_ID);


        motor.getConfigurator().apply(getMotorConfiguration());
        canCoder.getConfigurator().apply(getCANCoderConfiguration());

        simulatedMotor = new DCMotorSim(LinearSystemId.createDCMotorSystem(DCMotor.getKrakenX60(1), SingleJointedArmSim.estimateMOI(0.001, 0.001), RATIO), DCMotor.getKrakenX60(1));

        motorInputs = new OnyxMotorInputs(motor, "Arm/Wrist", "Motor");
    }

    private TalonFXConfiguration getMotorConfiguration() {
        TalonFXConfiguration config = new TalonFXConfiguration();

        config.Slot0.withKP(SIMULATED_KP);
        config.Slot0.withKI(SIMULATED_KI);
        config.Slot0.withKD(SIMULATED_KD);
        config.Slot0.withKG(SIMULATED_KG);
        config.Slot0.withGravityType(GravityTypeValue.Arm_Cosine);

        config.MotorOutput.withNeutralMode(NeutralModeValue.Brake);

        config.Feedback.FeedbackSensorSource = FeedbackSensorSourceValue.FusedCANcoder;
        config.Feedback.FeedbackRemoteSensorID = CANCODER_ID;
        config.Feedback.RotorToSensorRatio = RATIO;

        return config;
    }

    private CANcoderConfiguration getCANCoderConfiguration() {
        CANcoderConfiguration config = new CANcoderConfiguration();

        config.MagnetSensor.MagnetOffset = CANCODER_MAGNET_OFFSET;
        config.MagnetSensor.SensorDirection = SensorDirectionValue.CounterClockwise_Positive;

        return config;
    }


    @Override
    public void updateInputs(WristIOInputs inputs) {
        motorInputs.updateInputs();
        inputs.motorInputs = motorInputs;

        inputs.wristAngle = getWristAngle();

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
    public double getWristAngle() {
        return motor.getPosition().getValue().in(Degrees);
    }

    @Override
    public void moveWristToAngle(double angle) {
        motor.setControl(motionMagicVoltage.withPosition(angle / 360));
    }

    @Override
    public boolean isOnTarget(double target) {
        return false;
    }

    @Override
    public void updatePID(double kP, double kI, double kD, double kG) {
        motor.getConfigurator().apply(new Slot0Configs().withKP(kP).withKI(kI).withKD(kD).withKG(kG));
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

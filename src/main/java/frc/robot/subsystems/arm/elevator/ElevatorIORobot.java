package frc.robot.subsystems.arm.elevator;

import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.lib.OnyxMotorInputs;

import static frc.robot.subsystems.arm.elevator.ElevatorConstants.*;

public class ElevatorIORobot implements ElevatorIO {
    private final TalonFX masterMotor;
    private final TalonFX followerMotor;

    private final DigitalInput limitSwitch;

    private final MotionMagicVoltage motionMagicVoltage = new MotionMagicVoltage(0).withSlot(ELEVATOR_MOTION_MAGIC_DEFAULT_SLOT);

    private final OnyxMotorInputs masterMotorInputs;
    private final OnyxMotorInputs followerMotorInputs;

    public ElevatorIORobot() {
        masterMotor = new TalonFX(MASTER_MOTOR_ID);
        followerMotor = new TalonFX(FOLLOWER_MOTOR_ID);

        masterMotor.getConfigurator().apply(getMotorConfiguration());
        followerMotor.getConfigurator().apply(getMotorConfiguration());

        followerMotor.setControl(new Follower(MASTER_MOTOR_ID, true));

        limitSwitch = new DigitalInput(LIMIT_SWITCH_ID);

        masterMotorInputs = new OnyxMotorInputs(masterMotor, "Arm/Elevator", "Master Motor");
        followerMotorInputs = new OnyxMotorInputs(followerMotor, "Arm/Elevator", "Follower Motor");

    }
    private TalonFXConfiguration getMotorConfiguration() {
        TalonFXConfiguration config = new TalonFXConfiguration();

        config.Slot0.kP = KP;
        config.Slot0.kI = KI;
        config.Slot0.kD = KD;
        config.Slot0.kG = KG;

        config.MotionMagic.MotionMagicAcceleration = MOTION_MAGIC_ACCELERATION;
        config.MotionMagic.MotionMagicCruiseVelocity = MOTION_MAGIC_SPEED;
        config.MotionMagic.MotionMagicJerk = MOTION_MAGIC_JERK;

        config.MotorOutput.NeutralMode = NeutralModeValue.Brake;

        return config;
    }

    @Override
    public void updateInputs(ElevatorIOInputs inputs) {
        masterMotorInputs.updateInputs();
        inputs.masterMotorInputs = masterMotorInputs;

        followerMotorInputs.updateInputs();
        inputs.followerMotorInputs = followerMotorInputs;

        inputs.elevatorHeight = getHeight();

        inputs.isLimitSwitchPressed = limitSwitch.get();
    }

    @Override
    public void setDutyCycle(double dutyCycle) {
        masterMotor.set(dutyCycle);
    }

    @Override
    public void stop() {
        masterMotor.stopMotor();
    }

    @Override
    public double getHeight() {
        return ROTATIONS_TO_METERS(masterMotor.getPosition().getValueAsDouble());
    }

    @Override
    public void moveElevatorToHeight(double height) {
        masterMotor.setControl(motionMagicVoltage.withPosition(METERS_TO_ROTATIONS(height)));
    }

    @Override
    public boolean isOnTarget(double target) {
        return Math.abs(getHeight() - target) < HEIGHT_ERROR_TOLERANCE;
    }

    @Override
    public void updatePID(double kP, double kI, double kD, double kG) {
        masterMotor.getConfigurator().apply(new Slot0Configs().withKP(kP).withKI(kI).withKD(kD).withKG(kG));
    }

}

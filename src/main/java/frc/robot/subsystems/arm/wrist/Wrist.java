package frc.robot.subsystems.arm.wrist;

import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.lib.PID.PIDValues;
import frc.robot.subsystems.arm.wrist.WristIO.WristIOInputs;
import org.littletonrobotics.junction.Logger;

import static frc.robot.subsystems.arm.wrist.WristConstants.*;

public class Wrist extends SubsystemBase {
    private final WristIO wristIO;
    private final WristIOInputs inputs;

    private double currentSlot;

    public enum WantedState {
        IDLE,
        OPEN,
        CLOSE
    }

    public enum SystemState {
        IDLING,
        OPENING,
        CLOSING
    }

    private WantedState wantedState;
    private SystemState systemState;
    private SystemState previousSystemState;

    private double lastVel;
    private double lastAngleTimeStamp;

    public void setWantedState(WantedState wantedState) {
        this.wantedState = wantedState;
    }

    public WantedState getWantedState() {
        return wantedState;
    }

    public Wrist(WristIO wristIO) {
        this.wristIO = wristIO;
        this.inputs = new WristIOInputs();

        wantedState = WantedState.IDLE;
        systemState = SystemState.IDLING;
        previousSystemState = SystemState.IDLING;

        wristIO.updateInputs(inputs);

        currentSlot = WRIST_FAST_SLOT;

        lastVel = getAngle();
        lastAngleTimeStamp = RobotController.getFPGATime();
    }

    @Override
    public void periodic() {
        wristIO.updateInputs(inputs);

        inputs.motorInputs.log();
        log();

        systemState = handleStateTransition();
        applyStates();
        previousSystemState = systemState;

        updateLastAngle();
    }

    public void log() {
        Logger.recordOutput(LOG_PATH + "WantedState", wantedState);
        Logger.recordOutput(LOG_PATH + "SystemState", systemState);
        Logger.recordOutput(LOG_PATH + "previousSystemState", previousSystemState);

        Logger.recordOutput(LOG_PATH + "wristAngle", inputs.wristAngle);

        Logger.recordOutput(LOG_PATH + "currentSlot", currentSlot);
        Logger.recordOutput(LOG_PATH + "isDetectedPush", isDetectedPush());
        Logger.recordOutput(LOG_PATH + "lastAngle", lastVel);
    }

    public SystemState handleStateTransition() {
        return switch (wantedState) {
            case IDLE -> SystemState.IDLING;
            case OPEN -> SystemState.OPENING;
            case CLOSE -> SystemState.CLOSING;
        };
    }

    public void applyStates() {
        switch (systemState) {
            case IDLING -> idling();
            case OPENING -> movingToAngle();
            case CLOSING -> closing();
        }
    }

    public void idling() {
        wristIO.setDutyCycle(0);
    }

    public void movingToAngle() {
        if (wristIO.isOnTarget(OPEN_ANGLE) || isDetectedPush()) {
            currentSlot = WRIST_SLOW_SLOT;
            wristIO.moveWristToAngle(OPEN_ANGLE, WRIST_SLOW_SLOT);
            Logger.recordOutput(LOG_PATH + "pidSlot", WRIST_SLOW_SLOT);
        }
        else {
            currentSlot = WRIST_FAST_SLOT;
            wristIO.moveWristToAngle(OPEN_ANGLE, WRIST_FAST_SLOT);
            Logger.recordOutput(LOG_PATH + "pidSlot", WRIST_FAST_SLOT);
        }
    }

    public void closing() {
        currentSlot = WRIST_FAST_SLOT;
        wristIO.moveWristToAngle(CLOSE_ANGLE, WRIST_FAST_SLOT);
        Logger.recordOutput(LOG_PATH + "pidSlot", WRIST_FAST_SLOT);
    }

    public boolean isDetectedPush() {
        return Math.abs(lastVel - getVelocity()) < TOLERANCE;
    }

    public double getAngle() {
        return inputs.wristAngle;
    }

    public double getVelocity() {
        return inputs.motorInputs.getMotorAngularVelocityRadPerSec();
    }

    public double getAcceleration() {
        return inputs.motorInputs.getMotorAngularAccelerationRadPerSecSquared();
    }

    public void updatePIDSlot0(PIDValues PIDValues) {
        wristIO.updatePIDSlot0(PIDValues);
    }

    public void updatePIDSlot1(PIDValues PIDValues) {
        wristIO.updatePIDSlot1(PIDValues);
    }

    public void updateLastAngle() {
        if (RobotController.getFPGATime() - lastAngleTimeStamp > 50000) {
            lastAngleTimeStamp = RobotController.getFPGATime();
            lastVel = getVelocity();
        }
    }

    public void setBrakeMode() {
        wristIO.setBrakeMode();
    }

    public void setCoastMode() {
        wristIO.setCoastMode();
    }

    public static Wrist instance;

    public static void init(WristIO wristIO) {
        if (instance == null) {
            instance = new Wrist(wristIO);
        }
    }

    public static Wrist getInstance() {
        return instance;
    }
}

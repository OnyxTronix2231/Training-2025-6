package frc.robot.subsystems.arm.wrist;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.arm.wrist.WristIO.WristIOInputs;
import org.littletonrobotics.junction.Logger;

import static frc.robot.subsystems.arm.wrist.WristConstants.*;

public class Wrist extends SubsystemBase {
    private final WristIO wristIO;
    private final WristIOInputs inputs;

    public enum WantedState {
        IDLE,
        MOVE_TO_ANGLE,
        KEEP_OPEN
    }

    public enum SystemState {
        IDLING,
        MOVING_TO_ANGLE,
        KEEPING_OPEN,
    }

    private WantedState wantedState;
    private SystemState systemState;
    private SystemState previousSystemState;

    private double targetAngle;

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
    }

    @Override
    public void periodic() {
        wristIO.updateInputs(inputs);

        inputs.motorInputs.log();
        log();

        systemState = handleStateTransition();
        applyStates();
        previousSystemState = systemState;
    }

    public void log() {
        Logger.recordOutput(LOG_PATH + "WantedState", wantedState);
        Logger.recordOutput(LOG_PATH + "SystemState", systemState);
        Logger.recordOutput(LOG_PATH + "previousSystemState", previousSystemState);

        Logger.recordOutput(LOG_PATH + "wristAngle", inputs.wristAngle);
    }

    public SystemState handleStateTransition() {
        return switch (wantedState) {
            case IDLE -> SystemState.IDLING;
            case MOVE_TO_ANGLE -> SystemState.MOVING_TO_ANGLE;
            case KEEP_OPEN -> SystemState.KEEPING_OPEN;
        };
    }

    public void applyStates() {
        switch (systemState) {
            case IDLING -> idling();
            case MOVING_TO_ANGLE -> movingToAngle();
            case KEEPING_OPEN -> keepingOpen();
        }
    }

    public void idling() {
        wristIO.setDutyCycle(0);
    }

    public void movingToAngle() {
        if (wristIO.isOnTarget(targetAngle)) {
            wristIO.moveWristToAngle(targetAngle, WRIST_SLOW_SLOT);
        }
        else {
            wristIO.moveWristToAngle(targetAngle, WRIST_FAST_SLOT);
        }
    }

    public void keepingOpen() {
        if (wristIO.isDetectedPush()) {
            systemState = SystemState.MOVING_TO_ANGLE;
        }
        wristIO.moveWristToAngle(0, WRIST_SLOW_SLOT);
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

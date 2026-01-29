package frc.robot.subsystems.arm.wrist;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.lib.PID.PIDValues;
import frc.robot.subsystems.arm.wrist.WristIO.WristIOInputs;
import org.littletonrobotics.junction.Logger;

import static frc.robot.subsystems.arm.wrist.WristConstants.*;

public class Wrist extends SubsystemBase {
    private final WristIO wristIO;
    private final WristIOInputs inputs;

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
        if (wristIO.isOnTarget(OPEN_ANGLE)) {
            wristIO.moveWristToAngle(OPEN_ANGLE, WRIST_SLOW_SLOT);
        }
        else {
            wristIO.moveWristToAngle(OPEN_ANGLE, WRIST_FAST_SLOT);
        }
    }

    public void closing() {
        wristIO.moveWristToAngle(CLOSE_ANGLE, WRIST_FAST_SLOT);
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

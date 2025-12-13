package frc.robot.subsystems.wrist;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Wrist extends SubsystemBase {
    private final WristIO.WristInputs wristInputs;
    private final WristIO wristIO;

    private double wantedAngle;

    public enum WantedState {
        IDLE,
        MOVE_TO_ANGLE,
        MOVE_FORWARD,
        MOVE_BACKWARD
    }

    public enum CurrentState {
        IDLING,
        MOVING_TO_ANGLE,
        MOVING_FORWARDS,
        MOVING_BACKWARDS
    }

    private WantedState wantedState;
    private CurrentState currentState;

    public WantedState getWantedState() {
        return wantedState;
    }

    public void setWantedState(WantedState wantedState) {
        this.wantedState = wantedState;
    }

    public void setWantedState(WantedState wantedState, double wantedAngle) {
        this.wantedState = wantedState;
        this.wantedAngle = wantedAngle;
    }

    public double getWristAngle() {
        return wristInputs.wristMotorInputs.getMotorValue().getAsDouble();
    }

    public double getEncoderPosition() {
        return wristInputs.encoderPosition;
    }

    public Wrist(WristIO wristIO) {
        this.wristIO = wristIO;

        wristInputs = new WristIO.WristInputs();

        this.wristIO.updateInputs(wristInputs);

        wantedState = WantedState.IDLE;
        currentState = CurrentState.IDLING;
    }

    @Override
    public void periodic() {
        wristIO.updateInputs(wristInputs);

        currentState = handleStateTransition();

        wristInputs.wristMotorInputs.log();

        applyStates();
    }

    public CurrentState handleStateTransition() {
        switch (wantedState) {
            case IDLE:
                return CurrentState.IDLING;
            case MOVE_TO_ANGLE:
                return CurrentState.MOVING_TO_ANGLE;
            case MOVE_FORWARD:
                return CurrentState.MOVING_FORWARDS;
            case MOVE_BACKWARD:
                return CurrentState.MOVING_BACKWARDS;
        }
        return CurrentState.IDLING;
    }

    private void applyStates() {
        switch (currentState) {
            case IDLING:
                wristIO.setDutyCycle(0);
                break;
            case MOVING_FORWARDS:
                wristIO.setDutyCycle(0.1);
                break;
            case MOVING_BACKWARDS:
                wristIO.setDutyCycle(-0.1);
                break;
            case MOVING_TO_ANGLE:
                wristIO.moveToAngle(wantedAngle);
                break;
        }
    }

    private static Wrist instance;

    public static Wrist getInstance() {
        return instance;
    }

    public static void init(WristIO wristIO) {
        if (instance == null) {
            instance = new Wrist(wristIO);
        }
    }
}
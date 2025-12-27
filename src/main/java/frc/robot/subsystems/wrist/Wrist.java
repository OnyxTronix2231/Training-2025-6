package frc.robot.subsystems.wrist;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Wrist extends SubsystemBase {

    private final WristIO.WristInputs wristInputs;
    private final WristIO wristIO;

    private double wantedAngle;

    public enum WantedState {
        IDLE,
        OPEN,
        CLOSE,
        MOVE_TO_POSITION
    }


    public enum SystemState {
        IDLING,
        OPENING,
        CLOSING,
        MOVING_TO_POSITION
    }

    private Wrist.WantedState wantedState;
    private Wrist.SystemState systemState;

    public Wrist.WantedState getWantedState() {
        return wantedState;
    }

    public void setWantedState(Wrist.WantedState wantedState) {
        this.wantedState = wantedState;
    }

    public void setWantedState(Wrist.WantedState wantedState, double wantedAngle) {
        this.wantedState = wantedState;
        this.wantedAngle = wantedAngle;
    }

    public double getWristAngle() {
        return (wristInputs.wristMotor.getMotorValue().getAsDouble())*360;
    }

    public Wrist(WristIO wristIO) {
        this.wristIO = wristIO;

        wristInputs = new WristIO.WristInputs();

        this.wristIO.updateInputs(wristInputs);

        wantedState = Wrist.WantedState.IDLE;
        systemState = Wrist.SystemState.IDLING;
    }

    public void periodic() {
        wristIO.updateInputs(wristInputs);
        systemState = handleStateTransition();
        wristInputs.wristMotor.log();
        applyStates();
    }

    public Wrist.SystemState handleStateTransition() {
        switch (wantedState) {
            case IDLE:
                return Wrist.SystemState.IDLING;
            case OPEN:
                return Wrist.SystemState.OPENING;
            case CLOSE:
                return Wrist.SystemState.CLOSING;
            case MOVE_TO_POSITION:
                return Wrist.SystemState.MOVING_TO_POSITION;
        }
        return Wrist.SystemState.IDLING;
    }

    private void applyStates() {
        switch (systemState) {
            case IDLING:
                wristIO.setDutyCycle(0);
                break;
            case OPENING:
                wristIO.setDutyCycle(0.1);
                break;
            case CLOSING:
                wristIO.setDutyCycle(-0.1);
                break;
            case MOVING_TO_POSITION:
                wristIO.moveToAngle(wantedAngle);
                break;
        }
    }

    private static Wrist instance;

    public static void init(WristIO wristIO) {
        if (instance == null) {
            instance = new Wrist(wristIO);
        }
    }

    public static Wrist getInstance() {
        return instance;
    }

}

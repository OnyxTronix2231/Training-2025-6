package Lrobot.hinge;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Hinge extends SubsystemBase {
    private final HingeIO.HingeInputs hingeInputs;
    private final HingeIO hingeIO;

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

    public void setWantedState(WantedState wantedState) {
        this.wantedState = wantedState;
    }

    public WantedState getWantedState() {
        return wantedState;
    }

    public double getHingeAngle() {
        return hingeInputs.hingeInputs.getMotorValue().getAsDouble();
    }

    public void setWantedAngle(double angle) {
        hingeIO.setWantedAngle(angle);
    }

    public Hinge(HingeIO hingeIO) {
        this.hingeIO = hingeIO;

        hingeInputs = new HingeIO.HingeInputs();

        this.hingeIO.updateInputs(hingeInputs);

        wantedState = WantedState.IDLE;
        systemState = SystemState.IDLING;
    }

    @Override
    public void periodic() {
        this.hingeIO.updateInputs(hingeInputs);

        systemState = handleStateTransition();

        applyStates();
    }

    public SystemState handleStateTransition() {
        switch (wantedState) {
            case IDLE:
                return SystemState.IDLING;
            case OPEN:
                return SystemState.OPENING;
            case CLOSE:
                return SystemState.CLOSING;
        }
        return SystemState.IDLING;
    }

    private void applyStates() {
        switch (systemState) {
            case IDLING:
                hingeIO.setDutyCycle(0);
                break;
            case OPENING:
                hingeIO.setDutyCycle(0.3);
                break;
            case CLOSING:
                hingeIO.setDutyCycle(-0.3);
                break;
        }
    }

    private static Hinge instance;

    public static void init(HingeIO hingeIO) {
        if (instance == null) {
            instance = new Hinge(hingeIO);
        }
    }

    public static Hinge getInstance() {
        return instance;
    }
}


package Lrobot.hinge;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class HingeJava extends SubsystemBase {
    private final HingeIO.HingeInputs hingeInputs;
    private final HingeIO hingeIO;

    public double getHingeAngle() {
        return hingeInputs.hingeInputs.getMotorValue().getAsDouble();
    }

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

    public double getHingeLength() {
        return hingeInputs.hingeInputs.getMotorValue().getAsDouble();
    }

    public HingeJava(HingeIO hingeIO) {
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
                hingeIO.setDutyCycle(0.1);
                break;
            case CLOSING:
                hingeIO.setDutyCycle(-0.1);
                break;
        }
    }

    private static HingeJava instance;

    public static void init(HingeIO hingeIO) {
        if (instance == null) {
            instance = new HingeJava(hingeIO);
        }
    }

    public static HingeJava getInstance() {
        return instance;
    }
}

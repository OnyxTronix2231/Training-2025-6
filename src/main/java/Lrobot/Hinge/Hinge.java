package Lrobot.Hinge;

import TrainingUtils.KeyButton;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Hinge extends SubsystemBase {

    public enum SystemState {
        IDLING,
        OPENING,
        CLOSING
    }

    public enum WantedState {
        IDLE,
        OPEN,
        CLOSE
    }
    private SystemState currentState;
    private WantedState wantedState;
    private HingeIO hingeIO;
    private HingeIO.HingeInputs hingeInputs;
    private KeyButton button1;
    private KeyButton button2;

    public Hinge(HingeIO hingeIO) {
        this.hingeIO = hingeIO;

         hingeInputs = new HingeIO.HingeInputs();

        this.hingeIO.updateInputs(hingeInputs);

        wantedState = WantedState.IDLE;
        currentState = SystemState.IDLING;
        button1 = new KeyButton(1);
        button2 = new KeyButton(2);
    }

    @Override
    public void periodic() {
        this.hingeIO.updateInputs(hingeInputs);
        updateWantedState();
        currentState = handleStateTransition();
        applyState();
    }
    public void updateWantedState() {
        if (button1.isPressed()) {
            wantedState = WantedState.OPEN;
        }
        else if (button2.isPressed()) {
            wantedState = WantedState.CLOSE;
        }
        else {
            wantedState = WantedState.IDLE;
        }
    }

    public SystemState handleStateTransition () {
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

    public void applyState() {
        switch (currentState) {
            case OPENING:
                hingeIO.setDutyCycle(0.1);
                break;
            case CLOSING:
                hingeIO.setDutyCycle(-0.1);
                break;
            case IDLING:
                hingeIO.setDutyCycle(0);
                break;
        }
    }
}

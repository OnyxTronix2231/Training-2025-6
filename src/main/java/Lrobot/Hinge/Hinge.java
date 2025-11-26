package Lrobot.Hinge;

import Lrobot.elevator.Elevator;
import TrainingUtils.KeyButton;
import com.fasterxml.jackson.databind.util.RawValue;
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

    private final HingeIO.HingeInputs hingeInputs;

    public Hinge(HingeIO hingeIO) {
        this.hingeIO = hingeIO;

         hingeInputs = new HingeIO.HingeInputs();

        this.hingeIO.updateInputs(hingeInputs);



        wantedState = WantedState.IDLE;
        currentState = SystemState.IDLING;
    }

    @Override
    public void periodic() {
        this.hingeIO.updateInputs(hingeInputs);
        currentState = handleStateTransition();
        applyState();
    }
    public void setWantedState(WantedState wantedState) {
        this.wantedState = wantedState;
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

    private static Hinge instance;
    public  double getHingeAngle() {
        return hingeInputs.hingeMotorInputs.getMotorRawValue();
    }
    public static void init(HingeIO hingeIO ){
        if (instance == null) {
            instance = new Hinge(hingeIO);
        }
    }
    public static Hinge getInstance() {
        return instance;
    }

}

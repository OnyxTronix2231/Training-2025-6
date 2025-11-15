package Lrobot.Hinge;

import TrainingUtils.KeyButton;
import com.ctre.phoenix6.swerve.SwerveRequest;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.Superstructure;

public class Hinge extends SubsystemBase {

    public enum SystemState {
        IDLE,
        OPEN,
        CLOSE
    }

    public enum WantedState {
        IDLING,
        OPENING,
        CLOSING
    }
    private SystemState currentState;
    private WantedState wantedState;
    private HingeIO hingeIO;

    private KeyButton button1;
    private KeyButton button2;

    @Override
    public void periodic() {
        this.hingeIO.updateInputs(HingeIO.HingeInputs);
        updateWantedState();
        currentState = handleStateTransition();
        applyState();

    }
    public void updateWantedState() {
        if (button1.isPressed()) {
            wantedState = WantedState.OPENING;
        }
        else if (button2.isPressed()) {
            wantedState = WantedState.CLOSING;
        }
        else {
            wantedState = WantedState.IDLING;
        }
    }

    public SystemState handleStateTransition () {
        switch (wantedState) {
            case IDLING:
                return SystemState.IDLE;
            case OPENING:
                return SystemState.OPEN;
            case CLOSING:
                return SystemState.CLOSE;
        }
        return SystemState.IDLE;
    }

    public void applyState() {
        switch (currentState) {
            case OPEN:
                hingeIO.setDutyCycle(0.1);
            case CLOSE:
                hingeIO.setDutyCycle(-0.1);
            case IDLE:
                hingeIO.setDutyCycle(0);
        }
    }


}

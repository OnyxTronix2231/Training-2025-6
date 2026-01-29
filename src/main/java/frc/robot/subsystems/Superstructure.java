package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.littletonrobotics.junction.Logger;

public class Superstructure extends SubsystemBase {

    public enum WantedSuperState {
        DEFAULT_STATE,
        OPEN,
        CLOSE
    }

    public enum CurrentSuperState {
        DEFAULT_STATE,
        OPEN
    }

    private WantedSuperState wantedSuperState = WantedSuperState.DEFAULT_STATE;
    private CurrentSuperState currentSuperState = CurrentSuperState.DEFAULT_STATE;
    private CurrentSuperState previousSuperState;

    private Superstructure() {
    }

    @Override
    public void periodic() {

        Logger.recordOutput("Superstructure/WantedSuperState", wantedSuperState);
        Logger.recordOutput("Superstructure/CurrentSuperState", currentSuperState);
        Logger.recordOutput("Superstructure/PreviousSuperState", previousSuperState);

        currentSuperState = handleStateTransition();
        previousSuperState = currentSuperState;

        applyStates();
    }

    private CurrentSuperState handleStateTransition() {
        switch (wantedSuperState) {
            case DEFAULT_STATE:
                return CurrentSuperState.DEFAULT_STATE;
            case OPEN:
                return CurrentSuperState.OPEN;
        }

        return CurrentSuperState.DEFAULT_STATE;
    }

    public void applyStates() {
        switch (currentSuperState) {
            case DEFAULT_STATE:
                defaultState();
            case OPEN:
                stopped();
                break;
        }
    }

    private void defaultState() {
    }

    private void stopped() {
    }

    public WantedSuperState getWantedSuperState() {
        return wantedSuperState;
    }

    public void setWantedSuperState(WantedSuperState superState) {
        this.wantedSuperState = superState;
    }

    private static Superstructure instance;

    public static Superstructure getInstance() {
        return instance;
    }

    public static void init() {
        instance = new Superstructure();
    }
}
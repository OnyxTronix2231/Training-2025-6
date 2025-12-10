package Lrobot.elevator;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Elevator extends SubsystemBase {

    private final ElevatorIO.ElevatorInputs elevatorInputs;
    private final ElevatorIO elevatorIO;

    public enum WantedState {
        IDLE,
        TOGGLE,
        AUTO,
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

    public double getElevatorLength() {
        return elevatorInputs.elevatorMasterInputs.getMotorValue().getAsDouble();
    }

    public Elevator(ElevatorIO elevatorIO) {
        this.elevatorIO = elevatorIO;

        elevatorInputs = new ElevatorIO.ElevatorInputs();

        this.elevatorIO.updateInputs(elevatorInputs);

        wantedState = WantedState.IDLE;
        systemState = SystemState.IDLING;
    }

    @Override
    public void periodic() {
        this.elevatorIO.updateInputs(elevatorInputs);

        systemState = handleStateTransition();

        applyStates();

        elevatorInputs.elevatorMasterInputs.log();
    }

    public SystemState handleStateTransition() {
        switch (wantedState) {
            case IDLE:
                return SystemState.IDLING;
            case TOGGLE:
                if (SystemState.OPENING == systemState)
                    wantedState = WantedState.CLOSE;
                if (SystemState.CLOSING == systemState)
                    wantedState = WantedState.OPEN;
            case OPEN:
                return SystemState.OPENING;
            case CLOSE:
                return SystemState.CLOSING;
            case AUTO:
                if (elevatorIO.isFirstSwitchPressed() == elevatorIO.isSecondSwitchPressed()) {
                    return SystemState.IDLING;
                } else if (elevatorIO.isFirstSwitchPressed()) {
                    return SystemState.OPENING;
                } else {
                    return SystemState.CLOSING;
                }
        }
        return SystemState.IDLING;
    }

    private void applyStates() {
        switch (systemState) {
            case IDLING:
                elevatorIO.setDutyCycle(0);
                break;
            case OPENING:
                elevatorIO.setDutyCycle(0.1);
                break;
            case CLOSING:
                elevatorIO.setDutyCycle(-0.1);
                break;
        }
    }

    private static Elevator instance;

    public boolean isFirstSwitchPressed() {
        return elevatorIO.isFirstSwitchPressed();
    }

    public boolean isSecondSwitchPressed() {
        return elevatorIO.isSecondSwitchPressed();
    }

    public static void init(ElevatorIO elevatorIO) {
        if (instance == null) {
            instance = new Elevator(elevatorIO);
        }
    }

    public static Elevator getInstance() {
        return instance;
    }
}

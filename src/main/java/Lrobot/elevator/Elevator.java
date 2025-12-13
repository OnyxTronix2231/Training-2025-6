package Lrobot.elevator;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Elevator extends SubsystemBase {

    private final ElevatorIO.ElevatorInputs elevatorInputs;
    private final ElevatorIO elevatorIO;

    private double wantedLength;

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

    private WantedState wantedState;
    private SystemState systemState;

    public WantedState getWantedState() {
        return wantedState;
    }

    public void setWantedState(WantedState wantedState) {
        this.wantedState = wantedState;
    }

    public void setWantedState(WantedState wantedState, double wantedLength) {
        this.wantedState = wantedState;
        this.wantedLength = wantedLength;
    }

    public double getElevatorLength() {
        return elevatorInputs.elevatorMasterInputs.getMotorValue().getAsDouble();
    }

    public void setMicroswitch(boolean isPressed) {
        ElevatorIOSimulation.SimulatedSensors.isLimitSwitchPressed = isPressed;
    }

    public boolean isMicroswitchPressed() {
//        return elevatorIO.isMicroswitchPressed();
        return false;
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
        elevatorIO.updateInputs(elevatorInputs);
        systemState = handleStateTransition();
        elevatorInputs.elevatorMasterInputs.log();
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
            case MOVE_TO_POSITION:
                return SystemState.MOVING_TO_POSITION;
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
            case MOVING_TO_POSITION:
                elevatorIO.moveToLength(wantedLength);
                break;
        }
    }

    private static Elevator instance;

    public static void init(ElevatorIO elevatorIO) {
        if (instance == null) {
            instance = new Elevator(elevatorIO);
        }
    }

    public static Elevator getInstance() {
        return instance;
    }
}

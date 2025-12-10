package Lrobot.elevator;

import L5.training.LEDP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import org.littletonrobotics.junction.Logger;

public class Elevator extends SubsystemBase {

    private final ElevatorIO.ElevatorInputs elevatorInputs;
    private final ElevatorIO elevatorIO;

    public WantedState getWantedState() {
        return wantedState;
    }

    public enum WantedState {
        IDLE,
        OPEN,
        CLOSE,
        TAKE_IN
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

    public double getElevatorLength() {
        return elevatorInputs.elevatorMasterInputs.getMotorValue().getAsDouble();
    }

    public void setMicroswitch(boolean isPressed)
    {
        ElevatorIOSimulation.SimulatedSensors.isLimitSwitchPressed = isPressed;
    }

    public boolean isMicroswitchPressed()
    {
        return elevatorIO.isMicroswitchPressed();
    }

    public boolean isFirstSensorPressed()
    {
        return elevatorIO.isFirstSensorPressed();
    }

    public boolean isSecondSensorPressed()
    {
        return elevatorIO.isSecondSensorPressed();
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

//        Logger.recordOutput("Subsystems/Elevator/Current",
//                elevatorIO.getCurrent());
    }

    public SystemState handleStateTransition() {
        switch (wantedState) {
            case IDLE:
                return SystemState.IDLING;
            case OPEN:
                return SystemState.OPENING;
            case CLOSE:
                return SystemState.CLOSING;
            case TAKE_IN:
                if (elevatorIO.isFirstSensorPressed() && !elevatorIO.isSecondSensorPressed()) {
                    return SystemState.OPENING;
                } else if (!elevatorIO.isFirstSensorPressed() && elevatorIO.isSecondSensorPressed()) {
                    return SystemState.CLOSING;
                } else
                    return SystemState.IDLING;

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

    public static void init(ElevatorIO elevatorIO) {
        if (instance == null) {
            instance = new Elevator(elevatorIO);
        }
    }

    public static Elevator getInstance() {
        return instance;
    }
}

package Lrobot.elevator;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import org.littletonrobotics.junction.Logger;

public class Elevator extends SubsystemBase {

    private final ElevatorIO.ElevatorInputs elevatorInputs;
    private final ElevatorIO elevatorIO;

    private boolean censor1;
    private boolean censor2;

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

    public double getElevatorLength() {
        return elevatorInputs.elevatorMasterInputs.getMotorValue().getAsDouble();
    }

    public void setMicroswitch(boolean isPressed)
    {
        ElevatorIOSimulation.SimulatedSensors.isLimitSwitchPressed = isPressed;
    }

    public boolean isCensor1() {
        return censor1;
    }

    public void setCensor1(boolean censor1) {
        this.censor1 = censor1;
    }

    public boolean isCensor2() {
        return censor2;
    }

    public void setCensor2(boolean censor2) {
        this.censor2 = censor2;
    }

    public boolean isMicroswitchPressed()
    {
        return elevatorIO.isMicroswitchPressed();
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

//        Logger.recordOutput("Subsystems/Elevator/test",
//                elevatorIO.test());
    }

    public SystemState handleStateTransition() {
        switch (wantedState) {
            case IDLE:
                if (censor1 && censor2){return SystemState.IDLING;}
            case OPEN:
                if (censor1 && !censor2) {return SystemState.OPENING;}
            case CLOSE:
                if (!censor1 && censor2){return SystemState.CLOSING;}
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

    public void setLimitSwitchValue(boolean value) {
        ElevatorIOSimulation.SimulatedSensors.isLimitSwitchPressed = value;
    }

    public boolean getLimitSwitchValue() {
        return elevatorIO.getLimitSwitchValue();
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

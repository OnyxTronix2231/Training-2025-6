package frc.robot.subsystems.coralHolder;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class CoralHolder extends SubsystemBase {
    private final CoralHolderIO.CoralHolderInputs coralHolderInputs;
    private final CoralHolderIO coralHolderIO;
    private double fixTime;

    public enum WantedState {
        INTAKE,
        EJECT,
        IDLE
    }

    public enum SystemState {
        INTAKING,
        EJECTING,
        FIXING,
        IDLING
    }

    public enum CurrentFixDirection {
        INSIDE,
        OUTSIDE
    }

    private CurrentFixDirection currentFixDirection;

    private WantedState wantedState;
    private SystemState systemState;

    public WantedState getWantedState() {
        return wantedState;
    }

    public void setWantedState(WantedState wantedState) {
        this.wantedState = wantedState;
    }

    public SystemState getSystemState() {
        return systemState;
    }

    public double getSpinSpeed() {
        return coralHolderInputs.coralHolderInputs.getMotorAngularVelocityRadPerSec();
    }

    public CoralHolder(CoralHolderIO coralHolderIO) {
        this.coralHolderIO = coralHolderIO;

        coralHolderInputs = new CoralHolderIO.CoralHolderInputs();

        this.coralHolderIO.updateInputs(coralHolderInputs);

        wantedState = WantedState.INTAKE;
        systemState = SystemState.IDLING;

        fixTime = 0;
        currentFixDirection = CurrentFixDirection.OUTSIDE;
    }

    @Override
    public void periodic() {
        coralHolderIO.updateInputs(coralHolderInputs);

        systemState = handleStateTransition();

        coralHolderInputs.coralHolderInputs.log();

        applyStates();
    }

    public SystemState handleStateTransition() {
        switch (wantedState) {
            case IDLE:
                return SystemState.IDLING;
            case INTAKE:
                if (coralHolderInputs.isOuterSensorDetecting) {
                    if (coralHolderInputs.isInnerSensorDetecting) {
                        return SystemState.IDLING;
                    } else return SystemState.FIXING;
                }
                return SystemState.INTAKING;
            case EJECT:
                return SystemState.EJECTING;
        }
        return SystemState.IDLING;
    }


    public void applyStates() {
        switch (systemState) {
            case IDLING:
                coralHolderIO.setDutyCycle(0);
                break;
            case INTAKING:
                coralHolderIO.setDutyCycle(0.5);
                break;
            case EJECTING:
                coralHolderIO.setDutyCycle(-0.5);
                break;
            case FIXING:
                if (coralHolderInputs.isInnerSensorDetecting) {
                    systemState = SystemState.IDLING;
                    break;
                }

                if (fixTime != 0) {
                    if (Timer.getFPGATimestamp() - fixTime > `1) {
                        if (currentFixDirection == CurrentFixDirection.INSIDE) {
                            coralHolderIO.setDutyCycle(0.5);
                            currentFixDirection = CurrentFixDirection.OUTSIDE;
                        } else {
                            coralHolderIO.setDutyCycle(-0.5);
                            currentFixDirection = CurrentFixDirection.INSIDE;
                        }
                        fixTime = Timer.getFPGATimestamp();
                    }
                } else {
                    coralHolderIO.setDutyCycle(-0.5);
                    currentFixDirection = CurrentFixDirection.INSIDE;
                    fixTime = Timer.getFPGATimestamp();
                }

                break;
        }
    }

    public boolean isOuterSensorDetecting() {
        return coralHolderInputs.isOuterSensorDetecting;
    }

    public boolean isInnerSensorDetecting() {
        return coralHolderInputs.isInnerSensorDetecting;
    }

    private static CoralHolder instance;

    public static void init(CoralHolderIO coralHolderIO) {
        if (instance == null) {
            instance = new CoralHolder(coralHolderIO);
        }
    }

    public static CoralHolder getInstance() {
        return instance;
    }
}

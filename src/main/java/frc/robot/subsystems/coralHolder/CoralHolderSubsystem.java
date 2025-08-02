package frc.robot.subsystems.coralHolder;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.littletonrobotics.junction.Logger;

import static frc.robot.subsystems.coralHolder.CoralHolderConstants.*;

public class CoralHolderSubsystem extends SubsystemBase {

    private final CoralHolderIO coralHolderIO;
    private final CoralHolderIO.CoralHolderIOInputs coralHolderInputs;
    private double coralFixTimeStamp = Double.NaN;
    private double scoreTimeStamp = Double.NaN;
    private double scoreL1TimeStamp = Double.NaN;
    private boolean isFixSpeedOut = false;
    private boolean ejectedCoralFlag = false;

    public CoralHolderSubsystem(CoralHolderIO coralHolderIO) {
        this.coralHolderIO = coralHolderIO;
        coralHolderInputs = new CoralHolderIO.CoralHolderIOInputs();
        ShuffleboardTab coralHolderTab = Shuffleboard.getTab("Coral Holder");
    }

    public enum WantedState {
        IDLE,
        EJECT,
        KEEP,
        INTAKE,
        SCORE,
        SCORE_L1
    }

    private enum SystemState {
        IDLING,
        EJECTING,
        KEEP_CORAL,
        COLLECTING_CORAL,
        FIX_CORAL,
        SCORE_CORAL_L1,
        SCORE_CORAL
    }

    private WantedState wantedState = WantedState.IDLE;

    private SystemState systemState = SystemState.IDLING;
    private SystemState lastSystemState = SystemState.IDLING;

    @Override
    public void periodic() {
        double startTime = Timer.getFPGATimestamp();

        coralHolderIO.updateInputs(coralHolderInputs);

        systemState = handleStateTransition();

        lastSystemState = systemState;

        applyStates();

        Logger.recordOutput("Subsystems/CoralHolder/latencyPeriodicMs", (Timer.getFPGATimestamp() - startTime) * 1000);
        Logger.recordOutput("Subsystems/CoralHolder/Sensors/front sensor", coralHolderInputs.isFrontSensorTripped);
        Logger.recordOutput("Subsystems/CoralHolder/Sensors/back sensor", coralHolderInputs.isBackSensorTripped);
        Logger.recordOutput("Subsystems/CoralHolder/States/WantedState", wantedState.toString());
        Logger.recordOutput("Subsystems/CoralHolder/States/SystemState",systemState.toString());
    }

    private SystemState handleStateTransition() {
        switch (wantedState) {
            case IDLE:
                return SystemState.IDLING;
            case EJECT:
                return SystemState.EJECTING;
            case KEEP:
                return SystemState.KEEP_CORAL;
            case SCORE:
                if (lastSystemState != SystemState.SCORE_CORAL) {
                    scoreTimeStamp = Timer.getFPGATimestamp();
                    ejectedCoralFlag = false;
                }
                // TODO consider using rising edge or falling edge debouncer instead of timer stamps.
                if (!coralHolderInputs.isFrontSensorTripped && Timer.getFPGATimestamp() - scoreTimeStamp >= 0.2) {
                    scoreTimeStamp = Double.NaN;
//                    setWantedState(WantedState.IDLE);
                    ejectedCoralFlag = true;
                    // TODO should we change the WantedState here?
                    return SystemState.IDLING;
                }
                return SystemState.SCORE_CORAL;
            case SCORE_L1:
                if (lastSystemState != SystemState.SCORE_CORAL_L1) {
                    scoreL1TimeStamp = Timer.getFPGATimestamp();
                    ejectedCoralFlag = false;
                }

                if (!coralHolderInputs.isFrontSensorTripped && Timer.getFPGATimestamp() - scoreL1TimeStamp >= 0.2) {
                    scoreL1TimeStamp = Double.NaN;
                    ejectedCoralFlag = true;
//                    setWantedState(WantedState.IDLE);
                    return SystemState.IDLING;
                }

                return SystemState.SCORE_CORAL_L1;
            case INTAKE:
                if (coralHolderInputs.isBackSensorTripped && coralHolderInputs.isFrontSensorTripped) {
                    setWantedState(WantedState.KEEP);
                    return SystemState.KEEP_CORAL;
                } else if (coralHolderInputs.isFrontSensorTripped) {
                    if (lastSystemState != SystemState.FIX_CORAL) {
                        coralFixTimeStamp = Timer.getFPGATimestamp();
                        isFixSpeedOut = false;
                    }
                    return SystemState.FIX_CORAL;
                }
                return SystemState.COLLECTING_CORAL;
        }

        return SystemState.IDLING;
    }


    private void applyStates() {
        switch (systemState) {
            case IDLING:
                coralHolderIO.setDutyCycle(0);
                break;
            case EJECTING:
                coralHolderIO.setDutyCycle(EJECT_SPEED);
                break;
            case FIX_CORAL:
                if (isFixSpeedOut) {
                    if (Timer.getFPGATimestamp() - coralFixTimeStamp >= 0.35) {
                        isFixSpeedOut = false;
                        coralFixTimeStamp = Timer.getFPGATimestamp();
                    }

                    coralHolderIO.setDutyCycle(INTAKE_SPEED);
                } else {
                    if (Timer.getFPGATimestamp() - coralFixTimeStamp >= 0.2) {
                        isFixSpeedOut = true;
                        coralFixTimeStamp = Timer.getFPGATimestamp();
                    }

                    coralHolderIO.setDutyCycle(FIX_SPEED_OUT);
                }

                break;
            case KEEP_CORAL:
                coralHolderIO.setDutyCycle(KEEP_SPEED);
                break;
            case SCORE_CORAL:
                coralHolderIO.setDutyCycle(SCORE_SPEED);
                break;
            case SCORE_CORAL_L1:
                coralHolderIO.setDutyCycle(L1_SCORING);
                break;
            case COLLECTING_CORAL:
                coralHolderIO.setDutyCycle(INTAKE_SPEED);
                break;
        }
    }

    public boolean getEjectFlag() {
        return ejectedCoralFlag;
    }

    public boolean hasSensorsTriggered() {
        return coralHolderInputs.isFrontSensorTripped && coralHolderInputs.isBackSensorTripped;
    }

    public void
    setWantedState(WantedState wantedState) {
        this.wantedState = wantedState;
    }

    private static CoralHolderSubsystem instance;

    public static CoralHolderSubsystem getInstance() {
        return instance;
    }

    public static void init(CoralHolderIO coralHolderIO) {
        instance = new CoralHolderSubsystem(coralHolderIO);
    }

}

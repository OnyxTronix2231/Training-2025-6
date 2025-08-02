package frc.robot.subsystems.ballIntake;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.littletonrobotics.junction.Logger;

import static frc.robot.subsystems.ballIntake.BallIntakeConstants.*;

public class BallIntakeSubsystem extends SubsystemBase {

    private final BallIntakeIO ballIntakeIO;
    private final BallIntakeIO.BallIntakeIOInputs ballIntakeInputs;

    private double ejectAlgaeTimestamp = Double.NaN;
    private boolean algaeEjectedFlag;

    public BallIntakeSubsystem(BallIntakeIO ballIntakeIO) {
        this.ballIntakeIO = ballIntakeIO;

        ballIntakeInputs = new BallIntakeIO.BallIntakeIOInputs();
    }

    public enum WantedState {
        IDLE,
        INTAKE,
        SHOOT,
        PROCESSOR
    }

    private enum SystemState {
        IDLING,
        COLLECTING_ALGAE,
        EJECTING_PROCESSOR_ALGAE,
        SHOOTING_ALGAE
    }

    private WantedState wantedState = WantedState.IDLE;

    private SystemState currentSystemState = SystemState.IDLING;
    private SystemState previousSystemState = SystemState.IDLING;

    @Override
    public void periodic() {
        double startTime = Timer.getFPGATimestamp();

        ballIntakeIO.updateInputs(ballIntakeInputs);

        currentSystemState = handleStateTransition();

        previousSystemState = currentSystemState;

        applyStates();

        Logger.recordOutput("Subsystems/BallIntake/SystemState", currentSystemState);
        Logger.recordOutput("Subsystems/BallIntake/WantedState", wantedState);

        // TODO we need to create a function to convert seconds to miliseconds
        Logger.recordOutput("Subsystems/BallIntake/latencyPeriodicMs", (Timer.getFPGATimestamp() - startTime) * 1000);
    }


    private SystemState handleStateTransition() {
        switch (wantedState) {
            case IDLE:
                return SystemState.IDLING;
            case INTAKE:
                if (ballIntakeInputs.isBallIntakeMicroSwitchPressed || ballIntakeIO.reachedCurrentLimit(ballIntakeInputs)) {
                    return SystemState.IDLING;
                }
                return SystemState.COLLECTING_ALGAE;
            case PROCESSOR:
                if (previousSystemState != SystemState.EJECTING_PROCESSOR_ALGAE) {
                    algaeEjectedFlag = false;
                    ejectAlgaeTimestamp = Timer.getFPGATimestamp();
                }
                if (Timer.getFPGATimestamp() - ejectAlgaeTimestamp >= 0.4) {
                    ejectAlgaeTimestamp = Double.NaN;
                    algaeEjectedFlag = true;
                    return SystemState.IDLING;
                }
                return SystemState.EJECTING_PROCESSOR_ALGAE;
            case SHOOT:
                return SystemState.SHOOTING_ALGAE;
        }

        return SystemState.IDLING;
    }


    private void applyStates() {
        switch (currentSystemState) {
            case IDLING:
                ballIntakeIO.setDutyCycle(0);
                break;
            case COLLECTING_ALGAE:
                ballIntakeIO.setDutyCycle(BALL_INTAKE_SPEED);
                break;
            case EJECTING_PROCESSOR_ALGAE:
                ballIntakeIO.setDutyCycle(BALL_PROCESSOR_ANGLE_SPEED);
                break;
            case SHOOTING_ALGAE:
                // TODO placeholder
                ballIntakeIO.setDutyCycle(BALL_NET_PROCESSOR_ANGLE_SPEED);
                break;
        }
    }

    public boolean getEjectFlag() {
        return algaeEjectedFlag;
    }

    public boolean hasSensorsTriggered() {
        return ballIntakeInputs.isBallIntakeMicroSwitchPressed || ballIntakeIO.reachedCurrentLimit(ballIntakeInputs);
    }

    public void setWantedState(WantedState wantedState) {
        this.wantedState = wantedState;
    }

    private static BallIntakeSubsystem instance;

    public static BallIntakeSubsystem getInstance() {
        return instance;
    }

    public static void init(BallIntakeIO ballIntakeIO) {
        instance = new BallIntakeSubsystem(ballIntakeIO);
    }
}

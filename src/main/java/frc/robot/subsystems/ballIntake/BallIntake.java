package frc.robot.subsystems.ballIntake;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.subsystems.ballIntake.BallIntakeConstants.*;

public class BallIntake extends SubsystemBase {
    private final BallIntakeIO.ballIntakeInputs ballIntakeInputs;
    private final BallIntakeIO ballIntakeIO;

    public enum WantedState {
        IDLE,
        INTAKE,
        EJECT
    }

    public enum CurrentState {
        IDLE,
        INTAKING,
        HAS_BALL,
        EJECTING
    }

    private WantedState wantedState;
    private CurrentState currentState;

    public void setWantedState(WantedState wantedState) {
        this.wantedState = wantedState;
    }

    public WantedState getWantedState() {
        return wantedState;
    }

    public BallIntake(BallIntakeIO ballIntakeIO) {
        this.ballIntakeIO = ballIntakeIO;

        ballIntakeInputs = new BallIntakeIO.ballIntakeInputs();

        wantedState = WantedState.IDLE;
        currentState = CurrentState.IDLE;
    }

    public void periodic() {
        ballIntakeIO.updateInputs(ballIntakeInputs);

        currentState = handleStateTransition();

        applyStates();

        ballIntakeInputs.ballIntakeInput.log();
    }

    public CurrentState handleStateTransition() {
        switch (wantedState) {
            case IDLE:
                return CurrentState.IDLE;
            case INTAKE:
                if (ballIntakeIO.isMicroswitchPressed())
                    return CurrentState.HAS_BALL;
                return CurrentState.INTAKING;
            case EJECT:
                return CurrentState.EJECTING;
        }
        return CurrentState.IDLE;
    }

    public void applyStates() {
        switch (currentState) {
            case EJECTING -> ballIntakeIO.setDutyCycle(BALL_EJECT_SPEED);
            case INTAKING -> ballIntakeIO.setDutyCycle(BALL_INTAKE_SPEED);
            case HAS_BALL -> ballIntakeIO.setDutyCycle(KEEP_BALL_SPEED);
            case IDLE -> ballIntakeIO.setDutyCycle(0);
        }
    }

    public boolean isSwitchPressed() {
        return ballIntakeIO.isMicroswitchPressed();
    }

    private static BallIntake instance;

    public static void init(BallIntakeIO ballIntakeIO) {
        if (instance == null) {
            instance = new BallIntake(ballIntakeIO);
        }
    }

    public static BallIntake getInstance() {
        return instance;
    }
}

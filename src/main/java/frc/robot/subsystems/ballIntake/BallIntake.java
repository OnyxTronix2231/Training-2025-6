package frc.robot.subsystems.ballIntake;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class BallIntake extends SubsystemBase {

    private final BallIntakeIO.BallIntakeInputs ballIntakeInputs;
    private final BallIntakeIO ballIntakeIO;

    public enum WantedState {
        IDLE,
        INTAKE,
        EJECT
    }

    public enum SystemState {
        IDLING,
        INTAKING,
        EJECTING
    }

    private WantedState wantedState;
    private SystemState systemState;

    public void setWantedState(WantedState wantedState) {
        this.wantedState = wantedState;
    }

    public WantedState getWantedState() {
        return wantedState;
    }

    public boolean isMicroswitchPressed() {
        return ballIntakeIO.isMicroswitchPressed();
    }

    public BallIntake(BallIntakeIO ballIntakeIO) {
        this.ballIntakeIO = ballIntakeIO;

        ballIntakeInputs = new BallIntakeIO.BallIntakeInputs();

        this.ballIntakeIO.updateInputs(ballIntakeInputs);

        wantedState = BallIntake.WantedState.IDLE;
        systemState = BallIntake.SystemState.IDLING;
    }

    @Override
    public void periodic() {
        ballIntakeIO.updateInputs(ballIntakeInputs);
        systemState = handleStateTransition();
        ballIntakeInputs.ballIntakeMasterInputs.log();
        applyStates();
    }

    public SystemState handleStateTransition() {
        switch (wantedState) {
            case IDLE:
                return BallIntake.SystemState.IDLING;
            case INTAKE:
                return BallIntake.SystemState.INTAKING;
            case EJECT:
                return BallIntake.SystemState.EJECTING;
        }
        return BallIntake.SystemState.IDLING;
    }

    private void applyStates() {
        switch (systemState) {
            case IDLING -> {
                idle();
            }
            case INTAKING -> {
                intake();
            }
            case EJECTING -> {
                eject();
            }
        }
    }

    private void idle() {
        ballIntakeIO.setDutyCycle(0);
    }

    private void intake() {
        ballIntakeIO.setDutyCycle(0.1);
    }

    private void eject() {
        ballIntakeIO.setDutyCycle(-0.1);
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
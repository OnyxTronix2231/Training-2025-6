package frc.robot.subsystems.ballIntake;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.subsystems.ballIntake.BallIntakeConstants.EJECT_SPEED;
import static frc.robot.subsystems.ballIntake.BallIntakeConstants.INTAKE_SPEED;

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

    public WantedState getWantedState() {
        return wantedState;
    }

    public void setWantedState(WantedState wantedState) {
        this.wantedState = wantedState;
    }

    public boolean isLimitSwitchPressed() {
        return ballIntakeIO.isLimitSwitchPressed();
    }

    public BallIntake(BallIntakeIO ballIntakeIO) {
        this.ballIntakeIO = ballIntakeIO;

        ballIntakeInputs = new BallIntakeIO.BallIntakeInputs();

        this.ballIntakeIO.updateInputs(ballIntakeInputs);

        wantedState = WantedState.IDLE;
        systemState = SystemState.IDLING;
    }

    @Override
    public void periodic() {
        ballIntakeIO.updateInputs(ballIntakeInputs);

        systemState = handleStateTransition();

        ballIntakeInputs.ballIntakeMotorInputs.log();

        applyStates();
    }

    public SystemState handleStateTransition() {
        switch (wantedState) {
            case IDLE:
                return SystemState.IDLING;
            case INTAKE:
                return SystemState.INTAKING;
            case EJECT:
                return SystemState.EJECTING;
        }
        return SystemState.IDLING;
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

    private void idle(){
        ballIntakeIO.setDutyCycle(0);
    }

    private void intake(){
        ballIntakeIO.setDutyCycle(INTAKE_SPEED);
    }

    private void eject(){
        ballIntakeIO.setDutyCycle(EJECT_SPEED);
    }

    private static BallIntake instance;

    public static void init(BallIntakeIO ballIntakeIO) {
        if(instance == null){
            instance = new BallIntake(ballIntakeIO);
        }
    }

    public static BallIntake getInstance(){
        return instance;
    }

}

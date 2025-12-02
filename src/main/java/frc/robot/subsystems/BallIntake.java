package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.subsystems.BallIntakeConstants.EJECT_SPEED;
import static frc.robot.subsystems.BallIntakeConstants.INTAKE_SPEED;

public class BallIntake extends SubsystemBase {
    public enum WantedStates {
        IDLE,
        INTAKE,
        EJECT
    }

    public enum SystemStates {
        IDLE,
        INTAKE,
        EJECT;
    }

    private final BallIntakeIO.BallIntakeIOInputs ballIntakeIOInputs;
    private final BallIntakeIO ballIntakeIO;

    private WantedStates wantedState;
    private SystemStates systemState;

    public void setWantedState(WantedStates newWantedState) {wantedState = newWantedState;}

    public WantedStates getWantedState() {return wantedState;}

    public boolean isLimitSwitchPressed() {return ballIntakeIO.isLimitSwitchPressed();}

    public BallIntake(BallIntakeIO ballIntakeIO) {
        this.ballIntakeIO = ballIntakeIO;
        this.ballIntakeIOInputs = new BallIntakeIO.BallIntakeIOInputs();

        this.ballIntakeIO.updateInputs(ballIntakeIOInputs);

        wantedState = WantedStates.IDLE;
        systemState = SystemStates.IDLE;
    }

    @Override
    public void periodic() {
        ballIntakeIO.updateInputs(ballIntakeIOInputs);
        systemState = handleStateTransition();
        ballIntakeIOInputs.ballIntakeMotorInputs.log();
        applyState();
    }

    private SystemStates handleStateTransition() {
        switch (wantedState) {
            case IDLE:
                return SystemStates.IDLE;
            case INTAKE:
                return SystemStates.INTAKE;
            case EJECT:
                return SystemStates.EJECT;
        }

        return SystemStates.IDLE;
    }

    private void applyState() {
        switch (systemState) {
            case IDLE -> {
                idle();
            }
            case INTAKE -> {
                intake();
            }
            case EJECT -> {
                eject();
            }
        }
    }

    private void idle() {
        ballIntakeIO.setDutyCycle(0);
    }

    private void intake() {
        ballIntakeIO.setDutyCycle(INTAKE_SPEED);
    }

    private void eject() {
        ballIntakeIO.setDutyCycle(EJECT_SPEED);
    }

    private static BallIntake instance;

    public static BallIntake init(BallIntakeIO ballIntakeIO) {
        if (instance == null) {
            instance = new BallIntake(ballIntakeIO);
        }
        return instance;
    }

    public static BallIntake getInstance() {
        return instance;
    }

}

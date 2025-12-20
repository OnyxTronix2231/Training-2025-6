package frc.robot.subsystems.wrist;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Wrist extends SubsystemBase {

    private final WristIO.WristInputs wristInputs;
    private final WristIO wristIO;

    private double wantedAngle;

    public enum WantedState {

        IDLE,
        RAISE,
        LOWER,
        MOVE_TO_ANGLE
    }

    private enum SystemState {

        IDLING,
        RAISING,
        LOWERING,
        MOVING_TO_ANGLE
    }

    private WantedState wantedState;
    private SystemState systemState;

    public WantedState getWantedState() {
        return wantedState;
    }

    public void setWantedState(WantedState wantedState) {
        this.wantedState = wantedState;
    }

    public void setWantedState(WantedState wantedState, double wantedAngle) {
        this.wantedState = wantedState;
        this.wantedAngle = wantedAngle;
    }

    public double getWristAngle() {
        return wristInputs.wristMasterInputs.getMotorValue().getAsDouble();
    }

    public Wrist(WristIO wristIO) {
        this.wristIO = wristIO;

        wristInputs = new WristIO.WristInputs();

        this.wristIO.updateInputs(wristInputs);

        wantedState = WantedState.IDLE;
        systemState = SystemState.IDLING;
    }

    @Override
    public void periodic() {
        wristIO.updateInputs(wristInputs);
        systemState = handleStateTransition();
        wristInputs.wristMasterInputs.log();
        applyStates();
    }

    public SystemState handleStateTransition() {
        switch (wantedState) {
            case IDLE:
                return SystemState.IDLING;
            case RAISE:
                return SystemState.RAISING;
            case LOWER:
                return SystemState.LOWERING;
            case MOVE_TO_ANGLE:
                return SystemState.MOVING_TO_ANGLE;
        }
        return SystemState.IDLING;
    }

    private void applyStates() {
        switch (systemState) {
            case IDLING:
                wristIO.setDutyCycle(0);
                break;
            case RAISING:
                wristIO.setDutyCycle(0.1);
                break;
            case LOWERING:
                wristIO.setDutyCycle(-0.1);
                break;
            case MOVING_TO_ANGLE:
                wristIO.moveToAngle(wantedAngle);
                break;
        }
    }

    private static Wrist instance;

    public static void init(WristIO wristIO) {
        if (instance == null) {
            instance = new Wrist(wristIO);
        }
    }

    public static Wrist getInstance() {
        return instance;
    }
}

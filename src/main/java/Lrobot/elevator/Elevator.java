package Lrobot.elevator;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.Superstructure;
import org.littletonrobotics.junction.Logger;


public class Elevator extends SubsystemBase {

    private final ElevatorIO.ElevatorInputs elevatorInputs;
    private final ElevatorIO elevatorIO;

    private double wantedElevatorHeight;

    public enum WantedState {
        IDLE,
        MOVE_TO_POSITION,
        MOVE_DUTY_CYCLE,
        HOME
    }

    public enum SystemState {
        IDLING,
        MOVING_TO_POSITION,
        MOVE_DUTY_CYCLE,
        HOMING
    }

    private WantedState wantedState;
    private SystemState previousSystemState;
    private SystemState systemState;

    public void setWantedState(WantedState wantedState) {
        this.wantedState = wantedState;
    }

    public void setWantedState(WantedState wantedState, double wantedElevatorHeight) {
        this.wantedState = wantedState;
        this.wantedElevatorHeight = wantedElevatorHeight;
    }


    public double getElevatorLength() {
        return elevatorInputs.elevatorLength;
    }

    public Elevator(ElevatorIO elevatorIO) {
        this.elevatorIO = elevatorIO;

        elevatorInputs = new ElevatorIO.ElevatorInputs();

        this.elevatorIO.updateInputs(elevatorInputs);

        wantedState = WantedState.IDLE;
        systemState = SystemState.IDLING;
        previousSystemState = SystemState.IDLING;

    }

    @Override
    public void periodic() {
        this.elevatorIO.updateInputs(elevatorInputs);

        systemState = handleStateTransition();

        applyStates();

        previousSystemState = systemState;
    }

    public SystemState handleStateTransition() {
        switch (wantedState) {
            case IDLE:
                return SystemState.IDLING;
            case MOVE_TO_POSITION:
                return SystemState.MOVING_TO_POSITION;
            case MOVE_DUTY_CYCLE:
                return SystemState.MOVE_DUTY_CYCLE;
        }
        return SystemState.IDLING;
    }

    private void applyStates() {
        switch (systemState) {
            case IDLING:
                elevatorIO.setDutyCycle(0);
                break;

            case MOVING_TO_POSITION:
                elevatorIO.moveToLength(wantedElevatorHeight);
                break;

            case MOVE_DUTY_CYCLE:
                elevatorIO.setDutyCycle(wantedElevatorHeight);
                break;
        }
    }

    public boolean isElevatorOnTarget(double targetElevatorPosition, double elevatorTolerance) {
        return Math.abs(targetElevatorPosition - getElevatorLength()) <= elevatorTolerance;
    }

    public double getElevatorVelocity() {
        return elevatorInputs.elevatorAngularVelocityRadPerSec;
    }

    public double getElevatorAcceleration() {
        return elevatorInputs.elevatorAngularAccelerationRadPerSecSquared;
    }

    public WantedState getWantedState() {
        return wantedState;
    }

    public SystemState getSystemState() {
        return systemState;
    }

    public void updateElevatorPID(double p, double i, double d) {
        elevatorIO.updatePID(p, i, d);
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

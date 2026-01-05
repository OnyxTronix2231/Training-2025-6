package frc.robot.subsystems.arm;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.lib.PID.PIDValues;
import frc.robot.subsystems.arm.elevator.ElevatorIO;
import frc.robot.subsystems.arm.wrist.WristIO;

public class ArmSubsystem extends SubsystemBase {
    private final ElevatorIO.ElevatorInputs elevatorInputs;
    private final ElevatorIO elevatorIO;

    private final WristIO.WristInputs wristInputs;
    private final WristIO wristIO;

    private ArmPosition wantedArmPosition;
    private ArmPosition previousWantedArmPosition;

    public enum WantedState {
        IDLE,
        MOVE_TO_POSITION,
        HOME
    }

    public enum SystemState {
        IDLE,
        MOVES_TO_POSITION,
        HOME
    }

    private WantedState wantedState;
    private SystemState previousSystemState;
    private SystemState systemState;

    public void setWantedState(WantedState wantedState) {
        this.wantedState = wantedState;
    }

    public void setWantedState(WantedState wantedState, ArmPosition position) {
        this.wantedState = wantedState;
        this.wantedArmPosition = position;
    }

    public WantedState getWantedState() {
        return wantedState;
    }

    public SystemState getSystemState() {
        return systemState;
    }

    public double getElevatorLength() {
        return wantedArmPosition.getElevatorLength();
    }

    public double getWristAngle() {
        return wantedArmPosition.getWristAngle();
    }

    public ArmSubsystem(ElevatorIO elevatorIO, WristIO wristIO) {
        this.elevatorInputs = new ElevatorIO.ElevatorInputs();
        this.elevatorIO = elevatorIO;
        this.elevatorIO.updateInputs(elevatorInputs);

        this.wristInputs = new WristIO.WristInputs();
        this.wristIO = wristIO;
        this.wristIO.updateInputs(wristInputs);

        this.wantedState = WantedState.IDLE;
        this.systemState = SystemState.IDLE;
        this.previousSystemState = SystemState.IDLE;

        this.wantedArmPosition = ArmPositionConstants.ZEROED;
        this.previousWantedArmPosition = ArmPositionConstants.ZEROED;
    }

    @Override
    public void periodic() {
        elevatorIO.updateInputs(elevatorInputs);
        wristIO.updateInputs(wristInputs);

        systemState = handleStateTransition();

        applyStates();

        log();

        previousSystemState = systemState;
        previousWantedArmPosition = wantedArmPosition;
    }

    public void log() {
        elevatorInputs.elevatorMasterInputs.log();

        wristInputs.wristMotorInputs.log();
    }

    public SystemState handleStateTransition() {
        switch (wantedState) {
            case IDLE:
                return SystemState.IDLE;
            case MOVE_TO_POSITION:
                return SystemState.MOVES_TO_POSITION;
            case HOME:
                return SystemState.HOME;
        }
        return SystemState.IDLE;
    }

    public void applyStates() {
        switch (systemState) {
            case IDLE -> idle();

            case MOVES_TO_POSITION -> moveToPosition();

            case HOME -> home();
        }
    }

    public void idle() {
        elevatorIO.setDutyCycle(0);
        wristIO.setDutyCycle(0);
    }

    public void moveToPosition() {
        elevatorIO.moveToLength(wantedArmPosition.getElevatorLength());
        wristIO.moveToAngle(wantedArmPosition.getWristAngle());
    }

    public void home() {
        elevatorIO.moveToLength(0);
        wristIO.moveToAngle(0);
    }

    public void updateElevatorPID(PIDValues elevatorPIDValues) {
        elevatorIO.updatePID(elevatorPIDValues);
    }

    public void updateWristPID(PIDValues wristPIDValues) {
        wristIO.updatePID(wristPIDValues);
    }

    public double getElevatorVelocity(){
        return elevatorInputs.elevatorMasterInputs.getMotorAngularVelocityRadPerSec();
    }

    public double getElevatorAcceleration(){
        return elevatorInputs.elevatorMasterInputs.getMotorAngularAccelerationRadPerSecSquared();
    }

    public double getWristVelocity(){
        return wristInputs.wristMotorInputs.getMotorAngularVelocityRadPerSec();
    }

    public double getWristAcceleration(){
        return wristInputs.wristMotorInputs.getMotorAngularAccelerationRadPerSecSquared();
    }

    private static ArmSubsystem instance;

    public static void init(ElevatorIO elevatorIO, WristIO wristIO) {
        if (instance == null) {
            instance = new ArmSubsystem(elevatorIO, wristIO);
        }
    }

    public static ArmSubsystem getInstance() {
        return instance;
    }
}

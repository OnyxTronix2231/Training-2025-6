package frc.robot.subsystems.arm;

import edu.wpi.first.math.filter.Debouncer;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.lib.PID.PIDValues;
import frc.robot.subsystems.arm.elevator.ElevatorIO;
import frc.robot.subsystems.arm.wrist.WristIO;
import org.littletonrobotics.junction.Logger;

import static frc.robot.subsystems.arm.elevator.ElevatorConstants.*;
import static frc.robot.subsystems.arm.wrist.WristConstants.WRIST_ALLOWED_ANGLE_ERROR;
import static frc.robot.subsystems.arm.wrist.WristConstants.ZEROED_ANGLE;

public class Arm extends SubsystemBase {
    private final ElevatorIO.ElevatorInputs elevatorInputs;
    private final ElevatorIO elevatorIO;

    private final WristIO.WristInputs wristInputs;
    private final WristIO wristIO;

    private final Debouncer elevatorSwitchDebouncer;

    private ArmPosition wantedArmPosition;

    public enum WantedState {
        IDLE,
        MOVE_TO_POSITION,
    }

    public enum SystemState {
        IDLING,
        MOVING_TO_POSITION,
    }

    private WantedState wantedState;
    private SystemState previousSystemState;
    private SystemState systemState;

    public void setWantedState(WantedState wantedState) {
        this.wantedState = wantedState;
    }

    public void setWantedState(WantedState wantedState, ArmPosition wantedArmPosition) {
        this.wantedState = wantedState;
        this.wantedArmPosition = wantedArmPosition;
    }

    public WantedState getWantedState() {
        return wantedState;
    }

    public SystemState getSystemState() {
        return systemState;
    }

    public Arm(WristIO wristIO, ElevatorIO elevatorIO) {
        this.wristIO = wristIO;
        this.elevatorIO = elevatorIO;

        wristInputs = new WristIO.WristInputs();
        elevatorInputs = new ElevatorIO.ElevatorInputs();

        this.wristIO.updateInputs(wristInputs);
        this.elevatorIO.updateInputs(elevatorInputs);

        wantedState = WantedState.IDLE;
        systemState = SystemState.IDLING;
        previousSystemState = SystemState.IDLING;

        this.wantedArmPosition = new ArmPosition(ZEROED_HEIGHT, ZEROED_ANGLE);

        elevatorSwitchDebouncer = new Debouncer(ELEVATOR_SWITCH_DEBOUNCE_TIME, Debouncer.DebounceType.kBoth);
    }

    @Override
    public void periodic() {
        wristIO.updateInputs(wristInputs);
        elevatorIO.updateInputs(elevatorInputs);

        double timestamp = Timer.getFPGATimestamp();

        systemState = handleStateTransition();

        applyStates();

        wristInputs.wristMotorInputs.log();
        elevatorInputs.elevatorMasterInputs.log();

        previousSystemState = systemState;

        Logger.recordOutput("Subsystems/Arm/PeriodicTime", Timer.getFPGATimestamp() - timestamp);
    }

    SystemState handleStateTransition() {
        switch (wantedState) {
            case IDLE:
                return SystemState.IDLING;
            case MOVE_TO_POSITION:
                return SystemState.MOVING_TO_POSITION;
        }
        return SystemState.IDLING;
    }

    void applyStates() {
        switch (systemState) {
            case IDLING:
                wristIO.setDutyCycle(0);
                elevatorIO.setDutyCycle(0);
                break;
            case MOVING_TO_POSITION:
                wristIO.moveToAngle(wantedArmPosition.getArmAngle());
                elevatorIO.moveToLength(wantedArmPosition.getElevatorLength());
                break;
        }
    }

    public boolean isElevatorSwitchPressed() {
        return elevatorSwitchDebouncer.calculate(elevatorInputs.isMicroSwitchPressed);
    }

    public boolean isWristOnTarget() {
        return Math.abs(wantedArmPosition.getArmAngle() - this.getWristAngle()) <= WRIST_ALLOWED_ANGLE_ERROR;
    }

    public boolean isElevatorOnTarget() {
        return Math.abs(wantedArmPosition.getElevatorLength() - this.getElevatorLength()) <= ELEVATOR_ALLOWED_LENGTH_ERROR_METERS;
    }

    public boolean isOnTarget() {
        return isWristOnTarget() && isElevatorOnTarget();
    }

    public void updateElevatorPID(PIDValues pidValues) {
        elevatorIO.updatePID(pidValues);
    }

    public void updateWristPID(PIDValues pidValues) {
        wristIO.updatePID(pidValues);
    }

    public double getElevatorLength() {
        return elevatorInputs.elevatorMasterInputs.getMotorValue().getAsDouble();
    }

    public double getElevatorVelocity() {
        return elevatorInputs.elevatorMasterInputs.getMotorAngularVelocityRadPerSec();
    }

    public double getElevatorAcceleration() {
        return elevatorInputs.elevatorMasterInputs.getMotorAngularAccelerationRadPerSecSquared();
    }

    public double getWristAngle() {
        return wristInputs.wristMotorInputs.getMotorValue().getAsDouble();
    }

    public double getWristVelocity() {
        return wristInputs.wristMotorInputs.getMotorAngularVelocityRadPerSec();
    }

    public double getWristAcceleration() {
        return wristInputs.wristMotorInputs.getMotorAngularAccelerationRadPerSecSquared();
    }

    private static Arm instance;

    public static Arm getInstance() {
        return instance;
    }

    public static void init(WristIO wristIO, ElevatorIO elevatorIO) {
        if (instance == null) {
            instance = new Arm(wristIO, elevatorIO);
        }
    }
}

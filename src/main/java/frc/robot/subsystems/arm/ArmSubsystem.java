package frc.robot.subsystems.arm;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.arm.elevator.ElevatorIO;
import frc.robot.subsystems.arm.wrist.WristIO;
import frc.robot.utils.ArmPosition;
import org.littletonrobotics.junction.Logger;

import static frc.robot.subsystems.arm.elevator.ElevatorConstants.ELEVATOR_TOLERANCE;
import static frc.robot.subsystems.arm.wrist.WristConstants.SimulationWRISTConstants.PITCH_TOLERANCE;

public class ArmSubsystem extends SubsystemBase {

    private ArmPosition wantedArmPose;

    private final ElevatorIO.ElevatorIOInputs elevatorInputs;
    private final WristIO.WristIOInputs wristInputs;
    private final ElevatorIO elevatorIO;
    private final WristIO wristIO;

    private boolean isElevatorHomed;

    public enum WantedState {
        HOME,
        IDLE,
        MOVE_TO_POSITION,
    }

    private enum SystemState {
        HOMING_ELEVATOR,
        IDLING,
        MOVING_TO_POSITION
    }

    // TODO Dedicated shuffleboard that changes the States and WantedArmPosition
    private WantedState wantedState = WantedState.IDLE;
    private WantedState previousWantedState = WantedState.IDLE;
    private SystemState systemState = SystemState.IDLING;

    @Override
    public void periodic() {
        double startTime = Timer.getFPGATimestamp();
        elevatorIO.updateInputs(elevatorInputs);
        wristIO.updateInputs(wristInputs);
        Logger.recordOutput("Subsystems/Arm/Elevator/MasterMotorTemp", elevatorInputs.elevatorMotorMasterTemp);
        Logger.recordOutput("Subsystems/Arm/Elevator/Height", elevatorInputs.elevatorLength);
        Logger.recordOutput("Subsystems/Arm/Wrist/MotorTemp", wristInputs.wristMotorTemp);
        Logger.recordOutput("Subsystems/Arm/Wrist/Angle", wristInputs.wristAngle);

        systemState = handleStateTransitions();

        double wantedLengthMeters;
        double wantedWristAngle;

        if (wantedArmPose != null) {
            wantedLengthMeters = wantedArmPose.getElevatorLengthMeters();
            wantedWristAngle = wantedArmPose.getWristAngle();
            Logger.recordOutput("Subsystems/Arm/WantedLengthMeters", wantedLengthMeters);
            Logger.recordOutput("Subsystems/Arm/WantedWristAngle", wantedWristAngle);
        }

        applyStates();

        previousWantedState = this.wantedState;

        // TODO we need to create a function to convert seconds to miliseconds
        Logger.recordOutput("Subsystems/Arm/latencyPeriodicMs", (Timer.getFPGATimestamp() - startTime) * 1000);
    }

    public SystemState handleStateTransitions() {
        switch (wantedState) {
            case HOME:
                if (previousWantedState != WantedState.HOME) {
                    isElevatorHomed = false;
                }

                if (DriverStation.isEnabled()) {
                    if (elevatorInputs.isElevatorMicroSwitchPressed) {
                        elevatorIO.resetElevatorPosition(0);
                        setWantedState(WantedState.IDLE);
                        isElevatorHomed = true;

                        return SystemState.IDLING;
                    }
                }
            case IDLE:
                return SystemState.IDLING;
            case MOVE_TO_POSITION:
                return SystemState.MOVING_TO_POSITION;
        }

        return SystemState.IDLING;
    }

    public void applyStates() {
        switch (systemState) {
            case HOMING_ELEVATOR:
                elevatorIO.setDutyCycle(-0.07);
                break;
            case IDLING:
                elevatorIO.setDutyCycle(0);
                wristIO.setDutyCycle(0);
                break;
            case MOVING_TO_POSITION:
                elevatorIO.setTargetLength(wantedArmPose.getElevatorLengthMeters());
                wristIO.setTargetAngle(wantedArmPose.getWristAngle());
                break;
        }
    }


    // TODO create a observer class for logging
    private void toLog() {

    }

    public void setWantedState(WantedState wantedState) {
        this.wantedState = wantedState;
    }

    public void setWantedState(WantedState wantedState, ArmPosition armPosition) {
        this.wantedState = wantedState;
        this.wantedArmPose = armPosition;
    }

    private ArmSubsystem(ElevatorIO elevatorIO, WristIO wristIO) {
        this.elevatorIO = elevatorIO;
        this.wristIO = wristIO;

        elevatorInputs = new ElevatorIO.ElevatorIOInputs();
        wristInputs = new WristIO.WristIOInputs();
        wantedArmPose = ArmPoseConstants.ZEROED;
    }


    public boolean hasHomeCompleted() {
        return isElevatorHomed;
    }

    public double getCurrentElevatorLengthInMeters() {
        return elevatorInputs.elevatorLength;
    }

    public double getCurrentWristAngle() {
        return wristInputs.wristAngle;
    }

    public ArmPosition getWantedArmPose() {
        return wantedArmPose;
    }

    public boolean reachedSetpoint() {
        return MathUtil.isNear(
                wantedArmPose.getElevatorLengthMeters(),
                elevatorInputs.elevatorLength,
                ELEVATOR_TOLERANCE)
                && MathUtil.isNear(
                wantedArmPose.getWristAngle(),
                wristInputs.wristAngle,
                PITCH_TOLERANCE);
    }

    public boolean reachedSetpoint(ArmPosition armPosition) {
        return MathUtil.isNear(
                armPosition.getElevatorLengthMeters(),
                elevatorInputs.elevatorLength,
                ELEVATOR_TOLERANCE)
                && MathUtil.isNear(
                armPosition.getWristAngle(),
                wristInputs.wristAngle,
                PITCH_TOLERANCE);
    }

    private static ArmSubsystem instance;

    public static ArmSubsystem getInstance() {
        return instance;
    }

    public static void init(ElevatorIO elevatorIO, WristIO wristIO) {
        instance = new ArmSubsystem(elevatorIO, wristIO);
    }
}

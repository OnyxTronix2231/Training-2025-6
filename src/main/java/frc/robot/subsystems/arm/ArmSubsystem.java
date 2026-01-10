package frc.robot.subsystems.arm;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.lib.PID.PIDValues;
import frc.robot.subsystems.arm.elevator.ElevatorIO;
import frc.robot.subsystems.arm.elevator.ElevatorIOSimulation;
import frc.robot.subsystems.arm.wrist.WristIO;
import org.littletonrobotics.junction.Logger;

import static frc.robot.subsystems.arm.elevator.ElevatorConstants.ELEVATOR_PID_VALUES;
import static frc.robot.subsystems.arm.elevator.ElevatorConstants.SIMULATED_ELEVATOR_PID_VALUES;
import static frc.robot.subsystems.arm.wrist.WristConstants.SIMULATED_WRIST_PID_VALUES;
import static frc.robot.subsystems.arm.wrist.WristConstants.WRIST_PID_VALUES;

public class ArmSubsystem extends SubsystemBase {
    private final ElevatorIO elevatorIO;
    private final ElevatorIO.ElevatorIOInputs elevatorIOInputs;

    private final WristIO wristIO;
    private final WristIO.WristIOInputs wristIOInputs;

    enum WantedState {
        IDLE,
        MOVE_TO_POSITION
    }

    enum SystemState {
        IDLING,
        MOVING_TO_POSITION
    }

    private WantedState wantedState;
    private SystemState systemState;
    private SystemState previousSystemState;

    private ArmPosition wantedArmPosition;
    private ArmPosition previousWantedArmPosition;

    public ArmSubsystem(ElevatorIO elevatorIO, WristIO wristIO) {
        this.wantedState = WantedState.IDLE;
        this.systemState = SystemState.IDLING;
        this.previousSystemState = systemState;

        this.wantedArmPosition = ArmPositionConstants.ZERO_POSITION;
        this.previousWantedArmPosition = wantedArmPosition.createAClone();

        this.elevatorIO = elevatorIO;
        this.elevatorIOInputs = new ElevatorIO.ElevatorIOInputs();

        this.wristIO = wristIO;
        this.wristIOInputs = new WristIO.WristIOInputs();

        this.wristIO.updateInputs(wristIOInputs);
        this.elevatorIO.updateInputs(elevatorIOInputs);

    }

    public void setWantedState(WantedState wantedState) {
        this.wantedState = wantedState;
    }

    public void setWantedState(WantedState wantedState, ArmPosition position){
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
        return elevatorIOInputs.elevatorHeight;
    }

    public double getWristAngle() {
        return wristIOInputs.wristAngle;
    }

    public boolean isLimitSwitchPressed() {
        return elevatorIOInputs.isLimitSwitchPressed;
    }

    public double getWristVelocity() {
        return wristIOInputs.motorInputs.getMotorAngularVelocityRadPerSec();
    }

    public double getWristAcceleration() {
        return wristIOInputs.motorInputs.getMotorAngularAccelerationRadPerSecSquared();
    }

    public double getElevatorVelocity() {
        return elevatorIOInputs.masterMotorInputs.getMotorAngularVelocityRadPerSec();
    }

    public double getElevatorAcceleration() {
        return elevatorIOInputs.masterMotorInputs.getMotorAngularAccelerationRadPerSecSquared();
    }

    public PIDValues getElevatorPIDValues(boolean isSimulated) {
        return isSimulated ? SIMULATED_ELEVATOR_PID_VALUES : ELEVATOR_PID_VALUES;
    }

    public PIDValues getWristPIDValues(boolean isSimulated) {
        return isSimulated ? SIMULATED_WRIST_PID_VALUES : WRIST_PID_VALUES;
    }

    @Override
    public void periodic() {
        double timeStamp = Timer.getFPGATimestamp();
        this.elevatorIO.updateInputs(elevatorIOInputs);
        this.wristIO.updateInputs(wristIOInputs);
        systemState = handleStateTransition();
        applySystemState();
        previousSystemState = systemState;

        Logger.recordOutput("Subsystems/Arm/WantedState", wantedState);
        Logger.recordOutput("Subsystems/Arm/SystemState", systemState);

        Logger.recordOutput("Subsystems/Arm/WristAngle", wristIOInputs.wristAngle);
        Logger.recordOutput("Subsystems/Arm/ElevatorLength", elevatorIOInputs.elevatorHeight);

        elevatorIOInputs.masterMotorInputs.log();
        elevatorIOInputs.followerMotorInputs.log();
        Logger.recordOutput("Subsystems/Arm/Elevator/LimitSwitch", elevatorIOInputs.isLimitSwitchPressed);

        wristIOInputs.motorInputs.log();

        previousWantedArmPosition = wantedArmPosition.createAClone();

        Logger.recordOutput("Subsystems/Arm/PeriodicTime", timeStamp - Timer.getFPGATimestamp());
    }

    private SystemState handleStateTransition() {
        switch (wantedState) {
            case IDLE:
                return SystemState.IDLING;
            case MOVE_TO_POSITION:
                return SystemState.MOVING_TO_POSITION;
        }
        return SystemState.IDLING;
    }

    private void applySystemState() {
        switch (systemState) {
            case IDLING:
                idling();
                break;
            case MOVING_TO_POSITION:
                move_to_position(wantedArmPosition);
                break;
        }
    }

    private void idling() {
        elevatorIO.stop();
        wristIO.stop();
    }

    private void move_to_position(ArmPosition position) {
        elevatorIO.moveElevatorToHeight(position.getElevatorHeight());
        wristIO.moveWristToAngle(position.getWristAngle());
    }

    public void toggleLimitSwitchSimulated() {
        ElevatorIOSimulation.SimulatedSensors.isLimitSwitchPressed = !ElevatorIOSimulation.SimulatedSensors.isLimitSwitchPressed;
    }

    public void updateElevatorPID(double kP, double kI, double kD, double kG) {
        elevatorIO.updatePID(kP, kI, kD, kG);
    }

    public void updateWristPID(double kP, double kI, double kD, double kG) {
        wristIO.updatePID(kP, kI, kD, kG);
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

    public void setWristDutyCycle(double dC) {
        wristIO.setDutyCycle(dC);
    }
}

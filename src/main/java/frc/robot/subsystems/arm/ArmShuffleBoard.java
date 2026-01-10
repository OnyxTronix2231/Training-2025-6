package frc.robot.subsystems.arm;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.lib.PID.PIDEntries;
import frc.robot.lib.PID.PIDValues;
import frc.robot.lib.PhysicalTelemetryEntries;

import java.util.function.DoubleSupplier;

public class ArmShuffleBoard {
    String name = "Arm";
    ShuffleboardTab tab = Shuffleboard.getTab(name);
    PhysicalTelemetryEntries wrist = new PhysicalTelemetryEntries(
            name, "Wrist",
            () -> ArmSubsystem.getInstance().getWristAngle(),
            () -> ArmSubsystem.getInstance().getWristVelocity(),
            () -> ArmSubsystem.getInstance().getWristAcceleration());

    PhysicalTelemetryEntries elevator = new PhysicalTelemetryEntries(
            name, "Elevator",
            () -> ArmSubsystem.getInstance().getElevatorLength(),
            () -> ArmSubsystem.getInstance().getElevatorVelocity(),
            () -> ArmSubsystem.getInstance().getElevatorAcceleration());

    PIDEntries wristPID = new PIDEntries(
            name, "Wrist",
            ArmSubsystem.getInstance().getWristPIDValues(true));

    PIDEntries elevatorPID = new PIDEntries(
            name, "Elevator",
            ArmSubsystem.getInstance().getElevatorPIDValues(true));

    public ArmShuffleBoard() {

        tab.addString("Wanted State", () -> ArmSubsystem.getInstance().getWantedState().toString());
        tab.addString("System State", () -> ArmSubsystem.getInstance().getSystemState().toString());

        tab.add("UpdateElevatorPID", new InstantCommand(() -> ArmSubsystem.getInstance().updateElevatorPID(elevatorPID.getPIDValues().getkP(), elevatorPID.getPIDValues().getkI(), elevatorPID.getPIDValues().getkD(), elevatorPID.getPIDValues().getkG())));
        tab.add("UpdateWristPID", new InstantCommand(() -> ArmSubsystem.getInstance().updateWristPID(wristPID.getPIDValues().getkP(), wristPID.getPIDValues().getkI(), wristPID.getPIDValues().getkD(), wristPID.getPIDValues().getkG())));

         GenericEntry ELEVATOR_HEIGHT = tab.add("Elevator Height", ArmSubsystem.getInstance().getElevatorLength()).getEntry();
         GenericEntry WRIST_ANGLE = tab.add("Wrist Angle", ArmSubsystem.getInstance().getWristAngle()).getEntry();

        tab.add("IDLE", new InstantCommand(() -> ArmSubsystem.getInstance().setWantedState(ArmSubsystem.WantedState.IDLE)));
        tab.add("MOVE_TO_POSITION", new InstantCommand(() -> ArmSubsystem.getInstance().setWantedState(ArmSubsystem.WantedState.MOVE_TO_POSITION, new ArmPosition(WRIST_ANGLE.getDouble(ArmSubsystem.getInstance().getWristAngle()), ELEVATOR_HEIGHT.getDouble(ArmSubsystem.getInstance().getElevatorLength())))));

        tab.addBoolean("elevatorLimitSwitch", () -> ArmSubsystem.getInstance().isLimitSwitchPressed());

        tab.add("ToggleSimulatedLimitSwitch", new InstantCommand(() -> ArmSubsystem.getInstance().toggleLimitSwitchSimulated()));

    }

}

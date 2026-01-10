package frc.robot.subsystems.arm;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import frc.robot.lib.PID.PIDEntries;
import frc.robot.lib.PID.PIDValues;
import frc.robot.lib.PhysicalTelemetryEntries;

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
            () -> ArmSubsystem.getInstance().getWristPIDValues(),
            (PIDValues values) -> ArmSubsystem.getInstance().setWristPIDValues(values)
        );

        PIDEntries elevatorPID = new PIDEntries(
                name, "Elevator",
                () -> ArmSubsystem.getInstance().getElevatorPIDValues(),
                (PIDValues values) -> ArmSubsystem.getInstance().setElevatorPIDValues(values)
        );

        tab.addString("Wanted State", () -> ArmSubsystem.getInstance().getWantedState().toString());
        tab.addString("System State", () -> ArmSubsystem.getInstance().getSystemState().toString());

        tab.add("IDLE", new InstantCommand(() -> ArmSubsystem.getInstance().setWantedState(ArmSubsystem.WantedState.IDLE)));

        
        
}

package frc.robot.subsystems.arm;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.lib.PID.PIDEntries;
import frc.robot.lib.PhysicalTelemetryEntries;
import frc.robot.subsystems.arm.elevator.ElevatorIOSimulation;

import static frc.robot.subsystems.arm.elevator.ElevatorConstants.SIMULATION_ELEVATOR_PID_VALUES;
import static frc.robot.subsystems.arm.wrist.WristConstants.SIMULATION_WRIST_PID_VALUES;

public class ArmShuffleboard {
    public ArmShuffleboard() {
        String name = "arm";
        ShuffleboardTab tab = Shuffleboard.getTab(name);

        PhysicalTelemetryEntries wrist = new PhysicalTelemetryEntries(name, "Wrist",
            () -> Arm.getInstance().getWristAngle(),
            () -> Arm.getInstance().getWristVelocity(),
            () -> Arm.getInstance().getWristAcceleration());

        PIDEntries wristEntries = new PIDEntries(name, "Wrist", SIMULATION_WRIST_PID_VALUES);
        tab.add("Update Wrist PID", new InstantCommand(() -> Arm.getInstance().updateWristPID(wristEntries.getPIDValues())));

        PhysicalTelemetryEntries elevator = new PhysicalTelemetryEntries(name, "Elevator",
            () -> Arm.getInstance().getElevatorLength(),
            () -> Arm.getInstance().getElevatorVelocity(),
            () -> Arm.getInstance().getElevatorAcceleration());

        PIDEntries elevatorEntries = new PIDEntries(name, "Elevator", SIMULATION_ELEVATOR_PID_VALUES);
        tab.add("Update elevator PID", new InstantCommand(() -> Arm.getInstance().updateElevatorPID(elevatorEntries.getPIDValues())));

        tab.addString("Wanted state", () -> Arm.getInstance().getWantedState().toString());
        tab.addString("System state", () -> Arm.getInstance().getSystemState().toString());

        GenericEntry targetAngle = tab.add("Target wrist angle", 0).getEntry();
        GenericEntry targetLength = tab.add("Target elevator length", 0).getEntry();

        tab.add("idle", new InstantCommand(() -> Arm.getInstance().setWantedState(Arm.WantedState.IDLE)));

        tab.add("Move to position", new InstantCommand(() -> Arm.getInstance().setWantedState(Arm.WantedState.MOVE_TO_POSITION,
            new ArmPosition(targetLength.getDouble(0), targetAngle.getDouble(0)))));

        tab.add("Toggle limit switch", new InstantCommand(() -> ElevatorIOSimulation.setSwitchValue(!ElevatorIOSimulation.getSwitchValue())));
        tab.addBoolean("Is limit switch pressed", () -> Arm.getInstance().isElevatorSwitchPressed());

        tab.addBoolean("System on target", () -> Arm.getInstance().isOnTarget());
        tab.addBoolean("Elevator on target", () -> Arm.getInstance().isElevatorOnTarget());
        tab.addBoolean("Wrist on target", () -> Arm.getInstance().isWristOnTarget());
    }
}

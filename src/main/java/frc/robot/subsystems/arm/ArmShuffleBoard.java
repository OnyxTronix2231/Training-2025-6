package frc.robot.subsystems.arm;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.lib.PID.PIDEntries;
import frc.robot.lib.PhysicalTelemetryEntries;

public class ArmShuffleBoard {
    String name = "Arm";
    ShuffleboardTab tab = Shuffleboard.getTab(name);
    PhysicalTelemetryEntries wrist = new PhysicalTelemetryEntries(
            name, "Wrist",
            () -> Arm.getInstance().getWristAngle(),
            () -> Arm.getInstance().getWristVelocity(),
            () -> Arm.getInstance().getWristAcceleration());

    PhysicalTelemetryEntries elevator = new PhysicalTelemetryEntries(
            name, "Elevator",
            () -> Arm.getInstance().getElevatorLength(),
            () -> Arm.getInstance().getElevatorVelocity(),
            () -> Arm.getInstance().getElevatorAcceleration());

    PIDEntries wristPID = new PIDEntries(
            name, "Wrist",
            Arm.getInstance().getWristPIDValues(true));

    PIDEntries elevatorPID = new PIDEntries(
            name, "Elevator",
            Arm.getInstance().getElevatorPIDValues(true));

    public ArmShuffleBoard() {

        tab.addString("Wanted State", () -> Arm.getInstance().getWantedState().toString());
        tab.addString("System State", () -> Arm.getInstance().getSystemState().toString());

        tab.add("UpdateElevatorPID", new InstantCommand(() -> Arm.getInstance().updateElevatorPID(elevatorPID.getPIDValues().getkP(), elevatorPID.getPIDValues().getkI(), elevatorPID.getPIDValues().getkD(), elevatorPID.getPIDValues().getkG())));
        tab.add("UpdateWristPID", new InstantCommand(() -> Arm.getInstance().updateWristPID(wristPID.getPIDValues().getkP(), wristPID.getPIDValues().getkI(), wristPID.getPIDValues().getkD(), wristPID.getPIDValues().getkG())));

         GenericEntry ELEVATOR_HEIGHT = tab.add("Elevator Height", Arm.getInstance().getElevatorLength()).getEntry();
         GenericEntry WRIST_ANGLE = tab.add("Wrist Angle", Arm.getInstance().getWristAngle()).getEntry();

        tab.add("IDLE", new InstantCommand(() -> Arm.getInstance().setWantedState(Arm.WantedState.IDLE)));
        tab.add("MOVE_TO_POSITION", new InstantCommand(() -> Arm.getInstance().setWantedState(Arm.WantedState.MOVE_TO_POSITION, new ArmPosition(WRIST_ANGLE.getDouble(Arm.getInstance().getWristAngle()), ELEVATOR_HEIGHT.getDouble(Arm.getInstance().getElevatorLength())))));

        tab.addBoolean("elevatorLimitSwitch", () -> Arm.getInstance().isLimitSwitchPressed());

        tab.add("ToggleSimulatedLimitSwitch", new InstantCommand(() -> Arm.getInstance().toggleLimitSwitchSimulated()));

    }

}

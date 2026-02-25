package frc.robot.subsystems.arm.wrist;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.lib.PID.PIDEntries;
import frc.robot.lib.PhysicalTelemetryEntries;

import static frc.robot.subsystems.arm.wrist.WristConstants.*;

public class WristShuffleboard {
    public WristShuffleboard() {
        ShuffleboardTab tab = Shuffleboard.getTab("Wrist");

        PhysicalTelemetryEntries wrist = new PhysicalTelemetryEntries(
                "Wrist",
                "Motor",
                () -> Wrist.getInstance().getAngle(),
                () -> Wrist.getInstance().getVelocity(),
                () -> Wrist.getInstance().getAcceleration()
        );

        PIDEntries fastPIDEntries = new PIDEntries(
                "Wrist",
                "fast_PID",
                WRIST_PID_VALUES_FAST
        );

        PIDEntries slowPIDEntries = new PIDEntries(
                "Wrist",
                "slow_PID",
                WRIST_PID_VALUES_SLOW
        );

        tab.add("update slow PID (slot 1)", new InstantCommand(() -> Wrist.getInstance().updatePIDSlot1(slowPIDEntries.getPIDValues())));

        tab.add("update fast PID (slot 0)", new InstantCommand(() -> Wrist.getInstance().updatePIDSlot0(fastPIDEntries.getPIDValues())));

        tab.add("Open", new InstantCommand(() -> Wrist.getInstance().setWantedState(Wrist.WantedState.OPEN)));
        tab.add("Close", new InstantCommand(() -> Wrist.getInstance().setWantedState(Wrist.WantedState.CLOSE)));
        tab.add("Idle", new InstantCommand(() -> Wrist.getInstance().setWantedState(Wrist.WantedState.IDLE)));

        tab.add("Brake", new InstantCommand(() -> Wrist.getInstance().setBrakeMode()));
        tab.add("Coast", new InstantCommand(() -> Wrist.getInstance().setCoastMode()));

        tab.addBoolean("isOpened", () -> Wrist.getInstance().isOpened());
    }
}

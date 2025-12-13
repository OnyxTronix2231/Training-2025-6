package frc.robot.subsystems.wrist;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class WristShuffleboard {
    public WristShuffleboard() {
        ShuffleboardTab tab = Shuffleboard.getTab("wrist");
        GenericEntry targetAngle = tab.add("target angle", 0).getEntry();

        tab.addString("Wanted State", () -> Wrist.getInstance().getWantedState().toString());
        tab.addDouble("Wrist Angle", () -> Wrist.getInstance().getWristAngle());
        tab.addDouble("Cancoder Position", () -> Wrist.getInstance().getEncoderPosition());

        tab.add("Move to angle", new InstantCommand(() -> Wrist.getInstance().setWantedState(Wrist.WantedState.MOVE_TO_ANGLE, targetAngle.getDouble(0))));
        tab.add("Move forwards", new InstantCommand(() -> Wrist.getInstance().setWantedState(Wrist.WantedState.MOVE_FORWARD)));
        tab.add("Move backwards", new InstantCommand(() -> Wrist.getInstance().setWantedState(Wrist.WantedState.MOVE_BACKWARD)));
        tab.add("Idle", new InstantCommand(() -> Wrist.getInstance().setWantedState(Wrist.WantedState.IDLE)));
    }
}

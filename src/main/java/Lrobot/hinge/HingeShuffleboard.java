package Lrobot.hinge;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class HingeShuffleboard {
    public HingeShuffleboard() {
        ShuffleboardTab tab = Shuffleboard.getTab("Hinge");

        tab.addString("Wanted State", () -> Hinge.getInstance().getWantedState().toString());
        tab.addDouble("Hinge Angle", Hinge.getInstance()::getHingeAngle);
        tab.add("Open Hinge", new InstantCommand(() -> Hinge.getInstance().setWantedState(Hinge.WantedState.OPEN)));
        tab.add("Close Hinge", new InstantCommand(() -> Hinge.getInstance().setWantedState(Hinge.WantedState.CLOSE)));

        GenericEntry hingeAngle = tab.add("Wanted Hinge Angle", Hinge.getInstance().getHingeAngle()).getEntry();
        tab.add("Set hinge angle", new InstantCommand(() -> Hinge.getInstance().setWantedAngle(hingeAngle.getDouble(0))));
    }
}

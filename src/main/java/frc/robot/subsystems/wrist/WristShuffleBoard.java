package frc.robot.subsystems.wrist;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class WristShuffleBoard {

    public WristShuffleBoard() {
        ShuffleboardTab tab = Shuffleboard.getTab("wrist");
        GenericEntry targetAngle = tab.add("target angle", 0).getEntry();

        tab.addDouble("Wrist angle", () -> Wrist.getInstance().getWristAngle());

        tab.addString("Wanted State", () -> Wrist.getInstance().getWantedState().toString());

        tab.add("Idle",new InstantCommand(() -> Wrist.getInstance().setWantedState(Wrist.WantedState.IDLE)));
        tab.add("Close", new InstantCommand(() -> Wrist.getInstance().setWantedState(Wrist.WantedState.CLOSE)));
        tab.add("Open", new InstantCommand(() -> Wrist.getInstance().setWantedState(Wrist.WantedState.OPEN)));

        tab.add("move to position", new InstantCommand(() -> Wrist.getInstance().setWantedState(
                Wrist.WantedState.MOVE_TO_POSITION, targetAngle.getDouble(0))));


    }
}

package frc.robot.subsystems.wrist;

import Lrobot.elevator.Elevator;
import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class WristShuffleboard {

    public WristShuffleboard() {

            ShuffleboardTab tab = Shuffleboard.getTab("wrist");
            GenericEntry targetAngle = tab.add("TargetAngle", 0).getEntry();

            tab.add("MoveToAngle", new InstantCommand(()-> Wrist.getInstance().setWantedState(Wrist.WantedState.MOVE_TO_ANGLE,targetAngle.getDouble(0))));
            tab.add("Raise", new InstantCommand(() -> Wrist.getInstance().setWantedState(Wrist.WantedState.RAISE)));
            tab.add("Lower", new InstantCommand(() -> Wrist.getInstance().setWantedState(Wrist.WantedState.LOWER)));
            tab.add("Idle", new InstantCommand(() -> Wrist.getInstance().setWantedState(Wrist.WantedState.IDLE)));
            tab.addString("WantedState", ()-> Wrist.getInstance().getWantedState().toString());
            tab.addDouble("WristAngle", ()-> Wrist.getInstance().getWristAngle());

    }
}

package Lrobot.hinge;


import Lrobot.elevator.Elevator;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class HingeShuffleboard {

    public HingeShuffleboard() {

        ShuffleboardTab Tab = Shuffleboard.getTab("Hinge");

        Tab.add("Close", new InstantCommand(()-> HingeJava.getInstance().setWantedState(HingeJava.WantedState.CLOSE)));
        Tab.add("Open", new InstantCommand(()-> HingeJava.getInstance().setWantedState(HingeJava.WantedState.OPEN)));


    }
}


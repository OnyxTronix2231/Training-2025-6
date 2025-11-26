package Lrobot.Hinge;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class HingeShuffleborad {

    public HingeShuffleborad() {
        ShuffleboardTab tab = Shuffleboard.getTab("hinge");
        tab.add("Open",new InstantCommand(()->Hinge.getInstance().setWantedState(Hinge.WantedState.OPEN)));
        tab.add("Close",new InstantCommand(()->Hinge.getInstance().setWantedState(Hinge.WantedState.CLOSE)));
    }

}

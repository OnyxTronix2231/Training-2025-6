package Lrobot.elevator;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import org.littletonrobotics.junction.Logger;

public class ElevatorShuffleBored {
    public ElevatorShuffleBored() {
        ShuffleboardTab tab = Shuffleboard.getTab("elevator");

        tab.addDouble("Elevator length", ()-> Elevator.getInstance().getElevatorLength());
        tab.addInteger("test",()-> Elevator.getInstance().test());
        tab.addString("wanted state",()-> Elevator.getInstance().getWantedState().toString());
    }
}

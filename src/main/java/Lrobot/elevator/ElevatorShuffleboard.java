package Lrobot.elevator;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class ElevatorShuffleboard {

    public ElevatorShuffleboard()
    {
        ShuffleboardTab tab = Shuffleboard.getTab("elevator");
        GenericEntry targetLength = tab.add("target length", 0).getEntry();

        tab.addDouble("Elevator length", ()-> Elevator.getInstance().getElevatorLength());
        tab.add("Toggle Microswitch",new InstantCommand(()-> Elevator.getInstance().setMicroswitch(!ElevatorIOSimulation.SimulatedSensors.isLimitSwitchPressed)));
        tab.addString("Wanted State", ()->Elevator.getInstance().getWantedState().toString());

        tab.add("Close", new InstantCommand(() -> Elevator.getInstance().setWantedState(Elevator.WantedState.CLOSE)));
        tab.add("Open", new InstantCommand(() -> Elevator.getInstance().setWantedState(Elevator.WantedState.OPEN)));
        tab.add("move to position", new InstantCommand(() -> Elevator.getInstance().setWantedState(
                Elevator.WantedState.MOVE_TO_POSITION,targetLength.getDouble(0))));

    }

}

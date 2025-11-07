package Lrobot.elevator;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class ElevatorShuffleBoard {
    public ElevatorShuffleBoard() {
        ShuffleboardTab tab = Shuffleboard.getTab("Elevator");
        tab.addDouble("elevator length", () -> Elevator.getInstance().getElevatorLength());
        tab.addString("wanted state", () -> Elevator.getInstance().getWantedState().toString());
        tab.addString("system state", () -> Elevator.getInstance().getSystemState().toString());

        GenericEntry dutyCycle = tab.add("target Val", 0).getEntry();

        tab.add("move elevator to position", new InstantCommand(() -> Elevator.getInstance().setWantedState(
                Elevator.WantedState.MOVE_DUTY_CYCLE, dutyCycle.getDouble(0))));

        GenericEntry targetLength = tab.add("target length", 0).getEntry();
        tab.add("move elevator to position", new InstantCommand(() -> Elevator.getInstance().setWantedState(Elevator.WantedState.MOVE_TO_POSITION, targetLength.getDouble(0))));

        GenericEntry pElevator = tab.add("p Elevator", 0).getEntry();
        GenericEntry iElevator = tab.add("i Elevator", 0).getEntry();
        GenericEntry dElevator = tab.add("d Elevator", 0).getEntry();
        tab.add("update elevator pid", new InstantCommand(() -> Elevator.getInstance().updateElevatorPID(pElevator.getDouble(0), iElevator.getDouble(0), dElevator.getDouble(0))));
        tab.addDouble("elevator err", () -> (targetLength.getDouble(0) - Elevator.getInstance().getElevatorLength()));


    }
}

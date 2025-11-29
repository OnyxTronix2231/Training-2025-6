package Lrobot.elevator;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class ElevatorShuffleboard {
    public ElevatorShuffleboard() {
        ShuffleboardTab tab = Shuffleboard.getTab("Elevator");

        tab.addDouble("Elevator Length", () -> Elevator.getInstance().getElevatorLength());
        tab.addInteger("Test", () -> Elevator.getInstance().five());
        tab.addString("Wanted State", () -> Elevator.getInstance().getWantedState().toString());
        tab.add("Toggle Elevator", new InstantCommand(() -> Elevator.getInstance().setWantedState(Elevator.WantedState.TOGGLE)));
        tab.add("Lock Elevator", new InstantCommand(() -> Elevator.getInstance().setLocked(!Elevator.getInstance().isLocked())));
        tab.addBoolean("Locked", () -> Elevator.getInstance().isLocked());
        
    }
}

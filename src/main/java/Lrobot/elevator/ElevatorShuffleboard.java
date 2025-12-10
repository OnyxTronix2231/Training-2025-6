package Lrobot.elevator;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class ElevatorShuffleboard {
    public ElevatorShuffleboard() {
        ShuffleboardTab tab = Shuffleboard.getTab("Elevator");

        tab.addDouble("Elevator Length", () -> Elevator.getInstance().getElevatorLength());
        tab.addString("Wanted State", () -> Elevator.getInstance().getWantedState().toString());
        tab.add("Toggle Elevator", new InstantCommand(() -> Elevator.getInstance().setWantedState(Elevator.WantedState.TOGGLE)));
        tab.addBoolean("First Switch Pressed", () -> Elevator.getInstance().isFirstSwitchPressed());
        tab.add("Toggle First Switch", new InstantCommand(() -> ElevatorIOSimulation.setFirstSwitchValue(!ElevatorIOSimulation.getFirstSwitchValue())));
        tab.addBoolean("Second Switch Pressed", () -> Elevator.getInstance().isSecondSwitchPressed());
        tab.add("Toggle Second Switch", new InstantCommand(() -> ElevatorIOSimulation.setSecondSwitchValue(!ElevatorIOSimulation.getSecondSwitchValue())));
        tab.add("Move Automatically", new InstantCommand(() -> Elevator.getInstance().setWantedState(Elevator.WantedState.AUTO)));
    }
}

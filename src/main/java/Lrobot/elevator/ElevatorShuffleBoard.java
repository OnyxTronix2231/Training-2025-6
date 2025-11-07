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

        tab.add("move elevator to position", new InstantCommand(() -> {
            Elevator.getInstance().setWantedState(
                    Elevator.WantedState.MOVE_DUTY_CYCLE);
            Elevator.getInstance().setDutyCycle(dutyCycle.getDouble(0));
        }));
    }
}

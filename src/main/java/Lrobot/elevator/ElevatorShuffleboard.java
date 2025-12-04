package Lrobot.elevator;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class ElevatorShuffleboard {

        public ElevatorShuffleboard() {

            ShuffleboardTab tab = Shuffleboard.getTab("Elevator");

            tab.addBoolean("Micro Switch Pressed", (()-> Elevator.getInstance().isMicroswitchPressed()));
            tab.add("Toggle", new InstantCommand(()-> ElevatorIOSimulation.setLimitSwitchValue(!Elevator.getInstance().isMicroswitchPressed())));
            tab.addDouble("Elevator length", ()-> Elevator.getInstance().getElevatorLength());
            tab.addString("Wanted State", ()-> Elevator.getInstance().getWantedState().toString());
            tab.add("Close", new InstantCommand(()-> Elevator.getInstance().setWantedState(Elevator.WantedState.CLOSE)));
            tab.add("Open", new InstantCommand(()-> Elevator.getInstance().setWantedState(Elevator.WantedState.OPEN)));
        }
    }

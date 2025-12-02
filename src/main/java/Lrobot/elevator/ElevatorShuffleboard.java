package Lrobot.elevator;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class ElevatorShuffleboard {

        public ElevatorShuffleboard() {

            ShuffleboardTab Tab = Shuffleboard.getTab("Elevator");

            Tab.addBoolean("Micro Switch Pressed", (()-> Elevator.getInstance().isMicroswitchPressed()));
            Tab.add("Toggle", new InstantCommand(()-> ElevatorIOSimulation.setLimitSwitchValue(!Elevator.getInstance().isMicroswitchPressed())));
            Tab.addDouble("Elevator length", ()-> Elevator.getInstance().getElevatorLength());
            Tab.addString("Wanted State", ()-> Elevator.getInstance().getWantedState().toString());
            Tab.add("Close", new InstantCommand(()-> Elevator.getInstance().setWantedState(Elevator.WantedState.CLOSE)));
            Tab.add("Open", new InstantCommand(()-> Elevator.getInstance().setWantedState(Elevator.WantedState.OPEN)));
        }
    }

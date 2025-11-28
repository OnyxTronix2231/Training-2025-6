package Lrobot.elevator;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class ElevatorShuffleboard {

        public ElevatorShuffleboard() {

            ShuffleboardTab Tab = Shuffleboard.getTab("Elevator");

            Tab.addDouble("Elevator length", ()-> Elevator.getInstance().getElevatorLength());
            Tab.addInteger("Test", ()-> Elevator.getInstance().five());
            Tab.addString("Wanted State", ()-> Elevator.getInstance().getWantedState().toString());
            Tab.add("Close", new InstantCommand(()-> Elevator.getInstance().setWantedState(Elevator.WantedState.CLOSE)));
            Tab.add("Open", new InstantCommand(()-> Elevator.getInstance().setWantedState(Elevator.WantedState.OPEN)));
        }
    }

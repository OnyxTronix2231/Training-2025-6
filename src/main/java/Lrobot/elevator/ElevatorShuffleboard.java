package Lrobot.elevator;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class ElevatorShuffleboard {

    public ElevatorShuffleboard()
    {
        ShuffleboardTab tab = Shuffleboard.getTab("elevator");

        tab.addDouble("Elevator length", ()-> Elevator.getInstance().getElevatorLength());
        tab.addBoolean("setMicroSwitchValue",() -> Elevator.getInstance().getLimitSwitchValue());
        tab.add("toggleMicroSwitch",new InstantCommand(() -> Elevator.getInstance().setLimitSwitchValue(!Elevator.getInstance().getLimitSwitchValue())));
        tab.add("Close", new InstantCommand(() -> Elevator.getInstance().setWantedState(Elevator.WantedState.CLOSE)));
        tab.add("Open", new InstantCommand(() -> Elevator.getInstance().setWantedState(Elevator.WantedState.OPEN)));
        tab.add("censor1",new InstantCommand(() -> Elevator.getInstance().isCensor1()));
        tab.add("censor2", new InstantCommand(() -> Elevator.getInstance().isCensor2()));



    }

}

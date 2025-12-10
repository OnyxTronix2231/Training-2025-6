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
        tab.add("setSensorsState", new InstantCommand(() -> Elevator.getInstance().setWantedState(Elevator.WantedState.SENSOR)));

        tab.addBoolean("sensor1Value",() -> Elevator.getInstance().isSensor1());
        tab.addBoolean("sensor2Value",() -> Elevator.getInstance().isSensor2());
        tab.add("setSensor1",new InstantCommand(() -> Elevator.getInstance().setSensor1Value(!Elevator.getInstance().isSensor1())));
        tab.add("setSensor2", new InstantCommand(() -> Elevator.getInstance().setSensor2Value(!Elevator.getInstance().isSensor2())));
        tab.addString("WantedState",() -> Elevator.getInstance().getWantedState().toString());


    }

}

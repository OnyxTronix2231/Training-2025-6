package frc.robot.subsystems.coralHolder;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class CoralHolderShuffleboard {
    public CoralHolderShuffleboard() {
        ShuffleboardTab tab = Shuffleboard.getTab("Coral Holder");

        tab.addNumber("Spin Speed", () -> CoralHolder.getInstance().getSpinSpeed());
        tab.addBoolean("Is outer sensor detecting", () -> CoralHolder.getInstance().isOuterSensorDetecting());
        tab.addBoolean("Is inner sensor detecting", () -> CoralHolder.getInstance().isInnerSensorDetecting());

        tab.add("Toggle outer sensor", new InstantCommand(() -> CoralHolderIOSimulation.SimulatedSensors.outerSensorDetecting = !CoralHolderIOSimulation.SimulatedSensors.outerSensorDetecting));
        tab.add("Toggle inner sensor", new InstantCommand(() -> CoralHolderIOSimulation.SimulatedSensors.innerSensorDetecting = !CoralHolderIOSimulation.SimulatedSensors.innerSensorDetecting));

        tab.addString("Wanted State", () -> CoralHolder.getInstance().getWantedState().toString());
        tab.addString("System State", () -> CoralHolder.getInstance().getSystemState().toString());

        tab.add("Intake", new InstantCommand(() -> CoralHolder.getInstance().setWantedState(CoralHolder.WantedState.INTAKE)));
        tab.add("Eject", new InstantCommand(() -> CoralHolder.getInstance().setWantedState(CoralHolder.WantedState.EJECT)));
        tab.add("Idle", new InstantCommand(() -> CoralHolder.getInstance().setWantedState(CoralHolder.WantedState.IDLE)));
    }
}

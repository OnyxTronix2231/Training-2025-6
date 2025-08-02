package frc.robot.utils;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Superstructure;

public class ControllerUtils {
    public static InstantCommand setWantedState(Superstructure.WantedSuperState state) {
        return new InstantCommand(() -> Superstructure.getInstance().setWantedSuperState(state));
    }

    public static InstantCommand setWantedStateAsToggle(Superstructure.WantedSuperState state) {
        if (Superstructure.getInstance().getWantedSuperState() == state)
            return new InstantCommand(() -> Superstructure.getInstance().setWantedSuperState(Superstructure.WantedSuperState.DEFAULT_STATE));
        return new InstantCommand(() -> Superstructure.getInstance().setWantedSuperState(state));
    }
}

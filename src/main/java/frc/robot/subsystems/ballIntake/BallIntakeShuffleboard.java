package frc.robot.subsystems.ballIntake;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class BallIntakeShuffleboard {
    public BallIntakeShuffleboard() {
        ShuffleboardTab tab = Shuffleboard.getTab("BallIntake");

        tab.addString("CurrentWantedState", () -> BallIntake.getInstance().getWantedState().toString());

        tab.add("IDLE", new InstantCommand(() -> BallIntake.getInstance().setWantedState(BallIntake.WantedState.IDLE)));
        tab.add("EJECT", new InstantCommand(() -> BallIntake.getInstance().setWantedState(BallIntake.WantedState.EJECT)));
        tab.add("INTAKE", new InstantCommand(() -> BallIntake.getInstance().setWantedState(BallIntake.WantedState.INTAKE)));

        tab.addBoolean("isLimitSwitchPressed", () -> BallIntake.getInstance().isLimitSwitchPressed());
    }
}

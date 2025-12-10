package frc.robot.subsystems.ballIntake;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class BallIntakeShuffleboard {
    public BallIntakeShuffleboard() {
        ShuffleboardTab tab = Shuffleboard.getTab("Ball Intake");

        tab.addBoolean("Limit Switch Pressed", () -> BallIntake.getInstance().isSwitchPressed());
        tab.addString("Wanted State", () -> BallIntake.getInstance().getWantedState().toString());

        tab.add("Intake", new InstantCommand(() -> BallIntake.getInstance().setWantedState(BallIntake.WantedState.INTAKE)));
        tab.add("Eject", new InstantCommand(() -> BallIntake.getInstance().setWantedState(BallIntake.WantedState.EJECT)));
        tab.add("Enter Idle", new InstantCommand(() -> BallIntake.getInstance().setWantedState(BallIntake.WantedState.IDLE)));
    }
}

package frc.robot.subsystems.ballIntake;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class BallIntakeShuffleBoard {
    public BallIntakeShuffleBoard() {
        ShuffleboardTab tab = Shuffleboard.getTab("BallIntake");
        tab.add("IDLE", new InstantCommand(()-> BallIntake.getInstance().setWantedState(BallIntake.WantedState.IDLE)));
        tab.add("INTAKE", new InstantCommand(()-> BallIntake.getInstance().setWantedState(BallIntake.WantedState.INTAKE)));
        tab.add("EJECT", new InstantCommand(()-> BallIntake.getInstance().setWantedState(BallIntake.WantedState.EJECT)));

        tab.addString("wantedState", ()->BallIntake.getInstance().getWantedState().toString());
    }
}

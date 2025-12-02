package frc.robot.subsystems;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class BallIntakeShuffleBoard {
    public ShuffleboardTab tab = Shuffleboard.getTab("BallIntake");

    private final BallIntake ballIntake = BallIntake.getInstance();

    public BallIntakeShuffleBoard() {
        tab.add("IDLE", new InstantCommand(() -> ballIntake.setWantedState(BallIntake.WantedStates.IDLE)));
        tab.add("INTAKE", new InstantCommand(() -> ballIntake.setWantedState(BallIntake.WantedStates.INTAKE)));
        tab.add("EJECT", new InstantCommand(() -> ballIntake.setWantedState(BallIntake.WantedStates.EJECT)));

        tab.addString("currentWantedState", () -> ballIntake.getWantedState().toString());

    }
}

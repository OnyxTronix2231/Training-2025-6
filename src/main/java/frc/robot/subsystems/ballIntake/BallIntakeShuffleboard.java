package frc.robot.subsystems.ballIntake;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class BallIntakeShuffleboard {

    public BallIntakeShuffleboard() {

        ShuffleboardTab tab = Shuffleboard.getTab("BallIntake");

        tab.add("Idle", new InstantCommand(()-> BallIntake.getInstance().setWantedState(BallIntake.WantedState.IDLE)));
        tab.add("Intake", new InstantCommand(()-> BallIntake.getInstance().setWantedState(BallIntake.WantedState.INTAKE)));
        tab.add("Eject", new InstantCommand(()-> BallIntake.getInstance().setWantedState(BallIntake.WantedState.EJECT)));
        tab.addBoolean("isLimitSwitchPressed", ()-> BallIntake.getInstance().isMicroswitchPressed());
        tab.addString("WantedState", ()-> BallIntake.getInstance().getWantedState().toString());
    }
}

package frc.robot;


import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandPS5Controller;
import frc.robot.subsystems.Superstructure;
import frc.robot.subsystems.arm.ArmSubsystem;
import frc.robot.subsystems.coralHolder.CoralHolderSubsystem;
import frc.robot.subsystems.swerve.CommandSwerveDrivetrain;
import frc.robot.utils.ScoringData;

import java.util.Optional;

import static frc.robot.subsystems.Superstructure.WantedSuperState.*;
import static frc.robot.subsystems.arm.ArmPoseConstants.ZEROED;
import static frc.robot.utils.ControllerUtils.setWantedStateAsToggle;
import static frc.robot.utils.ScoringData.ReefLevel.L3;

public class DriverOi {

    public static final CommandPS5Controller controller = new CommandPS5Controller(0);
    private final Superstructure superstructure;

    public DriverOi() {
        superstructure = Superstructure.getInstance();
    }

    private static void resetSwerveAngle() {
        CommandSwerveDrivetrain.getInstance().tareEverything();
        CommandSwerveDrivetrain.getInstance().mHeadingSetpoint = Optional.of(CommandSwerveDrivetrain.getInstance().getState().Pose.getRotation());
    }

    public DriverOi withActions() {
        controller.options().onTrue(new InstantCommand(DriverOi::resetSwerveAngle));

        controller.R2().onTrue(setWantedStateAsToggle(FEEDER_INTAKE));
        controller.R1().onTrue(setWantedStateAsToggle(SCORE_CORAL));

        controller.L2().onTrue(setWantedStateAsToggle(REEF_INTAKE));
        controller.L1().onTrue(setWantedStateAsToggle(SCORE_ALGAE));

        controller.square().onTrue(new InstantCommand(() -> superstructure.setReefLevel(ScoringData.ReefLevel.L1)));
        controller.cross().onTrue(new InstantCommand(() -> superstructure.setReefLevel(ScoringData.ReefLevel.L2)));
        controller.circle().onTrue(new InstantCommand(() -> superstructure.setReefLevel(L3)));
        controller.triangle().onTrue(new InstantCommand(() -> superstructure.setReefLevel(ScoringData.ReefLevel.L4)));
        return this;
    }

    public DriverOi armTesting() {
        controller.R1().onTrue(new InstantCommand(() -> ArmSubsystem.getInstance().setWantedState(ArmSubsystem.WantedState.MOVE_TO_POSITION, L3.getArmPosition())));
        controller.L1().onTrue(new InstantCommand(() -> ArmSubsystem.getInstance().setWantedState(ArmSubsystem.WantedState.MOVE_TO_POSITION, ZEROED)));
        return this;
    }

    public DriverOi scoringTesting() {
        controller.L1().onTrue(new InstantCommand(() -> CoralHolderSubsystem.getInstance().setWantedState(CoralHolderSubsystem.WantedState.IDLE)));
        controller.R1().onTrue(new InstantCommand(() -> CoralHolderSubsystem.getInstance().setWantedState(CoralHolderSubsystem.WantedState.INTAKE)));
        return this;
    }
}

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.arm.ArmPoseConstants;
import frc.robot.subsystems.arm.ArmSubsystem;
import frc.robot.subsystems.ballIntake.BallIntakeSubsystem;
import frc.robot.subsystems.coralHolder.CoralHolderSubsystem;
import frc.robot.subsystems.swerve.CommandSwerveDrivetrain;
import frc.robot.utils.ArmPosition;
import frc.robot.utils.ScoringData;
import org.littletonrobotics.junction.Logger;

import static frc.robot.subsystems.arm.wrist.WristConstants.REEF_BALL_INTAKE_ANGLE;

public class Superstructure extends SubsystemBase {

    private final ArmSubsystem armSubsystem;
    private final CoralHolderSubsystem coralHolderSubsystem;
    private final BallIntakeSubsystem ballIntakeSubsystem;
    private final CommandSwerveDrivetrain commandSwerveDrivetrain;
    private ScoringData.ReefLevel reefLevel = ScoringData.ReefLevel.L2;
    private ScoringData.BallLevel ballLevel = ScoringData.BallLevel.NET;

    private final double scoreAlgaeTimeStamp = Double.NaN;
    private boolean lowerTargetAlgae = false;


    public enum WantedSuperState {
        DEFAULT_STATE,
        STOPPED,
        HOME,
        // CORAL
        FEEDER_INTAKE,
        SCORE_CORAL,
        EJECT_CORAL,
        // ALGAE
        FLOOR_INTAKE,
        REEF_INTAKE,
        SCORE_ALGAE,
        CLIMB
    }

    public enum CurrentSuperState {
        DEFAULT_STATE, // (Default is no piece)
        HOME,
        STOPPED,
        // CORAL
        HAS_CORAL,
        FEEDER_INTAKE,
        SCORE_CORAL_L1,
        SCORE_CORAL,
        EJECT_CORAL,
        // ALGAE
        FLOOR_INTAKE,
        REEF_INTAKE,
        SCORE_ALGAE,
        CLIMB
    }

    private WantedSuperState wantedSuperState = WantedSuperState.STOPPED;
    private CurrentSuperState currentSuperState = CurrentSuperState.STOPPED;
    private CurrentSuperState previousSuperState;

    private Superstructure(ArmSubsystem armSubsystem, CoralHolderSubsystem coralHolderSubsystem, BallIntakeSubsystem ballIntakeSubsystem, CommandSwerveDrivetrain commandSwerveDrivetrain) {
        this.armSubsystem = armSubsystem;
        this.coralHolderSubsystem = coralHolderSubsystem;
        this.ballIntakeSubsystem = ballIntakeSubsystem;
        this.commandSwerveDrivetrain = commandSwerveDrivetrain;
    }

    @Override
    public void periodic() {

        Logger.recordOutput("Superstructure/WantedSuperState", wantedSuperState);
        Logger.recordOutput("Superstructure/CurrentSuperState", currentSuperState);
        Logger.recordOutput("Superstructure/PreviousSuperState", previousSuperState);

        currentSuperState = handleStateTransition();
        previousSuperState = currentSuperState;

        applyStates();
    }

    private CurrentSuperState handleStateTransition() {
        switch (wantedSuperState) {
            case DEFAULT_STATE:
                if (coralHolderSubsystem.hasSensorsTriggered()) {
                    return CurrentSuperState.HAS_CORAL;
                }
                return CurrentSuperState.DEFAULT_STATE;
            case HOME:
                return CurrentSuperState.HOME;
            case STOPPED:
                return CurrentSuperState.STOPPED;
            case EJECT_CORAL:
                return CurrentSuperState.EJECT_CORAL;
            case FEEDER_INTAKE:
                return CurrentSuperState.FEEDER_INTAKE;
            case REEF_INTAKE:
                return CurrentSuperState.REEF_INTAKE;
            case SCORE_ALGAE:
                return CurrentSuperState.SCORE_ALGAE;
            case SCORE_CORAL:
                if (reefLevel == ScoringData.ReefLevel.L1)
                    return CurrentSuperState.SCORE_CORAL_L1;
                return CurrentSuperState.SCORE_CORAL;

            // TODO - We don't have that funcionality on RobotB
            case FLOOR_INTAKE:
                break;
            case CLIMB:
                break;
        }

        return CurrentSuperState.STOPPED;
    }

    public void applyStates() {
        switch (currentSuperState) {
            case DEFAULT_STATE:
                defaultState();
                break;
            case HOME:
                home();
                break;
            case STOPPED:
                stopped();
                break;
            case HAS_CORAL:
                hasCoral();
                break;
            case FEEDER_INTAKE:
                feederIntake();
                break;
            case EJECT_CORAL:
                ejectCoral();
                break;
            case SCORE_CORAL:
                scoreCoral();
                break;
            case SCORE_CORAL_L1:
                scoreCoralL1();
                break;
            case SCORE_ALGAE:
                scoreAlgae();
                break;
            case REEF_INTAKE:
                reefIntake();
                break;
            // TODO - We don't have that funcionality on RobotB
            case CLIMB:
                break;
            case FLOOR_INTAKE:
                break;
        }
    }

    private void defaultState() {
        armSubsystem.setWantedState(ArmSubsystem.WantedState.MOVE_TO_POSITION, ArmPoseConstants.ZEROED);
        coralHolderSubsystem.setWantedState(CoralHolderSubsystem.WantedState.IDLE);
        ballIntakeSubsystem.setWantedState(BallIntakeSubsystem.WantedState.IDLE);
        commandSwerveDrivetrain.setWantedState(CommandSwerveDrivetrain.WantedState.TELEOP);
    }

    private void ejectCoral() {
        armSubsystem.setWantedState(ArmSubsystem.WantedState.MOVE_TO_POSITION, ArmPoseConstants.EJECT);
        if (armSubsystem.reachedSetpoint()) {
            // TODO the driver should decide how much time he should eject.
            coralHolderSubsystem.setWantedState(CoralHolderSubsystem.WantedState.EJECT);
        }

        ballIntakeSubsystem.setWantedState(BallIntakeSubsystem.WantedState.IDLE);
    }

    private void scoreCoral() {
        // TODO the handleState should decide if we're on scoreCoral or scoreCoralL1\

        armSubsystem.setWantedState(ArmSubsystem.WantedState.MOVE_TO_POSITION, reefLevel.getArmPosition());
        if (armSubsystem.reachedSetpoint()) {
            coralHolderSubsystem.setWantedState(CoralHolderSubsystem.WantedState.SCORE);
            if (coralHolderSubsystem.getEjectFlag()) {
                coralHolderSubsystem.setWantedState(CoralHolderSubsystem.WantedState.IDLE);
                setWantedSuperState(WantedSuperState.DEFAULT_STATE);
            }
        }

        ballIntakeSubsystem.setWantedState(BallIntakeSubsystem.WantedState.IDLE);
        commandSwerveDrivetrain.setWantedState(CommandSwerveDrivetrain.WantedState.TELEOP);

    }

    private void scoreCoralL1() {
        armSubsystem.setWantedState(ArmSubsystem.WantedState.MOVE_TO_POSITION,
                ScoringData.ReefLevel.L1.getArmPosition());
        if (armSubsystem.reachedSetpoint()) {
            coralHolderSubsystem.setWantedState(CoralHolderSubsystem.WantedState.SCORE);
            if (coralHolderSubsystem.getEjectFlag()) {
                coralHolderSubsystem.setWantedState(CoralHolderSubsystem.WantedState.IDLE);
                setWantedSuperState(WantedSuperState.DEFAULT_STATE);
            }
        }

        ballIntakeSubsystem.setWantedState(BallIntakeSubsystem.WantedState.IDLE);
        commandSwerveDrivetrain.setWantedState(CommandSwerveDrivetrain.WantedState.TELEOP);

    }

    private void scoreAlgae() {
        armSubsystem.setWantedState(ArmSubsystem.WantedState.MOVE_TO_POSITION, ballLevel.getArmPosition());
        if (armSubsystem.reachedSetpoint()) {
            if (ballLevel == ScoringData.BallLevel.NET) {
                ballIntakeSubsystem.setWantedState(BallIntakeSubsystem.WantedState.SHOOT);
            } else {
                ballIntakeSubsystem.setWantedState(BallIntakeSubsystem.WantedState.PROCESSOR);
            }

            if (ballIntakeSubsystem.getEjectFlag()) {
                setWantedSuperState(WantedSuperState.DEFAULT_STATE);
            }
        }
        commandSwerveDrivetrain.setWantedState(CommandSwerveDrivetrain.WantedState.TELEOP);

    }

    public static final double HIGH_ALGAE_HEIGHT = 0.43;
    public static final double LOW_ALGAE_HEIGHT = 0.1;


    // TODO change the following function with vision-style localization pose estimation.
    public ArmPosition getReefHeight() {
        if (lowerTargetAlgae) {
            return new ArmPosition(LOW_ALGAE_HEIGHT, REEF_BALL_INTAKE_ANGLE);
        }
        return new ArmPosition(HIGH_ALGAE_HEIGHT, REEF_BALL_INTAKE_ANGLE);
    }

    private void reefIntake() {
        armSubsystem.setWantedState(ArmSubsystem.WantedState.MOVE_TO_POSITION, getReefHeight());
        if (armSubsystem.reachedSetpoint()) {
            ballIntakeSubsystem.setWantedState(BallIntakeSubsystem.WantedState.INTAKE);
            if (ballIntakeSubsystem.hasSensorsTriggered()) {
                setWantedSuperState(WantedSuperState.DEFAULT_STATE);
            }
        }
        commandSwerveDrivetrain.setWantedState(CommandSwerveDrivetrain.WantedState.TELEOP);
    }

    private void home() {
        if (armSubsystem.hasHomeCompleted() && previousSuperState == CurrentSuperState.HOME) {
            armSubsystem.setWantedState(ArmSubsystem.WantedState.IDLE);
        } else {
            armSubsystem.setWantedState(ArmSubsystem.WantedState.HOME);
        }

        // Checks if all the systems has been homed - change the Superstructure state to default.
        if (armSubsystem.hasHomeCompleted() && previousSuperState == currentSuperState) {
            setWantedSuperState(WantedSuperState.DEFAULT_STATE);
        }
        commandSwerveDrivetrain.setWantedState(CommandSwerveDrivetrain.WantedState.TELEOP);

    }

    private void hasCoral() {
        coralHolderSubsystem.setWantedState(CoralHolderSubsystem.WantedState.KEEP);
        ballIntakeSubsystem.setWantedState(BallIntakeSubsystem.WantedState.IDLE);
        armSubsystem.setWantedState(ArmSubsystem.WantedState.MOVE_TO_POSITION, ArmPoseConstants.ZEROED);
        commandSwerveDrivetrain.setWantedState(CommandSwerveDrivetrain.WantedState.TELEOP);

    }

    private void feederIntake() {
        ballIntakeSubsystem.setWantedState(BallIntakeSubsystem.WantedState.IDLE);
        armSubsystem.setWantedState(ArmSubsystem.WantedState.MOVE_TO_POSITION, ArmPoseConstants.FEEDER);
        coralHolderSubsystem.setWantedState(CoralHolderSubsystem.WantedState.INTAKE);
        if (coralHolderSubsystem.hasSensorsTriggered()) {
            setWantedSuperState(WantedSuperState.DEFAULT_STATE);
        }
        commandSwerveDrivetrain.setWantedState(CommandSwerveDrivetrain.WantedState.TELEOP);
    }

    private void stopped() {
        armSubsystem.setWantedState(ArmSubsystem.WantedState.IDLE);
        coralHolderSubsystem.setWantedState(CoralHolderSubsystem.WantedState.IDLE);
        ballIntakeSubsystem.setWantedState(BallIntakeSubsystem.WantedState.IDLE);
        commandSwerveDrivetrain.setWantedState(CommandSwerveDrivetrain.WantedState.IDLE);
    }

    public WantedSuperState getWantedSuperState() {
        return wantedSuperState;
    }

    public void setReefLevel(ScoringData.ReefLevel reefLevel) {
        this.reefLevel = reefLevel;
    }

    public void setBallLevel(ScoringData.BallLevel ballLevel) {
        this.ballLevel = ballLevel;
    }

    public void setLowerTargetAlgae(boolean lowerTargetAlgae) {
        this.lowerTargetAlgae = lowerTargetAlgae;
    }

    public void setWantedSuperState(WantedSuperState superState) {
        this.wantedSuperState = superState;
    }

    private static Superstructure instance;

    public static Superstructure getInstance() {
        return instance;
    }

    public static void init(ArmSubsystem armSubsystem, CoralHolderSubsystem coralHolderSubsystem, BallIntakeSubsystem ballIntakeSubsystem,CommandSwerveDrivetrain commandSwerveDrivetrain) {
        instance = new Superstructure(armSubsystem, coralHolderSubsystem, ballIntakeSubsystem,commandSwerveDrivetrain);
    }
}
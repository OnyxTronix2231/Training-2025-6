package frc.robot.subsystems.arm;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.lib.PID.PIDEntries;
import frc.robot.lib.PhysicalTelemetryEntries;
import frc.robot.subsystems.arm.elevator.ElevatorConstants;
import frc.robot.subsystems.arm.elevator.ElevatorIOSimulation;

import java.util.function.DoubleSupplier;

import static frc.robot.subsystems.arm.elevator.ElevatorConstants.*;
import static frc.robot.subsystems.arm.wrist.WristConstants.*;
import static frc.robot.subsystems.arm.wrist.WristConstants.WRIST_SUBSYSTEM_NAME;

public class ArmShuffleBoard {

    public ArmShuffleBoard() {

        ShuffleboardTab tab = Shuffleboard.getTab(ELEVATOR_SUBSYSTEM_NAME);

        PhysicalTelemetryEntries elevator = new PhysicalTelemetryEntries(ELEVATOR_SUBSYSTEM_NAME, ELEVATOR_MOTOR_MASTER_NAME,
                () -> ArmSubsystem.getInstance().getElevatorPosition(),
                () -> ArmSubsystem.getInstance().getElevatorVelocity(),
                () -> ArmSubsystem.getInstance().getElevatorAcceleration()
        );
        DoubleSupplier targetLength = ()-> elevator.getTargetPosition();

        PhysicalTelemetryEntries wrist = new PhysicalTelemetryEntries(WRIST_SUBSYSTEM_NAME, WRIST_MOTOR_NAME,
                () -> ArmSubsystem.getInstance().getWristPosition(),
                () -> ArmSubsystem.getInstance().getWristVelocity(),
                () -> ArmSubsystem.getInstance().getWristAcceleration()
        );
        DoubleSupplier targetAngle = ()-> wrist.getTargetPosition();

        //pid
        PIDEntries elevatorEntries = new PIDEntries(ElevatorConstants.ELEVATOR_SUBSYSTEM_NAME, ELEVATOR_MOTOR_MASTER_NAME, ELEVATOR_PID_VALUES);
        tab.add("update elevator pid" , new InstantCommand(()-> ArmSubsystem.getInstance().updateElevatorPID(elevatorEntries.getPIDValues())));

        PIDEntries wristEntries = new PIDEntries(WRIST_SUBSYSTEM_NAME, WRIST_MOTOR_NAME, WRIST_PID_VALUES);
        tab.add("update wrist pid" , new InstantCommand(()-> ArmSubsystem.getInstance().updateWristPID(wristEntries.getPIDValues())));

        //states
        tab.addString("Wanted State", ()->ArmSubsystem.getInstance().getWantedState().toString());
        tab.addString("System State", ()->ArmSubsystem.getInstance().getSystemState().toString());

        tab.add("idle", new InstantCommand(()-> ArmSubsystem.getInstance().setWantedState(ArmSubsystem.WantedState.IDLE)));
        tab.add("home", new InstantCommand(()-> ArmSubsystem.getInstance().setWantedState(ArmSubsystem.WantedState.HOME)));

        tab.add("Move elevator to position", new InstantCommand(()-> ArmSubsystem.getInstance().setWantedState(
                ArmSubsystem.WantedState.MOVE_TO_POSITION, new ArmPosition(targetLength.getAsDouble(), ArmSubsystem.getInstance().getWristPosition()))));

        tab.add("Move Wrist to position" , new InstantCommand(()-> ArmSubsystem.getInstance().setWantedState(
                ArmSubsystem.WantedState.MOVE_TO_POSITION, new ArmPosition(ArmSubsystem.getInstance().getElevatorPosition(), targetAngle.getAsDouble()))));

        tab.add("Move Elevator and Wrist to position", new InstantCommand(()-> ArmSubsystem.getInstance().setWantedState(
                ArmSubsystem.WantedState.MOVE_TO_POSITION, new ArmPosition(targetLength.getAsDouble(), targetAngle.getAsDouble()))));

        //Sensors
        tab.add("set limit switch value", new InstantCommand(()-> ElevatorIOSimulation.setLimitSwitchValue(!ElevatorIOSimulation.getLimitSwitchValue())));
        tab.addBoolean("is Limit Switch pressed", ()-> ElevatorIOSimulation.getLimitSwitchValue());

    }
}

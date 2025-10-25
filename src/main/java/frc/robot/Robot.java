// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import L5.lecture.LED;
import L5.training.LEDP;
import TrainingUtils.AddressableLEDSim;
import TrainingUtils.KeyButton;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.Superstructure;
import org.littletonrobotics.junction.LoggedRobot;
import org.littletonrobotics.junction.Logger;
import org.littletonrobotics.junction.networktables.NT4Publisher;
import org.littletonrobotics.junction.wpilog.WPILOGWriter;

import java.awt.*;

import static TrainingUtils.LedConstants.LedSimulationConstants.ROBOT_MECHANISM;

/**
 * The methods in this class are called automatically corresponding to each mode, as described in
 * the TimedRobot documentation. If you change the name of this class or the package after creating
 * this project, you must also update the manifest file in the resource directory.
 */
public class Robot extends LoggedRobot {
    private LEDP LEGO;
    // private LED led;
    // private KeyButton button1;

    @Override
    public void robotInit() {
        initializeLogger();
        Superstructure.init();

        LEGO = new LEDP(7);
        LEGO.ColorAllLED(Color.BLUE);

        //AddressableLEDSim strip = new AddressableLEDSim();
        //AddressableLEDBuffer buffer = new AddressableLEDBuffer(7);
        //strip.setLength(buffer.getLength());

        //buffer.setRGB(0, 255, 0, 0);


        //for (int index = 0; index < buffer.getLength(); index++) {
        //    buffer.setRGB(index, 255, 0, 0);
        //}
        //strip.setData(buffer);

        // led = new LED(7);
        //led.fullColor(Color.RED);
        //led.oneLed(3, Color.GREEN);

        //button1 = new KeyButton(1);
    }

    /**
     * This function is run once each time the robot enters autonomous mode.
     */
    @Override
    public void autonomousInit() {
    }

    /**
     * This function is called periodically during autonomous.
     */
    @Override
    public void autonomousPeriodic() {
    }

    /**
     * This function is called once each time the robot enters teleoperated mode.
     */
    @Override
    public void teleopInit() {
        Superstructure.getInstance().setWantedSuperState(Superstructure.WantedSuperState.DEFAULT_STATE);
    }

    @Override
    public void disabledInit() {
        Superstructure.getInstance().setWantedSuperState(Superstructure.WantedSuperState.STOPPED);
    }

    /**
     * This function is called periodically during teleoperated mode.
     */
    @Override
    public void teleopPeriodic() {

    }

    @Override
    public void robotPeriodic() {
        Logger.recordOutput("robot mechanism", ROBOT_MECHANISM);
        LEGO.periodic();
        // if (button1.isPressed()) {
        //     led.fullColor(Color.RED);
        // }
        // led.periodic();
        CommandScheduler.getInstance().run();
    }

    /**
     * This function is called once each time the robot enters test mode.
     */
    @Override
    public void testInit() {
    }

    /**
     * This function is called periodically during test mode.
     */
    @Override
    public void testPeriodic() {

    }

    private void initializeLogger() {
        Logger.recordMetadata("JackInTheOnyx", "MyProject"); // Set a metadata value

        if (isReal()) {
            Logger.addDataReceiver(new WPILOGWriter("u/kit_logs"));
            new PowerDistribution(1, PowerDistribution.ModuleType.kRev); // Enables power distribution logging
        } else
            Logger.addDataReceiver(new WPILOGWriter());

        Logger.addDataReceiver(new NT4Publisher()); // Publish data to NetworkTables

        Logger.start();
    }

    public static final Joystick joystick = new Joystick(0);
}

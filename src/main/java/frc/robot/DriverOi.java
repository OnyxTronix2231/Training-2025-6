package frc.robot;

import edu.wpi.first.wpilibj2.command.button.CommandPS5Controller;
import frc.robot.subsystems.Superstructure;

public class DriverOi {

    public static final CommandPS5Controller controller = new CommandPS5Controller(0);
    private final Superstructure superstructure;

    public DriverOi() {
        superstructure = Superstructure.getInstance();
    }

    public DriverOi withActions() {
        return this;
    }
}

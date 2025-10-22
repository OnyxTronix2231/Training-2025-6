package TrainingUtils;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Robot;

public class KeyButton extends SubsystemBase {
    private JoystickButton button;
    private boolean last;

    public KeyButton(int portNumber) {
        this.button = new JoystickButton(Robot.joystick, MathUtil.clamp(portNumber, 1, 32));
        this.last = false;
    }

    @Override
    public void periodic() {
        last = button.getAsBoolean();
    }

    public boolean isPressed() {
        return this.button.getAsBoolean() && !last;
    }
}

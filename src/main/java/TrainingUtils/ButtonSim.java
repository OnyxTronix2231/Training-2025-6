package TrainingUtils;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class ButtonSim {
    private boolean isPressed;

    public ButtonSim(String buttonName) {
        isPressed = false;

        ShuffleboardTab tab = Shuffleboard.getTab("Buttons");
        tab.addBoolean("Is" + buttonName + "Seeing", () -> isPressed);

        tab.add(buttonName, new InstantCommand(
                () -> setPressed(!isPressed)));
    }

    public boolean isPressed() {
        return isPressed;
    }

    public void setPressed(boolean pressed) {
        isPressed = pressed;
    }
}

package Lrobot.hinge;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.littletonrobotics.junction.Logger;

public class HingeJava extends SubsystemBase {

    public enum WantedState {

        IDLE,
        OPEN,
        CLOSE
    }

    public enum SystemState {

        IDLING,
        OPENING,
        CLOSING
    }



    public int getNumber() {
        return 5;
    }

}

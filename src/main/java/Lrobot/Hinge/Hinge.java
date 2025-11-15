package Lrobot.Hinge;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.Superstructure;

public class Hinge extends SubsystemBase {

    public enum SystemState {
        IDLE,
        OPEN,
        CLOSE
    }
    public enum WantedState {
        IDLING,
        OPENING,
        CLOSING
    }



    @Override
    public void periodic() {
        super.periodic();

    }

    public SystemState handleStateTransition () {
        switch (WantedState) {
            case IDLE:
                switch ()
        }
    }
}

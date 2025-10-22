package L5.lecture;

import TrainingUtils.AddressableLEDSim;
import TrainingUtils.KeyButton;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;

public class LED {

    private KeyButton button1;
    private KeyButton button2;
    private AddressableLEDSim strip;
    private AddressableLEDBuffer buffer;

    public enum State {
        blue,
        red,
        green,
        off
    }

    private State currentState;
    private State wantedState;

    public LED() {
        button1 = new KeyButton(1);
        button2 = new KeyButton(2);
        strip = new AddressableLEDSim();
        buffer = new AddressableLEDBuffer(7);
        strip.setLength(buffer.getLength());

        currentState = State.off;
        wantedState = State.off;
    }

    public void periodic() {
        handleStateTransition();
        currentState = wantedState;
    }

    public void handleStateTransition() {
        switch (currentState) {
            case off:
                buffer.setRGB(1, 0, 0, 0);
                strip.setData(buffer);
                if (button1.isPressed()) {
                    wantedState = State.blue;
                }
                break;
            case blue:
                buffer.setRGB(1, 0, 0, 255);
                strip.setData(buffer);
                if (button1.isPressed()) {
                    wantedState = State.red;
                }
                if (button2.isPressed()) {
                    wantedState = State.green;
                }
                break;
            case red:
                buffer.setRGB(1, 255, 0, 0);
                strip.setData(buffer);
                if (button1.isPressed()) {
                    wantedState = State.green;
                }
                if (button2.isPressed()) {
                    wantedState = State.blue;
                }
                break;
            case green:
                buffer.setRGB(1, 0, 255, 0);
                strip.setData(buffer);
                if (button1.isPressed()) {
                    wantedState = State.blue;
                }
                if (button2.isPressed()) {
                    wantedState = State.red;
                }
                break;
        }
    }

}

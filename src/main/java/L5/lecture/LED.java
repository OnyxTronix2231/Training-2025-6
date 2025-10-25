package L5.lecture;

import TrainingUtils.AddressableLEDSim;
import TrainingUtils.KeyButton;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;

import java.awt.*;

public class LED {
    private int length;
    private AddressableLEDSim strip;
    private AddressableLEDBuffer buffer;

    public enum LEDWantedState {
        CW,
        CCW,
        IDLE
    }

    public enum LEDSystemState {
        blue,
        red,
        green,
        off
    }

    private LEDSystemState currentState;
    private LEDWantedState wantedState;
    private KeyButton button1;
    private KeyButton button2;

    public LED(int length) {
        this.length = length;
        button1 = new KeyButton(1);
        button2 = new KeyButton(2);
        strip = new AddressableLEDSim();
        buffer = new AddressableLEDBuffer(length);
        strip.setLength(buffer.getLength());

        currentState = LEDSystemState.off;
        wantedState = LEDWantedState.IDLE;
    }

    public void oneLed(int index, Color color) {
        buffer.setRGB(index, color.getRed(), color.getGreen(), color.getBlue());
        strip.setData(buffer);
    }

    public void fullColor(Color color) {
        for (int i = 0; i < buffer.getLength(); i++) {
            buffer.setRGB(i, color.getRed(), color.getGreen(), color.getBlue());
        }
        strip.setData(buffer);
    }

    public void periodic() {
        updateWantedState();
        currentState = handleStateTransition();
        applyState();
    }

    public void updateWantedState() {
        if (button1.isPressed()) {
            wantedState = LEDWantedState.CW;
        } else if (button2.isPressed()) {
            wantedState = LEDWantedState.CCW;
        } else {
            wantedState = LEDWantedState.IDLE;
        }
    }

    public LEDSystemState handleStateTransition() {
        switch (wantedState) {
            case CW:
                switch (currentState) {
                    case off:
                        return LEDSystemState.blue;
                    case blue:
                        return LEDSystemState.green;
                    case green:
                        return LEDSystemState.red;
                    case red:
                        return LEDSystemState.blue;
                }
            case CCW:
                switch (currentState) {
                    case off:
                        return LEDSystemState.off;
                    case blue:
                        return LEDSystemState.red;
                    case green:
                        return LEDSystemState.blue;
                    case red:
                        return LEDSystemState.green;
                }
            case IDLE:
                return currentState;
            default:
                return currentState;
        }
    }

    public void applyState() {
        switch (currentState) {
            case off:
                fullColor(Color.BLACK);
                break;
            case blue:
                fullColor(Color.BLUE);
                break;
            case red:
                fullColor(Color.RED);
                break;
            case green:
                fullColor(Color.GREEN);
                break;
        }
    }
}

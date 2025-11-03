package L5.training;

import L5.lecture.LED;
import TrainingUtils.AddressableLEDSim;
import TrainingUtils.KeyButton;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;

import java.awt.*;

public class LEDP {

    private int length;
    private AddressableLEDSim strip;
    private AddressableLEDBuffer buffer;
    private WantedState wantedState;
    private SystemState currentState;

    private KeyButton button1;
    private KeyButton button2;
    private KeyButton button3;

    public LEDP(int length) {
        this.length = length;
        strip = new AddressableLEDSim();
        buffer = new AddressableLEDBuffer(length);
        strip.setLength(buffer.getLength());
        currentState = SystemState.OFF;
        wantedState = WantedState.BLUE;

        button1 = new KeyButton(1);
        button2 = new KeyButton(2);
        button3 = new KeyButton(3);
    }

    public void ColorOneLED(int index, Color color) {
        buffer.setRGB(index, color.getRed(), color.getGreen(), color.getBlue());
    }

    public void Show() {
        strip.setData(buffer);
    }

    public void ColorAllLED(Color color) {
        for (int i = 0; i < length; i++) {
            ColorOneLED(i, color);
        }
        Show();
    }

    public void periodic() {
        updateWantedState();
        currentState = handleStateTransition();
        applyState();
    }

    public enum SystemState {

        OFF,
        BLUE,
        RED,
        GREEN

    }

    public enum WantedState {

        BLUE,
        RED,
        GREEN

    }


    public void updateWantedState() {
        if (button1.isPressed()) {
            wantedState = WantedState.BLUE;
        }
        if (button2.isPressed()) {
            wantedState = WantedState.RED;
        }
        if (button3.isPressed()) {
            wantedState = WantedState.GREEN;
        }
    }

    public SystemState handleStateTransition() {
        switch (wantedState) {
            case BLUE:
                switch (currentState) {
                    case RED, GREEN:
                        return SystemState.OFF;
                    case BLUE, OFF:
                        return SystemState.BLUE;
                }
            case RED:
                switch (currentState) {
                    case BLUE, GREEN:
                        return SystemState.OFF;
                    case RED, OFF:
                        return SystemState.RED;
                }
            case GREEN:
                switch (currentState) {
                    case BLUE, RED:
                        return SystemState.OFF;
                    case GREEN, OFF:
                        return SystemState.GREEN;
                }
            default:
                return SystemState.OFF;
        }
    }

    public void applyState() {
        switch (currentState) {
            case OFF:
                ColorAllLED(Color.BLACK);
                break;
            case BLUE:
                ColorAllLED(Color.BLUE);
                break;
            case RED:
                ColorAllLED(Color.RED);
                break;
            case GREEN:
                ColorAllLED(Color.GREEN);
                break;
        }
    }

    public void colorFromArray(Color[] colors) {
        if ()
        for (int i = 0; i < colors.length; i++) {
            ColorOneLED(i, colors[i]);

        }

        }
    }

}


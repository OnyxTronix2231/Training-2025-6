package L5.lecture;

import TrainingUtils.AddressableLEDSim;
import TrainingUtils.KeyButton;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;

import java.awt.*;

public class Led1 {

    public enum LEDWantedState {
        RED,
        GREEN,
        BLUE
    }
    private LEDWantedState wantedState;

    public enum LEDSystemState {
        RED,
        GREEN,
        BLUE,
        OFF
    }
    private LEDSystemState currentState;

    private int length;
    private AddressableLEDSim strip;
    private AddressableLEDBuffer buffer;

    private KeyButton button1;
    private KeyButton button2;
    private KeyButton button3;

    public Led1(int length) {
        this.length = length;
        strip = new AddressableLEDSim();
        buffer = new AddressableLEDBuffer(length);
        strip.setLength(buffer.getLength());

        button1 = new KeyButton(1);
        button2 = new KeyButton(2);
        button3 = new KeyButton(3);

        currentState = LEDSystemState.GREEN;
        wantedState = LEDWantedState.GREEN;
    }

    public void periodic() {
        updateWantedState();
        currentState = handleStateTransition();
        applyState();
    }

    public void updateWantedState() {
        if (button1.isPressed()) {
            wantedState = LEDWantedState.BLUE;
        }
        if (button2.isPressed()) {
            wantedState = LEDWantedState.RED;
        }
        if (button3.isPressed()) {
            wantedState = LEDWantedState.GREEN;
        }
    }

    public LEDSystemState handleStateTransition() {
        switch (wantedState) {
            case BLUE:
                switch (currentState) {
                    case RED, GREEN:
                        return LEDSystemState.OFF;
                    case OFF, BLUE:
                        return LEDSystemState.BLUE;
                }
            case GREEN:
                switch (currentState) {
                    case RED, BLUE:
                        return LEDSystemState.OFF;
                    case OFF, GREEN:
                        return LEDSystemState.GREEN;
                }
            case RED:
                switch (currentState) {
                    case GREEN, BLUE:
                        return LEDSystemState.OFF;
                    case OFF, RED:
                        return LEDSystemState.RED;
                }
            default:
                return LEDSystemState.OFF;
        }
    }

    public void applyState() {
        switch (currentState) {
            case OFF:
                fullColor(Color.BLACK);
                break;
            case RED:
                fullColor(Color.RED);
                break;
            case BLUE:
                fullColor(Color.BLUE);
                break;
            case GREEN:
                fullColor(Color.GREEN);
                break;
        }
    }

    public void show() {
        strip.setData(buffer);
    }

    public void setOneLed(int index, Color color) {
        buffer.setRGB(index, color.getRed(), color.getGreen(), color.getBlue());
    }

    public void fullColor(Color color) {
        for (int i = 0; i < length; i++) {
            setOneLed(i, color);
        }
        show();
    }

    public void colorFromArray(Color[] colors) {
        if (colors.length > buffer.getLength()) {
            for (int i = 0; i < buffer.getLength(); i++) {
                setOneLed(i, colors[i]);
            }
        }else {
            for (int i = 0; i < colors.length; i++) {
                setOneLed(i, colors[i]);
            }
        }
        show();
    }

    public Color[] getBufferAsArray() {
        Color[] c = new Color[buffer.getLength()];
        for (int i = 0; i < buffer.getLength(); i++) {
            c[i] = new Color(buffer.getRed(i), buffer.getGreen(i), buffer.getBlue(i));
        }
        return c;
    }

}

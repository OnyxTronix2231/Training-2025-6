package L6.training;

import TrainingUtils.AddressableLEDSim;
import TrainingUtils.KeyButton;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;

import java.awt.*;

public class LED {
    private int length;
    private AddressableLEDBuffer buffer;
    private AddressableLEDSim led;
    private KeyButton button1;
    private KeyButton button2;
    private KeyButton button3;
    private LEDSystemState currentState;
    private LEDWantedState wantedState;

    public LED(int length) {
        this.length = length;
        this.led = new AddressableLEDSim();
        this.buffer = new AddressableLEDBuffer(length);
        this.led.setLength(buffer.getLength());

        button1 = new KeyButton(1);
        button2 = new KeyButton(2);
        button3 = new KeyButton(3);
        this.currentState = LEDSystemState.GREEN;
        this.wantedState = LEDWantedState.GREEN;
    }

    public void periodic() {
        updateWantedState();
        currentState = handleStateTransition();
        applyState();
    }

    public void updateWantedState() {
        if (button1.isPressed()) {
            wantedState = LEDWantedState.BLUE;
        } else if (button2.isPressed()) {
            wantedState = LEDWantedState.RED;
        } else if (button3.isPressed()) {
            wantedState = LEDWantedState.GREEN;
        }
    }

    public L3.training.Color[] getColors() {
        L3.training.Color[] colors = new L3.training.Color[length];
        for (int i = 0; i < length; i++) {
            colors[i] = new L3.training.Color(buffer.getRed(i), buffer.getGreen(i), buffer.getBlue(i));
        }
        return colors;
    }

    public void setColors(L3.training.Color[] colors) {
        for (int i = 0; i < colors.length; i++) {
            if (i > length - 1) break;
            setColor(i, new Color(colors[i].getRed(), colors[i].getGreen(), colors[i].getBlue()));
        }
    }

    public LEDSystemState handleStateTransition() {
        switch (wantedState) {
            case BLUE:
                switch (currentState) {
                    case OFF, BLUE:
                        return LEDSystemState.BLUE;
                    case GREEN, RED:
                        return LEDSystemState.OFF;
                }
            case RED:
                switch (currentState) {
                    case OFF, RED:
                        return LEDSystemState.RED;
                    case GREEN, BLUE:
                        return LEDSystemState.OFF;
                }
            case GREEN:
                switch (currentState) {
                    case OFF, GREEN:
                        return LEDSystemState.GREEN;
                    case BLUE, RED:
                        return LEDSystemState.OFF;
                }
            default:
                return currentState;
        }
    }

    public void applyState() {
        switch (currentState) {
            case OFF:
                setAll(Color.BLACK);
                break;
            case GREEN:
                setAll(Color.GREEN);
                break;
            case RED:
                setAll(Color.RED);
                break;
            case BLUE:
                setAll(Color.BLUE);
                break;
        }
    }

    public void setInBounds(int i1, int i2, Color color) {
        for (int i = i1; i <= i2; i++) {
            buffer.setRGB(i, color.getRed(), color.getGreen(), color.getBlue());
        }
        led.setData(buffer);
    }

    public void setColor(int index, Color color) {
        buffer.setRGB(index, color.getRed(), color.getGreen(), color.getBlue());
        led.setData(buffer);
    }

    public void makeRainbow() {
        int diff = 180 / length;
        int current = 0;
        for (int i = 0; i < length; i++) {
            buffer.setHSV(i, current, 255, 127);
            current += diff;
        }
        led.setData(buffer);
    }

    public void setAll(Color color) {
        for (int i = 0; i < length; i++) {
            buffer.setRGB(i, color.getRed(), color.getGreen(), color.getBlue());
        }
        led.setData(buffer);
    }

    public enum LEDSystemState {
        OFF, GREEN, BLUE, RED
    }

    public enum LEDWantedState {
        BLUE, RED, GREEN
    }
}

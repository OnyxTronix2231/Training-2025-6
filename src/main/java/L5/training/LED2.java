package L5.training;

import TrainingUtils.AddressableLEDSim;
import TrainingUtils.KeyButton;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;

import java.awt.*;

public class LED2 {
    private int length;
    private AddressableLEDBuffer buffer;
    private AddressableLEDSim led;
    private KeyButton button1;
    private KeyButton button2;
    private KeyButton button3;
    private LEDSystemState currentState;
    private LEDWantedState wantedState;

    public LED2(int length) {
        this.length = length;
        this.led = new AddressableLEDSim();
        this.buffer = new AddressableLEDBuffer(length);
        this.led.setLength(buffer.getLength());

        button1 = new KeyButton(1);
        button2 = new KeyButton(2);
        button3 = new KeyButton(3);
        this.currentState = LEDSystemState.OFF;
        this.wantedState = LEDWantedState.OFF;
    }

    public void periodic() {
        updateWantedState();
        currentState = handleStateTransition();
        applyState();
    }

    public void updateWantedState() {
        if (button1.isPressed()) {
            wantedState = LEDWantedState.LEFT;
        } else if (button2.isPressed()) {
            wantedState = LEDWantedState.RIGHT;
        } else if (button3.isPressed()) {
            wantedState = LEDWantedState.OFF;
        } else {
            wantedState = LEDWantedState.IDLE;
        }
    }

    public LEDSystemState handleStateTransition() {
        switch (wantedState) {
            case RIGHT:
                switch (currentState) {
                    case OFF:
                        return LEDSystemState.BLUE;
                    case BLUE:
                        return LEDSystemState.RED;
                    case RED:
                        return LEDSystemState.GREEN;
                    case GREEN:
                        return LEDSystemState.OFF;
                }
            case LEFT:
                switch (currentState) {
                    case OFF:
                        return LEDSystemState.GREEN;
                    case GREEN:
                        return LEDSystemState.RED;
                    case RED:
                        return LEDSystemState.BLUE;
                    case BLUE:
                        return LEDSystemState.OFF;
                }
            case OFF:
                return LEDSystemState.OFF;
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
        RIGHT, LEFT, OFF, IDLE
    }
}

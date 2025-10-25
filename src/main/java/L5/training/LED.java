package L5.training;

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

    public LED(int length) {
        this.length = length;
        this.led = new AddressableLEDSim();
        this.buffer = new AddressableLEDBuffer(length);
        this.led.setLength(buffer.getLength());

        button1 = new KeyButton(1);
        button2 = new KeyButton(2);
    }

    public void periodic() {
        if (button1.isPressed()) {
            setAll(Color.BLUE);
        }
        if (button2.isPressed()) {
            setAll(Color.GREEN);
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
}

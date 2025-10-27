package L5.lecture;

import TrainingUtils.AddressableLEDSim;
import TrainingUtils.KeyButton;
import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;

import java.awt.*;

public class Led1 {

    private int length;
    private AddressableLEDSim strip;
    private AddressableLEDBuffer buffer;

    private KeyButton button1;
    private KeyButton button2;

    public Led1(int length) {
        this.length = length;
        strip = new AddressableLEDSim();
        buffer = new AddressableLEDBuffer(length);
        strip.setLength(buffer.getLength());

        button1 = new KeyButton(1);
        button2 = new KeyButton(2);
    }

    public void periodic() {

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
}

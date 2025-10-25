package L5.training;

import TrainingUtils.AddressableLEDSim;
import TrainingUtils.KeyButton;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;

import java.awt.*;

public class LEDP {

    private int length;
    private AddressableLEDSim strip;
    private AddressableLEDBuffer buffer;

    private KeyButton button1;
    private KeyButton button2;

    public LEDP(int length) {
        this.length = length;
        strip = new AddressableLEDSim();
        buffer = new AddressableLEDBuffer(length);
        strip.setLength(buffer.getLength());

        button1 = new KeyButton(1);
        button2 = new KeyButton(2);
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
        if (button1.isPressed()) {
            ColorAllLED(Color.BLUE);
        }
        if (button2.isPressed()) {
            ColorAllLED(Color.GREEN);
        }
    }
}

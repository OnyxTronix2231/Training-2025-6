package L5.training;

import TrainingUtils.AddressableLEDSim;
import TrainingUtils.KeyButton;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;

import java.awt.*;
import java.nio.Buffer;

public class BarelLeds {
    private int length;
    private AddressableLEDSim strip;
    private AddressableLEDBuffer buffer;
    private KeyButton button1;
    private KeyButton button2;

    public BarelLeds(int length) {
        this.length = length;
        strip = new AddressableLEDSim();
        buffer = new AddressableLEDBuffer(length);
        strip.setLength(buffer.getLength());
        button1 = new KeyButton(1);
        button2 = new KeyButton(2);
    }

    public  void turnOnLed(Color color, int index){
        buffer.setRGB(index, color.getRed(), color.getBlue(), color.getGreen());
    }
    public void paintAll(Color color){
        for (int i =0; i<length;i++){
            buffer.setRGB(i,color.getRed(),color.getGreen(), color.getBlue());
        }
        show();
    }
    public void show(){
        strip.setData(buffer);
    }
    public void periodic(){
        if (button1.isPressed()){
            paintAll(Color.BLUE);
        }
        if (button2.isPressed()){
            paintAll(Color.GREEN);
        }
    }
}

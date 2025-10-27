package L5.training;

import L5.lecture.LED;
import L5.lecture.Led1;
import TrainingUtils.AddressableLEDSim;
import TrainingUtils.KeyButton;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;

import java.awt.*;
import java.nio.Buffer;

public class BarelLeds {

    public enum LEDWantedState {
        Right,
        Left,
        Off,
        Idle
    }

    public enum LEDSystemState {
        Green,
        Red,
        Blue,
        Off
    }

    private int length;
    private AddressableLEDSim strip;
    private AddressableLEDBuffer buffer;
    private KeyButton button1;
    private KeyButton button2;
    private KeyButton button3;

    private BarelLeds.LEDSystemState currentState;
    private BarelLeds.LEDWantedState wantedState;

    public BarelLeds(int length) {
        this.length = length;
        strip = new AddressableLEDSim();
        buffer = new AddressableLEDBuffer(length);
        strip.setLength(buffer.getLength());
        button1 = new KeyButton(1);
        button2 = new KeyButton(2);
        button3 = new KeyButton(3);

        currentState = BarelLeds.LEDSystemState.Off;
        wantedState = LEDWantedState.Off;
    }

    public void turnOnLed(Color color, int index) {
        buffer.setRGB(index, color.getRed(), color.getBlue(), color.getGreen());
    }

    public void paintAll(Color color) {
        for (int i = 0; i < length; i++) {
            buffer.setRGB(i, color.getRed(), color.getGreen(), color.getBlue());
        }
        show();
    }

    public void show() {
        strip.setData(buffer);
    }

    public void periodic() {
        updateWantedState();
        currentState = handleStateTransition();
        applyState();
    }

    public void updateWantedState() {
        if (button1.isPressed()){
            wantedState = LEDWantedState.Right;
        }
        else if (button2.isPressed()){
            wantedState = LEDWantedState.Left;
        } else if (button3.isPressed()) {
            wantedState = LEDWantedState.Off;
        }
        else {
            wantedState = LEDWantedState.Idle;
        }
    }
    public LEDSystemState handleStateTransition (){
        switch (wantedState){
            case Right:
                switch (currentState) {
                    case Green:
                        return LEDSystemState.Red;
                    case Red:
                        return LEDSystemState.Blue;
                    case Off:
                        return LEDSystemState.Green;
                    case Blue:
                        return LEDSystemState.Blue;
                }
            case Left:
                switch (currentState) {
                    case Blue:
                        return LEDSystemState.Red;
                    case Red:
                        return LEDSystemState.Green;
                    case Green:
                        return LEDSystemState.Green;
                    case Off:
                        return LEDSystemState.Blue;
                }
            case Idle:
                return currentState;
            case Off:
                return LEDSystemState.Off;
            default:
                return currentState;
        }
    }
    public void applyState(){
        switch (currentState){
            case Off:
                paintAll(Color.BLACK);
                break;
            case Blue:
                paintAll(Color.BLUE);
                break;
            case Green:
                paintAll(Color.GREEN);
                break;
            case Red:
                paintAll(Color.RED);
                break;
        }

    }
}


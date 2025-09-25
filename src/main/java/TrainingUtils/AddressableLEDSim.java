package TrainingUtils;

import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.MechanismLigament2d;

import static TrainingUtils.LedConstants.LedSimulationConstants.initializeLedMechanism;

public class AddressableLEDSim {

    private MechanismLigament2d[] LED;

    // on WPI_AddressableLED you would get a PWM port as an input to the constructor
    public AddressableLEDSim() {
        LED = new MechanismLigament2d[0];   // temp: len = 0 - for consistency with WPI_AddressableLED
        initializeLedMechanism(LED);
    }

    // original WPI_AddressableLED function
    public void setLength(int length) {
        LED = new MechanismLigament2d[length];
        initializeLedMechanism(LED);
    }

    // original WPI_AddressableLED function
    public void setData(AddressableLEDBuffer buffer) {
        int subdivisions = buffer.getLength() / LED.length; // the simulated led length may not be equal to the buffer
        Shuffleboard.getTab("ledsim").addNumber("numnum", ()->subdivisions);
        for (int i = 0; i < LED.length; i++) {
            LED[i].setColor(buffer.getLED8Bit(subdivisions * i));
        }
    }
}
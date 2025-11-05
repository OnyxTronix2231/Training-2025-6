package L8.training;

import TrainingUtils.AddressableLEDSim;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;

public class Robot {
    String name;
    double weight;
    AddressableLEDSim addressableLEDSim;
    AddressableLEDBuffer buffer;

    public Robot(String name, double weight) {
        this.name = name;
        this.weight = weight;
        addressableLEDSim = new AddressableLEDSim();
        buffer = new AddressableLEDBuffer(7);
    }

    public void setName(String name) {
        this.name = name;
    }
}

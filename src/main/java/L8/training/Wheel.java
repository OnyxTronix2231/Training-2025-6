package L8.training;

import java.util.function.DoubleSupplier;

public class Wheel {

    private Motor engine;
    private DoubleSupplier SpeedSupplier;

    public Wheel(Motor engine, DoubleSupplier SpeedSupplier) {
        this.engine = engine;
        this. SpeedSupplier = SpeedSupplier;
    }

    public void moveMotor() {
        
    }
}

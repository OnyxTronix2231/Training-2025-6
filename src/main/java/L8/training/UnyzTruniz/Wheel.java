package L8.training.UnyzTruniz;

import java.util.function.DoubleSupplier;

public class Wheel  {
    protected Motor motor;
    protected DoubleSupplier speedSupplier;

    public Wheel(DoubleSupplier speedSupplier,Motor engine) {
        this.speedSupplier = speedSupplier;
        this.motor = engine;
    }
    public void moveFromSup(){
        motor.setSpeed(speedSupplier.getAsDouble());
    }
    public void stopRobot(){
        motor.setSpeed(0);
        motor.setTarget(0);
    }
    
}

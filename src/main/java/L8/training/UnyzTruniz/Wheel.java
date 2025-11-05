package L8.training.UnyzTruniz;

import java.util.function.DoubleSupplier;

public class Wheel {
    Motor motor;
    DoubleSupplier speedSupplier;

    public Wheel(Motor motor, DoubleSupplier speedSupplier) {
        this.motor = motor;
        this.speedSupplier = speedSupplier;
    }

    public void updateMotor() {
        motor.setSpeed(speedSupplier.getAsDouble());
    }

    public void stop() {
        motor.setSpeed(0);
    }

    
}

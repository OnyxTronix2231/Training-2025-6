package L8.training.UnyzTruniz;

import java.util.function.DoubleSupplier;

public class Robot {
    Wheel leftWheel;
    Wheel rightWheel;
    DoubleSupplier controller;

    public Robot(Wheel leftWheel, Wheel rightWheel, DoubleSupplier controller) {
        robotInit(leftWheel, rightWheel, controller);
    }

    private void robotInit(Wheel leftWheel, Wheel rightWheel, DoubleSupplier controller) {
        this.leftWheel = new Wheel(new NEO(), () -> controller.getAsDouble());
        this.rightWheel = new Wheel(new Talon(), () -> controller.getAsDouble());
    }

    public void periodic() {
        leftWheel.updateMotor();
        rightWheel.updateMotor();
    }
}

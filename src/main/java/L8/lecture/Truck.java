package L8.lecture;

import java.awt.*;

public class Truck implements Noisy, Drivable{
    private Color color;
    private int maxSpeed;
    private int currentSpeed;

    public Truck(Color color, int maxSpeed) {
        this.color = color;
        this.maxSpeed = maxSpeed;
        this.currentSpeed = 0;
    }

    @Override
    public void makeNoise() {
        for (int i = 0; i < currentSpeed; i++) {
            System.out.println("vroom");
        }
    }

    @Override
    public void drive(int speed) {
        currentSpeed = Math.min(speed, maxSpeed);
    }
}

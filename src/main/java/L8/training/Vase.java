package L8.training;

import java.awt.*;

public class Vase implements Breakable {
    Color color;
    double diameter;
    double height;
    int pieces;

    public Vase(Color color, double diameter, double height) {
        this.color = color;
        this.diameter = diameter;
        this.height = height;
    }

    @Override
    public int breakIt(double speed) {
        pieces = (int) (diameter * height / speed);
        return pieces;
    }

    @Override
    public boolean fix() {
        return pieces < 25;
    }

    public double getSize() {
        return diameter * height;
    }
}

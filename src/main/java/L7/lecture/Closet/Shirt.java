package L7.lecture.Closet;

import java.awt.*;

public class Shirt extends Clothing{
    private Color color;

    public Shirt(int size, boolean isClean, Color color) {
        super(size, isClean);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}

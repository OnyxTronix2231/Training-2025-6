package L7.training.Clothing;

import java.awt.*;

public class Shirt extends Clothing {
    protected Color color;

    public Shirt(Color color, int size, boolean isClean) {
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

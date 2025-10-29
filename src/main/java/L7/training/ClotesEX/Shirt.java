package L7.training.ClotesEX;

import L3.training.Color;

public class Shirt extends Cloth {

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

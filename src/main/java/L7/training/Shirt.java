package L7.training;

public class Shirt extends Clothes {

    protected String Color;

    public Shirt(int Size, boolean isClean, String Color) {
        super(Size, isClean);
        this.Color = Color;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }
}

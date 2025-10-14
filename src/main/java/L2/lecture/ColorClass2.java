package L2.lecture;

public class ColorClass2 {
    private int red;
    private int green;
    private int blue;

    public ColorClass2(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public void lowerLight() {
        red /= 2;
        green /= 2;
        blue /= 2;
    }

    public int getLightAverage() {
        int avg = (red + green + blue) / 3;
        return avg;
    }

    public boolean isHighGreen(int newGreen) {
        boolean result = newGreen < green;
        return result;
    }

    // =======================================================

    public void addColor(int newRed, int newGreen, int newBlue) {
        red += newRed;
        green += newGreen;
        blue += newBlue;
    }

    public boolean smallerThanAll(int value) {
        boolean result = value < red && value < green && value < blue;
        return result;
    }

    // =======================================================

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    // =======================================================

    public static ColorClass2 addColors(ColorClass2 color1, ColorClass2 color2) {
        int resultRed = color1.getRed() + color2.getRed();
        int resultGreen = color1.getGreen() + color2.getGreen();
        int resultBlue = color1.getBlue() + color2.getBlue();

        ColorClass2 result = new ColorClass2(resultRed, resultGreen, resultBlue);
        return result;
    }
}



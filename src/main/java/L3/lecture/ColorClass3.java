package L3.lecture;

import edu.wpi.first.math.MathUtil;

public class ColorClass3 {
    private int red;
    private int green;
    private int blue;

    public ColorClass3(int red, int green, int blue) {
        this.red = MathUtil.clamp(red, 0, 255);
        this.green = MathUtil.clamp(green, 0, 255);
        this.blue = MathUtil.clamp(blue, 0, 255);
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
        red = MathUtil.clamp(red + newRed, 0, 255);
        green = MathUtil.clamp(green + newGreen, 0, 255);
        blue = MathUtil.clamp(blue + newBlue, 0, 255);
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
        this.red = MathUtil.clamp(red, 0, 255);
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = MathUtil.clamp(green, 0, 255);
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = MathUtil.clamp(blue, 0, 255);
    }

    // =======================================================

    public static ColorClass3 addColors(ColorClass3 color1, ColorClass3 color2) {
        int resultRed = color1.getRed() + color2.getRed();
        int resultGreen = color1.getGreen() + color2.getGreen();
        int resultBlue = color1.getBlue() + color2.getBlue();

        ColorClass3 result = new ColorClass3(resultRed, resultGreen, resultBlue);
        return result;
    }

    public String toString() {
        String redMsg = "Red: " + red;
        String greenMsg = "Green: " + green;
        String blueMsg = "Blue: " + blue;
        return "Color:\n\t" + redMsg + "\n\t" + greenMsg + "\n\t" + blueMsg;
    }
}



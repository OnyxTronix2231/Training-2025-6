package L2.training;

public class Color {
    private int red;
    private int green;
    private int blue;

    public Color(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public static Color getFactoredColor(Color color, int percentage) {
        color.red = color.red * percentage / 100;
        color.green = color.green * percentage / 100;
        color.blue = color.blue * percentage / 100;

        return color;
    }

    public static Color addByFactor(Color red, Color green, int percentage) {
        int newRed = red.red * percentage / 100;
        int newGreen = green.green * (100 - percentage) / 100;
        return new Color(newRed, newGreen, 0);
    }

    private void clear() {
        red = 0;
        green = 0;
        blue = 0;
    }

    public void addColors(int addRed, int addGreen, int addBlue) {
        red += addRed;
        green += addGreen;
        blue += addBlue;
    }

    public boolean isLowerThanValues(int value) {
        boolean highRed = value < red;
        boolean highGreen = value < green;
        boolean highBlue = value < blue;
        return highRed && highGreen && highBlue;
    }

    public int changeValues(int value) {
        red += value;
        green = green * green;
        return red + green + blue;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }
}

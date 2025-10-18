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

    // =======================================================

    public void clear() {
        red = 0;
        green = 0;
        blue = 0;
    }

    public static ColorClass2 getFactoredColor(ColorClass2 color, int percentage) {
        int red = color.getRed() * percentage / 100;
        int green = color.getGreen() * percentage / 100;
        int blue = color.getBlue() * percentage / 100;
        return new ColorClass2(red, green, blue);
    }

    public static ColorClass2 addByFactor(ColorClass2 c1, ColorClass2 c2, int percentage) {
        c1 = ColorClass2.getFactoredColor(c1, percentage);
        c2 = ColorClass2.getFactoredColor(c2, 100 - percentage);
        return ColorClass2.addColors(c1, c2);
    }

    public static void main(String[] args) {
        ColorClass2 c1 = new ColorClass2(200, 0, 0);
        ColorClass2 c2 = new ColorClass2(0, 200, 0);
        ColorClass2 c3 = ColorClass2.addByFactor(c1, c2, 25);

        System.out.println(c3.getGreen());
    }
}



package L2.training;

public class Color {
    private int red;
    private int blue;
    private int green;

    Color(int red, int blue, int green) {
        this.red = red;
        this.blue = blue;
        this.green = green;
    }

    public int getRed() {
        return red;
    }

    public int getBlue() {
        return blue;
    }

    public int getGreen() {
        return green;
    }

    public void setRed(int newRed) {
        red = newRed;
    }

    public void setBlue(int newBlue) {
        blue = newBlue;
    }

    public void setGreen(int newGreen) {
        green = newGreen;
    }

    public void add_to_newcolor(int new_red, int new_green, int new_blue) {
        new_red += red;
        new_green += green;
        new_blue += blue;
    }

    public boolean bigger_than_others(int num) {
        boolean red_check = num < red;
        boolean green_check = num < green;
        boolean blue_check = num < blue;
        return red_check && green_check && blue_check;
    }

    public int three_functions(int value) {
        red += value;
        green *= green;
        return red + green + blue;
    }

    public void clear_colors() {
        red = 0;
        blue = 0;
        green = 0;
    }

    public static Color getFactoredColor(int percentage , Color c1) {
        c1.red = c1.red*percentage/100;
        c1.blue = c1.blue*percentage/100;
        c1.green = c1.green*percentage/100;
        return c1;
    }
    public static Color addByFactor(int color1, int color2, int percentage){
        Color c2 = new Color(color1*percentage/100,color2*(100-percentage)/100,0);
        return c2;
    }
    public static void main(String[] args) {

        Color red = new Color(255, 0, 0);
        getFactoredColor(25,red);
        System.out.println(red.red);
        Color blue = new Color(0, 255, 0);

        Color green = new Color(0, 0, 255);
    }
}

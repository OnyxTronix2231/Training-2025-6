package L3;

public class Color {

    private int red;
    private int green;
    private int blue;

        public Color(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;

    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public void addColor(int Newred, int Newgreen, int Newblue) {
        red += Newred;
        green += Newgreen;
        blue += Newblue;

    }

    public boolean checkColor(int Value) {
        boolean resultRed = Value < red;
        boolean resultGreen = Value < green;
        boolean resultBlue = Value < blue;
        return resultRed && resultGreen && resultBlue;
    }

    public int mathColor(int Num) {
        red += Num;
        green *= green;
        return red + green + blue;

    }

    public void clearColor() {
        red = 0;
        green = 0;
        blue = 0 ;
    }
    
    public static void main(String[] args) {

        Color colorRed = new Color(255, 0, 0);

        Color colorGreen = new Color(0, 255, 0);

        Color colorBlue = new Color(0, 0, 255);

    }
}

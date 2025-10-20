package L2.lecture;

public class Color {

    private int red;
    private int green;
    private int blue;


    Color (int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public void setRed(int red){
        this.red = red;
    }

    public void addColor(int newGreen,int newBlue,int newRed){

        green += newGreen;
        blue += newBlue;
        red += newRed;

    }

    public boolean bigOrSmall(int oneMoreColor){
        boolean resultGreen = oneMoreColor < this.green;
        boolean resultBlue = oneMoreColor < this.blue;
        boolean resultRed = oneMoreColor < this.red;
        System.out.println(resultBlue);
        return resultBlue && resultRed && resultGreen;
    }

    int mix(int val){
        red += val;
        green = green * green;
        return green + red + blue;
    }

    void mmmColor (int oneMoreMoreColor){
        red =  red + oneMoreMoreColor;
        green = green*green;
        int allColor = (red + green + blue  );
    }

    void clear() {
        red = 0;
        green = 0;
        blue = 0;
    }

    static Color getFactoredColor(Color color, int precentage) {
        return new Color(color.red*precentage/100,
                color.green*precentage/100,
                color.blue*precentage/100);
    }

    public string toString (){
        string redMessage = "red: " + red;
        string greenMessage = "green: " + green;
        string blueMessage = "blue: " + blue;
        return redMessage + ","  + greenMessage + ","  + blueMessage;



    }


    public static void main(String[] args) {

        Color redColor = new Color(200, 0, 0);
        Color factored_color = redColor.getFactoredColor(redColor, 25);
        System.out.println(factored_color.red);
        redColor.setRed(80);
        System.out.println(redColor.red);
    }

    static Color addByFactor(Color firstColor, Color secondColor, int percentage)
    {
        return new Color(firstColor.red * percentage / 100 + secondColor.red * (100 - percentage) / 100,
                firstColor.green * percentage / 100 + secondColor.green * (100 - percentage) / 100,
                    firstColor.blue * percentage / 100 + secondColor.blue * (100 - percentage) / 100
                );
    }
}

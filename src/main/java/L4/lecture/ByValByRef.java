package L4.lecture;

import L3.lecture.ColorClass3;

public class ByValByRef {

    private static int green;

    public static void addInt(int x) {
        x += 1;
    }

    public static void addRed(ColorClass3 color) {
        color.setRed(color.getRed() + 1);
    }

    public static void main(String[] args) {
        int x = 5;
        System.out.println(x);
        addInt(x);
        System.out.println(x);

        ColorClass3 color = new ColorClass3(200, 0, 0);
        System.out.println(color.getRed());
        addRed(color);
        System.out.println(color.getRed());
    }
}

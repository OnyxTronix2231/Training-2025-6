package L8.training;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Vase vase = new Vase(Color.BLACK, 5, 20);
        System.out.println(vase);
        System.out.println(vase.breakIt(5));
        System.out.println("I got it fixed: " + vase.fix());
    }


}

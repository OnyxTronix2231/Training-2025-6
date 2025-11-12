package L8.lecture;

import java.awt.*;

public class Vase implements Breakable{

    private Color color;
    private int age;


    @Override
    public int toBreak(double speed) {
        return (int)(5 * speed);
    }

    @Override
    public boolean fix() {
        System.out.println("two weeks later...");
        return true;
    }
}

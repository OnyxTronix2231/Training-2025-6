package L7.training;

import L7.training.Clothing.Clothing;
import L7.training.Clothing.Jeans;
import L7.training.Clothing.Shirt;
import L7.training.SpeedController.SpeedController;
import L7.training.Worker.Programmer;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Shirt shirt = new Shirt(Color.BLACK, 405, false);
        Clothing.takeCareOf(shirt);
        System.out.println(shirt.getColor());
        Jeans jeans = new Jeans(400, false);
        System.out.println(jeans.getSize());
        Clothing.takeCareOf(jeans);
        System.out.println(jeans.getSize());

        Programmer programmer = new Programmer("JavaScript", "Exist", 30000, 5);
        System.out.println(programmer.calculateMonthlySalary(100));

        SpeedController speedController = new SpeedController("A", 0.3);
        System.out.println(speedController);
    }
}

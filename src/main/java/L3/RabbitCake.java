package L3;

public class RabbitCake {
    public static void main(String[] args) {
        double rabbitShekels = 500;
        double rabbitDollars = rabbitShekels / 3.29;
        double Cakes = rabbitDollars / 5;
        double Change = rabbitDollars % 5;
        double cakeMoney = (rabbitDollars - Change) / 5;
        boolean Cakes40 = rabbitDollars / 4 >= 40;

        System.out.println("The money the rabbit has in dollars is: " + rabbitDollars);
        System.out.println("The amount of cakes the rabbit can buy is: " + Cakes);
        System.out.println("The rabbit will have " + Change + "dollars left");
        System.out.println("The rabbit can buy " + cakeMoney + " whole cakes");
        System.out.println("Can he buy 40? " + Cakes40);
    }
}

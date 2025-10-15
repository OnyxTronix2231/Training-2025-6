package L2.training;

public class FlyLife {
    public static void main(String[] args) {
        int birthYear = 2010;
        int Age = 2025 - birthYear;
        int daysAlive = Age * 365;

        int avgFly = 17;
        int oldFromFly = daysAlive / avgFly;
        int daysLeft = daysAlive % avgFly;
        int deadFlies = avgFly - daysAlive % avgFly;

        System.out.println("Your age is: " + Age);
        System.out.println("The number od days in your life is: " + daysAlive);
        System.out.println("You are" + oldFromFly + "times older than a fly");
        System.out.println("The number of days left is: " + daysLeft);
        System.out.println(deadFlies + "flies died during your lifetime");

    }

}
